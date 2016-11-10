/**
 * Created by Chroon on 2016-11-10.
 */
package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Choix {
    private Scene choix;
    private Group root;
    private VBox org,optionVaisseau,ensPlan1,ensPlan2,ensPlan3,ensPlan4;
    private HBox planete,difficulte;
    private ImageView planete1,planete2,planete3,planete4,fond;
    private TextField nomVaisseau;
    private Text texte,terre,mars,lune,kaurava;

    public Choix(){
        fond=new ImageView();
        planete1=new ImageView();
        planete2=new ImageView();
        planete3=new ImageView();
        planete4=new ImageView();

        texte = new Text("Choisissez un nom pour votre vaisseau");
        nomVaisseau=new TextField();
        nomVaisseau.setPromptText("Nom de votre vaisseau");

        terre= new Text("Terre");
        mars=new Text("Mars");
        lune=new Text("Lune");
        kaurava=new Text("Kaurava");

        //Changer ImageView planete pour des button avec imageview ou queque chose de semblable
        ensPlan1=new VBox();
        ensPlan1.getChildren().addAll(terre,planete1);
        ensPlan2=new VBox();
        ensPlan2.getChildren().addAll(mars,planete2);
        ensPlan3=new VBox();
        ensPlan3.getChildren().addAll(lune,planete3);
        ensPlan4=new VBox();
        ensPlan4.getChildren().addAll(kaurava,planete4);

        planete=new HBox();
        planete.getChildren().addAll(ensPlan1,ensPlan2,ensPlan3,ensPlan4);
        difficulte=new HBox();

        optionVaisseau=new VBox();
        optionVaisseau.getChildren().addAll(texte,nomVaisseau);

        org =new VBox();
        org.getChildren().addAll(planete,difficulte,optionVaisseau);

        root= new Group();
        root.getChildren().addAll(fond,org);

        choix=new Scene(root,1400,700);
    }
}