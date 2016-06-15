Feature: User inserts a new data to the table

  As an User
  I want to insert person data to the table
  So that I can save the development recording their data

  Scenario: the person information is valid
    Given I have log entries:
      | firstName   | lastName    | height | weight |
      | Felipe      | Schmidt     | 170    | 70     |
      | Felipe      | de Medeiros | 170    | 70     |
      | De Medeiros | Schmidt     | 170    | 70     |
      | Schmidt     | Felipe      | 170    | 70     |
    When I enter a person information:
      | firstName | lastName | height | weight |
      | test      | test     | 180    | 80     |
    Then I see a result table:
      | firstName   | lastName    | height | weight |
      | Felipe      | Schmidt     | 170    | 70     |
      | Felipe      | de Medeiros | 170    | 70     |
      | De Medeiros | Schmidt     | 170    | 70     |
      | Schmidt     | Felipe      | 170    | 70     |
      | test        | test        | 180    | 80     |

  Scenario: the person information is invalid
    Given I have log entries:
      | firstName   | lastName    | height | weight |
      | Felipe      | Schmidt     | 170    | 70     |
      | Felipe      | de Medeiros | 170    | 70     |
      | De Medeiros | Schmidt     | 170    | 70     |
      | Schmidt     | Felipe      | 170    | 70     |
    When I enter a person information:
      | firstName | lastName | height | weight |
      |           |          | 180    | 80     |
    Then I see a result table:
      | firstName   | lastName    | height | weight |
      | Felipe      | Schmidt     | 170    | 70     |
      | Felipe      | de Medeiros | 170    | 70     |
      | De Medeiros | Schmidt     | 170    | 70     |
      | Schmidt     | Felipe      | 170    | 70     |
