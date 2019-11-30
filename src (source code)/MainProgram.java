import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/* 
 * NAME: SIVA LIKITHA VALLURU 
 * NOTE: ALL THE IDENTIFIERS IN THE PROGRAM WILL BE INITIALIZED USING CAMEL-NOTATION.
 * NOTE 2: Usage of Lambda Expressions is high (to simplify and lessen overhead)
 */

public class MainProgram extends Application implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent arg0) {
	}

	// Initialize static variables. These variables are going to be used throughout
	// the program. They're being declared static to avoid unwanted polymorphism.

	static BorderPane border = new BorderPane(); // Layout for the window

	static String typeOfGraph = null, operation = null;
	static Color selectedColor = Color.BURLYWOOD;

	static ComboBox<String> graphBox = new ComboBox<String>();
	static ComboBox<String> colorBox = new ComboBox<String>();
	static ComboBox<String> operations = new ComboBox<String>();

	static Button generateGraph = new Button();
	/*
	 * static Button drawEdge = new Button(); static RadioButton insertVertex = new
	 * RadioButton(); static Button deleteEdge = new Button(); static RadioButton
	 * colorVertex = new RadioButton();
	 */
	static Button clearGraphColors = new Button();
	static Button clearScreen = new Button();
	static Button quitApplication = new Button();
	/*
	 * static Button draw = new Button(); static ToggleGroup toggleGroup = new
	 * ToggleGroup();
	 */
	static Button go = new Button();
	static int insertCounter = 0;

	static Button helpWindow = new Button();
	static Button tutorial = new Button();
	static Button howToUseGUI = new Button();

	// Terminology: in JavaFX, the 'window' is called the Stage.
	// And the 'stuff inside the window (content)' is called the Scene.

	static Tree drawTree = new Tree();
	static TwoTree draw2Tree = new TwoTree();
	static MOP drawMOP = new MOP();
	static K4Planar drawK4Planar = new K4Planar();
	static K4PlanarCubic drawK4PlanarCubic = new K4PlanarCubic();
	static K6PlanarCubic drawK6PlanarCubic = new K6PlanarCubic();
	static K8PlanarCubic drawK8PlanarCubic = new K8PlanarCubic();

	static ArrayList<Line> edges = new ArrayList<Line>();
	static ArrayList<Circle> vertices = new ArrayList<Circle>();
	static ArrayList<Color> listOfColors = new ArrayList<Color>();

	static int traverseIndex = 0; // used in the place of common "i" for "for loops" (in case of mouse event
									// within ANOTHER mouse event)

	// Variables for Tree class
	static boolean flag; // used in switch case
	// static boolean flag2;// used in addVertex()
	// static boolean flag3;// used in initialization()
//	static int index = -1; // used in switch case
//	static int index2 = -1; // used in addVertex()
//	static int index3 = -1;// used in initialization()
//	//static int count = -1;
	// static boolean colorFlag = true;
//	static Circle treeCircle = null;

	// Variables for 2-tree
	static int selectCounter = 0;
	static boolean selectFlag = false, selectFlag1 = false, selectFlag2 = false, adjacencyFlag = false;
	static double selectEvent1X, selectEvent1Y, selectEvent2X, selectEvent2Y;
	static Circle selectEventVertex1, selectEventVertex2;
	// static Color eventColor1, eventColor2;

	// variables for MOP class
