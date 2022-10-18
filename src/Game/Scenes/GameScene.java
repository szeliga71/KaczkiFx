package Game.Scenes;

import Game.Elements.Chmura;
import Game.Elements.Ptak;
import Game.Elements.Time;
import Game.Main;

import javafx.animation.AnimationTimer;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;


import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GameScene extends GeneralScene  {


    public Image background;
    private Image explosion;
    public Ptak[]ptaki;
    public Chmura[]chmurki;
    public int levelSpeed;
    int localScore;


    public GameScene(String backgroundS,Ptak[]ptaki,Chmura[]chmurki,int levelSpeed) {
        super();
        this.ptaki=ptaki;
        this.chmurki=chmurki;
        this.levelSpeed=levelSpeed;


        try {
            background = new Image(Files.newInputStream((Paths.get(backgroundS))));
            explosion=new Image(Files.newInputStream(Paths.get("Images/wybuszek1.png")));
        } catch (IOException e) {
            e.printStackTrace();



        }


        }
        @Override
        public void draw() {


            ExecutorService executorService = Executors.newFixedThreadPool(1);
            Time time = new Time();

            executorService.submit(time);


        Random r = new Random();


            new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {

                    if (lives > 0) {



                        gc.drawImage(background, 0, 0);


                        for (int i = 0; i < ptaki.length; i++) {

                            latanie(ptaki[i], r);
                        }

                        for (int i = 0; i < chmurki.length; i++) {

                            pogoda(chmurki[i]);
                        }

                                    setOnMouseClicked(


                                            new EventHandler<MouseEvent>() {
                                                public void handle(MouseEvent e) {



                                                    for (int k = 0; k < ptaki.length; k++) {

                                                        if (ptaki[k].containsPoint(e.getX(), e.getY())) {
                                                            ImageView myImage = new ImageView();
                                                            myImage.setImage(explosion);
                                                            root.getChildren().add(myImage);
                                                            myImage.setX(ptaki[k].getX());
                                                            myImage.setY(ptaki[k].getY());
                                                            FadeTransition ft = new FadeTransition(Duration.millis(600));
                                                            ft.setNode(myImage);
                                                            ft.setFromValue(1.0);
                                                            ft.setToValue(0.0);
                                                            ft.play();

                                                            ptaki[k].power--;
                                                        }

                                                    }
                                                }

                                            });





                        String pointsText = "PUNKTY   " + (localScore);
                        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 50);
                        gc.setFont(theFont);
                        gc.setStroke(Color.WHITE);
                        gc.setLineWidth(1);
                        gc.fillText(pointsText, 100, 650, 200);
                        gc.strokeText(pointsText, 100, 650, 200);

                        String livesText = "LIVES   " + (lives);
                        gc.setStroke(Color.RED);
                        gc.fillText(livesText, 500, 650, 200);
                        gc.strokeText(livesText, 500, 650, 200);

                        String timeText = "CZAS   " + (time.sekunda);
                        gc.setStroke(Color.ORANGE);
                        gc.fillText(timeText, 850, 650, 200);
                        gc.strokeText(timeText, 850, 650, 200);



                    } else {

                        time.rozrusznik = false;
                        wCzasie=String.valueOf(time.sekunda);
                        executorService.shutdown();
                        setTotalPointss(localScore);
                        localScore=0;
                        lives=10;
                        this.stop();


                        Dialog<ButtonType> dialog = new Dialog<>();

                        dialog.setContentText("Czy chcesz zapisac wynik ?");


                        ButtonType but1 = new ButtonType("ZAPISZ", ButtonBar.ButtonData.OK_DONE);
                        ButtonType but2 = new ButtonType("KONIEC", ButtonBar.ButtonData.CANCEL_CLOSE);


                        dialog.getDialogPane().getButtonTypes().addAll(but1, but2);



                        Platform.runLater(()->{
                            Optional<ButtonType> response = dialog.showAndWait();
                            response.ifPresent(buttonType -> {

                                    if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                                        Main.setScene(Main.ADDHALLOFFAME_SCENE);

                                }
                                if (buttonType.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)  {

                                    Main.setScene(Main.GAME_OVER_SCENE);
                                }
                        });
                    });

}
                }
            }.start();

        }

        public void latanie (Ptak ptak, Random r) {

       int x = 0;

        int y;



        switch(ptak.side) {


            case 0:

                if (ptak.power > 0)
                    ptak.draw(gc);

                if (ptak.getX() > GAME_WIDTH) {
                    x = r.nextInt(200);
                    ptak.setX(x);
                    y = r.nextInt(500);
                    ptak.setY(y);
                    lives--;
                }
                if (ptak.power == 0) {
                    x = r.nextInt(200);
                    ptak.setX(x);
                    y = r.nextInt(500);
                    ptak.setY(y);
                    localScore=localScore+ptak.strong;

                    ptak.power = ptak.strong;
                } else {
                    ptak.move();
                }
                break;

            case 1:
                if (ptak.power > 0)
                          ptak.draw(gc);
                if (ptak.getX()<0) {
                    x = 1240;
                    ptak.setX(x);
                    y = r.nextInt(500);
                    ptak.setY(y);
                    lives--;
                }
                if (ptak.power == 0) {
                    x = 1240;
                    ptak.setX(x);
                    y = r.nextInt(500);
                    ptak.setY(y);
                    localScore=localScore+ptak.strong;

                    ptak.power = ptak.strong;
                } else {
                    ptak.move();
                }

                break;
        }}



        public void pogoda (Chmura chmura){

            chmura.draw(gc);


            if (chmura.getY() == -200) {
                chmura.kierunek = chmura.kierunek % 2;
                chmura.kierunek++;
            } else if (chmura.getY() == GAME_HEIGHT - 200) {
                chmura.kierunek = chmura.kierunek % 2;
                chmura.kierunek++;
            }
            chmura.move(chmura.kierunek);

        }

}

