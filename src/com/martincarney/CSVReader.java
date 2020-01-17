package com.martincarney;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * Simple reader for loading a .csv file and breaking it into a list of arrays, with the first array being the header row.
 */
public class CSVReader {

    /**
     * @param filePath Self-explanatory
     * @return List of String arrays. Index 0 in the list is the header row, each subsequent array is a row of data.
     * @throws Exception File not found, or IO. Typically due to bad file path or locking by another process.
     */
    public static List<String[]> loadFile(@NotNull String filePath) throws Exception {
        BufferedReader br = null;
        String line = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            List<String[]> result = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line != "") { // ignore any blank lines
                    result.add(line.split(","));
                }
            }

            return result;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

}
