package application;


import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * This a bird photo Analyser program
 * @author manziwit
 * below are all the variables needed for the program
 */
public class MainController implements Initializable {
	protected static final String Stage = null;
	@FXML private MenuBar menuBar = new MenuBar();
	@FXML private ImageView imageView = new ImageView();
	@FXML private Menu menu1 = new Menu("File");
	@FXML private Menu menu2 = new Menu("Find Fake Birds");
	@FXML private Menu menu3 = new Menu("Advanced Noise Analysis");
	@FXML private MenuItem open = new MenuItem("Open");
	@FXML private MenuItem findrealBirds = new MenuItem("Find Real Birds");
	@FXML private MenuItem birdsNumbering= new MenuItem("Birds Numbering");
	@FXML private MenuItem details = new MenuItem("Details");
	@FXML private MenuItem exit = new MenuItem("Exit");
	@FXML private MenuItem bnwhite = new MenuItem("Black and White");
	@FXML private MenuItem fakebirdsrecognition = new MenuItem("Fake Birds Recognition");
	@FXML private MenuItem clearRectangles= new MenuItem("clear Rectangles");
	@FXML private MenuItem realBIrdsNumbering= new MenuItem("Real Birds Numbering");
	@FXML private MenuItem addRectangles= new MenuItem("Add Rectangles to Real Birds");
	@FXML private MenuItem clearFakeBirdsNumber= new MenuItem("clear Fake Birds Number");
	@FXML private Image image;
	@FXML private Stage stage = new Stage();
	@FXML private Label bValue,cValue,numberOfFakeBirds,contrast,brighteness,fakeBirds,realBirds,numberOfRealBirds;
	@FXML Slider bSlider = new Slider();
	@FXML Slider cSlider = new Slider();
	private File file ;
	private FileChooser fileChooser;
	private int pixelHolder[];
	private int rootHolder[];
	private int imageDimension;
	private ArrayList<Text> birdList;
	private Rectangle rec;
	private WritableImage grayImage;
	private Set<Integer> s;
	private Set<Integer> s2;
	@FXML private AnchorPane pane;
	@FXML private ScrollPane sPane;
	@FXML private Text t;
	Main main =new Main();


	/**
	 * below is the function that initialises the variables when the program run
	 * on the first instance
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuBar.getMenus().addAll(menu1,menu2,menu3);
		menu1.getItems().addAll(open,details,bnwhite);
		menu2.getItems().add(fakebirdsrecognition);
		menu3.getItems().add(findrealBirds);
		menu2.getItems().add(birdsNumbering);
		menu2.getItems().add(clearRectangles);
		menu1.getItems().add(exit);
		menu3.getItems().add(realBIrdsNumbering);
		open.setDisable(false);
		details.setDisable(true);
		fakebirdsrecognition.setDisable(true);
		bnwhite.setDisable(true);
		cSlider.setVisible(false);
		bSlider.setVisible(false);
		findrealBirds.setDisable(true);
		birdsNumbering.setDisable(true);
		realBIrdsNumbering.setDisable(true);
		clearRectangles.setDisable(true);
		contrast.setVisible(false);
		brighteness.setVisible(false);
		bValue.setVisible(false);
		cValue.setVisible(false);
		bSlider.setMin(-1);
		cSlider.setMin(-1);
		bSlider.setMax(1);
		cSlider.setMax(1);
		numberOfFakeBirds.setVisible(false);
		fakeBirds.setVisible(false);
		numberOfRealBirds.setVisible(false);
		realBirds.setVisible(false);

		/**
		 * this an action that runs when i click on the open option in the menu bar
		 */
		open.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fileChooser = new FileChooser();
				FileChooser.ExtensionFilter ALLIMG = new FileChooser.ExtensionFilter("All Image Files", "*.jpg","*.jpeg","*.png");
				fileChooser.getExtensionFilters().add(ALLIMG);
				fileChooser.setTitle("Open Image File");
				file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					image = new Image(file.toURI().toString(),imageView.getFitWidth(),imageView.getFitHeight(),true,false);
					//System.out.print(image.impl_getUrl());
					imageDimension = (int) (image.getWidth()*image.getHeight());
					pixelHolder=  new int [imageDimension];
					for(int id=0;id<pixelHolder.length;id++) 
					{
						pixelHolder[id]=id;
						System.out.print(pixelHolder[id] + " ");

					}
					System.out.println();

					imageView.setImage(image);

