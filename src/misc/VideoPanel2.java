package misc;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

/**
 * VideoPanel v2.0
 * JFXPanel initialization and video playback class
 * @Author - Bryce Hill
 */
public class VideoPanel2 {

    /**
     *
     * @param panel - a JFX panel that needs to be populated
     * @param filepath - what video to use for playback
     * @param videoWidth - width of video
     * @param videoHeight - height of video
     * @param rotation - rotation of video
     * @param videoX - X location
     * @param videoY - Y location
     */
    public Scene VP2;
    public VideoPanel2(String filepath,int videoWidth,int videoHeight,int rotation,int videoX,int videoY)
    {
        Group root = new Group();
        Scene scene = new Scene(root);
        MediaPlayer med = playMedia(filepath);
        med.setAutoPlay(true);
        med.setCycleCount(1);
        MediaView view = new MediaView(med);
        view.setFitHeight(videoWidth);
        view.setFitWidth(videoHeight);
        view.setX(videoX);
        view.setY(videoY);
        view.setRotate(rotation);

        ((Group) scene.getRoot()).getChildren().add(view);
        VP2 = scene;
    }

    /**
     * plays media
     * @param filepath
     * @return
     */
    public MediaPlayer playMedia(String filepath)
    {
        File ff = new File(filepath);
        Media m = new Media(ff.toURI().toString());
        MediaPlayer med = new MediaPlayer(m);
        return med;
    }
}
