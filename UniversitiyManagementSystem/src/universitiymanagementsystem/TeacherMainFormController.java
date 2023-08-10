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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS 10
 */
public class TeacherMainFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label greet_name;

    @FXML
    private Label teacher_id;

    @FXML
    private Button addStudent_btn;

    @FXML
    private Button subjectHandle_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Label current_form;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<String> addStudents_course;

    @FXML
    private ComboBox<String> addStudents_year;

    @FXML
    private Label addStudents_label_fullName;

    @FXML
    private Label addStudents_label_gender;

    @FXML
    private ComboBox<String> addStudents_studentID;

    @FXML
    private Label addStudents_label_semester;

    @FXML
    private Label addStudents_label_year;

    @FXML
    private Label addStudents_label_course;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private Button addStudents_removeBtn;

    @FXML
    private TableView<DataStudentHandle> addStudents_tableView;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_studentID;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_name;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_gender;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_course;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_year;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_semester;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_date;

    @FXML
    private AnchorPane subjectHandle_form;

    @FXML
    private ComboBox<String> subjecthandle_subject;

    @FXML
    private Button subjecthandle_addBtn;

    @FXML
    private Button subjecthandle_clearBtn;

    @FXML
    private Button subjecthandle_removeBtn;

    @FXML
    private ComboBox<String> subjecthandle_code;

    @FXML
    private ComboBox<String> subjecthandle_status;

    @FXML
    private TableView<DataSubjectHandle> subjecthandle_tableView;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_subjectCode;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_subjectName;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_dateInsert;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_status;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    public void addStudentsAddBtn() {

        if (addStudents_course.getSelectionModel().getSelectedItem().isEmpty()
                || addStudents_year.getSelectionModel().getSelectedItem().isEmpty()
                || addStudents_studentID.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String insertData = "INSERT INTO teacher_student "
                    + "(teacher_id, stud_studentID, stud_name, stud_gender"
                    + ", stud_year, stud_course, stud_semester"
                    + ", date_insert, status) VALUES(?,?,?,?,?,?,?,?,?)";
            connect = Database.connectDB();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, teacher_id.getText());
                prepare.setString(2, addStudents_studentID.getSelectionModel().getSelectedItem());
                prepare.setString(3, addStudents_label_fullName.getText());
                prepare.setString(4, addStudents_label_gender.getText());
                prepare.setString(5, addStudents_year.getSelectionModel().getSelectedItem());
                prepare.setString(6, addStudents_course.getSelectionModel().getSelectedItem());
                prepare.setString(7, addStudents_label_semester.getText());
                prepare.setString(8, String.valueOf(sqlDate));
                prepare.setString(9, "Active");

                prepare.executeUpdate();

                addStudentDisplayData();

                alert.successMessage("Added successfully!");

                addStudentClearBtn();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addStudentsRemoveBtn() {

        if (addStudents_course.getSelectionModel().getSelectedItem() == null
                || addStudents_year.getSelectionModel().getSelectedItem()== null
                || addStudents_studentID.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            if (alert.confirmMessage("Are you sure you want to delete Student ID:"
                    + addStudents_studentID.getSelectionModel().getSelectedItem())) {
                String insertData = "UPDATE teacher_student SET date_delete = ?, status = ? "
                        + "WHERE stud_studentID = ?";
                connect = Database.connectDB();

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                try {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, "Inactive");
                    prepare.setString(3, addStudents_studentID.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    addStudentDisplayData();

                    alert.successMessage("Deleted successfully!");

                    addStudentClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled.");
            }

        }
    }

    public void addStudentClearBtn() {
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_studentID.getSelectionModel().clearSelection();

        addStudents_label_fullName.setText("----------");
        addStudents_label_gender.setText("----------");
        addStudents_label_semester.setText("----------");
        addStudents_label_year.setText("----------");
        addStudents_label_course.setText("----------");
    }

    public ObservableList<DataStudentHandle> addStudentListData() {

        ObservableList<DataStudentHandle> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Active'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            DataStudentHandle dshData;

            while (result.next()) {
//                DataStudentHandle(String studentID, String name, String gender,
//             String course, String year, String semester, Date dateInsert,
//             Date dateDelete, String status)
                dshData = new DataStudentHandle(result.getString("stud_studentID"),
                        result.getString("stud_name"),
                        result.getString("stud_gender"),
                        result.getString("stud_course"),
                        result.getString("stud_year"),
                        result.getString("stud_semester"),
                        result.getDate("date_insert"),
                        result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(dshData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<DataStudentHandle> addStudentGetData;

    public void addStudentDisplayData() {
        addStudentGetData = addStudentListData();

        addStudents_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        addStudents_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        addStudents_col_date.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));

        addStudents_tableView.setItems(addStudentGetData);

    }

    public void addStudentSelectitem() {
        DataStudentHandle dshData = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_course.getSelectionModel().select(dshData.getCourse());
        addStudents_year.getSelectionModel().select(dshData.getYear());
        addStudents_studentID.getSelectionModel().select(dshData.getStudentID());

        addStudents_label_fullName.setText(dshData.getName());
        addStudents_label_gender.setText(dshData.getGender());
        addStudents_label_semester.setText(dshData.getSemester());
        addStudents_label_year.setText(dshData.getYear());
        addStudents_label_course.setText(dshData.getCourse());

    }

    public void addStudentCourseList() {

        String sql = "SELECT * FROM course WHERE date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("course"));
            }

            addStudents_course.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsYearList() {

        List<String> listY = new ArrayList<>();

        for (String data : ListData.year) {
            listY.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listY);
        addStudents_year.setItems(listData);

        addStudentsStudentNumber();

    }

    public void addStudentsStudentNumber() {

        String sql = "SELECT * FROM student WHERE course = '"
                + addStudents_course.getSelectionModel().getSelectedItem() + "' AND year = '"
                + addStudents_year.getSelectionModel().getSelectedItem() + "' AND date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("student_id"));
            }

            addStudents_studentID.setItems(listData);

            addStudentsDisplayFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsDisplayFields() {

        String sql = "SELECT * FROM student WHERE student_id = '"
                + addStudents_studentID.getSelectionModel().getSelectedItem() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                addStudents_label_fullName.setText(result.getString("full_name"));
                addStudents_label_gender.setText(result.getString("gender"));
                addStudents_label_semester.setText(result.getString("semester"));
                addStudents_label_year.setText(result.getString("year"));
                addStudents_label_course.setText(result.getString("course"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void subjectHandleAddBtn() {

        if (subjecthandle_code.getSelectionModel().getSelectedItem().isEmpty()
                || subjecthandle_subject.getSelectionModel().getSelectedItem().isEmpty()
                || subjecthandle_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            String insertData = "INSERT INTO teacher_handle (subject_code, subject, date, status) "
                    + "VALUES(?,?,?,?)";
            connect = Database.connectDB();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, subjecthandle_code.getSelectionModel().getSelectedItem());
                prepare.setString(2, subjecthandle_subject.getSelectionModel().getSelectedItem());
                prepare.setString(3, String.valueOf(sqlDate));
                prepare.setString(4, subjecthandle_status.getSelectionModel().getSelectedItem());

                prepare.executeUpdate();

                subjectHandleDisplayData();

                alert.successMessage("Added successfully!");

                subjectHandleClearBtn();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void subjectHandleRemoveBtn() {

        if (subjecthandle_code.getSelectionModel().getSelectedItem().isEmpty()
                || subjecthandle_subject.getSelectionModel().getSelectedItem().isEmpty()
                || subjecthandle_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            if (alert.confirmMessage("Are you sure you want to Remove Subject Code: "
                    + subjecthandle_code.getSelectionModel().getSelectedItem() + "?")) {
                String deleteData = "DELETE FROM teacher_handle WHERE subject_code = '"
                        + subjecthandle_code.getSelectionModel().getSelectedItem() + "'";
                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(deleteData);

                    prepare.executeUpdate();

                    subjectHandleDisplayData();

                    alert.successMessage("Removed successfully!");

                    subjectHandleClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled");
            }

        }
    }

    public void subjectHandleClearBtn() {
        subjecthandle_code.getSelectionModel().clearSelection();
        subjecthandle_subject.getSelectionModel().clearSelection();
        subjecthandle_status.getSelectionModel().clearSelection();
    }

    public ObservableList<DataSubjectHandle> subjectHandleGetData() {

        ObservableList<DataSubjectHandle> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM teacher_handle";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            DataSubjectHandle dshData;

            while (result.next()) {
//                DataSubjectHandle(Integer id, String subjectCode,
//            String subject, Date insertData, String status)
                dshData = new DataSubjectHandle(result.getInt("id"),
                        result.getString("subject_code"),
                        result.getString("subject"),
                        result.getDate("date"), result.getString("status"));

                listData.add(dshData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<DataSubjectHandle> subjectHandleListData;

    public void subjectHandleDisplayData() {
        subjectHandleListData = subjectHandleGetData();

        subjecthandle_col_subjectCode.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        subjecthandle_col_subjectName.setCellValueFactory(new PropertyValueFactory<>("subject"));
        subjecthandle_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("insertData"));
        subjecthandle_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        subjecthandle_tableView.setItems(subjectHandleListData);
    }

    public void subjectHandleSelectItem() {

        DataSubjectHandle dshData = subjecthandle_tableView.getSelectionModel().getSelectedItem();
        int num = subjecthandle_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        subjecthandle_code.getSelectionModel().select(dshData.getSubjectCode());
        subjecthandle_subject.getSelectionModel().select(dshData.getSubject());
        subjecthandle_status.getSelectionModel().select(dshData.getStatus());

    }

    public void subjectHandleSubjectCodeList() {
        String sql = "SELECT * FROM subject WHERE date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("subject_code"));
            }

            subjecthandle_code.setItems(listData);

            subjectHandleSubjectList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subjectHandleSubjectList() {
        String sql = "SELECT * FROM subject WHERE date_delete IS NULL AND "
                + "subject_code = '"
                + subjecthandle_code.getSelectionModel().getSelectedItem() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("subject"));
            }

            subjecthandle_subject.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subjectHandleStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : ListData.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        subjecthandle_status.setItems(listData);

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == addStudent_btn) {
            addStudents_form.setVisible(true);
            subjectHandle_form.setVisible(false);

            addStudentCourseList();
            addStudentsYearList();
            addStudentDisplayData();

            current_form.setText("Add Students form");
        } else if (event.getSource() == subjectHandle_btn) {
            addStudents_form.setVisible(false);
            subjectHandle_form.setVisible(true);

            subjectHandleSubjectCodeList();
            subjectHandleStatusList();
            subjectHandleDisplayData();

            current_form.setText("Subject Handles form");
        }

    }

    public void logoutBtn() {
        try {
            if (alert.confirmMessage("Are you sure you want to logout?")) {
                // TO SHOW THE LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayTeacher() {

        String sql = "SELECT * FROM users WHERE username = '"
                + ListData.teacher_username + "'";

        connect = Database.connectDB();

        String temp_teacherName = "";
        String temp_teacherID = "";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_teacherID = result.getString("teacher_id");
            }

            String selectData = "SELECT * FROM teacher WHERE teacher_id = '"
                    + temp_teacherID + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(selectData);

            if (result.next()) {
                temp_teacherName = result.getString("full_name");
            }

            greet_name.setText("Welcome, " + temp_teacherName);
            teacher_id.setText(temp_teacherID);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayTeacher();

        addStudentCourseList();
        addStudentsYearList();
        addStudentDisplayData();

    }

}
