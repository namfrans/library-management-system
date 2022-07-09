package com.company;

import java.sql.Connection;
import java.util.Scanner;

//Change variable names to a more understandable manner suiting their context.
public class Main {
    final Scanner input1;
    final Scanner input2;
    final Scanner input3;
    final Scanner input4;
    final Scanner input5;

    public Main() {
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
    private Library addLibrary() {
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

    private Rack addRack() {
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

    private Book addBook(){
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

    private Member registerMember() {
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

    private Librarian registerLibrarian() {
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

    public static void main(String[] args) {
        //Connection
        Database db = new Database();
        Connection con = db.connectToDatabase("LibraryManagementSysDB", "postgres", "<your_database_password>");

        /*1st
        db.createTableLibrary(con);
        2nd
        db.createTableCard(con);
        3rd
        db.createTableRack(con);
        4th
        db.createTableBook(con);
        5th
        db.createTableMember(con);
        6th
        db.createTableLibrarian(con);*/

        //Operations
        Main main = new Main();
        Scanner kbd = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        int option;
        int adminOption;
        boolean back = false;

        do {

            main.printMainMenu();
            option = kbd.nextInt();

            switch ( option ){
                case 1 -> {

                    Library newLib = main.addLibrary();
                    db.insertLibraryInfo( con, newLib.getNum_books_borrowed(), newLib.getNum_new_books(), newLib.getNum_of_librarians(), newLib.getNum_books_returned(),
                            newLib.getNum_books_left(), newLib.getNum_of_racks() );

                }
                case 2 -> {

                    Rack newRack = main.addRack();
                    db.insertRackInfo( con, newRack.getLibId(), newRack.getTotal_books(), newRack.getLocationIdentity(), newRack.getDateCreated().toString() );

                    //To update the number of racks in library
                    int num_racks = Integer.parseInt( db.getNumberOfRacksInLibrary( con, newRack.getLibId() ) );
                    db.updateNumOfRacksInLib( con, newRack.getLibId(), num_racks + 1 );

                }
                case 3 -> {

                    Librarian newLibrarian = main.registerLibrarian();

                    db.insertCardInfo( con, newLibrarian.getCard().getDateOfIssue().toString(), newLibrarian.getCard().isActive(), "empty" );

                    int num_librarian = Integer.parseInt( db.getNumberOfLibrarians( con, 1 ) );
                    db.updateNumberOfLibrarians( con,1,num_librarian + 1 );

                    //Select card number here
                    int cardId;
                    if ( db.data_inserted ){

                        cardId = Integer.parseInt( db.getCardId( con ) );

                        //Process the selected card number
                        if ( cardId != 0 ){

                            newLibrarian.getCard().setCardNo( cardId );
                            //Assign barcode
                            String fullBarcode = newLibrarian.getCard().getBarcode() + String.format( "%04d", cardId );
                            newLibrarian.getCard().setBarcode( fullBarcode );
                            //add barcode to card
                            db.updateCardBarcode( con, cardId, fullBarcode );
                            //Add data to db
                            db.insertLibrarianInfo( con, newLibrarian.getName(), newLibrarian.getPhoneNumber(),
                                    newLibrarian.getAddress(), newLibrarian.getPassword(),
                                    newLibrarian.getCard().getCardNo(), newLibrarian.getCard().getDateOfIssue().toString(),
                                    newLibrarian.getCard().isActive(), newLibrarian.getCard().getBarcode() );

                        }else {
                            System.out.println("No card ID was received");
                        }
                    }
                }
                case 4 -> {

                    Member newMember = main.registerMember();
                    db.insertCardInfo( con,newMember.getCard().getDateOfIssue().toString(),newMember.getCard().isActive(),newMember.getCard().getBarcode() );

                    //Select card number here
                    int cardId;

                    if ( db.data_inserted ){
                        cardId = Integer.parseInt( db.getCardId( con ) );

                        //Process the selected card number
                        if ( cardId != 0 ){
                            newMember.getCard().setCardNo( cardId );
                            //Assign barcode
                            String fullBarcode = newMember.getCard().getBarcode() + String.format( "%04d", cardId );
                            newMember.getCard().setBarcode( fullBarcode );
                            //add barcode to card
                            db.updateCardBarcode( con, cardId, fullBarcode );
                            //Add data to db
                            db.insertMemberInfo( con, newMember.getName(), newMember.getPhoneNumber(), newMember.getAddress(), newMember.getPassword(),
                                    newMember.getDateOfMembership().toString(), newMember.getTotalBooksCheckedOut(),
                                    newMember.getCard().getCardNo(), newMember.getCard().getDateOfIssue().toString(),
                                    newMember.getCard().isActive(), newMember.getCard().getBarcode() );
                        }else {
                            System.out.println("No card ID was received");
                        }
                    }
                }
                case 5 -> {
                    //Second menu for admin
                    do {

                        main.printAdminMenu();
                        adminOption = sc.nextInt();

                        switch ( adminOption ) {
                            case 1 ->{

                                //Add a book
                                Scanner scn = new Scanner(System.in);

                                System.out.println("Enter the Library ID: ");
                                int id = scn.nextInt();

                                Book newBook = main.addBook();
                                db.insertBookInfo( con,newBook.getRack_no(),newBook.getTitle(),newBook.getSubject(),"empty",
                                        newBook.getDate_published().toString(),newBook.getDate_purchased().toString(),
                                        newBook.isChecked_out() );

                                int books_left = Integer.parseInt( db.getNumberOfBooksLeftInLibrary( con, id ) );
                                db.updateBooksLeftInLib( con, id,books_left + 1 );

                                int books_in_rack = Integer.parseInt( db.getNumberOfBooksInRack( con, newBook.getRack_no() ) );
                                db.updateNumberOfBooksInRack( con, newBook.getRack_no(), books_in_rack + 1 );

                                int num_new_books = Integer.parseInt( db.getNumberOfNewBooks( con ) );
                                db.updateNumberOfNewBooks( con, id, num_new_books + 1 );

                                if ( db.data_inserted ){

                                    int bookId = Integer.parseInt( db.getBookId( con ) );
                                    String fullBookBarcode  = newBook.getBarcode() + String.format( "%04d", bookId );
                                    db.updateBookBarcode( con, bookId, fullBookBarcode );
                                }
                            }
                            case 2 ->{
                                //Delete a book
                                Scanner scn = new Scanner(System.in);

                                System.out.println("Enter book to DELETE barcode: ");
                                String code = scn.next();
                                db.deleteBookByBarcode( con, code );
                            }
                            case 3 ->//Get the books in the database
                                    db.getBooksData( con );
                            case 4 ->{
                                //Search the book in the database
                                Scanner scn = new Scanner(System.in);
                                System.out.println("Enter book to SEARCH barcode: ");
                                String code = scn.next();
                                db.searchBookByBarcode( con, code );
                            }
                            case 5 -> back = true;
                        }
                    }while ( !back );
                }
                case 6 -> {
                    //Borrow a book
                    Scanner scn = new Scanner(System.in);

                    //Get number of books in table
                    int num_of_books_in_table = Integer.parseInt( db.getCountOfBooks( con ) );
                    if ( num_of_books_in_table > 0 ){
                        System.out.println("Enter the Library ID: ");
                        int id = scn.nextInt();

                        int books_left = Integer.parseInt( db.getNumberOfBooksLeftInLibrary( con, id ) );
                        int books_borrowed = Integer.parseInt( db.getNumberOfBooksBorrowedInLibrary( con, id ) );

                        System.out.println("Enter the Book barcode: ");
                        String barcode = scn.next();

                        boolean book_checked = Boolean.parseBoolean( db.getCheckoutStatus( con, barcode ) );
                        System.out.println("Enter member's Card barcode: ");
                        String card_barcode = scn.next();

                        int books_checked_out = Integer.parseInt( db.getNumberOfBooksCheckedOut( con, card_barcode ) );

                        System.out.println("=========Date borrowed=======");
                        System.out.print("Day: ");
                        String borrowed_day = scn.next();
                        System.out.print("Month: ");
                        String borrowed_month = scn.next();
                        System.out.print("Year: ");
                        String borrowed_year = scn.next();
                        Date borrowed_date = new Date( borrowed_year, borrowed_month, borrowed_day );

                        System.out.println("=========Due Date=======");
                        System.out.print("Day: ");
                        String due_day = scn.next();
                        System.out.print("Month: ");
                        String due_month = scn.next();
                        System.out.print("Year: ");
                        String due_year = scn.next();
                        Date date_due = new Date( due_year, due_month, due_day );

                        if ( books_left > 0 ){

                            if ( !book_checked ){

                                //Update number of books left in the library
                                db.updateBooksLeftInLib( con, id,books_left - 1 );
                                //Set number of total books borrowed
                                db.updateNumberOfBooksBorrowedInLibrary( con, id, books_borrowed + 1 );
                                //Update number of books a specific employee has checked out
                                db.updateMemberBookCheckedOut( con, card_barcode, books_checked_out + 1 );
                                //Update books checked out status to true
                                db.updateBookStatus( con, barcode, true );
                                //Set book date borrowed
                                db.updateBookDateBorrowed( con, barcode, borrowed_date.toString() );
                                //Set book borrowed book due date
                                db.updateBookDueDate( con, barcode, date_due.toString() );

                            }else {
                                System.out.println("Book is already checked out.");
                            }
                        }else {
                            System.out.println("There's no book left in the Library.");
                        }
                    }else {
                        System.out.println("There's no book added in the Library");
                    }
                }
                case 7 -> {

                    //Return book
                    Scanner scn = new Scanner(System.in);
                    //Get number of books in table
                    System.out.println("Enter the Library ID: ");
                    int id = scn.nextInt();

                    int books_left = Integer.parseInt( db.getNumberOfBooksLeftInLibrary( con, id ) );
                    int books_borrowed = Integer.parseInt( db.getNumberOfBooksBorrowedInLibrary( con, id ) );

                    System.out.println("Enter the Book barcode: ");
                    String barcode = scn.next();

                    System.out.println("Enter member's Card barcode: ");
                    String member_card_code = scn.next();

                    int books_checked_out = Integer.parseInt( db.getNumberOfBooksCheckedOut( con, member_card_code.trim() ) );

                    String check_out_details = db.getCheckoutStatus( con, barcode.trim() );
                    boolean checked_out = check_out_details.equals( "t" );

                    if ( checked_out ){

                        //Update number of books left in the library
                        db.updateBooksLeftInLib( con, id,books_left + 1 );
                        //Set number of total books borrowed
                        db.updateNumberOfBooksBorrowedInLibrary( con, id,books_borrowed - 1 );
                        //Update number of books a specific employee has checked out
                        db.updateMemberBookCheckedOut( con, member_card_code, books_checked_out - 1 );
                        //Update books checked out status to true
                        db.updateBookStatus( con, barcode, false );

                    }else {
                        System.out.println("The book has not been checked out yet, or \nIt is not from our library.");
                    }
                }
                case 8 -> System.exit(1);
                default -> throw new IllegalStateException( "Unexpected value: " + option );
            }

        }while ( true );
    }
}
