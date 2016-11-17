/**
 * Created by Chroon on 2016-11-10.
 */
package View;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class SceneJeu {
    private Scene jeu;
    private boolean accelerationJoueur;
    private Group root;

    public SceneJeu(){
        accelerationJoueur=false;

        root=new Group();

        jeu=new Scene(root,1400,700);
    }

    private void setAction(){
        jeu.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.UP)
                accelerationJoueur=true;
            if(event.getCode()== KeyCode.LEFT)
                return;
            if(event.getCode()== KeyCode.RIGHT)
                return;
        });
        jeu.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.UP)
                accelerationJoueur=false;

        });
    }

    public Scene getSceneJeu(){
        return jeu;
    }

    public void addElementJeu(Node node){
        root.getChildren().add(node);
    }

    public void cleanup(Node node){
        root.getChildren().removeAll();
    }







}