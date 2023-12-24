package api.endpoints;

import api.payload.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    /**
     * Create classes for CRUD operations
     * @param payload
     * @return
     */
    public static RequestSpecBuilder setRequestSpecBuilder(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setAccept(ContentType.JSON);
        return  builder;
    }
    public static Response createUser(User payload){
        var requestSpec = setRequestSpecBuilder().build();
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(Routes.post_url);
    }

    public static Response readUser(String username){
        var requestSpec = setRequestSpecBuilder().build();
        return given()
                .spec(requestSpec)
                .pathParams("username",username)
                .when()
                .get(Routes.get_url);
    }

    public static Response updateUser(String username,User payload){
        var requestSpec = setRequestSpecBuilder().build();
        return given()
                .spec(requestSpec)
                .pathParams("username",username)
                .body(payload)
                .when()
                .put(Routes.update_url);
    }

    public static Response deleteUser(String username){
        var requestSpec = setRequestSpecBuilder().build();
        return given()
                .spec(requestSpec)
                .pathParams("username",username)
                .when()
                .delete(Routes.delete_url);
    }
}
