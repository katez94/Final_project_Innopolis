package apiTestHelper;

import bookStore.TestConst;
import bookStore.bookStoreModel.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Random;

import static bookStore.TestConst.TEST_USER_NAME;
import static bookStore.TestConst.TEST_USER_PASSWORD;

public class ApiTestHelper {

    //todo request specification вынести все спецификации запросов


    private static final LogInViewModel logInViewModel = new LogInViewModel(TEST_USER_NAME, TEST_USER_PASSWORD);
    private static final Header AUTHORIZATION_HEADER = getAuthorizationHeader();

    public static Response getAllBooks() {
       return RestAssured.given()
                .log().all()
                .get("https://demoqa.com/BookStore/v1/Books");
    }

    public static Books getListOfBooks(){
        return RestAssured
                .given()
                .log().all()
                .get("https://demoqa.com/BookStore/v1/Books").as(Books.class);
    }

    public static Response getBookByISBN(String isbn){
      return RestAssured.given()
                .baseUri("https://demoqa.com/BookStore/v1/Book?")
                .param("ISBN", isbn)
                .get();
    }

    public static Response addBooksToUser(BooksToAdd books){
        return RestAssured
                .given()
                .auth().preemptive().basic(TEST_USER_NAME, TEST_USER_PASSWORD)
                .log().all()
                .contentType("application/json")
                .body(books)
                .post("https://demoqa.com/BookStore/v1/Books");
    }

    public static Response deleteBooks(String userId){
        return RestAssured
                .given().auth()
                .preemptive()
                .basic(TEST_USER_NAME, TEST_USER_PASSWORD)
                .log().all()
                .contentType("application/json")
                .delete("https://demoqa.com/BookStore/v1/Books?UserId=" + userId);
    }

    public static Response deleteBook(String isbn){
        String randomISBN = getListOfBooks().getBooks().get(new Random().nextInt(getListOfBooks().getBooks().size() - 1)).getIsbn();
        StringObjectModel bookToDelete = new StringObjectModel(isbn, getUser().getUserId());
        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .body(bookToDelete)
                .delete("https://demoqa.com/BookStore/v1/Book");
    }

    public static Header getAuthorizationHeader(){  //header or token
        LogInViewModel logInViewModel = new LogInViewModel(TEST_USER_NAME, TEST_USER_PASSWORD);
        return new Header("Authorization", generateToken().getToken());
    }

    private static TokenViewModel generateToken(){
        return RestAssured
                .given()
                .log().all()
                .body(logInViewModel)
                .contentType("application/json")
                .post("https://demoqa.com/Account/v1/GenerateToken").as(TokenViewModel.class);
    }

    public static User getUser(){
        return RestAssured
                .given()
                .log().all()
                .body(logInViewModel)
                .contentType("application/json")
                .post(TestConst.LOGIN_URL).as(User.class);
    }

    public static String getRandomIsbn(){
        return getListOfBooks().getBooks().get(new Random().nextInt(getListOfBooks().getBooks().size() - 1)).getIsbn();
    }
    //todo сделать методы, используемые в пределах одного класса, приватными
}
