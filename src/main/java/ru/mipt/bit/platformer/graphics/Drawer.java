package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.graphics.textures.BulletGraphics;
import ru.mipt.bit.platformer.graphics.textures.TankGraphicsWithHealth;
import ru.mipt.bit.platformer.graphics.textures.TreeGraphics;
import ru.mipt.bit.platformer.objects.gameObjects.Bullet;
import ru.mipt.bit.platformer.objects.gameObjects.Tank;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
/**
 * Adapter
 */
public class Drawer {
    public static void clearScreen(){
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void draw(Batch batch, List<Tank> tanks, List<TankGraphicsWithHealth> tankGraphicsWithHealths, List<TreeGraphics> treesGraphics,
                            List<Bullet> bullets, List<BulletGraphics> bulletGraphics) {
        batch.begin();

        for (int i = 0; i < tanks.size(); i++) {
            drawTextureRegionUnscaled(batch, tankGraphicsWithHealths.get(i).getGraphics(),
                                      tankGraphicsWithHealths.get(i).getRectangle(),
                                      tanks.get(i).getRotation());
            if (tankGraphicsWithHealths.get(i).isHealthOn()) {
                tankGraphicsWithHealths.get(i).getHealthGraphics().setRegionWidth(tanks.get(i).getLife());
                drawTextureRegionUnscaled(batch,
                        tankGraphicsWithHealths.get(i).getHealthGraphics(),
                        tankGraphicsWithHealths.get(i).getHealthRectangle().setCenter(
                                tankGraphicsWithHealths.get(i).getHealthRectangle().getX() + 50f,
                                tankGraphicsWithHealths.get(i).getHealthRectangle().getY() - 50f),
                        180f);
            }
        }

        for (TreeGraphics treeGraphics : treesGraphics) {
            drawTextureRegionUnscaled(batch, treeGraphics.getGraphics(), treeGraphics.getRectangle(), 0f);
        }

        for (int i = 0; i < bullets.size(); i++) {
            drawTextureRegionUnscaled(batch, bulletGraphics.get(i).getGraphics(),
                    bulletGraphics.get(i).getRectangle(),
                    bullets.get(i).getRotation());
        }

        batch.end();
    }
}
