package com.martincarney.model;

import java.time.LocalDate;
import java.util.*;

public class CellPhone {

    public int employeeId;
    public String employeeName;
    public LocalDate purchaseDate;
    public String model;
    public List<CellUsageRecord> usageRecords = new ArrayList<>();

    public CellPhone(String[] row, String[] mapping) {
        for (int i = 0; i < mapping.length && i < row.length; i++) {
            String colName = mapping[i];
            String value = row[i];
            switch (colName) {
                case "employeeId":
                    this.employeeId = Integer.parseInt(value); break;
                case "employeeName":
                    this.employeeName = value; break;
                case "purchaseDate":
                    int year = Integer.parseInt(value.substring(0,4));
                    int month = Integer.parseInt(value.substring(4,6));
                    int day = Integer.parseInt(value.substring(6));
                    this.purchaseDate = LocalDate.of(year, month, day);
                    break;
                case "model":
                    this.model = value; break;
                default:
                    System.err.println("Invalid column name: " + colName); break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.employeeId + ":" + this.employeeName + "\t" + this.model + "\t" + this.purchaseDate.toString());
        for (int i = 0; i < usageRecords.size(); i++) {
            CellUsageRecord rec = usageRecords.get(i);
            sb.append("\r\n\t" + rec.toString());
        }
        return sb.toString();
    }
}
