
@tag
Feature: Product Error Validation
 
 Background:
Given I landed on the Ecommerce page
 

  @ProductErrorValidation
  Scenario Outline: product error validation
  
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    Then I should not find the "Zara coat" in cart

    Examples: 
      |name               |password  |productName     |
      |bujji77@gmail.com  |Sush!123  |ADIDAS ORIGINAL |
 