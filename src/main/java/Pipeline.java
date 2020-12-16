import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

//pipeline class that generates a pipeline and uses it to execute functions of the data entered.
public class Pipeline {

    private static Properties props;
    private static StanfordCoreNLP scnlp;
    private static String propname = "tokenize, ssplit, pos, lemma, ner"; // annotator strings. this is where we add the abilities for our pipeline.

    private Pipeline(){


    }

    static {
        props = new Properties();
        props.setProperty("annotators", propname); //set annotator strings as properties

    }

    public static StanfordCoreNLP getPline() {

        if (scnlp == null) {
            scnlp = new StanfordCoreNLP(props);

        }
        return scnlp;
    }


}
