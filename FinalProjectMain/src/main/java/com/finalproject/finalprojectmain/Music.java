
package com.finalproject.finalprojectmain;

//pojo for the music interest
public class Music extends Interest
{
    //private attributes
    private String artist;
    //private attribute for the use of identifying the object type when reading from the user file
    private final String INTEREST_TYPE = "Music";

    //constructor
    public Music (String name, String genre, int length, String artist)
    {
        super.setName(name);
        super.setGenre(genre);
        super.setLength(length);
        this.artist = artist;
    }
    
    //getters and setters
    public String getArtist()
    {
        return artist;
    }
    
    @Override
    public String getInterestType()
    {
        return this.INTEREST_TYPE;
    }
    
    public void setArtist(String artist)
    {
        this.artist = artist;
    }
    
    //overridden interface method
    @Override
    public String print() 
    {
        String output = this.INTEREST_TYPE + "," + super.getName() + "," + super.getGenre() + "," + super.getLength() + "," + this.artist;
                
        return output;
    }
    
}
