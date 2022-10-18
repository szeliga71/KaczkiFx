package Game.Elements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;


public class Ramka {

    protected double width,height;
    public double x;
    public double y;
    protected double frameX,frameY;
    protected Image ptaszek;
    public int side;


    public Ramka(double width, double height,int side){
        this.width=width;
        this.height=height;
        this.side=side;

        Random r= new Random();

        if(side==1){

            x=1240;
            y=r.nextInt(500);
        }
else if(side==0){
       y=r.nextInt(500);
        x=r.nextInt(100);
             }

    }



    public void moveTo(int x,int y){
        this.x=x;
        this.y=y;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void draw(GraphicsContext gc) {
        gc.drawImage(ptaszek,frameX, frameY, width, height, x, y, width, height);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

