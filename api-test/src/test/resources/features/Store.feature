Feature: Store

  Scenario Outline: Verify successful add and delete function
    Given If order ID <orderId> is exist, then delete it
    When Add new order ID <orderId>
    Then Order ID <orderId> is found
    When Delete order ID <orderId>
    Then Order ID <orderId> is not found
    Examples:
      | orderId  |
      | 303     |

  Scenario Outline: Verify add and get order ID function with invalid input
    When Add new order by ID with invalid input "<invalidOrderId>"
    Then Invalid order ID "<invalidPetId>" supplied
    Examples:
      | invalidOrderId |
      | abcd           |

  Scenario Outline: Verify delete and get order ID function with invalid input
    When Delete order by ID with invalid input "<invalidOrderId>"
    Then Invalid order ID "<invalidPetId>" supplied
    Examples:
      | invalidOrderId |
      | abcd           |

  Scenario Outline: Verify delete and get order ID function for order not found
    Given If order ID <orderId> is exist, then delete it
    When Delete order by ID for order not found <orderId>
    Then Order ID <orderId> is not found
    Examples:
      | orderId |
      | 303    |

  Scenario: Verify get pet inventories by status