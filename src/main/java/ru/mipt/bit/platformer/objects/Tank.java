package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.CollisionChecker;
import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.control.ControlByKey;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements GameObject {

    private static final float MOVEMENT_SPEED = 0.4f;

    private final GridPoint2 coordinates;
    private final GridPoint2 destinationCoordinates;

    private float movementProgress;
    private float rotation;

    private Movement nextMove;

    private final CollisionChecker collisionChecker;

    public Tank(GridPoint2 destinationCoordinates, CollisionChecker collisionChecker) {
        this.destinationCoordinates = destinationCoordinates;
        coordinates = new GridPoint2(destinationCoordinates);
        rotation = 0f;
        movementProgress = 1f;
        nextMove = new Movement();
        this.collisionChecker = collisionChecker;
    }

    public boolean hasFinishedMovement() {
        return isEqual(movementProgress, 1f);
    }

    public void makeRotation() {
        rotation = nextMove.rotation;
    }

    public void makeMovement() {
        destinationCoordinates.add(nextMove.directionVector);
    }

    public GridPoint2 tryMovement() {
        GridPoint2 newCoordinates = new GridPoint2();
        newCoordinates.x = destinationCoordinates.x + nextMove.directionVector.x;
        newCoordinates.y = destinationCoordinates.y + nextMove.directionVector.y;
        return newCoordinates;
    }

    public void finishMovement() {
        nextMove.directionVector.x = 0;
        nextMove.directionVector.y = 0;
        movementProgress = 0f;
    }

    public boolean notObstacleAhead(List<Tree> trees) {
        GridPoint2 possibleCoordinates = tryMovement();
        for (Tree tree : trees) {
            if (tree.getCoordinates().equals(possibleCoordinates)){
                return false;
            }
        }
        return true;
    }

    public boolean isMovementPossible(GridPoint2 obstacleCoordinates, GridPoint2 newPosition) {
        return !obstacleCoordinates.equals(newPosition);
    }

    private boolean noWallAhead(int width, int height) {
        GridPoint2 possibleCoordinates = tryMovement();
        return possibleCoordinates.x >= 0 && possibleCoordinates.x < width &&
                possibleCoordinates.y >= 0 && possibleCoordinates.y < height;
    }

    public void moveCommand(List<Tree> trees, int width, int height) {
        if (!nextMove.isNull() && hasFinishedMovement()) {
            makeRotation();
            // if there is no tree ahead
            if (noCollisions()){
                makeMovement();
                finishMovement();
            }
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        updateMovementProgress(deltaTime, MOVEMENT_SPEED);
        if (hasFinishedMovement()) {
            // record that the player has reached his/her destination
            coordinates.set(destinationCoordinates);
        }
    }

    public void updateMovementProgress(float deltaTime, float movementSpeed) {
        movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
    }

    public float getRotation() {
        return rotation;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Movement getNextMove() {
        return nextMove;
    }

    public void setNextMove(Movement nextMove) {
        this.nextMove = nextMove;
    }

    public void moveUp(){
        nextMove = new Movement(new GridPoint2(Direction.UP.vector), Direction.UP.rotation);
    }

    public void moveDown(){
        nextMove = new Movement(new GridPoint2(Direction.DOWN.vector), Direction.DOWN.rotation);
    }

    public void moveLeft(){
        nextMove = new Movement(new GridPoint2(Direction.LEFT.vector), Direction.LEFT.rotation);
    }

    public void moveRight(){
        nextMove = new Movement(new GridPoint2(Direction.RIGHT.vector), Direction.RIGHT.rotation);
    }

    public void shoot(){

    }

    public boolean noCollisions() {
        GridPoint2 newCoordinates = tryMovement();
        return collisionChecker.noCollisionsForTank(newCoordinates, this);
    }
}

