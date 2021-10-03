package uiTestHelper.statics;

import java.util.List;
import java.util.Random;

public class UITestHelper {

    public static String compareBooks(String firstBookTitle, String secondBookTitle, List<String> titles){
        if (secondBookTitle.equals(firstBookTitle)){
            secondBookTitle = titles.get(new Random().nextInt(titles.size()-1));
            compareBooks(firstBookTitle,secondBookTitle,titles);
        }
        return secondBookTitle;
    }
}
