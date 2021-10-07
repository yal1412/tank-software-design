package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Drawer {
    public static void clearScreen(){
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void drawTextures(Batch batch, Player player, Tree tree) {
        // render player
        drawTextureRegionUnscaled(batch, player.getTexture().getGraphics(), player.getTexture().getRectangle(), player.getRotation());

        // render tree obstacle
        drawTextureRegionUnscaled(batch, tree.getGraphics(), tree.getRectangle(), 0f);
    }
}
