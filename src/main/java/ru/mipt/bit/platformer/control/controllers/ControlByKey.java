package ru.mipt.bit.platformer.control.controllers;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.control.Control;
import ru.mipt.bit.platformer.control.commands.Command;
import ru.mipt.bit.platformer.driver.GameDriver;
import ru.mipt.bit.platformer.driver.observation.Event;
import ru.mipt.bit.platformer.driver.observation.Observable;
import ru.mipt.bit.platformer.driver.observation.Observer;
import ru.mipt.bit.platformer.objects.gameObjects.GameObject;

import java.util.Date;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
/**
 * Use case
 */
public class ControlByKey implements Controller, Observable {
    private final Control control;
    Command command;
    private final GameDriver gameDriver;
    private long lastTimeChanged = new Date().getTime();

    public ControlByKey(Command moveUpCommand,
                        Command moveDownCommand,
                        Command moveLeftCommand,
                        Command moveRightCommand,
                        Command shootCommand,
                        Command noMoveCommand,
                        GameDriver gameDriver) {
        control = new Control(moveUpCommand, moveDownCommand, moveLeftCommand, moveRightCommand, shootCommand, noMoveCommand);
        this.gameDriver = gameDriver;
    }

    @Override
    public void generateCommand(){
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            command = control.getMoveUpCommand();
            return;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            command = control.getMoveLeftCommand();
            return;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            command = control.getMoveDownCommand();
            return;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            command = control.getMoveRightCommand();
            return;
        }
        if (Gdx.input.isKeyPressed(SPACE)){
            command = control.getShootCommand();
            return;
        }
        if (Gdx.input.isKeyPressed(L)) {
            long time = new Date().getTime();
            if (time - lastTimeChanged > 300) {
                notifyObservers(Event.OnOffHealth, null);
                lastTimeChanged = time;
            }
            return;
        }
        command = control.getNoMoveCommand();
    }

    @Override
    public void executeCommand() {
        command.execute();
    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers(Event event, GameObject object) {
        if (event == Event.OnOffHealth) {
            gameDriver.update(event, null, 0);
        }
    }
}
