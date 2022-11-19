package userClient;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import userModel.User;
import userModel.UserCredentials;

public class UserClient {
    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://stellarburgers.nomoreparties.site/api")
            .setContentType(ContentType.JSON)
            .build();

    private  String ROOT = "/auth";
    private  String REG = ROOT + "/register";
    private  String LOGIN = ROOT + "/login";
    private  String USER = ROOT + "/user";

    public Response create(User user) {
        return   RestAssured.given()
                .spec(requestSpec)
                .body(user)
                .log().all()
                .when()
                .post(REG);
    }

    public Response login(UserCredentials creds) {
        return RestAssured.given()
                .spec(requestSpec)
                //.header("authorization", accessToken)
                .body(creds)
                .log().all()
                .when()
                .post(LOGIN);
    }


    public Response delete(String accessToken) {
        return  RestAssured.given()
                .spec(requestSpec)
                .header("authorization", accessToken)
                .when()
                .delete(USER);
    }
}
