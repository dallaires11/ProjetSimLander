/**
 * Created by Chroon on 2016-10-25.
 */
public class Vaisseu {
    float vitesse;
    public Vaisseu(){
        vitesse= 0.00f;
    }

    public float getVitesse(){
        return  vitesse;
    }

    public  void accelerer(){
        vitesse+=0.0098f;
    }
}
