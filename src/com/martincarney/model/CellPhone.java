package com.martincarney.model;

import java.time.LocalDate;
import java.util.*;

/**
 * Model of an employee's cell phone.
 */
public class CellPhone {

    public int employeeId;
    public String employeeName;
    public LocalDate purchaseDate;
    public String model;
    public List<CellUsageRecord> usageRecords = new ArrayList<>();

    /**
     * @param row The row of data for this CellPhone
     * @param mapping The first row of the CSV, tells us which index corresponds to which piece of info
     */
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
                    // date string is formatted YYYYMMDD
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
