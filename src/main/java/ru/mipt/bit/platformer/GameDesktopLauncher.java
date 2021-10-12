package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.*;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private LevelLayer levelLayer;
    private Player player;
    private Tree tree;

    @Override
    public void create() {
        batch = new SpriteBatch();

        levelLayer = new LevelLayer(new TmxMapLoader().load("level.tmx"), batch);

        player = new Player(new Texture("images/tank_blue.png"), new GridPoint2(1, 1));

        tree = new Tree(new Texture("images/greenTree.png"), new GridPoint2(1, 3));

        levelLayer.placeObstacles(tree);
    }

    @Override
    public void render() {
        // clear the screen
        Drawer.clearScreen();

        player.move(Gdx.input, tree.getCoordinates(), MOVEMENT_SPEED);
        // calculate interpolated player screen coordinates
        levelLayer.updatePlayerPlacement(player);
        // render each tile of the level
        levelLayer.render();

        Drawer.draw(batch, player, tree);
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
        tree.dispose();
        player.dispose();
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
