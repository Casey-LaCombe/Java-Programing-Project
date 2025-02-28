
package com.finalproject.finalprojectmain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MovieDAO 
{
    //private attributes used for splicing the string from the file
    private final String DELIMIT = ",";
    private final int NAME = 1;
    private final int GENRE = 2;
    private final int LENGTH = 3;
    private final int DIRECTOR = 4;
    private final int DATE_RELEASED = 5;
    
    public MovieDAO()
    {
        //try catch block to create a movie txt file to act as the back end
        try
        {
            File file = new File("movie.txt");
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //returns an array list of movie objects
    public ArrayList<Movie> getMovies()
    {
        //array list of strings to hold the movies
        ArrayList<Movie> movies = new ArrayList<>();
        //try catch block to catch the ioException
        try
        {
            //creates a buffered reader
            BufferedReader in = new BufferedReader(new FileReader("movie.txt"));
            //string to store the next line from the file into
            String movie = in.readLine();
            //while loop that continues until the whole file is read
            while(movie != null)
            {
                //for each string split at the comma and create a movie object from them
                String[] parts = movie.split(DELIMIT);
                
                Movie m = new Movie(parts[NAME], parts[GENRE], Integer.parseInt(parts[LENGTH]), parts[DIRECTOR], parts[DATE_RELEASED]);
                
                //adds the movie object to the arrayList
                movies.add(m);
                //reads the next line
                movie = in.readLine();
            }
            //closes the buffered reader
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        return movies;
    }
    
    //updates the backend file
    public boolean addMovie(Movie movie)
    {
        ArrayList<Movie> movies = this.getMovies();
        movies.add(movie);
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("movie.txt")));
            //for each loop going through every element of the passed array
            for(Movie x : movies)
            {
                //writes the current movie to the file
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
    
    public boolean updateMovies(Movie movie)
    {
         ArrayList<Movie> movies = this.getMovies();
         for(Movie x : movies)
         {
             if(movie.getName().equals(x.getName()))
             {
                 movies.remove(x);
                 movies.add(movie);
             }
         }
        //try catch block to catch the ioException        
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("movie.txt")));
            //for each loop going through every element of the passed array
            for(Movie x : movies)
            {
                //writes the current movie to the file
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
    
    //deletes a movie from the backend file
    public boolean deleteMovie(ArrayList<Movie> movies, Movie movie)
    {
        //try catch block to catch the ioException
        try
        {
            //creates a print writer
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("movie.txt")));
            //for loop which runs through the length of the array
            for(int i = 0; i < movies.size(); i++)
            {
                //if the movie at the current element of the array equals the passed movie remove it from the array list
                if(movies.get(i).equals(movie))
                {
                    movies.remove(i);
                }
            }
            //for/each loop writing the updated arrayList to the file
            for(Movie x : movies)
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
