package com.testJpa;

import com.testJpa.model.Product;
import com.testJpa.crud.ProductService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {
    private ProductService productService;

    private static TableView<Product> table;

    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello, World!");
    }

    private static TableView<Product> createTableProduct() {
        TableView<Product> table = new TableView<Product>();

        table.setMinSize(550, 300);

        TableColumn<Product, Integer> productIdCol = new TableColumn<Product, Integer>("id");
        TableColumn<Product, String> productNameCol = new TableColumn<Product, String>("Name");
        TableColumn<Product, String> productMeasureCol = new TableColumn<Product, String>("Measure");
        TableColumn<Product, Double> productPriceCol = new TableColumn<Product, Double>("Price");
        TableColumn<Product, Integer> productCountCol = new TableColumn<Product, Integer>("Count");
        TableColumn<Product, Integer> productNumberOfStockCol = new TableColumn<Product, Integer>("Number of Stock");


        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productMeasureCol.setCellValueFactory(new PropertyValueFactory<>("measure"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productCountCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        productNumberOfStockCol.setCellValueFactory(new PropertyValueFactory<>("id_stock"));

        table.getColumns().addAll(productIdCol, productNameCol, productMeasureCol, productPriceCol, productCountCol, productNumberOfStockCol);

        return table;
    }

    private static GridPane createSettingsGrid(ProductService prodService) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setMinSize(450, 300);
        grid.setHgap(15);
        grid.setVgap(15);

        grid.setPadding(new Insets(5, 5, 5, 5));

        Button btn = new Button("All products");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                List<Product> listPr = prodService.getAll();
                ObservableList<Product> list = FXCollections.observableList(listPr);
                table.setItems(list);
            }
        });

        grid.add(hbBtn, 0, 0,2,1);

        Text scenetitle = new Text("Filter");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 1, 2, 1);

        grid.add(new Label("Id:"), 0, 2);
        TextField productIdTextField = new TextField();
        grid.add(productIdTextField, 1, 2);

        grid.add(new Label("Name:"), 0, 3);
        TextField productNameTextField = new TextField();
        grid.add(productNameTextField, 1, 3);


        grid.add(new Label("Stock:"), 0, 4);
        TextField productIdStockTextField = new TextField();
        grid.add(productIdStockTextField, 1, 4);


        Text price = new Text("Price");
        price.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(price, 0, 5, 2, 1);


        grid.add(new Label("From:"), 0, 6);
        TextField productPriceOtTextField = new TextField();
        grid.add(productPriceOtTextField, 1, 6);


        grid.add(new Label("Before:"), 0, 7);
        TextField productPriceDoTextField = new TextField();
        grid.add(productPriceDoTextField, 1, 7);


        Text count = new Text("Count");
        count.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(count, 0, 8, 2, 1);

        grid.add(new Label("From:"), 0, 9);
        TextField productCountOtTextField = new TextField();
        grid.add(productCountOtTextField, 1, 9);


        grid.add(new Label("Before:"), 0, 10);
        TextField productCountDoTextField = new TextField();
        grid.add(productCountDoTextField, 1, 10);


        Button btn1 = new Button("Filter");
        HBox hbBtn1 = new HBox();
        hbBtn1.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn1.getChildren().add(btn1);

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                List<String> listFilter = new LinkedList<>();

                String productId = productIdTextField.getText();
                if (!productId.equals("")) {
                    try {
                        Integer productIdInt = Integer.parseInt(productId);
                        if (productIdInt >= 0) {
                            listFilter.add(" p.id = " + productIdInt + " ");
                        } else {
                            productIdTextField.clear();
                        }
                    } catch (Exception ex) {
                        productIdTextField.clear();
                    }
                }

                String productName = productNameTextField.getText();
                if (!productName.equals("")) {
                    listFilter.add("p.name = \'" + productName +"\' ");
                }

                String productIdStock = productIdStockTextField.getText();
                if (!productIdStock.equals("")) {
                    try {
                        Integer productIdStockInt = Integer.parseInt(productIdStock);
                        if (productIdStockInt >= 0) {
                            listFilter.add(" p.id_stock = " + productIdStockInt + " ");
                        } else {
                            productIdStockTextField.clear();
                        }
                    } catch (Exception ex) {
                        productIdStockTextField.clear();
                    }
                }

                String productPriceFrom = productPriceOtTextField.getText();
                if (!productPriceFrom.equals("")) {
                    try {
                        Integer productPriceFromInt = Integer.parseInt(productPriceFrom);
                        if (productPriceFromInt >= 0) {
                            listFilter.add(" p.price >= " + productPriceFromInt + " ");
                        } else {
                            productPriceOtTextField.clear();
                        }
                    } catch (Exception ex) {
                        productPriceOtTextField.clear();
                    }
                }

                String productPriceBefore = productPriceDoTextField.getText();
                if (!productPriceBefore.equals("")) {
                    try {
                        Integer productPriceBeforeInt = Integer.parseInt(productPriceBefore);
                        if (productPriceBeforeInt >= 0) {
                            listFilter.add(" p.count <= " + productPriceBeforeInt + " ");
                        } else {
                            productPriceDoTextField.clear();
                        }
                    } catch (Exception ex) {
                        productPriceDoTextField.clear();
                    }
                }

                String productCountFrom =  productCountOtTextField.getText();
                if (!productCountFrom.equals("")) {
                    try {
                        Integer productCountFromInt = Integer.parseInt(productCountFrom);
                        if (productCountFromInt >= 0) {
                            listFilter.add(" p.count >= " + productCountFromInt + " ");
                        } else {
                            productCountOtTextField.clear();
                        }
                    } catch (Exception ex) {
                        productCountOtTextField.clear();
                    }
                }

                String productCountBefore =  productCountDoTextField.getText();
                if (!productCountBefore.equals("")) {
                    try {
                        Integer productCountBeforeInt = Integer.parseInt(productCountBefore);
                        if (productCountBeforeInt >= 0) {
                            listFilter.add(" p.count <= " + productCountBeforeInt + " ");
                        } else {
                            productCountDoTextField.clear();
                        }
                    } catch (Exception ex) {
                        productCountDoTextField.clear();
                    }
                }

                String res = "";

                if (listFilter.size() > 0) {
                    res = " where ";
                    for (int i = 0; i < listFilter.size() - 1; i++) {
                        res = res + listFilter.get(i) + " and ";
                    }
                    res = res + listFilter.get(listFilter.size() - 1);
                }

                List<Product> listP = prodService.filter(res);

                ObservableList<Product> list = FXCollections.observableList(listP);
                table.setItems(list);
            }
        });

        grid.add(hbBtn1, 0, 11,2,1);

        return grid;
    }

    private static VBox createAddProductVBox(ProductService prService) {
        TextField productName = new TextField();
        TextField productPrice = new TextField();
        TextField productCount = new TextField();


        ObservableList<String> mesure = FXCollections.observableArrayList("штук", "килограмм", "литров");
        ComboBox<String> mesureComboBox = new ComboBox<String>(mesure);
        mesureComboBox.setValue("штук");

        ObservableList<Integer> stocks = FXCollections.observableArrayList(1, 2, 3);
        ComboBox<Integer> stocksComboBox = new ComboBox<Integer>(stocks);
        stocksComboBox.setValue(1);

        Button btn = new Button("Add New Product");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String prName = productName.getText();
                String prPrice = productPrice.getText();
                String prCount = productCount.getText();

                if (prName.equals("") || prPrice.equals("") || prCount.equals("")) {
                    return;
                }

                Integer prPriceD;
                try {
                    prPriceD = Integer.parseInt(prPrice);
                } catch (Exception ex) {
                    productPrice.clear();
                    return;
                }

                Integer prCountD;
                try {
                    prCountD = Integer.parseInt(prCount);
                } catch (Exception ex) {
                    productCount.clear();
                    return;
                }

                //productRepo.addProduct(new Product(-1, prName, mesureComboBox.getValue(), prPriceD, prCountD, stocksComboBox.getValue()));

                Product product = new Product(prName, mesureComboBox.getValue(), prPriceD, prCountD, stocksComboBox.getValue());
                prService.add(product);

                productName.clear();
                productPrice.clear();
                productCount.clear();
            }
        });

        HBox hBox1 = new HBox(new Label("Name: "), productName, new Label("   Id stock:  "), stocksComboBox, new Label("                    Price: "), productPrice, new Label("   "), btn);
        hBox1.setPadding(new Insets(5,5,5,0));
        HBox hBox2 = new HBox(new Label("Count: "), productCount, new Label("   Measure: "), mesureComboBox);
        hBox2.setPadding(new Insets(5,5,5,0));

        VBox vBox = new VBox(hBox1, hBox2);
        vBox.setPadding(new Insets(5,5,5,5));

        return vBox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        productService = new ProductService();

        //List<Product> list = productService.getAll();

        Product product = new Product("pr_name", "pr_measure", 10,15,1);
        productService.add(product);

        System.out.println("It is OK");

        GridPane grid = createSettingsGrid(productService);
        table = createTableProduct();
        VBox vBoxAddProduct = createAddProductVBox(productService);

        BorderPane root = new BorderPane();
        root.setTop(vBoxAddProduct);
        root.setLeft(table);
        root.setRight(grid);

        Scene scene = new Scene(root, 850, 700);
        stage.setScene(scene);
        stage.setTitle("Склад №1");

        stage.show();
    }
}


