import Controller.*;
import Interface.OptionChoisi;
import Interface.TCI;
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
    private IntroJeu intro;
    private Turret turret;
    private TempeteSolaire tempete;

    @Override
    public void start(Stage primaryStage) throws Exception{
        leStage=primaryStage;
        vaisseau=new Vaisseau();
        sol=new Sol();
        vueJeu=new SceneJeu(leStage);
        perdre=new Perdre(leStage);
        gagner=new Gagner(leStage);
        points=new Points(vueJeu);
        turret=new Turret(vaisseau,vueJeu);
        tempete=new TempeteSolaire(vueJeu);
        controller=new ControllerJeu(vaisseau,sol,vueJeu,leStage,perdre,gagner,points,turret,tempete);
        choix=new Choix(leStage,controller);
        option=new Option(leStage);
        controller.setInterfaceChoix(option);
        menu=new Menu(leStage,choix,option);
        intro=new IntroJeu();
        primaryStage.setTitle("SimLander");
        primaryStage.setScene(intro.getSceneIntro(leStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}