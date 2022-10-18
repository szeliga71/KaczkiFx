package Game.Elements;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Ptak extends Lot {

    private Point2D center;
    private double  radius;
    private static  String IMAGE_PATH;
    public static final int PTASZEK_WIDTH =95;
    public static final int PTASZEK_HEIGHT = 85;
    private static int STEP = 4;
    private int point=0;
    public int side;
    public int strong;
    public int power;

    public int speed;


    public Ptak(String path, int[]lot, int strong,int side,int speed) {
        super(PTASZEK_WIDTH, PTASZEK_HEIGHT,side);
        center = new Point2D(x,y);
        radius=height;
        this.IMAGE_PATH=path;
        this.frameXCoordinates=lot;
        this.strong=strong;
        this.power=strong;
        this.side=side;
        this.speed=speed;

        try {
            ptaszek = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }


        frameYCoordinates = new int[]{0, 0, 0, 0, 0,0};


    }
    public void move(){

        if(side==0)
        x=x+speed;
        else if(side==1)
            x=x-speed;
        y=y+0;
        setCenter(x,y);
        animate();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
