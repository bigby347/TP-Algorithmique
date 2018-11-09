import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import java.util.ArrayList;

public class TestTrigramsDictionnary {

    @Test
    public void TestsetTrigramsList() {
        ArrayList<String> trigrams = new ArrayList<>();
        trigrams.add("aca");
        trigrams.add("cap");
        trigrams.add("apu");
        trigrams.add("pul");
        trigrams.add("ulc");
        trigrams.add("lco");
        TrigramsDictionnary dico = new TrigramsDictionnary();
        assertThat(dico.setTrigramsList("Acapulco"),equalTo(trigrams));
    }
}
