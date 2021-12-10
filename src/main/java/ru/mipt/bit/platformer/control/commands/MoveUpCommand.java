package ru.mipt.bit.platformer.control.commands;

import ru.mipt.bit.platformer.objects.Tank;

public class MoveUpCommand implements Command {

    private final Tank tank;

    public MoveUpCommand(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute(){
        tank.moveUp();
    }
}
