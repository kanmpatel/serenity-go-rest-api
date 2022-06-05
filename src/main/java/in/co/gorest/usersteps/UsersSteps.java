package in.co.gorest.usersteps;

import in.co.gorest.constants.EndPoints;
import in.co.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UsersSteps {

    @Step
    public ValidatableResponse createNewUser(String name, String email, String gender, String status){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer dd97bbf3bb026c051c7943ee3b49aa9d61bbe0045e77fc9aaefe3ce7df790907")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USERS)
                .then();
    }

    @Step
    public HashMap<String,Object> getAllUserList(){
        return SerenityRest.given()
                .header("Authorization","Bearer dd97bbf3bb026c051c7943ee3b49aa9d61bbe0045e77fc9aaefe3ce7df790907")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then().log().all()
                .statusCode(200)
                .extract()
                .path("get(0)");
    }

    @Step
    public ValidatableResponse updateUser(int userId, String name, String email,String gender, String status){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given()
                .header("Authorization","Bearer dd97bbf3bb026c051c7943ee3b49aa9d61bbe0045e77fc9aaefe3ce7df790907")
                .body(userPojo)
                .pathParam("usersID",userId)
                .contentType(ContentType.JSON)
                .when()
                .put(EndPoints.UPDATE_USERS_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse deleteUserRecord(int userId){
        return SerenityRest.given()
                .header("Authorization","Bearer dd97bbf3bb026c051c7943ee3b49aa9d61bbe0045e77fc9aaefe3ce7df790907")
                .pathParam("usersID",userId)
                .contentType(ContentType.JSON)
                .when()
                .delete(EndPoints.DELETE_USERS_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse singleUserRecord(int userId){
        return SerenityRest.given()
                .header("Authorization","Bearer dd97bbf3bb026c051c7943ee3b49aa9d61bbe0045e77fc9aaefe3ce7df790907")
                .pathParam("usersID",userId)
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_SINGLE_USERS)
                .then();
    }


}
