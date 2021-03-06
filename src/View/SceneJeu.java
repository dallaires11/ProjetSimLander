/**
 * Created by Chroon on 2016-11-10.
 */
package View;

import Model.Vaisseau;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class SceneJeu {
    private Scene jeu;
    private boolean accelerationJoueur;
    private Group root;
    private Vaisseau vaisseau;
    private Path sol;
    private ImageView fond,alerte;
    private Label points,gaz;

    public SceneJeu(Stage stage){
        accelerationJoueur=false;
        vaisseau=null;
        sol=null;
        fond=new ImageView("Image/fondJeu.jpg");
        alerte=new ImageView("Image/alert.png");
        points=new Label("Points: 14000");
        gaz=new Label("Gaz: 1000L");

        setText();
        setPosition(stage);
        setImage();

        root=new Group();
        root.getChildren().addAll(fond,gaz,points);

        jeu=new Scene(root,1400,700);

        jeu.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.UP)
                accelerationJoueur=true;
            if(event.getCode()== KeyCode.LEFT) {
                vaisseau.rotationGauche(5);
            }
            if(event.getCode()== KeyCode.RIGHT) {
                vaisseau.rotationDroite(5);
            }
        });
        jeu.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.UP)
                accelerationJoueur=false;

        });
    }


    public Scene getSceneJeu(){
        return jeu;
    }

    public void addElementJeu(Vaisseau vaisseau,Path sol){
        this.vaisseau=vaisseau;
        this.sol=sol;
        setPositionInitial();
        root.getChildren().addAll(this.vaisseau,this.sol);
    }

    public void cleanup(){
        root.getChildren().removeAll(vaisseau,sol);
        vaisseau=null;
        sol=null;
        accelerationJoueur=false;
        points.setText("Points: 14000");
        gaz.setText("Gaz: 1000");
    }

    private void setPosition(Stage stage){
        fond.fitHeightProperty().bind(stage.heightProperty());
        fond.fitWidthProperty().bind(stage.widthProperty());
        gaz.setTranslateY(20);
        gaz.setTranslateX(10);
        points.setTranslateY(10);
        points.setTranslateX(10);
    }

    private void setPositionInitial(){
        vaisseau.setTranslateX(690-vaisseau.getNomTailleMod());
        vaisseau.setTranslateY(80);
        vaisseau.setPosInitial(690,80);
    }

    private void setImage(){
        alerte.setFitHeight(50);
        alerte.setFitWidth(50);
        alerte.setTranslateY(250);
        alerte.setTranslateX(1500);
    }

    public boolean appuyerGaz(){
        return accelerationJoueur;
    }

    public void deplacement(double x,double y){
        vaisseau.setTranslateX(x);
        vaisseau.setTranslateY(y);
    }

    public void setPoints(int nb){
        points.setText("Points: "+nb);
    }

    public void setGaz(int qt){
        gaz.setText("Gaz: "+qt+"L");
    }

    private void setText(){
        gaz.setTextFill(Color.WHITE);
        points.setTextFill(Color.WHITE);
    }

    public void activerCible(Circle cible){
        root.getChildren().add(cible);
        root.getChildren().get(5).setTranslateX(-100);
        root.getChildren().get(5).setTranslateY(-100);

    }

    public void desactiverCible(Circle cible){
        root.getChildren().remove(cible);
    }

    public void deplacerCible(int x, int y){
        root.getChildren().get(5).setTranslateX(x);
        root.getChildren().get(5).setTranslateY(y);
    }

    public Group getRoot(){
        return root;
    }

    public void deplacerAlerte(int x){
        alerte.setTranslateX(x);
    }

    public ImageView getAlerte(){
        return alerte;
    }
}