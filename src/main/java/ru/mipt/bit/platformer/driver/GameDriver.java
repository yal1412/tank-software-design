package ru.mipt.bit.platformer.driver;

import ru.mipt.bit.platformer.control.controllers.ControlByAIAdaptor;
import ru.mipt.bit.platformer.control.controllers.ControlByKey;
import ru.mipt.bit.platformer.control.controllers.ControlByRandom;
import ru.mipt.bit.platformer.control.controllers.Controller;
import ru.mipt.bit.platformer.control.commands.*;
import ru.mipt.bit.platformer.driver.observation.Event;
import ru.mipt.bit.platformer.driver.observation.Observer;
import ru.mipt.bit.platformer.objects.gameObjects.GameObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Adapter
 */
public class GameDriver implements Observer {

    private final LogicLevel logicLevel;
    private final List<Controller> controllers;

    ControlByAIAdaptor aiController;

    public GameDriver(LogicLevel logicLevel) {
        this.logicLevel = logicLevel;
        controllers = new ArrayList<>();
        aiController = new ControlByAIAdaptor(logicLevel);
    }

    public void createManagerForPlayer() {
        controllers.add(new ControlByKey(new MoveUpCommand(logicLevel.getTanks().get(0)),
                                      new MoveDownCommand(logicLevel.getTanks().get(0)),
                                      new MoveLeftCommand(logicLevel.getTanks().get(0)),
                                      new MoveRightCommand(logicLevel.getTanks().get(0)),
                                      new ShootCommand(logicLevel.getTanks().get(0), logicLevel),
                                      new NoMoveCommand(),
                                      this));
    }

    public void createManagerForTanks() {
        int i = controllers.size();

        while (controllers.size() < logicLevel.getTanks().size()) {

            controllers.add(new ControlByRandom(new MoveUpCommand(logicLevel.getTanks().get(i)),
                                             new MoveDownCommand(logicLevel.getTanks().get(i)),
                                             new MoveLeftCommand(logicLevel.getTanks().get(i)),
                                             new MoveRightCommand(logicLevel.getTanks().get(i)),
                                             new ShootCommand(logicLevel.getTanks().get(i), logicLevel),
                                             new NoMoveCommand()));
            i++;
        }
    }

    public void generateCommands() {
        for (int i = 0; i < logicLevel.getTanks().size(); i++) {
            if (logicLevel.getTanks().get(i).hasFinishedMovement()) {
                controllers.get(i).generateCommand();
            }
        }
    }

    public void executeCommands() {
        for (int i = 0; i < logicLevel.getTanks().size(); i++) {
            if (logicLevel.getTanks().get(i).hasFinishedMovement()) {
                controllers.get(i).executeCommand();
            }
        }
    }

    public void updateObjects() {
        logicLevel.checkObjects();
        logicLevel.moveTanks();
        logicLevel.moveBullets();
    }

    @Override
    public void update(Event event, GameObject gameObject, int id) {
        if (event == Event.OnOffHealth) {
            logicLevel.levelRenderer.update(event, null, 0);
        }
    }
}
