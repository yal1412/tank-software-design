package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {

    @Test
    void makeMovement() {
    }

    @Test
    void tryMovement() {
    }

    @Test
    void notObstacleAhead() {
        Tank tank = new Tank(new Texture("src/main/resources/images/tank_blue.png"), new GridPoint2(1, 1));

    }
}