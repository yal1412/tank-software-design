package ru.mipt.bit.platformer.graphics.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class HealthDecorator implements GraphicObject{

    private final HealthGraphics health;
    private GraphicObject tankGraphics;
    private boolean healthOn = false;

    public HealthDecorator(GraphicObject tankGraphics, Texture healthTexture){
        this.tankGraphics = tankGraphics;
        this.health = new HealthGraphics(healthTexture);
    }

    public TextureRegion getGraphics() {
        return  ((TankGraphics) tankGraphics).getGraphics();
    }

    public Rectangle getRectangle() {
        return ((TankGraphics) tankGraphics).getRectangle();
    }

    public Texture getBlueTank() {
        return ((TankGraphics) tankGraphics).getBlueTank();
    }

    public Rectangle getHealthRectangle() {
        return health.getHealthRectangle();
    }

    public TextureRegion getHealthGraphics() {
        return health.getHealthGraphics();
    }

    public void changeHealthBar() {
        healthOn = !healthOn;
    }

    public boolean isHealthOn() {
        return healthOn;
    }
}
