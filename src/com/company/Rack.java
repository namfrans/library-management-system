package com.company;

import java.util.ArrayList;

public class Rack {
    private int total_books;
    private String locationIdentity;
    private Date dateCreated;
    private int libId;

    public void updateCatalogue(){

    }

    public Rack(){
        //Default
    }

    public Rack(int libId, int total_books, String locationIdentity, Date dateCreated) {
        this.libId = libId;
        this.total_books = total_books;
        this.locationIdentity = locationIdentity;
        this.dateCreated = dateCreated;
    }

    public int getLibId() {
        return libId;
    }


    public int getTotal_books() {
        return total_books;
    }

    public String getLocationIdentity() {
        return locationIdentity;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

}
