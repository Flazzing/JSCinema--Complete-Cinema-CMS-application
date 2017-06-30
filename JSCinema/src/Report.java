import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is used to generate the Report page.
 */

/**
 * @author Ong Jun Quan
 */

public class Report {
	public Scene getReport(Stage stage) throws Exception{
		stage.setTitle("Report");
		System.out.println("Entered report page!");
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
    	
    	HBox graph = displayBarGraph(); 
    	graph.setPadding(new Insets(200,50,50,395));
    	
    	root.getChildren().add(graph);// Calling graph pane
    	root.getChildren().add(CommonElements.getMenuBar(stage));// calling menubar to root pane
		Scene scene = new Scene(root, 1440, 960);
		return scene;
	}
	
	// Store the data of booking made into a Map and return the map
	public Map<String, List<Integer>> getMoviesBooked() throws Exception{
		Map<String,List<Integer>> movieBooked = new HashMap<String,List<Integer>>();
		File file = new File("MovieDataSource/bookingmade.txt");
		
		String movieName = "";
		
		if(file.exists()){
    		System.out.println("File Opened! Calculating number of movies booked");
    		Scanner input = new Scanner(file);

    		while(input.hasNextLine()){
    			String line = input.nextLine();	// Store a line from txt file
    			String[] section = line.split(";");	// Split each line with regex ";" to each section store in an element of array
    			movieName = section[0];	// Movie name is the first section for each line
    			List<Integer> numberBooked = new ArrayList<Integer>();
    			
    			for(int i = 1; i < section.length; i++){
    				numberBooked.add(Integer.parseInt(section[i]));
    			}
    			movieBooked.put(movieName, numberBooked);
    		}
    	input.close();
    	}else{
    		System.out.println("File not found, No graph to be shown");
    	}
		System.out.println(movieBooked);
		return movieBooked;
	}
	
	public HBox displayBarGraph() throws Exception{
		HBox graph = new HBox();
    	
    	CategoryAxis xAxis = new CategoryAxis();
    	NumberAxis yAxis = new NumberAxis();
    	
    	xAxis.setLabel("Date");
    	yAxis.setLabel("Number of Booking");
    	xAxis.tickLabelFontProperty().set(Font.font(20));
    	xAxis.setTickLabelFill(Color.CYAN);
    	yAxis.tickLabelFontProperty().set(Font.font(20));
    	yAxis.setTickLabelFill(Color.CYAN);
    	
    	BarChart<String,Number> bc = 
                new BarChart<String,Number>(xAxis,yAxis);

    	bc.setTitle("Booking summary");
    	bc.setPrefHeight(600);
    	bc.setPrefWidth(1000);
    	bc.setStyle("-fx-font-size: 20px; -fx-text-fill: BLUE;"
    			+ "-fx-background-color : transparent; -fx-background: transparent; ");
    	
		Map<String,List<Integer>> data = getMoviesBooked(); // Get the map of data
    	Collection<List<Integer>> value = data.values();
    	
    	// 3 Key set, loop 3 times
    	for(String key : data.keySet()){
			int valueCounter = 0;	// set counter to default
			//Each key have 2 values, yesterday and today's booking
			for(Integer i : data.get(key)){
				//	First value is yyesterday's booking
				if(valueCounter == 0){
					System.out.println("Key is: " +key + " Data is: " +i);
					XYChart.Series series = getSeries(bc, key, i);
					series.setName("Yesterday");
				}
				// Second value is tomorrow's booking
				if(valueCounter == 1){
					System.out.println("Key is: " +key + " Data is: " +i);
					XYChart.Series series = getSeries(bc, key, i);
					series.setName("Today");
				}
				
				valueCounter++;
			}
		}
    	graph.getChildren().add(bc);
		return graph;
	}
	
	private XYChart.Series getSeries(BarChart<String, Number> bc, String key, Integer i) {
    	XYChart.Series series = new XYChart.Series();
    	
    	series.getData().add(new XYChart.Data(key, i));
    	bc.getData().add(series);
    	
		return series;
	}
	
}
