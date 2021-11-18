package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.Drawer;
import ru.mipt.bit.platformer.LevelLayer;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class LevelRenderer {

    private final Batch batch;

    private final LevelLayer levelLayer;

    private final TankTexture tankTexture;
    private final List<TreeTexture> treeTextures;

    public LevelRenderer(List<Tree> trees){
        batch = new SpriteBatch();
        levelLayer = new LevelLayer(new TmxMapLoader().load("level.tmx"), batch);

        tankTexture = new TankTexture(new Texture("images/tank_blue.png"));

        treeTextures = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++){
            treeTextures.add(new TreeTexture(new Texture("images/greenTree.png")));
        }

        levelLayer.placeObstacles(trees, treeTextures);
    }

    public void render(List<Tank> tanks){
        for (Tank tank : tanks) {
            levelLayer.updatePlayerPlacement(tank, tankTexture);
        }
        levelLayer.render();
        for (Tank tank : tanks) {
            Drawer.draw(batch, tank, tankTexture, treeTextures);
        }
    }

    public void dispose(){
        for (TreeTexture treeTexture : treeTextures) {
            treeTexture.getTexture().dispose();
        }
        tankTexture.getBlueTank().dispose();
        levelLayer.dispose();
        batch.dispose();
    }

}
