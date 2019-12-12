package com.martincarney;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String[]> cellPhoneList = CSVReader.loadFile("CellPhone.csv");
        System.out.println(cellPhoneList.size()); // did it load?
        List<String[]> cellPhoneUsage = CSVReader.loadFile("CellPhoneUsageByMonth.csv");
        System.out.println(cellPhoneUsage.size());
    }
    
}
