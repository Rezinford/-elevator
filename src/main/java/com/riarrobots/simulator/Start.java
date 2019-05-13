package com.riarrobots.simulator;

import com.riarrobots.simulator.config.ApplicationConfig;
import com.riarrobots.simulator.logic.LifeConstructor;

public class Start {
    public static void main(String[] args) {
        System.out.println("***Start Simulation***");
        ApplicationConfig applicationConfig = new ApplicationConfig();
        LifeConstructor lifeConstructor = new LifeConstructor();
//        lifeConstructor.building.display();
        System.out.println("");
        lifeConstructor.work();
//
        System.out.println("***End Simulation***");
    }
}


