package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.control.commands.Command;

public class Control {
    private final Command moveUpCommand;
    private final Command moveDownCommand;
    private final Command moveLeftCommand;
    private final Command moveRightCommand;

    public Control(Command moveUpCommand,
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
