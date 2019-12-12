package com.martincarney;

import com.martincarney.model.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // load cell phones
        List<String[]> cellPhoneData = CSVReader.loadFile("CellPhone.csv");
        String[] mapping = cellPhoneData.get(0);
        List<CellPhone> cellPhoneList  = new ArrayList<>();
        System.out.println("---------------- Cell Phones ------------------");
        for (int i = 1; i < cellPhoneData.size(); i++) {
            cellPhoneList.add(new CellPhone(cellPhoneData.get(i), mapping));
            System.out.println(cellPhoneList.get(cellPhoneList.size() - 1).toString());
        }

        // load usage records
        List<String[]> cellPhoneUsageData = CSVReader.loadFile("CellPhoneUsageByMonth.csv");
        mapping = cellPhoneUsageData.get(0);
        List<CellUsageRecord> cellPhoneUsageList = new ArrayList<>();
        System.out.println("---------------- Usage Records ----------------");
        for (int i = 1; i < cellPhoneUsageData.size(); i++) {
            cellPhoneUsageList.add(new CellUsageRecord(cellPhoneUsageData.get(i), mapping));
            System.out.println(cellPhoneUsageList.get(cellPhoneUsageList.size() - 1).toString());
        }
    }
    
}
