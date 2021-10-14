package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelGenerator {

    private List<GridPoint2> tankCoordinates;
    private List<GridPoint2> treeCoordinates;

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
            char[] strToChar;

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

    public List<GridPoint2> getTankCoordinates() {
        return tankCoordinates;
    }

    public List<GridPoint2> getTreeCoordinates() {
        return treeCoordinates;
    }
}
