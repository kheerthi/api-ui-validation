@tag2
Feature: Call api and obtain miles and minutes between the two places

  Scenario Outline: Calculate distance and duration of travel
    Given Construct get Request for <Origin> to <Destination> in <Itenary>
 		When Validate Response
    Then Extract value of Time and Distance of travel

    
   Examples: 
      | Origin                  | Destination             | Itenary  |
      | San Francisco, CA 94102 | San Francisco, CA 94104 | Itenary1 |
      | New York, NY 10037      | New York, NY 10036      | Itenary2 |

