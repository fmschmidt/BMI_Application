Feature: As an User
  I want to edit an existing person within the table
  So that I can store new person informations

  Scenario: User writes all valid informations
    Given I have log entries
    When I select a person to edit
    And I edit the person informations correctly:
      | name  | lastname | weight | height |
      | xxxxx | yyyyy    | 900    | 190    |
    Then I see an updated table
    And I have an edited entry

  Scenario: User writes all valid informations
    Given I have log entries
    When I select a person to edit
    And I edit the person informations incorrectly:
      | name  | lastname | weight | height |
      | xxxxx | yyyyy    | abc    | 170    |
    Then I see an error
    And I dont have a new entry
