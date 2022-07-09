package com.company;

public class Librarian extends User{
    private Card card;

    public Librarian(){
        //Default
    }

    public Librarian(String name, String phoneNumber, String address, String password, Card card) {
        super(name, phoneNumber, address, password);
        this.card = card;
    }
    public void removeMembers(){

    }
    public Card getCard() {
        return card;
    }

}
