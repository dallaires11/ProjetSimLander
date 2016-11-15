/**
 * Created by Chroon on 2016-11-08.
 */
package Controller;

import Model.Vaisseau;
import javafx.animation.Timeline;
import javafx.scene.Scene;

public class ControllerJeu{
    private Timeline gaz,mouvement;
    private Vaisseau vaisseau;
    private Scene jeu;

    public ControllerJeu(){

    }

    public void setJeu(int planete,int diff){

    }

    public void startPerteEssence(){
        gaz.play();
    }
    public void stopPerteEssence(){
        gaz.stop();
    }
    public void startMouvement(){
        mouvement.play();
    }
    public void stopMouvment(){
        mouvement.stop();
    }
}