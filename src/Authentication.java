import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
/**
 * ' import javafx.scene.control.*; ' to avoid single class imports, this will import everything
 *
 */

public class Authentication extends Application{

    private int attempt = 0;/** put in 0 for the initial attempt in CLASS to use 'attempt++' later on to count amount of attempts */

    public enum AccountType{
        SelectAccount, King, Noble, Knight, Peasant
    }

    public static void main(String[]args){
        launch(args);//launch will call start
    }


    @Override
    public void start(Stage primaryStage)   {


        /** This should take the place of a DATABASE
         *  Set correctUsername to 'redskins8901' and correctPassword to 'hailtotheredskins'
         *  And the correctAcType of the username and password to Administrator
         *  Set the limit to 5 attempts in case of fraud or mis typed words/letters
         */
        String correctUsername = "redskins8901";
        String correctPassword = "hailtotheredskins";
        AccountType correctAcType = AccountType.Knight;
        final int LIMIT = 5;


        /**
         *  TextField will allow the user to type in a small quantity of text, used for 'usernameTxt'.
         *  PasswordField will hide characters, used for passwordField.
         *  popup list comes out of combo box so the user can choose between account types, used for AccountType.
         */
        TextField usernameTxt = new TextField();
        usernameTxt.setText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setText("password");
        ComboBox <AccountType> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(AccountType.SelectAccount, AccountType.King, AccountType.Noble, AccountType.Knight, AccountType.Peasant);
        comboBox.setValue(AccountType.SelectAccount);
        comboBox.setVisible(false);
        Button submitBtn = new Button("Log in");

        /** It has to be made sure that the correct Username, password, and the right account type is entered using if-else.
         *  ' submitBtn.setOnAction(e-> ' set what will happen when user press the button 'submitBtn'.
         *  Declaring alert outside 'if' will help avoid repeating declaration.
         *  If username and password are entered correctly with the account, it is successful.
         *  Else if the attempt are still under or equal to 5, it will ask again until it reach the LIMIT.
         *  Else if the attempt are over three(LIMIT), the account is locked.
         */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        submitBtn.setOnAction(e->{

            if(usernameTxt.getText().equals(correctUsername) && passwordField.getText().equals(correctPassword) && attempt<=LIMIT){
                comboBox.setVisible(true);

            } else if(attempt < LIMIT) {
                alert.setContentText("Your username and/or password is incorrect!");
                alert.showAndWait();
                attempt++;
            } else if( attempt >= LIMIT) {
                alert.setContentText("Please contact your network administrator to unlock your account!");
                alert.showAndWait();
            }
        });/** submitBtn.setOnAction(e->{...}' set what will happen when user entered right username and password */


        comboBox.setOnAction(e->{
            if(comboBox.getValue().equals(correctAcType)){
                alert.setContentText("Welcome " + correctUsername + "!");
                alert.showAndWait();
            } else  {
                alert.setContentText("Wrong Account type! Please select correct account type.");
                alert.showAndWait();
            }

        });/** 'comboBox.setOnAction(e->{...}' set what will happen when user chose the right account type */

        VBox pane = new VBox();
        pane.getChildren().addAll(usernameTxt, passwordField, comboBox, submitBtn);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Authentication");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


}
