package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.control.commands.Command;
/**
 * Use case
 */
public class Control {
    private final Command moveUpCommand;
    private final Command moveDownCommand;
    private final Command moveLeftCommand;
    private final Command moveRightCommand;
    private final Command shootCommand;
    private final Command noMoveCommand;

    public Control(Command moveUpCommand,
                           Command moveDownCommand,
                           Command moveLeftCommand,
                           Command moveRightCommand,
                   Command shootCommand,
                   Command noMoveCommand){
        this.moveUpCommand = moveUpCommand;
        this.moveDownCommand = moveDownCommand;
        this.moveLeftCommand = moveLeftCommand;
        this.moveRightCommand = moveRightCommand;
        this.shootCommand = shootCommand;
        this.noMoveCommand = noMoveCommand;
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

    public void shoot(){ shootCommand.execute(); }

    public Command getMoveDownCommand() {
        return moveDownCommand;
    }

    public Command getMoveLeftCommand() {
        return moveLeftCommand;
    }

    public Command getMoveRightCommand() {
        return moveRightCommand;
    }

    public Command getMoveUpCommand() {
        return moveUpCommand;
    }

    public Command getShootCommand() {
        return shootCommand;
    }

    public Command getNoMoveCommand() {
        return noMoveCommand;
    }
}
