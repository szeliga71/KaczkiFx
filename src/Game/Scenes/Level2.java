package Game.Scenes;

import Game.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Level2 extends GeneralScene{


    private static final String BACKGROUND_IMAGE = "Images/koniec2.jpg";
    private Image background;

    public Level2() throws IOException {
        super();
        try{
            background=new Image(Files.newInputStream((Paths.get(BACKGROUND_IMAGE))));
        }catch(Exception e){
            e.printStackTrace();
        }
        Font myFont = Font.font("Arial", FontWeight.EXTRA_BOLD,25);

        Button play = new Button(" PLAY 2");
        play.setFont(myFont);
        play.setPrefSize(200, 100);
        play.relocate(850,100);

        FadeTransition ftb=new FadeTransition(Duration.millis(1000),play);
        ftb.setFromValue(0.2);
        ftb.setToValue(1.0);
        ftb.setCycleCount(1000);
        ftb.setAutoReverse(true);
        ftb.play();

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Main.setScene(Main.GAME_SCENE1);}});



        root.getChildren().add(play);

        levelMessage();

    }

    private void levelMessage(){

        Font myFont = Font.font("Arial", FontWeight.EXTRA_BOLD,50);
        gc.setFont(myFont);
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);


        gc.fillText(" YOUR LEVEL  ", 230,200);
        gc.strokeText(" YOUR LEVEL  ", 230, 200);

        Font myFont1 = Font.font("Arial", FontWeight.BOLD,30);
        gc.setFont(myFont1);
        gc.setFill(Color.WHITE);
        gc.fillText(" Nacisnij  \"ESCAPE\"  aby  powrocic  na  START ", 50,400);
        gc.strokeText(" Nacisnij  \"ESCAPE\"  aby  powrocic  na  START ", 50, 400);


    }

    public void draw(){


        activeKeys.clear();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {


                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                gc.drawImage(background, 0, 0);
                levelMessage();



                if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                    Main.setScene(Main.START_SCENE);}


            } }.start();
    }
}

