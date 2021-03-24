package com.codecool.chessopen;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        Map<String, Integer> competitorsWithSumPoints = new HashMap<>();
        try {
            File file = new File(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] cutLine = line.split(",");
                int sumPoints = 0;
                for (int i = 1; i < cutLine.length - 1; i++) {
                    sumPoints += Integer.parseInt(cutLine[i]);
                }
                competitorsWithSumPoints.put(cutLine[0], sumPoints);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortCompetitorsNameByPoints(competitorsWithSumPoints);
    }

    private List<String> sortCompetitorsNameByPoints(Map<String, Integer> competitorsWithSumPoints) {
        List<String> sortedCompetitorsName;
        sortedCompetitorsName = competitorsWithSumPoints
                .entrySet()
                .stream()
                .sorted((Map.Entry<String, Integer> entryOne, Map.Entry<String, Integer> entryTwo) -> {
                    return entryTwo.getValue().equals(entryOne.getValue()) ?
                            entryTwo.getKey().compareTo(entryOne.getKey())
                            : entryTwo.getValue().compareTo(entryOne.getValue());
                })
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        return sortedCompetitorsName;
    }

}
