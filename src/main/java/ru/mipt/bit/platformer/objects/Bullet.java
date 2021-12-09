package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

public class Bullet {
    private final GridPoint2 coordinates;
    private final GridPoint2 destinationCoordinates;
    private float movementProgress;
    private Direction direction;

    public Bullet(GridPoint2 coordinates, Direction direction){
        this.coordinates = coordinates;
        this.direction = direction;
        movementProgress = 0f;
        destinationCoordinates = coordinates;
    }
}
