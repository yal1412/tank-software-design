package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.List;

public class MoveUpCommand implements Command{

    private Tank tank;
    private List<Tree> trees;
    private int width;
    private int height;

    public MoveUpCommand(Tank tank, List<Tree> trees, int width, int height){
        this.tank = tank;
        this.trees = trees;
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute(){
        tank.moveUp(trees, width, height);
    }
}
