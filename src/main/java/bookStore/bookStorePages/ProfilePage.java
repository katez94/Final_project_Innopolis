package bookStore.bookStorePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage {

    @FindBy (id = "userName-value")
    private static SelenideElement userNameLabel;

    @FindBy (id = "searchBox")
    private static SelenideElement profileSearchBox;

    @FindBy (id = "basic-addon2")
    private static SelenideElement searchBtn;

    @FindBy (xpath = "//div[@class='text-right col-md-4 col-sm-12']/button")
    private static SelenideElement logOutBtn;

    @FindBy (xpath = "//*[@class=\"text-left button\"]/button")
    private static SelenideElement gotoBookStoreBtn;

    @FindBy (css = ".text-center.button > button")
    private static SelenideElement deleteAccountBtn;

    @FindBy (css = ".text-right.button.di > button")
    private static SelenideElement deleteAllBooksBtn;

    @FindBy(css = ".-previous > button")
    private static SelenideElement previousButton;

    @FindBy(css = ".-next > button")
    private static SelenideElement nextButton;

    @FindBy(css = ".-pageSizeOptions > select")
    private static SelenideElement rowsSelector;

    @FindBy(css = ".-pageJump > input")
    private static SelenideElement pageNumber;

    @FindBy(css = ".action-buttons > span > a")
    private static List<SelenideElement> booksTitles;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[3][contains(text(), ' ')]")
    private static List<SelenideElement> booksAuthors;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[4][contains(text(), ' ')]")
    private static List<SelenideElement> booksPublishers;

    @FindBy(id = "delete-record-undefined")
    private static List<SelenideElement> deleteActionBtns;

    public static SelenideElement getBookAuthor(int index) {
        return booksAuthors.get(index);
    }
    public static SelenideElement getBookPublisher(int index) {
        return booksPublishers.get(index);
    }
    public static void clickGoToBookStoreBtn() {
        gotoBookStoreBtn.click();
    }

    public static SelenideElement getProfileSearchBox() {
        return profileSearchBox;
    }

    public static SelenideElement getSearchBtn() {
        return searchBtn;
    }

    public static SelenideElement getLogOutBtn() {
        return logOutBtn;
    }

    public static SelenideElement getGotoBookStoreBtn() {
        return gotoBookStoreBtn;
    }

    public static SelenideElement getDeleteAccountBtn() {
        return deleteAccountBtn;
    }

    public static SelenideElement getDeleteAllBooksBtn() {
        return deleteAllBooksBtn;
    }

    public static SelenideElement getPreviousButton() {
        return previousButton;
    }

    public static SelenideElement getNextButton() {
        return nextButton;
    }

    public static SelenideElement getRowsSelector() {
        return rowsSelector;
    }

    public static SelenideElement getPageNumber() {
        return pageNumber;
    }

    public static List<SelenideElement> getBooksTitles() {
        return booksTitles;
    }

    public static List<SelenideElement> getBooksAuthors() {
        return booksAuthors;
    }

    public static List<SelenideElement> getBooksPublishers() {
        return booksPublishers;
    }

    public static List<SelenideElement> getDeleteActionBtns() {
        return deleteActionBtns;
    }

    public static void clickSearchBtn() {
        searchBtn.click();
    }

    public static void clickLogOutBtn() {
        logOutBtn.click();
    }

    public static void clickDeleteAccountBtn() {
        deleteAccountBtn.click();
    }

    public static void clickDeleteAllBooksBtn() {
        deleteAllBooksBtn.click();
    }

    public static void clickPreviousBtn() {
        previousButton.click();
    }

    public static void clickNextBtn() {
        nextButton.click();
    }

    public static void findBooks(String keys) {
        profileSearchBox.sendKeys(keys);
    }

    public static SelenideElement getBookTitle(int index) {
        return booksTitles.get(index);
    }

    public static List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();
        for (SelenideElement title : booksTitles) {
            titles.add(title.getText());
        }
        return titles;
    }
    public static void selectRow(String value){
        Select rows = new Select(rowsSelector);
        rows.selectByValue(value);
    }

    public static String getPageFieldValue() {
        return pageNumber.getAttribute("value");
    }

//    public SelenideElement get(int bookIndex) {
//        return deleteActionBtns.get(bookIndex);
//    }

    public static String getUserNameLabel() {
        return userNameLabel.getText();
    }
}
