package ru.mipt.bit.platformer.control.commands;

import ru.mipt.bit.platformer.objects.LogicLevel;
import ru.mipt.bit.platformer.objects.Tank;

import java.util.logging.Level;

public class MoveDownCommand implements Command {

    private final Tank tank;
    private final LogicLevel logicLevel;

    public MoveDownCommand(Tank tank, LogicLevel logicLevel){
        this.tank = tank;
        this.logicLevel = logicLevel;
    }

    @Override
    public void execute(){
        tank.moveDown();
    }
}
