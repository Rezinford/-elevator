package com.riarrobots.simulator.logic;

import com.riarrobots.simulator.component.Building;
import com.riarrobots.simulator.component.ElevetorSystem;

import java.util.concurrent.ExecutorService;

import static com.riarrobots.simulator.config.ApplicationConfig.*;

public class LifeConstructor {
    public Building building;
    private Thread guiWorkerThread;
    private ExecutorService threadPool;
    private ElevetorSystem controller;

    public void work() {
        building = new Building(MAX_ELEVATOR_PASS, FLOOR_NUMBER);
        for (int i = 0; i < FLOOR_NUMBER-1; i++) {
            for (int j = 0; j < calculateNum(MIN_FLOOR_PASS, MAX_FLOOR_PASS)-1; j++) {
                    building.floors.get(i).addPass(i);
            }
        }
        building.display();
        building.start();

    }



}
