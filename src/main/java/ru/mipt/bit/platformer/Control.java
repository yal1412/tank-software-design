package ru.mipt.bit.platformer;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Direction;
import ru.mipt.bit.platformer.objects.Movement;

import java.util.ArrayList;
import java.util.Arrays;

import static com.badlogic.gdx.Input.Keys.*;
//{UP, DOWN, LEFT, RIGHT, W, A, S, D}

public class Control {

    public static Movement determineDirectionByKey(Input inputKey){
        if (inputKey.isKeyPressed(UP) || inputKey.isKeyPressed(W)) {
            return new Movement(new GridPoint2(Direction.UP.vector), Direction.UP.rotation);
        }
        if (inputKey.isKeyPressed(LEFT) || inputKey.isKeyPressed(A)) {
            return new Movement(new GridPoint2(Direction.LEFT.vector), Direction.LEFT.rotation);
        }
        if (inputKey.isKeyPressed(DOWN) || inputKey.isKeyPressed(S)) {
            return new Movement(new GridPoint2(Direction.DOWN.vector), Direction.DOWN.rotation);
        }
        if (inputKey.isKeyPressed(RIGHT) || inputKey.isKeyPressed(D)) {
            return new Movement(new GridPoint2(Direction.RIGHT.vector), Direction.RIGHT.rotation);
        }
        return new Movement();
    }

    public static Movement determineDirectionRandomly(Input inputKey){
        if (isMovementKey(inputKey)) {
            int random = (int) (Math.random() * 2);
            switch (random){
                case 0:
                    return new Movement(new GridPoint2(Direction.UP.vector), Direction.UP.rotation);
                case 1:
                    return new Movement(new GridPoint2(Direction.LEFT.vector), Direction.LEFT.rotation);
                case 2:
                    return new Movement(new GridPoint2(Direction.DOWN.vector), Direction.DOWN.rotation);
                case 3:
                    return new Movement(new GridPoint2(Direction.RIGHT.vector), Direction.RIGHT.rotation);
            }
        }
        return new Movement();
    }

    private static boolean isMovementKey(Input inputKey) {
        return inputKey.isKeyPressed(UP) ||
                inputKey.isKeyPressed(LEFT) ||
                inputKey.isKeyPressed(RIGHT) ||
                inputKey.isKeyPressed(DOWN) ||
                inputKey.isKeyPressed(W) ||
                inputKey.isKeyPressed(A) ||
                inputKey.isKeyPressed(S) ||
                inputKey.isKeyPressed(D);
    }

}
