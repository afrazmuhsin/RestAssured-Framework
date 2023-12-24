package api.endpoints;

import api.payload.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsFromConfig {
    static ResourceBundle getURL(){
        return ResourceBundle.getBundle("routes");
    }

    public static RequestSpecBuilder setRequestSpecBuilder(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setAccept(ContentType.JSON);
        return  builder;
    }
    public static Response createUser(User payload){
        var requestSpec = setRequestSpecBuilder().build();
        String postURL = getURL().getString("post_url");
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(postURL);
    }

    public static Response readUser(String username){
        var requestSpec = setRequestSpecBuilder().build();
        String getURL = getURL().getString("get_url");
        return given()
                .spec(requestSpec)
                .pathParams("username",username)
                .when()
                .get(getURL);
    }

    public static Response updateUser(String username,User payload){
        var requestSpec = setRequestSpecBuilder().build();
        String putURL = getURL().getString("update_url");
        return given()
                .spec(requestSpec)
                .pathParams("username",username)
                .body(payload)
                .when()
                .put(putURL);
    }

    public static Response deleteUser(String username){
        var requestSpec = setRequestSpecBuilder().build();
        String deleteURL = getURL().getString("delete_url");
        return given()
                .spec(requestSpec)
                .pathParams("username",username)
                .when()
                .delete(deleteURL);
    }
}
