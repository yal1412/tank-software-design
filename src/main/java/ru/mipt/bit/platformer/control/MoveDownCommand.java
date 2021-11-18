package ru.mipt.bit.platformer.control;

import ru.mipt.bit.platformer.objects.Tank;

public class MoveDownCommand implements Command{

    private Tank tank;

    public MoveDownCommand(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute(){
        tank.moveDown();
    }
}
