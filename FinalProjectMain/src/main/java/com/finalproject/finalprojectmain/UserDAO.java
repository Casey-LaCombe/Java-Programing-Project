
package com.finalproject.finalprojectmain;

import java.io.*;
import java.util.ArrayList;

//data access object for the user class
public class UserDAO 
{
    //private attributes used for splicing the string from the file
    //static so that it can be used in methods that are used in the speedrunDAO
    private static final String DELIMIT = ",";
    private static final int NAME = 0;
    private static final int DATE_ADDED = 1;
    private static final int DOB = 2;
    private static final int PASSWORD = 3;
    
    public UserDAO()
    {
        //try catch block to create a user txt file to act as the back end
        try
        {
            File file = new File("user.txt");
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //returns an array list of user objects
    //static so that it can be used in the speedrunDAO
    public static ArrayList<User> getUsers()
    {
        //array list of strings to hold the users
        ArrayList<User> users = new ArrayList<>();
        //try catch block to catch the ioException
        try
        {
            //creates a buffered reader
            BufferedReader in = new BufferedReader(new FileReader("user.txt"));
            //string to store the next line from the file into
            String user = in.readLine();
            //while loop that continues until the whole file is read
            while(user != null)
            {
                //for each string split at the comma and create a user object from them
                String[] parts = user.split(DELIMIT);
                
                User u = new User(parts[NAME], parts[DATE_ADDED], parts[DOB], parts[PASSWORD]);
                
                //checks for any interests for the user and adds them to the current user object
                //this is done by comparing the final interest type attributes 
                switch (parts.length) 
                {
                    case 9:
                        if(parts[4].equalsIgnoreCase("music"))
                        {
                            u.addInterest(new Music(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8]));
                        }
                        else
                        {
                            u.addInterest(new Book(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8]));
                        }
                        break;
                    case 10:
                        if(parts[4].equalsIgnoreCase("game"))
                        {
                            u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                        }
                        else
                        {
                            u.addInterest(new Movie(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                        }
                        break;
                    case 14:
                        if(parts[4].equalsIgnoreCase("music"))
                        {
                            u.addInterest(new Music(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8]));
                            u.addInterest(new Book(parts[10], parts[11], Integer.parseInt(parts[12]), parts[13]));
                        }
                        else
                        {
                            u.addInterest(new Book(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8]));
                            u.addInterest(new Music(parts[10], parts[11], Integer.parseInt(parts[12]), parts[13]));
                        }
                        break;
                    case 15:
                        if(parts[4].equalsIgnoreCase("Game"))
                        {
                            if(parts[10].equalsIgnoreCase("Music"))
                            {
                                u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                                u.addInterest(new Music(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14]));
                            }
                            else
                            {
                                u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                                u.addInterest(new Book(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14]));
                            }
                        }
                        else
                        {
                            if(parts[10].equalsIgnoreCase("Music"))
                            {
                                u.addInterest(new Movie(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                                u.addInterest(new Music(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14]));
                            }
                            else
                            {
                                u.addInterest(new Movie(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                                u.addInterest(new Book(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14]));
                            }
                        }
                        break;
                    case 16:
                        if(parts[4].equalsIgnoreCase("game"))
                        {
                            u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                            u.addInterest(new Movie(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14], parts[15]));
                        }
                        else
                        {
                            u.addInterest(new Movie(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                            u.addInterest(new Game(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14], parts[15]));
                        }
                        break;
                    case 20:
                        if(parts[4].equalsIgnoreCase("game"))
                        {
                            u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                            u.addInterest(new Music(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14]));
                            u.addInterest(new Book(parts[16], parts[17], Integer.parseInt(parts[18]), parts[19]));
                        }
                        else
                        {
                            u.addInterest(new Movie(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                            u.addInterest(new Music(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14]));
                            u.addInterest(new Book(parts[16], parts[17], Integer.parseInt(parts[18]), parts[19]));
                        }
                        break;
                    case 21:
                        if(parts[16].equalsIgnoreCase("music"))
                        {
                            u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                            u.addInterest(new Movie(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14], parts[15]));
                            u.addInterest(new Music(parts[17], parts[18], Integer.parseInt(parts[19]), parts[20]));
                        }
                        else
                        {
                            u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                            u.addInterest(new Movie(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14], parts[15]));
                            u.addInterest(new Book(parts[17], parts[18], Integer.parseInt(parts[19]), parts[20]));
                        }
                        break;
                    case 26:
                        u.addInterest(new Game(parts[5], parts[6], Integer.parseInt(parts[7]), parts[8], parts[9]));
                        u.addInterest(new Movie(parts[11], parts[12], Integer.parseInt(parts[13]), parts[14], parts[15]));
                        u.addInterest(new Music(parts[17], parts[18], Integer.parseInt(parts[19]), parts[20]));
                        u.addInterest(new Book(parts[22], parts[23], Integer.parseInt(parts[24]), parts[25]));
                        break;
                    default:
                        break;
                }
                
                //adds the user object to the arrayList
                users.add(u);
                //reads the next line
                user = in.readLine();
            }
            //closes the buffered reader
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        return users;
    }
    
    /*
    //returns a specific user from a specified element
    //static so that it can be used in the speedrunDAO
    public static User getUser(int element)
    {
        User user;
        //assigns a User array list label to the return of the getUsers method
        ArrayList<User> users = getUsers();
        
        //assigns user to the User object at the specified element 
        user = users.get(element);
        
        return user;
    }
    */  
    
    //returns a specific user from a specified name
    public static User getUser(String name)
    { 
        //assigns a User array list label to the return of the getUsers method
        ArrayList<User> users = getUsers();
        
        //for each User it checks if the names are the same then returns that User object
        for (User x : users)
        {
            if (x.getName().equals(name))
            {
                return x;
            }
        }
        
        return null;
    }
    
    //returns a specific username from a specified element in the User array list from the backend file
    public String getUsername(int element)
    {
        String username;
        
        //assings a user array list to the return of the getUsers method
        ArrayList<User> users = getUsers();
        //assigns the username string to the name at the specified element
        username = users.get(element).getName();
        
        return username;
    }
    
    //updates the backend file
    public boolean updateUser(ArrayList<User> users)
    {
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("user.txt")));
            //for each loop going through every element of the passed array
            for(User x : users)
            {
                //writes the current user to the file
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
    
    //deletes a specific user object from the backend file
    public boolean deleteUser(ArrayList<User> users, User user)
    {
        //try catch block to catch the ioException
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("user.txt")));
            //for loop which runs through the length of the array
            for(int i = 0; i < users.size(); i++)
            {
                //if the user at the current element of the array equals the passed user remove it from the array list
                if(users.get(i).equals(user))
                {
                    users.remove(i);
                }
            }
            //for/each loop writing the updated arrayList to the file
            for(User x : users)
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
