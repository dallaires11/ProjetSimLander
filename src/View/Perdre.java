/**
 * Created by Chroon on 2016-11-08.
 */
package View;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;

public class Perdre {
    private Scene perdre;
    private Text texte,sousTexte/*point*/;
    private Button toMenu/*,quitter*/;
    private HBox orgButton;
    private VBox org;
    private Group root;
    private MediaPlayer crashSound;

    public Perdre(Stage stage){
        crashSound=new MediaPlayer(new Media(new File("src/Sound/Crash.mp3").toURI().toString()));

        texte = new Text("YOU HAVE CRASHED");
        sousTexte = new Text("Get good");

        toMenu = new Button("I suck, take me back home");

        orgButton=new HBox();
        orgButton.getChildren().add(toMenu/*reyassayer,quiiter?*/);

        org=new VBox();
        org.getChildren().addAll(texte,sousTexte,orgButton);

        setAction(stage);
        setText();
        setPosition();

        root=new Group();
        root.getChildren().add(org);

        perdre = new Scene(root,1400,700,Color.BLACK);
    }

    private void setAction(Stage stage){
        toMenu.setOnAction(event -> {
            stage.setScene(Menu.getSceneMenu());
            crashSound.stop();
        });
    }

    private void setText(){
        texte.setScaleX(10); texte.setScaleY(10);
        texte.setFill(Color.RED);

        sousTexte.setFill(Color.WHITE);
        sousTexte.setScaleX(1.2);
        sousTexte.setScaleY(1.2);
    }

    private void setPosition(){
        org.setAlignment(Pos.CENTER);
        org.setSpacing(150);
        org.setPadding(new Insets(200,0,0,605));
    }

    public Scene getScene(){
        taPerdu();
        return perdre;
    }

    private void taPerdu(){
        root.setVisible(false);
        PauseTransition pt = new PauseTransition(crashSound.getTotalDuration());
        pt.setOnFinished(event->root.setVisible(true));

        perdre.setOnKeyPressed(event->{
            if(event.getCode()== KeyCode.SPACE) {
                pt.jumpTo(pt.getTotalDuration());
                crashSound.stop();
            }
        });

        pt.play();
        crashSound.play();
    }
}