//	static int counterMOP = 0;
	// static double xcoordMOP1, ycoordMOP1, xcoordMOP2, ycoordMOP2;
	// static double indicesMOP[] = new double[2];

	// Variables for K4-Planar
	static ArrayList<Polygon> triangles = new ArrayList<Polygon>();// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Polygon.html
	// static int indexForInsert, confirmIndex;
	// static double xcoord = 0, ycoord = 0;
	double newIndices[] = new double[3];
	static int counter = 0;
	static double xcoordinate1, ycoordinate1, xcoordinate2, ycoordinate2;
	static double[] drawIndices = new double[2];

	public static void main(String[] args) {
		launch(args); // Start JavaFX application.
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		mainStage.setTitle("Welcome to Graph Coloring!");
		border.setTop(addGraphColoring());
		border.setBottom(addHelpTopics());
		Scene mainScene = new Scene(border, 2000, 690, Color.LIGHTBLUE);
		mainStage.setScene(mainScene);

		// line.setVisible(true);
		// border.getChildren().add(line);

		// Reference: https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
		graphBox.getItems().addAll("Tree", "2-Tree", "Maximal Outer Planar Graph", "K4 Planar Graph",
				"K4 Planar Cubic Graph", "Planar Cubic Graph With 6 Vertices - Type I",
				"Planar Cubic Graph With 8 Vertices - Type I", "Planar Cubic Graph With 8 Vertices - Type II",
				"Planar Cubic Graph With 8 Vertices - Type III");
		graphBox.setPromptText("SELECT A TYPE OF GRAPH");
		graphBox.setMaxSize(250, 120);
		graphBox.setOnAction(event -> {
			switch (graphBox.getValue()) {
			case "Tree":
				typeOfGraph = "tree";
				break;
			case "2-Tree":
				typeOfGraph = "2tree";
				break;
			case "Maximal Outer Planar Graph":
				typeOfGraph = "MOP";
				break;
			case "K4 Planar Graph":
				typeOfGraph = "k4planar";
				break;
			case "K4 Planar Cubic Graph":
				typeOfGraph = "k4planarcubic";
				break;
			case "Planar Cubic Graph With 6 Vertices - Type I":
				typeOfGraph = "k6planarcubic";
				break;
			case "Planar Cubic Graph With 8 Vertices - Type I":
				typeOfGraph = "k8planarcubic1";
				break;
			case "Planar Cubic Graph With 8 Vertices - Type II":
				typeOfGraph = "k8planarcubic2";
				break;
			case "Planar Cubic Graph With 8 Vertices - Type III":
				typeOfGraph = "k8planarcubic3";
				break;
			}
			System.out.println(typeOfGraph);
		});

		colorBox.getItems().addAll("Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Purple", "Pink");
		colorBox.setPromptText("SELECT A COLOR");
		colorBox.setMaxSize(250, 120);
		colorBox.setOnAction(event -> {
			switch (colorBox.getValue()) {
			case "Red":
				selectedColor = Color.RED;
				break;
			case "Orange":
				selectedColor = Color.ORANGERED;
				break;
			case "Yellow":
				selectedColor = Color.YELLOW;
				break;
			case "Green":
				selectedColor = Color.LIME;
				break;
			case "Blue":
				selectedColor = Color.BLUE;
				break;
			case "Indigo":
				selectedColor = Color.INDIGO;
				break;
			case "Purple":
				selectedColor = Color.BLUEVIOLET;
				break;
			case "Pink":
				selectedColor = Color.HOTPINK;
				break;
			}
			System.out.println(selectedColor);
		});

		// line.setVisible(false);
		// border.getChildren().add(line);

		operations.getItems().addAll("None", "Insert Vertex", "Draw Edge", "Select Vertex", "Color Graph");
		operations.setMaxSize(250, 120);
		operations.setPromptText("SELECT AN OPERATION");
		operations.setOnAction(event -> {
			switch (operations.getValue()) {
			case "None":
				operation = "none";
				break;
			case "Insert Vertex":
				operation = "insert";
				break;
			case "Draw Edge":
				operation = "draw";
				break;
			case "Select Vertex":
				operation = "select";
				break;
			case "Color Graph":
				operation = "color";
				break;
			}
			System.out.println(operations);
		});

		generateGraph.setOnAction(event -> {
			if (typeOfGraph == null) {
				Stage errorStage = new Stage();
				errorStage.initOwner(mainStage);
				errorStage.setTitle("ERROR!");

				VBox errorDialogBox = new VBox(20);
				errorDialogBox.getChildren().addAll(new Text("Error: Please choose from a type of graph!"),
						new Text("If you need any help, please choose help topics from the bottom of the window."));

				Scene errorScene = new Scene(errorDialogBox, 500, 75);
				errorStage.setScene(errorScene);
				errorStage.show();

			}

			else {
				switch (typeOfGraph) {
				case "tree":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Insert Vertex");
					operations.getItems().remove("Select Vertex");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawTree.initialization();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;
				/*
				 * Circle circle = drawTree.initialization(); border.getChildren().add(circle);
				 * Line line = new Line();
				 */
				/*
				 * drawEdge.setOnAction(e -> { line.setVisible(true);
				 * circle.setOnMousePressed(ev -> { line.setStartX(circle.getCenterX());
				 * line.setStartY(circle.getCenterY()); });
				 * 
				 * circle.setOnMouseReleased(ev -> { for (int i = 0; i < vertices.size(); i++) {
				 * if (vertices.get(i).contains(line.getEndX(), line.getEndY())) { flag = true;
				 * index = i; System.out.println("FLAG=TRUE"); break; } } if (flag) {
				 * System.out.println("INSIDE"); flag = false; line.setVisible(false); Line draw
				 * = new Line(circle.getCenterX(), circle.getCenterY(),
				 * vertices.get(index).getCenterX(), vertices.get(index).getCenterY());
				 * border.getChildren().add(draw); } else { line.setVisible(true); Circle
				 * circle2 = drawTree.addVerticesAndEdges(ev.getX(), ev.getY());
				 * border.getChildren().add(circle2); System.out.println("MOUSE RELEASE"); Line
				 * draw = new Line(circle.getCenterX(), circle.getCenterY(), ev.getX(),
				 * ev.getY()); border.getChildren().add(draw); } });
				 * 
				 * circle.setOnMouseDragged(ev -> { line.setEndX(ev.getX());
				 * line.setEndY(ev.getY()); line.setVisible(true); });
				 * 
				 * }); colorVertex.setOnAction(e -> { for (int i = 0; i < vertices.size(); i++)
				 * { if (i == 0) count = 0; count++; System.out.println("INSIDE COLOR");
				 * vertices.get(count).setOnMousePressed(ev -> {
				 * vertices.get(count).setFill(selectedColor); }); } });
				 * 
				 * 
				 * // border.getChildren().add(line);
				 */

				case "2tree":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Draw Edge");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					draw2Tree.initialization();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;

				case "MOP":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Draw Edge");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawMOP.initialization();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;

				case "k4planar":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Select Vertex");
					operations.getItems().remove("Draw Edge");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawK4Planar.initialization();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;

				case "k4planarcubic":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Select Vertex");
					operations.getItems().remove("Insert Vertex");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawK4PlanarCubic.initialization();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;
				case "k6planarcubic":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Select Vertex");
					operations.getItems().remove("Insert Vertex");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawK6PlanarCubic.initialization();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;
				case "k8planarcubic1":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Select Vertex");
					operations.getItems().remove("Insert Vertex");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawK8PlanarCubic.initializationTypeI();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;

				case "k8planarcubic2":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Select Vertex");
					operations.getItems().remove("Insert Vertex");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawK8PlanarCubic.initializationTypeII();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;

				case "k8planarcubic3":
					colorBox.setDisable(false);
					clearGraphColors.setDisable(false);
					clearScreen.setDisable(false);
					operations.setDisable(false);
					go.setDisable(false);

					operations.getItems().remove("Select Vertex");
					operations.getItems().remove("Insert Vertex");

					vertices = new ArrayList<Circle>();
					edges = new ArrayList<Line>();
					drawK8PlanarCubic.initializationTypeIII();
					for (int i = 0; i < vertices.size(); i++)
						border.getChildren().add(vertices.get(i));
					for (int i = 0; i < edges.size(); i++)
						border.getChildren().add(edges.get(i));
					break;
				}
			}

		});

		go.setOnMouseClicked(event -> {
			System.out.println(operation);
			if (operation == "none" || operation == null) {
				Stage errorStage = new Stage();
				errorStage.initOwner(mainStage);
				errorStage.setTitle("ERROR!");

				VBox errorDialogBox = new VBox(20);
				errorDialogBox.getChildren().addAll(new Text("Error: Please choose an operation from the list!"),
						new Text("If you need any help, please choose help topics from the bottom of the window."));

				Scene errorScene = new Scene(errorDialogBox, 500, 75);
				errorStage.setScene(errorScene);
				errorStage.show();
			}

			else {
				switch (operation) {
				case "color": {
					mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent mouseEvent) {
							int index = vertices.size() + 1;
							boolean flag = true;
							/*
							 * Call graphColoring() method. If the coordinate that the user clicked on
							 * matches a vertex in the list of vertices, then call graphColoring() method.
							 */
							for (traverseIndex = 0; traverseIndex < vertices.size(); traverseIndex++) {
								if (vertices.get(traverseIndex).contains(mouseEvent.getX(), mouseEvent.getY())) {
									index = traverseIndex;
									graphColoring(vertices.get(traverseIndex).getCenterX(),
											vertices.get(traverseIndex).getCenterY());
								}
							}

							/*
							 * In the case where no colors are used yet (all vertices are nill of any
							 * color), then don't bother checking with any conditions.
							 */
							if (listOfColors.size() == 0) {
								vertices.get(index).setFill(selectedColor);
							}
							/*
							 * ELSE: listOfColors contains the list of colors that the user used so far in
							 * all of the vertices.
							 */
							else {
								for (traverseIndex = 0; traverseIndex < listOfColors.size(); traverseIndex++) {
									/*
									 * If the user picks a color that was ALREADY assigned to the ADJACENT VERTICES
									 * OF THE SELECTED VERTEX, then don't let user perform the action. Instead,
									 * display an error.
									 */
									if (selectedColor.equals(listOfColors.get(traverseIndex))) {
										Stage errorStage = new Stage();
										errorStage.initOwner(mainStage);
										errorStage.setTitle("ERROR!");

										VBox errorDialogBox = new VBox(20);
										errorDialogBox.getChildren()
												.addAll(new Text("Error: Please choose another color!"), new Text(
														"If you need any help, please choose help topics from the bottom of the window."));
										Scene errorScene = new Scene(errorDialogBox, 500, 75);
										errorStage.setScene(errorScene);
										errorStage.show();
										flag = false;
										break;
									}
								}
							}
							if (flag) {
								vertices.get(index).setFill(selectedColor);
							}
							boolean checkIfGraphIsCompletelyColored = true;
							Set<Color> usedColors = new LinkedHashSet<Color>();
							for (int i = 0; i < vertices.size(); i++) {
								if (vertices.get(i).getFill().equals(Color.BURLYWOOD))
									checkIfGraphIsCompletelyColored = false; // if even one node isn't colored.
								else
									usedColors.add((Color) vertices.get(i).getFill());
							}

							/*
							 * This project assumes that the maximum number of colors to be used for
							 * efficient graph coloring is FOUR.
							 */
							if (checkIfGraphIsCompletelyColored) {
								if (usedColors.size() > 4) { // if user used more than 4 colors
									Stage errorStage = new Stage();
									errorStage.initOwner(mainStage);
									errorStage.setTitle("Advice");
									VBox errorDialogBox = new VBox(20);
									errorDialogBox.getChildren()
											.addAll(new Text("Try to color the graph in fewer colors."), new Text(
													"You may refer to help related to graph coloring at the bottom of the window."));
									Scene errorScene = new Scene(errorDialogBox, 500, 75);
									errorStage.setScene(errorScene);
									errorStage.show();
								} else if (usedColors.size() <= 4) { // if user used less than 5 colors
									Stage errorStage = new Stage();
									errorStage.initOwner(mainStage);
									errorStage.setTitle("Good job!");
									VBox errorDialogBox = new VBox(20);
									errorDialogBox.getChildren().addAll(
											new Text("You've colored the entire graph using rules of graph coloring!"),
											new Text("Well done!"));
									Scene errorScene = new Scene(errorDialogBox, 500, 75);
									errorStage.setScene(errorScene);
									errorStage.show();
								}
							}
							mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
						}
					});
				}
					break;

				case "insert":
					switch (typeOfGraph) {
					case "2tree":
						mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent event) {
								if (selectFlag) // 2 adjacent vertices have been selected.
								{
									if (adjacencyFlag) { // vertices ARE adjacent
										// therefore, call the method to insert vertex and draw edges.

										/*
										 * drawIndices[0] = index where new vertex is vertices[] is present.
										 * drawIndices[1] = indices where the addition of new edges in edges[] starts
										 * from.
										 */

										drawIndices = draw2Tree.insertVertexAndDrawEdges(event.getX(), event.getY());
										for (int i = (int) drawIndices[0]; i < vertices.size(); i++)
											border.getChildren().add(vertices.get(i));
										for (int i = (int) drawIndices[1]; i < edges.size(); i++)
											border.getChildren().add(edges.get(i));
										mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
									} else {
										// if vertices are NOT adjacent => call error dialog box.
										Stage errorStage = new Stage();
										errorStage.initOwner(mainStage);
										errorStage.setTitle("ERROR!");

										VBox errorDialogBox = new VBox(20);
										errorDialogBox.getChildren().addAll(
												new Text("Error: Selected vertices are not adjacent!"), new Text(
														"If you need any help, please choose help topics from the bottom of the window."));

										Scene errorScene = new Scene(errorDialogBox, 500, 75);
										errorStage.setScene(errorScene);
										errorStage.show();
										mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
									}
								}
								mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
							}
						});
						break;
					case "MOP":
						mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								if (selectFlag) // 2 adjacent vertices have been selected.
								{
									if (adjacencyFlag) { // if vertices are adjacent
										// if new additions are outerplanar
										if (drawMOP.checkForOuterPlanarity(event.getX(), event.getY())) {
											System.out.println("OUTERPLANAR!!!");
											drawIndices = drawMOP.insertVertexAndDrawEdges(event.getX(), event.getY());
											for (int i = (int) drawIndices[0]; i < vertices.size(); i++)
												border.getChildren().add(vertices.get(i));
											for (int i = (int) drawIndices[1]; i < edges.size(); i++)
												border.getChildren().add(edges.get(i));
											mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
										}
										// if not outerplanar, initiate a dialog box.
										else {
											Stage errorStage = new Stage();
											errorStage.initOwner(mainStage);
											errorStage.setTitle("ERROR!");

											VBox errorDialogBox = new VBox(20);
											errorDialogBox.getChildren().addAll(
													new Text("Error: Outerplanarity is not preserved! Try again!"),
													new Text(
															"If you need any help, please choose help topics from the bottom of the window."));
											Scene errorScene = new Scene(errorDialogBox, 500, 75);
											errorStage.setScene(errorScene);
											errorStage.show();
											mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
										}
									}
									// if not adjacent at all, initiate a dialog box.
									else {
										Stage errorStage = new Stage();
										errorStage.initOwner(mainStage);
										errorStage.setTitle("ERROR!");

										VBox errorDialogBox = new VBox(20);
										errorDialogBox.getChildren().addAll(
												new Text("Error: Selected vertices are not adjacent!"), new Text(
														"If you need any help, please choose help topics from the bottom of the window."));
										Scene errorScene = new Scene(errorDialogBox, 500, 75);
										errorStage.setScene(errorScene);
										errorStage.show();
										mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
									}
								}
								mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
							}
						});
						break;
					case "k4planar": {
						mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent mouseEvent) {
								System.out.println("mouse click detected! " + mouseEvent.getSource());
								newIndices = drawK4Planar.splitIntoTriangles(mouseEvent.getSceneX(),
										mouseEvent.getSceneY());
								for (int i = (int) newIndices[0]; i < vertices.size(); i++)
									border.getChildren().add(vertices.get(i));
								for (int i = (int) newIndices[1]; i < edges.size(); i++)
									border.getChildren().add(edges.get(i));
								mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
							}
						});
						break;
					}
					}
					break;

				case "draw":
					switch (typeOfGraph)

					{
					case "tree":
						mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								System.out.println("INSIDE DRAW TREE EDGE");
								double positions[] = drawTree.addVerticesAndEdges(event.getX(), event.getY());
								System.out.println("AFTER INDICE");
								for (int i = (int) positions[0]; i < vertices.size(); i++)
									border.getChildren().add(vertices.get(i));
								for (int i = (int) positions[1]; i < edges.size(); i++)
									border.getChildren().add(edges.get(i));
								System.out.println("Drawn");
								mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
							}

						});
						break;
					case "k4planarcubic":
					case "k6planarcubic":
					case "k8planarcubic1":
					case "k8planarcubic2":
					case "k8planarcubic3":
						mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								System.out.println("INSIDE HANDLE");
								if (counter == 0) {
									counter++;
									xcoordinate1 = event.getX(); // user made only 1 selection, so do nothing
									ycoordinate1 = event.getY();
									mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
								} else if (counter == 1) {
									counter = 0; // user made a second selection, so counter=0
									xcoordinate2 = event.getX();
									ycoordinate2 = event.getY();
									drawIndices = addVerticesAndEdges(xcoordinate1, ycoordinate1, xcoordinate2,
											ycoordinate2);
									for (int i = (int) drawIndices[0]; i < vertices.size(); i++)
										border.getChildren().add(vertices.get(i));
									for (int i = (int) drawIndices[1]; i < edges.size(); i++) {
										try {
											border.getChildren().add(edges.get(i));
										} catch (IllegalArgumentException e) {
										}
									}
									mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
								}

							}
						});
						break;
					}
					break;

				case "select":
					switch (typeOfGraph) {
					case "MOP":
					case "2tree":
						mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent mouseEvent) {
								/*
								 * If the user is selecting his FIRST vertex, then DON'T CALL THE ADJACENCY
								 * METHOD YET! Use selectCounter to keep track of user's activities. If
								 * selectCounter==0, then wait. Else, call method and make selectCounter=0;
								 */
								if (selectCounter == 0) {
									selectCounter++;
									selectEvent1X = mouseEvent.getX(); // selectEvent1 = coordinates of first event
									selectEvent1Y = mouseEvent.getY();

									for (int i = 0; i < vertices.size(); i++) {
										if (vertices.get(i).contains(selectEvent1X, selectEvent1Y)) {
											selectEventVertex1 = vertices.get(i);
											// vertices.get(i).setFill(Color.CRIMSON);
										}
									}
									selectFlag1 = draw2Tree.selectVertex(mouseEvent.getX(), mouseEvent.getY());
								}
								// User selected his second coordinate
								else if (selectCounter == 1) {
									selectCounter++;
									selectEvent2X = mouseEvent.getX(); // selectEvent2 = coordinates of second event
									selectEvent2Y = mouseEvent.getY();
									selectFlag2 = draw2Tree.selectVertex(mouseEvent.getX(), mouseEvent.getY());
									// Check whether this event is INSIDE of a vertex.
									for (int i = 0; i < vertices.size(); i++) {
										if (vertices.get(i).contains(selectEvent2X, selectEvent2Y)) {
											selectEventVertex2 = vertices.get(i);
											// vertices.get(i).setFill(Color.CRIMSON);
										}
									}
									selectCounter = 0;

									// BOTH EVENTS ARE INSIDE VERTICES.
									// It's been confirmed that user selected vertices.
									selectFlag = selectFlag1 && selectFlag2;
									System.out.println("SELECTION!");

									/*
									 * Check for adjacency before inserting a vertex. If user selected TWO vertices,
									 * i.e., if selectFlag==true, then proceed to check for adjacency of those two
									 * vertices. If not, prompt the user to select vertices properly.
									 */
									if (selectFlag) {
										adjacencyFlag = checkForAdjacency(selectEventVertex1.getCenterX(),
												selectEventVertex1.getCenterY(), selectEventVertex2.getCenterX(),
												selectEventVertex2.getCenterY());
										if (adjacencyFlag)
											System.out.println("ADJACENTTTTTTTTTT!!!!!!");
									} else {
										Stage errorStage = new Stage();
										errorStage.initOwner(mainStage);
										errorStage.setTitle("ERROR!");

										VBox errorDialogBox = new VBox(20);
										errorDialogBox.getChildren()
												.addAll(new Text("Error: Please select vertices properly!"), new Text(
														"If you need any help, please choose help topics from the bottom of the window."));

										Scene errorScene = new Scene(errorDialogBox, 500, 75);
										errorStage.setScene(errorScene);
										errorStage.show();
									}

								}
								mainScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
							}
						});
					}
					break;

				}
			}

		});

		clearScreen.setOnMouseClicked(e -> {
			border.getChildren().clear();
			border.setTop(addGraphColoring());
			border.setBottom(addHelpTopics());
			mainStage.setScene(new Scene(border, 2000, 690, Color.LIGHTBLUE));
			operations.getItems().removeAll("None", "Insert Vertex", "Draw Edge", "Select Vertex", "Color Graph");
			operations.getItems().addAll("None", "Insert Vertex", "Draw Edge", "Select Vertex", "Color Graph");
			selectedColor = null;
			typeOfGraph = null;
			operation = null;
			mainStage.show();
		});

		clearGraphColors.setOnMouseClicked(e -> {
			for (int i = 0; i < vertices.size(); i++)
				vertices.get(i).setFill(Color.BURLYWOOD);

		});

		helpWindow.setOnMouseClicked(e -> {
			Stage graphWindow = new Stage();
			graphWindow.initOwner(mainStage);
			graphWindow.setTitle("Coloring Graphs");

			ScrollPane scroll = new ScrollPane();

			BorderPane graphBorder = new BorderPane();
			graphBorder.setPadding(new Insets(10));
			graphBorder.setCenter(scroll);

			VBox vbox = new VBox(20);

			Text trees = new Text("TREES");
			trees.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
			trees.setFill(Color.RED);

			Text text1 = new Text("A tree is an undirected, acyclic graph. You have 2 options in a tree.\n"
					+ "You can either choose to draw an edge or color the graph.\n"
					+ "If you choose to draw an edge, select a vertex and drag the edge to your desired position.\n"
					+ "The position can either be in a new place or an existing vertex.\n"
					+ "For coloring, make sure to color it in a way, so that no two adjacent vertices have the same color.");
			text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
			text1.setFill(Color.BLACK);
			text1.setStroke(Color.RED);
			text1.setStrokeWidth(0.2);

			Text twoTrees = new Text("2-TREES");
			twoTrees.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
			twoTrees.setFill(Color.GOLD);

			Text text2 = new Text(
					"A 2-tree generalizes a normal tree. It's focus is on triangles, whereas a normal tree's focus is on vertices.\n"
							+ "Excluding graph coloring, 2-tree provides you two options. If you want to insert a vertex, first select two adjacent vertices.\n"
							+ "And then, you can add a vertex anywhere of your choice.");
			text2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
			text2.setFill(Color.BLACK);
			text2.setStroke(Color.YELLOW);
			text2.setStrokeWidth(0.2);

			Text mops = new Text("MAXIMAL OUTER PLANAR GRAPHS");
			mops.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
			mops.setFill(Color.GREEN);

			Text text3 = new Text("A maximal outer planar graph (MOP) is a 2-tree, but vice-versa isn't true.\n."
					+ "A graph is a MOP if all of its vertices lie on the outerface\n."
					+ "Meaning that all vertices that are added should make the graph still planar."
					+ "Excluding graph coloring, MOP provides you two options. If you want to insert a vertex, first select two adjacent vertices.\n"
					+ "And then, you can add a vertex anywhere of your choice, provided the graph's outerplanarity is preserved.");
			text3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
			text3.setFill(Color.BLACK);
			text3.setStroke(Color.LIME);
			text3.setStrokeWidth(0.2);

			Text k4planar = new Text("K4 PLANAR GRAPH");
			k4planar.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
			k4planar.setFill(Color.DARKBLUE);

			Text text4 = new Text("A K4 planar graph is a subset of planar graphs.\n"
					+ "It replaces a triangle with three other triangles.\n"
					+ "It does this by placing a vertex in the triangular face and binding that vertex (with an edge) to each vertex that creates that face.\n"
					+ "Excluding graph coloring, K4 planar graph provides you with one option.\n"
					+ "Click on where you want to insert a vertex, provided that point in enclosed within a triangle.");
			text4.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
			text4.setFill(Color.BLACK);
			text4.setStroke(Color.DARKBLUE);
			text4.setStrokeWidth(0.2);

			Text planarCubic = new Text("PLANAR CUBIC GRAPHS");
			planarCubic.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
			planarCubic.setFill(Color.DARKMAGENTA);

			Text text5 = new Text("A planar cubic graph is a planar graph where each vertex has a degree of three.\n"
					+ "For each type of planar cubic graph, excluding graph coloring, you have one option.\n"
					+ "Each time you want to select a new edge, click on 'GO'. Select an edge and click on GO.\n"
					+ "When you click twice, an edge will show.");
			text5.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
			text5.setFill(Color.BLACK);
			text5.setStroke(Color.BLUEVIOLET);
			text5.setStrokeWidth(0.2);

			vbox.getChildren().addAll(trees, text1, twoTrees, text2, mops, text3, k4planar, text4, planarCubic, text5);
			scroll.setContent(vbox);

			graphBorder.setCenter(scroll);
			// graphBorder.getChildren().add(vbox);
			Scene graphScene = new Scene(graphBorder, 1200, 600);
			graphWindow.setScene(graphScene);
			graphWindow.show();
		});

		tutorial.setOnMouseClicked(e -> {
			Stage coloring = new Stage();
			coloring.initOwner(mainStage);
			coloring.setTitle("Coloring Graphs");

			BorderPane colorBorder = new BorderPane();
			colorBorder.setPadding(new Insets(10));

			VBox vbox = new VBox(20);

			Text text1 = new Text("Graph coloring is a way of labelling vertices with a certain color.");
			text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text1.setFill(Color.BLACK);
			text1.setStroke(Color.RED);
			text1.setStrokeWidth(0.2);

			Text text2 = new Text("No two adjacent vertices can have the same color.");
			text2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text2.setFill(Color.BLACK);
			text2.setStroke(Color.LIME);
			text2.setStrokeWidth(0.2);

			Text text3 = new Text("Two vertices are adjacent if they have an edge connecting each other.");
			text3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text3.setFill(Color.BLACK);
			text3.setStroke(Color.BLUEVIOLET);
			text3.setStrokeWidth(0.2);

			vbox.getChildren().addAll(text1, text2, text3);
			colorBorder.getChildren().add(vbox);
			Scene tutorialScene = new Scene(colorBorder, 700, 120);
			coloring.setScene(tutorialScene);
			coloring.show();
		});

		howToUseGUI.setOnMouseClicked(e -> {
			Stage GUI = new Stage();
			GUI.initOwner(mainStage);
			GUI.setTitle("Using the GUI");

			BorderPane GUIBorder = new BorderPane();
			GUIBorder.setPadding(new Insets(10));

			VBox graph = new VBox(20);

			Text text1 = new Text("First, select a type of graph and click on generate.");
			text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text1.setFill(Color.BLACK);
			text1.setStroke(Color.RED);
			text1.setStrokeWidth(0.2);

			Text text5 = new Text("Only then will you be able to utilize all of the options available.");
			text5.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text5.setFill(Color.BLACK);
			text5.setStroke(Color.YELLOW);
			text5.setStrokeWidth(0.2);

			Text text2 = new Text("Next, select a type of operation and press on Go.");
			text2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text2.setFill(Color.BLACK);
			text2.setStroke(Color.LIME);
			text2.setStrokeWidth(0.2);

			Text text6 = new Text("If you want to color your graph, then make sure to select a color.");
			text6.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text6.setFill(Color.BLACK);
			text6.setStroke(Color.BLUE);
			text6.setStrokeWidth(0.2);

			Text text3 = new Text("You can clear the colors you've used at any time that you want to.");
			text3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text3.setFill(Color.BLACK);
			text3.setStroke(Color.BLUEVIOLET);
			text3.setStrokeWidth(0.2);

			Text text4 = new Text(
					"Help is available at any time, so feel free to refer back in case you're stuck on anything.");
			text4.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
			text4.setFill(Color.BLACK);
			text4.setStroke(Color.HOTPINK);
			text4.setStrokeWidth(0.2);

			graph.getChildren().addAll(text1, text5, text2, text6, text3, text4);
			GUIBorder.setLeft(graph);
			Scene GUIScene = new Scene(GUIBorder, 750, 235);
			GUI.setScene(GUIScene);
			GUI.show();
		});
		mainStage.show();
	}

	// There will be TWO portions of this interface:
	// 1. Let user create and color graphs.
	// 2. Let user access help in case he needs it.

	// Place buttons and combo boxes ===> Decorate top row
	public HBox addGraphColoring() {
		HBox hbox = new HBox();// Basically, HBox is a layout format that presents the "children" in a
								// horizontal manner.
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);

		Text title = new Text("START =>");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		title.setFill(Color.DARKBLUE);
		title.setStroke(Color.CYAN);
		title.setStrokeWidth(0.5);

		// Add buttons to perform different events.

		// generateGraph => Clicking this button will generate desired graph.
		generateGraph.setText("GENERATE");
		generateGraph.setMaxSize(250, 120);

		// go => perform desired operation
		go.setText("=> GO <=");
		go.setMaxSize(250, 120);

		// clearGraphColors => clear graph of any colors
		clearGraphColors.setText("CLEAR COLORS");
		clearGraphColors.setMaxSize(250, 120);

		// clearScreen => clear the Stage
		clearScreen.setText("CLEAR THE SCREEN");
		clearScreen.setMaxSize(250, 120);

		// quitApplication => Exit
		quitApplication.setText("EXIT APPLICATION");
		quitApplication.setMaxSize(250, 120);
		quitApplication.setOnAction(event -> System.exit(0));

		// initially disable ALL operations and enable them when user generates a graph.
		colorBox.setDisable(true);
		operations.setDisable(true);
		go.setDisable(true);
		clearGraphColors.setDisable(true);
		clearScreen.setDisable(true);

		// Designing - separators to give the buttons more structure
		Text separator1 = new Text("||");
		separator1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		separator1.setFill(Color.CYAN);
		separator1.setStroke(Color.INDIGO);
		Text separator2 = new Text("||");
		separator2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		separator2.setFill(Color.CYAN);
		separator2.setStroke(Color.INDIGO);
		Text separator3 = new Text("||");
		separator3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		separator3.setFill(Color.CYAN);
		separator3.setStroke(Color.INDIGO);

		// Add the buttons (children) into the Stage (parent).
		hbox.getChildren().addAll(title, graphBox, generateGraph, separator1, operations, colorBox, go,
				clearGraphColors, separator2, clearScreen, separator3, quitApplication);

		return hbox;
	}

	// Place buttons for help topics ===> Decorate bottom row
	public HBox addHelpTopics() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);

		Text title = new Text("HELP TOPICS =>");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		title.setFill(Color.VIOLET);
		title.setStroke(Color.MEDIUMVIOLETRED);
		title.setStrokeWidth(1);

		helpWindow.setText("OVERVIEW OF DIFFERENT TYPES OF GRAPHS");
		helpWindow.setMaxSize(300, 150);

		tutorial.setText("HOW DO YOU COLOR GRAPHS?");
		tutorial.setMaxSize(250, 120);

		howToUseGUI.setText("USING THE GRAPHICAL USER INTERFACE");
		howToUseGUI.setMaxSize(250, 120);

		hbox.getChildren().addAll(title, helpWindow, tutorial, howToUseGUI);

		return hbox;
	}

	/*
	 * Implement rules of graph coloring ===> Common method for all graphs
	 */
	public void graphColoring(double vertexX, double vertexY) {
		// This method notes the colors of the ADJACENT vertices
		listOfColors = new ArrayList<Color>();
		for (int j = 0; j < edges.size(); j++) {

			/*
			 * Each edge's starting and ending points will correlate with a vertex's center.
			 * In this case, compare the ends of the edge to see which colors the adjacent
			 * vertices are placed in. Compare with the starting ends first.
			 */

			/*
			 * Run a loop and see each vertex's adjacent vertices' used colors. Add these
			 * used colors into listOfColors<>
			 */
			if (edges.get(j).getStartX() == vertexX && edges.get(j).getStartY() == vertexY) {
				for (int k = 0; k < vertices.size(); k++) {

					if (vertices.get(k).getCenterX() == edges.get(j).getEndX()
							&& vertices.get(k).getCenterY() == edges.get(j).getEndY()) {

						if (vertices.get(k).getFill().equals(Color.RED))
							listOfColors.add(Color.RED);
						else if (vertices.get(k).getFill().equals(Color.ORANGERED))
							listOfColors.add(Color.ORANGERED);
						else if (vertices.get(k).getFill().equals(Color.YELLOW))
							listOfColors.add(Color.YELLOW);
						else if (vertices.get(k).getFill().equals(Color.LIME))
							listOfColors.add(Color.LIME);
						else if (vertices.get(k).getFill().equals(Color.BLUE))
							listOfColors.add(Color.BLUE);
						else if (vertices.get(k).getFill().equals(Color.INDIGO))
							listOfColors.add(Color.INDIGO);
						else if (vertices.get(k).getFill().equals(Color.BLUEVIOLET))
							listOfColors.add(Color.BLUEVIOLET);
						else if (vertices.get(k).getFill().equals(Color.HOTPINK))
							listOfColors.add(Color.HOTPINK);
					}
				}
			}
			// Similarly, check the OTHER ends of the edges.
			if (edges.get(j).getEndX() == vertexX && edges.get(j).getEndY() == vertexY) {
				for (int k = 0; k < vertices.size(); k++) {
					if (vertices.get(k).getCenterX() == edges.get(j).getStartX()
							&& vertices.get(k).getCenterY() == edges.get(j).getStartY()) {
						if (vertices.get(k).getFill().equals(Color.RED))
							listOfColors.add(Color.RED);
						else if (vertices.get(k).getFill().equals(Color.ORANGERED))
							listOfColors.add(Color.ORANGERED);
						else if (vertices.get(k).getFill().equals(Color.YELLOW))
							listOfColors.add(Color.YELLOW);
						else if (vertices.get(k).getFill().equals(Color.LIME))
							listOfColors.add(Color.LIME);
						else if (vertices.get(k).getFill().equals(Color.BLUE))
							listOfColors.add(Color.BLUE);
						else if (vertices.get(k).getFill().equals(Color.INDIGO))
							listOfColors.add(Color.INDIGO);
						else if (vertices.get(k).getFill().equals(Color.BLUEVIOLET))
							listOfColors.add(Color.BLUEVIOLET);
						else if (vertices.get(k).getFill().equals(Color.PINK))
							listOfColors.add(Color.PINK);

					}
				}
			}

		}
	}

	// For 2-trees and MOPs, check if selected vertices are adjacent.
	public boolean checkForAdjacency(double xcoord1, double ycoord1, double xcoord2, double ycoord2) {
		boolean event1 = false, event2 = false;
		for (int i = 0; i < edges.size(); i++) {
			/*
			 * if two vertices are adjacent, then it means that have 1 common end point. The
			 * two events check for that. event1 ==> checks for one of the two edges event2
			 * ==> checks for 2nd edge.
			 */
			event1 = (edges.get(i).getStartX() == xcoord1) && (edges.get(i).getStartY() == ycoord1)
					&& (edges.get(i).getEndX() == xcoord2) && (edges.get(i).getEndY() == ycoord2);
			event2 = (edges.get(i).getStartX() == xcoord2) && (edges.get(i).getStartY() == ycoord2)
					&& (edges.get(i).getEndX() == xcoord1) && (edges.get(i).getEndY() == ycoord1);
			if (event1 || event2) {
				System.out.println(event1 + " " + event2);
				return true;
			}
		}
		return false;
	}

	/*
	 * For the planar cubic graphs ===> K4, K6, K8 (all 3 types). Total: 5 graphs.
	 */
	public double[] addVerticesAndEdges(double xcoord1, double ycoord1, double xcoord2, double ycoord2) {

		double indices[] = new double[2];

		Circle circle1 = new Circle();
		circle1.setRadius(20);
		circle1.setFill(Color.BURLYWOOD);

		Circle circle2 = new Circle();
		circle2.setRadius(20);
		circle2.setFill(Color.BURLYWOOD);

		boolean circleFlag1 = false, circleFlag2 = false;
		indices[0] = vertices.size();

		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).contains(xcoord1, ycoord1)) {
				circle1.setCenterX(xcoord1);
				circle1.setCenterY(ycoord1);
				vertices.add(circle1);
				circleFlag1 = true;
			}
		}

		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).contains(xcoord2, ycoord2)) {
				circle2.setCenterX(xcoord2);
				circle2.setCenterY(ycoord2);
				vertices.add(circle2);
				circleFlag2 = true;
			}
		}

		Line edge1 = new Line(), edge2 = new Line(), edge3 = new Line(), edge4 = new Line(), line = new Line();

		/*
		 * When a user adds a new within an empty cycle, delete the OLD edges and draw
		 * new edges. Each affected edge will break into 2 edges. The old one is deleted
		 * and in its place, 2 new ones are added and joined at the same place the user
		 * clicks on.
		 */
		int indexOfEdgeDelete1 = -1, indexOfEdgeDelete2 = -1;
		// Circle vertex1, vertex2;
		if (circleFlag1 == true && circleFlag2 == true) {
			for (int i = 0; i < edges.size(); i++) {
				if (edges.get(i).contains(xcoord1, ycoord1)) {
					indexOfEdgeDelete1 = i;
					edge1 = new Line(circle1.getCenterX(), circle1.getCenterY(), edges.get(i).getStartX(),
							edges.get(i).getStartY());
					edge2 = new Line(circle1.getCenterX(), circle1.getCenterY(), edges.get(i).getEndX(),
							edges.get(i).getEndY());
				}
				if (edges.get(i).contains(xcoord2, ycoord2)) {
					indexOfEdgeDelete2 = i;
					edge3 = new Line(circle2.getCenterX(), circle2.getCenterY(), edges.get(i).getStartX(),
							edges.get(i).getStartY());
					edge4 = new Line(circle2.getCenterX(), circle2.getCenterY(), edges.get(i).getEndX(),
							edges.get(i).getEndY());
				}
			}
			line = new Line(xcoord1, ycoord1, xcoord2, ycoord2);
		}
		/*
		 * To make sure that edges are not deleted due to wrong indices, first, delete
		 * that edge that has the HIGHER index of the two.
		 */
		if (indexOfEdgeDelete1 < indexOfEdgeDelete2) {
			edges.remove(indexOfEdgeDelete2);
			edges.remove(indexOfEdgeDelete1);
		} else {
			edges.remove(indexOfEdgeDelete1);
			edges.remove(indexOfEdgeDelete2);
		}

		/*
		 * The edges that are added are given thickness, so that user faces less
		 * difficulty while clicking an edge.
		 */
		edge1.setStrokeWidth(7);
		edge2.setStrokeWidth(7);
		edge3.setStrokeWidth(7);
		edge4.setStrokeWidth(7);
		line.setStrokeWidth(7);

		/* The edges are added into the static array. */
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(line);

		indices[1] = 0;
		System.out.println("EDGES  " + edges.size());
		return indices;
	}

	private int traverse;
	private Line line = new Line();
	private Circle circle = new Circle();

	public double[] addVerticesAndEdgesForTree(double xcoord, double ycoord) {
		double indices[] = { vertices.size(), edges.size() };
		int index;

		circle.setRadius(20);
		circle.setFill(Color.BURLYWOOD);
		circle.setVisible(true);

		// Line line = new Line();
		for (index = 0; index < vertices.size(); index++) {
			if (vertices.get(index).contains(xcoord, ycoord)) {
				circle = vertices.get(index);
				break;
			}
		}

		if (true) {
			// if (index > vertices.size()) {
			// System.out.println("SDKLJF"); //
			// vertices.get(index).setOnMousePressed(e -> {
			line.setStartX(circle.getCenterX());
			line.setStartY(circle.getCenterY());
			// });
			circle.setOnMouseDragged(e -> {
				line.setEndX(e.getX());
				line.setEndY(e.getY());
				line.setVisible(true);
			});
			circle.setOnMouseReleased(e -> {
				System.out.println("Mouse released");
				/*
				 * circle2.setCenterX(e.getX()); circle2.setCenterY(e.getY());
				 * circle.setVisible(true); line = new Line(e.getX(), e.getY(),
				 * vertices.get(index).getCenterX(), vertices.get(index).getCenterY());
				 */
				boolean ifElse = true;

				for (int i = 0; i < vertices.size(); i++) {
					if (circle.contains(e.getX(), e.getY())) {
						System.out.println("BROKEN");
						ifElse = false;
					}
					if (vertices.get(i).contains(e.getX(), e.getY())) {
						flag = true;
						traverse = i;
						System.out.println("FLAG=TRUE");
						break;
					}
				}
				if (ifElse) {
					if (flag) {
						// line.setEndX(vertices.get(traverse).getCenterX());
						// line.setEndY(vertices.get(traverse).getCenterY());
						Line draw = new Line(line.getStartX(), line.getStartY(), vertices.get(traverse).getCenterX(),
								vertices.get(traverse).getCenterY());
						edges.add(draw);
						flag = false;
						// border.getChildren().add(draw);
					}
					/*
					 * System.out.println("INSIDE"); line.setVisible(true); line = new
					 * Line(circle.getCenterX(), circle.getCenterY(),
					 * vertices.get(traverse).getCenterX(), vertices.get(traverse).getCenterY()); //
					 * // border.getChildren().add(this.line); edges.add(line); } else {
					 * line.setVisible(true); Circle circle2 = new Circle();//
					 * drawTree.addVerticesAndEdges(e.getX(), e.getY()); //
					 * circle2.setCenterX(e.getX()); circle2.setCenterY(e.getY());
					 * circle2.setRadius(20); circle2.setFill(Color.BURLYWOOD);
					 * circle2.setVisible(true); // border.getChildren().add(circle2);
					 * System.out.println("MOUSE RELEASE"); line = new Line(circle.getCenterX(),
					 * circle.getCenterY(), e.getX(), e.getY()); vertices.add(circle2);
					 * edges.add(line); // border.getChildren().add(circle2); }
					 * 
					 * 
					 * line.setEndX(e.getX()); line.setEndY(e.getY()); if
					 * (vertices.get(index).contains(e.getX(), e.getY())) line.setVisible(false);
					 * else { circle.setCenterX(e.getX()); circle.setCenterY(e.getY());
					 * line.setVisible(true); }
					 * 
					 */
					else {
						System.out.println("INSIDE ELSE!");
						Circle circle2 = new Circle();
						circle2.setCenterX(e.getX());
						circle2.setCenterY(e.getY());
						circle2.setVisible(true);
						circle2.setRadius(20);
						circle2.setFill(Color.BURLYWOOD);

						Line draw = new Line(line.getStartX(), line.getStartY(), e.getX(), e.getY());
						vertices.add(circle2);
						edges.add(draw);
						// border.getChildren().add(circle2);
						// border.getChildren().add(draw);
					}
				}
			});
		}
		return indices;
	}

	static class Tree {
		public Circle initialization() {
			Circle circle = new Circle();
			circle.setCenterX(500);
			circle.setCenterY(150);
			circle.setRadius(20);
			circle.setFill(Color.BURLYWOOD);
			/*
			 * double x = 0 * 60 + 10; double y = 0 * 60 + 10; Circle c = new Circle(x, y,
			 * 10); c.setFill(Color.WHITE); c.setStroke(Color.BLACK); c.setStrokeWidth(2);
			 * c.setStrokeType(StrokeType.OUTSIDE);
			 */
			// Line line = new Line();
			// boolean colorFlag=false;

			// if (!colorFlag) {
			/*
			 * if (colorFlag) { circle.setOnMousePressed(e -> { line.setStartX(500);
			 * line.setStartY(150);
			 * 
			 * });
			 * 
			 * circle.setOnMouseReleased(e -> { line.setVisible(true); for (int i = 0; i <
			 * vertices.size(); i++) { if (vertices.get(i).contains(line.getEndX(),
			 * line.getEndY())) { flag3 = true; index3 = i; System.out.println("FLAG=TRUE");
			 * break; } } if (flag3) { System.out.println("INSIDE"); flag3 = false;
			 * line.setVisible(false); Line draw = new Line(circle.getCenterX(),
			 * circle.getCenterY(), vertices.get(index3).getCenterX(),
			 * vertices.get(index3).getCenterY()); border.getChildren().add(draw);
			 * 
			 * } else { Circle circle2 = drawTree.addVerticesAndEdges(e.getX(), e.getY());
			 * border.getChildren().add(circle2); System.out.println("MOUSE RELEASE"); Line
			 * draw = new Line(circle.getCenterX(), circle.getCenterY(), e.getX(),
			 * e.getY()); border.getChildren().add(draw); edges.add(draw);
			 * 
			 * }
			 * 
			 * });
			 * 
			 * circle.setOnMouseDragged(e -> { line.setEndX(e.getX());
			 * line.setEndY(e.getY()); line.setVisible(true); }); }
			 */

			vertices.add(circle);
			return circle;
			/*
			 * double x = 0 * 60 + 10; double y = 0 * 60 + 10; Circle c = new Circle(x, y,
			 * 10); c.setFill(Color.WHITE); c.setStroke(Color.BLACK); c.setStrokeWidth(2);
			 * c.setStrokeType(StrokeType.OUTSIDE);
			 * 
			 * c.setOnMousePressed(e -> { line.setStartX(x); line.setStartY(y); });
			 * 
			 * c.setOnMouseReleased(e -> { line.setVisible(true); });
			 * 
			 * c.setOnMouseDragged(e -> { line.setEndX(e.getX()); line.setEndY(e.getY());
			 * line.setVisible(true); }); border.getChildren().add(c);
			 * 
			 * /*Circle circle = new Circle(); circle.setCenterX(500);
			 * circle.setCenterY(150); circle.setRadius(20);
			 * circle.setFill(Color.BURLYWOOD); // all nodes are initially of light brown
			 * color.
			 * 
			 * circle.setOnMousePressed(e -> { line.setStartX(e.getX());
			 * line.setStartY(e.getX()); });
			 * 
			 * circle.setOnMouseReleased(e -> { line.setVisible(true); addVertex(); });
			 * 
			 * circle.setOnMouseDragged(e -> { line.setEndX(e.getX());
			 * line.setEndY(e.getY()); line.setVisible(true); xcoord = e.getX(); ycoord =
			 * e.getY(); });
			 * 
			 * vertices.add(circle); border.getChildren().add(circle); return circle;
			 */
		}

		private int index;
		private int traverse;
		private Line line = new Line();
		private Circle circleTree = new Circle();
		private CubicCurve curve;
		private boolean planar = true;

		public double[] addVerticesAndEdges(double xcoord, double ycoord) {
			double indices[] = { vertices.size(), edges.size() };

			circleTree.setRadius(20);
			circleTree.setFill(Color.BURLYWOOD);
			circleTree.setVisible(true);

			// Determine whether or not the user selected a starting vertex initially
			for (index = 0; index < vertices.size(); index++) {
				/*
				 * If clicked point is within a vertex, break from the loop and store that
				 * vertex.
				 */
				if (vertices.get(index).contains(xcoord, ycoord)) {
					circleTree = vertices.get(index);
					break;
				}
			}

			if (true) {
				line.setStartX(circleTree.getCenterX());
				line.setStartY(circleTree.getCenterY());

				// Observe the user's movements as he moves the mouse around.
				circleTree.setOnMouseDragged(e -> {
					line.setEndX(e.getX());
					line.setEndY(e.getY());
					line.setVisible(true);
					// Check for planarity while dragging.
					if (planarity(e.getX(), e.getY()))
						;
				});

				// Observe the user's movements as he releases the mouse.
				circleTree.setOnMouseReleased(e -> {
					System.out.println("Mouse released");

					boolean ifElse = true;

					for (int i = 0; i < vertices.size(); i++) {
						/*
						 * If the user's starting and ending vertices are the same, then loop is broken.
						 */
						if (circleTree.contains(e.getX(), e.getY())) {
							System.out.println("BROKEN");
							ifElse = false; // user can return back to the event handler.
						}
						if (vertices.get(i).contains(e.getX(), e.getY())) {
							/* User decided to draw an edge between two existing vertices. */
							flag = true;
							traverse = i;
							System.out.println("FLAG=TRUE");
							break;
						}
					}
					/*
					 * If starting & ending vertices are same, then user will be redirected back to
					 * event handler.
					 */
					if (ifElse) {
						/* User drew an edge between an existing vertex to another existing vertex. */
						if (flag) {
							// line.setEndX(vertices.get(traverse).getCenterX());
							// line.setEndY(vertices.get(traverse).getCenterY());

							// Check for planarity
							if (!planarity(e.getX(), e.getY())) {
								curve = adjust(e.getX(), e.getY(), line.getStartX(), line.getStartY());
								border.getChildren().add(curve);
								edges.add(new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));
								return;
							}

							Line draw = new Line(line.getStartX(), line.getStartY(),
									vertices.get(traverse).getCenterX(), vertices.get(traverse).getCenterY());
							edges.add(draw);
							flag = false;
							border.getChildren().add(draw);

						}
						/* User drew an edge from an existing vertex to a new vertex. */
						else {
							System.out.println("INSIDE ELSE!");

							// Initialize new vertex.
							Circle circle2 = new Circle();
							circle2.setCenterX(e.getX());
							circle2.setCenterY(e.getY());
							circle2.setVisible(true);
							circle2.setRadius(20);
							circle2.setFill(Color.BURLYWOOD);

							if (!planarity(e.getX(), e.getY())) {
								curve = adjust(e.getX(), e.getY(), line.getStartX(), line.getStartY());
								border.getChildren().add(curve);
								vertices.add(circle2);
								edges.add(new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));
							}

							/* If there is no issue of planarity, then draw a new edge. */
							Line draw = new Line(line.getStartX(), line.getStartY(), e.getX(), e.getY());
							vertices.add(circle2);
							edges.add(draw);
							border.getChildren().add(circle2);
							border.getChildren().add(draw);
						}
					}
				});
			}

			return indices;

		}

		public static boolean planarity(double xcoord, double ycoord) {
			/*
			 * If any of the points of the line are a part of the existing edges, then the
			 * method will return false
			 */
			for (int i = 0; i < edges.size(); i++) {
				if (edges.get(i).contains(xcoord, ycoord))
					return false;
			}
			return true;
		}

		public static CubicCurve adjust(double lineX, double lineY, double xcoord, double ycoord) {
			CubicCurve cubic = new CubicCurve();
			cubic.setStartX(lineX);
			cubic.setStartY(lineY);
			cubic.setControlX1(xcoord);
			cubic.setControlY1(ycoord);
			cubic.setControlX2(lineX);
			cubic.setControlY2(lineY);
			cubic.setEndX(xcoord);
			cubic.setEndY(ycoord);
			return cubic;
		}
	}

	static class TwoTree {

		public void initialization() {

			// CIRCLE 1
			System.out.println("2tree-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(500);
			circle1.setCenterY(150);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);
			Line line = new Line(500, 150, 400, 250);

			// CIRCLE 2
			System.out.println("2tree-CIRCLE2");
			Circle circle2 = new Circle();
			circle2.setCenterX(400);
			circle2.setCenterY(250);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);
			Line line2 = new Line(400, 250, 600, 250);

			// CIRCLE 3
			System.out.println("2tree-CIRCLE3");
			Circle circle3 = new Circle();
			circle3.setCenterX(600);
			circle3.setCenterY(250);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);
			Line line3 = new Line(600, 250, 500, 150);

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);

			edges.add(line);
			edges.add(line2);
			edges.add(line3);

		}

		public boolean selectVertex(double vertexX, double vertexY) {
			for (int i = 0; i < vertices.size(); i++) {
				if (vertices.get(i).contains(vertexX, vertexY))
					return true;
			}
			return false;
		}

		// Outerplanarity is not even checked because 2-trees don't guarantee it.
		public double[] insertVertexAndDrawEdges(double vertexX, double vertexY) {
			double indices[] = new double[2];

			Circle circle = new Circle();
			circle.setCenterX(vertexX);
			circle.setCenterY(vertexY);
			circle.setRadius(20);
			circle.setFill(Color.BURLYWOOD);

			// Edges are drawn.
			Line line1 = new Line(vertexX, vertexY, selectEventVertex1.getCenterX(), selectEventVertex1.getCenterY());
			Line line2 = new Line(vertexX, vertexY, selectEventVertex2.getCenterX(), selectEventVertex2.getCenterY());

			/*
			 * Sizes of array are stored in indices[] because I only want to draw the edges
			 * that are not displayed yet. If ENTIRE vertices[] or edges[] arrays are
			 * returned, then ALL edges will be drawn over again and this is both a waste of
			 * time and space.
			 */
			indices[0] = vertices.size();
			indices[1] = edges.size();

			vertices.add(circle);
			edges.add(line1);
			edges.add(line2);

			return indices;
		}

	}

	static class MOP {

		public void initialization() {

			System.out.println("MOP-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(500);
			circle1.setCenterY(150);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);
			Line line = new Line(500, 150, 400, 250);

			// CIRCLE 2
			System.out.println("MOP-CIRCLE2");

			Circle circle2 = new Circle();
			circle2.setCenterX(400);
			circle2.setCenterY(250);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);
			Line line2 = new Line(400, 250, 600, 250);

			// CIRCLE 3
			System.out.println("MOP-CIRCLE3");

			Circle circle3 = new Circle();
			circle3.setCenterX(600);
			circle3.setCenterY(250);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);
			Line line3 = new Line(600, 250, 500, 150);

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);
			edges.add(line);
			edges.add(line2);
			edges.add(line3);
			// return circle1;
		}

		public boolean checkForOuterPlanarity(double vertexX, double vertexY) {
			for (int i = 0; i < edges.size(); i++) {
				/*
				 * Outerplanarity means that edges should not intersect with the inside of the
				 * graph. There will be TWO edges drawn in a MOP. SO: CHECK IF EACH EDGE
				 * INTERSECTS WITH ALREADY EXISTING EDGES. This method checks whether the new
				 * edges follow outerplanarity. These edges are not yet displayed onto the
				 * screen, but are identified in geometry.
				 */

				// NEW EDGE 1 ==> Check whether it intersects with any of the inner edges
				if (Line2D.linesIntersect(vertexX, vertexY, selectEventVertex1.getCenterX(),
						selectEventVertex1.getCenterY(), edges.get(i).getStartX(), edges.get(i).getStartY(),
						edges.get(i).getEndX(), edges.get(i).getEndY())) {
					/*
					 * IF THEY DO INTERSECT, MAKE SURE THAT INTERSECTION DOESN'T MEAN HAVING A
					 * COMMON ENDPOINT!!!!!!!!!
					 */
					if ((edges.get(i).getStartX() == selectEventVertex1.getCenterX()
							&& edges.get(i).getStartY() == selectEventVertex1.getCenterY())
							|| (edges.get(i).getEndX() == selectEventVertex1.getCenterX()
									&& edges.get(i).getEndY() == selectEventVertex1.getCenterY()))
						;
					else
						return false;

				}
				// NEW EDGE 2 ==> Check whether it intersects with any of the inner edges
				if (Line2D.linesIntersect(vertexX, vertexY, selectEventVertex2.getCenterX(),
						selectEventVertex2.getCenterY(), edges.get(i).getStartX(), edges.get(i).getStartY(),
						edges.get(i).getEndX(), edges.get(i).getEndY())) {
					/*
					 * IF THEY DO INTERSECT, MAKE SURE THAT INTERSECTION DOESN'T MEAN HAVING A
					 * COMMON ENDPOINT!!!!!!!!!
					 */
					if ((edges.get(i).getStartX() == selectEventVertex2.getCenterX()
							&& edges.get(i).getStartY() == selectEventVertex2.getCenterY())
							|| (edges.get(i).getEndX() == selectEventVertex2.getCenterX()
									&& edges.get(i).getEndY() == selectEventVertex2.getCenterY()))
						;
					else
						return false;

				}
			}

			return true;
		}

		public double[] insertVertexAndDrawEdges(double vertexX, double vertexY) {
			double indices[] = new double[2];

			Circle circle = new Circle();
			circle.setCenterX(vertexX);
			circle.setCenterY(vertexY);
			circle.setRadius(20);
			circle.setFill(Color.BURLYWOOD);

			// Edges are drawn.
			Line line1 = new Line(vertexX, vertexY, selectEventVertex1.getCenterX(), selectEventVertex1.getCenterY());
			Line line2 = new Line(vertexX, vertexY, selectEventVertex2.getCenterX(), selectEventVertex2.getCenterY());

			/*
			 * Sizes of array are stored in indices[] because I only want to draw the edges
			 * that are not displayed yet. If ENTIRE vertices[] or edges[] arrays are
			 * returned, then ALL edges will be drawn over again and this is both a waste of
			 * time and space.
			 */
			indices[0] = vertices.size();
			indices[1] = edges.size();

			vertices.add(circle);
			edges.add(line1);
			edges.add(line2);

			return indices;
		}

	}

	static class K4Planar {

		public void initialization() {

			// CIRCLE 1
			System.out.println("K4-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(700);
			circle1.setCenterY(80);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);

			// CIRCLE 2
			System.out.println("K4-CIRCLE2");
			Circle circle2 = new Circle();
			circle2.setCenterX(300);
			circle2.setCenterY(600);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);

			// CIRCLE 3
			System.out.println("K4-CIRCLE3");
			Circle circle3 = new Circle();
			circle3.setCenterX(1100);
			circle3.setCenterY(600);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);

			// CIRCLE 4
			System.out.println("K4-CIRCLE4");
			Circle circle4 = new Circle();
			circle4.setCenterX(700);
			circle4.setCenterY(380);
			circle4.setRadius(20);
			circle4.setFill(Color.BURLYWOOD);

			// LINES
			Line line1 = new Line(700, 80, 300, 600);
			Line line2 = new Line(300, 600, 1100, 600);
			Line line3 = new Line(1100, 600, 700, 80);
			Line line4 = new Line(700, 80, 700, 380);
			Line line5 = new Line(300, 600, 700, 380);
			Line line6 = new Line(1100, 600, 700, 380);

			// Keep track of divided triangles
			Polygon polygon1 = new Polygon();
			polygon1.getPoints().addAll(new Double[] { 700.0, 80.0, 700.0, 380.0, 300.0, 600.0 });
			Polygon polygon2 = new Polygon();
			polygon2.getPoints().addAll(new Double[] { 300.0, 600.0, 700.0, 380.0, 1100.0, 600.0 });
			Polygon polygon3 = new Polygon();
			polygon3.getPoints().addAll(new Double[] { 700.0, 80.0, 700.0, 380.0, 1100.0, 600.0 });

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);
			vertices.add(circle4);

			edges.add(line1);
			edges.add(line2);
			edges.add(line3);
			edges.add(line4);
			edges.add(line5);
			edges.add(line6);

			triangles.add(polygon1);
			triangles.add(polygon2);
			triangles.add(polygon3);
		}

		public double[] splitIntoTriangles(double xcoord, double ycoord) {

			double indices[] = new double[3];

			if (xcoord == 0 || ycoord == 0)
				return indices;

			Circle circle = new Circle();
			circle.setCenterX(xcoord);
			circle.setCenterY(ycoord);
			circle.setRadius(20);
			circle.setFill(Color.BURLYWOOD);

			// System.out.println("AFTER RETURN STATEMENT");
			int index = -1;
			for (int i = 0; i < triangles.size(); i++) {
				if (triangles.get(i).contains(xcoord, ycoord))
					index = i;
			}

			// The points forming the area of polygon are denoted as point1, point2, point3
			double point1X = triangles.get(index).getPoints().get(0);
			double point1Y = triangles.get(index).getPoints().get(1);
			double point2X = triangles.get(index).getPoints().get(2);
			double point2Y = triangles.get(index).getPoints().get(3);
			double point3X = triangles.get(index).getPoints().get(4);
			double point3Y = triangles.get(index).getPoints().get(5);

			// New edges are initialized.
			Line line1 = new Line(point1X, point1Y, xcoord, ycoord);
			Line line2 = new Line(point2X, point2Y, xcoord, ycoord);
			Line line3 = new Line(point3X, point3Y, xcoord, ycoord);

			/*
			 * The original polygon is divided into 3 smaller polygons. These polygons are
			 * not displayed. They're used for geometrical purposes to identify the
			 * triangles.
			 */
			Polygon polygon1 = new Polygon();
			polygon1.getPoints().addAll(new Double[] { point1X, point1Y, point2X, point2Y, xcoord, ycoord });
			Polygon polygon2 = new Polygon();
			polygon2.getPoints().addAll(new Double[] { point1X, point1Y, point3X, point3Y, xcoord, ycoord });
			Polygon polygon3 = new Polygon();
			polygon3.getPoints().addAll(new Double[] { point2X, point2Y, point3X, point3Y, xcoord, ycoord });

			indices[0] = vertices.size();
			indices[1] = edges.size();
			indices[2] = triangles.size();

			vertices.add(circle);

			edges.add(line1);
			edges.add(line2);
			edges.add(line3);

			triangles.add(polygon1);
			triangles.add(polygon2);
			triangles.add(polygon3);

			return indices;
		}
	}

	static class K4PlanarCubic {
		public void initialization() {

			// CIRCLE 1
			System.out.println("K4-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(700);
			circle1.setCenterY(80);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);

			// CIRCLE 2
			System.out.println("K4-CIRCLE2");
			Circle circle2 = new Circle();
			circle2.setCenterX(300);
			circle2.setCenterY(600);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);

			// CIRCLE 3
			System.out.println("K4-CIRCLE3");
			Circle circle3 = new Circle();
			circle3.setCenterX(1100);
			circle3.setCenterY(600);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);

			// CIRCLE 4
			System.out.println("K4-CIRCLE4");
			Circle circle4 = new Circle();
			circle4.setCenterX(700);
			circle4.setCenterY(380);
			circle4.setRadius(20);
			circle4.setFill(Color.BURLYWOOD);

			// LINES
			Line line1 = new Line(700, 80, 300, 600);
			Line line2 = new Line(300, 600, 1100, 600);
			Line line3 = new Line(1100, 600, 700, 80);
			Line line4 = new Line(700, 80, 700, 380);
			Line line5 = new Line(300, 600, 700, 380);
			Line line6 = new Line(1100, 600, 700, 380);

			line1.setStrokeWidth(7);
			line2.setStrokeWidth(7);
			line3.setStrokeWidth(7);
			line4.setStrokeWidth(7);
			line5.setStrokeWidth(7);
			line6.setStrokeWidth(7);

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);
			vertices.add(circle4);

			edges.add(line1);
			edges.add(line2);
			edges.add(line3);
			edges.add(line4);
			edges.add(line5);
			edges.add(line6);
		}
	}

	static class K6PlanarCubic {
		public void initialization() {
			// CIRCLE 1
			System.out.println("K6-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(100);
			circle1.setCenterY(80);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);

			// CIRCLE 2
			System.out.println("K6-CIRCLE2");
			Circle circle2 = new Circle();
			circle2.setCenterX(100);
			circle2.setCenterY(600);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);

			// CIRCLE 3
			System.out.println("K6-CIRCLE3");
			Circle circle3 = new Circle();
			circle3.setCenterX(1250);
			circle3.setCenterY(80);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);

			// CIRCLE 4
			System.out.println("K6-CIRCLE4");
			Circle circle4 = new Circle();
			circle4.setCenterX(1250);
			circle4.setCenterY(600);
			circle4.setRadius(20);
			circle4.setFill(Color.BURLYWOOD);

			// CIRCLE 5
			System.out.println("K6-CIRCLE5");
			Circle circle5 = new Circle();
			circle5.setCenterX(350);
			circle5.setCenterY(340);
			circle5.setRadius(20);
			circle5.setFill(Color.BURLYWOOD);

			// CIRCLE 6
			System.out.println("K6-CIRCLE6");
			Circle circle6 = new Circle();
			circle6.setCenterX(1000);
			circle6.setCenterY(340);
			circle6.setRadius(20);
			circle6.setFill(Color.BURLYWOOD);

			// LINES 1-9
			Line line1 = new Line(100, 80, 1250, 80);
			Line line2 = new Line(100, 80, 100, 600);
			Line line3 = new Line(100, 600, 1250, 600);
			Line line4 = new Line(1250, 600, 1250, 80);
			Line line5 = new Line(100, 80, 350, 340);
			Line line6 = new Line(100, 600, 350, 340);
			Line line7 = new Line(350, 340, 1000, 340);
			Line line8 = new Line(1000, 340, 1250, 80);
			Line line9 = new Line(1000, 340, 1250, 600);

			line1.setStrokeWidth(7);
			line2.setStrokeWidth(7);
			line3.setStrokeWidth(7);
			line4.setStrokeWidth(7);
			line5.setStrokeWidth(7);
			line6.setStrokeWidth(7);
			line7.setStrokeWidth(7);
			line8.setStrokeWidth(7);
			line9.setStrokeWidth(7);

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);
			vertices.add(circle4);
			vertices.add(circle5);
			vertices.add(circle6);

			edges.add(line1);
			edges.add(line2);
			edges.add(line3);
			edges.add(line4);
			edges.add(line5);
			edges.add(line6);
			edges.add(line7);
			edges.add(line8);
			edges.add(line9);
		}

	}

	static class K8PlanarCubic {
		public void initializationTypeI() {
			// CIRCLE 1
			System.out.println("K8-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(100);
			circle1.setCenterY(80);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);

			// CIRCLE 2
			System.out.println("K8-CIRCLE2");
			Circle circle2 = new Circle();
			circle2.setCenterX(100);
			circle2.setCenterY(600);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);

			// CIRCLE 3
			System.out.println("K8-CIRCLE3");
			Circle circle3 = new Circle();
			circle3.setCenterX(1250);
			circle3.setCenterY(80);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);

			// CIRCLE 4
			System.out.println("K8-CIRCLE4");
			Circle circle4 = new Circle();
			circle4.setCenterX(1250);
			circle4.setCenterY(600);
			circle4.setRadius(20);
			circle4.setFill(Color.BURLYWOOD);

			// CIRCLE 5
			Circle circle5 = new Circle();
			circle5.setCenterX(300);
			circle5.setCenterY(180);
			circle5.setRadius(20);
			circle5.setFill(Color.BURLYWOOD);

			// CIRCLE 6
			Circle circle6 = new Circle();
			circle6.setCenterX(1050);
			circle6.setCenterY(180);
			circle6.setRadius(20);
			circle6.setFill(Color.BURLYWOOD);

			// CIRCLE 7
			Circle circle7 = new Circle();
			circle7.setCenterX(300);
			circle7.setCenterY(500);
			circle7.setRadius(20);
			circle7.setFill(Color.BURLYWOOD);

			// CIRCLE 8
			Circle circle8 = new Circle();
			circle8.setCenterX(1050);
			circle8.setCenterY(500);
			circle8.setRadius(20);
			circle8.setFill(Color.BURLYWOOD);

			// LINES 1-12
			// big rectangle
			Line line1 = new Line(100, 80, 1250, 80);
			Line line2 = new Line(100, 80, 100, 600);
			Line line3 = new Line(100, 600, 1250, 600);
			Line line4 = new Line(1250, 600, 1250, 80);

			// little rectangle
			Line line5 = new Line(300, 180, 1050, 180);
			Line line6 = new Line(1050, 180, 1050, 500);
			Line line7 = new Line(1050, 500, 300, 500);
			Line line8 = new Line(300, 500, 300, 180);

			// cross edges
			Line line9 = new Line(1250, 80, 1050, 180);
			Line line10 = new Line(1250, 600, 1050, 500);
			Line line11 = new Line(100, 80, 300, 180);
			Line line12 = new Line(100, 600, 300, 500);

			line1.setStrokeWidth(7);
			line2.setStrokeWidth(7);
			line3.setStrokeWidth(7);
			line4.setStrokeWidth(7);
			line5.setStrokeWidth(7);
			line6.setStrokeWidth(7);
			line7.setStrokeWidth(7);
			line8.setStrokeWidth(7);
			line9.setStrokeWidth(7);
			line10.setStrokeWidth(7);
			line11.setStrokeWidth(7);
			line12.setStrokeWidth(7);

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);
			vertices.add(circle4);
			vertices.add(circle5);
			vertices.add(circle6);
			vertices.add(circle7);
			vertices.add(circle8);

			edges.add(line1);
			edges.add(line2);
			edges.add(line3);
			edges.add(line4);
			edges.add(line5);
			edges.add(line6);
			edges.add(line7);
			edges.add(line8);
			edges.add(line9);
			edges.add(line10);
			edges.add(line11);
			edges.add(line12);

		}

		public void initializationTypeII() {
			// CIRCLE 1
			System.out.println("K8-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(100);
			circle1.setCenterY(340);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);

			// CIRCLE 2
			System.out.println("K8-CIRCLE2");

			Circle circle2 = new Circle();
			circle2.setCenterX(300);
			circle2.setCenterY(80);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);

			// CIRCLE 3
			System.out.println("K8-CIRCLE3");
			Circle circle3 = new Circle();
			circle3.setCenterX(1050);
			circle3.setCenterY(80);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);

			// CIRCLE 4
			System.out.println("K8-CIRCLE4");
			Circle circle4 = new Circle();
			circle4.setCenterX(1250);
			circle4.setCenterY(340);
			circle4.setRadius(20);
			circle4.setFill(Color.BURLYWOOD);

			// CIRCLE 5
			Circle circle5 = new Circle();
			circle5.setCenterX(500);
			circle5.setCenterY(340);
			circle5.setRadius(20);
			circle5.setFill(Color.BURLYWOOD);

			// CIRCLE 6
			Circle circle6 = new Circle();
			circle6.setCenterX(850);
			circle6.setCenterY(340);
			circle6.setRadius(20);
			circle6.setFill(Color.BURLYWOOD);

			// CIRCLE 7
			Circle circle7 = new Circle();
			circle7.setCenterX(300);
			circle7.setCenterY(600);
			circle7.setRadius(20);
			circle7.setFill(Color.BURLYWOOD);

			// CIRCLE 8
			Circle circle8 = new Circle();
			circle8.setCenterX(1050);
			circle8.setCenterY(600);
			circle8.setRadius(20);
			circle8.setFill(Color.BURLYWOOD);

			// LINES
			// rhombus 1 (left side one)
			Line line1 = new Line(300, 80, 100, 340);
			Line line2 = new Line(100, 340, 300, 600);
			Line line3 = new Line(300, 600, 500, 340);
			Line line4 = new Line(500, 340, 300, 80);
			Line line5 = new Line(500, 340, 100, 340);

			// rhombus 2 (right side one)
			Line line6 = new Line(1050, 80, 850, 340);
			Line line7 = new Line(850, 340, 1050, 600);
			Line line8 = new Line(1050, 600, 1250, 340);
			Line line9 = new Line(1250, 340, 1050, 80);
			Line line10 = new Line(850, 340, 1250, 340);

			// other edges
			Line line11 = new Line(300, 80, 1050, 80);
			Line line12 = new Line(300, 600, 1050, 600);

			line1.setStrokeWidth(7);
			line2.setStrokeWidth(7);
			line3.setStrokeWidth(7);
			line4.setStrokeWidth(7);
			line5.setStrokeWidth(7);
			line6.setStrokeWidth(7);
			line7.setStrokeWidth(7);
			line8.setStrokeWidth(7);
			line9.setStrokeWidth(7);
			line10.setStrokeWidth(7);
			line11.setStrokeWidth(7);
			line12.setStrokeWidth(7);

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);
			vertices.add(circle4);
			vertices.add(circle5);
			vertices.add(circle6);
			vertices.add(circle7);
			vertices.add(circle8);

			edges.add(line1);
			edges.add(line2);
			edges.add(line3);
			edges.add(line4);
			edges.add(line5);
			edges.add(line6);
			edges.add(line7);
			edges.add(line8);
			edges.add(line9);
			edges.add(line10);
			edges.add(line11);
			edges.add(line12);

		}

		public void initializationTypeIII() {

			// CIRCLE 1
			System.out.println("K8-CIRCLE1");
			Circle circle1 = new Circle();
			circle1.setCenterX(300);
			circle1.setCenterY(80);
			circle1.setRadius(20);
			circle1.setFill(Color.BURLYWOOD);

			// CIRCLE 2
			System.out.println("K8-CIRCLE2");

			Circle circle2 = new Circle();
			circle2.setCenterX(675);
			circle2.setCenterY(80);
			circle2.setRadius(20);
			circle2.setFill(Color.BURLYWOOD);

			// CIRCLE 3
			System.out.println("K8-CIRCLE3");

			Circle circle3 = new Circle();
			circle3.setCenterX(1050);
			circle3.setCenterY(80);
			circle3.setRadius(20);
			circle3.setFill(Color.BURLYWOOD);

			// CIRCLE 4
			System.out.println("K8-CIRCLE4");

			Circle circle4 = new Circle();
			circle4.setCenterX(300);
			circle4.setCenterY(340);
			circle4.setRadius(20);
			circle4.setFill(Color.BURLYWOOD);

			// CIRCLE 5
			Circle circle5 = new Circle();
			circle5.setCenterX(675);
			circle5.setCenterY(340);
			circle5.setRadius(20);
			circle5.setFill(Color.BURLYWOOD);

			// CIRCLE 6
			Circle circle6 = new Circle();
			circle6.setCenterX(1050);
			circle6.setCenterY(340);
			circle6.setRadius(20);
			circle6.setFill(Color.BURLYWOOD);

			// CIRCLE 7
			Circle circle7 = new Circle();
			circle7.setCenterX(100);
			circle7.setCenterY(600);
			circle7.setRadius(20);
			circle7.setFill(Color.BURLYWOOD);

			// CIRCLE 8
			Circle circle8 = new Circle();
			circle8.setCenterX(1250);
			circle8.setCenterY(600);
			circle8.setRadius(20);
			circle8.setFill(Color.BURLYWOOD);

			// LINES
			// rectangle 1
			Line line1 = new Line(300, 80, 675, 80);
			Line line2 = new Line(675, 80, 675, 340);
			Line line3 = new Line(675, 340, 300, 340);
			Line line4 = new Line(300, 340, 300, 80);

			// rectangle 2
			Line line5 = new Line(675, 80, 1050, 80);
			Line line6 = new Line(1050, 80, 1050, 340);
			Line line7 = new Line(1050, 340, 675, 340); // another edge already listed above

			// trapezoidal edges
			Line line8 = new Line(300, 80, 100, 600);
			Line line9 = new Line(300, 340, 100, 600);
			Line line10 = new Line(1050, 80, 1250, 600);
			Line line11 = new Line(1050, 340, 1250, 600);
			Line line12 = new Line(100, 600, 1250, 600);

			line1.setStrokeWidth(7);
			line2.setStrokeWidth(7);
			line3.setStrokeWidth(7);
			line4.setStrokeWidth(7);
			line5.setStrokeWidth(7);
			line6.setStrokeWidth(7);
			line7.setStrokeWidth(7);
			line8.setStrokeWidth(7);
			line9.setStrokeWidth(7);
			line10.setStrokeWidth(7);
			line11.setStrokeWidth(7);
			line12.setStrokeWidth(7);

			vertices.add(circle1);
			vertices.add(circle2);
			vertices.add(circle3);
			vertices.add(circle4);
			vertices.add(circle5);
			vertices.add(circle6);
			vertices.add(circle7);
			vertices.add(circle8);

			edges.add(line1);
			edges.add(line2);
			edges.add(line3);
			edges.add(line4);
			edges.add(line5);
			edges.add(line6);
			edges.add(line7);
			edges.add(line8);
			edges.add(line9);
			edges.add(line10);
			edges.add(line11);
			edges.add(line12);

		}

	}

}
