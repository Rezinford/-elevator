package com.riarrobots.simulator.component;

import com.riarrobots.simulator.element.ElevatorCore;
import com.riarrobots.simulator.enums.Direction;

import java.util.ArrayList;
import java.util.TreeSet;

import static com.riarrobots.simulator.config.ApplicationConfig.FLOOR_NUMBER;

public class ElevetorSystem extends ElevatorCore {

    public ElevetorSystem(int capacity) {
        super(capacity);
    }

    public ElevetorSystem() {
    }

    public int maxFlor() {
        int maxFlor = getpassenger(0).getNeedDestinationFloor();

        for (int i = 1; i < getPassengersNumber(); i++) {
            if (maxFlor < getpassenger(i).getNeedDestinationFloor()) {
                maxFlor = getpassenger(i).getNeedDestinationFloor();
            }
        }
        return maxFlor;
    }

    public int getValveMove(TreeSet<Integer> valvefloor, int currentFloor) {
        ArrayList<Integer> ref = new ArrayList<Integer>(valvefloor);
        int valve = 0;
        if (ref.get(0) > currentFloor) {
            return ref.get(0) - currentFloor;
        } else {
            return currentFloor - ref.get(0);
        }
    }

    public int followingFloor(ArrayList<Direction> floors, int CurrentFloor) {
        int floor = 0;
        if (getCurrentFloor() == 0) {
            for (int i = 0; i < FLOOR_NUMBER - 1; i++) {
                if (getvalvefloor(floors.get(i))) {
                    return i;
                }
            }

        } else if (getCurrentFloor() == FLOOR_NUMBER - 1) {
            for (int i = FLOOR_NUMBER - 1; i >= 0; i--) {
                if (getvalvefloor(floors.get(i))) {
                    return i;
                }
            }
        } else if (getCurrentFloor() > ((FLOOR_NUMBER - 1) / 2)) {
            for (int i = FLOOR_NUMBER - 1; i > getCurrentFloor(); i--) {
                if (getvalvefloor(floors.get(i))) {
                    return i;
                }
            }
        } else if (getCurrentFloor() <= ((FLOOR_NUMBER - 1) / 2)) {
            for (int i = getCurrentFloor(); i < FLOOR_NUMBER - 1; i++) {
                if (getvalvefloor(floors.get(i))) {
                    return i;
                }
            }
        }
        return 0;
    }

    public boolean vlidate(Direction directFloor) {
        return (getDirection().equals(directFloor)) ? true : false;

    } //Validate match Direction

    public boolean getvalvefloor(Direction direction) {
        switch (direction) {
            case UP:
                return true;
//                break;
            case DOWN:
                return true;
//                break;
            case UP_DOWN:
                return true;
//                break;
            default:
                return false;
        }

    }

}
