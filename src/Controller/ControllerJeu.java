/**
 * Created by Chroon on 2016-11-08.
 */
package Controller;

import Model.Sol;
import Model.Vaisseau;
import View.SceneJeu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerJeu{
    private Timeline gaz,mouvementCollision;
    private Vaisseau vaisseau;
    private Sol sol;
    private SceneJeu jeu;
    private Scene perdu,gagne;
    private Stage stage;
    private Points points;

    public ControllerJeu(Vaisseau vaisseau, Sol sol, SceneJeu jeu,Stage leStage, Scene perdu, Scene gagne,Points points){
        this.jeu=jeu;
        this.vaisseau=vaisseau;
        this.sol=sol;
        stage=leStage;
        this.perdu=perdu;
        this.gagne=gagne;
        this.points=points;

        initialisationMouvementCollision();
    }

    public void setJeu(int planete, int diff, String nom){
        sol.genererPath(diff,planete);
        vaisseau.setNom(nom);
        vaisseau.setRotate(90);
        vaisseau.setVitesseIni(0);
        jeu.addElementJeu(vaisseau,sol.getPath());
        stage.setScene(jeu.getSceneJeu());

        points.resetPoints();
        points.startPoint();

        startMouvementCollision();

    }

    private void finJeu(){
        jeu.cleanup();
    }

    private void initialisationMouvementCollision(){
        mouvementCollision=new Timeline(new KeyFrame(
                Duration.millis(15), t->{
            vaisseau.accelerer(jeu.appuyerGaz());
            jeu.deplacement(vaisseau.getTranslateX()+vaisseau.getVitesseX(),
                    vaisseau.getTranslateY()-vaisseau.getVitesseY());
            if (jeu.appuyerGaz())
                jeu.setGaz(vaisseau.getEssence());

            //collision();
        }
        ));
        mouvementCollision.setCycleCount(Animation.INDEFINITE);
    }

    private void startMouvementCollision(){
        mouvementCollision.play();
    }

    private void stopMouvmentCollision(){
        mouvementCollision.stop();
    }

    private  void gagner(){
        stopMouvmentCollision();
        points.stopPoint();
        finJeu();
        stage.setScene(gagne);
    }

    private void perdre(){
        stopMouvmentCollision();
        points.stopPoint();
        finJeu();

        stage.setScene(perdu);
    }

    private void conditionVictoire(){
        if(vaisseau.getVitesseY()<=12&&vaisseau.getRotation()>80&&vaisseau.getRotation()<100)
            gagner();
        else
            perdre();
    }

    private void collision() {
        if (1==2){
            finJeu();
            conditionVictoire();
        }
    }
}