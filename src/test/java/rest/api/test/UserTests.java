package rest.api.test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rest.api.endpoints.UserEndPoints;
import rest.api.payload.User;

import java.io.PushbackReader;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupData(){

        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setUserStatus(faker.number().randomDigit());
        userPayload.setPhone("34564645456");
    }


    @Test(priority = 1)
    public void testPostUser(){

      Response response= UserEndPoints.createUser(userPayload);
      response.then().log().all();
      Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 1)
    public void testGetUserByName(){

      Response response = UserEndPoints.readUser(this.userPayload.getUsername());
      response.then().log().all();
      Assert.assertEquals(response.getStatusCode(),200);


    }
}
