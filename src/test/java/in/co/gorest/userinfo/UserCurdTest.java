package in.co.gorest.userinfo;

import in.co.gorest.testbase.TestBase;
import in.co.gorest.usersteps.UsersSteps;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCurdTest extends TestBase {

    static String name = "john" + TestUtils.getRandomValue();
    static String email = "john" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;
    ValidatableResponse response;

    @Steps
    UsersSteps usersSteps;

    @Test
    public void test001(){
        response = usersSteps.createNewUser(name,email,gender,status);
        response.log().all().statusCode(201);
    }

    @Test
    public void test002(){
        HashMap<String,Object> userList = usersSteps.getAllUserList();
        Assert.assertThat(userList,hasValue(name));
        userId = (int) userList.get("id");
    }

    @Test
    public void test003(){
        name = name + "_updated";
        email = "johnsmit" + TestUtils.getRandomValue() + "@gmail.com";
        gender = "male";
        status = "inactive";
        response = usersSteps.updateUser(userId,name,email,gender,status);
        response.log().all().statusCode(200);
        HashMap<String,Object> userList = usersSteps.getAllUserList();
        Assert.assertThat(userList,hasValue(name));
    }

    @Test
    public void test004(){
        response = usersSteps.deleteUserRecord(userId);
        response.log().all().statusCode(204);

        response = usersSteps.singleUserRecord(userId);
        response.log().all().statusCode(404);
    }




}
