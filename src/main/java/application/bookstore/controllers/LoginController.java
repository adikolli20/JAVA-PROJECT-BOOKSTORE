package application.bookstore.controllers;

import application.bookstore.models.User;
import application.bookstore.views.LoginView;
import application.bookstore.views.MainView;
import application.bookstore.views.View;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {
    private final Stage primaryStage;
    private final View nextView;

    public User getCurrentUser() {
        return currentUser;
    }



    private User currentUser;
    public LoginController(LoginView view, View nextView, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.nextView = nextView;
        addListener(view);
    }

    private void addListener(LoginView view) {
        view.getLoginBtn().setOnAction(e -> {
            String password = view.getPasswordField().getText();
            String username = view.getUsernameField().getText();
            User potentialUser = new User(username, password);
            if ((currentUser = User.getIfExists(potentialUser)) != null) {
                nextView.setCurrentUser(currentUser);
                primaryStage.setScene(new Scene(nextView.getView(), 1080, 720));
                primaryStage.centerOnScreen();
            }
            else
                view.getErrorLabel().setText("Wrong username or password");
        });
        MainView.logout.setOnAction(event -> {
            LoginView loginView = new LoginView();
            LoginController controller = new LoginController(loginView, new MainView(), primaryStage);
            Scene scene = new Scene(loginView.getView(), 1080, 720);
            scene.setFill(Color.LIGHTCYAN);
            primaryStage.setTitle("Bookstore");
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        });
    }
}
