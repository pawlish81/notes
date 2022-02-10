Feature: Verify notes api get method

  Scenario: Check if service return empty list when database is empty
    Given Database is empty
    When I send a request to get notes with criteria page:0, size:10,id:"",title:"",tag:""
    Then the response will return list of notes with size 0

  Scenario Outline: Check if service return list by search criteria
    Given Database is empty
    When I send a post request to add note with title: "<title>" , text: "<text>" , tag: "<tag>"
    Then I send a request to get notes with criteria page:0, size:10,id:"",title:"<title>",tag:"<tag>"
    And the response will return list of notes with size 1

    Examples:
      | title  | text            | tag      |
      | title1 | text as text    | BUSINESS |
      | title2 | text as Number  | BUSINESS |
      | title3 | text is one one | PERSONAL |


  Scenario Outline: Check if service return list with one note with id
    Given I send a post request to add note with title: "<title>" , text: "<text>" , tag: "<tag>"
    Then I send a request to get notes with criteria only by id

    Examples:
      | title  | text         | tag      |
      | title1 | text as text | BUSINESS |
