/**
 * It represents meta information about token.
 */
public class TokenDescription {
    private String token;
    private String lowerCaseToken;
    private String punctuationMarks = "’'[](){}:,??–—?!.?-?;/‘ ’“”''\"\"";
    private int tokenLength;

    public TokenDescription(String token) {
        this.token = token;
        lowerCaseToken = token.toLowerCase();
        tokenLength = token.length();
    }

    boolean startsWithVowel(){
        return lowerCaseToken.matches("^[aeiou].*");
    }

    boolean endsWithWay(){
        return lowerCaseToken.endsWith("way");
    }

    boolean[] punctuationMarks() {
        boolean[] punctuations = new boolean[tokenLength];
        for(int i = 0; i < tokenLength; i++) {
            punctuations[i] = punctuationMarks.indexOf(token.charAt(i)) != -1;
        }
        return punctuations;
    }

    boolean[] upperCaseChars() {
        boolean[] upperCaseChars = new boolean[tokenLength];
        for(int i = 0; i < tokenLength; i++) {
            upperCaseChars[i] = token.charAt(i) != lowerCaseToken.charAt(i);
        }
        return upperCaseChars;
    }

    public boolean startsWithConsonant() {
        return !startsWithVowel() && !punctuationMarks.contains(token.substring(0, 1));
    }
}
