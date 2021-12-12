package ru.mipt.bit.platformer.graphics.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Entity
 */
public class TankGraphicsWithHealth extends HealthDecorator {

    public TankGraphicsWithHealth(GraphicObject tankGraphics, Texture healthTexture){
        super(tankGraphics, healthTexture);
    }
}
