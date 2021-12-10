package ru.mipt.bit.platformer.control.commands;

import ru.mipt.bit.platformer.objects.gameObjects.Tank;
/**
 * Use case
 */
public class MoveDownCommand implements Command {

    private final Tank tank;

    public MoveDownCommand(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute(){
        tank.moveDown();
    }
}
