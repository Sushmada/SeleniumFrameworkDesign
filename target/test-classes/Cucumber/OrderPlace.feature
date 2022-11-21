@tag
Feature: Purchase the order from the Ecommerce Website

Background:
Given I landed on the Ecommerce page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the Confirmationpage

    Examples: 
      | name           | password  | productName     |
      | sush@gmail.com | Bujji!123 | ADIDAS ORIGINAL |
     
