package application.bookstore.views;

import application.bookstore.ui.CreateButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LoginView extends View {
    private final BorderPane borderPane = new BorderPane();

    private final TextField usernameField = new TextField();

    private final PasswordField passwordField = new PasswordField();
    private final Button loginBtn = new Button("Login");
    private final Label errorLabel = new Label("");
    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }
    public Label getErrorLabel() {
        return errorLabel;
    }
    public LoginView() {
        setView();
    }

    private void setView() {
        Label usernameLabel = new Label("Username", usernameField);
        Label passwordLabel = new Label("Password", passwordField);
        VBox vBox = new VBox();
        Label loginLabel = new Label("LOGIN");
        loginLabel.setTextFill(Color.DARKRED);
        loginLabel.setFont(Font.font("Algerian",25));
        usernameLabel.setTextFill(Color.DARKRED);
        usernameLabel.setFont(Font.font("Algerian",25));
        passwordLabel.setTextFill(Color.DARKRED);
        passwordLabel.setFont(Font.font("Algerian",25));

        vBox.getChildren().addAll(loginLabel, usernameLabel, passwordLabel, loginBtn, errorLabel);

        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);

        Image image = new Image(String.valueOf(CreateButton.class.getResource("/images/pngwing.png")));
        BackgroundImage bImg = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(bImg);

        borderPane.setBackground(bGround);

        borderPane.setCenter(vBox);
    }

    @Override
    public Parent getView() {
        return borderPane;
    }
}
