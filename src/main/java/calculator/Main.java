package calculator;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        String fxmlFile = "/calcview.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("Мебельный калькулятор");
        primaryStage.setScene(new Scene(root, 603, 650));
        Controller controller = (Controller) loader.getController();
        Model model = new Model();
        controller.setModel(model);
        primaryStage.show();
        controller.setFields();
        model.newConnection();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
