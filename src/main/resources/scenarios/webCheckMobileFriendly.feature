@Web
Feature: NFL validate

  @SimpleValidation
  Scenario: Validate NFL
    Given I open browser to webpage "http://www.nfl.com"
    Then I check mobileFriendly current URL
    #Then I check mobileFriendly URL "http://www.nfl.com"
    Then I wait "5" seconds to see the text "video"


