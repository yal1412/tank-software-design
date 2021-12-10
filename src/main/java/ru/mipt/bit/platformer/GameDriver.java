package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.control.ControlByAIAdaptor;
import ru.mipt.bit.platformer.control.ControlByKey;
import ru.mipt.bit.platformer.control.ControlByRandom;
import ru.mipt.bit.platformer.control.Manager;
import ru.mipt.bit.platformer.control.commands.*;
import ru.mipt.bit.platformer.objects.GameObject;
import ru.mipt.bit.platformer.objects.LogicLevel;

import java.util.ArrayList;
import java.util.List;

public class GameDriver{

    private final LogicLevel logicLevel;
    private final List<Manager> managers;

    ControlByAIAdaptor aiController;

    public GameDriver(LogicLevel logicLevel) {
        this.logicLevel = logicLevel;
        managers = new ArrayList<>();
        aiController = new ControlByAIAdaptor(logicLevel);
    }

    public void createManagerForPlayer() {
        managers.add(new ControlByKey(new MoveUpCommand(logicLevel.getTanks().get(0), logicLevel),
                                      new MoveDownCommand(logicLevel.getTanks().get(0), logicLevel),
                                      new MoveLeftCommand(logicLevel.getTanks().get(0), logicLevel),
                                      new MoveRightCommand(logicLevel.getTanks().get(0), logicLevel),
                                      new ShootCommand(logicLevel.getTanks().get(0), logicLevel)));
    }

    public void createManagerForTanks() {
        int i = managers.size();

        while (managers.size() < logicLevel.getTanks().size()) {

            managers.add(new ControlByRandom(new MoveUpCommand(logicLevel.getTanks().get(i), logicLevel),
                                             new MoveDownCommand(logicLevel.getTanks().get(i), logicLevel),
                                             new MoveLeftCommand(logicLevel.getTanks().get(i), logicLevel),
                                             new MoveRightCommand(logicLevel.getTanks().get(i), logicLevel),
                                             new ShootCommand(logicLevel.getTanks().get(i), logicLevel)));
            i++;
        }
    }

    public void generateCommands() {
        for (int i = 0; i < logicLevel.getTanks().size(); i++) {
            if (logicLevel.getTanks().get(i).hasFinishedMovement()) {
                managers.get(i).executeCommand();
            }
        }
    }

    public void updateObjects() {
        logicLevel.checkObjects();
        logicLevel.moveTanks();
        logicLevel.moveBullets();
    }
}
