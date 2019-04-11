package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;


public class Main extends Application {
	Stage window;
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			ScrollPane root = new ScrollPane();
			root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.getStyle();
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
//	public void shutdown() {
//		window.close();
//	}
}
