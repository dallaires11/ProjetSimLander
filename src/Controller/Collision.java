/**
 * Created by Chroon on 2016-11-08.
 */
package Controller;

import Model.Vaisseau;
import javafx.animation.Timeline;

import java.util.Vector;

public class Collision {
    private Timeline collision;
    private Vaisseau vaisseau;
    private Vector<Integer> sol;

    public Collision(Vaisseau vaisseau,Vector sol){
        this.vaisseau=vaisseau;
        this.sol=sol;

        collision= new Timeline();
    }

    public void startCollision(){
        collision.play();
    }

    public void stopCollision(){
        collision.stop();
    }

}