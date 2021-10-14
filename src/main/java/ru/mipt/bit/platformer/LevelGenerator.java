package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelGenerator {

    private final List<GridPoint2> tankCoordinates;
    private final List<GridPoint2> treeCoordinates;

    public LevelGenerator(){
        tankCoordinates = new ArrayList<>();
        treeCoordinates = new ArrayList<>();
    }

    public void generateLevelFromFile(String pathToFile){
        try {
            File file = new File(pathToFile);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            List<String> lines = new ArrayList<>();
            String oneLine = reader.readLine();

            while (oneLine != null) {
                lines.add(0, oneLine);
                oneLine = reader.readLine();
            }

            tankCoordinates.addAll(FileParser.calculateTankCoordinates(lines));
            treeCoordinates.addAll(FileParser.calculateTreeCoordinates(lines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateRandomCoordinates(int numberOfObstacles){
        tankCoordinates.add(generateCoordinateOnField());

        if (numberOfObstacles > 79){
            numberOfObstacles = 79;
        }

        GridPoint2 tmpTreeCoordinate;
        for (int i = 0; i < numberOfObstacles; i++){
            tmpTreeCoordinate = generateCoordinateOnField();
            while (tankCoordinates.contains(tmpTreeCoordinate) || treeCoordinates.contains(tmpTreeCoordinate)){
                tmpTreeCoordinate = generateCoordinateOnField();
            }
            treeCoordinates.add(tmpTreeCoordinate);
        }
    }

    private GridPoint2 generateCoordinateOnField(){
        return new GridPoint2((int) (Math.random() * 10), (int) (Math.random() * 8));
    }

    public List<GridPoint2> getTankCoordinates() {
        return tankCoordinates;
    }

    public List<GridPoint2> getTreeCoordinates() {
        return treeCoordinates;
    }
}
