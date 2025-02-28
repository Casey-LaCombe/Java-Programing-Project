
package com.finalproject.finalprojectmain;

//pojo for the book interest
public class Book extends Interest
{
    //private attributes
    private String author;
    //private attribute for the use of identifying the object type when reading from the user file
    private final String INTEREST_TYPE = "Book";
    
    //constructor
    public Book (String name, String genre, int length, String author)
    {
        super.setName(name);
        super.setGenre(genre);
        super.setLength(length);
        this.author = author;
    }
    
    //getters and setters
    public String getAuthor()
    {
        return author;
    }
    
    @Override
    public String getInterestType()
    {
        return this.INTEREST_TYPE;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    //overridden interface method
    @Override
    public String print() 
    {
        String output = this.INTEREST_TYPE + "," + super.getName() + "," + super.getGenre() + "," + super.getLength() + "," + this.author;
                
        return output;
    }
    
}
