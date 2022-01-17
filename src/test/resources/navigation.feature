Feature: Navigation of the robotic rover to the left right forward and back directions
  Description: As a robotic rover controller
  I would like to send navigation commands to the rover
  So that I can control it and captures videos of the planet mars

  Background: The rover is settled on mars successfully
    Given the rover is landed mars at the given coordinates
    |axisX|axisY|facing|
    |0    |0    |1     |

  Scenario: The rover should be able to turn left
    When the operator sends a single command of turn left
    Then the rover is settled at coordinates x = 0 and y =0
    And  the rover is facing towards west
    And  the initial coordinates of the rover is not changed

  Scenario: The rover should be able to turn right
    When the operator sends a single command of turn right
    Then the rover is settled at coordinates x = 0 and y =0
    And  the rover is facing towards east
    And  the initial coordinates of the rover is not changed

  Scenario: The rover should be able to move forward
    When the operator sends a single command of move 3 units forward
    Then the rover is not positioning at its initial location
    And  the rover is settled at coordinates x = 0 and y = 3
    And  the rover is facing towards north

     # Assuming that the wheels of rover can round to both forward and back.
     # The rover will be facing to reverse direction accordingly.
  Scenario: The rover should be able to move back
    When the operator sends a single command of move 5 units back
    Then the rover is not positioning at its initial location
    And  the rover is settled at coordinates x = 0 and y = -5
    And  the rover is facing towards south

    # Turn back manoeuvre can be done both over the left and right-hand side
    # in case of moving directly backwards is not functioning.
    # Choosing the accurate side to turn is extremely important
    # when the rover is located in such a situation as around a rock or edge of an abyss.
    # There might not be a way to turn either right or left sides.
  Scenario: The rover should be able to turn back over the right hand side
    When the operator sends a single command of turn right
    And  the operator sends a single command of turn right
    Then the rover is settled at coordinates x = 0 and y = 0
    And  the rover is facing towards south

  Scenario: The rover should be able to turn back over the left hand side
    When the operator sends a single command of turn left
    And  the operator sends a single command of turn left
    Then the rover is settled at coordinates x = 0 and y = 0
    And  the rover is facing towards south

  Scenario: The rover should be able to navigate to a given location
    When the operator sends the navigation coordinates as x = -5 and y = -3
    Then the rover is settled at coordinates x = -5 and y = -3
    And  the rover is facing towards north

  Scenario: The rover should be able to process a multiple navigation command sequentially
    When the operator sends the navigation coordinates as x = 1 and y = 2
    And  the operator sends a series of navigation command as LMLMLMLMM
    Then the rover is settled at coordinates x = 1 and y = 3
    And  the rover is facing towards north

  Scenario: The rover should be able to move forward
    Given the rover is landed
    When the operator sends turn left command
    Then the rover is located at here

  Scenario: The rover should be able to move to a given location
    Given the rover is landed
    When the operator sends turn left command
    Then the rover is located at here