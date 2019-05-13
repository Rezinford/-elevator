package com.riarrobots.simulator.element;

import java.util.*;

public class Container {
    private List<Passenger> passengersList = new ArrayList<Passenger>();

    public void addint(Passenger passenger) {
        passengersList.add(passenger);
    }

    public void addPass(int initFloor) {
        passengersList.add(new Passenger(initFloor));
    }

    public Passenger getpassenger(int i) {
        if(getPassengersNumber()>=i) {
            return passengersList.get(i);
        }
        return null;
    }

    public void removePass(Passenger passenger) {
        passengersList.remove(passenger);
    }

    public void removePass(int i) {
        passengersList.remove(i);
    }

    public int getPassengersNumber() {
        return passengersList.size();
    }







}
