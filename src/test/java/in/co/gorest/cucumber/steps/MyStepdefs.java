package in.co.gorest.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.co.gorest.usersteps.UsersSteps;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {
    static String name = "john" + TestUtils.getRandomValue();
    static String email = "john" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;
    ValidatableResponse response;

    @Steps
    UsersSteps usersSteps;
    @When("^I sends the Post Request for create the User Data$")
    public void iSendsThePostRequestForCreateTheUserData() {

    }

    @And("^I insert the name, email,gender,status$")
    public void iInsertTheNameEmailGenderStatus() {
        response = usersSteps.createNewUser(name,email,gender,status);
    }

    @Then("^I verify the status code (\\d+) for User data$")
    public void iVerifyTheStatusCodeForUserData(int statusCode) {
        response.log().all().statusCode(statusCode);
    }

    @And("^I get the id of new created User data$")
    public void iGetTheIdOfNewCreatedUserData() {
        HashMap<String,Object> userList = usersSteps.getAllUserList();
        userId = (int) userList.get("id");
    }

    @When("^I sends a GET request for fatching User data by given ID$")
    public void iSendsAGETRequestForFatchingUserDataByGivenID() {
    }

    @Then("^I verify the name is for created record for User$")
    public void iVerifyTheNameIsForCreatedRecordForUser() {
        HashMap<String,Object> userList = usersSteps.getAllUserList();
        Assert.assertThat(userList,hasValue(name));
    }

    @When("^I send the Put Request for updating the User data$")
    public void iSendThePutRequestForUpdatingTheUserData() {
    }

    @And("^I update the name, email,gender, status$")
    public void iUpdateTheNameEmailGenderStatus() {
        name = name + "_updated";
        email = "johnsmit" + TestUtils.getRandomValue() + "@gmail.com";
        gender = "male";
        status = "inactive";
        response = usersSteps.updateUser(userId,name,email,gender,status);
    }

    @Then("^I verify the status code (\\d+) for update User data$")
    public void iVerifyTheStatusCodeForUpdateUserData(int statusCode) {
        response.log().all().statusCode(statusCode);

    }

    @And("^I verify the name for updating the User data$")
    public void iVerifyTheNameForUpdatingTheUserData() {
        HashMap<String,Object> userList = usersSteps.getAllUserList();
        Assert.assertThat(userList,hasValue(name));
    }

    @When("^I sends the Delete Request for deleting the User  given ID$")
    public void iSendsTheDeleteRequestForDeletingTheUserGivenID() {
        response = usersSteps.deleteUserRecord(userId);
    }

    @Then("^I verify the status code (\\d+) for delete User data$")
    public void iVerifyTheStatusCodeForDeleteUserData(int statusCode) {
        response.log().all().statusCode(statusCode);
    }

    @And("^I get the data for deleted record ID for User data$")
    public void iGetTheDataForDeletedRecordIDForUserData() {
        response = usersSteps.singleUserRecord(userId);
    }

    @And("^I verify the status code (\\d+) for verifing the delete record$")
    public void iVerifyTheStatusCodeForVerifingTheDeleteRecord(int statusCode) {
        response.log().all().statusCode(statusCode);
    }
}
