
package com.finalproject.finalprojectmain;

//time pojo
public class Time implements Printable
{
    //private attributes
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;
    
    //constructor
    public Time (int hours, int minutes, int seconds, int milliseconds)
    {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }
    
    //overloaded constructor taking in an already formatted time string
    public Time (String time)
    {
        String[] parts = time.split(":|\\.");
        this.hours = Integer.parseInt(parts[0]);
        this.minutes = Integer.parseInt(parts[1]);
        this.seconds = Integer.parseInt(parts[2]);
        this.milliseconds = Integer.parseInt(parts[3]);
    }
    
    //returns the time object as a formatted string
    public String getTime()
    {
        String time = this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds() + "." + this.getMilliseconds();
        
        return time;
    }

    //overridden equals and hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.getHours();
        hash = 59 * hash + this.getMinutes();
        hash = 59 * hash + this.getSeconds();
        hash = 59 * hash + this.getMilliseconds();
        return hash;
    }

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
        final Time other = (Time) obj;
        if (this.getHours() != other.getHours()) {
            return false;
        }
        if (this.getMinutes() != other.getMinutes()) {
            return false;
        }
        if (this.getSeconds() != other.getSeconds()) {
            return false;
        }
        return this.getMilliseconds() == other.getMilliseconds();
    }

    //overridden interface method
    @Override
    public String print() 
    {
        String output = this.getHours() + "," + this.getMinutes() + "," + this.getSeconds() + "," + this.getMilliseconds();
        
        return output;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param minutes the minutes to set
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * @param seconds the seconds to set
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * @return the milliseconds
     */
    public int getMilliseconds() {
        return milliseconds;
    }

    /**
     * @param milliseconds the milliseconds to set
     */
    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }
    
    
}
