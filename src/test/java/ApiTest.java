import bookStore.bookStoreModel.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static apiTestHelper.ApiTestHelper.*;


@Epic("ApiTest")
@Feature("BookStoreApiTest")
@Tag("api")
public class ApiTest {
    private static final User user = getUser();
    public static final String ISBN = getRandomIsbn();

    @BeforeEach
    public void deleteBooksInit() {
        deleteBooks(user.getUserId());
    }

    @Step("GetAllBooks")
    @Test
    void getAllBooksTest() {
        getListOfBooks();
    }

    @Step("GetOneBook")
    @Test
    void getBookByISBNTest() {
        Book book = getBookByISBN(ISBN);
        Assertions.assertEquals(ISBN, book.getIsbn());
    }

    @Step("AddListOfBooks")
    @Test
    void addListOfBooksToUserTest() {
        BooksToAdd bookToAdd = getBookToAdd(user.getUserId(), ISBN);
        addBooksToUser(bookToAdd, 201);
        addBooksToUser(bookToAdd, 400);
    }

    @Step("ReplaceBook")
    @Test
    void replaceBookTest() {
        String randomIsbn = getRandomIsbn();
        while (randomIsbn.equals(ISBN)) {
            randomIsbn = getRandomIsbn();
        }
        addBooksToUser(getBookToAdd(user.getUserId(), ISBN), 201);
        ReplaceIsbn replacableIsbn = new ReplaceIsbn(randomIsbn, getUser().getUserId());
        replaceBook(ISBN, replacableIsbn);
        addBooksToUser(getBookToAdd(user.getUserId(), ISBN), 201);
        addBooksToUser(getBookToAdd(user.getUserId(), randomIsbn), 400);
    }
}
