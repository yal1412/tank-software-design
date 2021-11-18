package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class TankTexture {
    private final Texture blueTank;
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    public TankTexture(Texture tankTexture){
        blueTank = tankTexture;
        this.graphics = new TextureRegion(blueTank);
        this.rectangle = createBoundingRectangle(this.graphics);
    }

    public TextureRegion getGraphics() {
        return graphics;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Texture getBlueTank() {
        return blueTank;
    }
}
