Feature: User

  Scenario Outline: Verify successful add, update, and delete function
    Given Logout current login user
    And Login with the loginUser "<loginUser>"
    And If username "<userName>" is exist, then delete it
    When Add new username "<userName>" and password "<password>"
    Then Username "<userName>" is found
    When Update username "<userName>"
    Then Username "<userName>" was updated with "<updatedUserName>"
    When Delete username "<updatedUserName>"
    Then Username "<updatedUserName>" is not found
    Examples:
    | loginUser | userName  | updatedUserName  | password |
    | theUser   | david5586 | updateddavid5586 | david123 |

  Scenario: Verify successful add list of users function

  Scenario Outline: Verify delete function with invalid input
    Given Logout current login user
    And Login with the loginUser "<loginUser>"
    When Delete username with invalid input <invalidUserName>
    Then Get username <userName> with invalid input
    Examples:
    | loginUser | invalidUserName |
    | theUser   | 123             |

  Scenario Outline: Verify delete function for user not found
    Given Logout current login user
    And Login with the loginUser "<loginUser>"
    And If username "<userName>" is exist, then delete it
    When Delete username "<userName>" for user not found
    Then Username "<userName>" is not found
    Examples:
      | loginUser | userName  |
      | theUser   | david5586 |

  Scenario: Verify login function with invalid username/password