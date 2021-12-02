package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.math.GridPoint2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomGenerator implements newLevelGenerator{

    private final Level level;
    private int numberOfTanks;
    private int numberOfTrees;

    public RandomGenerator(){
        level = new Level();

        getFieldSizeFromFile();
        generateRandomObjects();
    }

    private void getFieldSizeFromFile(){
        try {
            File file = new File("src/main/resources/startingSettings/fieldSize.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            String[] sizes;

            if (line != null) {
                sizes = line.split(" ");
                level.setWidth(Integer.parseInt(sizes[0]));
                level.setHeight(Integer.parseInt(sizes[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getNumbersFromFile(){
        try {
            File file = new File("src/main/resources/startingSettings/level.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            String[] sizes;

            if (line != null) {
                sizes = line.split(" ");
                numberOfTanks = Integer.parseInt(sizes[0]);
                numberOfTrees = Integer.parseInt(sizes[1]);
            }

            if (numberOfTanks > level.getWidth() * level.getHeight() - 2){
                numberOfTanks = level.getWidth() * level.getHeight() - 2;
            }

            if (numberOfTrees + numberOfTanks > level.getWidth() * level.getHeight()){
                numberOfTrees = level.getWidth() * level.getHeight() - numberOfTanks;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GridPoint2 generateCoordinateOnField(){
        return new GridPoint2((int) (Math.random() * level.getWidth()), (int) (Math.random() * level.getHeight()));
    }

    private void generateRandomObjects() {
        getNumbersFromFile();

        List<GridPoint2> tankCoordinates = new ArrayList<>();
        List<GridPoint2> treeCoordinates = new ArrayList<>();

        tankCoordinates.add(generateCoordinateOnField());

        GridPoint2 tmpCoordinate;
        for (int i = 0; i < numberOfTanks; i++){
            tmpCoordinate = generateCoordinateOnField();
            while (tankCoordinates.contains(tmpCoordinate)){
                tmpCoordinate = generateCoordinateOnField();
            }
            tankCoordinates.add(tmpCoordinate);
        }

        for (int i = 0; i < numberOfTrees; i++){
            tmpCoordinate = generateCoordinateOnField();
            while (tankCoordinates.contains(tmpCoordinate) || treeCoordinates.contains(tmpCoordinate)){
                tmpCoordinate = generateCoordinateOnField();
            }
            treeCoordinates.add(tmpCoordinate);
        }

        level.createTanks(tankCoordinates);
        level.createTrees(treeCoordinates);
    }

    @Override
    public Level getLevel() {
        return level;
    }
}
