package Game.Scenes;

import Game.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class AddToHallOfFame extends GeneralScene {


        private static final String BACKGROUND_IMAGE = "Images/koniec3.png";
        private Image background;

        TextField nickTextField =new TextField();

        public AddToHallOfFame() throws IOException {
            super();

            try{
                background=new Image(Files.newInputStream((Paths.get(BACKGROUND_IMAGE))));
            }catch(Exception e){
                e.printStackTrace();
            }


            nickTextField.relocate(550,350);

            Font myFont2 = Font.font("Arial", FontWeight.NORMAL,25);
            nickTextField.fontProperty().set(myFont2);
            nickTextField.autosize();

            Button add =new Button(" ZAPISZ ");
            Font myFont3 = Font.font("Arial", FontWeight.BOLD,25);
            add.setFont(myFont3);
            add.relocate(600,450);

            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {


                    nickTextField.appendText("  ");
                    nickTextField.appendText (String.valueOf(totalPoints)+ " punktow w czasie "+wCzasie+" sekund.");
                    imiona.add(nickTextField.getText());

                    nick=nickTextField.getText()+"  ";


                  System.out.println(nickTextField.getText()+"  ");
                  hall.refresh();
                  hall.getItems().removeAll(imiona);
                  hall.getItems().addAll(imiona);
                nickTextField.clear();

                    Main.setScene(Main.START_SCENE);

                }
            });

            root.getChildren().addAll(nickTextField,add);

        }


        private void AddToHallOfFameMessage(){



            Font myFont = Font.font("Arial", FontWeight.EXTRA_BOLD,50);
            gc.setFont(myFont);
            gc.setFill(Color.RED);
            gc.setStroke(Color.WHITE);
            gc.fillText(" ZAPISZ WYNIK ", 500,250);
            gc.strokeText(" ZAPISZ WYNIK ", 500, 250);

            Font myFont1 = Font.font("Arial", FontWeight.EXTRA_BOLD,30);
            gc.setFont(myFont1);
            gc.setFill(Color.OLIVE);
            gc.setStroke(Color.BLACK);
            gc.fillText(" Jesli nie chcesz zapisywac wyniku wcisnij \'ESCAPE \'", 200,600);
            gc.strokeText(" Jesli nie chcesz zapisywac wyniku wcisnij \'ESCAPE \'", 200, 600);

            Font myFont4 = Font.font("Arial", FontWeight.EXTRA_BOLD,40);
            gc.setFont(myFont4);
            gc.setFill(Color.BLUE);
            gc.setStroke(Color.YELLOW);
            gc.fillText(   " Zdobyles  "+  totalPoints+"  punktow w czasie "+ wCzasie+" sekund ", 200,100);
            gc.strokeText(  " Zdobyles  "+  totalPoints+"  punktow w czasie "+ wCzasie+" sekund ", 200,100);




        }


        public void draw(){


            activeKeys.clear();

            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                    gc.drawImage(background,0,0);
                    AddToHallOfFameMessage();
                    if (activeKeys.contains(KeyCode.ESCAPE)) {

                        this.stop();

                        Main.setScene(Main.START_SCENE);
                    }

                } }.start();
        }
    }




