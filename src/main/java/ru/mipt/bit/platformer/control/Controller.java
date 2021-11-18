package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.objects.Tree;

import java.util.List;

public class Controller {
    private Command moveUpCommand;
    private Command moveDownCommand;
    private Command moveLeftCommand;
    private Command moveRightCommand;

    public Controller(Command moveUpCommand,
                      Command moveDownCommand,
                      Command moveLeftCommand,
                      Command moveRightCommand){
        this.moveUpCommand = moveUpCommand;
        this.moveDownCommand = moveDownCommand;
        this.moveLeftCommand = moveLeftCommand;
        this.moveRightCommand = moveRightCommand;
    }

    public void moveUp(){
        moveUpCommand.execute();
    }

    public void moveDown(){
        moveDownCommand.execute();
    }

    public void moveLeft(){
        moveLeftCommand.execute();
    }

    public void moveRight(){
        moveRightCommand.execute();
    }
}
