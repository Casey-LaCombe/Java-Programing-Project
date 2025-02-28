
package com.finalproject.finalprojectmain;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

//data access object for the speedrun class
public class SpeedrunDAO 
{
    //private attributes used for splicing the string from the file
    private final String DELIMIT = ",";
    private final int NAME = 0;
    private final int GENRE = 1;
    private final int LENGTH = 2;
    private final int DIRECTOR = 3;
    private final int DATE_RELEASED = 4;
    private final int CATEGORY = 5;
    private final int TIME = 6;
    private final int USER = 7;
    
    public SpeedrunDAO()
    {
        //try catch block to create a speedrun txt file to act as the back end
        try
        {
            File file = new File("speedrun.txt");
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //returns an array list of speedrun objects
    public ArrayList<Speedrun> getSpeedruns()
    {
        //array list of strings to hold the speedruns
        ArrayList<Speedrun> speedruns = new ArrayList<>();
        //try catch block to catch the ioException
        try
        {
            //creates a buffered reader
            BufferedReader in = new BufferedReader(new FileReader("speedrun.txt"));
            //string to store the next line from the file into
            String speedrun = in.readLine();
            //while loop that continues until the whole file is read
            while(speedrun != null)
            {
                //for each string split at the comma and create a speedrun object from them
                String[] parts = speedrun.split(DELIMIT);
                
                Speedrun s = new Speedrun(parts[NAME], parts[GENRE], Integer.parseInt(parts[LENGTH]), parts[DIRECTOR], parts[DATE_RELEASED], parts[CATEGORY], new Time(parts[TIME]), UserDAO.getUser(parts[USER]));
                
                //adds the speedrun object to the arrayList
                speedruns.add(s);
                //reads the next line
                speedrun = in.readLine();
            }
            //closes the buffered reader
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        speedruns = this.sort(speedruns);
        return speedruns;
    }
    
    //updates the backend file
    public boolean updateSpeedruns(ArrayList<Speedrun> speedruns)
    {
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("speedrun.txt")));
            //for each loop going through every element of the passed array
            for(Speedrun x : speedruns)
            {
                //writes the current speedrun to the file
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
    
    //deletes a specific speedrun from the backend
    public boolean deleteSpeedrun(Speedrun speedrun)
    {
        ArrayList<Speedrun> speedruns = this.getSpeedruns();
        //try catch block to catch the ioException
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("speedrun.txt")));
            //for loop which runs through the length of the array
            for(int i = 0; i < speedruns.size(); i++)
            {
                //if the speedrun at the current element of the array equals the passed speedrun remove it from the array list
                if(speedruns.get(i).equals(speedrun))
                {
                    speedruns.remove(i);
                }
            }
            //for/each loop writing the updated arrayList to the file
            for(Speedrun x : speedruns)
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
    
    private ArrayList<Speedrun> sort(ArrayList<Speedrun> speedruns)
        {
            for(int i = 0; i < speedruns.size(); i++)
            {
                for(int j = 0; j < speedruns.size(); j++)
                {
                    if(speedruns.get(i).getTime().getHours() > speedruns.get(j).getTime().getHours())
                    {
                        Collections.swap(speedruns, i, j);
                    }
                    else if(speedruns.get(i).getTime().getHours() == speedruns.get(j).getTime().getHours())
                    {
                        if(speedruns.get(i).getTime().getMinutes() > speedruns.get(j).getTime().getMinutes())
                        {
                            Collections.swap(speedruns, i, j);
                        }
                        else if(speedruns.get(i).getTime().getMinutes() == speedruns.get(j).getTime().getMinutes())
                        {
                            if(speedruns.get(i).getTime().getSeconds() > speedruns.get(j).getTime().getSeconds())
                            {
                                Collections.swap(speedruns, i, j);
                            }
                            else if(speedruns.get(i).getTime().getSeconds() == speedruns.get(j).getTime().getSeconds())
                            {
                                if(speedruns.get(i).getTime().getMilliseconds() > speedruns.get(j).getTime().getMilliseconds())
                                {
                                    Collections.swap(speedruns, i, j);
                                }
                            }
                        }
                    }
                }
            }
            return speedruns;
        }
}
