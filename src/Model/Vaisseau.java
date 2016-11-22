/**
 * Created by Chroon on 2016-11-08.
 */
package Model;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Vaisseau extends Group {

    private int essence;
    private float vitesseX, vitesseY;
    private int rotation;
    private float positionX, positionY;
    private Label nom;
    private VBox org;
    private Group ensembleForme;

    public Vaisseau() {
        essence = 1000;
        vitesseX = 0;
        vitesseY = 0;
        rotation = 90;
        positionX = 0;
        positionY = 0;
        nom = new Label("");

        ensembleForme=generateImageVaisseau();

        org=new VBox();
        org.getChildren().addAll(nom,ensembleForme);

        setPositonForme();

        this.getChildren().add(org);
    }

    private Group generateImageVaisseau() {
        Polygon triangle = new Polygon(0, 20, 10, 0, 20, 20);
        Rectangle gun1 = new Rectangle(5, 15, 2, -15);
        Rectangle gun2 = new Rectangle(15, 15, 2, -15);

        return new Group(triangle, gun1, gun2);
    }

    public void reduireEssence(int reduction) {
        essence -= reduction;
    }

    public void rotationGauche(int vitesseRotation) {
        rotation -= vitesseRotation;
        if (rotation < 0)
            rotation = 360 + rotation;
    }

    public void rotationDroite(int vitesseRotation) {
        rotation = +vitesseRotation;
        if (rotation > 360)
            rotation -= 360;
    }

    private void bouger() {
        positionX += vitesseX;
        positionY -= vitesseY;

        if(positionX <= 0){
            positionX = 1400;
        }
        if(positionX >= 1400){
            positionX = 0;
        }

        this.setTranslateX(positionX);
        this.setTranslateY(positionY);
    }

    public void accelerer(boolean appuyer) {
<<<<<<< HEAD
        float accelerationX = 0;
        float accelerationY = 0;
        if (appuyer) {
            accelerationX =(float) (0.7 * Math.sin(rotation));
            accelerationY =(float) (0.7 * Math.cos(rotation));
            reduireEssence(10);
        }
        vitesseX += accelerationX;
        vitesseY += accelerationY - 0.049;
=======
        double accelerationX = 0;
        double accelerationY = 0;
        double radian=Math.toRadians(rotation);
        if (appuyer) {
            accelerationX = 0.05 * Math.cos(radian);
            accelerationY =0.05 * Math.sin(radian);
            reduireEssence(10);
        }
        vitesseX += accelerationX;
        vitesseY += accelerationY - 0.0098;
>>>>>>> 232def4e0d92a94caed70c7603a3d4f3797c5e02
        bouger();
    }

    public int getEssence(){
        return essence;
    }

    public float getX(){
        return positionX;
    }

    public float getY(){
        return positionY;
    }

    public void setPosInitial(int x,int y){
        positionX=x;
        positionY=y;
    }

    public int getRotation(){
        return rotation;
    }

    public void setNom(String nouveauNom){
        if (nouveauNom.equalsIgnoreCase("vincent")){
            nom.setText("Dieu");
        } else
            nom.setText(nouveauNom);
    }

    private void setPositonForme(){
        org.setAlignment(Pos.CENTER);
    }

<<<<<<< HEAD
    public float getPositionX(){
        return positionX;
=======
    public double getVitesseX(){
        return vitesseX;
>>>>>>> 232def4e0d92a94caed70c7603a3d4f3797c5e02
    }

    public double getVitesseY(){
        return vitesseY;
    }


}


