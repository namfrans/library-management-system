package com.company;

public class Member extends User{
    private Date dateOfMembership;
    private int totalBooksCheckedOut;
    private Card card;

    public Member(){
        //Default
    }

    public Member(String name, String phoneNumber, String address, String password, Date dateOfMembership, int totalBooksCheckedOut, Card card) {
        super(name, phoneNumber, address, password);
        this.dateOfMembership = dateOfMembership;
        this.totalBooksCheckedOut = totalBooksCheckedOut;
        this.card = card;
    }

    public Date getDateOfMembership() {
        return dateOfMembership;
    }

    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    public Card getCard() {
        return card;
    }

}
