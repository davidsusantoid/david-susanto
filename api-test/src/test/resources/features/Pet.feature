Feature: Pet

  Scenario Outline: Verify successful add, update, and delete function
    Given If pet by ID <petId> is exist, then delete it
    When Add new pet by ID <petId>, name "<petName>", tagname "<tagName>", and status "<status>"
    Then Pet by ID <petId> is found
    When Update pet by ID <petId> with name to be "<updatedName>" and status to be "<updatedStatus>"
    Then Pet by ID <petId> was updated with name to be "<updatedName>" and status to be "<updatedStatus>"
    When Delete pet by ID <petId>
    Then Pet by ID <petId> is not found
    Examples:
      | petId  | petName | updatedName | tagName | status    | updatedStatus |
      | 5586   | dog     | dog5586     | tag5586 | available | sold          |

  Scenario Outline: Verify add function with invalid ID input
    When Add new pet by ID with invalid input "<invalidPetId>"
    Then Invalid pet ID "<invalidPetId>" supplied
    Examples:
      | invalidPetId |
      | abcd         |

  Scenario Outline: Verify update function using form data
    Given If pet by ID <petId> is exist, then delete it
    And Add new pet by ID <petId>, name "<petName>", tagname "<tagName>", and status "<status>"
    When Update pet by ID <petId> using form data with name to be "<updatedName>" and status to be "<updatedStatus>"
    Then Pet by ID <petId> was updated with name to be "<updatedName>" and status to be "<updatedStatus>"
    Examples:
      | petId | petName | updatedName | tagName | status    | updatedStatus |
      | 5586  | dog     | dog5586     | tag5586 | available | sold          |

  Scenario Outline: Verify update function using form data with invalid ID input
    When Update pet by ID using form data with invalid input "<invalidPetId>"
    Then Invalid pet ID "<invalidPetId>" supplied
    Examples:
      | invalidPetId |
      | abc          |

  Scenario Outline: Verify update function with invalid ID input
    When Update pet by ID with invalid input "<invalidPetId>"
    Then Invalid pet ID "<invalidPetId>" supplied
    Examples:
      | invalidPetId |
      | abc          |

  Scenario Outline: Verify update function with pet ID not found
    Given If pet by ID <petId> is exist, then delete it
    When Update pet by ID with pet ID <petId> not found
    Then Pet by ID <petId> is not found
    Examples:
      | petId |
      | 5586  |

  Scenario Outline: Verify delete function with invalid ID input
    When Delete pet by ID with invalid input "<invalidPetId>"
    Then Invalid pet ID "<invalidPetId>" supplied
    Examples:
      | invalidPetId  |
      | abc           |

  Scenario Outline: Verify get pet by status
    Given If pet by ID <petId> is exist, then delete it
    When Add new pet by ID <petId>, name "<petName>", tagname "<tagName>", and status "<status>"
    Then Newly added pet ID <petId> exist in query find pet by status "<status>"
    And All returned correct status in query find pet by status "<status>"
    Examples:
      | petId | petName | tagName | status    |
      | 5586  | dog     | tag5586 | available |
      | 5586  | dog     | tag5586 | pending   |
      | 5586  | dog     | tag5586 | sold      |

  Scenario Outline: Verify get pet by status with invalid status value
    When Find pets by status with invalid status value "<invalidStatus>"
    Then All returned correct status in query find pet by status "<validStatus>"
    Examples:
      | invalidStatus | validStatus |
      | unavailable   | available   |

  Scenario Outline: Verify get pet by tags
    Given If pet by ID <petId> is exist, then delete it
    When Add new pet by ID <petId>, name "<petName>", tagname "<tagName>", and status "<status>"
    Then Newly added pet ID <petId> exist in query find pet by tags "<tagName>"
    And All returned correct tag in query find pet by tags "<tagName>"
    Examples:
      | petId | petName | tagName | status    |
      | 5586  | dog     | tag5586 | available |

  Scenario: Verify sucessful upload an image function

  Scenario: Verify upload image function with error status