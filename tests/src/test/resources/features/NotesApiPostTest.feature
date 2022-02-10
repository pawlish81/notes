Feature: Verify notes api post method

  Scenario Outline: Send a valid Request to create note
    And Database is empty
    Given I send a post request to add note with title: "<title>" , text: "<text>" , tag: "<tag>"
    Then Created response will contain notes with id and title: "<title>" , text: "<text>" , tag: "<tag>"

    Examples:
      |title         |text                 |tag       |
      |title1        |text as text         |BUSINESS  |
      |title2        |text as Number       |BUSINESS  |
      |title3        |text is one one      |PERSONAL  |

  Scenario Outline: Send a valid Request to create note with incorrect tag
    Given I send a post request to add note with title: "<title>" , text: "<text>" , tag: "<tag>"
    Then User will get exception with status code 400

    Examples:
      |title         |text                 |tag         |
      |title1        |text as text         |incorrect tag|

  Scenario Outline: Send a valid Request to create note with missing text and title
    Given I send a post request to add note with title: "<title>" , text: "<text>" , tag: "<tag>"
    Then User will get exception with status code 400

    Examples:
      |title         |text                 |tag      |
      |title1        |                      |PERSONAL |
      |              |text for missing     |PERSONAL |
