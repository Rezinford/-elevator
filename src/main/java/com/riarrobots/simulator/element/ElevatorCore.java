package com.riarrobots.simulator.element;

import com.riarrobots.simulator.enums.Direction;
import com.riarrobots.simulator.interfaces.Diraction;

import static com.riarrobots.simulator.config.ApplicationConfig.getviewDirection;

public class ElevatorCore extends Container implements Diraction {
    private Direction direction;
    private int currentFloor = 0;
    private final int capacity;

    public ElevatorCore(int capacity) {
        this.capacity = capacity;
        this.direction = Direction.UP;
    }

    public ElevatorCore() {
        capacity = 0;
        this.direction = Direction.UP;
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirectionDown(){
        direction = Direction.DOWN;
    }

    public void setDirectionUP() {
        direction = Direction.UP;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int goTOEND() {
        return currentFloor = 0;
    }

    public int getMaxFloor(){
        int max = 0;
        for (int i = 0; i < getPassengersNumber(); i++) {
            max = (max > getpassenger(i).getNeedDestinationFloor())? max: getpassenger(i).getNeedDestinationFloor();
        }
        return max;
    }

    public int getMinFloor(){
        int min = 0;
        for (int i = 0; i < getPassengersNumber(); i++) {
            min = (min < getpassenger(i).getNeedDestinationFloor())? min: getpassenger(i).getNeedDestinationFloor();
        }
        return min;
    }

    public boolean getFull() {
        return (getPassengersNumber() == capacity) ? false : true;
    } //validate full

    public boolean getFullPlace() {
        return (getPassengersNumber() > 0) ? true : false;
    }

    public void goUp() {
        currentFloor ++;
    }

    public void goDown() {
        currentFloor--;
    }

    public void Displey(int i) {
        if (currentFloor == i) {
            Displey();
        } else {
            DispleyEmpty();
        }

    }

    private void Displey() {
        System.out.print("|");
        System.out.print(getviewDirection(getDirection()));
        System.out.print("|");
        for (int i = 0; i < getPassengersNumber(); i++) {
            System.out.print(getpassenger(i).getNeedDestinationFloor());
            System.out.print(getviewDirection(getpassenger(i).getDirection()));
            System.out.print(" ");
        }
        if (getPassengersNumber() < capacity) {
            for (int i = 0; i < (capacity - getPassengersNumber()-1); i++) {
                System.out.print("  ");
            }
        }
        System.out.print("|");

    }

    private void DispleyEmpty() {
        System.out.print("|");
        System.out.print("  ");
        System.out.print("|");
        for (int i = 0; i < capacity; i++) {
            System.out.print("  ");
        }
        System.out.print("|");

    }

}
