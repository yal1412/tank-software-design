package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.objects.Tank;

public class MoveUpCommand implements Command{
    private Tank tank;

    public MoveUpCommand(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute(){
        tank.moveUp();
    }
}
