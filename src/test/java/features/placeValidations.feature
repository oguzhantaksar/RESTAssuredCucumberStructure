Feature: Validating Place API

  Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceEndpoint" with "POST" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceEndpoint"

    Examples:
      | name    | language | address     |
      | AAHouse | English  | World Cross |


@DeletePlace
  Scenario: Verify if deletePlace endpoint is working
    Given Delete Place Payload
    When User calls "deletePlaceEndpoint" with "DELETE" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
