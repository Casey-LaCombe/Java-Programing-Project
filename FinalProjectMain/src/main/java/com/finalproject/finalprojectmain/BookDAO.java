
package com.finalproject.finalprojectmain;

import java.io.*;
import java.util.ArrayList;

public class BookDAO 
{
    //private attributes used for splicing the string from the file
    private final String DELIMIT = ",";
    private final int NAME = 1;
    private final int GENRE = 2;
    private final int LENGTH = 3;
    private final int AUTHOR = 4;
    
    public BookDAO()
    {
        //try catch block to create a book txt file to act as the back end
        try
        {
            File file = new File("book.txt");
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //returns an array list of game objects
    public ArrayList<Book> getBooks()
    {
        //array list of strings to hold the books
        ArrayList<Book> books = new ArrayList<>();
        //try catch block to catch the ioException
        try
        {
            //creates a buffered reader
            BufferedReader in = new BufferedReader(new FileReader("book.txt"));
            //string to store the next line from the file into
            String book = in.readLine();
            //while loop that continues until the whole file is read
            while(book != null)
            {
                //for each string split at the comma and create a book object from them
                String[] parts = book.split(DELIMIT);
                
                Book b = new Book(parts[NAME], parts[GENRE], Integer.parseInt(parts[LENGTH]), parts[AUTHOR]);
                
                //adds the book object to the arrayList
                books.add(b);
                //reads the next line
                book = in.readLine();
            }
            //closes the buffered reader
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        return books;
    }
    
    //updates the backend file
    public boolean addBook(Book book)
    {
        ArrayList<Book> books = this.getBooks();
        books.add(book);
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("book.txt")));
            //for each loop going through every element of the passed array
            for(Book x : books)
            {
                //writes the current book object to the file
                fout.println(x.print());
            }
            //closes the print writer
            fout.close();
            return true;
        }
        catch(IOException e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean updateBooks(Book book)
    {
        ArrayList<Book> books = this.getBooks();
        for(Book x : books)
        {
            if(book.getName().equals(x.getName()))
            {
                books.remove(x);
                books.add(book);
            }
        }
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("book.txt")));
            //for each loop going through every element of the passed array
            for(Book x : books)
            {
                //writes the current book object to the file
                fout.println(x.print());
            }
            //closes the print writer
            fout.close();
            return true;
        }
        catch(IOException e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    //deletes a book object from the backend file
    public boolean deleteBook(ArrayList<Book> books, Book book)
    {
        //try catch block to catch the ioException
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("book.txt")));
            //for loop which runs through the length of the array
            for(int i = 0; i < books.size(); i++)
            {
                //if the book at the current element of the array equals the passed book remove it from the array list
                if(books.get(i).equals(book))
                {
                    books.remove(i);
                }
            }
            //for/each loop writing the updated arrayList to the file
            for(Book x : books)
            {
                fout.println(x.print());
            }
            //closes the print writer
            fout.close();
            return true;
        }
        catch(IOException e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    
}
