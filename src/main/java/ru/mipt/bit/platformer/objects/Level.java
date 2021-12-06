package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Event;
import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Observable;
import ru.mipt.bit.platformer.control.ControlByAIAdaptor;
import ru.mipt.bit.platformer.control.ControlByKey;
import ru.mipt.bit.platformer.control.ControlByRandom;
import ru.mipt.bit.platformer.control.Manager;
import ru.mipt.bit.platformer.control.commands.MoveDownCommand;
import ru.mipt.bit.platformer.control.commands.MoveLeftCommand;
import ru.mipt.bit.platformer.control.commands.MoveRightCommand;
import ru.mipt.bit.platformer.control.commands.MoveUpCommand;
import ru.mipt.bit.platformer.graphics.LevelRenderer;

import java.util.ArrayList;
import java.util.List;

public class Level implements Observable {

    public LevelRenderer levelRenderer;

    private final List<Tank> tanks;
    private final List<Tree> trees;

    private int height;
    private int width;

    private final List<Manager> managers;
    ControlByAIAdaptor aiController;

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

        aiController = new ControlByAIAdaptor(trees, tanks, width, height);

        levelRenderer = new LevelRenderer(tanks, trees);
    }

    public void createTanks(List<GridPoint2> tankCoordinates) {
        for (GridPoint2 coordinate : tankCoordinates) {
            tanks.add(new Tank(coordinate));
        }
        createControllers();
    }

    private void createControllers() {
        int i = 0;
        if (managers.isEmpty()) {
            managers.add(new ControlByKey(new MoveUpCommand(tanks.get(0)),
                    new MoveDownCommand(tanks.get(0)),
                    new MoveLeftCommand(tanks.get(0)),
                    new MoveRightCommand(tanks.get(0))));
            i = 1;
        }
        else {
            i = managers.size();
        }

        for ( ; i < tanks.size(); i++) {
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

    @Override
    public void addObserver(Object o) {

    }

    @Override
    public void removeObserver(Object o) {

    }

    @Override
    public void notifyObservers(Event event, GameObject object) {
        switch (event){
            case RemoveTank:
                int id = tanks.indexOf((Tank) object);
                tanks.remove(id);
                managers.remove(id);
                levelRenderer.update(event, id);
                break;
            default:
                break;
        }

    }
}
