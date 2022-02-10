Feature: Verify notes api put method

  Scenario Outline: Send a valid Request to create note
    And Database is empty
    Given I send a post request to add note with title: "<title>" , text: "<text>" , tag: "<tag>"
    Then Created response will contain notes with id and title: "<title>" , text: "<text>" , tag: "<tag>"

    Examples:
      |title         |text                 |tag       |
      |title1        |text as text         |BUSINESS  |

  Scenario Outline: Send a valid Request to update  note
    Given I send a put request to update note with title: "<title>" , text: "<text>" , tag: "<tag>"
    Then Updated response will contain notes with id and title: "<title>" , text: "<text>" , tag: "<tag>"
    And I send a request to get note with new values title:"<title>" , text: "<text>" , tag: "<tag>" by get endpoint

    Examples:
      |title         |text                 |tag       |
      |title2        |updated text         |PERSONAL  |

