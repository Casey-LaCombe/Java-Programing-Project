
package com.finalproject.finalprojectmain;

import java.io.*;
import java.util.ArrayList;

//data access object for the time class
public class TimeDAO 
{
    //private attributes used for splicing the string from the file
    //static so that it can be used in the methods that are used by the speedrunDAO
    private final String DELIMIT = ",";
    private final int HOUR = 0;
    private final int MINUTE = 1;
    private final int SECOND = 2;
    private final int MILLISECOND = 3;
    
    public TimeDAO()
    {
        //try catch block to create a time txt file to act as the back end
        try
        {
            File file = new File("time.txt");
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //returns an array list of time objects
    //static so that it can be used in the speedrunDAO
    public ArrayList<Time> getTimes()
    {
        //array list of time objects to hold the times
        ArrayList<Time> times = new ArrayList<>();
        //try catch block to catch the ioException
        try
        {
            //creates a buffered reader
            BufferedReader in = new BufferedReader(new FileReader("time.txt"));
            //string to store the next line from the file into
            String time = in.readLine();
            //while loop that continues until the whole file is read
            while(time != null)
            {
                //for each string split at the comma and create a time object from them
                String[] parts = time.split(DELIMIT);
                int hour = Integer.parseInt(parts[HOUR]);
                int minute = Integer.parseInt(parts[MINUTE]);
                int second = Integer.parseInt(parts[SECOND]);
                int millisecond = Integer.parseInt(parts[MILLISECOND]);
                
                Time t = new Time(hour, minute, second, millisecond);
                
                //adds the time object to the arrayList
                times.add(t);
                //reads the next line
                time = in.readLine();
            }
            //closes the buffered reader
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        return times;
    }
    
    //updates the backend file
    public boolean updateTime(ArrayList<Time> times)
    {
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("time.txt")));
            //for each loop going through every element of the passed array
            for(Time x : times)
            {
                //writes the current string to the file
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
    
    //deletes a specific time from the backend file
    public boolean deleteTime(ArrayList<Time> times, String time)
    {
        //try catch block to catch the ioException
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("time.txt")));
            //for loop which runs through the length of the array
            for(int i = 0; i < times.size(); i++)
            {
                //if the time at the current element of the array equals the passed time remove it from the array list
                if(times.get(i).getTime().equalsIgnoreCase(time))
                {
                    times.remove(i);
                }
            }
            //for/each loop writing the updated arrayList to the file
            for(Time x : times)
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
