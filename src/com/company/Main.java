package com.company;

import java.sql.Connection;
import java.util.Scanner;

//Change variable names to a more understandable manner suiting their context.
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Database db = new Database();
        Scanner kbd = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        int option;
        int adminOption;
        boolean back = false;

        //Connection
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

        do {
            menu.printMainMenu();
            option = kbd.nextInt();

            switch ( option ){
                case 1 -> {

                    Library newLib = menu.addLibrary();
                    db.insertLibraryInfo( con, newLib.getNum_books_borrowed(), newLib.getNum_new_books(), newLib.getNum_of_librarians(), newLib.getNum_books_returned(),
                            newLib.getNum_books_left(), newLib.getNum_of_racks() );

                }
                case 2 -> {

                    Rack newRack = menu.addRack();
                    db.insertRackInfo( con, newRack.getLibId(), newRack.getTotal_books(), newRack.getLocationIdentity(), newRack.getDateCreated().toString() );

                    //To update the number of racks in library
                    int num_racks = Integer.parseInt( db.getNumberOfRacksInLibrary( con, newRack.getLibId() ) );
                    db.updateNumOfRacksInLib( con, newRack.getLibId(), num_racks + 1 );

                }
                case 3 -> {

                    Librarian newLibrarian = menu.registerLibrarian();

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

                    Member newMember = menu.registerMember();
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
                        menu.printAdminMenu();
                        adminOption = sc.nextInt();

                        switch ( adminOption ) {
                            case 1 ->{
                                //Add a book
                                Scanner scn = new Scanner(System.in);

                                System.out.println("Enter the Library ID: ");
                                int id = scn.nextInt();

                                Book newBook = menu.addBook();
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
