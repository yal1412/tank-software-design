package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class Tree {

    private final GridPoint2 coordinates;

    public Tree(GridPoint2 gridPoint2){
        coordinates = gridPoint2;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
}
