/**
 * Created by Chroon on 2016-11-15.
 */
package Controller;

import View.SceneJeu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Points {
    private Timeline point;
    private int points;
    private SceneJeu jeu;

    public Points(SceneJeu jeu) {
        points = 14000;

        point = new Timeline(new KeyFrame(
                Duration.millis(1000), t -> {
            points-=100;
            jeu.setPoints(points);
        }
        ));
        point.setCycleCount(Animation.INDEFINITE);
    }

    public void startPoint(){
        point.play();
    }

    public void stopPoint(){
        point.stop();
    }

    public double getPoints(){
        return points;
    }

    public void resetPoints(){
        points=14000;
    }
}
