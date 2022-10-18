package Game.Scenes;

import Game.Main;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


    public class ExitScene extends GeneralScene {
        private static final String BACKGROUND_IMAGE = "Images/koniec3.png";
        private Image background;


        public ExitScene() throws IOException {
            super();

            try{
                background=new Image(Files.newInputStream((Paths.get(BACKGROUND_IMAGE))));
            }catch(Exception e){
                e.printStackTrace();
            }


            gameOverMessage();

        }


        private void gameOverMessage(){






            Font myFont = Font.font("Arial", FontWeight.EXTRA_BOLD,50);
            gc.setFont(myFont);
            gc.setFill(Color.RED);
            gc.setStroke(Color.WHITE);
            gc.fillText(" GAME OVER", 500,230);
            gc.strokeText(" GAME OVER", 500, 230);

            myFont = Font.font("Arial",FontWeight.BOLD,30);
            gc.setFont(myFont);
            gc.setFill(Color.ORANGE);
            gc.setStroke(Color.WHITE);
            gc.fillText(" Twoj Wynik : "+ totalPoints    , 500,300);
            gc.strokeText(" Twoj Wynik : "+ totalPoints , 500, 300);
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.BLACK);
            gc.fillText(" KONIEC  ", 500,400);
            gc.strokeText(" KONIEC  ", 500, 400);


            myFont = Font.font("Arial",FontWeight.BOLD,25);
            gc.setFont(myFont);
            gc.setFill(Color.DARKRED);
            gc.setStroke(Color.WHITE);
            gc.fillText(" Powrot do START nacisnij \"ESCAPE\" \" lub \'CONTROL\'+\'ALT\'+\'Q\'"  , 200,600);
            gc.strokeText(" Powrot do START nacisnij \"ESCAPE\" \" lub \'CONTROL\'+\'ALT\'+\'Q\'" , 200, 600);


        }

        public void draw(){


            activeKeys.clear();

            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                    gc.drawImage(background,0,0);
                    gameOverMessage();
                    if (activeKeys.contains(KeyCode.ESCAPE)) {
                        this.stop();
                        Main.setScene(Main.START_SCENE);
                    }

                } }.start();
        }
    }

