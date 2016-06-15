package presenter;

import java.io.File;

import application.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class MenuOptions {

	
	public void openFile(Main mainApp){
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			mainApp.loadPersonDataFromFile(file);
		}
	}
	
	
	public void saveFile(Main mainApp){
		File personFile = mainApp.getPersonFilePath();
		if (personFile != null) {
			mainApp.savePersonDataToFile(personFile);
		} else {
			saveFileAs(mainApp);
		}
	}
	
	public void saveFileAs(Main mainApp){
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}
	
	
	public void aboutMenu(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("BMI Calculator");
		alert.setHeaderText("About");
		alert.setContentText("Author: Felipe Schmidt\nBachelorarbeit\nHeadless Testing for Standalone User Interfaces\n\nView Object Pattern");

		alert.showAndWait();
	}
	
}
