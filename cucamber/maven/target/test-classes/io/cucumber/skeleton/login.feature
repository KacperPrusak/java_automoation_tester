Feature: Add new address

  Scenario Outline: User adds a new address
    Given I go to the login page
    And I click on sign up
    When I enter "<email>" as email
    And I enter "<password>" as password
    And I click on Login button
    And I navigate to the Addresses page
    And I click on "+ Create new address"
    And I fill in the new address form with the following details:
      | alias | address    | city     | zip/postal code | country | phone      |
      | Home  | Warszawska | Warszawa | 12345           | Poland  | 1234567890 |
    And I submit the new address form
    Then the new address should be listed with the correct details:
      | alias | address    | city     | zip/postal code | country | phone      |
      | Home  | Warszawska | Warszawa | 12345           | Poland  | 1234567890 |


    Examples:
      | email              | password |
      | qbx81207@tccho.com | 123456   |
