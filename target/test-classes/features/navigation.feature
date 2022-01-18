Feature: Navigation of the robotic rover to the left right forward and back directions
  Description: As a robotic rover controller
  I would like to send navigation commands to the rover
  So that I can control it and capture videos of the planet mars

  Background: The rover is settled on mars successfully
    Given the rover is landed mars at the given coordinates
      | axisX | axisY | facing |
      | 0     | 0     | 1      |

  @Smoke
  Scenario: The rover should be able to turn left
    When the operator sends a single command of turn left
    Then the rover is settled at coordinates x = 0 and y = 0
    And  the rover is facing towards west

  @Smoke
  Scenario: The rover should be able to turn right
    When the operator sends a single command of turn right
    Then the rover is settled at coordinates x = 0 and y = 0
    And  the rover is facing towards east

  @Smoke
  Scenario: The rover should be able to move forward
    When the operator sends a single command of move 3 units forward
    Then the rover is not positioning at its initial coordinates
    And  the rover is settled at coordinates x = 0 and y = 3
    And  the rover is facing towards north

     # Assuming that the wheels of rover can round to both forward and back.
     # The rover will be facing to reverse direction accordingly.
  @Smoke
  Scenario: The rover should be able to move back
    When the operator sends a single command of move 5 units back
    Then the rover is not positioning at its initial coordinates
    And  the rover is settled at coordinates x = 0 and y = -5
    And  the rover is facing towards south

    # Turn back manoeuvre can be done both over the left and right-hand side
    # in case of moving directly backwards is not functioning.
    # Choosing the accurate side to turn is extremely important
    # when the rover is located in such a situation as around a rock or edge of an abyss.
    # There might not be a way to turn either right or left sides.
  @Smoke
  Scenario: The rover should be able to turn back over the right hand side
    When the operator sends a single command of turn right
    And  the operator sends a single command of turn right
    Then the rover is settled at coordinates x = 0 and y = 0
    And  the rover is facing towards south

  @Smoke
  Scenario: The rover should be able to turn back over the left hand side
    When the operator sends a single command of turn left
    And  the operator sends a single command of turn left
    Then the rover is settled at coordinates x = 0 and y = 0
    And  the rover is facing towards south

  @Smoke
  Scenario: The rover should be able to navigate to a given location
    When the operator sends the navigation coordinates as x = -5 and y = -3 facing 1
    Then the rover is settled at coordinates x = -5 and y = -3
    And  the rover is facing towards north

  @Regression
  Scenario Outline: The rover should be able to process a multiple navigation command sequentially
    When the operator sends the navigation coordinates as x = <axisX1> and y = <axisY1> facing <direction1>
    And  the operator sends a series of navigation command as <commands>
    Then the rover is settled at coordinates x = <axisX2> and y = <axisY2>
    And  the rover is facing towards <direction2>

    Examples:
      | axisX1 | axisY1 | direction1 | commands   | axisX2 | axisY2 | direction2 |
      | 1      | 2      | 1          | LMLMLMLMM  | 1      | 3      | north      |
      | 3      | 3      | 2          | MMRMMRMRRM | 5      | 1      | east       |

  @Regression
  Scenario Outline: The rover should be able to process a series of single navigation commands sequentially
    When the operator sends a single command of move <distance1> units <direction1>
    And  the operator sends a single command of move <distance2> units <direction2>
    And  the operator sends a single command of turn <side1>
    And  the operator sends a single command of turn <side2>
    Then the rover is settled at coordinates x = <axisX> and y = <axisY>
    And  the rover is facing towards <facing>

    Examples:
      | distance1 | direction1 | distance2 | direction2 | side1 | side2 | axisX | axisY | facing |
      | 5         | forward    | 2         | left       | right | left  | -2    | 5     | west   |
      | 4         | back       | 6         | right      | back  | left  | -6    | -4    | north  |
      | 1         | left       | 1         | back       | back  | right | 0     | 0     | north  |

