package Game.Elements;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;


public class Chmura extends Ramka {


    private static final String IMAGE_PATH ="images/chmura1.png";
    public static int CHMURKA_WIDTH =100;
    public static int CHMURKA_HEIGHT =200;
    private Image cloud;

    private Point2D center;
    private double  radius;
    public int kierunek;





    public Chmura(int x, int kierunek) {
        super(CHMURKA_WIDTH, CHMURKA_HEIGHT,kierunek);
        this.x=x;
        this.kierunek=kierunek;
        Random r= new Random();
        y=r.nextInt(500);


        try
        {
            cloud=new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        center = new Point2D(x,y);
        radius=height;


    }

    public void moveTo(double x,double y){
        this.x=x;
        this.y=y;

    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void draw(GraphicsContext gc){

        gc.drawImage(cloud,x,y);
    }





    public void move(int kierunek){

        if(kierunek==2)
        this.y=y-1;
        if(kierunek==1)
        this.y=y+1;


        setCenter(x,y);
    }

    public boolean containsPoint(double x, double y)
    {
        return (center.distance(x,y) < radius);
    }

    public void setCenter(double x, double y)
    {
        center = new Point2D(x,y);
    }

}
