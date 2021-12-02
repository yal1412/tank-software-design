package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.graphics.TankTexture;
import ru.mipt.bit.platformer.graphics.TreeTexture;
import ru.mipt.bit.platformer.objects.Tank;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Drawer {
    public static void clearScreen(){
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void draw(Batch batch, List<Tank> tanks, List<TankTexture> tankTextures, List<TreeTexture> treeTextures) {
        batch.begin();
        // render player
        for (int i = 0; i < tanks.size(); i++) {
            drawTextureRegionUnscaled(batch, tankTextures.get(i).getGraphics(),
                                      tankTextures.get(i).getRectangle(),
                                      tanks.get(i).getRotation());
        }

        // render tree obstacle
        for (TreeTexture treeTexture : treeTextures) {
            drawTextureRegionUnscaled(batch, treeTexture.getGraphics(), treeTexture.getRectangle(), 0f);
        }

        batch.end();
    }
}
