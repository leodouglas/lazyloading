Feature: Sample with upload

  Scenario: fail simple sample
    Given the url /upload
    And with the parameter idNumber with the value 123456
    When make a POST
    Then the response status must be 400

  Scenario: fail simple sample
    Given the url /upload
    And with the file lazy_loading_example_input.txt with the name file
    When make a POST
    Then the response status must be 400

  Scenario: simple input sample
    Given the url /upload
    And with the file lazy_loading_example_input.txt with the name file
    And with the parameter idNumber with the value 123456
    When make a POST
    Then the response status must be 200
    And the response must be:
    """
    Case #1: 2
    Case #2: 1
    Case #3: 2
    Case #4: 3
    Case #5: 8
    """