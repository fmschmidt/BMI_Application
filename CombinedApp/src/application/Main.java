package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;
import view.OverviewController;
import view.RootViewController;
import controller.OverviewActionController;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    OverviewActionController overviewController;

    /**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personDataToRetrieve = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main() {
        // Add some sample data
    	overviewController = new OverviewActionController();

    }



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Calculator - Combined View Objects");

        initRootLayout();

        showPersonOverview();

        //show javafx version
        //System.out.println("javafx.runtime.version: " + System.getProperties().get("javafx.runtime.version"));
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootView.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

         // Give the controller access to the main app.
            RootViewController controller = loader.getController();
            controller.setMainApp(this, overviewController);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/Overview.fxml"));
            AnchorPane overview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(overview);

            // Give the controller access to the main app.
            OverviewController controller = loader.getController();
            controller.setMainApp(this, overviewController);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }



    public static void main(String[] args) {
        launch(args);
    }
}