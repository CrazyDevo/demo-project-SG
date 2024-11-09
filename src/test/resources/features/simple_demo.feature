Feature: The application should be running

  @smoke @test1
  Scenario: simple search
    Given I am on the home page
    When I search for "apple"
    Then I should see the results

  @regression @test2
  Scenario: another search
    Given I am on the home page
    When I search for "peach"
    Then I should see the results
