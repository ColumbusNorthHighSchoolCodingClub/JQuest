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
import game.player.Map;
import game.player.Player;

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
     * @param imageY - Y location
     */
    public Scene image;
    public ImagePanel(/*JFXPanel panel,*/ String filepath1, String filepath2, int imageWidth, int imageHeight, int rotation, int imageX,
                       int imageY ) throws FileNotFoundException {
//        panel.setLayout(null);
        Player me = new Player("demo", "1234", "dest", "station");
        Map map =  new Map(me.getAvailableFFStations(), me.getAvailableSFStations(), me.getStation(), me.getDestination(), me.getTimeline());
        Group root = new Group();
        Scene scene = new Scene(root);
        
        ImageView view = new ImageView();
        view.setImage(map.plantFFStations(getImage(filepath1), me.getAvailableFFStations()));
        view.setFitHeight(imageWidth);
        view.setFitWidth(imageHeight);
        view.setX(imageX);
        view.setY(imageY);
        view.setRotate(rotation);
////////////////////////////////////////////////////////////////////////////////
        ImageView view2 = new ImageView();
        view2.setImage(map.plantSFStations(getImage(filepath2), me.getAvailableSFStations()));
        view2.setFitHeight(imageWidth);
        view2.setFitWidth(imageHeight);
        view2.setX(imageX+400);
        view2.setY(imageY);
        view2.setRotate(rotation);

        ((Group) scene.getRoot()).getChildren().add(view);
        ((Group) scene.getRoot()).getChildren().add(view2);
        image = scene;
//        panel.setScene(scene);
    }

    /**
     * plays media
     * @param filepath
     * @return
     */
    public Image getImage(String filepath) {
        InputStream stream=null;
    try{stream = new FileInputStream(filepath);} catch (FileNotFoundException e){}
      Image image = new Image(stream);
        return image;
    }
    
}