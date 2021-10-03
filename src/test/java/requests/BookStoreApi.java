package requests;

import apiTestHelper.EndPoints;
import bookStore.bookStoreModel.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static apiTestHelper.ApiTestHelper.logInViewModel;
import static bookStore.TestConst.TEST_USER_NAME;
import static bookStore.TestConst.TEST_USER_PASSWORD;
import static org.hamcrest.Matchers.*;

public class BookStoreApi {

    private static RequestSpecification requestSpecification = getRequestSpecification();

    private static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://demoqa.com/BookStore/v1");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }

    public Books getListOfBooks() {
        return RestAssured
                .given(requestSpecification)
                .when()
                .get(EndPoints.GET_BOOKS)
                .then().statusCode(200)
                .extract().as(Books.class);
    }

    public Book getBookByISBN(String isbn) {
        return RestAssured.given(requestSpecification)
                .param("ISBN", isbn)
                .when()
                .get(EndPoints.GET_BOOK)
                .then().statusCode(200)
                .extract()
                .response().as(Book.class);
    }

    public Response addBooksToUser(BooksToAdd booksToAdd, int expectedStatusCode) {
        return RestAssured
                .given(requestSpecification)
                .auth().preemptive().basic(TEST_USER_NAME, TEST_USER_PASSWORD)
                .body(booksToAdd)
                .when()
                .post(EndPoints.GET_BOOKS)
                .then().statusCode(anyOf(is(expectedStatusCode))).extract().response();
    }

    public Response deleteBooks(String userId) {
        return RestAssured
                .given(requestSpecification)
                .auth().preemptive().basic(TEST_USER_NAME, TEST_USER_PASSWORD)
                .params("UserId", userId)
                .when()
                .delete(EndPoints.DELETE_BOOKS)
                .then().statusCode(204).extract().response();
    }

    public Response replaceBook(String isbn, ReplaceIsbn replaceIsbn) {
        return RestAssured
                .given(requestSpecification)
                .auth().preemptive().basic(TEST_USER_NAME, TEST_USER_PASSWORD)
                .pathParam("ISBN", isbn)
                .body(replaceIsbn)
                .when()
                .put(EndPoints.REPLACE_BOOK)
                .then().statusCode(anyOf(is(200), is(400))).extract().response();
    }



    private static TokenViewModel generateToken() {
        return RestAssured
                .given()
                .log().all()
                .body(logInViewModel)
                .contentType("application/json")
                .post("https://demoqa.com/Account/v1/GenerateToken").as(TokenViewModel.class);
    }

    public User getUser() {
        return RestAssured
                .given()
                .body(logInViewModel)
                .contentType("application/json")
                .post("https://demoqa.com/Account/v1/Login")
                .body().as(User.class);
    }



}
