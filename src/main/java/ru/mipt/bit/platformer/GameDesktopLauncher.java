package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.control.*;
import ru.mipt.bit.platformer.control.commands.MoveDownCommand;
import ru.mipt.bit.platformer.control.commands.MoveLeftCommand;
import ru.mipt.bit.platformer.control.commands.MoveRightCommand;
import ru.mipt.bit.platformer.control.commands.MoveUpCommand;
import ru.mipt.bit.platformer.objects.Level;
import ru.mipt.bit.platformer.generators.LevelGenerator;
import ru.mipt.bit.platformer.generators.RandomGenerator;
import ru.mipt.bit.platformer.graphics.Drawer;
import ru.mipt.bit.platformer.graphics.LevelRenderer;
import ru.mipt.bit.platformer.objects.Tank;

import java.util.ArrayList;
import java.util.List;

public class GameDesktopLauncher implements ApplicationListener {

    private LevelRenderer levelRenderer;

    private Level logicLevel;

//    private List<Tank> tanks;
//    private List<Tree> trees;
//
//    private int height;
//    private int width;

//    private List<Controller> controllers;

    @Override
    public void create() {
        LevelGenerator levelGenerator = new RandomGenerator();

        logicLevel = levelGenerator.getLevel();
        
        levelRenderer = new LevelRenderer(logicLevel.getTrees());
    }

    @Override
    public void render() {
        // clear the screen
        Drawer.clearScreen();

        logicLevel.moveTanks();

        levelRenderer.render(logicLevel.getTanks());
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
