import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Prototype extends Application {

    Vaisseu vaisseu;
    Rectangle vesseau;
    boolean accelerationJoueur;
    int debPlat1;
    int debPlat2;
    int diff;
    final int precision;
    Vector<Integer> solH;
    Timeline gravite;

    public Prototype(){
        vaisseu=new Vaisseu();
        vesseau= new Rectangle(650,100,20,20);
        accelerationJoueur=false;
        solH= new Vector<>();
        debPlat1=(int)(Math.random()*40+60);
        debPlat2=(int)(Math.random()*22+110);
        diff=0;
        precision=10;
        //20* Easy3 med 2 hard 1.5


    }

    public Path creerSol(int diff){
        Vector<Integer> sol=new Vector();
        IntStream.iterate(0, i->i=(int)(Math.random()*250+1))
                .limit(140)
                .forEach(i->sol.add(i));
        Path ground = new Path();
        for(int x=0;x<140;x++){
            if (x==0)
                ground.getElements().add(new MoveTo(0,700-sol.get(x)));
            else if(x==debPlat1||x==debPlat2){
                int h= 700- sol.get(x);
                ground.getElements().addAll(new LineTo(x*precision,h));
                x+=diff;
                ground.getElements().addAll(new LineTo(x*precision,h));
                for(int z=0;z<diff;z++){
                    solH.add(h);
                }
            }
            else {
                ground.getElements().add(new LineTo((x*precision),700-(sol.get(x))));
                solH.add(700-(sol.get(x)));
            }
        }

        return ground;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Label vitesse = new Label("vitesseY :" + vaisseu.getVitesseY());
        Label position = new Label("Position :" + vesseau.getTranslateY());
        Label acY = new Label("AccY: "+(vaisseu.getAccelationy()-0.0098));
        Label rotation = new Label("Rotation:"+vaisseu.getRotation());
        Label hauteurVaisseau = new Label("");
        Label hauteurSol = new Label("");
        Label positionXVaisseau = new Label("");
        position.setTranslateY(10);
        acY.setTranslateY(20);
        rotation.setTranslateY(30);
        hauteurVaisseau.setTranslateY(40);
        positionXVaisseau.setTranslateY(50);
        hauteurSol.setTranslateY(60);
        Alert difficul = new Alert(Alert.AlertType.CONFIRMATION);
        difficul.setTitle("Difficulte");
        difficul.setContentText("Veulliez choisir une difficulté");


        ButtonType easy= new ButtonType("Facile");
        ButtonType medium= new ButtonType("Moyen");
        ButtonType hard = new ButtonType("Difficile");
        difficul.getButtonTypes().setAll(easy,medium,hard);
        Optional<ButtonType> result = difficul.showAndWait();
        if (result.get() == easy)
            diff=6;
        if (result.get() == medium)
            diff=3;
        if (result.get() == hard)
            diff=2;


        Path montagne = creerSol(diff);

        root.getChildren().addAll(vesseau,vitesse,position,acY,rotation,montagne,hauteurSol,hauteurVaisseau,positionXVaisseau);

        Scene cena = new Scene(root, 1400, 700);
        cena.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.Z)
                accelerationJoueur=true;
            else if(event.getCode()== KeyCode.LEFT){
                vaisseu.rotationDroite(vesseau);
            }
            else if(event.getCode()==KeyCode.RIGHT){
                vaisseu.rotationGauche(vesseau);
            }
        });
        cena.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.Z)
                accelerationJoueur=false;

        });
        gravite=new Timeline(new KeyFrame(
                Duration.millis(15), t->{
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
        }
        ));
        gravite.setCycleCount(Animation.INDEFINITE);
        gravite.play();

        primaryStage.setTitle("SimLander - Prototype");
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    boolean peutetre(){
        if((700-(vesseau.getTranslateY()+120))<=700-solH.get((int) (vesseau.getTranslateX()+650)/precision)||
                (700-(vesseau.getTranslateY()+120))<=700-solH.get(((int) (vesseau.getTranslateX()+650)/precision))+1||
                (700-(vesseau.getTranslateY()+120))<=700-solH.get((int) (vesseau.getTranslateX()+650)/precision)+2){
            System.out.println("collision");
            return  true;
        }
        return  false;
    }

    boolean conditionvictoire(){
        if(vaisseu.getVitesseY()<=5) {
            if (vaisseu.getRotation() > 80 && vaisseu.getRotation() < 100) {
                if ((vesseau.getTranslateX()+660 >= debPlat1*10 + 10 && vesseau.getTranslateX()+660 <= debPlat1*10 + (diff * 10) - 10) ||
                        (vesseau.getTranslateX()+660 >= debPlat2*10 + 10 && vesseau.getTranslateX()+660 <= debPlat2*10 + (diff * 10) - 10))
                    return true;
            }
        }
        return false;
    }

    void win(){
        Alert win = new Alert(Alert.AlertType.INFORMATION);
        win.setTitle("Victoire!!!");
        win.setContentText("Vous faites la fierte de votre mere");
        System.out.println("win");
        gravite.stop();
        win.showAndWait();
    }
    void loose(){
        Alert looser = new Alert(Alert.AlertType.INFORMATION);
        looser.setTitle("Please try again later             GG EZ");
        looser.setContentText("«Tu vas mourir seul»\n-Vincent Boily 2016");
        System.out.println("lose");
        gravite.stop();
        looser.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}