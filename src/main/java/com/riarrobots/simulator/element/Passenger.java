package com.riarrobots.simulator.element;

import com.riarrobots.simulator.enums.Direction;
import com.riarrobots.simulator.interfaces.Diraction;

import java.util.Random;

import static com.riarrobots.simulator.config.ApplicationConfig.*;

public class Passenger implements Diraction {
    private static int passIDGen = 1; // Generator ID counts
    private final int passengerID;
    private Direction direction;
    private int needDestinationFloor;
    private int initFloor;

    public Passenger(int initFloor) {
        this.passengerID = passIDGen++;
        this.initFloor = initFloor;
        needDestinationFloor = calculateNum(initFloor);
        direction = initDirection(needDestinationFloor, initFloor);
    }

    public int getPassengerID() {
        return passengerID;
    }

    public Direction getDirection() {
        return direction;
}

    public int getNeedDestinationFloor() {
        return needDestinationFloor;
    }

    public int getInitFloor() {
        return initFloor;
    }

    public void setInitFloor(int initFloor) {
        this.initFloor = initFloor;

        direction = initDirection(needDestinationFloor, initFloor);
    }

    private static int calculateNum(int init) {
        Random random = new Random();
        int num;
        if (init == 0) {
            num = 1 + random.nextInt(FLOOR_NUMBER - 1);
        } else if (init == FLOOR_NUMBER) {
            num = random.nextInt(FLOOR_NUMBER - 2);
        } else {
            num = random.nextInt(FLOOR_NUMBER);
            while (init == num) {
                num = random.nextInt(FLOOR_NUMBER);
            }
        }
        return num;
    }

}
