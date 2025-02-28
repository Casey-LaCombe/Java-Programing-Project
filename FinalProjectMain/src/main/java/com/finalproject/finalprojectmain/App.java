package com.finalproject.finalprojectmain;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.application.Application.launch;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



/**
 * JavaFX App
 */
public class App extends Application 
{
    //declared scenes
    Scene mainScene;
    Scene addUser;
    Scene addGame;
    Scene addMovie;
    Scene addMusic;
    Scene addBook;
    Scene updateUser;
    Scene verifiedUser;
    Scene updateGame;
    Scene updateMovie;
    Scene updateMusic;
    Scene updateBook;
    Scene deleteUser;
    Scene speedrunRecords;
    Scene deleteRecord;
    Scene addRecord;

    @Override
    public void start(Stage mainStage) 
    {
        //data access objects to pass objects between the front and backend
        GameDAO gDAO = new GameDAO();
        MovieDAO movDAO = new MovieDAO();
        MusicDAO musDAO = new MusicDAO();
        BookDAO bDAO = new BookDAO();
        SpeedrunDAO sDAO = new SpeedrunDAO();
        UserDAO uDAO = new UserDAO();
        
        //sets the title
        mainStage.setTitle("Speedrun Social");
        
        /*
        
        Main Scene
        
        */
        
        //sets the gridpane
        GridPane mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.TOP_CENTER);
        mainGrid.setVgap(20);
        mainGrid.setPadding(new Insets(30,30,30,30));
        
        //sets the column constraints
        ColumnConstraints c = new ColumnConstraints(200);
        mainGrid.getColumnConstraints().add(c);
        
        //sets the label and buttons
        Label title = new Label("Welcome to Speedrun Social");
        mainGrid.add(title, 0, 0);
        GridPane.setHalignment(title, HPos.LEFT);
        Button speedrun = new Button("Check out speedrunning records");
        mainGrid.add(speedrun, 1, 0);
        GridPane.setHalignment(speedrun, HPos.RIGHT);
        
        TextArea taUsers = new TextArea();
        taUsers.setEditable(false);
        ScrollPane scroll = new ScrollPane(taUsers);
        mainGrid.add(scroll, 0, 1, 2, 1);
        
        String userTextAreaText = "";
        ArrayList<String> userToStrings = new ArrayList<>();
        ArrayList<User> usersForStrings = UserDAO.getUsers();
        for(User x : usersForStrings)
        {
            userToStrings.add(x.toString());
        }
        for(String x : userToStrings)
        {
            userTextAreaText += x + "\n";  
        }
        taUsers.setText(userTextAreaText);
        
        Button createNewUser = new Button("Create new user");
        Button updateUserBtn = new Button("Update user");
        Button deleteUserBtn = new Button("Delete user");
        Button exit = new Button("Exit");
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(createNewUser, updateUserBtn, deleteUserBtn, exit);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        mainGrid.add(buttonBox, 0, 4, 3, 1);
        
        //event handlers for the buttons
        speedrun.setOnAction(event ->
        {
            mainStage.setScene(speedrunRecords);
            mainStage.show();
        });
        
        createNewUser.setOnAction(event -> 
        {
            mainStage.setScene(addUser);
            mainStage.show();
        });
        
        updateUserBtn.setOnAction(event ->
        {
            mainStage.setScene(updateUser);
            mainStage.show();
        });
        
        deleteUserBtn.setOnAction(event ->
        {
            mainStage.setScene(deleteUser);
            mainStage.show();
        });
        
        exit.setOnAction(event ->
        {
            System.exit(0);
        });
        
        //initializes the main scene
        mainScene = new Scene(mainGrid, 800, 400);
        
        //sets the main scene as the first scene when launched
        mainStage.setScene(mainScene);
        mainStage.show();
        
        
        /*
        
        Add User Scene
        
        */
        //alternate stage for the creation of objects
        Stage altStage = new Stage();
        
        //creates the grid pane for this scene
        GridPane userGrid = new GridPane();
        userGrid.setAlignment(Pos.TOP_LEFT);
        userGrid.setVgap(20);
        userGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels
        userGrid.add(new Label("Create New User"), 0, 0);
        userGrid.add(new Label("Name: "), 0, 1);
        userGrid.add(new Label("Password: "), 0, 2);
        userGrid.add(new Label("DOB: "), 0, 3);
        userGrid.add(new Label ("Add an Interest!!!"), 0, 5);
        
        //adds textfields
        TextField tfName = new TextField();
        TextField tfPass = new TextField();
        TextField tfDOB = new TextField();
        userGrid.add(tfName, 1, 1);
        userGrid.add(tfPass, 1, 2);
        userGrid.add(tfDOB, 1, 3);
        
        //adds buttons and text fields
        Button addGameButton = new Button("Add Game!");
        Button addMovieButton = new Button("Add Movie!");
        Button addMusicButton = new Button("Add Music!");
        Button addBookButton = new Button("Add Book!");
        HBox interestButtonBox = new HBox(10);
        interestButtonBox.getChildren().addAll(addGameButton, addMovieButton, addMusicButton, addBookButton);
        userGrid.add(interestButtonBox, 0, 6, 2, 1);
        
        //hidden textfields
        TextField tfGame = new TextField();
        TextField tfMovie = new TextField();
        TextField tfMusic = new TextField();
        TextField tfBook = new TextField();
        
        Button createUserButton = new Button("Create");
        Button exitUserButton = new Button("Exit");
        HBox userButtonBox = new HBox(10);
        userButtonBox.getChildren().addAll(createUserButton, exitUserButton);
        userButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        userGrid.add(userButtonBox, 0, 7, 2, 1);
        
        //adds functionality to the buttons
        addGameButton.setOnAction(event ->
        {
            altStage.setScene(addGame);
            altStage.show();
        });
        addMovieButton.setOnAction(event ->
        {
            altStage.setScene(addMovie);
            altStage.show();
        });
        addMusicButton.setOnAction(event -> 
        {
            altStage.setScene(addMusic);
            altStage.show();
        });
        addBookButton.setOnAction(event ->
        {
           altStage.setScene(addBook);
           altStage.show();
        });
        
