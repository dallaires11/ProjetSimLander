/**
 * Created by Chroon on 2016-11-08.
 */
package Controller;

import Interface.OptionChoisi;
import Interface.TCI;
import Model.Sol;
import Model.Vaisseau;
import View.Gagner;
import View.Perdre;
import View.SceneJeu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerJeu implements TCI{
    private Timeline mouvementCollision;
    private Vaisseau vaisseau;
    private Sol sol;
    private SceneJeu jeu;
    private Gagner gagne;
    private Perdre perdre;
    private Stage stage;
    private int diff;
    private Points points;
    private OptionChoisi optChoisi;
    private Turret turret;

    public ControllerJeu(Vaisseau vaisseau, Sol sol, SceneJeu jeu,Stage leStage, Perdre perdu, Gagner gagne,
                         Points points,Turret turret){
        this.jeu=jeu;
        this.vaisseau=vaisseau;
        this.sol=sol;
        stage=leStage;
        perdre=perdu;
        this.gagne=gagne;
        this.points=points;
        this.turret=turret;

        turret.setInterfacePerdre(this);

        initialisationMouvementCollision();
    }

    public void setInterfaceChoix(OptionChoisi choix){
        optChoisi=choix;
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

        chargementOption();

    }

    private void finJeu(){
        jeu.cleanup();
        sol.cleanup();
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
        turret.finAttaqueOrbital();
        points.stopPoint();
        finJeu();
        stage.setScene(gagne.getScene());
    }

    public void perdre(){
        stopMouvmentCollision();
        turret.finAttaqueOrbital();
        points.stopPoint();
        finJeu();

        stage.setScene(perdre.getScene());
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
<<<<<<< Updated upstream
=======
        double valeurPrecise1;
        double valeurPrecise2;

        valeurPrecise1 = (sol.getSolValeurs().get((int) (Math.round(vaisseau.getX() / 10)))
                    + (((sol.getSolValeurs().get((int) (Math.round(vaisseau.getX() / 10) + 1)) - sol.getSolValeurs().get((int) (Math.round(vaisseau.getX() / 10)))) / 10)
                    * (((vaisseau.getX() / 10) - Math.round(vaisseau.getX() / 10)) * 10)));
>>>>>>> Stashed changes

        double valeurPrecise1;
        if(Math.round(vaisseau.getX()/10) < 140){
            valeurPrecise1 = (sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10)))
                    * (((vaisseau.getX() / 10) - Math.round(vaisseau.getX()/10)) * 10));
        }
        else
            valeurPrecise1 = sol.getSolValeurs().get(140);
        

<<<<<<< Updated upstream
        double valeurPrecise2;
         if(((Math.round(vaisseau.getX()/10) + 2)) > 140)
             valeurPrecise2 = valeurPrecise1;
        else
        valeurPrecise2 = (sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 2))
                + (((sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 1)) - sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 2))) / 10)
=======
>>>>>>> Stashed changes

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

    private void chargementOption(){
        if(optChoisi.asaultOrbital()){
            turret.commencerAttaqueObirtal(diff);
        }

        if (optChoisi.chaos()){
            //Mode de jeu op
        }

        if (optChoisi.cheatEssence()){
            vaisseau.essenceInfini();
        }
    }
}