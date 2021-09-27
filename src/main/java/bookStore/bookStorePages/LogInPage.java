package bookStore.bookStorePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;


public class LogInPage {

    @FindBy(className = "main-header")
    private static SelenideElement header;

    @FindBy(xpath = "//*[@id=\"userForm\"]/div[1]/h2")
    private static SelenideElement titleH2;

    @FindBy(xpath = "//*[@id=\"userForm\"]/div[1]/h5")
    private static SelenideElement titleH5;

    @FindBy(id = "userName-label")
    private static SelenideElement usernameLabel;

    @FindBy(id = "password-label")
    private static SelenideElement passwordLabel;

    @FindBy(xpath = "//*[@id='userName']")
    private static SelenideElement userNameField;

    @FindBy(id = "password")
    private static SelenideElement passwordField;

    @FindBy(xpath = "//div[@class='mt-2 buttonWrap row']/div[1]/button")
    private static SelenideElement loginBtn;

    @FindBy(xpath = "//div[@class='mt-2 buttonWrap row']/div[2]/button")
    private static SelenideElement newUserBtn;

    public static void inputUserName(String userName){
        userNameField.sendKeys(userName);
    }

    public static void inputPassword(String password){
        passwordField.sendKeys(password);
    }

    public static void clickLogInBtn() {
        loginBtn.click();
    }

    public static void clickNewUserBtn() {
        newUserBtn.click();
    }

    public  static SelenideElement getUserNameField() {
        return userNameField;
    }

    public static SelenideElement getPasswordField() {
        return passwordField;
    }

    public static SelenideElement getLoginBtn() {
        return loginBtn;
    }

    public static SelenideElement getNewUserBtn() {
        return newUserBtn;
    }

    public static SelenideElement getHeader() {
        return header;
    }

    public static SelenideElement getTitleH2() {
        return titleH2;
    }

    public static SelenideElement getTitleH5() {
        return titleH5;
    }

    public static SelenideElement getUsernameLabel() {
        return usernameLabel;
    }

    public static SelenideElement getPasswordLabel() {
        return passwordLabel;
    }


}
