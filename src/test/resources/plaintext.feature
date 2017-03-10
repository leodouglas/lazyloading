Feature: Sample input output tests

  Scenario: simple input sample
    Given the url /
    And with the text:
    """
    5
    4
    30
    30
    1
    1
    3
    20
    20
    20
    11
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    6
    9
    19
    29
    39
    49
    59
    10
    32
    56
    76
    8
    44
    60
    47
    85
    71
    91
    """
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