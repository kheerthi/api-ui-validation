@tag
Feature: Find miles and minutes between the two locations
Scenario: 1
  Scenario Outline: Calculate distance and duration of travel
    Given Open website
    When Provide <Origin> and <Destination> for <Itinerary>
    Then Obtain miles and minutes

    Examples: 
      | Origin                  | Destination             | Itinerary  |
      | San Francisco, CA 94102 | San Francisco, CA 94104 | Itinerary1 |
      | New York, NY 10037      | New York, NY 10036      | Itinerary2 |
 
    
  Scenario Outline: Calculate distance and duration of travel
  	Given Construct get Request for <Origin> to <Destination> in <Itenary>
 		When Validate Response
    Then Extract value of Time and Distance of travel

    
   Examples: 
      | Origin                  | Destination             | Itenary  |
      | San Francisco, CA 94102 | San Francisco, CA 94104 | Itinerary1 |
      | New York, NY 10037      | New York, NY 10036      | Itinerary2 |
      