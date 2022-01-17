package steps;

import controller.RoverController;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.Map;

public class MyStepDefinitions {

    RoverController roverController = new RoverController();
    SoftAssert softAssert = new SoftAssert();

    @Given("^the rover is landed mars at the given coordinates$")
    public void theRoverIsLandedMarsOnTheInitialCoordinates(DataTable position) throws Throwable {
        List<Map<String, Integer>> location = position.asMaps(String.class, Integer.class);
        roverController.setPosition(
                location.get(0).get("axisX"),
                location.get(0).get("axisY"),
                location.get(0).get("facing"));
    }

    @When("^the operator sends a single command of turn (left|right|back)$")
    public void theOperatorSendsASingleTurnLeftCommand(String direction) {
        switch (direction) {
            case "left":
                roverController.turnLeft();
                break;
            case "right":
                roverController.turnRight();
                break;
            case "back":
                roverController.turnBack();
                break;
        }
    }

    @When("^the operator sends a single command of move (\\d+) units (left|right|back|forward)$")
    public void theOperatorSendsASingleMoveCommandOfUnitForward(int distance, String direction) {
        switch (direction) {
            case "left":
                roverController.turnLeft();
                roverController.move(distance);
                break;
            case "right":
                roverController.turnRight();
                roverController.move(distance);
                break;
            case "back":
                roverController.turnBack();
                roverController.move(distance);
                break;
            case "forward":
                roverController.move(distance);
                break;
        }
    }

    @When("the operator sends the navigation coordinates as x = {int} and y = {int} facing {int}")
    public void theOperatorSendsTheNavigationCoordinatesAsXAndY(int coordinateX, int coordinateY, int direction) {
        roverController.setPosition(coordinateX, coordinateY, direction);
        System.out.println("Move: The rover has moved to a given location "
                + "(" + roverController.getPosition() + ")");
    }

    @When("^the operator sends a series of navigation command as (.*)$")
    public void theOperatorSendsASeriesOfNavigationCommandAs(String commands) {
        roverController.process(commands);
    }


    // using SoftAssert from TestNG for multiple assertions so that
    // any failing ones can be tracked after test execution
    @Then("the rover is settled at coordinates x = {int} and y = {int}")
    public void theRoverIsSettledAtCoordinatesXAndY(int coordinateX, int coordinateY) {
        var actualX = roverController.getAxisX();
        var actualY = roverController.getAxisY();
        softAssert.assertEquals(actualX, coordinateX,
                "Coordinate X is NOT correct!");
        softAssert.assertEquals(actualY, coordinateY,
                "Coordinate Y is NOT correct!");
        softAssert.assertAll();
    }

    @Then("^the rover is facing towards (north|east|south|west)$")
    public void theRoverIsFacingTowardsDirection(String facing) {
        var actualDirection = roverController.getDirection();
        int direction = 0;
        switch (facing) {
            case "north":
                direction = 1;
                break;
            case "east":
                direction = 2;
                break;
            case "south":
                direction = 3;
                break;
            case "west":
                direction = 4;
                break;
        }
        Assert.assertEquals(actualDirection, direction,
                "Direction is NOT correct!");
    }

    @Then("the rover is not positioning at its initial coordinates")
    public void theRoverIsNotPositioningAtItsInitialCoordinates() {
        Assert.assertNotEquals(RoverController.getPosition(), "0 0 N",
                "The rover has not changed its initial position");
    }
}
