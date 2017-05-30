// Homework 6: Java Database GUI
// Student Name: Benjamin Teskey
// Course: CIS357, Winter 2017
// Instructor: Dr. Cho
// Date finished: 4/18/2017
// Program description: This program provides a GUI post system for purchasing products and displaying
// current sale, new sale and total sale data to the user. Item, Sale, and Total Sale are abstracted into
// separate classes. Items are fetched from a local database. 

// [Done] 1.	Item database created
// [Done] 2.	Query to the database works in both command line and GUI
// [Done] 3.	Connecting to the database using Java program
// [Done] 4.	Retrieving item data (item name and unit price) from item code, and displaying 
// them on the GUI
// [Done] 5.	Displaying all items and their total for each sale
// [Not Done] 6.	Storing the sales data in the database (bonus 5 points)
// [Regular Done, bonus Not Done] 7.	Displaying total sale (total amount and all the sale items) 
// for all the sales stored so far (bonus 5 points if the sales data is retrieved from the database)


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Hw6GUIDatabase extends Application {

    // Arraylists of items and total sale object
    private static ArrayList<Item> items = new ArrayList<Item>();
    private ListView<String> salesList;

    private TotalSale sales = new TotalSale();
    private Label lblSaleSubTotalAmount;
    private Label lblSaleTaxTotalAmount;
    private Label lblChangeAmount;
    private DecimalFormat df = new DecimalFormat("$#0.00");

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("POST Register");

        // Make welcome label
        Label lblWelcomeText = new Label("Welcome to the POST system!!!");
        lblWelcomeText.setFont(Font.font("Comic Sans MS", 22));

        // Make new buttons for new sale and total, and set dimensions
        Button btnNewSale = new Button("New Sale");
        btnNewSale.setPrefSize(100, 40);

        Button btnTotalSale = new Button("Total Sale");
        btnTotalSale.setPrefSize(100, 40);

        // Create and add padding to borderpane layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20, 20, 20, 20));

        // Create VBox to hold both buttons
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(btnNewSale, btnTotalSale);

        // Create action listeners for buttons
        btnNewSale.setOnAction(e -> newSale());
        btnTotalSale.setOnAction(e -> totalSale());

        // Set welcome text to top, VBox with both buttons to center
        mainLayout.setTop(lblWelcomeText);
        mainLayout.setCenter(buttonBox);

        // Center align button box
        buttonBox.setAlignment(Pos.CENTER);

        Scene mainScene = new Scene(mainLayout, 375, 300);

        // Set scene for primary stage
        primaryStage.setScene(mainScene);

        // Show the GUI
        primaryStage.show();

        // Call method to choose and load file
        loadFromDatabase();

    }

    /**
     * Handles getting item data from local database
     *
     */
    private void loadFromDatabase() {

        try{

            Class.forName("com.mysql.jdbc.Driver");

            // Driver manager connection to local mysql database.
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hw6","root","");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM item");

            while(rs.next()) items.add(new Item(rs.getString("id").charAt(0),
                    rs.getDouble("price"), rs.getString("name"), 0));

            con.close();

        } catch(Exception e){

            e.printStackTrace();

        }

    }

    /**
     * Handles building the new sale window
     */
    private void newSale() {

        Stage newSaleStage = new Stage();
        newSaleStage.setTitle("New Sale");

        // Make a new sale object to store item data
        Sale newSale = new Sale();

        // Create VBox to hold top and bottom content
        VBox newSaleVBox = new VBox(20);
        newSaleVBox.setPrefWidth(500);
        newSaleVBox.setStyle("-fx-border-color: black");

        // Create listview object
        salesList = new ListView<String>();
        salesList.getItems().add("Items List:");
        salesList.getItems().add("");


        // Create top gridPane
        GridPane topGrid = buildTopGrid(newSale);
        topGrid.setPadding(new Insets(10, 10, 10, 10));
        topGrid.setPrefWidth(500);
        topGrid.setHgap(10);
        topGrid.setVgap(10);

        GridPane bottomGrid = buildBottomGrid(newSale, newSaleStage);
        bottomGrid.setPadding(new Insets(10, 10, 10, 10));
        bottomGrid.setPrefWidth(500);
        bottomGrid.setHgap(10);
        bottomGrid.setVgap(10);

        // Add Gridpanes to VBox
        newSaleVBox.getChildren().addAll(topGrid, bottomGrid);

        Scene newSaleScene = new Scene(newSaleVBox, 400, 550);

        // Set the scene and show the stage
        newSaleStage.setScene(newSaleScene);
        newSaleStage.show();

    }

    /**
     * Handles building the top grid for the new sale window, then returning it
     *
     * @param newSale Sale which is the user's current sale in progress
     * @return The top gridpane for the new sale window
     */
    private GridPane buildTopGrid(Sale newSale) {

        GridPane topGrid = new GridPane();

        Label lblItemID = new Label("Item ID:");
        Label lblItemNameLabel = new Label("Item Name:");
        Label lblItemNameContent = new Label("");
        Label lblItemPriceLabel = new Label("Item Price:");
        Label lblItemPriceContent = new Label("");
        Label lblQuantity = new Label("Quantity:");
        Label lblItemTotalLabel = new Label("Item Total:");
        Label lblItemTotalContent = new Label("");

        TextField txtQuantity = new TextField();

        ComboBox cbItemBox = new ComboBox();

        Button btnAdd = new Button("Add");

        // Populate combobox with item IDs
        for (int i = 0; i < items.size(); i++) cbItemBox.getItems().add(items.get(i).getId());

        // Add listener for selection change
        cbItemBox.getSelectionModel().selectedItemProperty().addListener(
                (options, oldValue, newValue) -> itemSelectionChange(cbItemBox, lblItemNameContent,
                        lblItemPriceContent, txtQuantity, lblItemTotalContent)
        );

        txtQuantity.textProperty().addListener((observable, oldValue, newValue) ->
                itemSelectionChange(cbItemBox, lblItemNameContent, lblItemPriceContent, txtQuantity,
                        lblItemTotalContent));

        btnAdd.setOnAction(e -> addItem(cbItemBox, txtQuantity, newSale));

        // Select the first item by default
        cbItemBox.getSelectionModel().select(0);

        topGrid.getChildren().addAll(lblItemID, cbItemBox, lblItemNameLabel, lblItemNameContent,
                lblItemPriceLabel, lblItemPriceContent, lblQuantity, txtQuantity,
                lblItemTotalLabel, lblItemTotalContent, btnAdd);

        topGrid.setConstraints(lblItemID, 0, 0);
        topGrid.setConstraints(cbItemBox, 1, 0);
        topGrid.setConstraints(lblItemNameLabel, 0, 1);
        topGrid.setConstraints(lblItemNameContent, 1, 1);
        topGrid.setConstraints(lblItemPriceLabel, 0, 2);
        topGrid.setConstraints(lblItemPriceContent, 1, 2);
        topGrid.setConstraints(lblQuantity, 0, 3);
        topGrid.setConstraints(txtQuantity, 1, 3);
        topGrid.setConstraints(lblItemTotalLabel, 0, 4);
        topGrid.setConstraints(lblItemTotalContent, 1, 4);
        topGrid.setConstraints(btnAdd, 1, 5);

        return topGrid;

    }

    /**
     * Handles building the bottom grid for the new sale window, then returning it
     *
     * @param newSale Sale which is the user's current sale in progress
     * @param newSaleStage Stage for the new sale window
     * @return The bottom gridpane for the new sale window
     */
    private GridPane buildBottomGrid(Sale newSale, Stage newSaleStage) {

        // Listview preferred dimensions
        salesList.setPrefWidth(500);
        salesList.setPrefHeight(200);

        // Create bottom gridpane
        GridPane bottomGrid = new GridPane();

        // Node items
        Label lblSaleSubTotal = new Label("Sale Sub Total:");
        lblSaleSubTotalAmount = new Label("");
        Label lblSaleTaxTotal = new Label("Sale Tax Total (6%):");
        lblSaleTaxTotalAmount = new Label("");
        Label lblTenderedAmount = new Label("Tendered Amount:");
        TextField txtTenderedAmount = new TextField();
        Button btnCheckout = new Button("Checkout");
        Label lblChange = new Label("Change:");
        lblChangeAmount = new Label("");
        Button btnDone = new Button("Done");

        // Add them
        bottomGrid.getChildren().addAll(salesList, lblSaleSubTotal, lblSaleSubTotalAmount, lblSaleTaxTotal,
                lblSaleTaxTotalAmount, lblTenderedAmount, txtTenderedAmount, btnCheckout, lblChange, lblChangeAmount,
                btnDone);

        // Columns and rows of items
        bottomGrid.setConstraints(salesList, 0,0, 3, 2);
        bottomGrid.setConstraints(lblSaleSubTotal, 0, 3);
        bottomGrid.setConstraints(lblSaleSubTotalAmount, 1, 3);
        bottomGrid.setConstraints(lblSaleTaxTotal, 0, 4);
        bottomGrid.setConstraints(lblSaleTaxTotalAmount, 1, 4);
        bottomGrid.setConstraints(lblTenderedAmount, 0, 5);
        bottomGrid.setConstraints(txtTenderedAmount, 1, 5);
        bottomGrid.setConstraints(btnCheckout, 2, 5);
        bottomGrid.setConstraints(lblChange, 0, 6);
        bottomGrid.setConstraints(lblChangeAmount, 1, 6);
        bottomGrid.setConstraints(btnDone, 1, 7);

        // Event handlers for buttons
        btnCheckout.setOnAction(e -> {
            checkout(Double.parseDouble(txtTenderedAmount.getText()), newSale);
        });
        btnDone.setOnAction(e -> {

            // Check if the user has checked out (their sale will already be in total sale)
            if (sales.getSaleList().contains(newSale)) {
                newSaleStage.close();
                done(newSale);
            }

        });

        return bottomGrid;

    }

    /**
     * Handles displaying the user's completed order in a new window
     *
     * @param newSale Sale which is the user's current sale in progress
     */
    public void done(Sale newSale) {

        // Create a new stage for the window
        Stage doneStage = new Stage();
        doneStage.setTitle("Your Purchase");

        VBox itemsDoneBox = new VBox(10);
        itemsDoneBox.setPadding(new Insets(10, 10, 10, 10));
        ListView<String> itemsDoneList = new ListView<String>();
        Button btnOK = new Button("OK");

        // This algorithm ensures that there will be no duplicate list entries for items
        // by adding up quantities and putting them in the items list, rather than just taking
        // the user's list and outputting it to the screen again.
        for (int i = 0; i < items.size(); i++) {

            for (int j = 0; j < newSale.getPurchaseList().size(); j++) {

                if (items.get(i).getId() == newSale.getPurchaseList().get(j).getId())
                    items.get(i).setQuantity(items.get(i).getQuantity() +
                            newSale.getPurchaseList().get(j).getQuantity());

            }

        }

        double cost = 0;

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getQuantity() > 0) {

                itemsDoneList.getItems().add(items.get(i).getQuantity() + " " + items.get(i).getName());
                cost += items.get(i).getQuantity() * items.get(i).getPrice();

                items.get(i).setQuantity(0);

            }

        }

        // Close the window when the OK button is clicked
        btnOK.setOnAction(e -> doneStage.close());

        Label lblTotal = new Label("Total:");
        Label lblTotalAmount = new Label(df.format(cost));

        HBox totalBox = new HBox(10);
        totalBox.getChildren().addAll(lblTotal, lblTotalAmount, btnOK);

        itemsDoneBox.getChildren().addAll(itemsDoneList, totalBox);

        Scene doneScene = new Scene(itemsDoneBox, 200, 300);
        doneStage.setScene(doneScene);
        doneStage.show();

    }

    /**
     * Handles the event for adding an item to a sale in the new sale window
     *
     * @param checkoutInput double which is the amount the user is attempting to pay
     * @param newSale Sale which is the user's current sale in progress
     */
    public void checkout(double checkoutInput, Sale newSale) {

        // Re-calculate sales tax total
        double salesTaxTotal = 0;

        for (int i = 0; i < newSale.getPurchaseList().size(); i++) {

            salesTaxTotal += newSale.getPurchaseList().get(i).getPrice() *
                    newSale.getPurchaseList().get(i).getQuantity() * 1.06;

        }

        if (checkoutInput >= salesTaxTotal) {

            // Return the change amount to the user and commit the sale to the total sale
            lblChangeAmount.setText(df.format(checkoutInput - salesTaxTotal));
            sales.getSaleList().add(newSale);

        }

    }

    /**
     * Handles the event for adding an item to a sale in the new sale window
     *
     * @param cbItemBox ComboBox for the item selected
     * @param txtQuantity TextField for getting the amount of the item chosen
     * @param newSale Sale which is the user's current sale in progress
     */
    private void addItem(ComboBox cbItemBox, TextField txtQuantity, Sale newSale) {

        if (!txtQuantity.getText().isEmpty() && !cbItemBox.getSelectionModel().isEmpty()) {

            // Get selected item, then add a new item to purchases in Sale
            Item selectedItem = items.get(cbItemBox.getSelectionModel().getSelectedIndex());
            Item newItem = new Item(selectedItem.getId(), selectedItem.getPrice(),
                    selectedItem.getName(), Integer.parseInt(txtQuantity.getText()));

            newSale.getPurchaseList().add(newItem);

            // Now add the item to the list
            salesList.getItems().add(newItem.getQuantity() + " " + newItem.getName() + "      " +
                df.format(newItem.getPrice() * newItem.getQuantity()));

            // Now calculate sale sub total
            double saleSubTotal = 0;

            for (int i = 0; i < newSale.getPurchaseList().size(); i++) {

                saleSubTotal += newSale.getPurchaseList().get(i).getQuantity() *
                        newSale.getPurchaseList().get(i).getPrice();

            }

            lblSaleSubTotalAmount.setText(df.format(saleSubTotal));
            lblSaleTaxTotalAmount.setText(df.format(saleSubTotal + (0.06 * saleSubTotal)));

        }

    }

    /**
     * Handles the event for changing an item selection in the new sale window
     *
     * @param cbItemBox ComboBox for all the items
     * @param lblItemNameContent Label for the item name selected
     * @param lblItemPriceContent Label for the price of the item selected
     * @param txtQuantity TextField for the quantity of items selected
     * @param lblItemTotalContent Label for handling total cost of the items
     */
    private void itemSelectionChange(ComboBox cbItemBox, Label lblItemNameContent, Label lblItemPriceContent,
                                     TextField txtQuantity, Label lblItemTotalContent) {

        int selectedIndex = cbItemBox.getSelectionModel().getSelectedIndex();

        lblItemNameContent.setText(items.get(selectedIndex).getName());

        lblItemPriceContent.setText(df.format(items.get(selectedIndex).getPrice()));

        double quantity = 0;
        try {

            quantity = Double.parseDouble(txtQuantity.getText());

        } catch(Exception someExceptionOrOther) {}

        lblItemTotalContent.setText(df.format(items.get(selectedIndex).getPrice() * quantity));

    }

    /**
     * Handles the total sale window, and all the code necessary to display its content
     */
    private void totalSale() {

        // Create stage. Pretty much same structure and formula as done window, but with all of the sales
        Stage totalSaleStage = new Stage();
        totalSaleStage.setTitle("Total Sale");

        VBox totalSaleBox = new VBox(10);
        ListView totalSaleList = new ListView();

        // Create OK button
        Button btnOK = new Button("OK");

        totalSaleBox.setPadding(new Insets(10, 10, 10, 10));

        // Certainly not a very efficient algorithm, but it gets the job done
        for (int i = 0; i < sales.getSaleList().size(); i++) {

            for (int j = 0; j < items.size(); j++) {

                for (int k = 0; k < sales.getSaleList().get(i).getPurchaseList().size(); k++) {

                    if (items.get(j).getId() == sales.getSaleList().get(i).getPurchaseList().get(k).getId())
                        items.get(j).setQuantity(items.get(j).getQuantity() +
                                sales.getSaleList().get(i).getPurchaseList().get(k).getQuantity());

                }

            }

        }

        double cost = 0;

        // Add items to list
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getQuantity() > 0) {

                totalSaleList.getItems().add(items.get(i).getQuantity() + " " + items.get(i).getName() +
                "    " + df.format(items.get(i).getQuantity() * items.get(i).getPrice()));
                cost += items.get(i).getQuantity() * items.get(i).getPrice();

                items.get(i).setQuantity(0);

            }

        }

        Label lblTotal = new Label("Total:");
        Label lblTotalAmount = new Label("");

        lblTotalAmount.setText(df.format(cost));

        // Make hbox for non-list view content
        HBox totalBox = new HBox(10);
        totalBox.getChildren().addAll(lblTotal, lblTotalAmount, btnOK);

        // Action listener for OK button to close the window
        btnOK.setOnAction(e -> totalSaleStage.close());

        totalSaleBox.getChildren().addAll(totalSaleList, totalBox);

        // Make scene, set scene, and show stage
        Scene totalSaleScene = new Scene(totalSaleBox);
        totalSaleStage.setScene(totalSaleScene);
        totalSaleStage.show();

    }

}
