Feature: Store

  Scenario Outline: Verify successful add and delete function
    Given If order ID <orderId> is exist, then delete it
    When Add new order ID <orderId>
    Then Order ID <orderId> is found
    When Delete order ID <orderId>
    Then Order ID <orderId> is not found
    Examples:
      | orderId  |
      | 5586     |

  Scenario Outline: Verify add and get order ID function with invalid input
    When Add new order by ID with invalid input "<invalidOrderId>"
    Then Invalid order ID "<invalidPetId>" supplied
    Examples:
      | invalidOrderId |
      | abcd           |

  Scenario: Verify delete function with invalid input

  Scenario: Verify delete function for order not found

  Scenario: Verify get order function for order not found

  Scenario: Verify get pet inventories by status