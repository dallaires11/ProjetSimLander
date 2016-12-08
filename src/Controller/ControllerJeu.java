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
    private TempeteSolaire tempete;

    public ControllerJeu(Vaisseau vaisseau, Sol sol, SceneJeu jeu,Stage leStage, Perdre perdu, Gagner gagne,
                         Points points,Turret turret,TempeteSolaire tempete){
        this.jeu=jeu;
        this.vaisseau=vaisseau;
        this.sol=sol;
        stage=leStage;
        perdre=perdu;
        this.gagne=gagne;
        this.points=points;
        this.turret=turret;
        this.tempete=tempete;

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
        tempete.stopTempete();
        points.stopPoint();
        finJeu();
        stage.setScene(gagne.getScene());
    }

    public void perdre(){
        stopMouvmentCollision();
        turret.finAttaqueOrbital();
        tempete.stopTempete();
        points.stopPoint();
        finJeu();

        stage.setScene(perdre.getScene());
    }

    private void conditionVictoire(){
        if(vaisseau.getX() >= sol.getPlat1()*10 - 3 && (vaisseau.getX()+20) <= sol.getPlat1() * 10 + (60/diff) + 3) {
            if (vaisseau.getVitesseY() <= 2 && vaisseau.getRotation() > 80 && vaisseau.getRotation() < 100)
                gagner();
            else
                perdre();
        } else if (vaisseau.getX() >= sol.getPlat2() * 10 - 3 && (vaisseau.getX()+20) <= sol.getPlat2() * 10 + 3 + (60/diff)){
            if (vaisseau.getVitesseY() <= 2 && vaisseau.getRotation() > 80 && vaisseau.getRotation() < 100)
                gagner();
            else
                perdre();
        } else
            perdre();
    }

    private void collision() {

        double valeurPrecise1;
        if(Math.round(vaisseau.getX()/10) >= 140||Math.round(vaisseau.getX()/10)<0){
            valeurPrecise1 = sol.getSolValeurs().get(140);
        }
        else
            valeurPrecise1 = (sol.getSolValeurs().get((int) Math.round(vaisseau.getX()/10))
                    + (((sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 1)) - sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 1))) / 10)));

        double valeurPrecise2;

        if(((Math.round(vaisseau.getX()/10) + 2)) > 140||Math.round(vaisseau.getX()/10)<0)

             valeurPrecise2 = valeurPrecise1;
        else
        valeurPrecise2 = (sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 2))
                + (((sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 1)) - sol.getSolValeurs().get((int) (Math.round(vaisseau.getX()/10) + 2))) / 10)));


        if (vaisseau.getY() >= valeurPrecise1 || vaisseau.getY() >= valeurPrecise2){
            System.out.println("<----------------------->");
            System.out.println("X : " + vaisseau.getX());
            System.out.println("X2 : " + (vaisseau.getX() + 20));
            System.out.println("Y : " + vaisseau.getY());
            System.out.println("rotation : " + vaisseau.getRotation());
            System.out.println("Vitesse : " + vaisseau.getVitesseY());
            System.out.println("Sol 1 : " + valeurPrecise1);
            System.out.println("sol 2 : " + valeurPrecise2);
            System.out.println("plat 1 : " + sol.getPlat1());
            System.out.println("plat 2 : " + sol.getPlat2());
            System.out.println("<----------------------->");
            System.out.println();

            conditionVictoire();
        }
    }

    private void chargementOption(){
        if(optChoisi.asaultOrbital()){
            turret.setTir(diff);
            turret.commencerAttaqueObirtal(diff);
        }

        if (optChoisi.tempete()){
            tempete.startTempete();
        }

        if (optChoisi.cheatEssence()){
            vaisseau.essenceInfini();
        }
    }
}