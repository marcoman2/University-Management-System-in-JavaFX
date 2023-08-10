/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitiymanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author WINDOWS 10
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private Button login_btn;

    @FXML
    private ComboBox<String> login_role;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private TextField admin_username;

    @FXML
    private PasswordField admin_password;

    @FXML
    private Button admin_signupBtn;

    @FXML
    private Hyperlink admin_signIn;

    @FXML
    private PasswordField admin_cPassword;

    @FXML
    private AnchorPane student_form;

    @FXML
    private TextField student_email;

    @FXML
    private TextField student_username;

    @FXML
    private PasswordField student_password;

    @FXML
    private Button student_signupBtn;

    @FXML
    private Hyperlink student_signIn;

    @FXML
    private PasswordField student_cPassword;

    @FXML
    private AnchorPane teacher_form;

    @FXML
    private TextField teacher_email;

    @FXML
    private TextField teacher_username;

    @FXML
    private PasswordField teacher_password;

    @FXML
    private Button teacher_signupBtn;

    @FXML
    private Hyperlink teacher_signIn;

    @FXML
    private PasswordField teacher_cPassword;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    public void loginAccount() {

        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String selectData = "SELECT * FROM users WHERE username = ? AND password = ?";

            connect = Database.connectDB();

            String role = "";
            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    role = result.getString("role");

                    System.out.println(role);

                    Thread.sleep(1000);

                    if (role.equals("Admin")) {
                        ListData.admin_username = login_username.getText();
                        // LINK YOUR MAIN FORM FOR ADMIN
                        Parent root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("University Management System | Admin Portal");
                        stage.setScene(new Scene(root));

                        stage.show();

                        // TO HIDE YOUR LOGIN FORM
                        login_btn.getScene().getWindow().hide();

                    } else if (role.equals("Student")) {
                        String tempStudentID = result.getString("student_id");

                        String checkData = "SELECT * FROM student WHERE student_id = '"
                                + tempStudentID + "'";

                        statement = connect.createStatement();
                        result = statement.executeQuery(checkData);

                        if (result.next()) {
                            if (result.getString("status").equals("Approval")) {
                                alert.errorMessage("Need the approval of the Admin!");
                            } else {
                                // TO GET THE USERNAME
                                ListData.student_username = login_username.getText();

                                Parent root = FXMLLoader.load(getClass().getResource("StudentMainForm.fxml"));
                                Stage stage = new Stage();

                                stage.setTitle("University Management System | Student Portal");
                                stage.setScene(new Scene(root));
                                stage.show();

                                // TO HIDE YOUR LOGIN FORM
                                login_btn.getScene().getWindow().hide();
                            }
                        }
                    } else if (role.equals("Teacher")) {

                        String tempTeacherID = result.getString("teacher_id");

                        String checkData = "SELECT * FROM teacher WHERE teacher_id = '"
                                + tempTeacherID + "'";

                        statement = connect.createStatement();
                        result = statement.executeQuery(checkData);

                        if (result.next()) {
                            if (result.getString("status").equals("Approval")) {
                                alert.errorMessage("Need the approval of the Admin!");
                            } else {
                                // TO GET THE USERNAME
                                ListData.teacher_username = login_username.getText();

                                Parent root = FXMLLoader.load(getClass().getResource("TeacherMainForm.fxml"));
                                Stage stage = new Stage();

                                stage.setTitle("University Management System | Teacher Portal");
                                stage.setScene(new Scene(root));
                                stage.show();

                                // TO HIDE YOUR LOGIN FORM
                                login_btn.getScene().getWindow().hide();
                            }
                        }

                    }
                } else {
                    alert.errorMessage("Incorrect Username/Password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void registerAdmin() {

        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()
                || admin_cPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '"
                    + admin_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.errorMessage(admin_username.getText() + " is already exist");
                } else if (!admin_password.getText().equals(admin_cPassword.getText())) {
                    alert.errorMessage("Password does not match.");
                } else if (admin_password.getText().length() < 8) {
                    alert.errorMessage("Invalid password, at least 8 characters needed");
                } else {
                    String insertData = "INSERT INTO users (username, password, role, date) "
                            + "VALUES(?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, admin_username.getText());
                    prepare.setString(2, admin_password.getText());
                    prepare.setString(3, "Admin");
                    prepare.setString(4, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered successfully!");

                    login_form.setVisible(true);
                    admin_form.setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void registerStudent() {

        if (student_email.getText().isEmpty() || student_username.getText().isEmpty()
                || student_password.getText().isEmpty()
                || student_cPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '"
                    + admin_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.errorMessage(student_username.getText() + " is already exist");
                } else {
                    if (!student_password.getText().equals(student_cPassword.getText())) {
                        alert.errorMessage("Password does not match.");
                    } else if (student_password.getText().length() < 8) {
                        alert.errorMessage("Invalid password, at least 8 characters needed");
                    } else {
                        String insertData = "INSERT INTO users (email, username, password, role, student_id, date) "
                                + "VALUES(?,?,?,?,?,?)";

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        SimpleDateFormat format = new SimpleDateFormat("yyyy");
                        String getYear = format.format(date);
                        String studentNum = getYear + "0000";
                        int sNum = Integer.parseInt(studentNum) + studentIDGenerator();

                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, student_email.getText());
                        prepare.setString(2, student_username.getText());
                        prepare.setString(3, student_password.getText());
                        prepare.setString(4, "Student");
                        prepare.setString(5, String.valueOf(sNum));
                        prepare.setString(6, String.valueOf(sqlDate));

                        prepare.executeUpdate();

                        String insertStudent = "INSERT INTO student (student_id, date_insert, status) "
                                + "VALUES(?,?,?)";

                        prepare = connect.prepareStatement(insertStudent);
                        prepare.setString(1, String.valueOf(sNum));
                        prepare.setString(2, String.valueOf(sqlDate));
                        prepare.setString(3, "Approval");

                        prepare.executeUpdate();

                        alert.successMessage("Registered successfully!");

                        login_form.setVisible(true);
                        student_form.setVisible(false);
                    }
                }

                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private int studentID;

    public int studentIDGenerator() {
        String selectData = "SELECT MAX(id) FROM student";

        connect = Database.connectDB();

        int temp_studentID = 0;

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(selectData);

            if (result.next()) {
                temp_studentID = result.getInt("MAX(id)");
            }

            if (temp_studentID == 0) {
                studentID = 1;
            } else {
                studentID = temp_studentID + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentID;
    }

    public void registerTeacher() {

        if (teacher_email.getText().isEmpty() || teacher_username.getText().isEmpty()
                || teacher_password.getText().isEmpty()
                || teacher_cPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '"
                    + teacher_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.errorMessage(teacher_username.getText() + " is already exist");
                } else if (!teacher_password.getText().equals(teacher_cPassword.getText())) {
                    alert.errorMessage("Password does not match.");
                } else if (teacher_password.getText().length() < 8) {
                    alert.errorMessage("Invalid password, at least 8 characters needed");
                } else {

                    String temp_teacherID = "TID-" + String.valueOf(teacherIDGenerator());

                    String insertData = "INSERT INTO users "
                            + "(email, username, password, role, teacher_id, date) "
                            + "VALUES(?,?,?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, teacher_email.getText());
                    prepare.setString(2, teacher_username.getText());
                    prepare.setString(3, teacher_password.getText());
                    prepare.setString(4, "Teacher");
                    prepare.setString(5, temp_teacherID);
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    String insertStudent = "INSERT INTO teacher "
                            + "(teacher_id, date_insert, status) "
                            + "VALUES(?,?,?)";

                    prepare = connect.prepareStatement(insertStudent);
                    prepare.setString(1, temp_teacherID);
                    prepare.setString(2, String.valueOf(sqlDate));
                    prepare.setString(3, "Approval");

                    prepare.executeUpdate();

                    alert.successMessage("Registered successfully!");

                    login_form.setVisible(true);
                    teacher_form.setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int teacherID = 0;

    public int teacherIDGenerator() {

        String sql = "SELECT MAX(id) FROM teacher";

        connect = Database.connectDB();
        int temp_teacherID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_teacherID = result.getInt("MAX(id)");
            }

            if (temp_teacherID == 0) {
                teacherID = 1;
            } else {
                teacherID = temp_teacherID + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherID;
    }

    public void roleList() {

        List<String> listR = new ArrayList<>();

        for (String data : ListData.role) {
            listR.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listR);
        login_role.setItems(listData);

    }

    public void signInForm() {
        login_form.setVisible(true);
        admin_form.setVisible(false);
        student_form.setVisible(false);
        teacher_form.setVisible(false);
    }

    public void switchForm(ActionEvent event) {

        switch (login_role.getSelectionModel().getSelectedItem()) {
            case "Admin":
                login_form.setVisible(false);
                admin_form.setVisible(true);
                student_form.setVisible(false);
                teacher_form.setVisible(false);
                break;
            case "Student":
                login_form.setVisible(false);
                admin_form.setVisible(false);
                student_form.setVisible(true);
                teacher_form.setVisible(false);
                break;
            case "Teacher":
                login_form.setVisible(false);
                admin_form.setVisible(false);
                student_form.setVisible(false);
                teacher_form.setVisible(true);
                break;
            default:
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleList();
    }

}
