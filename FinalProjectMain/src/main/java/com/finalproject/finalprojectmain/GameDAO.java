
package com.finalproject.finalprojectmain;

import java.io.*;
import java.util.ArrayList;

//data access object for the game class
public class GameDAO 
{
    //private attributes used for splicing the string from the file
    private final String DELIMIT = ",";
    private final int NAME = 1;
    private final int GENRE = 2;
    private final int LENGTH = 3;
    private final int DIRECTOR = 4;
    private final int DATE_RELEASED = 5;
    
    public GameDAO()
    {
        //try catch block to create a game txt file to act as the back end
        try
        {
            File file = new File("game.txt");
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //returns an array list of game objects
    public ArrayList<Game> getGames()
    {
        //array list of strings to hold the games
        ArrayList<Game> games = new ArrayList<>();
        //try catch block to catch the ioException
        try
        {
            //creates a buffered reader
            BufferedReader in = new BufferedReader(new FileReader("game.txt"));
            //string to store the next line from the file into
            String game = in.readLine();
            //while loop that continues until the whole file is read
            while(game != null)
            {
                //for each string split at the comma and create a game object from them
                String[] parts = game.split(DELIMIT);
                
                Game g = new Game(parts[NAME], parts[GENRE], Integer.parseInt(parts[LENGTH]), parts[DIRECTOR], parts[DATE_RELEASED]);
                
                //adds the game object to the arrayList
                games.add(g);
                //reads the next line
                game = in.readLine();
            }
            //closes the buffered reader
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        return games;
    }
    
    //returns a specific game object from the passed name
    public Game getGame(String name)
    {
        //assigns the game array list to the return of the getGames method
        ArrayList<Game> games = getGames();
        
        //for each loop checking if the name of the current game object is the same as the passed name
        for (Game x : games)
        {
            if(x.getName().equalsIgnoreCase(name))
            {
                return x;
            }
        }
        return null;
    }
    
    //updates the backend file
    public boolean addGame(Game game)
    {
        ArrayList<Game> games = this.getGames();
        games.add(game);
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("game.txt")));
            //for each loop going through every element of the passed array
            for(Game x : games)
            {
                //writes the current game to the file
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
    
    public boolean updateGames(Game game)
    {
        ArrayList<Game> games = this.getGames();
        for(Game x : games)
        {
            if(game.getName().equals(x.getName()))
            {
                games.remove(x);
                games.add(game);
            }
        }
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("game.txt")));
            //for each loop going through every element of the passed array
            for(Game x : games)
            {
                //writes the current game to the file
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
    
    //deletes a game from the backend file
    public boolean deleteGame(ArrayList<Game> games, Game game)
    {
        //try catch block to catch the ioException
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("game.txt")));
            //for loop which runs through the length of the array
            for(int i = 0; i < games.size(); i++)
            {
                //if the game at the current element of the array equals the passed game remove it from the array list
                if(games.get(i).equals(game))
                {
                    games.remove(i);
                }
            }
            //for/each loop writing the updated arrayList to the file
            for(Game x : games)
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
