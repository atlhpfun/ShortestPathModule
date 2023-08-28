package application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/**
 * Creates the application UI for the Campus GPS APP
 * 
 * @author Charles Caton
 *
 */
public class Main extends Application {

  String startLocation = (""); // String that will later be edited by user input
  String endLocation = (""); // String that will later be edited by user input
  String extraStops = (""); // String that will later be edited by user input

  @Override
  public void start(Stage stage) {
    Rectangle rect = new Rectangle();
    rect.setX(0);
    rect.setY(0);
    rect.setWidth(2000);
    rect.setHeight(1920);
    rect.setFill(Color.LIGHTBLUE);

    Rectangle rect1 = new Rectangle();
    rect1.setX(10);
    rect1.setY(100);
    rect1.setWidth(400);
    rect1.setHeight(740);
    rect1.setFill(Color.GRAY);
    rect1.setStroke(Color.BLACK);
    rect1.setStrokeWidth(10);
    rect1.setArcHeight(100);
    rect1.setArcWidth(100);

    Rectangle rect2 = new Rectangle();
    rect2.setX(450);
    rect2.setY(100);
    rect2.setWidth(1070);
    rect2.setHeight(740);
    rect2.setFill(Color.GRAY);
    rect2.setStroke(Color.BLACK);
    rect2.setStrokeWidth(10);
    rect2.setArcHeight(100);
    rect2.setArcWidth(100);

    Rectangle rect3 = new Rectangle();
    rect3.setX(50);
    rect3.setY(550);
    rect3.setWidth(300);
    rect3.setHeight(250);
    rect3.setFill(Color.LIGHTGRAY);
    rect3.setStroke(Color.BLACK);
    rect3.setStrokeWidth(10);

    Rectangle rect4 = new Rectangle();
    rect4.setX(50);
    rect4.setY(150);
    rect4.setWidth(300);
    rect4.setHeight(100);
    rect4.setFill(Color.LIGHTGRAY);
    rect4.setStroke(Color.BLACK);
    rect4.setStrokeWidth(10);

    Rectangle rect5 = new Rectangle();
    rect5.setX(50);
    rect5.setY(350);
    rect5.setWidth(300);
    rect5.setHeight(100);
    rect5.setFill(Color.LIGHTGRAY);
    rect5.setStroke(Color.BLACK);
    rect5.setStrokeWidth(10);

    Rectangle rect6 = new Rectangle();
    rect6.setX(500);
    rect6.setY(150);
    rect6.setWidth(975);
    rect6.setHeight(500);
    rect6.setFill(Color.LIGHTGRAY);
    rect6.setStroke(Color.BLACK);
    rect6.setStrokeWidth(10);

    Rectangle rect7 = new Rectangle();
    rect7.setX(800);
    rect7.setY(700);
    rect7.setWidth(300);
    rect7.setHeight(100);
    rect7.setFill(Color.LIGHTGRAY);
    rect7.setStroke(Color.BLACK);
    rect7.setStrokeWidth(10);

    Circle circ = new Circle(1300.0f, 745.0f, 60.f);
    circ.setStroke(Color.FORESTGREEN);
    circ.setStrokeWidth(6);
    circ.setFill(Color.GRAY);

    Rectangle rect8 = new Rectangle();
    rect8.setX(1200);
    rect8.setY(745);
    rect8.setWidth(200);
    rect8.setHeight(10);
    rect8.setFill(Color.FORESTGREEN);

    Rectangle rect9 = new Rectangle();
    rect9.setX(1295);
    rect9.setY(660);
    rect9.setWidth(10);
    rect9.setHeight(170);
    rect9.setFill(Color.FORESTGREEN);

    Button goButton = new Button("GO!");
    goButton.setPrefHeight(100);
    goButton.setPrefWidth(300);
    goButton.setLayoutX(800);
    goButton.setLayoutY(700);

    Text text = new Text();
    text.setText("Start Location");
    text.setX(165);
    text.setY(175);
    text.setScaleX(2);
    text.setScaleY(2);


    Text text1 = new Text();
    text1.setText("End Location");
    text1.setX(175);
    text1.setY(375);
    text1.setScaleX(2);
    text1.setScaleY(2);

    Text text2 = new Text();
    text2.setText("Other Locations");
    text2.setX(165);
    text2.setY(575);
    text2.setScaleX(2);
    text2.setScaleY(2);

    Text text3 = new Text();
    text3.setText("(Seperated by \", \")");
    text3.setX(155);
    text3.setY(600);
    text3.setScaleX(1.5);
    text3.setScaleY(1.5);

    TextField box = new TextField();
    box.setLayoutX(125);
    box.setLayoutY(200);

    TextField box1 = new TextField();
    box1.setLayoutX(130);
    box1.setLayoutY(400);

    TextField box2 = new TextField();
    box2.setLayoutX(125);
    box2.setLayoutY(610);
    box2.setPrefHeight(150);

    Text text4 = new Text();
    text4.setText("Starting Location: " + startLocation);
    text4.setX(600);
    text4.setY(200);
    text4.setScaleX(1.5);
    text4.setScaleY(1.5);

    Text text5 = new Text();
    text5.setText("Ending Location Details: ");
    text5.setX(1200);
    text5.setY(200);
    text5.setScaleX(1.5);
    text5.setScaleY(1.5);

    Text text6 = new Text();
    text6.setText("Ending Location Details: ");
    text6.setX(1200);
    text6.setY(300);
    text6.setScaleX(1.5);
    text6.setScaleY(1.5);

    Text text7 = new Text();
    text7.setText("Your Recomended Route:");
    text7.setX(600);
    text7.setY(300);
    text7.setScaleX(1.5);
    text7.setScaleY(1.5);

    Text text8 = new Text();
    text8.setText("Route to hit all stops:");
    text8.setX(600);
    text8.setY(400);
    text8.setScaleX(1.5);
    text8.setScaleY(1.5);

    Text text9 = new Text();
    text9.setText("CampusGPS");
    text9.setX(150);
    text9.setY(50);
    text9.setScaleX(3.5);
    text9.setScaleY(3.5);
    text9.setFill(Color.FORESTGREEN);

    Group group = new Group(rect, rect1, rect2, rect3, rect4, rect5, rect6, rect7, circ, rect8,
        rect9, goButton, text, text1, text2, text3, text4, text5, text6, text7, text8, text9, box,
        box1, box2);
    try {
      Scene scene = new Scene(group, 600, 300);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


      stage.setTitle("CampusGPS");
      stage.setFullScreen(true);
      stage.setScene(scene);
      stage.show();



    } catch (Exception e) {
      e.printStackTrace();
    }

    /**
     * Event Handler that occurs on the click of the go button and then takes data from the user
     * input and then proceeds to use it as information for the other methods of the application
     */
    EventHandler<ActionEvent> onGo = new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        String startLocation = box.getText();
        String endLocation = box1.getText();
        String extraStops = box2.getText();

        text4.setText("Starting Location: " + startLocation);
        text5.setText(endLocation + " Details:");
        CampusGPSApp a = new CampusGPSApp();
        String details = a.getEndLocationDetails(endLocation);
        //System.out.println(a.getEndLocationDetails(endLocation));
        text6.setText(details);
        text7.setText("Your Recomended Route:\n" + a.getPath(startLocation, null, endLocation));
        text8.setText("Route to hit all stops:\n" + a.getMST());

        System.out.println(startLocation + ", " + extraStops + endLocation);
      }
    };

    goButton.setOnAction(onGo);

  }

  public static void main(String[] args) {
    launch(args);
  }
}
