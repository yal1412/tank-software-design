package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

public class Tree implements GameObject {

    private final GridPoint2 coordinates;

    public Tree(GridPoint2 gridPoint2){
        coordinates = gridPoint2;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
}
