package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.objects.Player;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Drawer {
    public static void clearScreen(){
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void draw(Batch batch, Player player, List<Tree> trees) {
        batch.begin();
        // render player
        drawTextureRegionUnscaled(batch, player.getTexture().getGraphics(), player.getTexture().getRectangle(), player.getRotation());

        // render tree obstacle
        for (Tree tree : trees) {
            drawTextureRegionUnscaled(batch, tree.getGraphics(), tree.getRectangle(), 0f);
        }

        batch.end();
    }
}
