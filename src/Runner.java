/**
 * Command line runner.
 * Expects text to be translated as first parameter.
 */
public class Runner {
    public static void main(String[] args){
        Translator translator = new PigLatinTranslator();
        String pigLatin = translator.translateTo(args[0]);
        System.out.println(pigLatin);
    }
}
