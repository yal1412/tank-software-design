package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.control.*;
import ru.mipt.bit.platformer.graphics.LevelRenderer;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

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

        LevelGenerator levelGenerator = new LevelGenerator();
        levelGenerator.generateLevelFromFile("src/main/resources/startingSettings/level.txt");

        width = levelGenerator.getWidth();
        height = levelGenerator.getHeight();
 //       levelGenerator.generateRandomCoordinates(5);

        tanks = new ArrayList<>();
        controllers = new ArrayList<>();
        for (int i = 0; i < levelGenerator.getTankCoordinates().size(); i++){
            tanks.add(new Tank(levelGenerator.getTankCoordinates().get(i)));
            controllers.add(new Controller(new MoveUpCommand(tanks.get(i)),
                            new MoveDownCommand(tanks.get(i)),
                            new MoveLeftCommand(tanks.get(i)),
                            new MoveRightCommand(tanks.get(i))));
        }

        trees = new ArrayList<>();
        for (int i = 0; i < levelGenerator.getTreeCoordinates().size(); i++){
            trees.add(new Tree(levelGenerator.getTreeCoordinates().get(i)));
        }

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
