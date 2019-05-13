package com.riarrobots.simulator.config;

import com.riarrobots.simulator.enums.Direction;

import java.util.Random;

public class ApplicationConfig {
    public static final int MIN_FLOOR = 5;
    public static final int MAX_FLOOR = 20;
    public static final int MIN_FLOOR_PASS = 0;
    public static final int MAX_FLOOR_PASS = 10;
    public static final int MAX_ELEVATOR_PASS = 5;
    public static final int FLOOR_NUMBER = calculateNum(MIN_FLOOR, MAX_FLOOR);
    public static final boolean INFiNITI = false;

    public static int calculateNum(int min, int max) {
        Random random = new Random();
        return (min + random.nextInt(max - min));
    }

    public static int calculateNum(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public static Direction initDirection(int needDestinationFloor, int initFloor) {

        if (initFloor == 0) {
            return Direction.UP;
        } else if (initFloor == FLOOR_NUMBER) {
            return Direction.DOWN;
        } else if (needDestinationFloor < initFloor) {
            return Direction.DOWN;
        } else {
            return Direction.UP;
        }
    }

    public static String getviewDirection(Direction direction) {

        if (direction.equals(Direction.UP)){
            return "▲ ";
        }else if (direction.equals(Direction.DOWN)){
            return "▼ ";
        }else if (direction.equals(Direction.UP_DOWN)){
            return "▲▼";
        }else if (direction.equals(Direction.NONE)){
            return "  ";
        }else{
            return "  ";
        }

    }




}
