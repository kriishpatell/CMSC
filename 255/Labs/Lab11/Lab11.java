package Labs.Lab11;

import java.util.ArrayList;

public class Lab11
{
    public static void main(String[] args)
    {
        Book book1 = new Book("Percy Jackson and the Lightning Thief", "Rick Riordan");
        Book book2 = new Book("Harry Potter and the Goblet of Fire", "J.K Rowling");
        Book book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald");

        System.out.println(book1.toString());
        System.out.println(book2.toString());
        System.out.println(book3.toString());
        System.out.println();

        Bookshelf bookshelf = new Bookshelf(3);

        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        for(Book str : bookshelf.getBooks())
        {
            System.out.println(str);
        }

        bookshelf.emptyBookshelf();
        for(Book str : bookshelf.getBooks())
        {
            System.out.print(str);
        }
    }
}
