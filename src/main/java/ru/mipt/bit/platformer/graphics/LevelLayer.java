package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.graphics.textures.BulletGraphics;
import ru.mipt.bit.platformer.graphics.textures.TankGraphicsWithHealth;
import ru.mipt.bit.platformer.graphics.textures.TreeGraphics;
import ru.mipt.bit.platformer.objects.gameObjects.Bullet;
import ru.mipt.bit.platformer.objects.gameObjects.Tank;
import ru.mipt.bit.platformer.objects.gameObjects.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
/**
 * Adapter
 */
public class LevelLayer {
    public TiledMap level;
    public MapRenderer levelRenderer;
    public TileMovement tileMovementTank;
    public TileMovement tileMovementBullet;
    public TiledMapTileLayer groundLayer;

    public LevelLayer(TiledMap load, Batch batch){
        level = load;
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovementTank = new TileMovement(groundLayer, Interpolation.smooth);
        tileMovementBullet = new TileMovement(groundLayer, Interpolation.linear);
    }

    public void updateTanksPlacement(Tank tank, TankGraphicsWithHealth tankGraphicsWithHealth) {
        tileMovementTank.moveRectangleBetweenTileCenters(tankGraphicsWithHealth.getRectangle(),
                                                     tank.getCoordinates(),
                                                     tank.getDestinationCoordinates(),
                                                     tank.getMovementProgress());

        tileMovementTank.moveRectangleBetweenTileCenters(tankGraphicsWithHealth.getHealthRectangle(),
                                                         tank.getCoordinates(),
                                                         tank.getDestinationCoordinates(),
                                                         tank.getMovementProgress());
    }

    public void updateBulletsPlacement(Bullet bullet, BulletGraphics bulletGraphics) {
        tileMovementBullet.moveRectangleBetweenTileCenters(bulletGraphics.getRectangle(),
                                                           bullet.getCoordinates(),
                                                           bullet.getDestinationCoordinates(),
                                                           bullet.getMovementProgress());
    }

    public void placeObstacles(List<Tree> trees, List<TreeGraphics> treeGraphics) {
        for (int i = 0; i < trees.size(); i++) {
            moveRectangleAtTileCenter(groundLayer, treeGraphics.get(i).getRectangle(), trees.get(i).getCoordinates());
        }

    }

    public void render(){
        levelRenderer.render();
    }

    public void dispose(){
        level.dispose();
    }

}
