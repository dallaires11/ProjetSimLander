/**
 * Created by Chroon on 2016-11-08.
 */
package Model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

class Vaisseau extends Group {

    private int essence;
    private double vitesseX, vitesseY;
    private int rotation;
    private int positionX, positionY;
    private Label nom;

    Vaisseau(){
        essence = 1000;
        vitesseX = 0;
        vitesseY = 0;
        rotation = 0;
        positionX = 100;
        positionY = 200;
        nom = new Label("Player");

        this.getChildren().addAll(new VBox(nom, generateImageVaisseau()));
    }

    private Group generateImageVaisseau(){
        Polygon triangle = new Polygon(0,20, 10,0, 20,20);
        Rectangle gun1 = new Rectangle(5,15, 2,15);
        Rectangle gun2 = new Rectangle(15,15, 2,15);

        return new Group(triangle, gun1, gun2);
    }

    void reduireEssence(int reduction){
        essence -= reduction;
    }

    void rotationGauche(int vitesseRotation){
        rotation -= vitesseRotation;
        if (rotation < 0)
            rotation = 360 + rotation;
    }

    void rotationDroite(int vitesseRotation){
        rotation =+ vitesseRotation;
        if (rotation > 360)
            rotation -= 360;
    }

    void bouger(){
        positionX += vitesseX;
        positionY += vitesseY;
    }

    void accelerer(boolean appuyer){
        double accelerationX = 0;
        double accelerationY = 0;
        if(appuyer){
            accelerationX = 10 * Math.cos((double)rotation);
            accelerationY = 10 * Math.sin((double)rotation);
        }
        vitesseX += accelerationX;
        vitesseY += accelerationY - 9.8;
    }

    int getEssence(){
        return essence;
    }

    int getX(){
        return positionX;
    }

    int getY(){
        return positionY;
    }

    int getRotation(){
        return rotation;
    }

    void setNom(String nouveauNom){
        if (nouveauNom.equalsIgnoreCase("vincent")){
            nom.setText("Dieu");
        } else
            nom.setText(nouveauNom);
    }
}
