package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class LevelRenderer {

    private final Batch batch;

    private final LevelLayer levelLayer;

    private final List<TankTexture> tankTextures;
    private final List<TreeTexture> treeTextures;

    public LevelRenderer(List<Tree> trees){
        batch = new SpriteBatch();
        levelLayer = new LevelLayer(new TmxMapLoader().load("level.tmx"), batch);

        tankTextures = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++){
            tankTextures.add(new TankTexture(new Texture("images/tank_blue.png")));
        }

        treeTextures = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++){
            treeTextures.add(new TreeTexture(new Texture("images/greenTree.png")));
        }

        levelLayer.placeObstacles(trees, treeTextures);
    }

    public void render(List<Tank> tanks){
        for (int i = 0; i < tanks.size(); i++) {
            levelLayer.updatePlayerPlacement(tanks.get(i), tankTextures.get(i));
        }
        levelLayer.render();

        Drawer.draw(batch, tanks, tankTextures, treeTextures);

    }

    public void dispose(){
        for (TreeTexture treeTexture : treeTextures) {
            treeTexture.getTexture().dispose();
        }
        for (TankTexture tankTexture : tankTextures) {
            tankTexture.getBlueTank().dispose();
        }
        levelLayer.dispose();
        batch.dispose();
    }

}