					errorHandling();
				}

			}


		});


		/**
		 * this is an action that will display the details of the image when clicked
		 */
		details.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Image Information");
				alert.setHeaderText(null);
				alert.setContentText("Name:" + file.getName() + "\nSize: " + file.length() + "\nDimensions:" + image.getWidth() + "x" + image.getHeight() + "\nPath : " + file.getAbsolutePath());
				alert.showAndWait();
			}

		});
		/**
		 * this is an action that will turn the image into black and white once clicked
		 */
		bnwhite.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PixelReader pixelReader = image.getPixelReader();
				int width = (int) image.getWidth();
				int height = (int) image.getHeight();
				grayImage = new WritableImage(width,height);
				PixelWriter pixelWriter = grayImage.getPixelWriter();


				for(int y = 0;y<height;y++) {
					for(int x = 0;x<width;x++) {
						int pixel = pixelReader.getArgb(x, y);
						int red = ((pixel >> 16) & 0xff);
						int green = ((pixel >> 8) & 0xff);
						int blue = (pixel & 0xff);
						int grayLevel = (red + green + blue) /3;
						grayLevel = grayLevel<108 ? 0 : 255;
						int gray = 0xFF000000 | (grayLevel << 16) | (grayLevel << 8) | grayLevel;
						pixelWriter.setArgb(x, y, gray);
					}
				}
				imageView.setImage(grayImage);

			}

		});
		/**
		 * this is an action that will run other function for example unionPixel 
		 * Getting pixel off the image and creating the roots and then drawing 
		 * rectangles after a union operation on the black pixels
		 */
		fakebirdsrecognition.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				pixelHolderStorage(grayImage);
				rootHolderStorage();
				unionPixel(grayImage);
				getNumberofRoot();
				displayRootHolder();
				rectangles();

			}

		});
		/**
		 * this will run once your doing the analysis to find the real birds
		 * this checks if the root is a bird or not if it is a bird then the rectangle is added
		 * and the added to a new HashSet of real birds
		 */
		findrealBirds.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				brighenessAndContrast();
				BirdsOrNoBirds();

			}

		});
		
		/**
		 * this is a menu option that when pressed on exit the program
		 */
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				exit();

			}

		});

		/**
		 * this numbers the fake birds or all the roots or black pizel that have been
		 * unionised 
		 */
		birdsNumbering.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fakeBirds.setVisible(true);
				numberOfFakeBirds.setVisible(true);
				numbering();
				int bird = s.size();
				numberOfFakeBirds.setText(String.valueOf(bird));
				System.out.println( "number of birds : " + bird);
			}

		});

		/**
		 * this function clear the rectangles that have been added to the fake birds
		 * before making them again on the real birds
		 */
		clearRectangles.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				clearRectangle();

			}

		});

		/**
		 * this menu option once clicked will number the real birds on the 
		 * pane
		 */
		realBIrdsNumbering.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				realBirds.setVisible(true);
				numberOfRealBirds.setVisible(true);
				realBIrdsNumbering();
				int birds = s2.size();
				numberOfRealBirds.setText(String.valueOf(birds));
			}

		});

		/**
		 * this once clicked clears the numbers on the fake birds
		 * before applying them on top of real birds
		 */
		//		clearFakeBirdsNumber.setOnAction(new EventHandler<ActionEvent>() {
		//
		//			@Override
		//			public void handle(ActionEvent arg0) {
		//				clearNumbersOnFakeRectangle();
		//
		//			}
		//
		//		});



	}
	/**
	 * this function plays with the brightness and contrast of the image
	 */
	@FXML public void brighenessAndContrast() {
		brighteness.setVisible(true);
		contrast.setVisible(true);
		bValue.setVisible(true);
		cValue.setVisible(true);
		cSlider.setVisible(true);
		bSlider.setVisible(true);
		ColorAdjust colorAdjust = new ColorAdjust();
		bSlider.valueProperty().addListener(new
				ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> Observable, Number newValue, Number oldValue) {
				if(newValue != null) {
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					bValue.setText(String.valueOf(df.format(newValue)));
					colorAdjust.setBrightness(Double.valueOf(bValue.getText()));
					imageView.setEffect(colorAdjust);

				}

			}

		});
		cSlider.valueProperty().addListener(new
				ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> Observable, Number newValue, Number oldValue) {
				if(newValue != null) {
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					cValue.setText(String.valueOf(df.format(newValue)));
					colorAdjust.setContrast(Double.valueOf(cValue.getText()));
					imageView.setEffect(colorAdjust);
				}

			}

		});
	}

	/**
	 * this switched the black and white image to the original coloured image
	 */
	public void Original() {
		imageView.setImage(image);
	}

	/**
	 * this handles error that could happen if you try to do something before doing
	 * something that will allow you to so what you want
	 */

	public void errorHandling() {
		if(open.getOnAction() != null) {
			details.setDisable(false);
			bnwhite.setDisable(false);
			fakebirdsrecognition.setDisable(false);
			findrealBirds.setDisable(false);
			birdsNumbering.setDisable(false);
			clearRectangles.setDisable(false);
			realBIrdsNumbering.setDisable(false);

		}
	}

	/**
	 * this function labels the white pixels in the pixel holder to be -1
	 * and the other pixel they have their values
	 * @param grayimage
	 */
	public void pixelHolderStorage(Image grayimage) {
		PixelReader pixelReader = grayimage.getPixelReader();
		int width = (int) image.getWidth();
		int height =  (int) image.getHeight();
		int id = 0;

		for (int y=0;y<height;y++) {
			for(int i=0;i<width;i++) {
				if(((pixelReader.getArgb(y, i)) & 0xFFFFFF) == 0xFFFFFF)
					pixelHolder[id]=-1;
				//System.out.print(pixelHolder[id]+" ");


				id++;
			}
			//System.out.println();
		}
		//System.out.println();
	}

	/**
	 * this copies the pixels from pixel holder to the root holder
	 */
	public void rootHolderStorage() {
		rootHolder = new int [imageDimension];
		for(int i=0;i<rootHolder.length;i++) {
			rootHolder[i] = pixelHolder[i];
			if(i>0 && i%image.getWidth()==0) System.out.println();
			//System.out.print(find(i)+" "); //rootHolder[i]+ " ");

		}
		//System.out.println("\n-----");
	}

	/**
	 * this displays the root holder on the console
	 */
	public void displayRootHolder() {
		for(int i=0;i<pixelHolder.length;i++) {
			if(i>0 && i%image.getWidth()==0) System.out.println();
			//System.out.print(find(i)+" "); //rootHolder[i]+ " ");

		}
		//System.out.println("\n-----");
	}

	/**
	 * this is the function that union the pixel that are close to 
	 * each other e.g checks the left and down and if there is a
	 * pixel close to it then boom union that
	 * @param grayimage
	 */
	public void unionPixel(Image grayimage) {
		//PixelReader pixelReader = grayimage.getPixelReader();
		int width = (int) image.getWidth();
		int height =  (int) image.getHeight();


		for (int y = 0;y<height;y++) {
			for(int x = 0;x<width;x++) {
				int id= (y*width+x);

				if(find(id)!=-1) { //rootHolder[id] !=-1) {

					int id2=(y*width+x+1);
					int id3=((y+1)*width+x);

					if(x < width -1 && find(id2)!=-1) {  //((pixelReader.getArgb(x+1, y)) & 1) == 0) {
						unite(id,id2);
					}

					if(y < height -1 && find(id3)!=-1) { //((pixelReader.getArgb(x, y+1)) & 1) == 0) {
						unite(id,id3);
					}

				}
			}

		}

		for(int i = 0;i<rootHolder.length;i++) {
			if(i%width==0) { 
				//System.out.println();
				//System.out.print(rootHolder[i]+" ");
			}
		}

	}

	public int root(int i) {
		while (i != rootHolder[i]) {
			if(rootHolder[i] != -1) {
				rootHolder[i] = rootHolder[rootHolder[i]]; 
				i = rootHolder[i];
			}
			else return -1;
		}

		return i;
	}


