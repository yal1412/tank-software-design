package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.driver.observation.Event;
import ru.mipt.bit.platformer.driver.observation.Observer;
import ru.mipt.bit.platformer.graphics.textures.*;
import ru.mipt.bit.platformer.objects.gameObjects.Bullet;
import ru.mipt.bit.platformer.objects.gameObjects.GameObject;
import ru.mipt.bit.platformer.objects.gameObjects.Tank;
import ru.mipt.bit.platformer.objects.gameObjects.Tree;

import java.util.ArrayList;
import java.util.List;
/**
 * Adapter
 */
public class LevelRenderer implements Observer {

    private final Batch batch;

    private final LevelLayer levelLayer;

    private final List<TankGraphicsWithHealth> tankGraphicsWithHealths;
    private final List<TreeGraphics> treeGraphics;
    private final List<BulletGraphics> bulletGraphics;

    public LevelRenderer(List<Tank> tanks, List<Tree> trees){
        batch = new SpriteBatch();
        levelLayer = new LevelLayer(new TmxMapLoader().load("level.tmx"), batch);

        tankGraphicsWithHealths = new ArrayList<>();
        for (int i = 0; i < tanks.size(); i++){
            tankGraphicsWithHealths.add(new TankGraphicsWithHealth(new TankGraphics(new Texture("images/tank_blue.png")),
                                             new Texture("images/health.png")));
        }

        treeGraphics = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++){
            treeGraphics.add(new TreeGraphics(new Texture("images/greenTree.png")));
        }

        bulletGraphics = new ArrayList<>();

        levelLayer.placeObstacles(trees, treeGraphics);
    }

    public void render(List<Tank> tanks, List<Bullet> bullets){
        for (int i = 0; i < tanks.size(); i++) {
            levelLayer.updateTanksPlacement(tanks.get(i), tankGraphicsWithHealths.get(i));
        }
        for (int i = 0; i < bullets.size(); i++) {
            levelLayer.updateBulletsPlacement(bullets.get(i), bulletGraphics.get(i));
        }
        levelLayer.render();

        Drawer.draw(batch, tanks, tankGraphicsWithHealths, treeGraphics, bullets, bulletGraphics);

    }

    public void dispose(){
        for (TreeGraphics treeGraphics : this.treeGraphics) {
            treeGraphics.getTexture().dispose();
        }
        for (TankGraphicsWithHealth tankGraphicsWithHealth : tankGraphicsWithHealths) {
            tankGraphicsWithHealth.getBlueTank().dispose();
        }
        for (BulletGraphics bulletGraphics : this.bulletGraphics) {
            bulletGraphics.getBulletTexture().dispose();
        }
        levelLayer.dispose();
        batch.dispose();
    }

    @Override
    public void update(Event event, GameObject gameObject, int id) {
        switch(event) {
            case AddBullet:
                bulletGraphics.add(new BulletGraphics(new Texture("images/bullet_square.png")));
                break;
            case RemoveBullet:
                bulletGraphics.remove(id);
                break;
            case RemoveTank:
                tankGraphicsWithHealths.remove(id);
                break;
            case OnOffHealth:
                for (TankGraphicsWithHealth tankGraphicsWithHealth : tankGraphicsWithHealths) {
                    tankGraphicsWithHealth.changeHealthBar();
                }
                break;
        }
    }
}
