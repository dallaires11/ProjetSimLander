import Model.Vaisseau;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import View.*;

public class Main extends Application {
    Timeline timeline;
    Vaisseau vaisseau;
    double point;
    Stage leStage;
    Menu menu;
    Choix choix;
    Option option;
    SceneJeu vueJeu;



    @Override
    public void start(Stage primaryStage) throws Exception{
        leStage=primaryStage;
        choix=new Choix(leStage);
        option=new Option(leStage);
        vueJeu=new SceneJeu();
        menu=new Menu(leStage,choix,option);
        Group root = new Group();
        //Temps 2 keyframe / timeline
        primaryStage.setTitle("SimLander");
        primaryStage.setScene(menu.getSceneMenu());
        primaryStage.show();
    }






    public static void main(String[] args) {
        launch(args);
    }
}