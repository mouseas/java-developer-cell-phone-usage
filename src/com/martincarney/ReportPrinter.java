package com.martincarney;

import com.martincarney.model.*;
import java.awt.*;
import java.awt.print.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class ReportPrinter implements Printable {

    private static final double PAGE_MARGINS = 50;
    private static final double ROW_HEIGHT = 14;

    private List<CellPhone> phoneData;
    private LocalDate runDate;
    private int numPhones = 0;
    private int totalMinutes = 0;
    private double totalData = 0;
    private double avgMinutes = 0;
    private double avgData = 0;

    public ReportPrinter(Map<Integer, CellPhone> phoneData) {
        this.phoneData = new ArrayList<>();

        // calculate header data
        this.runDate = LocalDate.now();
        this.numPhones = phoneData.size();
        this.totalMinutes = 0;
        this.totalData = 0.0;
        for (CellPhone cell : phoneData.values()) {
            this.phoneData.add(cell);
            for (CellUsageRecord rec : cell.usageRecords) {
                this.totalMinutes += rec.totalMinutes;
                this.totalData += rec.totalData;
            }
        }
        this.avgMinutes = this.totalMinutes / (double)this.numPhones;
        this.avgData = this.totalData / this.numPhones;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        // TODO: determine number of pages based on number of phone users, taking the header on the first page into account.
        if (pageIndex > 1) {
            return NO_SUCH_PAGE;
        }

        float y = (float)pageFormat.getImageableY();
        Graphics2D g2d = (Graphics2D) graphics;

        g2d.translate(pageFormat.getImageableX() + PAGE_MARGINS, pageFormat.getImageableY() + PAGE_MARGINS);

        // print report header only on the first page.
        if (pageIndex == 0) {
            y = printHeader(g2d, pageFormat);
        }

        float detailStart = y;
        float detailEnd = (float)pageFormat.getImageableHeight();
        int numRowsThisPageCanFit = (int)Math.floor((detailEnd - detailStart) / ROW_HEIGHT);
        System.out.println("Page " + pageIndex + " can fit " + numRowsThisPageCanFit + " rows.");
        int startIndex = 0;
        // TODO: for pages after the first, calculate which index to start from for that page.

        // print detail column headers
        g2d.drawString("Emp ID", 0, y);
        float COL_NAME_LEFT = 50;
        g2d.drawString("Name", COL_NAME_LEFT, y);
        float COL_MODEL_LEFT = COL_NAME_LEFT + 110;
        g2d.drawString("Phone Model", COL_MODEL_LEFT, y);
        float COL_MIN_LEFT = COL_MODEL_LEFT + 110;
        g2d.drawString("Minutes", COL_MIN_LEFT, y);
        float COL_DATA_LEFT = COL_MIN_LEFT + 80;
        g2d.drawString("Data", COL_DATA_LEFT, y);
        y += ROW_HEIGHT;

        // print details
        for (CellPhone cell : this.phoneData) {
            g2d.drawString("" + cell.employeeId, 0, y);
            g2d.drawString(cell.employeeName, COL_NAME_LEFT, y);
            g2d.drawString(cell.model, COL_MODEL_LEFT, y);
            // TODO add up records, group by month.
            // TODO display 1 row per month, or 2 columns? Instructions say columns, but rows make more sense.
            g2d.drawString("TODO", COL_MIN_LEFT, y); // TODO minutes by month
            g2d.drawString("TODO", COL_DATA_LEFT, y); // TODO data by month

            y += ROW_HEIGHT;
        }

        return PAGE_EXISTS;
    }

    private float printHeader(Graphics2D g2d, PageFormat pf) {
        float y = 0;

        /*
         *	Report Run Date
         *	Number of Phones
         *	Total Minutes
         *	Total Data
         *	Average Minutes
         *	Average Data
         */

        // draw it out.
        g2d.drawString("Report Run Date:", 0, y);
        g2d.drawString(this.runDate.toString(), 100, y);
        y += ROW_HEIGHT;
        g2d.drawString("# Phones:", 0, y);
        g2d.drawString("" + this.numPhones, 100, y);
        y += ROW_HEIGHT;
        g2d.drawString("Total Minutes:", 0, y);
        g2d.drawString(this.totalMinutes + " min", 100, y);
        y += ROW_HEIGHT;
        g2d.drawString("Total Data:", 0, y);
        g2d.drawString(String.format("%.1f", this.totalData) + "GB", 100, y);
        y += ROW_HEIGHT;
        g2d.drawString("Average Minutes:", 0, y);
        g2d.drawString(String.format("%.1f", this.avgMinutes) + " min/user", 100, y);
        y += ROW_HEIGHT;
        g2d.drawString("Average Data:", 0, y);
        g2d.drawString(String.format("%.1f", this.avgData) + "GB/user", 100, y);
        y += ROW_HEIGHT * 2;

        return y;
    }
}
