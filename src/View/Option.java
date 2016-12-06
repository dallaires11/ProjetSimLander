/**
 * Created by Chroon on 2016-11-12.
 */
package View;

import Interface.OptionChoisi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Option implements OptionChoisi{
    private Scene option,menu;
    private ImageView fond;
    private CheckBox chaos,god,assault;
    private Button retour;
    Text infoChaos,infoGod,infoAssault;
    HBox opt1,opt2,opt3;
    VBox org;

    //Mode que quand tu lache le gaz le vaisseau deveinnt invisible

    public Option(Stage leStage){
        fond=new ImageView("Image/cielEtoile.gif");

        retour=new Button("Retour");

        chaos=new CheckBox();
        infoChaos=new Text("Mode Chaos: ");
        god=new CheckBox();
        infoGod=new Text("Infini Essence: ");
        assault=new CheckBox();
        infoAssault=new Text("Assault orbital: ");

        opt1=new HBox();
        opt1.getChildren().addAll(infoChaos,chaos);
        opt2=new HBox();
        opt2.getChildren().addAll(infoGod,god);
        opt3=new HBox();
        opt3.getChildren().addAll(infoAssault,assault);
        org=new VBox();
        org.getChildren().addAll(opt1,opt2,opt3,retour);

        setPosition(leStage);
        setAction(leStage);
        setText();

        Group root = new Group();
        root.getChildren().addAll(fond,org);

        option=new Scene(root,1400,700);
    }

    private void setPosition(Stage stage){
        org.setAlignment(Pos.CENTER);
        org.setSpacing(30);
        org.setPadding(new Insets(250,640,100,640));
        fond.fitHeightProperty().bind(stage.heightProperty());
        fond.fitWidthProperty().bind(stage.widthProperty());
    }

    private void setAction(Stage stage){
        retour.setOnAction(event -> {
            stage.setScene(menu);
        });

    }

    private void setText(){
        infoGod.setStroke(Color.WHITE);
        infoChaos.setStroke(Color.WHITE);
        infoAssault.setStroke(Color.WHITE);

    }

    Scene getSceneOption(){
        return  option;
    }

    void setSceneMenu(Scene scene){
        menu=scene;
    }

    public boolean chaos(){
        return  chaos.isSelected();
    }

    public boolean cheatEssence(){
        return god.isSelected();
    }

    public boolean asaultOrbital(){
        return assault.isSelected();
    }
}

/*
ven solaire forece sur cote
Lumiere soudaine rend lecran blanc
Météorite
quand tubouge tu devien invisibel
Moins ta te gaz + taccelaire
 */