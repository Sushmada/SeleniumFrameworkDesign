@tag
Feature: Error Validation on login and submit order workflow


@ErrorValidation
Scenario Outline: login error validation

Given I landed on the Ecommerce page
Given Logged in with username <name> and password <password>
Then "Incorrect email or password." Error message is displayed

Examples:
|name            |password    |
|sush@gmail.com  |Bujji!1234  |



