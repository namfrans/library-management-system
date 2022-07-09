package com.company;

public class Date {
    private final String day;
    private final String month;
    private final String year;

    public Date(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return year+ "-" + month + "-" + day ;
    }
}
