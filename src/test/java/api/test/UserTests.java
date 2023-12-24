package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker = new Faker();
    User userPayload = new User();

    @BeforeMethod
    public void setupData() {
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
    }

    @Test(priority = 0)
    public void testPostUser() {
        userPayload.setUsername(faker.name().username());
        Response response = UserEndPoints.createUser(userPayload);
        System.out.println("--------" + response.getBody().print());
        System.out.println("UserName--: " + userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    public void testGetUserByName() {
        setupData();
        System.out.println("-----------" + this.userPayload.getUsername());
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testPutUserByName() {
        setupData();
        Response response = UserEndPoints.updateUser("update.username", this.userPayload);
        Assert.assertEquals(200, response.getStatusCode());
        Response responseGet = UserEndPoints.readUser("update.username");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(priority = 3)
    public void testDeleteUserByName() {
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(200, response.getStatusCode());
    }

}
