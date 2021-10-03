package hooks;

import com.codeborne.selenide.Configuration;

public class Hooks {

    public Hooks() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.timeout = 7000;
    }



}
