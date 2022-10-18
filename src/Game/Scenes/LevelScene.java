package Game.Scenes;

import Game.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LevelScene extends GeneralScene {


    private static final String BACKGROUND_IMAGE = "Images/koniec2.jpg";
    private Image background;

    public LevelScene(){
        super();
        try{
            background=new Image(Files.newInputStream((Paths.get(BACKGROUND_IMAGE))));
        }catch(Exception e){
            e.printStackTrace();
        }
        Font myFont = Font.font("Arial", FontWeight.EXTRA_BOLD,25);

        Button level1 = new Button(" LEVEL 1 ");
        level1.setFont(myFont);
        level1.setPrefSize(200, 100);
        level1.relocate(850,100);

        level1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Main.setScene(Main.LEVEL_1);}});

        Button level2 = new Button(" LEVEL 2 ");

            level2.setFont(myFont);

        level2.setPrefSize(200, 100);
        level2.relocate(850,300);

        level2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Main.setScene(Main.LEVEL_2);}
        });


        Button level3 = new Button(" LEVEL 3 ");
        level3.setPrefSize(200, 100);
        level3.relocate(850,500);
        level3.setFont(myFont);

        level3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.setScene(Main.LEVEL_3);}
        });


        root.getChildren().addAll(level1,level2,level3);

        levelMessage();

    }

    private void levelMessage(){

        Font myFont = Font.font("Arial", FontWeight.EXTRA_BOLD,50);
        gc.setFont(myFont);
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);


        gc.fillText(" CHOOSE LEVEL  ", 230,200);
        gc.strokeText(" CHOOSE LEVEL  ", 230, 200);

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

