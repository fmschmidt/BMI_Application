Feature: As an User
  I want to insert a new person to the table
  So that I can store the person informations

  Scenario: User writes valid information
    Given I have log entries
    When I enter a person information correctly:
      | name   | lastname | weight | height |
      | felipe | Schmidt  | 70     | 170    |
    Then I see an updated table
    And I have a new entry

  Scenario: User writes invalid information
    Given I have log entries
    When I enter a person information incorrectly:
      | name   | lastname | weight | height |
      | felipe | Schmidt  | abc    | 170    |
    Then I see an error
    And I dont have a new entry
