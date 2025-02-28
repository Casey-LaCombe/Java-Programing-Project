
package com.finalproject.finalprojectmain;

//pojo for the game interest
public class Game extends Interest
{
    //private attributes
    private String director;
    private String dateReleased;
    //private attribute for the use of identifying the object type when reading from the user file
    private final String INTEREST_TYPE = "Game";
    
    //constructor
    public Game(String name, String genre, int length, String director, String dateReleased)
    {
        super.setName(name);
        super.setGenre(genre);
        super.setLength(length);
        this.director = director;
        this.dateReleased = dateReleased;
    }
    
    //setters and getters
    public String getDirector()
    {
        return this.director;
    }
    
    public String getDateReleased()
    {
        return this.dateReleased;
    }
    
    @Override
    public String getInterestType()
    {
        return this.INTEREST_TYPE;
    }
    
    public void setDirector(String director)
    {
        this.director = director;
    }
    
    public void setDateReleased(String dateReleased)
    {
        this.dateReleased = dateReleased;
    }
    
    //overridden interface method
    @Override
    public String print() 
    {
        String output = this.INTEREST_TYPE + "," + super.getName() + "," + super.getGenre() + "," + super.getLength() + "," + this.director + "," + this.dateReleased;
                
        return output;
    }
    
}
