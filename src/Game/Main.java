package Game;

import Game.Elements.Chmura;
import Game.Elements.Ptak;
import Game.Scenes.*;
import Game.Scenes.StartScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import static Game.Scenes.GeneralScene.*;


public class Main extends Application {


    public static final int TOTAL_SCENES=11;
    public static  final int START_SCENE= 0;
    public static final int GAME_SCENE =1;
    public static final int GAME_SCENE1 =4;
    public static final int GAME_SCENE2 =5;
    public static  final int GAME_OVER_SCENE= 2;
    public static  final int LEVEL_SCENE= 3;
    public static  final int LEVEL_1= 6;
    public static  final int LEVEL_2= 7;
    public static  final int LEVEL_3= 8;
    public static  final int ADDHALLOFFAME_SCENE= 9;
    public static  final int HALLOFFAME_SCENE= 10;

    public static GeneralScene[]scenes=new GeneralScene[TOTAL_SCENES];

    private static Stage stage;
    Chmura chmura;
    Chmura chmura1;
    Chmura chmura2;
    Chmura chmura3;
    String background;
    String   background1;
    String   background2;
    public Ptak[]ptaki;
    public Ptak[]ptaki1;
    public Ptak[]ptaki2;
    public Chmura[]chmurki;
    public Chmura[]chmurki1;
    public Chmura[]chmurki2;


    @Override

    public void start(Stage stage) throws Exception{

        int[] frameXCoordinates1 = new int[]{0, 104, 312, 208, 312, 104};
        int[] frameXCoordinates2 = new int[]{0, 86, 172, 258, 344, 430};
        int[] frameXCoordinates3 = new int[]{0, 104, 208, 312, 208, 104};
        int[] frameXCoordinates4 = new int[]{0, 104, 312, 208, 312, 104};
        int[] frameXCoordinates5 = new int[]{0, 104, 312, 208, 312, 104};
        int[] frameXCoordinates6 = new int[]{421,337,253,169,85,169 };
        int[] frameXCoordinates7 = new int[]{560,467, 374,281, 188,94};
        int[] frameXCoordinates1rev = new int[]{104,312,208,312,104,0};
        int[] frameXCoordinates2rev = new int[]{430,344,258,172,86,0};
        int[] frameXCoordinates3rev = new int[]{104,208,312,208,104,0};
        int[] frameXCoordinates4rev = new int[]{104,312,208,312,104,0};
        int[] frameXCoordinates5rev = new int[]{104,312,208,312,104,0};

        Ptak ptak = new Ptak("images/ptaszek1.png", frameXCoordinates1, 2, 0,1);
        Ptak ptak1 = new Ptak("images/ptaszek2.png", frameXCoordinates2, 3, 0,2);
        Ptak ptak3 = new Ptak("images/ptaszek3.png", frameXCoordinates3, 4,0,2);
        Ptak ptak4 = new Ptak("images/ptaszek4.png", frameXCoordinates4, 5,0,2);
        Ptak ptak5 = new Ptak("images/ptaszek5.png", frameXCoordinates5, 6, 0,2);
        Ptak ptak6 = new Ptak("images/ptaszek6.png", frameXCoordinates6, 4, 1,2);
        Ptak ptak7 = new Ptak("images/ptaszek7a.png", frameXCoordinates7, 3, 1,2);
        Ptak ptakRev = new Ptak("images/ptaszek1rev.png", frameXCoordinates1rev, 2, 1,3);
        Ptak ptak1rev = new Ptak("images/ptaszek2rev.png", frameXCoordinates2rev, 3, 1,2);
        Ptak ptak3rev = new Ptak("images/ptaszek3rev.png", frameXCoordinates3rev, 4, 1,2);
        Ptak ptak4rev = new Ptak("images/ptaszek4rev.png", frameXCoordinates4rev, 5, 1,2);
        Ptak ptak5rev = new Ptak("images/ptaszek5rev.png", frameXCoordinates5rev, 6, 1,2);






        ptaki = new Ptak[]{ptak, ptak1, ptak3, ptak4, ptak5};//,ptak6,ptak7};
        ptaki1 = new Ptak[]{ptak,ptak1,ptakRev,ptak1rev,ptak4,ptak3rev,ptak6};
        ptaki2=new Ptak[]{ptak1,ptak1rev,ptak3,ptak5rev,ptak4rev,ptak5};

        chmura = new Chmura(100, 1);
        chmura1 = new Chmura(300, 2);
        chmura2 = new Chmura(600, 1);
        chmura3 = new Chmura(900, 2);

        chmurki = new Chmura[]{chmura2, chmura3, chmura, chmura1};
        chmurki1 = new Chmura[]{chmura2, chmura3, chmura1};
        chmurki2 = new Chmura[]{chmura2, chmura3};
        background="Images/jezioro5a.png";
        background1="Images/jez1a.png";
        background2="Images/jeziorko541.png";

        Main.stage =stage;


        scenes[0]= new StartScene();
        scenes[1]=new GameScene(background,ptaki,chmurki2,0);
        scenes[4]=new GameScene(background1,ptaki1,chmurki1,1);
        scenes[5]=new GameScene(background2,ptaki2,chmurki,2);
        scenes[2]=new ExitScene();
        scenes[3]=new LevelScene();
        scenes[6]=new Level1();
        scenes[7]=new Level2();
        scenes[8]=new Level3();
        scenes[9]=new AddToHallOfFame();
        scenes[10]=new HallOfFame();


        URL url = getClass().getResource( "/celownik2.png");
        Image myIcon = new Image(url.toString());
        stage.getIcons().add(myIcon);
        stage.setTitle(" BIRD HUNTER  ");
        setScene(START_SCENE);
        stage.show();



       FileInputStream fisWyniki = null;
        try {
            fisWyniki = new FileInputStream("wyniki.txt");

            int c;
            String wpis = "";

            while ((c = fisWyniki.read()) != -1) {

                if (c == 46) {
                    wpis=wpis+(char)c;

                    imiona.add(wpis);

                    hall.getItems().add(wpis);
                    wpis="";
                }

                if (c != 46) {
                    wpis = wpis + (char) c;
                    }
            }

            }finally{
                if (fisWyniki != null)
                    fisWyniki.close();
            }


        }

    public static void setScene(int numScene){

        stage.setScene(scenes[numScene]);
        scenes[numScene].draw();
    }

    public static void exit() throws Exception{

        stage.hide();


        FileOutputStream fosWyniki = null;
        try{
               fosWyniki = new FileOutputStream("wyniki.txt");
            for(int i = 0; i< imiona.size(); i++){
                for(int j = 0; j< imiona.get(i).length(); j++){
                   int c= imiona.get(i).charAt(j);
            fosWyniki.write(c);}}


        }finally{
            if(fosWyniki!=null)
                fosWyniki.close();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

