import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is used to generate the Dashboard page.
 */

/**
 * @author Ong Jun Quan
 */

public class Dashboard{
	/**
	* This method generate the dashboard scene
	*
	* @param stage
	*	method receives stage 
	* @return scene
	* 	scene for stage
	*/
	public Scene getDashboard(Stage stage) throws Exception{
		stage.setTitle("Dashboard");
		Pane root = new Pane();// root pane 
		
		//Setting Background Image
		ImageView imgview = CommonElements.getBackgroundImage();
		imgview.setFitWidth(1440);
		imgview.setFitHeight(960);
		root.getChildren().add(imgview);
		
		//Create Header
		Pane header = new Pane();
		ImageView imgview2 = CommonElements.getHeaderImage();
		imgview2.setLayoutY(0);
		header.getChildren().add(imgview2);
		header.setLayoutY(0);
		root.getChildren().add(header);
		
		// Title on header
		Pane title = new Pane();
		ImageView imgview3 = CommonElements.getTitleImage();
		imgview3.setLayoutX(542);
		imgview3.setLayoutY(26);
		title.getChildren().add(imgview3);
		root.getChildren().add(title);
		
		// Center content
		Pane centTop = new Pane();
    		StackPane centerTop = new StackPane();
    		centerTop.setPadding(new Insets(185,50,50,400));	
		GridPane ctop1 = new GridPane();
		ctop1.setPadding(new Insets(70, 50,50,15));
		ctop1.setHgap(40);
		
		ImageView imgview10 = CommonElements.getElementView("TopDashboard/4.png");
		imgview10.setFitHeight(100);
		imgview10.setFitWidth(100);
		GridPane.setRowSpan(imgview10, 5);
		ctop1.add(imgview10, 0, 0);
		
		ImageView imgview11 = CommonElements.getElementView("LeftDashboard/4.png");
		imgview11.setFitHeight(80);
		imgview11.setFitWidth(80);
		GridPane.setRowSpan(imgview11, 5);
		ctop1.add(imgview11, 4, 0);
		
		ImageView imgview12 = CommonElements.getElementView("LeftDashboard/5.png");
		imgview12.setFitHeight(90);
		imgview12.setFitWidth(90);
		GridPane.setRowSpan(imgview12, 5);
		ctop1.add(imgview12, 8, 0);
		
		centerTop.getChildren().add(ctop1);
		
		ImageView imgview13 = CommonElements.getElementView("TopDashboard/5.png");
		imgview13.setFitWidth(960);
		imgview13.setFitHeight(180);
		
		centerTop.getChildren().add(imgview13);
		
		centTop.getChildren().add(centerTop);
		
		Text ctText1 = new Text(Integer.toString(CommonElements.getNowShowingNo())); // get number of movie showing then change to String type
		Text ctText2 = new Text("movie showing today");
		ctText1.setFont(Font.font(28));
		ctText2.setFont(Font.font(14));
		ctText1.setFill(Color.WHITE);
		ctText2.setFill(Color.WHITE);
		ctop1.add(ctText1, 1, 3);
		ctop1.add(ctText2, 1, 4);
		Text ctText3 = new Text(Integer.toString(getTotalBookingMade())); // get number of booking made then change to String type
		Text ctText4 = new Text("booking made today");
		ctText3.setFont(Font.font(28));
		ctText4.setFont(Font.font(14));
		ctText3.setFill(Color.WHITE);
		ctText4.setFill(Color.WHITE);
		ctop1.add(ctText3, 5, 3);
		ctop1.add(ctText4, 5, 4);
		Text ctText5 = new Text("6");
		Text ctText6 = new Text("halls");
		ctText5.setFont(Font.font(28));
		ctText6.setFont(Font.font(14));
		ctText5.setFill(Color.WHITE);
		ctText6.setFill(Color.WHITE);
		ctop1.add(ctText5, 9, 3);
		ctop1.add(ctText6, 9, 4);
		
		root.getChildren().add(centTop); 
		
		// 2nd heading
		HBox headingContainer = new HBox();
		headingContainer.setPadding(new Insets(400,50,50,430));
		Text heading1 = new Text("Now Showing:");
		heading1.setFont(Font.font(45));
		heading1.setStyle("-fx-font-weight: bold;");
		heading1.setFill(Color.WHITE);
		
		headingContainer.getChildren().add(heading1);
		root.getChildren().add(headingContainer);
		
		// Now Showing (Bottom)
		ScrollPane container = new ScrollPane();
		container.setPrefHeight(330);
		container.setPrefWidth(905);
		container.setPadding(new Insets(50,50,50,50));
		container.setStyle("-fx-background-color : transparent; -fx-background: transparent; ");
		container.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		StackPane content = new StackPane();
		content.setPadding(new Insets(506,50,50,450));
		
		StackPane nowShowing = new StackPane();
		nowShowing.setPadding(new Insets(450,50,50,395));
		
		FileInputStream imageStream14 = new FileInputStream("LeftDashboard/container.png");
		Image image14 = new Image(imageStream14);
		ImageView imgview14 = new ImageView(image14);
		imgview14.setFitWidth(1000);
		imgview14.setFitHeight(440);
		nowShowing.getChildren().add(imgview14);
		
		
		container.setContent(displayNowShowing()); // Calling the VBox to show content in nowshowing
		content.getChildren().add(container);// Content containing the scrollpane
		root.getChildren().add(nowShowing);
		root.getChildren().add(content);
		
		root.getChildren().add(CommonElements.getMenuBar(stage));
		Scene scene = new Scene(root, 1440, 960);
		return scene;
	}
	
