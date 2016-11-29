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
    private int diff;
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
        this.diff = diff;
        sol.genererPath(diff,planete);
        vaisseau.setNom(nom);
        vaisseau.setRotate(0);
        vaisseau.setVitesseIni(0);
        vaisseau.setEssence(1000);
        vaisseau.setRotationIni();


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

            collision();
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
        if(vaisseau.getX()/10 >= sol.getPlat1() && ((vaisseau.getX()+20)/10) <= sol.getPlat1() + (6/diff)) {
            if (vaisseau.getVitesseY() <= 2 && vaisseau.getRotation() > 80 && vaisseau.getRotation() < 100)
                gagner();
            else
                perdre();
        } else if (vaisseau.getX()/10 >= sol.getPlat2() && ((vaisseau.getX()+20)/10) <= sol.getPlat2() + (6/diff)){
            if (vaisseau.getVitesseY() <= 2 && vaisseau.getRotation() > 80 && vaisseau.getRotation() < 100)
                gagner();
            else
                perdre();
        } else
            perdre();
    }

    private void collision() {
        double valeurPrecise1 = (sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10)))
                + (((sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 1)) - sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10)))) / 10)
                * (((vaisseau.getX() / 10) - Math.round(vaisseau.getX()/10)) * 10)));

        double valeurPrecise2 = (sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 2))
                + (((sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 1)) - sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 2))) / 10)
                * (((vaisseau.getX() / 10) - Math.round(vaisseau.getX())/10) * 10)));

        if (vaisseau.getY() >= valeurPrecise1 || vaisseau.getY() >= valeurPrecise2){
            System.out.println("X : " + vaisseau.getX());
            System.out.println("X arrondi : " + (int) (Math.round(vaisseau.getX()/10)));
            System.out.println("X2 : " + (vaisseau.getX() + 20));
            System.out.println("X2 arrondi : " + (int) (Math.round(vaisseau.getX()/10) + 2));
            System.out.println("Y : " + vaisseau.getY());
            System.out.println("rotation : " + vaisseau.getRotation());
            System.out.println("Vitesse : " + vaisseau.getVitesseY());
            System.out.println("Sol 1 : " + valeurPrecise1);
            System.out.println("sol 2 : " + valeurPrecise2);
            System.out.println("plat 1 : " + sol.getPlat1());
            System.out.println("plat 2 : " + sol.getPlat2());
            conditionVictoire();
        }
    }
}