package apiTestHelper;

import bookStore.bookStoreModel.BooksToAdd;
import bookStore.bookStoreModel.CollectionOfIsbn;
import bookStore.bookStoreModel.LogInViewModel;
import io.restassured.http.Header;
import requests.BookStoreApi;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static bookStore.TestConst.TEST_USER_NAME;
import static bookStore.TestConst.TEST_USER_PASSWORD;

public class ApiTestHelper {

    public static final LogInViewModel logInViewModel = new LogInViewModel(TEST_USER_NAME, TEST_USER_PASSWORD);
    BookStoreApi bookStoreApi = new BookStoreApi();

    //todo написать что делает
    public  String getRandomIsbn() {
        return bookStoreApi.getListOfBooks().getBooks().get(new Random().nextInt(bookStoreApi.getListOfBooks().getBooks().size() - 1)).getIsbn();
    }

    public static BooksToAdd getBooksToAdd(String userId, List<CollectionOfIsbn> collection){
        return new BooksToAdd(userId, collection);
    }

    public BooksToAdd getBookToAdd(String userId, String isbn){
        CollectionOfIsbn collection = new CollectionOfIsbn();
        collection.setIsbn(isbn);
        return new BooksToAdd(userId, Collections.singletonList(collection));
    }

//    private static Header getAuthorizationHeader() {
//        LogInViewModel logInViewModel = new LogInViewModel(TEST_USER_NAME, TEST_USER_PASSWORD);
//        return new Header("Authorization", generateToken().getToken());
//    }
}
