Feature: User inserts a new data to the table

  As an User
  I want to insert person data to the table
  So that I can save the development recording their data

  Scenario: a person entry is removed from the table
    Given I have log entries:
      | firstName   | lastName    | height | weight |
      | Felipe      | Schmidt     | 170    | 70     |
      | Felipe      | de Medeiros | 170    | 70     |
      | De Medeiros | Schmidt     | 170    | 70     |
      | Schmidt     | Felipe      | 170    | 70     |
    When I remove the person on the line 3
    Then I see a result table:
      | firstName | lastName    | height | weight |
      | Felipe    | Schmidt     | 170    | 70     |
      | Felipe    | de Medeiros | 170    | 70     |
      | Schmidt   | Felipe      | 170    | 70     |