        createUserButton.setOnAction(event ->
        {
            //creates a new user from the data in the text fields and adds the interests created from the hidden textfields populated by the pop up windows then saves that user to the back end through the dao to the back end
            ArrayList<User> users = UserDAO.getUsers();
            User u = new User(tfName.getText(), LocalDate.now().toString(), tfDOB.getText(), tfPass.getText());
            String[] parts;
            if(!tfGame.getText().equals(""))
            {
                parts = tfGame.getText().split(",");
                Game g = new Game(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5]);
                u.addInterest(g);
            }
            if(!tfMovie.getText().equals(""))
            {
                parts = tfMovie.getText().split(",");
                Movie m = new Movie(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5]);
                u.addInterest(m);
            }
            if(!tfMusic.getText().equals(""))
            {
                parts = tfMusic.getText().split(",");
                Music m = new Music(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                u.addInterest(m);
            }
            if(!tfBook.getText().equals(""))
            {
                parts = tfBook.getText().split(",");
                Book b = new Book(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                u.addInterest(b);
            }
            users.add(u);
            if (uDAO.updateUser(users))
            {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("User Successfully Created");
                a.showAndWait();
                
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error");
                e.setHeaderText("Invalid Data, User not Created");
                e.showAndWait();
            }
            
            String tempTextAreaText = taUsers.getText();
            tempTextAreaText += u.toString() + "\n";
            taUsers.setText(tempTextAreaText);
            
            tfName.setText("");
            tfPass.setText("");
            tfDOB.setText("");
            tfGame.setText("");
            tfMovie.setText("");
            tfMusic.setText("");
            tfBook.setText("");
            
                    
        });
        exitUserButton.setOnAction(event ->
        {
            tfName.setText("");
            tfPass.setText("");
            tfDOB.setText("");
            tfGame.setText("");
            tfMovie.setText("");
            tfMusic.setText("");
            tfBook.setText("");
            mainStage.setScene(mainScene);
            mainStage.show();
        });
        
        //initializes the scene
        addUser = new Scene(userGrid, 400, 400);
        
        /*
        
        Speedrun Records Scene
        
        */
        //creates vbox
        VBox speedrunVBox = new VBox(10);
        speedrunVBox.setAlignment(Pos.TOP_LEFT);
        speedrunVBox.setPadding(new Insets(30,30,30,30));
        
        speedrunVBox.getChildren().add(new Label("Speedrunning Records"));
        
        //creates and populates combo boxes
        ComboBox<String> gameComboBox = new ComboBox<>();
        gameComboBox.setPromptText("Pick a Game");
        ArrayList<String> gameName = new ArrayList<>();
        ArrayList<Game> numGames = gDAO.getGames();
        for(Game x : numGames)
        {
            boolean isIn = false;
            for(String y : gameName)
            {
                if(x.getName().equalsIgnoreCase(y))
                {
                    isIn = true;
                }
            }
            if(!isIn)
            {
                gameName.add(x.getName());
            }
        }
        for(String x : gameName)
        {
            gameComboBox.getItems().add(x);
        }
        
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.setPromptText("Pick a Category");
        ArrayList<String> categoryName = new ArrayList<>();
        ArrayList<Speedrun> numSpeedruns = sDAO.getSpeedruns();
        for(Speedrun x : numSpeedruns)
        {
            boolean isIn = false;
            for(String y : categoryName)
            {
                if(x.getCategory().equalsIgnoreCase(y))
                {
                    isIn = true;
                }
            }
            if(!isIn)
            {
                categoryName.add(x.getCategory());
            }
        }
        for(String x : categoryName)
        {
            categoryComboBox.getItems().add(x);
        }
        
        //creates button
        Button selectGameCategoryBtn = new Button("Select Game and Category");
        //creats a hbox for the two combo boxes and the button
        HBox comboBoxHBox = new HBox(10);
        comboBoxHBox.getChildren().addAll(gameComboBox, categoryComboBox, selectGameCategoryBtn);
        //adds to the vbox
        speedrunVBox.getChildren().add(comboBoxHBox);
        //creates a text field and adds it to the vbox
        TextArea taRecords = new TextArea();
        speedrunVBox.getChildren().add(taRecords);
        
        //creates more buttons, puts them in a hbox and adds them to the vbox
        Button addRecordButton = new Button("Add Record");
        Button deleteRecordButton = new Button("Delete Record");
        Button exitRecords = new Button("Exit");
        HBox recordBox = new HBox(10);
        recordBox.setAlignment(Pos.BOTTOM_CENTER);
        recordBox.getChildren().addAll(addRecordButton, deleteRecordButton, exitRecords);
        speedrunVBox.getChildren().add(recordBox);
        
        //gives functionality to the buttons
        selectGameCategoryBtn.setOnAction(event ->
        {
            //pulls the selected text from the combo boxes and uses it to find all the speedruns that match it from the from the back end through the dao to the back end
            String gName = gameComboBox.getSelectionModel().getSelectedItem();
            String cName = categoryComboBox.getSelectionModel().getSelectedItem();
            String textAreaText = "";
            ArrayList<String> gameNameLambda = new ArrayList<>();
            ArrayList<Game> numGamesLambda = gDAO.getGames();
            for(Game x : numGamesLambda)
            {
                gameNameLambda.add(x.getName());
            }
            ArrayList<Speedrun> numSpeedrunsLambda = sDAO.getSpeedruns();
            for(String x : gameNameLambda)
            {
                for(Speedrun y : numSpeedrunsLambda)
                {
                    if(gName.equalsIgnoreCase(x) && cName.equalsIgnoreCase(y.getCategory()))
                    {
                        textAreaText += y.getFormattedSpeedrun() + "\n";
                    }
                }
            }
            taRecords.setText(textAreaText);
        });
        
        addRecordButton.setOnAction(event ->
        {
            taRecords.setText("");
            mainStage.setScene(addRecord);
            mainStage.show();
        });
        deleteRecordButton.setOnAction(event ->
        {
            taRecords.setText("");
            mainStage.setScene(deleteRecord);
            mainStage.show();
        });
        exitRecords.setOnAction(event -> 
        {
            taRecords.setText("");
            mainStage.setScene(mainScene);
            mainStage.show();
        });
        
        //initializes the scene
        speedrunRecords = new Scene(speedrunVBox, 500, 500);
        
        
        /*
        
        Add Game Scene
        
        */
        //makes a grid for the game scene
        GridPane gameGrid = new GridPane();
        gameGrid.setAlignment(Pos.TOP_LEFT);
        gameGrid.setVgap(20);
        gameGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels to the grid
        gameGrid.add(new Label("Add Game Interest"), 0, 0);
        
        gameGrid.add(new Label("Title: "), 0, 1);
        gameGrid.add(new Label("Genre: "), 0, 2);
        gameGrid.add(new Label("Year released: "), 0, 3);
        gameGrid.add(new Label("Director: "), 0, 4);
        gameGrid.add(new Label("Length(hours): "), 0, 5);
        
        //creates and adds text fields to the grid
        TextField tfGameTitle = new TextField();
        TextField tfGameGenre = new TextField();
        TextField tfGameYearReleased = new TextField();
        TextField tfGameDirector = new TextField();
        TextField tfGameLength = new TextField();
        
