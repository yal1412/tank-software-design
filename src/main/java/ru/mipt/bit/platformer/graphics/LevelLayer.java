package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.graphics.TankTexture;
import ru.mipt.bit.platformer.graphics.TreeTexture;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelLayer {
    public TiledMap level;
    public MapRenderer levelRenderer;
    public TileMovement tileMovement;
    public TiledMapTileLayer groundLayer;

    public LevelLayer(TiledMap load, Batch batch){
        level = load;
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void updatePlayerPlacement(Tank tank, TankTexture tankTexture) {
        tileMovement.moveRectangleBetweenTileCenters(tankTexture.getRectangle(),
                                                     tank.getCoordinates(),
                                                     tank.getDestinationCoordinates(),
                                                     tank.getMovementProgress());
    }

    public void placeObstacles(List<Tree> trees, List<TreeTexture> treeTextures) {
        for (int i = 0; i < trees.size(); i++) {
            moveRectangleAtTileCenter(groundLayer, treeTextures.get(i).getRectangle(), trees.get(i).getCoordinates());
        }

    }

    public void render(){
        levelRenderer.render();
    }

    public void dispose(){
        level.dispose();
    }

}
