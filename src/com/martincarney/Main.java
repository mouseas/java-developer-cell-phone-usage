package com.martincarney;

import com.martincarney.model.*;

import java.awt.print.PrinterJob;
import java.util.*;

public class Main {

    /**
     * @throws Exception FileNotFound or IOException from loading CSV; PrinterException from printing
     */
    public static void main(String[] args) throws Exception {

        Map<Integer, CellPhone> cellPhoneList = loadCellUsageData();

        ReportPrinter repPrint = new ReportPrinter(cellPhoneList);

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(repPrint);

        // get user input; result is whether to print or not.
        boolean doPrint = job.printDialog();

        if (doPrint) {
            job.print();
        }
    }

    /**
     * @return Map of employeeId to their CellPhone record, including usage records for each phone.
     * @throws Exception from CSVReader; FileNotFoundException or IOException
     */
    private static Map<Integer, CellPhone> loadCellUsageData() throws Exception {
        // load cell phones
        List<String[]> cellPhoneData = CSVReader.loadFile("CellPhone.csv");
        String[] mapping = cellPhoneData.get(0);
        Map<Integer, CellPhone> cellPhoneList  = new HashMap<>();
        for (int i = 1; i < cellPhoneData.size(); i++) {
            CellPhone cell = new CellPhone(cellPhoneData.get(i), mapping);
            cellPhoneList.put(cell.employeeId, cell);
        }

        // load usage records
        List<String[]> cellPhoneUsageData = CSVReader.loadFile("CellPhoneUsageByMonth.csv");
        mapping = cellPhoneUsageData.get(0);
        for (int i = 1; i < cellPhoneUsageData.size(); i++) {
            CellUsageRecord rec = new CellUsageRecord(cellPhoneUsageData.get(i), mapping);
            CellPhone cell = null;
            if (cellPhoneList.containsKey(rec.employeeId)) {
                cell = cellPhoneList.get(rec.employeeId);
                cell.usageRecords.add(rec);
            } else {
                System.err.print("Lost Usage Record: " + rec.toString());
            }
        }

        return cellPhoneList;
    }
    
}
