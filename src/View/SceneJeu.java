/**
 * Created by Chroon on 2016-11-10.
 */
package View;

import Model.Vaisseau;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class SceneJeu {
    private Scene jeu;
    private boolean accelerationJoueur;
    private Vaisseau vaisseau;
    private Path sol;

    public SceneJeu(Vaisseau vaisseau,Path sol){
        accelerationJoueur=false;
        this.vaisseau=vaisseau;
        this.sol=sol;


        Group root=new Group(vaisseau,sol);

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







}