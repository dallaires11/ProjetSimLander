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
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerJeu{
    private Timeline gaz,mouvementCollision;
    private Vaisseau vaisseau;
    private Sol sol;
    private SceneJeu jeu;

    public ControllerJeu(Vaisseau vaisseau, Sol sol, SceneJeu jeu){
        this.jeu=jeu;
        this.vaisseau=vaisseau;
        this.sol=sol;

        initialisationMouvementCollision();

    }

    public void setJeu(int planete, int diff,String nom,Stage stage){
        sol.genererPath(diff,planete);
        vaisseau.setNom(nom);
        jeu.addElementJeu(vaisseau,sol.getPath());
        //points.startPoint();
        //startMouvementCollision();
        stage.setScene(jeu.getSceneJeu());

        startMouvementCollision();

    }

    public void finJeu(){
        jeu.cleanup();
    }

    private void initialisationMouvementCollision(){
        mouvementCollision=new Timeline(new KeyFrame(
                Duration.millis(15), t->{
            vaisseau.accelerer(jeu.appuyerGaz());
            jeu.deplacement(vaisseau.getX(),vaisseau.getY());
        }
        ));
        mouvementCollision.setCycleCount(Animation.INDEFINITE);
        /*
            vaisseu.accelerer(accelerationJoueur);
            vesseau.setTranslateY(vesseau.getTranslateY()+vaisseu.getVitesseY());
            vesseau.setTranslateX(vesseau.getTranslateX()+vaisseu.getVitesseX());
            vitesse.setText("vitesse :" + vaisseu.getVitesseY());
            position.setText("Position :" + vesseau.getTranslateY());
            acY.setText("AccY: "+(vaisseu.getAccelationy()-0.0098));
            rotation.setText("Rotation:"+vaisseu.getRotation());
            hauteurVaisseau.setText("VaisseauY: "+(700-(vesseau.getTranslateY()+120)));
            hauteurSol.setText("Hauteur Sol: "+(700-solH.get(((int)(vesseau.getTranslateX()+650)/precision))));
            positionXVaisseau.setText("VaisseauX: "+((vesseau.getTranslateX()+650)/precision));
            if(peutetre())
                if(conditionvictoire()){

                    win();
                    primaryStage.close();
                }
                else{
                    loose();
                    primaryStage.close();
                }
        }*/
    }

    public void startMouvementCollision(){
        mouvementCollision.play();
    }
    public void stopMouvmentCollision(){
        mouvementCollision.stop();
    }
}