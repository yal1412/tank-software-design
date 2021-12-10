package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.control.ControlByAI;
import ru.mipt.bit.platformer.control.ControlByKey;
import ru.mipt.bit.platformer.control.ControlByRandom;
import ru.mipt.bit.platformer.control.Manager;
import ru.mipt.bit.platformer.control.commands.MoveDownCommand;
import ru.mipt.bit.platformer.control.commands.MoveLeftCommand;
import ru.mipt.bit.platformer.control.commands.MoveRightCommand;
import ru.mipt.bit.platformer.control.commands.MoveUpCommand;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private final List<Tank> tanks;
    private final List<Tree> trees;

    private int height;
    private int width;

    private final List<Manager> managers;
    ControlByAI aiController;

    public Level(){
        tanks = new ArrayList<>();
        trees = new ArrayList<>();
        height = 0;
        width = 0;
        managers = new ArrayList<>();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void createObjects(List<GridPoint2> tankCoordinates, List<GridPoint2> treeCoordinates){
        createTanks(tankCoordinates);
        createTrees(treeCoordinates);

        aiController = new ControlByAI(trees, tanks, width, height);
    }

    public void createTanks(List<GridPoint2> tankCoordinates) {
        for (GridPoint2 coordinate : tankCoordinates) {
            tanks.add(new Tank(coordinate));
        }
        createControllers();
    }

    private void createControllers() {
        managers.add(new ControlByKey(new MoveUpCommand(tanks.get(0)),
                        new MoveDownCommand(tanks.get(0)),
                        new MoveLeftCommand(tanks.get(0)),
                        new MoveRightCommand(tanks.get(0))));

        for (int i = 1; i < tanks.size(); i++) {
            managers.add(new ControlByRandom(new MoveUpCommand(tanks.get(i)),
                            new MoveDownCommand(tanks.get(i)),
                            new MoveLeftCommand(tanks.get(i)),
                            new MoveRightCommand(tanks.get(i))));
        }
    }

    public void createTrees(List<GridPoint2> treeCoordinates) {
        for (GridPoint2 coordinate : treeCoordinates) {
            trees.add(new Tree(coordinate));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void moveTanks() {
        for (int i = 0; i < tanks.size(); i++) {
            if (tanks.get(i).hasFinishedMovement()) {
                managers.get(i).executeCommand();
//                aiController.executeCommand();
            }
        }
        for (Tank tank : tanks) {
            tank.moveCommand(trees, width, height);
        }
    }
}
