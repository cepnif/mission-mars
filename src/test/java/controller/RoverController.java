// changed package name
package controller;

// changed class name
public class RoverController {
    public static final Integer N = 1;
    public static final Integer E = 2;
    public static final Integer S = 3;
    public static final Integer W = 4;

    // access modifiers changed to private
    private static Integer x = 0;
    private static Integer y = 0;
    private static Integer facing = N;

    // removed the constructor - not required

    public void setPosition(Integer x, Integer y, Integer facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public int getAxisX() {
        return this.x;
    }

    public int getAxisY() {
        return this.y;
    }

    public int getDirection() {
        return this.facing;
    }

    // changed to static that returns the rover's current position as string
    public static String getPosition() {
        char direction = 'N';
        if (facing == 1) {
            direction = 'N';
        } else if (facing == 2) {
            direction = 'E';
        } else if (facing == 3) {
            direction = 'S';
        } else if (facing == 4) {
            direction = 'W';
        }
        String position = String.valueOf(x) + " " + String.valueOf(y) + " " + direction;
        return position;
    }

    public void process(String commands) {
        for (int idx = 0; idx < commands.length(); idx++) {
            process(commands.charAt(idx));
        }
    }

    // added a turn back function
    private void process(Character command) {
        if (command.equals('L')) {
            turnLeft();
        } else if (command.equals('R')) {
            turnRight();
        } else if (command.equals('M')) {
            move(1);
        } else if (command.equals("B")) {
            turnBack();
        } else {
            throw new IllegalArgumentException(
                    "Speak in Mars language, please!");
        }
    }

    // access modifier changed to public
    // added a distance parameter so that multiple navigation steps would be available
    // added a print statement to log the rover's moves
    // a logging library like log4j can be added instead
    public void move(int distance) {
        if (facing == N) {
            this.y = y + distance;
            System.out.println("Move: The rover has moved "
                    + distance + " units to North " + "(" + getPosition() + ")");
        } else if (facing == E) {
            this.x = x + distance;
            System.out.println("Move: The rover has moved " + distance
                    + " units to East " + "(" + getPosition() + ")");
        } else if (facing == S) {
            this.y = y - distance;
            System.out.println("Move: The rover has moved " + distance
                    + " units to South " + "(" + getPosition() + ")");
        } else if (facing == W) {
            this.x = x - distance;
            System.out.println("Move: The rover has moved " + distance
                    + " units to West " + "(" + getPosition() + ")");
        }
    }

    // access modifier changed to public
    // added a print statement to log the rover's moves
    // a logging library like log4j can be added instead
    public void turnLeft() {
        facing = (facing - 1) < N ? W : facing - 1;
        System.out.println("Turn: The rover has turned left "
                + "(" + getPosition() + ")");
    }

    // access modifier changed to public
    // added a print statement to log the rover's moves
    // a logging library like log4j can be added instead
    public void turnRight() {
        facing = (facing + 1) > W ? N : facing + 1;
        System.out.println("Turn: The rover has turned right "
                + "(" + getPosition() + ")");
    }

    // added turn back method
    public void turnBack() {
        switch (facing) {
            case 1:
                facing = S;
                break;
            case 2:
                facing = W;
                break;
            case 3:
                facing = N;
                break;
            case 4:
                facing = E;
                break;
        }
        System.out.println("Turn: The rover has turned back "
                + "(" + getPosition() + ")");
    }

    //removed the main method - not required
}
