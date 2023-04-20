package com.example.ecommerceapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {
    GridPane loginPage;
    HBox headerBar, footerBar;
    VBox body;
    Label welcomeLabel;
    VBox productPage;
    TextField searchBar;
    Button homeButton, signInButton, signOutButton, searchButton, addToCart, buyNowButton, placeOrderButton, accountButton, cartButton, ordersButton;
    Customer loggedInCustomer;
    ProductList productList = new ProductList();
    OrderList orderList = new OrderList();
    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();


    UserInterface(){
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }


    public BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setTop(headerBar);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage = productList.getAllProducts();
        body.getChildren().add(productPage);
        root.setBottom(footerBar);
        return root;
    }


    private void createLoginPage(){
        Text username = new Text("Email or mobile phone number: ");
        Text password = new Text("Password: ");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Your Email or mobile phone number ");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Your password");

        Button loginButton = new Button("Login");

        Label textLabel = new Label();
        welcomeLabel = new Label();
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Login login = new Login();
                loggedInCustomer = login.customerLogin(usernameField.getText(),passwordField.getText());
                if(loggedInCustomer!=null){
                    textLabel.setText("Login Successful...");
                    welcomeLabel.setText("Welcome, "+loggedInCustomer.getName());
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                    headerBar.getChildren().clear();
                    headerBar.getChildren().addAll(homeButton,searchBar,searchButton,welcomeLabel,accountButton,ordersButton,cartButton);
                }else {
                    textLabel.setText("Login Unsuccessful!!! ");
                    showDialogWarning("Incorrect Credentials \nUSER NOT FOUND");
                }

            }
        });

        loginPage = new GridPane();
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setVgap(20);
        loginPage.setHgap(20);
        loginPage.add(username,0,0);
        loginPage.add(usernameField,1,0);
        loginPage.add(password,0,1);
        loginPage.add(passwordField,1,1);
        loginPage.add(loginButton,1,2);
        loginPage.add(textLabel,1,3);
    }


    private void createHeaderBar(){
        searchBar = new TextField();
        searchBar.setPromptText("Search products here...");
        searchBar.setPrefSize(400,25);

        // Home Button
        homeButton = new Button();
        ImageView ecommerceIcon = new ImageView("file:/Users/ajay/IdeaProjects/E-Commerce-Application/src/main/resources/home_icon.png");
        ecommerceIcon.setFitHeight(20);
        ecommerceIcon.setFitWidth(20);
        homeButton.setGraphic(ecommerceIcon);

        //search button
        searchButton = new Button();
        ImageView imageView = new ImageView("file:/Users/ajay/IdeaProjects/E-Commerce-Application/src/main/resources/search_icon.png");
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        searchButton.setGraphic(imageView);

        // Sign In Button
        signInButton = new Button("Sign In");
        ImageView signInButtonIcon = new ImageView("file:/Users/ajay/IdeaProjects/E-Commerce-Application/src/main/resources/signIn_icon.png");
        signInButtonIcon.setFitHeight(20);
        signInButtonIcon.setFitWidth(20);
        signInButton.setGraphic(signInButtonIcon);

        // Cart Button
        cartButton = new Button("Cart");
        ImageView cartButtonIcon = new ImageView("file:/Users/ajay/IdeaProjects/E-Commerce-Application/src/main/resources/cart_icon.png");
        cartButtonIcon.setFitHeight(20);
        cartButtonIcon.setFitWidth(20);
        cartButton.setGraphic(cartButtonIcon);

        // Account Button
        accountButton = new Button("Account");
        accountButton.setGraphic(signInButtonIcon);

        // Sign Out Button
        signOutButton = new Button("Sign Out");
        ImageView signOutButtonIcon = new ImageView("file:/Users/ajay/IdeaProjects/E-Commerce-Application/src/main/resources/signout_icon.png");
        signOutButtonIcon.setFitHeight(20);
        signOutButtonIcon.setFitWidth(20);
        signOutButton.setGraphic(signOutButtonIcon);

        // Orders Button
        ordersButton = new Button("Orders");
        ImageView ordersButtonIcon = new ImageView("file:/Users/ajay/IdeaProjects/E-Commerce-Application/src/main/resources/orders_icon.png");
        ordersButtonIcon.setFitHeight(20);
        ordersButtonIcon.setFitWidth(20);
        ordersButton.setGraphic(ordersButtonIcon);

        //more buttons
        Button updateButton = new Button("Update");
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");
        Button cancelButton = new Button("Cancel Order");



        headerBar = new HBox(10);
        headerBar.setPadding(new Insets(5));
        headerBar.setAlignment(Pos.CENTER);
        headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,ordersButton,cartButton);

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                headerBar.getChildren().clear();
                if(loggedInCustomer==null){
                    headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,ordersButton,cartButton);
                }else{
                    headerBar.getChildren().addAll(homeButton,searchBar,searchButton,welcomeLabel,accountButton,ordersButton,cartButton);
                }
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.getChildren().clear();
                footerBar.getChildren().addAll(addToCart, buyNowButton);
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchProduct = "SELECT id, price, name FROM product WHERE NAME LIKE '%"+searchBar.getText()+"%';";
                body.getChildren().clear();
                VBox searchedProduct = productList.getSearchedProduct(searchProduct);
                body.getChildren().add(searchedProduct);
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
            }
        });
        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                headerBar.getChildren().clear();
                body.getChildren().clear();
                footerBar.getChildren().clear();
                headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,ordersButton,cartButton);
                loggedInCustomer=null;
                body.getChildren().add(productPage);
                footerBar.getChildren().addAll(addToCart,buyNowButton);
            }
        });

        ordersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedInCustomer==null){
                    showDialogWarning("Please login first to view order");
                    return;
                }
                body.getChildren().clear();
                VBox ordersPage = orderList.getAllOrders(loggedInCustomer.getId());
                body.getChildren().add(ordersPage);
                footerBar.getChildren().clear();
                footerBar.getChildren().addAll(cancelButton,backButton);
            }
        });

        accountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                headerBar.getChildren().clear();
                body.getChildren().clear();
                footerBar.getChildren().clear();
                GridPane accountPage = loggedInCustomer.customerDetails();
                body.getChildren().add(accountPage);
                headerBar.getChildren().addAll(homeButton,searchBar,searchButton,welcomeLabel,signOutButton,ordersButton,cartButton);
                footerBar.getChildren().addAll(updateButton,backButton);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemsInCart.size()==0){
                    showDialogWarning("Your Cart is Empty !!!");
                    return;
                }
                body.getChildren().clear();
                VBox cartPage = productList.getProductInCart(itemsInCart);
                Text total = new Text("Subtotal:  ₹"+productList.getSubtotal(itemsInCart)+"           ");
                body.getChildren().add(cartPage);
                footerBar.getChildren().clear();
                footerBar.getChildren().addAll(total,placeOrderButton,backButton);
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                headerBar.getChildren().clear();
                if(loggedInCustomer==null){
                    headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,ordersButton,cartButton);
                }else{
                    headerBar.getChildren().addAll(homeButton,searchBar,searchButton,welcomeLabel,accountButton,ordersButton,cancelButton);
                }
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.getChildren().clear();
                footerBar.getChildren().addAll(addToCart, buyNowButton);
            }
        });
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loggedInCustomer.editDetails();
                footerBar.getChildren().clear();
                footerBar.getChildren().addAll(saveButton,backButton);
            }
        });
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loggedInCustomer.updateCustomerDetails();
                loggedInCustomer.getUpdatedCustomerDetails();
                body.getChildren().clear();
                footerBar.getChildren().clear();
                GridPane accountPage = loggedInCustomer.customerDetails();
                body.getChildren().add(accountPage);
                footerBar.getChildren().addAll(updateButton,backButton);
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Order order = orderList.getSelectedOrder();
                if(order==null){
                    showDialogWarning("Please select a order to cancel");
                    return;
                }
                order.cancelOrder();
                showDialogInformation("Order Cancelled Successfully");
                body.getChildren().clear();
                VBox ordersPage = orderList.getAllOrders(loggedInCustomer.getId());
                body.getChildren().add(ordersPage);
                footerBar.getChildren().clear();
                footerBar.getChildren().addAll(cancelButton,backButton);
            }
        });
    }


    private void createFooterBar(){
        buyNowButton = new Button("Buy Now");
        addToCart = new Button("Add to Cart");
        placeOrderButton = new Button("Place Order");

        footerBar = new HBox(10);
        footerBar.setPadding(new Insets(5));
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(addToCart,buyNowButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product==null){
                    showDialogWarning("Please select a product to order");
                    return;
                }
                if(loggedInCustomer==null){
                    showDialogWarning("Please login first to place order");
                    return;
                }
                boolean status = Order.placeOrder(loggedInCustomer,product);
                if(status){
                    showDialogInformation("Order Placed Successfully...");
                }else{
                    showDialogWarning("Order Failed!!!");
                }
            }
        });
        addToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product==null){
                    showDialogWarning("Please select a product to order");
                    return;
                }
                if(product.getQuantity()==0){
                    showDialogWarning("Product Out of Stock");
                    return;
                }
                showDialogInformation("Item Added to Cart Successfully");
                itemsInCart.add(product);
            }
        });


        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(itemsInCart.size()==0){
                    showDialogWarning("Your Cart is Empty");
                    return;
                }
                if(loggedInCustomer==null){
                    showDialogWarning("Please login first to place order");
                    return;
                }
                int count = Order.placeMultipleOrder(loggedInCustomer,itemsInCart);
                if(count!=0){
                    showDialogInformation("Order for "+count+" Items Placed Successfully...");
                }else{
                    showDialogWarning("Order Failed!!!");
                }
            }
        });


    }


    private void showDialogWarning(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showDialogInformation(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
