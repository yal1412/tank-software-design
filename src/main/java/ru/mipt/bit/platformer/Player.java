package ru.mipt.bit.platformer;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Player {
    public TextureRegion graphics;
    public Rectangle rectangle;
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    public GridPoint2 coordinates;
    // which tile the player want to go next
    public GridPoint2 destinationCoordinates;
    public float movementProgress = 1f;
    public float rotation;

    public GridPoint2 directionVector = new GridPoint2(0, 0);
    public float movementRotation;


    public boolean isMovementKeyPressed(Input inputKey) {
        if (inputKey.isKeyPressed(UP) || inputKey.isKeyPressed(W)) {
            directionVector = new GridPoint2(Direction.UP.vector);
            movementRotation = Direction.UP.rotation;
            return true;
        }
        if (inputKey.isKeyPressed(LEFT) || inputKey.isKeyPressed(A)) {
            directionVector = new GridPoint2(Direction.LEFT.vector);
            movementRotation = Direction.LEFT.rotation;
            return true;
        }
        if (inputKey.isKeyPressed(DOWN) || inputKey.isKeyPressed(S)) {
            directionVector = new GridPoint2(Direction.DOWN.vector);
            movementRotation = Direction.DOWN.rotation;
            return true;
        }
        if (inputKey.isKeyPressed(RIGHT) || inputKey.isKeyPressed(D)) {
            directionVector = new GridPoint2(Direction.RIGHT.vector);
            movementRotation = Direction.RIGHT.rotation;
            return true;
        }
        return false;
    }

    ;

    public boolean hasFinishedMovement() {
        return isEqual(movementProgress, 1f);
    }

    public void makeRotation() {
        rotation = movementRotation;
    }

    public void makeMovement() {
        destinationCoordinates.add(directionVector);
    }

    public GridPoint2 tryMovement() {
        GridPoint2 newCoordinates = new GridPoint2();
        newCoordinates.x = destinationCoordinates.x + directionVector.x;
        newCoordinates.y = destinationCoordinates.y + directionVector.y;
        return newCoordinates;
    }

    public void finishMovement() {
        directionVector.x = 0;
        directionVector.y = 0;
        movementProgress = 0f;
    }

    public boolean notObstacleAhead(GridPoint2 obstacleCoordinates) {
        GridPoint2 possibleCoordinates = tryMovement();
        return !obstacleCoordinates.equals(possibleCoordinates);
    }
}

