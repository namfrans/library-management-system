package com.company;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_RESET = "\u001B[0m";

    public boolean data_inserted = false;

    public Connection connectToDatabase( String database_name, String user, String password ){
        Connection connection = null;
        try{
            Class.forName( "org.postgresql.Driver" );
            connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/" + database_name, user, password );
            if ( connection != null ){
                System.out.println("Connection success!!");
            }else {
                System.out.println("Connection failed!!");
            }
        }catch ( Exception e ){
            System.out.println( e );
        }
        return connection;
    }

    //Okay 1
    public void createTableLibrary( Connection con ){

        Statement statement;

        try {
            String query = "CREATE TABLE Library" + "(libId INT GENERATED ALWAYS AS IDENTITY, num_books_borrowed INT, num_new_books INT, " +
                    "num_of_librarians INT, num_books_returned INT, num_books_left INT, num_of_racks INT, primary key(libId));";
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Table 'Library' Created successfully!");
        }catch ( Exception e ){
            System.out.println( e );
        }

    }
    //Okay 2
    public void createTableCard( Connection con ) {

        Statement statement;

        try {
            String query = "CREATE TABLE Card" + "(cardId INT GENERATED ALWAYS AS IDENTITY, dateOfIssue DATE NOT NULL DEFAULT CURRENT_DATE, " +
                    "active BOOLEAN NOT NULL, barcode VARCHAR(200), primary key(cardId));";
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table 'Card' Created successfully!");
        } catch ( Exception e ) {
            System.out.println ( e );
        }

    }

    //Okay 3
    public void createTableRack( Connection con ) {

        Statement statement;

        try {
            String query = "CREATE TABLE Rack" + "(rackId INT GENERATED ALWAYS AS IDENTITY, libId INT, total_books INT, " +
                    "locationIdentity VARCHAR(200), dateCreated DATE, primary key(rackId), " +
                    "CONSTRAINT fk_library FOREIGN KEY(libId) REFERENCES Library(libId));";
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Table 'Rack' Created successfully!");
        } catch ( Exception e ) {
            System.out.println(e);
        }

    }

    //Okay 4
    public void createTableBook( Connection con ) {

        Statement statement;

        try {
            String query = "CREATE TABLE Book" + "(bookId INT GENERATED ALWAYS AS IDENTITY, rackId INT, title VARCHAR(200), " +
                    "subject VARCHAR(200), barcode VARCHAR(200), date_published DATE NOT NULL, date_borrowed DATE, due_date DATE, " +
                    "date_purchased DATE, checked_out BOOLEAN, primary key(bookId)," +
                    "CONSTRAINT fk_rack FOREIGN KEY(rackId) REFERENCES Rack(rackId));";
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Table 'Book' Created successfully!");
        } catch ( Exception e ) {
            System.out.println( e );
        }

    }

    //Okay 5
    public void createTableMember( Connection con ) {

        Statement statement;

        try {
            String query = "CREATE TABLE Member" + "(memberId INT GENERATED ALWAYS AS IDENTITY, name VARCHAR(200), phone_no VARCHAR(200), address VARCHAR(200), " +
                    "password VARCHAR(200), dateOfMembership DATE, booksCheckedOut INT, cardId INT, dateOfIssue DATE, active BOOLEAN" +
                    ", barcode VARCHAR(200), primary key(memberId), " +
                    "CONSTRAINT fk_card FOREIGN KEY(cardId) REFERENCES Card(cardId));";
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table 'Member' Created successfully!");
        } catch ( Exception e ) {
            System.out.println( e );
        }

    }

    //Okay 6
    public void createTableLibrarian( Connection con ) {

        Statement statement;

        try {
            String query = "CREATE TABLE Librarian" + "(librarianId INT GENERATED ALWAYS AS IDENTITY, name VARCHAR(200), phone_no VARCHAR(200), address VARCHAR(200), " +
                    "password VARCHAR(200), " +
                    "cardId INT, issueDate DATE, active BOOLEAN, barcode VARCHAR(200), primary key(librarianId), " +
                    "CONSTRAINT fk_card FOREIGN KEY(cardId) REFERENCES Card(cardId));";
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table 'Librarian' Created successfully!");
        } catch ( Exception e ) {
            System.out.println(e);
        }

    }

    //Inserting data to the db

    public void insertLibraryInfo( Connection con, int num_books_borrowed, int num_new_books,
                                   int num_of_librarians, int num_books_returned,
                                   int num_books_left, int num_of_racks ) {

        Statement statement;

        try {
            String query = String.format( "INSERT INTO Library(num_books_borrowed,num_new_books,num_of_librarians," +
                    "num_books_returned,num_books_left,num_of_racks) values ('%s','%s','%s','%s','%s','%s');", num_books_borrowed, num_new_books,
                    num_of_librarians, num_books_returned, num_books_left, num_of_racks );
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Data inserted!");
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void insertCardInfo( Connection con, String dateOfIssue, boolean active , String barcode ){

        Statement statement;

        try {
            String query = String.format("INSERT INTO Card(dateOfIssue,active,barcode) values ('%s','%s','%s');",dateOfIssue, active, barcode);
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Data inserted!");
            data_inserted = true;
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void insertRackInfo( Connection con, int libId, int total_books,
            String locationIdentity, String dateCreated ) {

        Statement statement;

        try {
            String query = String.format("INSERT INTO Rack(libId,total_books,locationIdentity,dateCreated) values ('%s','%s','%s','%s');", libId, total_books, locationIdentity, dateCreated);
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Data inserted!");
        }catch ( Exception e ){
            System.out.println( e );
        }

    }

    public void insertBookInfo( Connection con, int rackId, String title,
                                String subject, String barcode, String date_published,
                                String date_purchased, boolean checked_out ) {

        Statement statement;

        try {
            String query = String.format("INSERT INTO Book(rackId,title,subject,barcode,date_published," +
                            "date_purchased,checked_out) values ('%s','%s','%s','%s'," +
                            "'%s','%s','%s');",rackId, title, subject, barcode, date_published,
                    date_purchased, checked_out );
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Data inserted!");
            data_inserted = true;
        }catch ( Exception e ){
            System.out.println(e);
        }

    }

    public void insertMemberInfo( Connection con, String name, String phone_no, String address,
                                  String password, String dateOfMembership, int booksCheckedOut,
                                  int cardId, String dateOfIssue, boolean active, String barcode ) {

        Statement statement;

        try {
            String query = String.format("INSERT INTO Member(name,phone_no,address,password,dateOfMembership,booksCheckedOut,cardId,dateOfIssue,active, barcode) " +
                            "values ('%s','%s','%s','%s','%s','%s','%s','%s','%s', '%s');",
                   name, phone_no,address,password,dateOfMembership,booksCheckedOut,cardId,dateOfIssue,active,barcode);
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Data inserted!");
        } catch ( Exception e ){
            System.out.println(e);
        }

    }

    public void insertLibrarianInfo( Connection con, String name, String phone_no,
                                    String address, String password, int cardId,
                                    String dateOfIssue, boolean active, String barcode ) {

        Statement statement;

        try {
            String query = String.format("INSERT INTO Librarian(name,phone_no,address,password,cardId,issueDate,active,barcode) values ('%s','%s','%s','%s','%s','%s','%s','%s');",
                    name, phone_no,address,password,cardId,dateOfIssue,active,barcode);
            statement = con.createStatement();
            statement.executeUpdate( query );
            System.out.println("Data inserted!");
        } catch ( Exception e ){
            System.out.println(e);
        }

    }


    //Getting data from the db
    //Query to return all books in the database
    public void getBooksData( Connection con ) {

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT * FROM Book");
            statement = con.createStatement();
            rs = statement.executeQuery( query );
            while ( rs.next() ){
                System.out.println( " " );
                System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.print( TEXT_CYAN + "\nBook ID: " + TEXT_RESET + rs.getString("bookId") );
                System.out.print( TEXT_CYAN + "\nRack ID: " + TEXT_RESET + rs.getString("rackId") );
                System.out.print( TEXT_CYAN + "\nTitle: " + TEXT_RESET + rs.getString("title") );
                System.out.print( TEXT_CYAN + "\nSubject: " + TEXT_RESET + rs.getString("subject") );
                System.out.print( TEXT_CYAN + "\nBarcode: " + TEXT_RESET + rs.getString("barcode") );
                System.out.print( TEXT_CYAN + "\nDate published: " + TEXT_RESET + rs.getString("date_published") );
                System.out.print( TEXT_CYAN + "\nDate borrowed: " + TEXT_RESET + rs.getString("date_borrowed") );
                System.out.print( TEXT_CYAN + "\nDue Date: " + TEXT_RESET + rs.getString("due_date") );
                System.out.print( TEXT_CYAN + "\nDate purchased: " + TEXT_RESET + rs.getString("date_purchased") );
                System.out.print( TEXT_CYAN + "\nChecked out? " + TEXT_RESET + rs.getString("checked_out") + "\n" );
                System.out.print("*****************************************************************************************************************************\n");
            }

        } catch ( Exception e ){
            System.out.println(e);
        }

    }

    //Query to get card id for a newly inserted card
    public String getCardId( Connection con ) {

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT cardId FROM Card ORDER BY cardId DESC LIMIT 1");
            statement = con.createStatement();
            rs = statement.executeQuery( query );
            while ( rs.next() ){
                return rs.getString("cardId");
            }
        }catch ( Exception e ){
            System.out.println(e);
        }

        return null;

    }

    //Get number of Books in Book's table
    public String getCountOfBooks( Connection con ) {

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT COUNT(*) FROM Book WHERE barcode IS NOT NULL");
            statement = con.createStatement();
            rs = statement.executeQuery( query );
            while ( rs.next() ){
                return rs.getString("count");
            }
        }catch ( Exception e ){
            System.out.println(e);
        }

        return null;

    }
    //Query to get number of books left in the library
    public String getNumberOfBooksLeftInLibrary( Connection con, int library_Id ){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT num_books_left FROM Library WHERE libId = '%s'",library_Id);
            statement = con.createStatement();
            rs = statement.executeQuery( query );
            while ( rs.next() ){
                return rs.getString("num_books_left");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;

    }
    //Get number of books returned in library
    public String getNumberOfBooksReturned(Connection con, int library_Id){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT num_books_returned FROM Library WHERE libId = '%s'",library_Id);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("num_books_returned");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    //Get number of racks in the library
    public String getNumberOfRacksInLibrary(Connection con, int library_Id){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT num_of_racks FROM Library WHERE libId = '%s'",library_Id);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("num_of_racks");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    //Get the number of librarians in the library
    public String getNumberOfLibrarians(Connection con, int library_Id){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT num_of_librarians FROM Library WHERE libId = '%s'",library_Id);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("num_of_librarians");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }
    //Get number of new books in library
    public String getNumberOfNewBooks(Connection con){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT COUNT(*) FROM Book WHERE date_published >= '2020-01-01'::date");
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("count");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;

    }

    //Get number of books borrowed in Library
    public String getNumberOfBooksBorrowedInLibrary(Connection con, int library_Id){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT num_books_borrowed FROM Library WHERE libId = '%s'",library_Id);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("num_books_borrowed");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    //Get the total number of books in a rack
    public String getNumberOfBooksInRack(Connection con, int rackId){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT total_books FROM Rack WHERE rackId = '%s'",rackId);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("total_books");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    //Query to check if the book is checked or not
    public String getCheckoutStatus(Connection con, String barcode){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT checked_out FROM Book WHERE barcode = '%s'", barcode);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("checked_out");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    //Query to get num of books checked out by member
    public String getNumberOfBooksCheckedOut(Connection con, String barcode){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT booksCheckedOut FROM Member WHERE barcode = '%s'", barcode);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("booksCheckedOut");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    //Query to get book id for a newly inserted book
    public String getBookId(Connection con){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT bookId FROM Book ORDER BY bookId DESC LIMIT 1");
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                return rs.getString("bookId");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    //Updating table data on the db
    //Update member number of books checked out after every transaction
    public void updateMemberBookCheckedOut(Connection con, String barcode, int book_checked){

        Statement statement;

        try {
            String query = String.format("UPDATE Member SET booksCheckedOut = '%s' WHERE barcode = '%s'", book_checked, barcode);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Books checked updated success!");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update Card table with a new barcode
    public void updateCardBarcode(Connection con, int cardId,String barcode){

        Statement statement;

        try {
            String query = String.format("UPDATE Card SET barcode = '%s' WHERE cardId = '%s'", barcode, cardId);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Barcode updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update Book table with a new barcode
    public void updateBookBarcode(Connection con, int bookId,String barcode){

        Statement statement;

        try {
            String query = String.format("UPDATE Book SET barcode = '%s' WHERE bookId = '%s'", barcode, bookId);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Barcode updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Query to update number of books left in the library
    public void updateBooksLeftInLib(Connection con, int library_Id, int books_left){

        Statement statement;

        try {
            String query = String.format("UPDATE Library SET num_books_left = '%s' WHERE libId = '%s'", books_left, library_Id);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Books left updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update number of books returned in library
    public void updateBooksReturned(Connection con, int library_Id, int books_returned){

        Statement statement;

        try {
            String query = String.format("UPDATE Library SET num_books_returned = '%s' WHERE libId = '%s'", books_returned, library_Id);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Books returned updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update number of racks in library
    public void updateNumOfRacksInLib(Connection con, int library_Id, int number_of_racks){

        Statement statement;

        try {
            String query = String.format("UPDATE Library SET num_of_racks = '%s' WHERE libId = '%s'", number_of_racks, library_Id);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Number if racks updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update number of librarians in library
    public void updateNumberOfLibrarians(Connection con, int library_Id, int number_of_librarians){

        Statement statement;

        try {
            String query = String.format("UPDATE Library SET num_of_librarians = '%s' WHERE libId = '%s'", number_of_librarians, library_Id);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Number of librarians updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update number of new books in library
    public void updateNumberOfNewBooks(Connection con, int library_Id, int books_new){

        Statement statement;

        try {
            String query = String.format("UPDATE Library SET num_new_books = '%s' WHERE libId = '%s'", books_new, library_Id);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updated num of new books successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update number of books in a rack
    public void updateNumberOfBooksInRack(Connection con, int rackId, int number_of_books){

        Statement statement;

        try {
            String query = String.format("UPDATE Rack SET total_books = '%s' WHERE rackId = '%s'", number_of_books, rackId);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updated number of books in rack successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Update number of books borrowed in library
    public void updateNumberOfBooksBorrowedInLibrary(Connection con, int library_Id, int books_borrowed){

        Statement statement;

        try {
            String query = String.format("UPDATE Library SET num_books_borrowed = '%s' WHERE libId = '%s'", books_borrowed, library_Id);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Books borrowed in Library updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Query to update the book status
    public void updateBookStatus(Connection con, String barcode, boolean status){

        Statement statement;

        try {
            String query = String.format("UPDATE Book SET checked_out = '%s' WHERE barcode = '%s'", status, barcode);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Book checkout status updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Query to update the book date borrowed
    public void updateBookDateBorrowed(Connection con, String barcode, String date_borrowed){

        Statement statement;

        try {
            String query = String.format("UPDATE Book SET date_borrowed = '%s' WHERE barcode = '%s'", date_borrowed, barcode);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Book dated borrowed updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Query to update the book due date
    public void updateBookDueDate(Connection con, String barcode, String due_date){

        Statement statement;

        try {
            String query = String.format("UPDATE Book SET due_date = '%s' WHERE barcode = '%s'", due_date, barcode);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Book due date updated successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Searching data from the db
    //Query to search a book from the database
    public void searchBookByBarcode(Connection con, String barcode){

        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("SELECT * FROM Book WHERE barcode = '%s'", barcode);
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println( " " );
                System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.print( TEXT_CYAN + "\nBook ID: " + TEXT_RESET + rs.getString("bookId") );
                System.out.print( TEXT_CYAN + "\nRack ID: " + TEXT_RESET + rs.getString("rackId") );
                System.out.print( TEXT_CYAN + "\nTitle: " + TEXT_RESET + rs.getString("title") );
                System.out.print( TEXT_CYAN + "\nSubject: " + TEXT_RESET + rs.getString("subject") );
                System.out.print( TEXT_CYAN + "\nBarcode: " + TEXT_RESET + rs.getString("barcode") );
                System.out.print( TEXT_CYAN + "\nDate published: " + TEXT_RESET + rs.getString("date_published") );
                System.out.print( TEXT_CYAN + "\nDate borrowed: " + TEXT_RESET + rs.getString("date_borrowed") );
                System.out.print( TEXT_CYAN + "\nDue Date: " + TEXT_RESET + rs.getString("due_date") );
                System.out.print( TEXT_CYAN + "\nDate purchased: " + TEXT_RESET + rs.getString("date_purchased") );
                System.out.print( TEXT_CYAN + "\nChecked out? " + TEXT_RESET + rs.getString("checked_out")+ "\n");
                System.out.print("*****************************************************************************************************************************\n");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Deleting data from the db
    public void deleteBookByBarcode(Connection con, String barcode){

        Statement statement;

        try {
            String query = String.format("DELETE FROM Book WHERE barcode = '%s'", barcode);
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Book deleted successfully.");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
