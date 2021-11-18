package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.Graphics.TankTexture;
import ru.mipt.bit.platformer.Graphics.TreeTexture;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class GameDesktopLauncher implements ApplicationListener {

    private Batch batch;

    private LevelLayer levelLayer;
    private Tank tank;
    private List<Tree> trees;

    private TankTexture tankTexture;
    private List<TreeTexture> treeTextures;

    private static int height;
    private static int width;

    @Override
    public void create() {
        batch = new SpriteBatch();

        levelLayer = new LevelLayer(new TmxMapLoader().load("level.tmx"), batch);

        LevelGenerator levelGenerator = new LevelGenerator();
        levelGenerator.generateLevelFromFile("src/main/resources/startingSettings/level.txt");

        width = levelGenerator.getWidth();
        height = levelGenerator.getHeight();
 //       levelGenerator.generateRandomCoordinates(5);

        tank = new Tank(levelGenerator.getTankCoordinates().get(0));
        tankTexture = new TankTexture(new Texture("images/tank_blue.png"));

        trees = new ArrayList<>();
        treeTextures = new ArrayList<>();
        for (int i = 0; i < levelGenerator.getTreeCoordinates().size(); i++){
            trees.add(new Tree(levelGenerator.getTreeCoordinates().get(i)));
            treeTextures.add(new TreeTexture(new Texture("images/greenTree.png")));
        }

        levelLayer.placeObstacles(trees, treeTextures);
    }

    @Override
    public void render() {
        // clear the screen
        Drawer.clearScreen();

        tank.move(trees, width, height);
        // calculate interpolated player screen coordinates
        levelLayer.updatePlayerPlacement(tank, tankTexture);
        // render each tile of the level
        levelLayer.render();

        Drawer.draw(batch, tank, tankTexture, treeTextures);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (TreeTexture treeTexture : treeTextures) {
            treeTexture.getTexture().dispose();
        }
        tankTexture.getBlueTank().dispose();
        levelLayer.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
