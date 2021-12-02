package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.control.*;
import ru.mipt.bit.platformer.generators.FileGenerator;
import ru.mipt.bit.platformer.generators.Level;
import ru.mipt.bit.platformer.generators.newLevelGenerator;
import ru.mipt.bit.platformer.graphics.LevelRenderer;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameDesktopLauncher implements ApplicationListener {

    private LevelRenderer levelRenderer;

    private List<Tank> tanks;
    private List<Tree> trees;

    private int height;
    private int width;

    private List<Controller> controllers;

    @Override
    public void create() {

        newLevelGenerator levelGenerator = new FileGenerator();
//        levelGenerator.generateLevelFromFile("src/main/resources/startingSettings/level.txt");
        Level level = levelGenerator.getLevel();

        width = level.getWidth();
        height = level.getHeight();
 //       levelGenerator.generateRandomCoordinates(5);

        tanks = level.getTanks();
        controllers = new ArrayList<>();
        for (Tank tank : tanks) {
//            tanks.add(new Tank(levelGenerator.getTankCoordinates().get(i)));
            controllers.add(new Controller(new MoveUpCommand(tank),
                            new MoveDownCommand(tank),
                            new MoveLeftCommand(tank),
                            new MoveRightCommand(tank)));
        }

        trees = level.getTrees();

        levelRenderer = new LevelRenderer(trees);
    }

    @Override
    public void render() {
        // clear the screen
        Drawer.clearScreen();

        moveTanks();

        levelRenderer.render(tanks);
    }

    private void moveTanks() {
 //       tank.move(trees, width, height);

        for (int i = 0; i < tanks.size(); i++) {
            int c = (int) (Math.random() * 4);
            switch (c) {
                case 0:
                    controllers.get(i).moveUp();
                    break;
                case 1:
                    controllers.get(i).moveDown();
                    break;
                case 2:
                    controllers.get(i).moveLeft();
                    break;
                case 3:
                    controllers.get(i).moveRight();
                    break;
            }
            tanks.get(i).moveCommand(trees, width, height);
        }
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
        levelRenderer.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
