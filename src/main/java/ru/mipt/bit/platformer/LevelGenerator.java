package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelGenerator {

    private final List<GridPoint2> tankCoordinates;
    private final List<GridPoint2> treeCoordinates;
    private int width;
    private int height;

    public LevelGenerator(){
        tankCoordinates = new ArrayList<>();
        treeCoordinates = new ArrayList<>();
        width = 0;
        height = 0;
    }

    private void getFieldSizeFromFile(String pathToFile){
        try {
            File file = new File(pathToFile);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            String[] sizes;

            if (line != null) {
                sizes = line.split(" ");
                width = Integer.parseInt(sizes[0]);
                height = Integer.parseInt(sizes[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateLevelFromFile(String pathToFile){
        try {
            getFieldSizeFromFile("src/main/resources/startingSettings/fieldSize.txt");
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

    public void generateRandomCoordinates(int numberOfTanks, int numberOfObstacles){
        getFieldSizeFromFile("src/main/resources/startingSettings/fieldSize.txt");

        if (numberOfTanks > width * height - 2){
            numberOfTanks = width * height - 2;
        }

        if (numberOfObstacles + numberOfTanks > width * height){
            numberOfObstacles = width * height - numberOfTanks;
        }
        tankCoordinates.add(generateCoordinateOnField());

        GridPoint2 tmpCoordinate;
        for (int i = 0; i < numberOfTanks; i++){
            tmpCoordinate = generateCoordinateOnField();
            while (tankCoordinates.contains(tmpCoordinate)){
                tmpCoordinate = generateCoordinateOnField();
            }
            tankCoordinates.add(tmpCoordinate);
        }

        for (int i = 0; i < numberOfObstacles; i++){
            tmpCoordinate = generateCoordinateOnField();
            while (tankCoordinates.contains(tmpCoordinate) || treeCoordinates.contains(tmpCoordinate)){
                tmpCoordinate = generateCoordinateOnField();
            }
            treeCoordinates.add(tmpCoordinate);
        }
    }

    private GridPoint2 generateCoordinateOnField(){
        return new GridPoint2((int) (Math.random() * width), (int) (Math.random() * height));
    }

    public List<GridPoint2> getTankCoordinates() {
        return tankCoordinates;
    }

    public List<GridPoint2> getTreeCoordinates() {
        return treeCoordinates;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
