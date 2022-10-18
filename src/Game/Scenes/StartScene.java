package Game.Scenes;


import Game.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class StartScene extends GeneralScene {

    private static final String BACKGROUND_IMAGE = "Images/38712tlo.jpg";
    private Image background;






    public StartScene() throws IOException {
        super();

        try{
            background=new Image(Files.newInputStream((Paths.get(BACKGROUND_IMAGE))));
        }catch(Exception e){
            e.printStackTrace();
        }



        Font myFont = Font.font("Arial", FontWeight.BOLD, 45);
        Text playText=new Text(" PRESS \"P\" TO PLAY ");
        playText.setFont(myFont);
        playText.setX(400);
        playText.setY(450);
        playText.setFill(Color.YELLOW);
        playText.setStroke(Color.BROWN);

        ScaleTransition st = new ScaleTransition(Duration.millis(1000), playText);
        st.setFromX(1);
        st.setToX(1.5);
        st.setFromY(1);
        st.setToY(1.5);
        st.setCycleCount(100);
        st.setAutoReverse(true);
        st.play();

        root.getChildren().add(playText);

        welcomeMessage();

    }


    private void welcomeMessage() {

        setLives(10);

        Font myFont1 = Font.font("Verdana", FontWeight.BOLD, 25);
        gc.setFont(myFont1);
        gc.setFill(Color.BLUE);
        gc.fillText(" PRESS \"L\" TO HALL OF FAME  ", 400, 550);
        gc.strokeText(" PRESS \"L\" TO HALL OF FAME  ", 400, 550);



        gc.setFont(myFont1);
        gc.setFill(Color.RED);
        gc.fillText(" PRESS \"ESC\" TO  EXIT  ", 400, 650);
        gc.strokeText(" PRESS \"ESC\" TO  EXIT  ", 400, 650);


    }

    public void draw() {

        activeKeys.clear();

        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime) {

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                gc.drawImage(background,0,0);


                welcomeMessage();

                if (activeKeys.contains(KeyCode.P)) {
                    this.stop();
                    Main.setScene(Main.LEVEL_SCENE);


                }else
                    if(activeKeys.contains(KeyCode.L)){
                    this.stop();

                    Main.setScene(Main.HALLOFFAME_SCENE);


                } else if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                        try {
                            Main.exit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


            } }.start();
    }





}
