import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Known limitations:
 *  It handles only characters from the ascii as vowels. Vowels from for example Czech won't work.
 *  Only these ’'[](){}:,??–—?!.?-?;/‘ ’“”''"" are considered punctuation marks.
 */
public class PigLatinTranslatorTest {
    private Translator translator;

    @Before
    public void setUp(){
        translator = new PigLatinTranslator();
    }

    @Test
    public void transformWordsStartingWithConsonant() {
        String toTranslate = "Hello";
        String result = translator.translateTo(toTranslate);
        assertThat(result, is("Ellohay"));

        toTranslate = "Pet";
        result = translator.translateTo(toTranslate);
        assertThat(result, is("Etpay"));
    }

    @Test
    public void transformWordsStartingWithVowel() {
        String toTranslate = "apple";
        String result = translator.translateTo(toTranslate);
        assertThat(result, is("appleway"));

        toTranslate = "End";
        result = translator.translateTo(toTranslate);
        assertThat(result, is("Endway"));
    }

    @Test
    public void transformWordEndingWithWay() {
        String toTranslate = "stairway";
        String result = translator.translateTo(toTranslate);
        assertThat(result, is("stairway"));

        toTranslate = "Peopleway";
        result = translator.translateTo(toTranslate);
        assertThat(result, is("Peopleway"));
    }

    @Test
    public void transformWordContainingPunctuation() {
        String toTranslate = "can't";
        String result = translator.translateTo(toTranslate);
        assertThat(result, is("antca'y"));

        toTranslate = "end.";
        result = translator.translateTo(toTranslate);
        assertThat(result, is("endway."));
    }

    @Test
    public void transformHyphenatedWord() {
        String toTranslate = "stair-away";
        String result = translator.translateTo(toTranslate);
        assertThat(result, is("tairsay-away"));

        toTranslate = "People-magazine";
        result = translator.translateTo(toTranslate);
        assertThat(result, is("Eoplepay-agazinemay"));
    }

    @Test
    public void transformWithCapitalization(){
        String toTranslate = "sTaIr-Away";
        String result = translator.translateTo(toTranslate);
        assertThat(result, is("tAiRsay-Away"));

        toTranslate = "People-magaZIne";
        result = translator.translateTo(toTranslate);
        assertThat(result, is("Eoplepay-agazINemay"));
    }

    @Test
    public void transformParagraph() {
        String toTranslate = "Madam, it so fell out, that certain players\n" +
                "We o'er-raught on the way: of these we told him;\n" +
                "And there did seem in him a kind of joy\n" +
                "To hear of it: they are about the court,\n" +
                "And, as I think, they have already order\n" +
                "This night to play before him.";
        String result = translator.translateTo(toTranslate);
        String expectedResult = "Adammay, itway osay ellfay outway, hattay ertaincay layerspay\n" +
                "Eway oerw'ay-aughtray onway hetay ayway: ofway hesetay eway oldtay imhay;\n" +
                "Andway heretay idday eemsay inway imhay away indkay ofway oyjay\n" +
                "Otay earhay ofway itway: heytay areway aboutway hetay ourtcay,\n" +
                "Andway, asway Iway hinktay, heytay avehay alreadyway orderway\n" +
                "Histay ightnay otay laypay eforebay imhay.";
        assertThat(result, is(expectedResult));
    }


}
