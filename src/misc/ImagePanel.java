/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import game.player.GameMap;
import game.player.Player;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Fabian
 */
public class ImagePanel {
    
    public ImagePanel()
    {
      
      //Creating the image view
      ImageView imageView = new ImageView();
      //Setting image to the image view
      imageView.setImage(getImage("src\\images\\FirstFloor_Reference.png"));
      //Setting the image view parameters
      imageView.setX(10);
      imageView.setY(10);
      imageView.setFitWidth(575);
      imageView.setPreserveRatio(true);
      //Setting the Scene object
      Group root = new Group(imageView);
      Scene scene = new Scene(root, 595, 370);
//      stage.setTitle("Displaying Image");
//      stage.setScene(scene);
//      stage.show()
     }
    /**
     *
     * @param panel - a JFX panel that needs to be populated
     * @param filepath - what image to use for playback
     * @param imageWidth - width of image
     * @param imageHeight - height of image
     * @param rotation - rotation of image
     * @param imageX - X location
     * @param imageY - Y location+
     */
    public Scene image;
    public ImagePanel(ArrayList<ImageView> imageViews)
    {
        Player me;
        try
        {
            me = new Player("demo", "1234", "dest", "station", "golbin"); // creates a player to reference the map
            GameMap map =  new GameMap(me.getAvailableFFStations(), me.getAvailableSFStations(), 
                    me.getStation(), me.getDestination(), me.getTimeline()); // initiallizes the map corresponding to the player class
        }
        catch (FileNotFoundException e)
        {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, e);
        }
        Group root = new Group(); // creates a group of pixels 
        Scene scene = new Scene(root);//declasres a scene ont he group of pixels
        for(ImageView view : imageViews)
        {
            ((Group) scene.getRoot()).getChildren().add(view); // add the scenes to the group of pixels
        }
        image = scene;
    }
    public ImagePanel( String filepath1, String filepath2, int imageWidth, int imageHeight, int rotation, int imageX,
                       int imageY ) throws FileNotFoundException {
//        panel.setLayout(null);
        Player me = new Player("demo", "1234", "dest", "station", "golbin"); // creates a player to reference the map
        GameMap map =  new GameMap(me.getAvailableFFStations(), me.getAvailableSFStations(), 
                me.getStation(), me.getDestination(), me.getTimeline()); // initiallizes the map corresponding tot he plaeyr class
        Group root = new Group(); // creates a group of pixels 
        Scene scene = new Scene(root);//declasres a scene ont he group of pixels
        
        ImageView view = new ImageView(); // an image you can se on a scene
        view.setImage(map.plantFFStations(getImage(filepath1), me.getAvailableFFStations())); // call image from the map for the FFstations
        view.setFitHeight(imageWidth);
        view.setFitWidth(imageHeight);
        view.setX(imageX); // coordinates of images
        view.setY(imageY); // coordinates of images
        view.setRotate(rotation);
////////////////////////////////////////////////////////////////////////////////
        /*repeat this process for both maps*/
        ImageView view2 = new ImageView();
        view2.setImage(map.plantSFStations(getImage(filepath2), me.getAvailableSFStations()));
        view2.setFitHeight(imageWidth);
        view2.setFitWidth(imageHeight);
        view2.setX(imageX+400);
        view2.setY(imageY);
        view2.setRotate(rotation);

        ((Group) scene.getRoot()).getChildren().add(view); // add the scenes to the group of pixels
        ((Group) scene.getRoot()).getChildren().add(view2);
        image = scene; // declare the scene as a single image
//        panel.setScene(scene);
    }

    /**
     * plays media
     * @param filepath
     * @return
     */
    public static Image getImage(String filepath) {
        InputStream stream=null;
    try{stream = new FileInputStream(filepath);} catch (FileNotFoundException e){}
      Image image = new Image(stream);
        return image;
    }

    /**
     * plays media
     * @param filepath
     * @return
     */
    public static ImageView createImageView(String filepath,int imgWidth,int imgHeight,int x,int y,int rotation,GameMap map)
    {
        InputStream stream=null;
        try{stream = new FileInputStream(filepath);} catch (FileNotFoundException e){}
        ImageView imageView = new ImageView();
        if(filepath.contains("FirstFloor"))
        	imageView.setImage(map.plantFFStations(getImage(filepath), map.availableFFStations));
        else if(filepath.contains("SecondFloor"))
        	imageView.setImage(map.plantSFStations(getImage(filepath), map.availableSFStations));
        else
        	imageView.setImage(getImage(filepath));
        imageView.setFitHeight(imgWidth);
        imageView.setFitWidth(imgHeight);
        imageView.setX(x); // coordinates of images
        imageView.setY(y); // coordinates of images
        imageView.setRotate(rotation);
        return imageView;
    }
}