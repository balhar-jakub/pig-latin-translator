import java.util.StringTokenizer;

/**
 * Naive implementation of translator for PigLatin.
 * TranslateTo takes text and translates it into pigLatin.
 */
public class PigLatinTranslator implements Translator {
    @Override
    public String translateTo(String text) {
        StringTokenizer parser = new StringTokenizer(text, " \t\n\r\f-", true);
        StringBuilder result = new StringBuilder();
        String translatedToken;
        boolean isDelimiter = false;
        while(parser.hasMoreTokens()) {
            String token = parser.nextToken();
            if(!isDelimiter) {
                translatedToken = translateToken(token);
            } else {
                translatedToken = token;
            }
            result.append(translatedToken);
            isDelimiter = !isDelimiter;
        }

        return result.toString();
    }

    private String translateToken(String token){
        String lowerCaseToken = token.toLowerCase();
        TokenDescription description = new TokenDescription(token);
        StringBuilder translatedToken = new StringBuilder();
        int tokenLength = token.length();
        if(description.endsWithWay()) {
            return token;
        }

        int moveModifier = 0;
        if(description.startsWithVowel()) {
            translatedToken.append(lowerCaseToken);
            translatedToken.append("way");
        } else if(description.startsWithConsonant()) {
            translatedToken.append(lowerCaseToken.substring(1));
            translatedToken.append(lowerCaseToken.charAt(0));
            translatedToken.append("ay");
            moveModifier = -1;
        }

        int actualLength = translatedToken.length();
        boolean[] upperCaseChars = description.upperCaseChars();
        boolean[] punctuationMarks = description.punctuationMarks();
        int moveBy = actualLength - tokenLength + 1;
        for(int i = 0; i < tokenLength; i++) {
            if(upperCaseChars[i]){
                // Replace with uppercase letter
                char letterToUppercase  = translatedToken.charAt(i);
                translatedToken.setCharAt(i, Character.toUpperCase(letterToUppercase));
            }
            if(punctuationMarks[i]){
                // Move the punctuation to correct location.
                int finalLocation = i + moveBy;
                translatedToken.insert(finalLocation, token.charAt(i));
                translatedToken.deleteCharAt(i + moveModifier);
            }
        }

        return translatedToken.toString();
    }
}
