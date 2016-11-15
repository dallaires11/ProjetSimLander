/**
 * Created by Chroon on 2016-11-08.
 */
package Controller;

import Model.Sol;
import Model.Vaisseau;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerJeu{
    private Timeline gaz,mouvementCollision;
    private Vaisseau vaisseau;
    private Scene jeu;

    public ControllerJeu(){

    }

    public void setJeu(int planete, int diff, Points points, Sol sol,String nom,Vaisseau vaisseau,Stage stage){
        sol.genererPath(diff,planete);
        vaisseau.setNom(nom);
        //points.startPoint();
        //startMouvementCollision();

    }

    public void startMouvementCollision(){
        mouvementCollision.play();
    }
    public void stopMouvmentCollision(){
        mouvementCollision.stop();
    }
}