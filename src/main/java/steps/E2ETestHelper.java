package steps;

import java.util.List;
import java.util.Random;

public class E2ETestHelper {

    public static String compareBooks(String firstBookTitle, String secondBookTitle, List<String> titles){
        if (secondBookTitle.equals(firstBookTitle)){
            compareBooks(firstBookTitle,getRandomTitle(titles),titles);
        }
        return secondBookTitle;
    }

    public static String getRandomTitle(List<String> titles) {
        return titles.get(new Random().nextInt(titles.size() - 1));
    }
}
