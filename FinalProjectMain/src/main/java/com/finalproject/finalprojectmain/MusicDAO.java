
package com.finalproject.finalprojectmain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MusicDAO 
{
    //private attributes used for splicing the string from the file
    private final String DELIMIT = ",";
    private final int NAME = 1;
    private final int GENRE = 2;
    private final int LENGTH = 3;
    private final int ARTIST = 4;
    
    public MusicDAO()
    {
        //try catch block to create a game txt file to act as the back end
        try
        {
            File file = new File("music.txt");
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //returns an array list of game objects
    public ArrayList<Music> getMusic()
    {
        //array list of strings to hold the music
        ArrayList<Music> songs = new ArrayList<>();
        //try catch block to catch the ioException
        try
        {
            //creates a buffered reader
            BufferedReader in = new BufferedReader(new FileReader("music.txt"));
            //string to store the next line from the file into
            String song = in.readLine();
            //while loop that continues until the whole file is read
            while(song != null)
            {
                //for each string split at the comma and create a music object from them
                String[] parts = song.split(DELIMIT);
                
                Music m = new Music(parts[NAME], parts[GENRE], Integer.parseInt(parts[LENGTH]), parts[ARTIST]);
                
                //adds the music object to the arrayList
                songs.add(m);
                //reads the next line
                song = in.readLine();
            }
            //closes the buffered reader
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        return songs;
    }
    
    //updates the backend file
    public boolean addMusic(Music song)
    {
        ArrayList<Music> songs = this.getMusic();
        songs.add(song);
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("music.txt")));
            //for each loop going through every element of the passed array
            for(Music x : songs)
            {
                //writes the current music object to the file
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
    
    public boolean updateMusic(Music song)
    {
        ArrayList<Music> songs = this.getMusic();
        for(Music x : songs)
        {
            if(song.getName().equals(x.getName()))
            {
                songs.remove(x);
                songs.add(song);
            }
        }
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("music.txt")));
            //for each loop going through every element of the passed array
            for(Music x : songs)
            {
                //writes the current music object to the file
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
    
    //deletes a music object from the backend file
    public boolean deleteSong(ArrayList<Music> songs, Music song)
    {
        //try catch block to catch the ioException
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("music.txt")));
            //for loop which runs through the length of the array
            for(int i = 0; i < songs.size(); i++)
            {
                //if the song at the current element of the array equals the passed song remove it from the array list
                if(songs.get(i).equals(song))
                {
                    songs.remove(i);
                }
            }
            //for/each loop writing the updated arrayList to the file
            for(Music x : songs)
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
