package ru.mipt.bit.platformer.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.control.commands.Command;
import ru.mipt.bit.platformer.objects.Direction;
import ru.mipt.bit.platformer.objects.Movement;

import java.util.Date;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class ControlByKey implements Manager{
    private final Control control;

    public ControlByKey(Command moveUpCommand,
                        Command moveDownCommand,
                        Command moveLeftCommand,
                        Command moveRightCommand,
                        Command shootCommand) {
        control = new Control(moveUpCommand, moveDownCommand, moveLeftCommand, moveRightCommand, shootCommand);
    }

    @Override
    public void executeCommand() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            control.moveUp();
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            control.moveLeft();
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            control.moveDown();
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            control.moveRight();
        }
        if (Gdx.input.isKeyPressed(SPACE)){
            control.shoot();
        }
    }
}
