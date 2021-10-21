package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Control;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank {

    private final TankTexture texture;
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private final GridPoint2 coordinates;
    // which tile the player want to go next
    private final GridPoint2 destinationCoordinates;
    private float movementProgress;
    private float rotation;

    private Movement nextMove;

    public Tank(Texture tankTexture, GridPoint2 destinationCoordinates) {
        texture = new TankTexture(tankTexture);
        this.destinationCoordinates = destinationCoordinates;
        coordinates = new GridPoint2(destinationCoordinates);
        rotation = 0f;
        movementProgress = 1f;
        nextMove = new Movement();
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
        if (nextMove.isNull())
            return destinationCoordinates;

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

    public boolean noObstacleAhead(List<Tree> trees) {
        GridPoint2 possibleCoordinates = tryMovement();
        for (Tree tree : trees) {
            if (tree.getCoordinates().equals(possibleCoordinates)){
                return false;
            }
        }
        return true;
    }

    private boolean noTanksAhead(List<Tank> tanks) {
        GridPoint2 thisPossibleCoordinates = tryMovement();
//        System.out.println("----------------------------------------");
//        System.out.println("thisCurrentCoordinates: " + this.getCoordinates().x + " " + this.getCoordinates().y);
//        System.out.println("thisPossibleCoordinates: " + thisPossibleCoordinates.x + " " + thisPossibleCoordinates.y);
        GridPoint2 tankPossibleCoordinates;
        for (Tank tank : tanks) {

//            System.out.println("tankCurrentCoordinates: " + tank.getCoordinates().x + " " + tank.getCoordinates().y);
//            System.out.println("tankPossibleCoordinates: " + tankPossibleCoordinates.x + " " + tankPossibleCoordinates.y);
            if (this.equals(tank)) {
                continue;
            }
            tankPossibleCoordinates = tank.tryMovement();
            if (tank.getCoordinates().equals(thisPossibleCoordinates) ||
                    tankPossibleCoordinates.equals(thisPossibleCoordinates)){
                return false;
            }
        }
        return true;
    }

    public void updateCoordinates(){
        if (hasFinishedMovement()) {
            // record that the player has reached his/her destination
            coordinates.set(destinationCoordinates);
        }
    }

    public void move(List<Tree> trees, List<Tank> tanks, float movementSpeed, int width, int hight) {
        if (!nextMove.isNull() && hasFinishedMovement()) {
            makeRotation();
            // if there is no tree ahead
            if (noWallAhead(width, hight) && noObstacleAhead(trees) && noTanksAhead(tanks)){
                makeMovement();
                finishMovement();
            }
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        updateMovementProgress(deltaTime, movementSpeed);
    }

    private boolean noWallAhead(int width, int hight) {
        GridPoint2 possibleCoordinates = tryMovement();
        return possibleCoordinates.x >= 0 && possibleCoordinates.x < width &&
                possibleCoordinates.y >= 0 && possibleCoordinates.y < hight;
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

    public TankTexture getTexture() {
        return texture;
    }

    public Movement getNextMove() {
        return nextMove;
    }

    public void dispose() {
        texture.getBlueTank().dispose();
    }

    public void setNextMove(Movement nextMove) {
        this.nextMove = nextMove;
    }

    public void updateNextMove(boolean isPlayer){
        if (isPlayer){
            updateNextMovePlayer();
        }
        else {
            updateNextMoveRandomly();
        }
    }

    private void updateNextMovePlayer() {
        nextMove = Control.determineDirectionByKey(Gdx.input);
    }

    private void updateNextMoveRandomly() {
        nextMove = Control.determineDirectionRandomly(Gdx.input);
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }
}

