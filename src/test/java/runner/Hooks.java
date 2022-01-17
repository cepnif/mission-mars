package runner;

import controller.RoverController;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    // prints initial location of the rover before starting test
    @Before
    public void initialLocation(){
        System.out.println("ROVER'S INITIAL LOCATION >>> "
                + RoverController.getPosition());
    }

    // prints final location of the rover and also prints
    // test result as PASSED or FAILED with a separator within the log
    @After
    public void finalLocation(Scenario scenario){
        System.out.println("ROVER'S FINAL LOCATION >>> "
                + RoverController.getPosition());
        System.out.println("TEST " + scenario.getStatus());
        System.out.println("===========================================================================");
    }
}
