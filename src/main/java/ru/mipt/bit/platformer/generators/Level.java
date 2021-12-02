package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Tank> tanks;
    private List<Tree> trees;

    private int height;
    private int width;

    public Level(){
        tanks = new ArrayList<>();
        trees = new ArrayList<>();
        height = 0;
        width = 0;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void createTanks(List<GridPoint2> tankCoordinates) {
        for (GridPoint2 coordinate : tankCoordinates) {
            tanks.add(new Tank(coordinate));
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
}
