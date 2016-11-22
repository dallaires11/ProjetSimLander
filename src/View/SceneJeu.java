/**
 * Created by Chroon on 2016-11-10.
 */
package View;

import Model.Vaisseau;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class SceneJeu {
    private Scene jeu;
    private boolean accelerationJoueur;
    private Group root;
    private Vaisseau vaisseau;
    private Path sol;
    private ImageView fond;

    public SceneJeu(Stage stage){
        accelerationJoueur=false;
        vaisseau=null;
        sol=null;
        fond=new ImageView("Image/fondJeu.jpg");

        setPosition(stage);

        root=new Group();
        root.getChildren().add(fond);

        jeu=new Scene(root,1400,700);

        jeu.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.UP)
                accelerationJoueur=true;
            if(event.getCode()== KeyCode.LEFT) {
                vaisseau.rotationGauche(10);
            }
            if(event.getCode()== KeyCode.RIGHT) {
                vaisseau.rotationDroite(10);
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
    }

    private void setPosition(Stage stage){
        //fond.fitHeightProperty().bind(stage.heightProperty());
        //fond.fitWidthProperty().bind(stage.widthProperty());
    }

    private void setPositionInitial(){
        vaisseau.setTranslateX(690);
        vaisseau.setTranslateY(80);
        vaisseau.setPosInitial(690,80);
    }

    public boolean appuyerGaz(){
        return accelerationJoueur;
    }

    public void deplacement(int x,int y){
        vaisseau.setTranslateX(x);
        vaisseau.setTranslateY(y);
    }
}