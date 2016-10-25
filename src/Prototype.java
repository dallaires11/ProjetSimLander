import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;

public class Prototype extends Application {

    Vaisseu vaisseu = new Vaisseu();
    Rectangle vesseau = new Rectangle(650,100,20,20);
    boolean accelerationJoueur=false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Label vitesse = new Label("vitesseY :" + vaisseu.getVitesseY());
        Label position = new Label("Position :" + vesseau.getTranslateY());
        Label acY = new Label("AccY: "+(vaisseu.getAccelationy()-0.0098));
        Label rotation = new Label("Rotation:"+vaisseu.getRotation());
        position.setTranslateY(10);
        acY.setTranslateY(20);
        rotation.setTranslateY(30);


        root.getChildren().addAll(vesseau,vitesse,position,acY,rotation);

        Scene cena = new Scene(root, 1400, 700);
        cena.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.SPACE)
                accelerationJoueur=true;
            else if(event.getCode()== KeyCode.LEFT){
                vaisseu.rotationGauche();
            }
            else if(event.getCode()==KeyCode.RIGHT){
                vaisseu.rotationDroite();
            }
        });
        cena.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.SPACE)
                accelerationJoueur=false;

        });
        Timeline gravite=new Timeline(new KeyFrame(
                Duration.millis(15), t->{
            vaisseu.accelerer(accelerationJoueur);
            vesseau.setTranslateY(vesseau.getTranslateY()+vaisseu.getVitesseY());
            //vesseau.setTranslateX(vesseau.getTranslateX()+vaisseu.getVitesseX());
            vitesse.setText("vitesse :" + vaisseu.getVitesseY());
            position.setText("Position :" + vesseau.getTranslateY());
            acY.setText("AccY: "+(vaisseu.getAccelationy()-0.0098));
            rotation.setText("Rotation:"+vaisseu.getRotation());
            //System.out.println((int)(vaisseu.getVitesse()));
        }
        ));
        gravite.setCycleCount(Animation.INDEFINITE);
        gravite.play();

        primaryStage.setTitle("SimLander - Prototype");
        primaryStage.setScene(cena);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}