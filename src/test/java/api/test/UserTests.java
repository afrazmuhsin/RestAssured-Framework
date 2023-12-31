package api.test;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsFromConfig;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker = new Faker();
    User userPayload = new User();
    public Logger logger;

    @BeforeMethod
    public void setupData() {
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));

        //logs
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 0)
    public void testPostUser() {
        logger.info("******** Creating User ************");
        userPayload.setUsername(faker.name().username());
        Response response = UserEndPointsFromConfig.createUser(userPayload);
        System.out.println("--------" + response.getBody().print());
        System.out.println("UserName--: " + userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******** User is Created ************");
    }

    @Test(priority = 1)
    public void testGetUserByName() {
        logger.info("******** Getting User Details ************" +  this.userPayload.getUsername());
        setupData();
        Response response = UserEndPointsFromConfig.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******** Getting User Details Completed************");
    }

    @Test(priority = 2)
    public void testPutUserByName() {
        logger.info("******** Updating User Details ************" +  this.userPayload.getUsername());
        setupData();
        Response response = UserEndPointsFromConfig.updateUser("update.username", this.userPayload);
        Assert.assertEquals(200, response.getStatusCode());
        Response responseGet = UserEndPoints.readUser("update.username");
        Assert.assertEquals(200, response.getStatusCode());
        logger.info("******** Updated user details Completed ************");
    }

    @Test(priority = 3)
    public void testDeleteUserByName() {
        logger.info("******** Deleting User Details ************");
        Response response = UserEndPointsFromConfig.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(200, response.getStatusCode());
        logger.info("******** Deleted User Details Completed ************");
    }

}
