package com.martincarney.model;

import java.time.LocalDate;

public class CellUsageRecord {

    public int employeeId;
    public LocalDate date;
    public int totalMinutes;
    public double totalData;

    public CellUsageRecord(String[] row, String[] mapping) {
        for (int i = 0; i < row.length && i < mapping.length; i++) {
            String colName = mapping[i];
            String value = row[i];
            switch (colName) {
                case "emplyeeId": // sic - file has a typo.
                case "employeeId":
                    this.employeeId = Integer.parseInt(value); break;
                case "date":
                    int year = Integer.parseInt(value.substring(value.lastIndexOf("/") + 1));
                    int month = Integer.parseInt(value.substring(0, value.indexOf("/")));
                    int day = Integer.parseInt(value.substring(value.indexOf("/") + 1, value.lastIndexOf("/")));
                    this.date = LocalDate.of(year, month, day);
                    break;
                case "totalMinutes":
                    this.totalMinutes = Integer.parseInt(value); break;
                case "totalData":
                    this.totalData = Double.parseDouble(value); break;
                default:
                    System.err.println("Invalid column name: " + colName); break;
            }
        }
    }

    @Override
    public String toString() {
        return this.employeeId + "\t" + this.date.toString() + "\tMins: " + this.totalMinutes + " \tData: " + this.totalData + " GB";
    }

}
