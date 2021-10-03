package bookStore.bookStorePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BookStorePage {
 //   private WebDriver webDriver; // зачем тут нужен?

//    public BookStorePage(WebDriver webDriver) {
//        PageFactory.initElements(webDriver,this);
//        this.webDriver = webDriver;
//    }

    @FindBy(id = "searchBox")
    private static SelenideElement searchBox;

    @FindBy(css = ".action-buttons > span > a")
    private static List<SelenideElement> booksTitles;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[3][contains(text(), ' ')]")
    private static List<SelenideElement> booksAuthors;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[4][contains(text(), ' ')]")
    private static List<SelenideElement> booksPublishers;

    @FindBy(css = ".-previous > button")
    private static SelenideElement previousButton;

    @FindBy(css = ".-next > button")
    private static SelenideElement nextButton;

    @FindBy(css = ".-pageSizeOptions > select")
    private static SelenideElement rowsSelector;

    @FindBy(css = ".-pageJump > input")
    private static SelenideElement pageNumber;

    @FindBy(css = ".-pageInfo > span")
    private static SelenideElement numberOfPages;

    @FindBy (xpath = "//*[@class='action-buttons']/span/a")
    private static SelenideElement chosenBook;

    @FindBy (xpath = "//*[@class='text-right fullButton']/button")
    private static SelenideElement addToCollectionBtn;

    @FindBy (xpath = "//*[@class='text-left fullButton']/button")
    private static SelenideElement backToBookStoreBtn;

    public static SelenideElement getAddToCollectionBtn() {
        return addToCollectionBtn;
    }

    public static SelenideElement getChosenBook() {
        return chosenBook;
    }

    public static void clickBackToStoreBtn() {
        backToBookStoreBtn.click();
    }

    public static SelenideElement getNextButton() {
        return nextButton;
    }

    public static SelenideElement getNumberOfPages() {
        return numberOfPages;
    }

    public static void findBooks(String keys) {
        searchBox.sendKeys(keys);
    }

    public static void clearSearchBox() {
        searchBox.clear();
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

    public static SelenideElement getBookAuthor(int index) {
        return booksAuthors.get(index);
    }

    public static List<String> getBookAuthors() {
        List<String> authors = new ArrayList<>();
        for (SelenideElement author : booksAuthors) {
            authors.add(author.getText());
        }
        return authors;
    }

    public static SelenideElement getBookPublisher(int index) {
        return booksPublishers.get(index);
    }

    public List<String> getBookPublishers() {
        List<String> publishers = new ArrayList<>();
        for (WebElement publisher : booksPublishers) {
            publishers.add(publisher.getText());
        }
        return publishers;
    }

    public static void clickPreviousButton() {
        previousButton.click();
    }

    public static void clickNextButton() {
        nextButton.click();
    }

    public static boolean checkEnabledPrevBtn() {
        return previousButton.isEnabled();
    }

    public static boolean checkEnabledNextBtn() {
        return nextButton.isEnabled();
    }

    public static SelenideElement getRowsSelector() {
        return rowsSelector;
    }

    public static void selectRow(String value){
        Select rows = new Select(rowsSelector);
        rows.selectByValue(value);
    }

    public static String getPageFieldValue() {
        return pageNumber.getAttribute("value");
    }

    public static void clickChosenBook() {
        chosenBook.click();
    }

    public static void clickAddToCollectionBtn() {
        addToCollectionBtn.click();
    }
}

