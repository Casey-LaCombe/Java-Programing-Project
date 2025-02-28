
package com.finalproject.finalprojectmain;

//pojo for speedrun that extends the game class and imlements the printable interface

import java.util.Objects;

public class Speedrun extends Game implements Printable
{
    //private attributes
    private String category;
    private Time time;
    private User user;
    
    //constructor
    public Speedrun(String name, String genre, int length, String director, String dateReleased, String category, Time time, User user) 
    {
        //calls the parent constructor
        super(name, genre, length, director, dateReleased);
        this.category = category;
        this.time = time;
        this.user = user;
    }
    
    //getters and setters
    public String getCategory()
    {
        return category;
    }
    
    public Time getTime()
    {
        return time;
    }
    
    public User getUser()
    {
        return user;
    }
        
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public void setTime(Time time)
    {
        this.time = time;
    }
    
    public String getFormattedSpeedrun()
    {
        String output = this.user.getName() + "    " + this.time.getTime();
        
        return output;
    }

    //overridden equals and hashcode
    @Override
    public boolean equals(Object obj) {
        final Speedrun other = (Speedrun) obj;
        if (super.getName().equalsIgnoreCase(other.getName()) &&
            this.category.equalsIgnoreCase(other.category) &&
            this.time.getTime().equals(other.time.getTime()) &&
            this.user.getName().equals(other.user.getName()))
        {
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.category);
        hash = 89 * hash + Objects.hashCode(this.time);
        hash = 89 * hash + Objects.hashCode(this.user);
        return hash;
    }
    
    //implemented interface method
    @Override
    public String print() 
    {
        String output = super.getName() + "," + super.getGenre() + "," + super.getLength() + "," + super.getDirector() + "," + super.getDateReleased() + "," + this.category + "," + time.getTime() + "," + user.getName();
        
        return output;
    }
    
}
