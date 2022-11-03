package application.bookstore.views;

import application.bookstore.Main;
import application.bookstore.controllers.AuthorController;
import application.bookstore.controllers.BookController;
import application.bookstore.controllers.LoginController;
import application.bookstore.models.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainView extends View {

    @Override
    public Parent getView() {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        //Tab authorTab = new Tab("Authors");
        //authorTab.setContent(new AuthorView().getView());
        //Tab bookTab = new Tab("Books");
        //bookTab.setContent(new BookView().getView());
        Tab adminTab= new Tab("Admin");
       // Tab logout= new Tab("Log Out");
        //logout.setContent(new LoginView().getView());
        adminTab.setContent(getAdminMenu());
        Tab librarianTab = new Tab("Librarian");
        librarianTab.setContent(getLibrarianMenu());
        Tab managerTab = new Tab("Manager");
        managerTab.setContent(new StackPane(getManagerMenu()));

        Role currentRole = (getCurrentUser() != null ? getCurrentUser().getRole() : null);
        if (currentRole != null) {
            if (currentRole == Role.ADMIN)
                tabPane.getTabs().addAll(adminTab);
            if (currentRole == Role.MANAGER || currentRole == Role.ADMIN)
                tabPane.getTabs().add(managerTab);
            tabPane.getTabs().addAll(librarianTab);
        }
        borderPane.setTop(tabPane);
        borderPane.setCenter(BookOrder.newOrder());
        borderPane.setBottom(new StackPane(new Text(getCurrentUser().getUsername() + ", welcome to our bookstore")));
        return borderPane;
    }
     public static MenuItem logout = new MenuItem("Logout");

    public  MenuItem getLogout() {
        return logout;
    }

    public StackPane getLibrarianMenu() {

        Menu librarian = new Menu("Librarian Menu");

        MenuItem changePass = new MenuItem("Change Password");

        MenuItem viewAllBooks = new MenuItem("View All Books");
        MenuItem printAuthors = new MenuItem("View all authors");



        librarian.getItems().addAll(viewAllBooks,printAuthors,changePass,logout);
        viewAllBooks.setOnAction(event -> {
            BookView b = new BookView(false);
            b.getTableView().setEditable(false);

            b.getView();
        });

        printAuthors.setOnAction(event -> {
            new AuthorView().getAuthorView();
        });
        changePass.setOnAction(event -> {
            UserView.ChangePassword();

        });

        MenuBar menubar= new MenuBar(librarian);
        StackPane stackpane= new StackPane();
        stackpane.getChildren().add(menubar);
        return  stackpane;
    }
    public StackPane getManagerMenu(){

        Menu manager = new Menu("Manager Menu");
        MenuItem addBook = new MenuItem("Manage books");
        MenuItem changePass = new MenuItem("Change Password");
        addBook.setOnAction(event -> {
            new BookView(true).getView();
        });

        MenuItem pieChart = new MenuItem("Pie Chart Based on Books");
        MenuItem pieChartUser = new MenuItem("Pie Chart Based on Users");
        changePass.setOnAction(event -> {
            UserView.ChangePassword();
        });
        pieChart.setOnAction(event -> {
            BookOrder.piechartOfBooks();
        });
        pieChartUser.setOnAction(event -> {
            BookOrder.piechartOfUsers();
        });


        MenuItem addAnewAuthor = new MenuItem("Manage authors");
        addAnewAuthor.setOnAction(event -> {
            new AuthorView().getView();
        });
        addBook.setOnAction(event -> {
            new BookView(true).getView();
        });

        manager.getItems().addAll(addBook,addAnewAuthor,changePass,pieChart,pieChartUser);
        MenuBar menubar= new MenuBar(manager);
        StackPane stackpane= new StackPane();
        stackpane.getChildren().add(menubar);
        return  stackpane;
    }

    public StackPane getAdminMenu(){

        Menu manager = new Menu("Admin Menu");
        MenuItem ManageUser = new MenuItem("Manage a user");
        ManageUser.setOnAction(event -> {
            new UserView().addUser();
        });

        MenuItem showAllWorkers = new MenuItem("Show all employees");

        showAllWorkers.setOnAction(event -> {
            new UserView().PrintUsers();
        });
        manager.getItems().addAll(ManageUser);
        MenuBar menubar= new MenuBar(manager);
        StackPane stackpane= new StackPane();
        stackpane.getChildren().add(menubar);
        return  stackpane;
    }

}