//	public boolean find(int p,int q) {
//		return root(p)  == root(q);
//	}

	/**
	 * well this short function helps us find roots
	 * @param p
	 * @return
	 */
	public int find(int p) {
		return root(p);
	}


	/**
	 * this is the union function that takes in two different roots and unites them
	 * @param p
	 * @param q
	 */
	public void unite(int p, int q) {
		int i = root(p);
		int j = root(q);
		rootHolder[j] = rootHolder[i];
//		if (pixelHolder[i] < pixelHolder[j]) {
//			rootHolder[i] = j;
//			pixelHolder[j] += pixelHolder[i];
//		} else {
//			rootHolder[j] = i;
//			pixelHolder[i] += pixelHolder[j];
//		}
	}


	/**
	 * this function adds the available roots to the hashSet and if they are found to be duplicate
	 * only one is taken in as a hashset does not take in duplicate values
	 * @return
	 */
	public int getNumberofRoot() {
		s = new HashSet<Integer>();
		for(int i = 0;i<imageDimension;i++){
			if(rootHolder[i] != -1) {
				s.add(root(i));
			}
		}

		System.out.println("");
		for(int i= 0;i<rootHolder.length;i++) {

		}
		System.out.println("Number of birds : " + s.size());
		System.out.println("--------" + s + "----------");
		return s.size();


	}

	/**
	 * this draws the rectangle on the roots added to a HashSet
	 * and these are fake birds
	 */
	public void rectangles() {
		for(int k : s) {
			double top = image.getHeight();
			double left = image.getWidth();
			double bottom = 0;
			double right = 0;

			for(int i = 0;i<rootHolder.length;i++) {
				double y = i/(int)image.getWidth();
				double x =  i%(int)image.getWidth();

				if(find(i)==k) {
					if(x<left) left=x;
					if(x>right) right=x;
					if(y<top) top=y;
					if(y>bottom) bottom=y;

				}
			}

			rec = new Rectangle(left,top,right-left,bottom-top);
			k++;
			System.out.println("XX Bird Id: "+k+", Bounds: "+left+", "+top+", "+bottom+", "+right + ", area " + (right-left)*(bottom-top));
			rec.setFill(Color.TRANSPARENT);
			rec.setStroke(Color.RED);
			pane.getChildren().addAll(rec);
			imageView.setImage(grayImage);

		}
	}


	/**
	 * this numbers the fake birds on after the rectangles have 
	 * been added
	 */
	//@SuppressWarnings("unchecked")
	public void numbering() {		
		int counter = 1;

		for(int num : s) {
			double top = image.getHeight();
			double left = image.getWidth();
			double bottom = 0;
			double right = 0;

			for(int i = 0;i<rootHolder.length;i++) {
				double y = i/(int)image.getWidth();
				double x =  i%(int)image.getWidth();

				if(find(i)==num) {
					if(x<left) left=x;
					if(x>right) right=x;
					if(y<top) top=y;
					if(y>bottom) bottom=y;

				}
			}
			t = new Text(right + 2,top,String.valueOf(counter));
			counter++;
			birdList = new ArrayList<Text>();
			birdList.add(t);
			pane.getChildren().add(t);

		}
	}
	/**
	 *this is the function that clears the rectangles and numbering
	 *on the fake birds
	 */
	public void clearRectangle() {
		pane.getChildren().remove(1, s.size()+1);
		pane.getChildren().remove(1,birdList.size());

	}

	//	public void clearNumbersOnFakeRectangle() {
	//		pane.getChildren().remove(1,birdList.size());
	//	}



	/**
	 * this function checks using the area of the birds to check 
	 * if they are greater than a certain number and if they are 
	 * greater then i conclude that they are birds,real birds so the
	 * rectangles are drawn on them
	 */
	public void BirdsOrNoBirds() {
		double area;
		s2 = new HashSet<Integer>();

		for(int k : s) {
			double top = image.getHeight();
			double left = image.getWidth();
			double bottom = 0;
			double right = 0;

			for(int i = 0;i<rootHolder.length;i++) {
				double y = i/(int)image.getWidth();
				double x =  i%(int)image.getWidth();

				if(find(i)==k) {
					if(x<left) left=x;
					if(x>right) right=x;
					if(y<top) top=y;
					if(y>bottom) bottom=y;


				}
			}

			area = (right-left)*(bottom-top);
			if(area>=100) {
				rec = new Rectangle(left,top,right-left,bottom-top);
				k++;
				System.out.println("XX Bird Id: "+k+", Bounds: "+left+", "+top+", "+bottom+", "+right + ", area " + (right-left)*(bottom-top));
				rec.setFill(Color.TRANSPARENT);
				rec.setStroke(Color.RED);
				pane.getChildren().addAll(rec);
				imageView.setImage(grayImage);
				s2.add(k);
			}

		}
		System.out.println("number of birds "+ s2.size());
	}

	/**
	 * this function now numbers these so called real birds 
	 */
	public void realBIrdsNumbering() {		
		double area;
		int counter=1;
		s2 = new HashSet<Integer>();

		for(int k : s) {
			double top = image.getHeight();
			double left = image.getWidth();
			double bottom = 0;
			double right = 0;

			for(int i = 0;i<rootHolder.length;i++) {
				double y = i/(int)image.getWidth();
				double x =  i%(int)image.getWidth();

				if(find(i)==k) {
					if(x<left) left=x;
					if(x>right) right=x;
					if(y<top) top=y;
					if(y>bottom) bottom=y;


				}
			}

			area = (right-left)*(bottom-top);
			if(area>=100) {
				t = new Text(right + 2,top,String.valueOf(counter));
				counter++;
				pane.getChildren().add(t);
				s2.add(k);

			}
		}


	}
	
	public void exit() {
		
		//main.window.onCloseRequestProperty();
		main.window.close();

	}
}
