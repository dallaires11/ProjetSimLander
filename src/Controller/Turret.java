/**
 * Created by Chroon on 2016-12-01.
 */
package Controller;

import Interface.TCI;
import Model.Vaisseau;
import View.SceneJeu;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Turret {
    private Vaisseau vaisseau;
    private Circle cible;
    private Timeline ciblage;
    private SceneJeu sceneJeu;
    private TCI tci;
    private int diff;

    public Turret(Vaisseau vaisseau,SceneJeu jeu,TCI tci){
        this.vaisseau=vaisseau;
        sceneJeu=jeu;
        cible=new Circle(10);
        this.tci=tci;
        diff=1;

        initialiserTir();
        initialiserCiblage();

    }

    private void setImage(){
        cible.setFill(new ImagePattern(new Image("Image/cible.gif")));


    }

    private void initialiserTir(){
        FadeTransition ft= new FadeTransition(Duration.seconds(2/diff),cible);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setOnFinished(event->{
            initialiserCiblage();
        });
        ciblage=new Timeline(new KeyFrame(Duration.seconds(4),event ->{
            //sceneJeu.deplacementCible(vaisseau.getX(),vaisseau.getY());
            ft.playFromStart();
        }));

    }
    private void initialiserCiblage(){
        //SonChargement.play()
        PauseTransition chargement=new PauseTransition(/*charmentSon.getTotalDuration()*/);
        chargement.setOnFinished(event->{
            //Sonchargement.stop();
            touche();
        });

    }

    private void touche(){
        if(collisionTir());
            tci.perdre();

    }

    private boolean collisionTir(){
        if(vaisseau.getBoundsInLocal().intersects(cible.getBoundsInLocal()))
            return true;
        return false;
    }

    public void commencerAttaqueObirtal(int diff){
        this.diff=diff;
        ciblage.play();
    }

    public void finAttaqueOrbital(){
        ciblage.stop();

    }
}