        gameGrid.add(tfGameTitle, 1, 1);
        gameGrid.add(tfGameGenre, 1, 2);
        gameGrid.add(tfGameYearReleased, 1, 3);
        gameGrid.add(tfGameDirector, 1, 4);
        gameGrid.add(tfGameLength, 1, 5);
        
        //creates and adds buttons to the grid
        Button createGame = new Button("Add Game");
        Button gameExit = new Button("Exit");
        HBox gameButtonBox = new HBox(10);
        gameButtonBox.getChildren().addAll(createGame, gameExit);
        gameGrid.add(gameButtonBox, 0, 6, 2, 1);
        //gives the buttons funcionality
        createGame.setOnAction(event ->
        {
            //creates a game from the text in the text fields and sends it through a dao to save to the back end, this button also checks if the game name is in the combo box for the speedrun drop down and if it isn't it adds it
            Game g = new Game(tfGameTitle.getText(), tfGameGenre.getText(), Integer.parseInt(tfGameLength.getText()), tfGameDirector.getText(), tfGameYearReleased.getText());
            
            boolean hasGame = false;
            for(String x : gameComboBox.getItems())
            {
                if(x.equalsIgnoreCase(g.getName()))
                {
                    hasGame = true;
                }
            }
            
            if(!hasGame)
            {
                gameComboBox.getItems().add(g.getName());
            }
            
            if (gDAO.addGame(g))
            {
                tfGame.setText(g.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Game Successfully Added!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Game Not Added");
                e.showAndWait();
            }
            
            tfGameTitle.setText("");
            tfGameGenre.setText("");
            tfGameLength.setText("");
            tfGameDirector.setText("");
            tfGameYearReleased.setText("");
            
            altStage.close();
        });
        gameExit.setOnAction(event ->
        {
            tfGameTitle.setText("");
            tfGameGenre.setText("");
            tfGameLength.setText("");
            tfGameDirector.setText("");
            tfGameYearReleased.setText("");
            altStage.close();
        });
        
        //initializes the scene
        addGame = new Scene(gameGrid, 400, 400);
        
        
        
        /*
        
        Add Movie Scene
        
        */
        //creates a grid for the movie scene
        GridPane movieGrid = new GridPane();
        movieGrid.setAlignment(Pos.TOP_LEFT);
        movieGrid.setVgap(20);
        movieGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels to the grid
        movieGrid.add(new Label("Add Movie Interest"), 0, 0);
        
        movieGrid.add(new Label("Title: "), 0, 1);
        movieGrid.add(new Label("Genre: "), 0, 2);
        movieGrid.add(new Label("Year released: "), 0, 3);
        movieGrid.add(new Label("Director: "), 0, 4);
        movieGrid.add(new Label("Length(minutes): "), 0, 5);
        
        //adds textfields and buttons to the grid
        TextField tfMovieTitle = new TextField();
        TextField tfMovieGenre = new TextField();
        TextField tfMovieYearReleased = new TextField();
        TextField tfMovieDirector = new TextField();
        TextField tfMovieLength = new TextField();
        
        movieGrid.add(tfMovieTitle, 1, 1);
        movieGrid.add(tfMovieGenre, 1, 2);
        movieGrid.add(tfMovieYearReleased, 1, 3);
        movieGrid.add(tfMovieDirector, 1, 4);
        movieGrid.add(tfMovieLength, 1, 5);
        
        Button createMovie = new Button("Add Movie");
        Button movieExit = new Button("Exit");
        HBox movieButtonBox = new HBox(10);
        movieButtonBox.getChildren().addAll(createMovie, movieExit);
        movieGrid.add(movieButtonBox, 0, 6, 2, 1);
        
        //gives the buttons funcionality
        createMovie.setOnAction(event ->
        {
            //creates a movie object from the text in the text fields then saves it to the back end through a dao
            Movie m = new Movie(tfMovieTitle.getText(), tfMovieGenre.getText(), Integer.parseInt(tfMovieLength.getText()), tfMovieDirector.getText(), tfMovieYearReleased.getText());
            if (movDAO.addMovie(m))
            {
                tfMovie.setText(m.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Movie Successfully Added!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Movie Not Added");
                e.showAndWait();
            }
            
            tfMovieTitle.setText("");
            tfMovieTitle.setText("");
            tfMovieTitle.setText("");
            tfMovieDirector.setText("");
            tfMovieYearReleased.setText("");
            
            altStage.close();
        });
        movieExit.setOnAction(event ->
        {
            tfMovieTitle.setText("");
            tfMovieTitle.setText("");
            tfMovieTitle.setText("");
            tfMovieDirector.setText("");
            tfMovieYearReleased.setText("");
            altStage.close();
        });
        
        //initializes the scene
        addMovie = new Scene(movieGrid, 400, 400);
        
        
        /*
        
        Add Music Scene
        
        */
        //makes a grid for the music scene
        GridPane musicGrid = new GridPane();
        musicGrid.setAlignment(Pos.TOP_LEFT);
        musicGrid.setVgap(20);
        musicGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels to the grid
        musicGrid.add(new Label("Add Music Interest"), 0, 0);
        
        musicGrid.add(new Label("Song Title: "), 0, 1);
        musicGrid.add(new Label("Genre: "), 0, 2);
        musicGrid.add(new Label("Artist: "), 0, 3);
        musicGrid.add(new Label("Length(seconds): "), 0, 4);
        
        //adds text fields and buttons to the grid
        TextField tfSongTitle = new TextField();
        TextField tfSongGenre = new TextField();
        TextField tfSongArtist = new TextField();
        TextField tfSongLength = new TextField();
        
        musicGrid.add(tfSongTitle, 1, 1);
        musicGrid.add(tfSongGenre, 1, 2);
        musicGrid.add(tfSongArtist, 1, 3);
        musicGrid.add(tfSongLength, 1, 4);
        
        Button createMusic = new Button("Add Song");
        Button musicExit = new Button("Exit");
        HBox musicButtonBox = new HBox(10);
        musicButtonBox.getChildren().addAll(createMusic, musicExit);
        musicGrid.add(musicButtonBox, 0, 5, 2, 1);
        
        //gives the buttons functionality
        createMusic.setOnAction(event ->
        {
            //creates a music object from the text in the text fields and saves it to the back end through a dao
            Music m = new Music(tfSongTitle.getText(), tfSongGenre.getText(), Integer.parseInt(tfSongLength.getText()), tfSongArtist.getText());
            if (musDAO.addMusic(m))
            {
                tfMusic.setText(m.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Music Successfully Added!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Music Not Added");
                e.showAndWait();
            }
            
            tfSongTitle.setText("");
            tfSongGenre.setText("");
            tfSongLength.setText("");
            tfSongArtist.setText("");
            
            altStage.close();
        });
        musicExit.setOnAction(event ->
        {
            tfSongTitle.setText("");
            tfSongGenre.setText("");
            tfSongLength.setText("");
            tfSongArtist.setText("");
            altStage.close();
        });
        
        //initializes the scene
        addMusic = new Scene(musicGrid, 400, 400);
        
        
        /*
        
        Add Book Scene
        
        */
        
        //creats a grid for the book scene
        GridPane bookGrid = new GridPane();
        bookGrid.setAlignment(Pos.TOP_LEFT);
        bookGrid.setVgap(20);
        bookGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels to the grid
        bookGrid.add(new Label("Add Book Interest"), 0, 0);
        
        bookGrid.add(new Label("Title: "), 0, 1);
        bookGrid.add(new Label("Genre: "), 0, 2);
        bookGrid.add(new Label("Author: "), 0, 3);
        bookGrid.add(new Label("Length (pages): "), 0, 4);
        
        //adds textfields and buttons
        TextField tfBookTitle = new TextField();
        TextField tfBookGenre = new TextField();
        TextField tfBookAuthor = new TextField();
        TextField tfBookLength = new TextField();
        
        bookGrid.add(tfBookTitle, 1, 1);
        bookGrid.add(tfBookGenre, 1, 2);
        bookGrid.add(tfBookAuthor, 1, 3);
        bookGrid.add(tfBookLength, 1, 4);
        
        Button createBook = new Button("Add Book");
        Button bookExit = new Button("Exit");
        HBox bookButtonBox = new HBox(10);
        bookButtonBox.getChildren().addAll(createBook, bookExit);
        bookGrid.add(bookButtonBox, 0, 5, 2, 1);
        
        //gives the buttons functionality
        createBook.setOnAction(event ->
        {
            //creates a book object from the text in the textfields and saves it to the back end through a dao
            Book b = new Book(tfBookTitle.getText(), tfBookGenre.getText(), Integer.parseInt(tfBookLength.getText()), tfBookAuthor.getText());
            if (bDAO.addBook(b))
            {
                tfBook.setText(b.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Book Successfully Added!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Book Not Added");
                e.showAndWait();
            }
            
            tfBookTitle.setText("");
            tfBookGenre.setText("");
            tfBookLength.setText("");
            tfBookAuthor.setText("");
            
            altStage.close();
        });
        bookExit.setOnAction(event ->
        {
            tfBookTitle.setText("");
            tfBookGenre.setText("");
            tfBookLength.setText("");
            tfBookAuthor.setText("");
            altStage.close();
        });
        
        //initializes the scene
        addBook = new Scene(bookGrid, 400, 400);
        
        /*
        
        Update User Scene
        
        */
        //makes a vbox for the scene
        VBox updateVBox = new VBox(10);
        updateVBox.setAlignment(Pos.CENTER);
        updateVBox.setPadding(new Insets(60,60,60,60));
        
        //add text fields and buttons to the box
        updateVBox.getChildren().add(new Label("Username: "));
        TextField tfUpdateUsername = new TextField();
        updateVBox.getChildren().add(tfUpdateUsername);
        updateVBox.getChildren().add(new Label("Password: "));
        TextField tfUpdatePassword = new TextField();
        updateVBox.getChildren().add(tfUpdatePassword);
        Button verifyBtn = new Button("Verify");
        Button exitUpdateBtn = new Button("Exit");
        HBox updateButtonBox = new HBox(10);
        updateButtonBox.getChildren().addAll(verifyBtn, exitUpdateBtn);
        updateVBox.getChildren().add(updateButtonBox);
        
        //gives the buttons functionality
        verifyBtn.setOnAction(event ->
        {
            //checks if the user name and password match a user from the back end, this is done through a dao, if verified it calls the verified user scene
            ArrayList<User> users = UserDAO.getUsers();
            for(User x : users)
            {
                if(x.getName().equalsIgnoreCase(tfUpdateUsername.getText()) && x.getPassword().equals(tfUpdatePassword.getText()))
                {
                    tfUpdateUsername.setText("");
                    tfUpdatePassword.setText("");
                    
                    mainStage.setScene(verifiedUser);
                    mainStage.show();
                }
            }
            Alert e = new Alert(Alert.AlertType.ERROR);
            e.setTitle("Error");
            e.setHeaderText("User Not Found");
        });
        exitUpdateBtn.setOnAction(event ->
        {
            tfUpdateUsername.setText("");
            tfUpdatePassword.setText("");
            mainStage.setScene(mainScene);
            mainStage.show();
        });
        
        //initializes the scene
        updateUser = new Scene(updateVBox, 300, 300);
        
        /*
        
        Verified User Scene
        
        */
        //makes a grid for the scene
        GridPane verifyGrid = new GridPane();
        verifyGrid.setAlignment(Pos.TOP_CENTER);
        verifyGrid.setVgap(20);
        verifyGrid.setHgap(20);
        verifyGrid.setPadding(new Insets(30, 30, 30, 30));
        
        //adds labels to the grid
        verifyGrid.add(new Label("Select what you'd like to change: "), 0, 0, 3, 1);
        verifyGrid.add(new Label("Username: "), 0, 1);
        verifyGrid.add(new Label("Password: "), 0, 2);
        verifyGrid.add(new Label("Date of Birth: "), 0, 3);
        
        //adds checkboxes to the grid
        CheckBox usernameCheck = new CheckBox();
        verifyGrid.add(usernameCheck, 1, 1);
        CheckBox passwordCheck = new CheckBox();
        verifyGrid.add(passwordCheck, 1, 2);
        CheckBox DOBCheck = new CheckBox();
        verifyGrid.add(DOBCheck, 1, 3);
        CheckBox editInterestsCheck = new CheckBox("Edit Interests?");
        verifyGrid.add(editInterestsCheck, 0, 4);
        
        //adds textfields to the grid
        TextField tfUsernameChange = new TextField();
        tfUsernameChange.setEditable(false);
        verifyGrid.add(tfUsernameChange, 2, 1);
        TextField tfPasswordChange = new TextField();
        tfPasswordChange.setEditable(false);
        verifyGrid.add(tfPasswordChange, 2, 2);
        TextField tfDOBChange = new TextField();
        tfDOBChange.setEditable(false);
        verifyGrid.add(tfDOBChange, 2, 3);
        
        //adds buttons to a hbox and the hbox to the grid
        Button editGameBtn = new Button("Edit Game");
        Button editMovieBtn = new Button("Edit Movie");
        Button editMusicBtn = new Button("Edit Music");
        Button editBookBtn = new Button("Edit Book");
        editGameBtn.setDisable(true);
        editMovieBtn.setDisable(true);
        editMusicBtn.setDisable(true);
        editBookBtn.setDisable(true);
        HBox editInterestsBox = new HBox(10);
        editInterestsBox.getChildren().addAll(editGameBtn, editMovieBtn, editMusicBtn, editBookBtn);
        verifyGrid.add(editInterestsBox, 0, 5, 3, 1);
        
        Button updateBtn = new Button("Update");
        Button verifyExitBtn = new Button("Exit");
        HBox verifyBtnBox = new HBox(10);
        verifyBtnBox.setAlignment(Pos.CENTER_RIGHT);
        verifyBtnBox.getChildren().addAll(updateBtn, verifyExitBtn);

        verifyGrid.add(verifyBtnBox, 2, 6, 3, 1);
        
        //hidden textfields 
        TextField tfUpdateGame = new TextField();
        TextField tfUpdateMovie = new TextField();
        TextField tfUpdateMusic = new TextField();
        TextField tfUpdateBook = new TextField();
        
        //gives the buttons and checkboxes funcionality
        usernameCheck.setOnAction(event ->
        {
            tfUsernameChange.setEditable(true);
        });
        passwordCheck.setOnAction(event ->
        {
            tfPasswordChange.setEditable(true);
        });
        DOBCheck.setOnAction(event ->
        {
            tfDOBChange.setEditable(true);
        });
        editInterestsCheck.setOnAction(event -> 
        {
            editGameBtn.setDisable(false);
            editMovieBtn.setDisable(false);
            editMusicBtn.setDisable(false);
            editBookBtn.setDisable(false);
        });
        
        editGameBtn.setOnAction(event ->
        {
            altStage.setScene(updateGame);
            altStage.show();
        });
        editMovieBtn.setOnAction(event ->
        {
            altStage.setScene(updateMovie);
            altStage.show();
        });
        editMusicBtn.setOnAction(event -> 
        {
            altStage.setScene(updateMusic);
            altStage.show();
        });
        editBookBtn.setOnAction(event ->
        {
           altStage.setScene(updateBook);
           altStage.show();
        });
        
        updateBtn.setOnAction(event ->
        {
            //creates a new user then sets it equal to the verified user through a dao, once that is done it changes the corresponding values then saves it to the back end through a dao
            ArrayList<User> users = UserDAO.getUsers();
            User u = new User("", "", "", "");
            for(User x : users)
            {
                if(x.getName().equalsIgnoreCase(tfUpdateUsername.getText()) && x.getPassword().equals(tfUpdatePassword.getText()))
                {
                    u = x;
                }
            }
            
            if(usernameCheck.isSelected())
            {
                u.setName(tfUsernameChange.getText());
            }
            if(passwordCheck.isSelected())
            {
                u.setPassword(tfPasswordChange.getText());
            }
            if(DOBCheck.isSelected())
            {
                u.setDOB(tfDOBChange.getText());
            }
            String[] parts;
            if(!tfUpdateGame.getText().equals(""))
            {
                parts = tfUpdateGame.getText().split(",");
                Game g = new Game(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5]);
                u.updateInterest(g);
            }
            if(!tfUpdateMovie.getText().equals(""))
            {
                parts = tfUpdateMovie.getText().split(",");
                Movie m = new Movie(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5]);
                u.updateInterest(m);
            }
            if(!tfUpdateMusic.getText().equals(""))
            {
                parts = tfUpdateMusic.getText().split(",");
                Music m = new Music(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                u.updateInterest(m);
            }
            if(!tfUpdateBook.getText().equals(""))
            {
                parts = tfUpdateBook.getText().split(",");
                Book b = new Book(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                u.updateInterest(b);
            }
            if (uDAO.updateUser(users))
            {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("User Successfully Updated");
                a.showAndWait();
                mainStage.setScene(mainScene);
                mainStage.show();
                
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error");
                e.setHeaderText("Invalid Data, User not Updated");
                e.showAndWait();
            }
        });
        
        verifyExitBtn.setOnAction(event ->
        {
            tfUsernameChange.setText("");
            tfPasswordChange.setText("");
            tfDOBChange.setText("");
            
            tfUpdateGame.setText("");
            tfUpdateMovie.setText("");
            tfUpdateMusic.setText("");
            tfUpdateBook.setText("");
        
            mainStage.setScene(mainScene);
            mainStage.show();
        });
        
        //initializes the scene
        verifiedUser = new Scene(verifyGrid, 400, 600);
        
        /*
        
        Update Game Scene
        
        */
        //grid for the scene
        GridPane updateGameGrid = new GridPane();
        updateGameGrid.setAlignment(Pos.TOP_LEFT);
        updateGameGrid.setVgap(20);
        updateGameGrid.setPadding(new Insets(30,30,30,30));
        
        //add labels to the grid
        updateGameGrid.add(new Label("Update Game Interest"), 0, 0);
        
        updateGameGrid.add(new Label("Title: "), 0, 1);
        updateGameGrid.add(new Label("Genre: "), 0, 2);
        updateGameGrid.add(new Label("Year released: "), 0, 3);
        updateGameGrid.add(new Label("Director: "), 0, 4);
        updateGameGrid.add(new Label("Length: "), 0, 5);
        
        //adds textfields and buttons to the grid
        TextField tfUpdateGameTitle = new TextField();
        TextField tfUpdateGameGenre = new TextField();
        TextField tfUpdateGameYearReleased = new TextField();
        TextField tfUpdateGameDirector = new TextField();
        TextField tfUpdateGameLength = new TextField();
        
        updateGameGrid.add(tfUpdateGameTitle, 1, 1);
        updateGameGrid.add(tfUpdateGameGenre, 1, 2);
        updateGameGrid.add(tfUpdateGameYearReleased, 1, 3);
        updateGameGrid.add(tfUpdateGameDirector, 1, 4);
        updateGameGrid.add(tfUpdateGameLength, 1, 5);
        
        Button updateGameBtn = new Button("Update Game");
        Button updateGameExit = new Button("Exit");
        HBox gameUpdateButtonBox = new HBox(10);
        gameUpdateButtonBox.getChildren().addAll(updateGameBtn, updateGameExit);
        updateGameGrid.add(gameUpdateButtonBox, 0, 6, 2, 1);
        
        //gives the buttons functionality
        updateGameBtn.setOnAction(event ->
        {
            //creates a new game and from the entered data and saves it to the back end through a dao
            Game g = new Game(tfUpdateGameTitle.getText(), tfUpdateGameGenre.getText(), Integer.parseInt(tfUpdateGameLength.getText()), tfUpdateGameDirector.getText(), tfUpdateGameYearReleased.getText());
            if (gDAO.updateGames(g))
            {
                tfUpdateGame.setText(g.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Game Successfully Updated!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Game Not Updated");
                e.showAndWait();
            }
            
            tfUpdateGameTitle.setText("");
            tfUpdateGameGenre.setText("");
            tfUpdateGameYearReleased.setText("");
            tfUpdateGameDirector.setText("");
            tfUpdateGameLength.setText("");
           
            altStage.close();
        });
        updateGameExit.setOnAction(event ->
        {
            tfUpdateGameTitle.setText("");
            tfUpdateGameGenre.setText("");
            tfUpdateGameYearReleased.setText("");
            tfUpdateGameDirector.setText("");
            tfUpdateGameLength.setText("");
            altStage.close();
        });
        
        //initializes the scene
        updateGame = new Scene(updateGameGrid, 400, 400);
        
        
        
        /*
        
        Update Movie Scene
        
        */
        //createa a grid
        GridPane updateMovieGrid = new GridPane();
        updateMovieGrid.setAlignment(Pos.TOP_LEFT);
        updateMovieGrid.setVgap(20);
        updateMovieGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels to the grid
        updateMovieGrid.add(new Label("Update Movie Interest"), 0, 0);
        
        updateMovieGrid.add(new Label("Title: "), 0, 1);
        updateMovieGrid.add(new Label("Genre: "), 0, 2);
        updateMovieGrid.add(new Label("Year released: "), 0, 3);
        updateMovieGrid.add(new Label("Director: "), 0, 4);
        updateMovieGrid.add(new Label("Length: "), 0, 5);
        
        //adds text fields and buttons to the grid
        TextField tfUpdateMovieTitle = new TextField();
        TextField tfUpdateMovieGenre = new TextField();
        TextField tfUpdateMovieYearReleased = new TextField();
        TextField tfUpdateMovieDirector = new TextField();
        TextField tfUpdateMovieLength = new TextField();
        
        updateMovieGrid.add(tfUpdateMovieTitle, 1, 1);
        updateMovieGrid.add(tfUpdateMovieGenre, 1, 2);
        updateMovieGrid.add(tfUpdateMovieYearReleased, 1, 3);
        updateMovieGrid.add(tfUpdateMovieDirector, 1, 4);
        updateMovieGrid.add(tfUpdateMovieLength, 1, 5);
        
        Button updateMovieBtn = new Button("Add Movie");
        Button updateMovieExit = new Button("Exit");
        HBox updateMovieButtonBox = new HBox(10);
        updateMovieButtonBox.getChildren().addAll(updateMovieBtn, updateMovieExit);
        updateMovieGrid.add(updateMovieButtonBox, 0, 6, 2, 1);
        
        //gives the buttons functionality
        updateMovieBtn.setOnAction(event ->
        {
            //makes a new movie object from the entered data in the text fields and saves it to the back end through a dao
            Movie m = new Movie(tfUpdateMovieTitle.getText(), tfUpdateMovieGenre.getText(), Integer.parseInt(tfUpdateMovieLength.getText()), tfUpdateMovieDirector.getText(), tfUpdateMovieYearReleased.getText());
            if (movDAO.updateMovies(m))
            {
                tfUpdateMovie.setText(m.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Movie Successfully Updated!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Movie Not Updated");
                e.showAndWait();
            }
            
            tfUpdateMovieTitle.setText("");
            tfUpdateMovieGenre.setText("");
            tfUpdateMovieYearReleased.setText("");
            tfUpdateMovieDirector.setText("");
            tfUpdateMovieLength.setText("");
            
            altStage.close();
        });
        updateMovieExit.setOnAction(event ->
        {
            tfUpdateMovieTitle.setText("");
            tfUpdateMovieGenre.setText("");
            tfUpdateMovieYearReleased.setText("");
            tfUpdateMovieDirector.setText("");
            tfUpdateMovieLength.setText("");
            altStage.close();
        });
        
        //initializes the scene
        updateMovie = new Scene(updateMovieGrid, 400, 400);
        
        
        /*
        
        Update Music Scene
        
        */
        //makes a grid
        GridPane updateMusicGrid = new GridPane();
        updateMusicGrid.setAlignment(Pos.TOP_LEFT);
        updateMusicGrid.setVgap(20);
        updateMusicGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels to the grid
        updateMusicGrid.add(new Label("Update Music Interest"), 0, 0);
        
        updateMusicGrid.add(new Label("Song Title: "), 0, 1);
        updateMusicGrid.add(new Label("Genre: "), 0, 2);
        updateMusicGrid.add(new Label("Artist: "), 0, 3);
        updateMusicGrid.add(new Label("Length: "), 0, 4);
        
        //adds text fields and buttons to the grid
        TextField tfUpdateSongTitle = new TextField();
        TextField tfUpdateSongGenre = new TextField();
        TextField tfUpdateSongArtist = new TextField();
        TextField tfUpdateSongLength = new TextField();
        
        updateMusicGrid.add(tfUpdateSongTitle, 1, 1);
        updateMusicGrid.add(tfUpdateSongGenre, 1, 2);
        updateMusicGrid.add(tfUpdateSongArtist, 1, 3);
        updateMusicGrid.add(tfUpdateSongLength, 1, 4);
        
        Button updateMusicBtn = new Button("Add Song");
        Button updateMusicExit = new Button("Exit");
        HBox updateMmusicButtonBox = new HBox(10);
        updateMmusicButtonBox.getChildren().addAll(updateMusicBtn, updateMusicExit);
        updateMusicGrid.add(updateMmusicButtonBox, 0, 5, 2, 1);
        
        //gives the buttons functionality
        updateMusicBtn.setOnAction(event ->
        {
            //creats a music object from the data in the text fields and saves it to the back end through a dao
            Music m = new Music(tfUpdateSongTitle.getText(), tfUpdateSongGenre.getText(), Integer.parseInt(tfUpdateSongLength.getText()), tfUpdateSongArtist.getText());
            if (musDAO.updateMusic(m))
            {
                tfUpdateMusic.setText(m.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Music Successfully Updated!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Music Not Updated");
                e.showAndWait();
            }
            
            tfUpdateSongTitle.setText("");
            tfUpdateSongGenre.setText("");
            tfUpdateSongLength.setText("");
            tfUpdateSongArtist.setText("");
            
            altStage.close();
        });
        updateMusicExit.setOnAction(event ->
        {
            tfUpdateSongTitle.setText("");
            tfUpdateSongGenre.setText("");
            tfUpdateSongLength.setText("");
            tfUpdateSongArtist.setText("");
            altStage.close();
        });
        
        //initializes the scene
        updateMusic = new Scene(updateMusicGrid, 400, 400);
        
        
        /*
        
        Update Book Scene
        
        */
        //creates a grid
        GridPane updateBookGrid = new GridPane();
        updateBookGrid.setAlignment(Pos.TOP_LEFT);
        updateBookGrid.setVgap(20);
        updateBookGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels to the grid
        updateBookGrid.add(new Label("Add Book Interest"), 0, 0);
        
        updateBookGrid.add(new Label("Title: "), 0, 1);
        updateBookGrid.add(new Label("Genre: "), 0, 2);
        updateBookGrid.add(new Label("Author: "), 0, 3);
        updateBookGrid.add(new Label("Length (pages): "), 0, 4);
        
        //adds text fields and buttons to the grid
        TextField tfUpdateBookTitle = new TextField();
        TextField tfUpdateBookGenre = new TextField();
        TextField tfUpdateBookAuthor = new TextField();
        TextField tfUpdateBookLength = new TextField();
        
        updateBookGrid.add(tfUpdateBookTitle, 1, 1);
        updateBookGrid.add(tfUpdateBookGenre, 1, 2);
        updateBookGrid.add(tfUpdateBookAuthor, 1, 3);
        updateBookGrid.add(tfUpdateBookLength, 1, 4);
        
        Button updateBookBtn = new Button("Add Book");
        Button updateBookExit = new Button("Exit");
        HBox updateBookButtonBox = new HBox(10);
        updateBookButtonBox.getChildren().addAll(updateBookBtn, updateBookExit);
        updateBookGrid.add(updateBookButtonBox, 0, 5, 2, 1);
        
        //gives the buttons functionality
        updateBookBtn.setOnAction(event ->
        {
            //creates a book object from the entered data and saves it to the back end through a dao
            Book b = new Book(tfUpdateBookTitle.getText(), tfUpdateBookGenre.getText(), Integer.parseInt(tfUpdateBookLength.getText()), tfUpdateBookAuthor.getText());
            if (bDAO.updateBooks(b))
            {
                tfUpdateBook.setText(b.print());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Book Successfully Updated!!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error!!");
                e.setHeaderText("Invalid Data, Book Not Updated");
                e.showAndWait();
            }
            
            tfUpdateBookTitle.setText("");
            tfUpdateBookGenre.setText("");
            tfUpdateBookLength.setText("");
            tfUpdateBookAuthor.setText("");
            
            altStage.close();
        });
        updateBookExit.setOnAction(event ->
        {
            tfUpdateBookTitle.setText("");
            tfUpdateBookGenre.setText("");
            tfUpdateBookLength.setText("");
            tfUpdateBookAuthor.setText("");
            altStage.close();
        });
        
        //initializes the scene
        updateBook = new Scene(updateBookGrid, 400, 400);
        
        
        
        /*
        
        Delete User Scene
        
        */
        //creatsa a vbox for the scene
        VBox deleteVBox = new VBox(10);
        deleteVBox.setAlignment(Pos.CENTER);
        deleteVBox.setPadding(new Insets(60,60,60,60));
        
        //adds labels, textfields and buttons to the vbox
        deleteVBox.getChildren().add(new Label("Enter the username and password"));        
        deleteVBox.getChildren().add(new Label("for the user to be deleted"));        
        deleteVBox.getChildren().add(new Label("Username: "));
        TextField tfDeleteUsername = new TextField();
        deleteVBox.getChildren().add(tfDeleteUsername);
        deleteVBox.getChildren().add(new Label("Password: "));
        TextField tfDeletePassword = new TextField();
        deleteVBox.getChildren().add(tfDeletePassword);
        Button deleteBtn = new Button("Delete");
        Button exitDeleteBtn = new Button("Exit");
        HBox deleteButtonBox = new HBox(10);
        deleteButtonBox.setAlignment(Pos.CENTER_RIGHT);
        deleteButtonBox.getChildren().addAll(deleteBtn, exitDeleteBtn);
        deleteVBox.getChildren().add(deleteButtonBox);
        
        //gives the buttons functionality
        deleteBtn.setOnAction(event ->
        {
            //finds the corresponding user from the user name and password entered then it sends the corresponding object from the dao through the dao to delete it
            ArrayList<User> users = UserDAO.getUsers();
            for(User x : users)
            {
                if(x.getName().equalsIgnoreCase(tfDeleteUsername.getText()) && x.getPassword().equals(tfDeletePassword.getText()))
                {
                    System.out.println("got here");
                    if(uDAO.deleteUser(users, x))
                    {
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                        a.setTitle("Success");
                        a.setHeaderText("User Successfully Deleted");
                        a.showAndWait();
                        
                        tfDeleteUsername.setText("");
                        tfDeletePassword.setText("");
        
                        mainStage.setScene(mainScene);
                        mainStage.show();
                        break;
                    }
                }
            }
            Alert e = new Alert(Alert.AlertType.ERROR);
            e.setTitle("Error");
            e.setHeaderText("User Not Found");
            e.showAndWait();
        });
        exitDeleteBtn.setOnAction(event ->
        {
            tfDeleteUsername.setText("");
            tfDeletePassword.setText("");
            mainStage.setScene(mainScene);
            mainStage.show();
        });
        
        deleteUser = new Scene(deleteVBox, 300, 300);
        
        /*
        
        Add Record Scene
        
        */
        //creates grid
        GridPane addRecordGrid = new GridPane();
        addRecordGrid.setAlignment(Pos.TOP_LEFT);
        addRecordGrid.setVgap(20);
        addRecordGrid.setPadding(new Insets(30,30,30,30));
        
        //adds labels, textfields and buttons
        addRecordGrid.add(new Label("Game name: "), 0, 0);
        TextField tfGameName = new TextField();
        addRecordGrid.add(tfGameName, 1, 0);
        Button findGameButton = new Button("Find Game");
        addRecordGrid.add(findGameButton, 2, 0);
        
        Label categoryLabel = new Label("Category: ");
        Label nameLabel = new Label("Name: ");
        Label timeLabel = new Label("Time: ");
        categoryLabel.setVisible(false);
        nameLabel.setVisible(false);
        timeLabel.setVisible(false);
        addRecordGrid.add(categoryLabel, 0, 4);
        addRecordGrid.add(nameLabel, 0, 5);
        addRecordGrid.add(timeLabel, 0, 6);
        
        TextField tfCategory = new TextField();
        TextField tfUsername = new TextField();
        TextField tfTime = new TextField();
        tfCategory.setVisible(false);
        tfUsername.setVisible(false);
        tfTime.setVisible(false);
        addRecordGrid.add(tfCategory, 1, 4);
        addRecordGrid.add(tfUsername, 1, 5);
        addRecordGrid.add(tfTime, 1, 6);
        
        Button addNewRecordButton = new Button("Add Record");
        addNewRecordButton.setVisible(false);
        addRecordGrid.add(addNewRecordButton, 1, 7);
        
        //gives the buttons functionality
        findGameButton.setOnAction(event ->
        {
            //checks if the game entered exists, if it does it makes the rest of the scene visible if not it pulls up the add game scene
            ArrayList<Game> games = gDAO.getGames();
            boolean found = false;
            for (Game x : games)
            {
                if(x.getName().equalsIgnoreCase(tfGameName.getText()))
                {
                    found = true;
                }
            }
            
            
            if(found)
            {
                categoryLabel.setVisible(true);
                nameLabel.setVisible(true);
                timeLabel.setVisible(true);
                tfCategory.setVisible(true);
                tfUsername.setVisible(true);
                tfTime.setVisible(true);
                addNewRecordButton.setVisible(true);

            }
            else
            {
                mainStage.setScene(addGame);
                mainStage.show();
            }
        });
        
        addNewRecordButton.setOnAction(event ->
        {
            //adds the new record made from the entered data to the back end through a dao, also updates the category combo box if its a new category
            ArrayList<Speedrun> speedruns = sDAO.getSpeedruns();
            ArrayList<Game> games = gDAO.getGames();
            for (Game x : games)
            {
                if(x.getName().equalsIgnoreCase(tfGameName.getText()))
                {
                    Speedrun s = new Speedrun(x.getName(), x.getGenre(), x.getLength(), x.getDirector(), x.getDateReleased(), tfCategory.getText(), new Time(tfTime.getText()), uDAO.getUser(tfUsername.getText()));
                    
                    boolean hasCategory = false;
                    for(String a : categoryComboBox.getItems())
                    {
                        if(a.equals(s.getCategory()))
                        {
                            hasCategory = true;
                        }
                    }
                    
                    if(!hasCategory)
                    {
                        categoryComboBox.getItems().add(s.getCategory());
                    }
                    
                    speedruns.add(s);
                }
            }
            
            if (sDAO.updateSpeedruns(speedruns))
            {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success!");
                a.setHeaderText("Speedrun successfully added!");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error");
                e.setHeaderText("Invalid Data!");
                e.showAndWait();
            }
            
            tfGameName.setText("");
            tfCategory.setText("");
            tfUsername.setText("");
            tfTime.setText("");
            
            mainStage.setScene(speedrunRecords);
            mainStage.show();
        });
        
        //initializes the scene
        addRecord = new Scene(addRecordGrid, 600, 600);
        
        
        /*
        
        Delete Record Scene
        
        */
        
        ArrayList<Speedrun> speedruns = new ArrayList<>();
        //creates a vbox
        VBox deleteSpeedrunVBox = new VBox(10);
        deleteSpeedrunVBox.setAlignment(Pos.TOP_LEFT);
        deleteSpeedrunVBox.setPadding(new Insets(30,30,30,30));
        
        //adds a label and combo box's to the vbox
        deleteSpeedrunVBox.getChildren().add(new Label("Speedrunning Records"));
        
        ComboBox<String> deleteGameCombo = new ComboBox<>();
        deleteGameCombo.setPromptText("Pick a Game");
        gameName = new ArrayList<>();
        numGames = gDAO.getGames();
        for(Game x : numGames)
        {
            gameName.add(x.getName());
        }
        for(String x : gameName)
        {
            deleteGameCombo.getItems().add(x);
        }
        
        ComboBox<String> deleteGameCategory = new ComboBox<>();
        deleteGameCategory.setPromptText("Pick a Category");
        categoryName = new ArrayList<>();
        numSpeedruns = sDAO.getSpeedruns();
        for(Speedrun x : numSpeedruns)
        {
            categoryName.add(x.getCategory());
        }
        for(String x : categoryName)
        {
            deleteGameCategory.getItems().add(x);
        }
        
        ComboBox<String> deleteRecordComboBox = new ComboBox<>();
        deleteRecordComboBox.setPromptText("Pick a Record");
        deleteRecordComboBox.setVisible(false);
        
        //adds buttons
        Button selectRecordsBtn = new Button("Select Game and Category");
        
        HBox deleteComboBoxHBox = new HBox(10);
        deleteComboBoxHBox.getChildren().addAll(deleteGameCombo, deleteGameCategory, selectRecordsBtn);
        
        deleteSpeedrunVBox.getChildren().add(deleteComboBoxHBox);

        deleteSpeedrunVBox.getChildren().add(deleteRecordComboBox);
        
        Button deleteRecordBtn = new Button("Delete Record");
        Button exitDeleteRecords = new Button("Exit");
        HBox recordDeleteBox = new HBox(10);
        recordDeleteBox.setAlignment(Pos.BOTTOM_RIGHT);
        recordDeleteBox.getChildren().addAll(deleteRecordBtn, exitDeleteRecords);
        deleteSpeedrunVBox.getChildren().add(recordDeleteBox);
        
        //gives the buttons functionality
        selectRecordsBtn.setOnAction(event ->
        {
            //uses the game and category from the combo boxes to find the corresponding speedruns from the backend through a dao, then adds them to a cmobo box and makes the combo box visible
            String gName = deleteGameCombo.getSelectionModel().getSelectedItem();
            String cName = deleteGameCategory.getSelectionModel().getSelectedItem();
            ArrayList<String> gameNameLambda = new ArrayList<>();
            ArrayList<Game> numGamesLambda = gDAO.getGames();
            for(Game x : numGamesLambda)
            {
                gameNameLambda.add(x.getName());
            }
            ArrayList<Speedrun> numSpeedrunsLambda = sDAO.getSpeedruns();
            for(String x : gameNameLambda)
            {
                for(Speedrun y : numSpeedrunsLambda)
                {
                    if(gName.equalsIgnoreCase(x) && cName.equalsIgnoreCase(y.getCategory()))
                    {
                        deleteRecordComboBox.getItems().add(y.getFormattedSpeedrun());
                        speedruns.add(y);
                    }
                }
            }
            deleteRecordComboBox.setVisible(true);
        });
        
        deleteRecordBtn.setOnAction(event ->
        {
            //passes the selected speedrun to a dao to delete it
            SelectionModel selectionModel = deleteRecordComboBox.getSelectionModel();
            if(sDAO.deleteSpeedrun(speedruns.get(selectionModel.getSelectedIndex())))
            {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Success");
                a.setHeaderText("Speedrun Record Successfully Deleted");
                a.showAndWait();
            }
            else
            {
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setTitle("Error");
                e.setHeaderText("Invalid Data, Record not Deleted");
                e.showAndWait();
                
            }
        });
        
        exitDeleteRecords.setOnAction(event ->
        {
            mainStage.setScene(speedrunRecords);
            mainStage.show();
        });
        
        //initializes the scene
        deleteRecord = new Scene (deleteSpeedrunVBox, 600, 400);
        
    }

    
    
    
    public static void main(String[] args) 
    {
        launch();
    }

}