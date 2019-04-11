package application;


import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

class MainControllerTest {
	
	public void setUp() {
	MainController mcontroller = new MainController();
	}
	
	 @Test
	    public void testA() throws InterruptedException {
	        Thread thread = new Thread(new Runnable() {

	            @Override
	            public void run() {
	                new JFXPanel(); // Initializes the JavaFx Platform
	                Platform.runLater(new Runnable() {

	              // Create and initialize your app.
	                    @Override
	                    public void run() {
	                        new Main().start(new Stage()); 

	                    }
	                });
	            }
	        });
	        thread.start();// Initialize the thread
	        // Time to use the app, with out this, the thread will be killed before you can tell.
	        Thread.sleep(10000);
	    }
	 
	 
//	 @Test
//	 public void 
//	 
	 


}
