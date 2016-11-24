import Controller.ControllerJeu;
import Controller.Points;
import Model.Sol;
import Model.Vaisseau;
import javafx.application.Application;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import View.*;

public class Main extends Application {
    private Points points;
    private Stage leStage;
    private Menu menu;
    private Choix choix;
    private Option option;
    private SceneJeu vueJeu;
    private Vaisseau vaisseau;
    private Sol sol;
    private ControllerJeu controller;
    private Perdre perdre;
    private Gagner gagner;
    @Override
    public void start(Stage primaryStage) throws Exception{
        leStage=primaryStage;
        vaisseau=new Vaisseau();
        sol=new Sol();
        vueJeu=new SceneJeu(leStage);
        perdre=new Perdre(leStage);
        gagner=new Gagner(leStage);
        points=new Points(vueJeu);
        controller=new ControllerJeu(vaisseau,sol,vueJeu,leStage,perdre.getScene(),gagner.getScene(),points);
        choix=new Choix(leStage,controller);
        option=new Option(leStage);
        menu=new Menu(leStage,choix,option);
        primaryStage.setTitle("SimLander");
        primaryStage.setScene(menu.getSceneMenu());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}