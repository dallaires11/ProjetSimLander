/**
 * Created by Chroon on 2016-11-08.
 */
package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Perdre {
    private Scene perdre;
    private Text texte,sousTexte/*point*/;
    private Button toMenu/*,quitter*/;
    private HBox orgButton;
    private VBox org;

    public Perdre(Stage stage){
        texte = new Text("YOU HAVE CRASHED");
        sousTexte = new Text("Get good");

        toMenu = new Button("I suck, take me back home");
        toMenu.setOnAction(event -> stage.setScene(Menu.getSceneMenu()));

        orgButton=new HBox();
        orgButton.getChildren().add(toMenu/*reyassayer,quiiter?*/);

        org=new VBox();
        org.getChildren().addAll(texte,sousTexte,orgButton);

        setText();
        setPosition();

        Group root=new Group();
        root.getChildren().add(org);

        perdre = new Scene(root,1400,700,Color.BLACK);
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
        return perdre;
    }
}