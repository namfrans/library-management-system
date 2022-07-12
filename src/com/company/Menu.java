package com.company;

import java.util.Scanner;

public class Menu {
    final Scanner input1;
    final Scanner input2;
    final Scanner input3;
    final Scanner input4;
    final Scanner input5;

    public Menu() {
        this.input1 = new Scanner(System.in);
        this.input2 = new Scanner(System.in);
        this.input3 = new Scanner(System.in);
        this.input4 = new Scanner(System.in);
        this.input5 = new Scanner(System.in);
    }

    public void printMainMenu() {
        System.out.print("===================Main Menu=================\n");
        System.out.println("1. Add a library.");
        System.out.println("2. Add rack.");
        System.out.println("3. Register librarian.");
        System.out.println("4. Register member.");
        System.out.println("5. Administrator privileges.");
        System.out.println("6. Borrow a book.");
        System.out.println("7. Return a book.");
        System.out.println("8. Exit.");
    }

    public void printAdminMenu() {
        System.out.print("===================Admin Menu=================\n");
        System.out.println("1. Add a book.");
        System.out.println("2. Remove a book.");
        System.out.println("3. Show books.");
        System.out.println("4. Search a book.");
        System.out.println("5. Back.");
    }
    public Library addLibrary() {
        System.out.print("\n==================================\n");
        System.out.print("Number of books borrowed: ");
        int booksBorrowed = this.input1.nextInt();
        System.out.print("Number of new books: ");
        int booksNew = this.input1.nextInt();
        System.out.print("Number of librarians: ");
        int numLibrarians = this.input1.nextInt();
        System.out.print("Number of books returned: ");
        int booksReturned = this.input1.nextInt();
        System.out.print("Number of books left: ");
        int booksLeft = this.input1.nextInt();
        System.out.print("Number of books racks: ");
        int numRacks = this.input1.nextInt();

        return new Library( booksBorrowed, booksNew, numLibrarians, booksReturned, booksLeft, numRacks );
    }

    public Rack addRack() {
        System.out.print("\n==================================\n");
        System.out.print("Library Id: ");
        int libId = this.input2.nextInt();
        System.out.print("Total books: ");
        int t_num_books = this.input2.nextInt();
        System.out.print("Location identity: ");
        String location = this.input2.nextLine();

        System.out.println("======Date created========");
        System.out.print("");
        System.out.print("Day: ");
        String day = this.input2.next();
        System.out.print("Month: ");
        String month = this.input2.next();
        System.out.print("Year: ");
        String year = this.input2.next();

        return new Rack( libId, t_num_books, location, new Date( year, month, day ) );
    }

    public Book addBook(){
        System.out.print("\n==================================\n");
        System.out.print("Rack number: ");
        int rack_no = this.input3.nextInt();
        System.out.print("Title: ");
        String title = this.input3.nextLine();
        System.out.print("Subject: ");
        String subject = input3.nextLine();

        System.out.println("\n======Date Published========");
        System.out.print("Day: ");
        String p_day = this.input3.next();
        System.out.print("Month: ");
        String p_month = this.input3.next();
        System.out.print("Year: ");
        String p_year = this.input3.next();

        System.out.println("\n======Date Purchased========");
        System.out.print("Day: ");
        String pur_day = this.input3.next();
        System.out.print("Month: ");
        String pur_month = this.input3.next();
        System.out.print("Year: ");
        String pur_year = this.input3.next();

        System.out.print("Checked out? (True/False): ");
        boolean checked = this.input3.nextBoolean();

        return new Book( rack_no, title, subject, new Date( p_year, p_month, p_day ), new Date( pur_year, pur_month, pur_day ), checked );
    }

    public Member registerMember() {
        System.out.print("\n==================================\n");
        System.out.print("Name: ");
        String name = this.input4.nextLine();
        System.out.print("Phone number: ");
        String phone_number = this.input4.next();
        System.out.print("Address: ");
        String address = this.input4.nextLine();
        System.out.print("Password: ");
        String password = this.input4.next();

        System.out.println("======Date Of Membership========");
        System.out.print("Day: ");
        String m_day = this.input4.next();
        System.out.print("Month: ");
        String m_month = this.input4.next();
        System.out.print("Year: ");
        String m_year = this.input4.next();
        System.out.print("Total books checked out: ");
        int t_books_checked = this.input4.nextInt();

        System.out.println("=========Give card details below==========");

        System.out.println("======Date Of Issue========");
        System.out.print("Day: ");
        String i_day = this.input4.next();
        System.out.print("Month: ");
        String i_month = this.input4.next();
        System.out.print("Year: ");
        String i_year = this.input4.next();

        System.out.println("Active (True/False): ");
        boolean active = input4.nextBoolean();

        return new Member( name, phone_number, address, password, new Date( m_year, m_month, m_day),t_books_checked, new Card( new Date( i_year,i_month,i_day ), active ) );
    }

    public Librarian registerLibrarian() {
        System.out.print("\n==================================\n");
        System.out.print("Name: ");
        String name = this.input5.nextLine();
        System.out.print("Phone number: ");
        String phone_number = this.input5.next();
        System.out.print("Address: ");
        String address = this.input5.nextLine();
        System.out.print("Password: ");
        String password = this.input5.next();

        System.out.println("=========Give card details below==========");

        System.out.println("========Date Of Issue========");
        System.out.print("Day: ");
        String issue_day = this.input5.next();
        System.out.print("Month: ");
        String issue_month = this.input5.next();
        System.out.print("Year: ");
        String issue_year = this.input5.next();

        System.out.println("Active (True/False): ");
        boolean active = input5.nextBoolean();

        return new Librarian( name, phone_number, address, password, new Card( new Date( issue_year, issue_month, issue_day), active ) );
    }

}
