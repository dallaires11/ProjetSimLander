/**
 * Created by Chroon on 2016-11-10.
 */
package View;

import Controller.ControllerJeu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class Choix {
    private MediaPlayer illu;
    private Scene choix;
    private Group root;
    private VBox org,optionVaisseau,ensPlan1,ensPlan2,ensPlan3,ensPlan4;
    private HBox planete,difficulte;
    private ImageView planete1,planete2,planete3,planete4,fond,easter;
    private TextField nomVaisseau;
    private Text texte,terre,mars,lune,kaurava;
    private int choixPLanete;
    private int choixDifficulte;
    private Button facile,medium,difficile,pret;
    private ControllerJeu controller;

    public Choix(Stage stage,ControllerJeu controller){
        illu=new MediaPlayer(new Media(new File("src/Sound/XFiles.mp3").toURI().toString()));

        choixPLanete=1;
        choixDifficulte=1;

        facile=new Button("Facile");
        medium=new Button("Moyen");
        difficile=new Button("Difficile");
        pret=new Button("Prêt");

        fond=new ImageView("Image/cielEtoile.gif");
        easter=new ImageView("Image/Easter.png");
        planete1=new ImageView("Image/Earth.png");
        planete2=new ImageView("Image/Mars.png");
        planete3=new ImageView("Image/Moon.png");
        planete4=new ImageView("Image/Kaurava.png");

        texte = new Text("Choisissez un nom pour votre vaisseau");
        nomVaisseau=new TextField();

        terre= new Text("Terre");
        mars=new Text("Mars");
        lune=new Text("Lune");
        kaurava=new Text("Kaurava");

        //Changer ImageView planete pour des button avec imageview ou queque chose de semblable
        ensPlan1=new VBox();
        ensPlan1.getChildren().addAll(planete1,terre);
        ensPlan2=new VBox();
        ensPlan2.getChildren().addAll(planete2,mars);
        ensPlan3=new VBox();
        ensPlan3.getChildren().addAll(planete3,lune);
        ensPlan4=new VBox();
        ensPlan4.getChildren().addAll(planete4,kaurava);

        planete=new HBox();
        planete.getChildren().addAll(ensPlan1,ensPlan2,ensPlan3,ensPlan4);
        difficulte=new HBox();
        difficulte.getChildren().addAll(facile,medium,difficile);

        optionVaisseau=new VBox();
        optionVaisseau.getChildren().addAll(texte,nomVaisseau);

        org =new VBox();
        org.getChildren().addAll(planete,difficulte,optionVaisseau,pret);

        root= new Group();
        root.getChildren().addAll(fond,org,easter);

        this.controller=controller;

        setPosition(stage);
        setAction(stage);
        setTexte();

        choix=new Scene(root,1400,700);

    }

    private void  setPosition(Stage stage){
        planete.setSpacing(75);
        planete.setAlignment(Pos.CENTER);
        difficulte.setSpacing(10);
        difficulte.setAlignment(Pos.CENTER);
        org.setAlignment(Pos.CENTER);
        org.setSpacing(100);
        org.setPadding(new Insets(20,30,20,45));
        easter.setTranslateX(20);
        easter.setTranslateY(220);
        easter.setScaleX(0.01);
        easter.setScaleY(0.01);
        fond.fitHeightProperty().bind(stage.heightProperty());
        fond.fitWidthProperty().bind(stage.widthProperty());
    }

    private void setAction(Stage leStage){
        planete1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            ChangeCouleurPresente(choixPLanete);
            choixPLanete=1;
            terre.setStroke(Color.GREEN);
        });
        planete2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            ChangeCouleurPresente(choixPLanete);
            choixPLanete=2;
            mars.setStroke(Color.GREEN);
        });
        planete3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            ChangeCouleurPresente(choixPLanete);
            choixPLanete=3;
            lune.setStroke(Color.GREEN);
        });
        planete4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            ChangeCouleurPresente(choixPLanete);
            choixPLanete=4;
            kaurava.setStroke(Color.GREEN);
        });
        facile.setOnAction(event -> choixDifficulte=1);
        medium.setOnAction(event -> choixDifficulte=2);
        difficile.setOnAction(event -> choixDifficulte=3);
        pret.setOnAction(event -> {
            controller.setJeu(choixPLanete,choixDifficulte,nomVaisseau.getText());
            //pop up controle
        });

        easter.setOnMouseEntered(event -> {
            illu.play();
            System.out.println("joeur");
        });

        easter.setOnMouseExited(event -> illu.stop());

    }

    private void setTexte(){
        terre.setStroke(Color.GREEN);
        mars.setStroke(Color.YELLOW);
        lune.setStroke(Color.YELLOW);
        kaurava.setStroke(Color.YELLOW);
        texte.setStroke(Color.WHITE);
        nomVaisseau.setPromptText("Nom de votre vaisseau");

    }

    private void ChangeCouleurPresente(int x){
        switch (x){
            case 1:
                terre.setStroke(Color.YELLOW);
                break;
            case 2:
                mars.setStroke(Color.YELLOW);
                break;
            case 3:
                lune.setStroke(Color.YELLOW);
                break;
            case 4:
                kaurava.setStroke(Color.YELLOW);
        }
    }

    public Scene getSceneChoix(){
        return choix;
    }



}