	/*
	 * This method create the content of nowshowing in dashboard
	 *
	 * @return moviesInfo : a VBox
	 */
	public VBox displayNowShowing() throws Exception{
		System.out.println("Getting Movies Information...");
		
		VBox moviesInfo = new VBox(30);
		moviesInfo.setStyle("-fx-background: transparent; -fx-background-color: transparent");
		/*
		 * Declaring size of hbox and Gridpane according to the number of movie airing
		 */
		HBox[] movieBox = new HBox[CommonElements.getNowShowingNo()];
		GridPane[] gpane = new GridPane[CommonElements.getNowShowingNo()];
		
		int j = 0;
		java.io.File file = new java.io.File("MovieDataSource/Movie.txt");
		Scanner input = new Scanner(file);
		
		while(input.hasNext()){
			String line = input.nextLine();
			String[] section = line.split(",");
			movieBox[j] = new HBox();
			movieBox[j].setStyle("-fx-background: transparent; -fx-background-color: #336699");
			gpane[j] = new GridPane();
			gpane[j].setVgap(15);
			gpane[j].setHgap(30);

			for(int i = 1; i < 8; i++){
				Label lbl = new Label(section[i]);
				lbl.setFont(Font.font(20));
				/*
				 * Second section of the line is always Movie Name
				 * Checking if the movie is in fast selling
				 * If the movie is in fast selling, Prompt fast selling and change text color
				 */
				if(i == 1){
					if(isFastSellingMovie(lbl.toString())){
						Label lbl2 = new Label("Fast Selling!");
						lbl.setStyle("-fx-text-fill : Yellow");
						lbl.setFont(Font.font(null, FontWeight.BOLD, 20));
						lbl2.setStyle("-fx-text-fill : Yellow");
						lbl2.setFont(Font.font(null, FontWeight.BOLD, 20));
						gpane[j].add(lbl2, 3,0);
					}
					gpane[j].add(lbl, 0, 0);
					
					GridPane.setColumnSpan(lbl, 3);
				}
				else{
					gpane[j].add(lbl, i-1, 1);
				}
					
			}
			 movieBox[j].getChildren().add(gpane[j]);
			 moviesInfo.getChildren().add(movieBox[j]);
			j++;
		}
		return moviesInfo;
	}
	
	/*
	 * This method Calculate the total booking made 
	 *
	 * @return tot : total with int as data type
	 */
	public int getTotalBookingMade() throws Exception{
		int tot = 0;
		String movieName = "";
		
		File file = new File("MovieDataSource/bookingmade.txt");
		
		if(file.exists()){
    		System.out.println("File Opened! Calculating number of movies booked");
    		Scanner input = new Scanner(file);

    		while(input.hasNextLine()){
    			String line = input.nextLine();	// Store a line from txt file
    			String[] section = line.split(";");	// Split each line with regex ";" to each section store in an element of array
    			movieName = section[0];	// Movie name is the first section for each line
    			
    			for(int i = 1; i < section.length; i++){
    				tot += Integer.parseInt(section[i]);// increament total
    			}
    		}
    	input.close();
    	}else{
    		System.out.println("File not found, No graph to be shown");
    	}
		System.out.println("Booking made: " +tot);
		return tot; // Return total number of booking made 
	}

	/*
	 * This method checks which movie is in fast Selling
	 * 
	 * @param movieName 
	 * 	mtehod receive name of movie
	 * @return checkIsSellingFast : a boolean flag
	 *
	 */
	public static boolean isFastSellingMovie(String movieName) throws Exception{
		Map<String,List<Integer>> data = CommonElements.getMoviesBooked(); // Get the map of data
		boolean checkIsSellingFast = false;
		int diff = 0;
		int[] value = new int[2];
		
		for(String key : data.keySet()){
			int j = 0;
			for(Integer i : data.get(key)){
				value[j] = i;
				j++;
			}
			diff = value[1] - value[0];
			System.out.println(key +"'s booking difference is: " +Math.abs(diff));
			/*
			 * Check if the movie name is the same as the key
			 * Check if the difference of Yesterday booking and Today booking is +30 and above
			 * If yes then the movie is in fast selling
			 * If no then continue until all data is compared 
			 * If no key is matched return false means nothing is in fast selling
			 */
			if(movieName.contains(key) && diff >= 30){
				System.out.println("Fast Selling movie are: " +key);
				return checkIsSellingFast = true;
			}
			else
				continue;
		}
		return checkIsSellingFast;
	}
}
