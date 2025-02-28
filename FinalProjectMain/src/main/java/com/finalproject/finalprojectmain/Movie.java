
package com.finalproject.finalprojectmain;

//pojo for the movie interest
public class Movie extends Interest
{
    //private attributes
    private String director;
    private String dateReleased;
    //private attribute for the use of identifying the object type when reading from the user file
    private final String INTEREST_TYPE = "Movie";
    
    //constructor
    public Movie (String name, String genre, int length, String director, String dateReleased)
    {
        super.setName(name);
        super.setGenre(genre);
        super.setLength(length);
        this.director = director;
        this.dateReleased = dateReleased;
    }
    
    //getters and setters
    public String getDirector() 
    {
        return director;
    }    

    public String getDateReleased() 
    {
        return dateReleased;
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
