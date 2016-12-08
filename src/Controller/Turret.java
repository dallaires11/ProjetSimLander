/**
 * Created by Chroon on 2016-12-01.
 */
package Controller;

import Interface.TCI;
import Model.Vaisseau;
import View.SceneJeu;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;

public class Turret {
    private Vaisseau vaisseau;
    private Circle cible;
    private Timeline ciblage;
    private SceneJeu sceneJeu;
    private TCI tci;
    private int diff;
    private MediaPlayer sonTir,tir1,tir2,tir3;
    private PauseTransition chargement;

    public Turret(Vaisseau vaisseau,SceneJeu jeu){
        this.vaisseau=vaisseau;
        sceneJeu=jeu;
        cible=new Circle(50);
        diff=1;

        tir1=new MediaPlayer(new Media(new File("src/Sound/sonTirEasy.mp3").toURI().toString()));
        tir2=new MediaPlayer(new Media(new File("src/Sound/sonTirMedium.mp3").toURI().toString()));
        tir3=new MediaPlayer(new Media(new File("src/Sound/sonTirHard.mp3").toURI().toString()));

        setImage();

        initialiserCiblage();
        initialiserTir();
    }

    private void setImage(){
        cible.setFill(new ImagePattern(new Image("Image/cible.gif")));


    }

    private void initialiserCiblage(){
        FadeTransition ftStart = new FadeTransition(Duration.seconds(0.5),cible);
        ftStart.setFromValue(0);
        ftStart.setToValue(1);
        ftStart.setOnFinished(event-> tir());
        ciblage=new Timeline(new KeyFrame(Duration.seconds(4),event ->{
            sceneJeu.deplacerCible((int)vaisseau.getX(),(int)vaisseau.getY());
            ftStart.playFromStart();
        }));
        ciblage.setCycleCount(Animation.INDEFINITE);

    }
    private void initialiserTir(){
        FadeTransition ftEnd = new FadeTransition(Duration.seconds(0.5),cible);
        ftEnd.setFromValue(1);
        ftEnd.setToValue(0);
        ftEnd.setOnFinished(event-> sonTir.stop());

        chargement=new PauseTransition(Duration.seconds(2/diff));
        chargement.setOnFinished(event->{
            touche();
            ftEnd.playFromStart();

        });
    }

    private void tir(){
        sonTir.play();
        chargement.playFromStart();
    }

    private void touche(){
        if(collisionTir()) {
            System.out.println("Touche");
            tci.perdre();
        }
    }

    private boolean collisionTir(){
        if(vaisseau.getBoundsInParent().intersects(cible.getBoundsInParent()))
            return true;
        return false;
    }

    public void commencerAttaqueObirtal(int diff){
        this.diff=diff;
        sceneJeu.activerCible(cible);
        ciblage.play();
    }

    public void finAttaqueOrbital(){
        sceneJeu.desactiverCible(cible);
        ciblage.stop();
    }

    public void setInterfacePerdre(TCI tci){
        this.tci=tci;
    }

    public void setTir(int diff){
        switch (diff){
            case 1:
                sonTir=tir1;
                break;
            case 2:
                sonTir=tir2;
                break;
            case 3:
                sonTir=tir3;
                break;
            default:
                sonTir=null;
                break;
        }
    }
}
