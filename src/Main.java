import Controller.ControllerJeu;
import Controller.Points;
import Model.Sol;
import Model.Vaisseau;
import javafx.application.Application;
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
        vueJeu=new SceneJeu(leStage);
        choix=new Choix(leStage,controller);
        option=new Option(leStage);
        menu=new Menu(leStage,choix,option);
        controller=new ControllerJeu(vaisseau,sol,vueJeu,leStage,Perdre.getScene(), Gagner.getScene());
        primaryStage.setTitle("SimLander");
        primaryStage.setScene(menu.getSceneMenu());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}