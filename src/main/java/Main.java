import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.apache.commons.lang3.StringUtils;
import javax.xml.bind.annotation.XmlElementDecl;
import java.util.*;

//this is the main function where all the magic happens.
public class Main {

    public static void main(String[] args) {

        String contextEntry = "Cyberpunk is a good game."; //enter context here. please only enter one sentence. right now this only works with 1 sentence of context.
        String statement = "Cyberpunk is not a good game."; // enter statement here. Please only enter one sentence.
        StanfordCoreNLP cnlp = Pipeline.getPline(); //gets pipeline
        CoreDocument context = new CoreDocument(contextEntry); //provides context to pipeline as a CoreDocument, a coredocument is the format in which the pipeline can work on a string.
        CoreDocument cd = new CoreDocument(statement); // provides input statement to pipeline as a CoreDocument.
        cnlp.annotate(cd); // lets pipeline work on parameters.
        cnlp.annotate(context); //lets pipeline work on paramenters
        List convertedtext = new ArrayList(); // initiates a list where the PLL version of the text will be stored.
        List convertedcontext = new ArrayList(); // initiates a list where the PLL version of the context will be stored.

        List < CoreLabel > cll = context.tokens(); // tokenizes the context
        for (CoreLabel call: cll) {
            String lemma = call.lemma();
            convertedcontext.add(lemma);
        }
        List < CoreLabel > cll2 = cd.tokens(); //tokenizes the text
        for (CoreLabel call: cll2) {
            String lemma = call.lemma();
            convertedtext.add(lemma);
        }

        String convertedcontextString = convertedcontext.toString(); //converts context in PLL form into a string
        String convertedtextString = convertedtext.toString(); // converts text in PLL form into a string

        String[] negatory = { // this is where the logical operator of negation is analogous to natural language
                "not",
                "no",
                "none"
        };
        String[] conjugation = { // this is where the logical operator of conjugation is analogous to natural language
                "be, a",
                "must, be",
                "have, to"
        };
        String[] conditional = { // this is where the logical operator of condinationals is analogous to natural language
                "if",
                "therefore",
                "because"
        };
        List < String > negato = Arrays.asList(negatory); // lines 55 to 61 are bunch of inititations for use further below
        List < String > cond = Arrays.asList(conditional);
        List < String > conj = Arrays.asList(conjugation);
        boolean negcheck;
        boolean condcheck;
        boolean conjcheck;
        int n = convertedtextString.length();
        for (int i = 0; i < n; i++) { // this codes itterates over all possible substrings in the input string and compares it with the string arrays initiated above to see if any can be replaced by a logical operator. it also marks a boolean as positive if there is a place where a logical operator can be used.
            for (int j = i + 1; j < n; j++) {
                negcheck = negato.contains(convertedtextString.substring(i, j));
                condcheck = cond.contains(convertedtextString.substring(i, j));
                conjcheck = conj.contains(convertedtextString.substring(i, j));
            }
        }
        int ah = negatory.length;
        if (negcheck = true) { //if the code on line 62 finds something, this code replaces the natural langauge part of the text with a logical operator.
            for (int i = 0; i < ah; i++) {
                String stringo = convertedtextString.replace(negatory[i], "negation");
                convertedtextString = stringo;
            }
        }
        int ahh = conjugation.length;
        if (conjcheck = true) { //if the code on line 62 finds something, this code replaces the natural langauge part of the text with a logical operator.
            for (int i = 0; i < ahh; i++) {
                String stringo = convertedtextString.replace(conjugation[i], "and");
                convertedtextString = stringo;

            }
        }
        int ahhh = conditional.length;
        if (condcheck = true) { //if the code on line 62 finds something, this code replaces the natural langauge part of the text with a logical operator.
            for (int i = 0; i < ahhh; i++) {
                String stringo = convertedtextString.replace(conditional[i], "cond starts");
                convertedtextString = stringo;

            }
        }
        int m;
        m = convertedcontextString.length();
        for (int i = 0; i < m; i++) { //this is the same code from line 62 but for the context
            for (int j = i + 1; j < m; j++) {
                negcheck = negato.contains(convertedcontextString.substring(i, j));
                condcheck = cond.contains(convertedcontextString.substring(i, j));
                conjcheck = conj.contains(convertedcontextString.substring(i, j));
            }
        }
        int ah1 = negatory.length;
        if (negcheck = true) { //if the code on line 94 finds something, this code replaces the natural langauge part of the text with a logical operator.
            for (int i = 0; i < ah1; i++) {
                String stringo2 = convertedcontextString.replace(negatory[i], "negation");
                convertedcontextString = stringo2;
            }
        }
        int ahh1 = conjugation.length;
        if (conjcheck = true) { //if the code on line 94 finds something, this code replaces the natural langauge part of the text with a logical operator.
            for (int i = 0; i < ahh1; i++) {
                String stringo2 = convertedcontextString.replace(conjugation[i], "and");
                convertedcontextString = stringo2;
            }
        }
        int ahhh1 = conditional.length;
        if (condcheck = true) { //if the code on line 94 finds something, this code replaces the natural langauge part of the text with a logical operator.
            for (int i = 0; i < ahhh1; i++) {
                String stringo2 = convertedcontextString.replace(conditional[i], "cond starts");
                convertedcontextString = stringo2;
            }
        }

        //code below converts the PLL format context into an array. contextfinal string is the fully converted form of the context from natural langauage in PLL
        List < String > contextfinal = new ArrayList < String > ();
        String strcontext[] = convertedcontextString.split(",");
        contextfinal = Arrays.asList(strcontext);
        contextfinal = new ArrayList < > (contextfinal);
        //System.out.println(contextfinal); use this line to see what the context looks like having been converted from natural langauge to PLL

        //code below converts the PLL format text into an array
        List < String > textfinal = new ArrayList < String > ();
        String strtext[] = convertedtextString.split(",");
        textfinal = Arrays.asList(strtext);
        textfinal = new ArrayList < > (textfinal);
        //System.out.println(textfinal); use this line to see what the input text look like having been converted from natural language to PLL

        textfinal.removeAll(contextfinal); //this line compares the text and context PLL formats and elminates the common parts leaving just the differences between the two.
        String negs = "negation";
        String bob = textfinal.toString();
        if (bob.toLowerCase().indexOf(negs.toLowerCase()) != -1) { //this line checks for negation and prints if true
            System.out.println("The context statement differs from the input statement. The input statement might be a lie.");
        }
        String backwards = "cond starts";
        if (bob.toLowerCase().indexOf(backwards.toLowerCase()) != -1) { //this line checks for backwards logic and prints if true
            System.out.println("This might be a case of backwards logic.");
        }
    }
}