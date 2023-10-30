package org.example;

public class Book {

    public int bookId;
    public String bookIsbn;
    public String bookTitle;
    public boolean isCheckedOut;
    public String checkedOutTo;


    public Book(int bookId, String bookIsbn, String bookTitle, boolean isCheckedOut, String checkedOutTo) {
        this.bookId = bookId;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }

    public String checkOut(String name) {
        if (isCheckedOut == true){
            checkedOutTo = name;

        }
        return checkedOutTo;
    }

    public String checkIn(){
        if (isCheckedOut == false){
            checkedOutTo = "";
        }
        return checkedOutTo;
    }
}
