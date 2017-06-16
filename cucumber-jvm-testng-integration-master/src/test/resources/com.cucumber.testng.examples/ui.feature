@tag1
Feature: Go to website and obtain miles and minutes between the two places

  Scenario Outline: Calculate distance and duration of travel
    Given Open website
    When Provide <Origin> and <Destination> for <Itenary>
    Then Obtain miles and minutes

    Examples: 
      | Origin                  | Destination             | Itenary  |
      | San Francisco, CA 94102 | San Francisco, CA 94104 | Itenary1 |
      | New York, NY 10037      | New York, NY 10036      | Itenary2 |

 
