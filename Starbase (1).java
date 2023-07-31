

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
//import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;


public class Starbase extends Application {
    private Starship starType;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("StarBase Command");

        //add the space image to the background
        Image image = new Image("space.jpg");
        ImageView view = new ImageView(image);
        StackPane stack = new StackPane();
        BorderPane pane = new BorderPane();

        //HEADER ON TOP!
        Label welcome = new Label("Welcome to StarBase 1331!");
        welcome.setContentDisplay(ContentDisplay.TOP); //this method is only for labels
        Font font = new Font("Times New Roman", 50);
        welcome.setTextFill(Color.WHITE);
        welcome.setStyle("-fx-font-weight: bold");
        welcome.setFont(font);
        pane.setAlignment(welcome, Pos.CENTER);
        pane.setTop(welcome);

        // 8 DOCKING STATIONS!
        HBox first = new HBox();
        first.setSpacing(20);
        ArrayList<Button> dock1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            button.setText("Empty");
            button.setFont(new Font(30));
            button.setMinSize(200, 200);
            button.setStyle("-fx-background-color: GREEN");
            dock1.add(button);
            button.setOnAction((ActionEvent e) -> {
                //@Override
                button.setText("Empty");
                button.setStyle("-fx-background-color: GREEN");
            });
        }
        first.getChildren().addAll(dock1);
        first.setAlignment(Pos.CENTER);
        HBox second = new HBox();
        second.setSpacing(20);
        ArrayList<Button> dock2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            button.setText("Empty");
            button.setFont(new Font(30));
            button.setMinSize(200, 200);
            button.setStyle("-fx-background-color: GREEN");
            dock2.add(button);
            button.setOnAction((ActionEvent e) -> {
                //@Override
                button.setText("Empty");
                button.setStyle("-fx-background-color: GREEN");
            });
        }
        second.getChildren().addAll(dock2);
        second.setAlignment(Pos.CENTER);
        VBox squares = new VBox();
        squares.getChildren().addAll(first, second);
        squares.setSpacing(10);
        pane.setAlignment(squares, Pos.CENTER);
        pane.setCenter(squares);
        ArrayList<Button> bigDock = new ArrayList<>();
        bigDock.addAll(dock1);
        bigDock.addAll(dock2);
        //-------------------------------------------------------------
        //INPUT FIELDS ON BOTTOM
        //text field to enter name of incoming Starship
        HBox bottom = new HBox();
        bottom.setSpacing(10);
        final TextField name = new TextField();
        name.setMinSize(300, 40);
        name.setPromptText("Enter the name of the incoming starship");
        name.getText();
        //create dropdown menu
        ComboBox<Starship> dropDown = new ComboBox<Starship>();
        dropDown.getItems().addAll(Starship.values());
        dropDown.setMinSize(220, 40);
        //Docking button
        Button dock = new Button("Request Docking Clearance");
        dock.setMinSize(300, 40);
        dock.setFont(new Font(20));
        Button evac = new Button("Evacuate!");
        evac.setMinSize(150, 40);
        evac.setStyle("-fx-font-weight: bold");
        evac.setFont(new Font(25));
        evac.setStyle("-fx-background-color: RED");
        dropDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                starType = dropDown.getSelectionModel().getSelectedItem();
            }
        });
        dock.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                boolean exist = false;
                for (int i = 0; i < bigDock.size(); i++) {
                    if (bigDock.get(i).getText().equals("Empty")) {
                        exist = true;
                    }
                    if (exist) {
                        if (name.getText() != null && name.getText().trim().length() > 0
                                && starType != null) {
                            bigDock.get(i).setText(name.getText() + "\n" + starType);
                            Font size = new Font(23);
                            bigDock.get(i).setFont(size);
                            bigDock.get(i).setStyle("-fx-background-color: YELLOW");
                            name.clear();
                            dropDown.getSelectionModel().clearSelection();
                        }
                    } else if (name.getText() == null || name.getText().trim().length() <= 0
                        || starType == null) {
                        name.clear();
                        dropDown.getSelectionModel().clearSelection();
                        exist = true;
                    }
                }
                if (!(exist))  {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText(name.getText() + " did not receive docking clearance!");
                    Button retry = new Button("Try Again");
                    alert.showAndWait();
                    retry.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            name.clear();
                        }
                    });
                }
            }
        });
        evac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (Button b: bigDock) {
                    b.setText("Empty");
                    b.setStyle("-fx-background-color: GREEN");
                }
            }
        });
        bottom.getChildren().addAll(name, dropDown, dock, evac);
        pane.setAlignment(bottom, Pos.CENTER);
        pane.setBottom(bottom);

        stack.getChildren().addAll(view, pane);
        //SHOW PROGRAM!
        stage.setScene(new Scene(stack));
        stage.show();
    }
}
