Feature: Validate google place API's
  Scenario Outline: Verify if the place being added successfully using add place api
    Given Add place "<Language>"  "<Name>" <Lat>
    When user calls "AddPlaceApi" with "post" request
    Then API call got success with status code  200
 #   And "status" in response body  is "OK"
  #  And "scope" in response body  is "APP"
    And verify the added place_name mapped to "<Name>" using "GetPlaceAPI"

    Examples:
    | Language  | Name              | Lat        |
    | French-IN | Frontline house   | -38.383494 |
  #  | English   | Frontline world   | -38.383495 |
 #   | Arabic    | Frontline Land    | -38.383496 |

  Scenario: Verify if the place being deleting successfully using delete place api
    Given  place id
    When user calls "DeletePlaceAPI" with "post" request
    Then API call got success with status code  200
