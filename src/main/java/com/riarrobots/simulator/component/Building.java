package com.riarrobots.simulator.component;

import com.riarrobots.simulator.element.Container;
import com.riarrobots.simulator.enums.Direction;

import java.util.ArrayList;
import java.util.List;

import static com.riarrobots.simulator.config.ApplicationConfig.*;

public class Building {
    public final List<Container> floors;
    public final List<Container> outfloors;
    public final ElevetorSystem elevator;
    public static int step = 0;
    private final int elevatorcapacity;
    private final int floorCount;

    public Building(int elevatorcapacity, int floorCount) {
        this.elevatorcapacity = elevatorcapacity;
        this.floorCount = floorCount;
        elevator = new ElevetorSystem(elevatorcapacity);
        floors = new ArrayList<Container>();
        for (int i = 0; i < floorCount; i++) {
            floors.add(new Container());
        }
        outfloors = new ArrayList<Container>();
        for (int i = 0; i < floorCount; i++) {
            outfloors.add(new Container());
        }
    }

    public Direction getDirectionFloat(int i) {
        try {
            int up = 0;
            int down = 0;
            if (floors.get(i).getPassengersNumber() == 0) {
                return Direction.NONE;
            } else {

                for (int j = 0; j < floors.get(i).getPassengersNumber(); j++) {
                    switch (floors.get(i).getpassenger(j).getDirection()) {
                        case UP:
                            up++;
                            break;
                        case DOWN:
                            down++;
                            break;
                    }
                }
                return (up == down) ? Direction.UP_DOWN : ((up > down) ? Direction.UP : Direction.DOWN);
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }
        return Direction.UP_DOWN;
    } // посмотреть что это

    public void displayFloor(int i) {
        System.out.print(getviewDirection(getDirectionFloat(i)));
        System.out.print("| ");
        for (int j = 0; j < floors.get(i).getPassengersNumber(); j++) {
            System.out.print(floors.get(i).getpassenger(j).getNeedDestinationFloor());
            System.out.print(getviewDirection(floors.get(i).getpassenger(j).getDirection()));
            System.out.print(" ");
        }
    }

    public void displayOutFloor(int i) {
//        System.out.print(getviewDirection(getDirectionFloat(i)));
        System.out.print(" /out/ ");
        for (int j = 0; j < outfloors.get(i).getPassengersNumber(); j++) {
            System.out.print(outfloors.get(i).getpassenger(j).getNeedDestinationFloor());
//            System.out.print(getviewDirection(outfloors.get(i).getpassenger(j).getDirection()));
            System.out.print(" ");
        }
    }

    public void display() {
            DispleyStep();
            for (int line = 0; line < 10; line++) {
                System.out.print(" - -");
            }
            System.out.println();
            for (int i = FLOOR_NUMBER - 1; i >= 0; i--) {
                if (i >= 10) {
                    System.out.print(i);
                } else {
                    System.out.print(i + " ");
                }

                elevator.Displey(i);
                displayFloor(i);

                displayOutFloor(i);

                System.out.println();
                for (int line = 0; line < 10; line++) {
                    System.out.print(" - -");
                }
                System.out.println();
            }

    }

    public ArrayList<Direction> getDirectionSignsl() {
        ArrayList<Direction> directSignal = new ArrayList<Direction>();
    try {
        for (int i = 0; i < floorCount; i++) {
            directSignal.add(getDirectionFloat(i));
        }
    }catch (StackOverflowError e){
        System.out.println("ssss");
    }
        return directSignal;
    }

    public int getDirectionSignslfull() {
        int size = 0;
        ArrayList<Direction> directSignal = new ArrayList<Direction>(getDirectionSignsl());
        for (int i = 0; i < floorCount-1; i++) {
            if (directSignal.get(i) != Direction.NONE) {
                size++;
            }
        }
        return size;
    }

    public void passGoOut(int currentFloor) {
        if (!INFiNITI) {
            int index = 0;
            int passinfloor = elevator.getPassengersNumber();
            for (int i = 0; i < passinfloor; i++) {
                if (elevator.getpassenger(i - index).getNeedDestinationFloor() == currentFloor) {
                    outfloors.get(currentFloor).addint(elevator.getpassenger(i - index));
                    elevator.removePass(i - index);
                    index++;
                }
            }
        } else {
            //перемещение в другой контейнер
        }
    }

    public void addToElevetor(int currentFloor) {
        int index = 0;
        int passinfloor = floors.get(currentFloor).getPassengersNumber();
        for (int i = 0; i < passinfloor; i++) {
            if (elevator.getPassengersNumber() < elevatorcapacity) {
                if (elevator.getDirection().equals(floors.get(currentFloor).getpassenger(i - index).getDirection())) {
                    elevator.addint(floors.get(currentFloor).getpassenger(i - index));
                    floors.get(currentFloor).removePass(i - index);
                    index++;

                }
            }
        }
    }

    public boolean needWork() {
        if (elevator.getFullPlace()) {
            if (getDirectionSignslfull() != 0) {
                return true;
            } else {
                return true;
            }
        } else {
            if (getDirectionSignslfull() != 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void start() {
        if (needWork()) {
            if (elevator.getFullPlace()) {
                if (elevator.getDirection() == Direction.UP) {
                    for (int i = elevator.getCurrentFloor(); i < elevator.getMaxFloor(); i++) {
                        elevatormoveUP();
                        migration();
                        display();
                    }
                } else {
                      for (int i = elevator.getCurrentFloor(); i > elevator.getMinFloor(); i--) {
                          if (needWork()) {
                              elevatormoveDown();
                              migration();
                              display();
                          }else {
                              elevator.goTOEND();
                          }
                        }
                }
                if (elevator.getvalvefloor(getDirectionSignsl().get(elevator.getCurrentFloor()))){
                    migration();
                    migration();
                }
            } else {
                int fullfloor = elevator.followingFloor(getDirectionSignsl(), elevator.getCurrentFloor());
                if (elevator.getCurrentFloor() != fullfloor) {
                    if (elevator.getCurrentFloor() < fullfloor) {
                        for (int i = 0; i < fullfloor; i++) {
                            elevator.goUp();
                            elevator.setDirectionUP();
                            display();
                        }
                    } else {
                        for (int i = elevator.getCurrentFloor(); i > fullfloor; i--) {
                            elevator.goDown();
                            elevator.setDirectionDown();

                            display();
                        }
                    }
                    if (getDirectionFloat(elevator.getCurrentFloor())==Direction.DOWN ){
                        elevator.setDirectionDown();
                    }else if (getDirectionFloat(elevator.getCurrentFloor())==Direction.UP ){
                        elevator.setDirectionUP();
                    }
                    migration();
                    display();
                } else {
                    migration();
                    elevatormoveUP();
                    display();
                }
            }
            start();
        }
    }

    public void elmovGO() {
        if (elevator.vlidate(getDirectionFloat(elevator.getCurrentFloor()))) {
            if (elevator.getFull()) {
                migration();
                elevator.maxFlor();
            } else {
                migration();
            }
        } else {
            migration();
        }
    }

    public void elevatormoveUP(){
        elmovGO();
        elevator.goUp();
        elevator.setDirectionUP();
    }

    public void elevatormoveDown(){
        elmovGO();
        elevator.goDown();
        elevator.setDirectionDown();

    }

    public void migration() {
        if (needWork()) {
            passGoOut(elevator.getCurrentFloor());
            addToElevetor(elevator.getCurrentFloor());
        }
    }

    public static void DispleyStep() {
        System.out.println("***************Step " + step + "********************");
        step++;
    }

}
