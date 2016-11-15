/**
 * Created by Chroon on 2016-11-15.
 */
package Controller;

import javafx.animation.Timeline;

public class Points {
    private Timeline point;
    private double points;

    public Points(){
        points=14000;
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
