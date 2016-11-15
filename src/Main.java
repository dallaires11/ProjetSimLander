import Controller.ControllerJeu;
import Controller.Points;
import Model.Sol;
import Model.Vaisseau;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import View.*;

public class Main extends Application {
    Points points;
    Stage leStage;
    Menu menu;
    Choix choix;
    Option option;
    SceneJeu vueJeu;
    Vaisseau vaisseau;
    Sol sol;
    Path solCourant;
    ControllerJeu controller;


    @Override
    public void start(Stage primaryStage) throws Exception{
        leStage=primaryStage;
        vaisseau=new Vaisseau();
        sol=new Sol();
        choix=new Choix(leStage);
        option=new Option(leStage);
        //vueJeu=new SceneJeu(vaisseau,sol.getPath());
        menu=new Menu(leStage,choix,option);
        controller=new ControllerJeu();
        Group root = new Group();
        primaryStage.setTitle("SimLander");
        primaryStage.setScene(menu.getSceneMenu());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}