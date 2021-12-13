package ru.mipt.bit.platformer.graphics.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class TankGraphics implements GraphicObject {
    private final Texture blueTank;
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    public TankGraphics(Texture tankTexture){
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
