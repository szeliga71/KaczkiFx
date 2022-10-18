package Game.Scenes;

import Game.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HallOfFame extends GeneralScene {


    private static final String BACKGROUND_IMAGE = "Images/koniec2.jpg";
    private Image background;


    public HallOfFame()  {
        super();

        try {
            background = new Image(Files.newInputStream((Paths.get(BACKGROUND_IMAGE))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hall.getItems().removeAll(imiona);
        hall.getItems().addAll(imiona);
        hall.refresh();
        hall.setLayoutX(850);
        hall.setLayoutY(50);
        Label info= new Label(" Brak  wynikow ");
        Font myFont5 = Font.font("Arial", FontWeight.BOLD, 25);
        info.setFont(myFont5);
        hall.setPlaceholder(info);
        hall.refresh();


        Font myFont = Font.font("Arial", FontWeight.EXTRA_BOLD, 50);
        Text hallOfFame= new Text(" HALL OF FAME ");
        hallOfFame.setFont(myFont);
        hallOfFame.setX(200);
        hallOfFame.setY(100);
        hallOfFame.setFill(Color.RED);
        hallOfFame.setStroke(Color.WHITE);


        FadeTransition ft = new FadeTransition(Duration.millis(1500),hallOfFame);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1000);
        ft.setAutoReverse(true);
        ft.play();


        root.getChildren().addAll(hall,hallOfFame);

    }
        public void draw () {


            activeKeys.clear();

            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                    gc.drawImage(background, 0, 0);

                    HallOfFameMessage();

                    if (activeKeys.contains(KeyCode.ESCAPE)) {
                        this.stop();
                        Main.setScene(Main.START_SCENE);
                    }

                }
            }.start();
        }


    private void HallOfFameMessage() {


        Font myFont1 = Font.font("Arial", FontWeight.EXTRA_BOLD, 30);
        gc.setFont(myFont1);
        gc.setFill(Color.ORANGE);
        gc.setStroke(Color.BLACK);
        gc.fillText(" Powrot do START wcisnij \'ESCAPE \' lub \'CONTROL\'+\'ALT\'+\'Q\'", 200, 600);
        gc.strokeText(" Powrot do START wcisnij \'ESCAPE \' lub \'CONTROL\'+\'ALT\'+\'Q\'", 200, 600);


    }
}