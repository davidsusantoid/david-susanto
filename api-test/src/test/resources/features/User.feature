Feature: User

  Scenario Outline: Verify successful add, update, and delete function
    Given If username "<userName>" is exist, then delete it
    When Add new username "<userName>"
    Then Username "<userName>" is found
    When Update username "<userName>"
    Then Username "<userName>" was updated with "<updatedUserName>"
    When Delete username "<updatedUserName>"
    Then Username "<updatedUserName>" is not found
    Examples:
    | userName  | updatedUserName  |
    | david5586 | updateddavid5586 |

  Scenario: Verify successful add list of users function

  Scenario Outline: Verify delete function with invalid input
    When Delete username <userName> with invalid input
    Then Get username <userName> with invalid input
    Examples: :
    | userName |
    | 1234     |

  Scenario: Verify delete function for user not found

  Scenario: Verify get user function with invalid input

  Scenario: Verify get user function for user not found

  Scenario: Verify successful login and logout function

  Scenario: Verify login function with invalid username/password