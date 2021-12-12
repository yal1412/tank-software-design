package ru.mipt.bit.platformer.control.controllers;

import ru.mipt.bit.platformer.control.Control;
import ru.mipt.bit.platformer.control.commands.Command;
import ru.mipt.bit.platformer.control.commands.ShootCommand;

/**
 * Use case
 */
public class ControlByRandom implements Controller {
    private final Control control;
    Command command;

    public ControlByRandom(Command moveUpCommand,
                           Command moveDownCommand,
                           Command moveLeftCommand,
                           Command moveRightCommand,
                           Command shootCommand,
                           Command noMoveCommand){
        control = new Control(moveUpCommand, moveDownCommand, moveLeftCommand, moveRightCommand, shootCommand, noMoveCommand);
    }

    @Override
    public void generateCommand() {
        int mover = (int) (Math.random() * 4);
        int shooter = (int) (Math.random() * 100);

        if (shooter < 10) {
            command = control.getShootCommand();
        }
        else {
            switch (mover) {
                case 0:
                    command = control.getMoveUpCommand();
                    break;
                case 1:
                    command = control.getMoveDownCommand();
                    break;
                case 2:
                    command = control.getMoveLeftCommand();
                    break;
                case 3:
                    command = control.getMoveRightCommand();
                    break;
            }
        }
    }

    @Override
    public void executeCommand() {
//        if (shooter < 10) {
//            control.shoot();
//        }
//        else {
//            switch (mover) {
//                case 0:
//                    control.moveUp();
//                    break;
//                case 1:
//                    control.moveDown();
//                    break;
//                case 2:
//                    control.moveLeft();
//                    break;
//                case 3:
//                    control.moveRight();
//                    break;
//            }
//        }
        command.execute();
    }
}
