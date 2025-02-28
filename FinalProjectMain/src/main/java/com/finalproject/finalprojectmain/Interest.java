
package com.finalproject.finalprojectmain;

//abstract class that all the interests extend from, implements printable so that the child classes all have a print method
public abstract class Interest implements Printable
{
    private String name;
    private String genre;
    private int length;
    
    public String getName()
    {
        return this.name;
    }
    public String getGenre()
    {
        return this.genre;
    }
    public int getLength()
    {
        return this.length;
    }
    abstract public String getInterestType();
    public void setName(String name)
    {
        this.name = name;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    public void setLength(int length)
    {
        this.length = length;
    }
}
