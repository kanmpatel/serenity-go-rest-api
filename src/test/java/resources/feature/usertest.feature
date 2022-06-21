Feature: End to End testing of User data

  Scenario: Create a new User data & verify if the User is added
    When    I sends the Post Request for create the User Data
    And     I insert the name, email,gender,status
    Then    I verify the status code 201 for User data
    And     I get the id of new created User data

  Scenario: Verify the User data are successfully created
    When I sends a GET request for fatching User data by given ID
    Then I verify the name is for created record for User

  Scenario: Update the User data for given ID
    When    I send the Put Request for updating the User data
    And     I update the name, email,gender, status
    Then    I verify the status code 200 for update User data
    And     I verify the name for updating the User data

  Scenario: Delete the Prodct data for given ID & verify the record are deleted successfully
    When    I sends the Delete Request for deleting the User  given ID
    Then    I verify the status code 204 for delete User data
    And     I get the data for deleted record ID for User data
    And     I verify the status code 404 for verifing the delete record