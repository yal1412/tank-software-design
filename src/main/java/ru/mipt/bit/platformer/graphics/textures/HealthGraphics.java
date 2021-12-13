package ru.mipt.bit.platformer.graphics.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
/**
 * Entity
 */
public class HealthGraphics {
    private final TextureRegion healthGraphics;
    private final Rectangle healthRectangle;
    private final Texture healthTexture;

    public HealthGraphics(Texture healthTexture) {
        this.healthTexture = healthTexture;
        healthGraphics = new TextureRegion(healthTexture);
        healthRectangle = createBoundingRectangle(healthGraphics);
    }

    public Texture getHealthTexture() {
        return healthTexture;
    }

    public TextureRegion getHealthGraphics() {
        return healthGraphics;
    }

    public Rectangle getHealthRectangle() {
        return healthRectangle;
    }
}
