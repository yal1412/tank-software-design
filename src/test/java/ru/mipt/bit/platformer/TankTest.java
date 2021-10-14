package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Tank;

class TankTest {

    @org.junit.jupiter.api.Test
    void makeMovement() {
    }

    @org.junit.jupiter.api.Test
    void tryMovement() {
    }

    @org.junit.jupiter.api.Test
    void notObstacleAhead() {
        Tank tank = new Tank(new Texture("images/tank_blue.png"), new GridPoint2(1, 1));
//        player.nextMove = new Movement(Direction.UP.vector, Direction.UP.rotation);

//        assertFalse(player.notObstacleAhead(new GridPoint2(1, 2)));
//        assertTrue(player.notObstacleAhead(new GridPoint2(1, 3)));

//        player.nextMove = new Movement(Direction.UP.vector, Direction.UP.rotation);
    }
}