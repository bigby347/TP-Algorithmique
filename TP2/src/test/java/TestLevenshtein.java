import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class TestLevenshtein {


    @Test
    public void levenshteinDistanceTest(){
        String word1 ="niche";
        String word2 = "chiens";
        Levenshtein distance = new Levenshtein(word1);
        assertThat(distance.levenshteinDistance(word2),equalTo(5));
    }
}
