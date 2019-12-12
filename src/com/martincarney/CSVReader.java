package com.martincarney;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class CSVReader {

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
