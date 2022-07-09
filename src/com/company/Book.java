package com.company;

public class Book {
    private String title, subject, barcode;
    private int rack_no;
    private Date date_published, date_purchased;
    private boolean checked_out;


    public Book(){
        //Default
    }

    public Book(int rack_no, String title, String subject, Date date_published, Date date_purchased, boolean checked_out) {
        this.rack_no = rack_no;
        this.title = title;
        this.subject = subject;
        this.barcode = "22112";
        this.date_published = date_published;
        this.date_purchased = date_purchased;
        this.checked_out = checked_out;
    }

    public void updateStatus(){

    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getRack_no() {
        return rack_no;
    }

    public Date getDate_published() {
        return date_published;
    }

    public Date getDate_purchased() {
        return date_purchased;
    }

    public boolean isChecked_out() {
        return checked_out;
    }

}
