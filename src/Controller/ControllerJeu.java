/**
 * Created by Chroon on 2016-11-08.
 */
package Controller;

import Model.Sol;
import Model.Vaisseau;
import View.SceneJeu;
import javafx.animation.Timeline;
import javafx.stage.Stage;

public class ControllerJeu{
    private Timeline gaz,mouvementCollision;
    private Vaisseau vaisseau;
    private Sol sol;
    private SceneJeu jeu;

    public ControllerJeu(Vaisseau vaisseau, Sol sol, SceneJeu jeu){
        this.jeu=jeu;
        this.vaisseau=vaisseau;
        this.sol=sol;

    }

    public void setJeu(int planete, int diff,String nom,Stage stage){
        sol.genererPath(diff,planete);
        vaisseau.setNom(nom);
        jeu.addElementJeu(vaisseau,sol.getPath());
        //points.startPoint();
        //startMouvementCollision();
        stage.setScene(jeu.getSceneJeu());

    }

    public void finJeu(){
        jeu.cleanup();
    }

    public void startMouvementCollision(){
        mouvementCollision.play();
    }
    public void stopMouvmentCollision(){
        mouvementCollision.stop();
    }
}