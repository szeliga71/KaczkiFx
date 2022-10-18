package Game.Scenes;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;



public abstract class GeneralScene extends Scene  {

    public static final int GAME_WIDTH =  1240;
    public static final int GAME_HEIGHT =  700;


    public Pane root;
    protected GraphicsContext gc;

    protected  Set<KeyCode>activeKeys;
    protected Set<KeyCode> releasedKeys;
    public static int lives;
    public static  String nick;
    public static int totalPoints;
    public static String wCzasie;
    public static ObservableList<String> imiona = FXCollections.<String>observableArrayList();

    public static ListView<String> hall=new ListView<>();

    public int getTotalPointss() {
        return totalPoints;
    }

    public void setTotalPointss(int points) {
        this.totalPoints =points;
    }


    public static void setLives(int lives) {
        GeneralScene.lives = lives;
    }

    public GeneralScene() {

        super(new Pane(), GAME_WIDTH, GAME_HEIGHT);
        root = new Pane();
        this.setRoot(root);


        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);


        gc = canvas.getGraphicsContext2D();


        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();
        this.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
        });
        this.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
            releasedKeys.add(e.getCode());

        });

        URL url = getClass().getResource("/celownik1.png");
        Cursor cel = Cursor.cursor(url.toString());
        setCursor(cel);




        this.getAccelerators().put(new KeyCodeCombination(
                KeyCode.Q, KeyCombination.CONTROL_ANY,KeyCombination.ALT_ANY),
                new Runnable() {
            @Override public void run() {
                activeKeys.add(KeyCode.ESCAPE);
                lives=0;


            }
        });

    }

    public abstract  void draw();


}
