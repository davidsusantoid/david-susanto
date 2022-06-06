Feature: User

  Scenario Outline: Verify successful add, update, and delete function
    Given If username "<userName>" is exist, then delete it
    When Add new username "<userName>" and password "<password>"
    Then Username "<userName>" is found
    When Update username "<userName>"
    Then Username "<userName>" was updated with "<updatedUserName>"
    When Delete username "<updatedUserName>"
    Then Username "<updatedUserName>" is not found
    Examples:
    | userName  | updatedUserName  | password |
    | david5586 | updateddavid5586 | david123    |

  Scenario: Verify successful add list of users function

  Scenario Outline: Verify delete function with invalid input
    When Delete username <userName> with invalid input
    Then Get username <userName> with invalid input
    Examples: :
    | userName |
    | 1234     |

  Scenario Outline: Verify delete function for user not found
    Given If username "<userName>" is exist, then delete it
    When Delete username "<userName>" for user not found
    Then Username "<updatedUserName>" is not found
    Examples:
      | userName  |
      | david5586 |

  Scenario Outline: Verify successful login and logout function
    Given If username "<userName>" is exist, then delete it
    And Add new username "<userName>" and password "<password>"
    When Username "<userName>" is found
    Then Login with username "<userName>" and password "<password>"
    Examples:
      | userName  | password |
      | david5586 | david123 |


  Scenario: Verify login function with invalid username/password