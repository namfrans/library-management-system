package com.company;

import java.util.ArrayList;

public class Library {
    private int num_books_borrowed, num_new_books, num_of_librarians;
    private int num_books_returned, num_books_left, num_of_members;
    private int num_of_racks;

    public Library(){
        //Default
    }

    public Library(int num_books_borrowed, int num_new_books, int num_of_librarians, int num_books_returned, int num_books_left, int num_of_racks) {
        this.num_books_borrowed = num_books_borrowed;
        this.num_new_books = num_new_books;
        this.num_of_librarians = num_of_librarians;
        this.num_books_returned = num_books_returned;
        this.num_books_left = num_books_left;
        this.num_of_racks = num_of_racks;
    }

    public int getNum_books_borrowed() {
        return num_books_borrowed;
    }

    public int getNum_new_books() {
        return num_new_books;
    }


    public int getNum_of_librarians() {
        return num_of_librarians;
    }

    public int getNum_books_returned() {
        return num_books_returned;
    }

    public int getNum_books_left() {
        return num_books_left;
    }

    public int getNum_of_racks() {
        return num_of_racks;
    }


}
