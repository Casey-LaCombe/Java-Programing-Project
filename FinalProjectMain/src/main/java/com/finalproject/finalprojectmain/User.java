
package com.finalproject.finalprojectmain;

import java.util.ArrayList;
import java.util.Objects;

//pojo for the user object
public class User implements Printable
{
    //private attributes
    private String name;
    private final String dateAdded;
    private String DOB;
    private String password;
    private ArrayList<Interest> interests;
    
    //constructor
    public User (String name, String dateAdded, String DOB, String password)
    {
        this.name = name;
        this.dateAdded = dateAdded;
        this.DOB = DOB;
        this.password = password;
        interests = new ArrayList<>();
        
    }
    
    //setters and getters
    public String getName()
    {
        return name;
    }
    
    public String getDateAdded()
    {
        return dateAdded;
    }
    
    public String getDOB()
    {
        return DOB;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public ArrayList<Interest> getInterests()
    {
        return this.interests;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDOB(String DOB)
    {
        this.DOB = DOB;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    //adds an interest to the array list
    public void addInterest(Interest interest)
    {
        interests.add(interest);
    }
    
    public void updateInterest(Interest interest)
    {
        for(Interest x : interests)
        {
            if(interest.getName().equals(x.getName()))
            {
                interests.remove(x);
                interests.add(interest);
            }
        }
    }
    
    @Override
    public String toString()
    {
        String interestsString = "";
        if(!this.interests.isEmpty())
        {
            interestsString += "    Interests: ";
        }
        
        
        for(Interest x : this.interests)
        {
            interestsString += x.getInterestType() + ": " + x.getName() + "    ";
        }
        
        String output = this.name + "  " + this.dateAdded + interestsString;
        
        return output;
    }

    //overridden equals and hashcode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.DOB, other.DOB)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return Objects.equals(this.interests, other.interests);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.dateAdded);
        hash = 53 * hash + Objects.hashCode(this.DOB);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.interests);
        return hash;
    }
    
    //overridden interface method
    @Override
    public String print() 
    {
        String output = this.name + "," + this.dateAdded + "," + this.DOB + "," + this.password + ",";
        
        for(Interest x : interests)
        {
            output += x.print() + ",";
        }
        
        return output;
    }
    
}
