package com.company;

public class Card {
    private int cardNo;
    private Date dateOfIssue;
    private boolean active;
    private String barcode;

    public Card(){
        //Default
    }

    public Card(Date dateOfIssue, boolean active) {
        this.dateOfIssue = dateOfIssue;
        this.active = active;
        this.barcode = "11221";
    }

    public boolean isActive(){
        return active;
    }

    public int getCardNo() {
        return cardNo;
    }
    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

}
