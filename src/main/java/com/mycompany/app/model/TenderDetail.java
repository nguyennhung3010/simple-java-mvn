package com.mycompany.app.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TenderDetail {
    private String country;
    private String purchaser;
    private String tenderDetail;
    private String postingDate;
    private String bidDeadline;

    public TenderDetail(String country, String purchaser, String tenderDetail, String postingDate, String bidDeadline) {
        this.country = country;
        this.purchaser = purchaser;
        this.tenderDetail = tenderDetail;
        this.postingDate = postingDate;
        this.bidDeadline = bidDeadline;
    }

    public String toCsvRow() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                country, purchaser, tenderDetail, postingDate,  bidDeadline, timestamp);
    }

    public static String getCsvHeader() {
       /* return "\"Ref No\",\"Notice Type\",\"Tender Notice Number\",\"Contract Type\"" +
                ",\"Competition\",\"Category\",\"Financer\",\"Country\",\"Purchaser\"," +
                "\"Tender Details\",\"Posting Date\",\"Estimated Cost\",\"Bid Deadline\"";*/
        return "\"Country\",\"Purchaser\"," +
                "\"Tender Details\",\"Posting Date\",\"Bid Deadline\",\"Time stamp\"";
    }
}
