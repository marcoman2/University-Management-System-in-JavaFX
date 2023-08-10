/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitiymanagementsystem;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.temporal.ChronoUnit;
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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS 10
 */
public class AdminMainFormController implements Initializable {

    @FXML
    private Label greet_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button addStudent_btn;

    @FXML
    private Button addTeacher_btn;

    @FXML
    private Button addCourse_btn;

    @FXML
    private Button addSubject_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Button salary_btn;

    @FXML
    private AnchorPane addStudent_form;

    @FXML
    private AnchorPane addTeacher_form;

    @FXML
    private TableView<StudentData> addStudent_tableView;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_studentNumber;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_name;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_year;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_course;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_section;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_pay;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_statusPayment;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_dateInsert;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_status;

    @FXML
    private Button addStudent_deleteBtn;

    @FXML
    private Button addStudent_updateBtn;

    @FXML
    private Button addStudent_addBtn;

    @FXML
    private TableView<TeacherData> addTeacher_tableView;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_teacherID;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_name;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_gender;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_yearExperience;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_experience;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_department;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_salary;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_dateInsert;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_status;

    @FXML
    private TextField addTeacher_teacherID;

    @FXML
    private TextField addTeacher_fullName;

    @FXML
    private ComboBox<String> addTeacher_gender;

    @FXML
    private ComboBox<String> addTeacher_experienceYear;

    @FXML
    private TextField addTeacher_experience;

    @FXML
    private ComboBox<String> addTeacher_department;

    @FXML
    private TextField addTeacher_salary;

    @FXML
    private ComboBox<String> addTeacher_status;

    @FXML
    private ImageView addTeacher_imageView;

    @FXML
    private Button addTeacher_importBtn;

    @FXML
    private Button addTeacher_addBtn;

    @FXML
    private Button addTeacher_updateBtn;

    @FXML
    private Button addTeacher_clearBtn;

    @FXML
    private Button addTeacher_deleteBtn;

    @FXML
    private TextField addCourse_course;

    @FXML
    private TextField addCourse_department;

    @FXML
    private TextField addCourse_price;

    @FXML
    private ComboBox<String> addCourse_status;

    @FXML
    private Button addCourse_addBtn;

    @FXML
    private Button addCourse_updateBtn;

    @FXML
    private Button addCourse_clearBtn;

    @FXML
    private Button addCourse_deleteBtn;

    @FXML
    private TableView<CourseData> addCourse_tableView;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_course;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_price;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_department;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_dateInsert;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_status;

    @FXML
    private AnchorPane addCourse_form;

    @FXML
    private AnchorPane addSubject_form;

    @FXML
    private TextField addSubject_code;

    @FXML
    private TextField addSubject_subject;

    @FXML
    private ComboBox<String> addSubject_course;

    @FXML
    private ComboBox<String> addSubject_status;

    @FXML
    private Button addSubject_addBtn;

    @FXML
    private Button addSubject_updateBtn;

    @FXML
    private Button addSubject_clearBtn;

    @FXML
    private Button addSubject_deleteBtn;

    @FXML
    private TableView<SubjectData> addSubject_tableView;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_code;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_subject;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_course;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_dateInsert;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_status;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private TextField payment_studentID;

    @FXML
    private TextField payment_name;

    @FXML
    private TextField payment_year;

    @FXML
    private TextField payment_section;

    @FXML
    private TextField payment_semester;

    @FXML
    private TextField payment_payment;

    @FXML
    private ComboBox<String> payment_status;

    @FXML
    private ComboBox<String> addTeacher_salaryStatus;

    @FXML
    private ImageView payment_imageView;

    @FXML
    private Button payment_payBtn;

    @FXML
    private Button payment_clearBtn;

    @FXML
    private TableView<StudentData> payment_tableView;

    @FXML
    private TableColumn<String, StudentData> payment_col_studentID;

    @FXML
    private TableColumn<String, StudentData> payment_col_name;

    @FXML
    private TableColumn<String, StudentData> payment_col_year;

    @FXML
    private TableColumn<String, StudentData> payment_col_section;

    @FXML
    private TableColumn<String, StudentData> payment_col_semester;

    @FXML
    private TableColumn<String, StudentData> payment_col_payment;

    @FXML
    private TableColumn<String, StudentData> payment_col_statusPayment;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private DatePicker salary_fromDate;

    @FXML
    private DatePicker salary_toDate;

    @FXML
    private TextField salary_teacherID;

    @FXML
    private TextField salary_name;

    @FXML
    private TextField salary_salaryPerDay;

    @FXML
    private ComboBox<String> salary_status;

    @FXML
    private Label salary_totalDays;

    @FXML
    private Label salary_salary;

    @FXML
    private Button salary_payBtn;

    @FXML
    private Button salary_clearBtn;

    @FXML
    private TableView<TeacherData> salary_tableView;

    @FXML
    private TableColumn<TeacherData, String> salary_col_teacherID;

    @FXML
    private TableColumn<TeacherData, String> salary_col_fullName;

    @FXML
    private TableColumn<TeacherData, String> salary_col_salaryPerDay;

    @FXML
    private TableColumn<TeacherData, String> salary_col_gender;

    @FXML
    private TableColumn<TeacherData, String> salary_col_dateInsert;

    @FXML
    private TableColumn<TeacherData, String> salary_col_dateUpdate;

    @FXML
    private TableColumn<TeacherData, String> salary_col_status;

    @FXML
    private TableView<SalaryData> salary_SP_tableView;

    @FXML
    private TableColumn<SalaryData, String> salary_SP_col_teacherID;

    @FXML
    private TableColumn<SalaryData, String> salary_SP_col_salaryPaid;

    @FXML
    private TableColumn<SalaryData, String> salary_SP_col_datePaid;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_TS;

    @FXML
    private Label dashboard_TT;

    @FXML
    private Label dashboard_SRT;

    @FXML
    private Label dashboard_TI;

    @FXML
    private AreaChart<?, ?> dashboard_chart_DS;

    @FXML
    private BarChart<?, ?> dashboard_chart_DT;

    @FXML
    private LineChart<?, ?> dashboard_chart_DI;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    public void dashboardDisplayTS() {
        String sql = "SELECT COUNT(id) FROM student WHERE date_delete IS NULL";
        connect = Database.connectDB();
        int tempTS = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTS = result.getInt("COUNT(id)");
            }
            dashboard_TS.setText("" + tempTS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardDisplayTT() {
        String sql = "SELECT COUNT(id) FROM teacher WHERE date_delete IS NULL";
        connect = Database.connectDB();
        int tempTT = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTT = result.getInt("COUNT(id)");
            }
            dashboard_TT.setText(String.valueOf(tempTT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardDisplaySRT() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT COUNT(id) FROM student WHERE date_delete IS NULL AND date_insert = '"
                + sqlDate + "'";
        connect = Database.connectDB();
        int tempSRT = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempSRT = result.getInt("COUNT(id)");

                dashboard_SRT.setText("" + tempSRT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardDisplayTI() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(payment) FROM student WHERE status_payment = 'Paid' AND date_delete IS NULL";
        connect = Database.connectDB();
        double tempTI = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTI = result.getDouble("SUM(payment)");
            }
            dashboard_TI.setText("$" + tempTI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardDSChart() {

        dashboard_chart_DS.getData().clear();

        String sql = "SELECT date_insert, COUNT(id) FROM student WHERE date_delete IS NULL GROUP BY TIMESTAMP(date_insert) ASC LIMIT 9";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DS.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDTChart() {

        dashboard_chart_DT.getData().clear();

        String sql = "SELECT date_insert, COUNT(id) FROM teacher WHERE date_delete IS NULL GROUP BY TIMESTAMP(date_insert) ASC LIMIT 5";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DT.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDIChart() {

        dashboard_chart_DI.getData().clear();

        String sql = "SELECT date_insert, SUM(payment) FROM student WHERE status_payment = 'Paid' AND date_delete IS NULL GROUP BY TIMESTAMP(date_insert) ASC LIMIT 5";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DI.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<StudentData> addStudentGetData() {

        ObservableList<StudentData> listData = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM student WHERE date_delete IS NULL";

        connect = Database.connectDB();

        StudentData sData;

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
//                StudentData(Integer id, String studentID, String fullName, String year, String course,
//            String section, Double payment, String statusPayment, Date dateInsert, String status)

                sData = new StudentData(result.getInt("id"), result.getString("student_id"),
                        result.getString("full_name"), result.getString("year"),
                        result.getString("course"), result.getString("section"),
                        result.getString("semester"),
                        result.getDouble("payment"), result.getString("status_payment"),
                        result.getDate("date_insert"), result.getString("status"));
                listData.add(sData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<StudentData> addStudentListData;

    public void addStudentDisplayData() {
        addStudentListData = addStudentGetData();

        addStudent_col_studentNumber.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        addStudent_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        addStudent_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudent_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudent_col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        addStudent_col_pay.setCellValueFactory(new PropertyValueFactory<>("payment"));
        addStudent_col_statusPayment.setCellValueFactory(new PropertyValueFactory<>("statusPayment"));
        addStudent_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addStudent_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudent_tableView.setItems(addStudentListData);
    }

    public void addStudentAddBtn() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Student");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addStudentUpdateBtn() {

        StudentData sData = addStudent_tableView.getSelectionModel().getSelectedItem();
        int num = addStudent_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            alert.errorMessage("Please select the item first");
            return;
        } else {
            ListData.temp_studentNumber = sData.getStudentID();
            ListData.temp_studentName = sData.getFullName();
            ListData.temp_studentBirthDate = sData.getBirthDate();
            ListData.temp_studentYear = sData.getYear();
            ListData.temp_studentCourse = sData.getCourse();
            ListData.temp_studentGender = sData.getGender();
            ListData.temp_studentSemester = sData.getSemester();
            ListData.temp_studentSection = sData.getSection();
            ListData.temp_studentPay = sData.getPayment();
            ListData.temp_studentPaymentStatus = sData.getStatusPayment();
            ListData.temp_studentStatus = sData.getStatus();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Update Student");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void addStudentDeleteBtn() {

        StudentData sData = addStudent_tableView.getSelectionModel().getSelectedItem();
        int num = addStudent_tableView.getSelectionModel().getSelectedIndex();

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        if ((num - 1) < -1) {
            alert.errorMessage("Please select item first");
            return;
        } else {
            if (alert.confirmMessage("Are you sure you want to Delete Student ID: "
                    + sData.getStudentID() + "?")) {
                String deleteData = "UPDATE student SET date_delete = ? WHERE student_id = ?";
                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, sData.getStudentID());
                    prepare.executeUpdate();
                    alert.successMessage("Deleted successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled.");
            }
        }
        addStudentDisplayData();
    }

    public ObservableList<TeacherData> addTeacherGetData() {

        ObservableList<TeacherData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM teacher WHERE date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TeacherData tData;

            while (result.next()) {
//                TeacherData(Integer id, String teacherID, String fullName, String gender, Date birthDate,
//            String yearExperience, String experience, String department, Double salary,
//            String image, Date dateInsert, Date dateUpdate, Date dateDelete, String status)
                tData = new TeacherData(result.getInt("id"),
                        result.getString("teacher_id"),
                        result.getString("full_name"), result.getString("gender"),
                        result.getString("year_experience"), result.getString("experience"),
                        result.getString("department"), result.getDouble("salary"),
                        result.getString("salary_status"),
                        result.getString("image"), result.getDate("date_insert"),
                        result.getDate("date_update"), result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(tData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<TeacherData> addTeacherListData;

    public void addTeacherDisplayData() {
        addTeacherListData = addTeacherGetData();

        addTeacher_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        addTeacher_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        addTeacher_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addTeacher_col_yearExperience.setCellValueFactory(new PropertyValueFactory<>("yearExperience"));
        addTeacher_col_experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        addTeacher_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        addTeacher_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        addTeacher_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addTeacher_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addTeacher_tableView.setItems(addTeacherListData);

    }

    public void addTeacherSelectItems() {

        TeacherData tData = addTeacher_tableView.getSelectionModel().getSelectedItem();
        int num = addTeacher_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addTeacher_teacherID.setText(tData.getTeacherID());
        addTeacher_fullName.setText(tData.getFullName());
        addTeacher_gender.getSelectionModel().select(tData.getGender());
        addTeacher_experienceYear.getSelectionModel().select(tData.getYearExperience());
        addTeacher_experience.setText(tData.getExperience());
        addTeacher_department.getSelectionModel().select(tData.getDepartment());
        addTeacher_salary.setText("" + tData.getSalary());
        addTeacher_status.getSelectionModel().select(tData.getStatus());
        addTeacher_salaryStatus.getSelectionModel().select(tData.getSalaryStatus());

        ListData.path = tData.getImage();

        image = new Image("File:" + tData.getImage(), 90, 103, false, true);
        addTeacher_imageView.setImage(image);
    }

    public void addTeacherGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : ListData.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        addTeacher_gender.setItems(listData);
    }

    public void addTeacherEYList() {

        List<String> listEY = new ArrayList<>();

        for (String data : ListData.yearExperience) {
            listEY.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listEY);
        addTeacher_experienceYear.setItems(listData);
    }

    public void addTeacherSSList() {

        List<String> listSS = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listSS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listSS);

        addTeacher_salaryStatus.setItems(listData);

    }

    public void addTeacherDepartmentList() {

//        List<String> listD = new ArrayList<>();
//
//        for (String data : ListData.department) {
//            listD.add(data);
//        }
//        ObservableList listData = FXCollections.observableArrayList(listD);
//        addTeacher_department.setItems(listData);
        String sql = "SELECT * FROM course WHERE date_delete IS NULL";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("department"));
            }
            addTeacher_department.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addTeacherStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : ListData.status) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        addTeacher_status.setItems(listData);
    }

    private String teacherID;

    public void generateTeacherID() {

        String sql = "SELECT MAX(id) FROM teacher";

        connect = Database.connectDB();
        String temp_teacherID = "TID-";
        int temp_count = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_count = result.getInt("MAX(id)");
            }

            if (temp_count == 0) {
                temp_count += 1;
                teacherID = temp_teacherID + temp_count;
            } else {
                teacherID = temp_teacherID + (temp_count + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addTeacherDisplayTeacherID() {
        generateTeacherID();
        addTeacher_teacherID.setText(teacherID);
    }

    public void addTeacherAddBtn() {

        if (addTeacher_teacherID.getText().isEmpty()
                || addTeacher_fullName.getText().isEmpty()
                || addTeacher_gender.getSelectionModel().getSelectedItem().isEmpty()
                || addTeacher_experience.getText().isEmpty()
                || addTeacher_experienceYear.getSelectionModel().getSelectedItem().isEmpty()
                || addTeacher_department.getSelectionModel().getSelectedItem().isEmpty()
                || addTeacher_salary.getText().isEmpty()
                || addTeacher_status.getSelectionModel().getSelectedItem().isEmpty()
                || ListData.path == null || "".equals(ListData.path)
                || addTeacher_salaryStatus.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String insertData = "INSERT INTO teacher "
                    + "(teacher_id, full_name, gender, year_experience, experience"
                    + ", department, salary, salary_status, image, date_insert, status) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            connect = Database.connectDB();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String path = ListData.path;
            path = path.replace("\\", "\\\\");

            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, addTeacher_teacherID.getText());
                prepare.setString(2, addTeacher_fullName.getText());
                prepare.setString(3, addTeacher_gender.getSelectionModel().getSelectedItem());
                prepare.setString(4, addTeacher_experienceYear.getSelectionModel().getSelectedItem());
                prepare.setString(5, addTeacher_experience.getText());
                prepare.setString(6, addTeacher_department.getSelectionModel().getSelectedItem());
                prepare.setString(7, addTeacher_salary.getText());
                prepare.setString(8, addTeacher_salaryStatus.getSelectionModel().getSelectedItem());
                prepare.setString(9, path);
                prepare.setString(10, String.valueOf(sqlDate));
                prepare.setString(11, addTeacher_status.getSelectionModel().getSelectedItem());

                prepare.executeUpdate();

                addTeacherDisplayData();

                Path transfer = Paths.get(path);
                Path copy = Paths.get("C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\UniversitiyManagementSystem\\src\\Teacher_Directory\\"
                        + addTeacher_teacherID.getText() + ".jpg");

                Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                alert.successMessage("Added Successfully!");

                addTeacherClearBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addTeacherUpdateBtn() {

        if (addTeacher_teacherID.getText().isEmpty()
                || addTeacher_fullName.getText().isEmpty()
                || addTeacher_gender.getSelectionModel().getSelectedItem().isEmpty()
                || addTeacher_experience.getText().isEmpty()
                || addTeacher_experienceYear.getSelectionModel().getSelectedItem().isEmpty()
                || addTeacher_department.getSelectionModel().getSelectedItem().isEmpty()
                || addTeacher_salary.getText().isEmpty()
                || addTeacher_status.getSelectionModel().getSelectedItem().isEmpty()
                || ListData.path == null || "".equals(ListData.path)
                || addTeacher_salaryStatus.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            if (alert.confirmMessage("Are you sure you want to Update Teacher ID: "
                    + addTeacher_teacherID.getText() + "?")) {
                String path = ListData.path;
                path = path.replace("\\", "\\\\");

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                String updateData = "UPDATE teacher SET full_name = '"
                        + addTeacher_fullName.getText() + "', gender = '"
                        + addTeacher_gender.getSelectionModel().getSelectedItem() + "', experience = '"
                        + addTeacher_experience.getText() + "', year_experience = '"
                        + addTeacher_experienceYear.getSelectionModel().getSelectedItem() + "', department = '"
                        + addTeacher_department.getSelectionModel().getSelectedItem() + "', salary = '"
                        + addTeacher_salary.getText() + "', salary_status = '"
                        + addTeacher_salaryStatus.getSelectionModel().getSelectedItem() + "', image = '"
                        + path + "', date_update = '"
                        + sqlDate + "', status = '"
                        + addTeacher_status.getSelectionModel().getSelectedItem() + "' "
                        + "WHERE teacher_id = '"
                        + addTeacher_teacherID.getText() + "'";

                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    addTeacherDisplayData();

                    Path transfer = Paths.get(path);
                    Path copy = Paths.get("C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\UniversitiyManagementSystem\\src\\Teacher_Directory\\"
                            + addTeacher_teacherID.getText() + ".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                    alert.successMessage("Updated Successfully!");

                    addTeacherClearBtn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled.");
            }
        }
    }

    public void addTeacherDeleteBtn() {

        if (addTeacher_teacherID.getText().isEmpty()
                || addTeacher_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please select the item first");
        } else {

            if (alert.confirmMessage("Are you sure you want to Delete Teacher ID: "
                    + addTeacher_teacherID.getText() + "?")) {
                String deleteData = "UPDATE teacher SET date_delete = ? WHERE teacher_id = ?";
                connect = Database.connectDB();

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, "" + sqlDate);
                    prepare.setString(2, addTeacher_teacherID.getText());

                    prepare.executeUpdate();

                    addTeacherDisplayData();

                    alert.successMessage("Deleted Successfully!");

                    addTeacherClearBtn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled");
            }
        }
    }

    public void addTeacherClearBtn() {
        addTeacherDisplayTeacherID();
        addTeacher_fullName.clear();
        addTeacher_gender.getSelectionModel().clearSelection();
        addTeacher_experienceYear.getSelectionModel().clearSelection();
        addTeacher_experience.clear();
        addTeacher_department.getSelectionModel().clearSelection();
        addTeacher_salary.clear();
        ListData.path = "";
        addTeacher_status.getSelectionModel().clearSelection();

        addTeacher_imageView.setImage(null);

    }

    public void addTeacherImportBtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.jpg", "*.jpeg", "*.png"));

        File file = open.showOpenDialog(addTeacher_importBtn.getScene().getWindow());

        if (file != null) {
            ListData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 90, 103, false, true);
            addTeacher_imageView.setImage(image);
        }
    }

    public ObservableList<CourseData> addCourseGetData() {

        ObservableList<CourseData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course WHERE date_delete IS NULL";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            CourseData cData;

            while (result.next()) {
//                CourseData(Integer id, String course, String department,
//             Date dateInsert, Date dateUpdate, Date dateDelete, String status)
                cData = new CourseData(result.getInt("id"), result.getString("course"),
                        result.getString("department"), result.getDouble("price"),
                        result.getDate("date_insert"),
                        result.getDate("date_update"), result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(cData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<CourseData> addCourseListData;

    public void addCourseDisplayData() {
        addCourseListData = addCourseGetData();

        addCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addCourse_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        addCourse_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addCourse_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addCourse_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addCourse_tableView.setItems(addCourseListData);
    }

    private int courseID = 0;

    public void addCourseSelectItem() {
        CourseData cData = addCourse_tableView.getSelectionModel().getSelectedItem();
        int num = addCourse_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addCourse_course.setText(cData.getCourse());
        addCourse_department.setText(cData.getDepartment());
        addCourse_price.setText("" + cData.getPrice());
        addCourse_status.getSelectionModel().select(cData.getStatus());

        courseID = cData.getId();
    }

    public void addCourseAddBtn() {

        if (addCourse_course.getText().isEmpty()
                || addCourse_department.getText().isEmpty()
                || addCourse_price.getText().isEmpty()
                || addCourse_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            connect = Database.connectDB();

            String checkCourse = "SELECT * FROM course WHERE course = '"
                    + addCourse_course.getText() + "' AND date_delete IS NULL";
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkCourse);

                if (result.next()) {
                    alert.errorMessage(addCourse_course.getText() + " is already exist");
                } else {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    String insertData = "INSERT INTO course (course, department, price, date_insert, status) "
                            + "VALUES(?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addCourse_course.getText());
                    prepare.setString(2, addCourse_department.getText());
                    prepare.setString(3, addCourse_price.getText());
                    prepare.setString(4, String.valueOf(sqlDate));
                    prepare.setString(5, addCourse_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    addCourseDisplayData();

                    alert.successMessage("Added Successfully!");

                    addCourseClear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addCourseUpdateBtn() {

        if (addCourse_course.getText().isEmpty()
                || addCourse_department.getText().isEmpty()
                || addCourse_price.getText().isEmpty()
                || addCourse_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            if (alert.confirmMessage("Are you sure you want to Update the course "
                    + addCourse_course.getText() + "?")) {
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                String updateData = "UPDATE course SET course = '"
                        + addCourse_course.getText() + "', department = '"
                        + addCourse_department.getText() + "', price = '"
                        + addCourse_price.getText() + "', date_update = '"
                        + sqlDate + "', status = '"
                        + addCourse_status.getSelectionModel().getSelectedItem() + "' "
                        + "WHERE id = " + courseID;

                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    addCourseDisplayData();

                    alert.successMessage("Updated Successfully!");

                    addCourseClear();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled");
            }
        }
    }

    public void addCourseDeleteBtn() {

        if (courseID == 0) {
            alert.errorMessage("Please select item first");
        } else {

            if (alert.confirmMessage("Are you sure you want to Delete Course "
                    + addCourse_course.getText() + "?")) {
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String deleteData = "UPDATE course SET date_delete = ? WHERE id = ?";
                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, String.valueOf(courseID));

                    prepare.executeUpdate();

                    addCourseDisplayData();

                    alert.successMessage("Updated Successfully!");

                    addCourseClear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void addCourseClear() {
        addCourse_course.clear();
        addCourse_department.clear();
        addCourse_price.clear();
        addCourse_status.getSelectionModel().clearSelection();
    }

    public void addCourseStatus() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.statusA) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addCourse_status.setItems(listData);
    }

    public ObservableList<SubjectData> addSubjectGetData() {

        ObservableList<SubjectData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM subject WHERE date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            SubjectData sData;
//            SubjectData(Integer id, String subjectCode, String subject, String course,
//            Date dateInsert, Date dateUpdate, Date dateDelete, String status)
            while (result.next()) {
                sData = new SubjectData(result.getInt("id"),
                        result.getString("subject_code"),
                        result.getString("subject"),
                        result.getString("course"),
                        result.getDate("date_insert"),
                        result.getDate("date_update"),
                        result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(sData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<SubjectData> addSubjectListData;

    public void addSubjectDisplayData() {

        addSubjectListData = addSubjectGetData();

        addSubject_col_code.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        addSubject_col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        addSubject_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addSubject_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addSubject_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addSubject_tableView.setItems(addSubjectListData);
    }

    private int subjectID = 0;

    public void addSubjectSelectItem() {
        SubjectData sData = addSubject_tableView.getSelectionModel().getSelectedItem();
        int num = addSubject_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addSubject_code.setText(sData.getSubjectCode());
        addSubject_subject.setText(sData.getSubject());
        addSubject_course.getSelectionModel().select(sData.getCourse());
        addSubject_status.getSelectionModel().select(sData.getStatus());

        subjectID = sData.getId();
    }

    public void addSubjectAddBtn() {

        if (addSubject_code.getText().isEmpty()
                || addSubject_subject.getText().isEmpty()
                || addSubject_course.getSelectionModel().getSelectedItem().isEmpty()
                || addSubject_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            connect = Database.connectDB();

            String checkSubject = "SELECT * FROM subject WHERE subject_code = '"
                    + addSubject_code.getText() + "' AND date_delete IS NULL";
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkSubject);

                if (result.next()) {
                    alert.errorMessage(addSubject_code.getText() + " is already exist");
                } else {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    String insertData = "INSERT INTO subject "
                            + "(subject_code, subject, course, date_insert, status) "
                            + "VALUES(?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addSubject_code.getText());
                    prepare.setString(2, addSubject_subject.getText());
                    prepare.setString(3, addSubject_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, String.valueOf(sqlDate));
                    prepare.setString(5, addSubject_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    addSubjectDisplayData();

                    alert.successMessage("Added Successfully!");

                    addSubjectClear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addSubjectUpdateBtn() {

        if (addSubject_code.getText().isEmpty()
                || addSubject_subject.getText().isEmpty()
                || addSubject_course.getSelectionModel().getSelectedItem().isEmpty()
                || addSubject_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            if (alert.confirmMessage("Are you sure you want to Update the Subject Code: "
                    + addSubject_code.getText() + "?")) {
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                String updateData = "UPDATE subject SET subject_code = '"
                        + addSubject_code.getText() + "', subject = '"
                        + addSubject_subject.getText() + "', course = '"
                        + addSubject_course.getSelectionModel().getSelectedItem()
                        + "', date_update = '"
                        + sqlDate + "', status = '"
                        + addSubject_status.getSelectionModel().getSelectedItem() + "' "
                        + "WHERE id = " + subjectID;

                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    addSubjectDisplayData();

                    alert.successMessage("Updated Successfully!");

                    addSubjectClear();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled");
            }
        }
    }

    public void addSubjectDeleteBtn() {

        if (subjectID == 0) {
            alert.errorMessage("Please select item first");
        } else {

            if (alert.confirmMessage("Are you sure you want to Delete Subject Code: "
                    + addSubject_code.getText() + "?")) {
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String deleteData = "UPDATE subject SET date_delete = ? WHERE id = ?";
                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, String.valueOf(subjectID));

                    prepare.executeUpdate();

                    addSubjectDisplayData();

                    alert.successMessage("Deleted Successfully!");

                    addSubjectClear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void addSubjectClear() {
        addSubject_code.clear();
        addSubject_subject.clear();
        addSubject_course.getSelectionModel().clearSelection();
        addSubject_status.getSelectionModel().clearSelection();
    }

    public void addSubjectCourseList() {

        String sql = "SELECT * FROM course WHERE date_delete IS NULL";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("course"));
            }

            addSubject_course.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSubjectStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : ListData.statusA) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addSubject_status.setItems(listData);
    }

    public ObservableList<StudentData> paymentGetData() {

        ObservableList<StudentData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM student WHERE status_payment = 'Pending' AND date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            StudentData sData;

            while (result.next()) {
//StudentData(Integer id, String studentID, String fullName, String year, String course,
//            String section, String semester, Double payment, String statusPayment, String image,
//            Date dateUpdate, String status)
                sData = new StudentData(result.getInt("id"), result.getString("student_id"),
                        result.getString("full_name"), result.getString("year"),
                        result.getString("course"), result.getString("section"),
                        result.getString("semester"), result.getDouble("payment"),
                        result.getString("status_payment"), result.getString("image"),
                        result.getDate("date_update"), result.getString("status"));

                listData.add(sData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<StudentData> paymentListData;

    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        payment_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        payment_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        payment_col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        payment_col_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        payment_col_payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        payment_col_statusPayment.setCellValueFactory(new PropertyValueFactory<>("statusPayment"));

        payment_tableView.setItems(paymentListData);

    }

    public void paymentSelectItem() {
        StudentData sData = payment_tableView.getSelectionModel().getSelectedItem();
        int num = payment_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        payment_studentID.setText(sData.getStudentID());
        payment_name.setText(sData.getFullName());
        payment_year.setText(sData.getYear());
        payment_section.setText(sData.getSection());
        payment_semester.setText(sData.getSemester());
        payment_payment.setText("" + sData.getPayment());

        ListData.path = sData.getImage();

        image = new Image("File:" + ListData.path, 94, 96, false, true);
        payment_imageView.setImage(image);

        payment_status.getSelectionModel().select(sData.getStatusPayment());
    }

    public void paymentDisableFields() {
        payment_studentID.setDisable(true);
        payment_name.setDisable(true);
        payment_year.setDisable(true);
        payment_section.setDisable(true);
        payment_semester.setDisable(true);
        payment_payment.setDisable(true);
    }

    public void paymentPaymentStatusList() {

        List<String> listPS = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listPS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listPS);
        payment_status.setItems(listData);

    }

    public void paymentUpdateBtn() {

        if (payment_studentID.getText().isEmpty()
                || payment_payment.getText().isEmpty()) {
            alert.errorMessage("Please select item first");
        } else {
            if (alert.confirmMessage("Are you sure?")) {
                String updateData = "UPDATE student SET status_payment = ? WHERE student_id = ?";
                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, payment_status.getSelectionModel().getSelectedItem());
                    prepare.setString(2, payment_studentID.getText());
                    prepare.executeUpdate();

                    paymentDisplayData();

                    alert.successMessage("Updated Successfully!");

                    paymentClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void paymentClearBtn() {

        payment_studentID.clear();
        payment_name.clear();
        payment_year.clear();
        payment_section.clear();
        payment_semester.clear();
        payment_payment.clear();

        ListData.path = "";

        payment_imageView.setImage(null);

        payment_status.getSelectionModel().clearSelection();

    }

    public ObservableList<TeacherData> salaryGetData() {

        ObservableList<TeacherData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM teacher WHERE salary_status = 'Pending' AND date_delete IS NULL AND status = 'Active'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TeacherData tData;
            while (result.next()) {

//                TeacherData(Integer id, String teacherID, String fullName,
//            String gender, Double salary, String salaryStatus, Date dateInsert,
//            Date dateUpdate, String status)
                tData = new TeacherData(result.getInt("id"),
                        result.getString("teacher_id"), result.getString("full_name"),
                        result.getString("gender"), result.getDouble("salary"),
                        result.getString("salary_status"), result.getDate("date_insert"),
                        result.getDate("date_update"), result.getString("status"));

                listData.add(tData);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<TeacherData> salaryListData;

    public void salaryDisplayData() {
        salaryListData = salaryGetData();

        salary_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        salary_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        salary_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        salary_col_salaryPerDay.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salary_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        salary_col_dateUpdate.setCellValueFactory(new PropertyValueFactory<>("dateUpdate"));
        salary_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        salary_tableView.setItems(salaryListData);
    }

    public void salarySelectItem() {

        TeacherData tData = salary_tableView.getSelectionModel().getSelectedItem();
        int num = salary_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        salary_teacherID.setText(tData.getTeacherID());
        salary_name.setText(tData.getFullName());
        salary_salaryPerDay.setText("" + tData.getSalary());
        salary_status.getSelectionModel().select(tData.getSalaryStatus());

    }

    public void salaryDisableFields() {
        salary_teacherID.setDisable(true);
        salary_name.setDisable(true);
        salary_salaryPerDay.setDisable(true);
    }

    public double salaryGetSalaryPerDay() {

        double getSalary = 0;

        String sql = "SELECT * FROM teacher WHERE teacher_id = '"
                + salary_teacherID.getText() + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getSalary = result.getDouble("salary");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getSalary;
    }

    private long countDays;
    private double totalSalary;

    public void salaryCountDays() {
        if (salary_fromDate.getValue() != null
                || salary_toDate.getValue() != null) {
            try {
                countDays = ChronoUnit.DAYS.between(salary_fromDate.getValue(), salary_toDate.getValue());

                totalSalary = (salaryGetSalaryPerDay() * countDays);

                salary_totalDays.setText("" + countDays);
                salary_salary.setText("$" + totalSalary);

            } catch (Exception e) {
                alert.errorMessage("Invalid.");
            }
        }
    }

    public void salaryPayBtn() {

        if (salary_totalDays.getText().equals("-----")
                || salary_salary.getText().equals("-----")
                || salary_teacherID.getText().isEmpty()
                || totalSalary == 0
                || countDays == 0) {
            alert.errorMessage("Please select item first");
        } else {
            if (alert.confirmMessage("Pay?")) {
                String sql = "INSERT INTO salary "
                        + "(teacher_id, name, salary_per_day, total_days, salary_paid, date_paid)"
                        + " VALUES(?,?,?,?,?,?)";

                connect = Database.connectDB();

                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, salary_teacherID.getText());
                    prepare.setString(2, salary_name.getText());
                    prepare.setString(3, salary_salaryPerDay.getText());
                    prepare.setString(4, "" + countDays);
                    prepare.setString(5, "" + totalSalary);
                    prepare.setString(6, "" + sqlDate);

                    prepare.executeUpdate();

                    String updateData = "UPDATE teacher SET salary_status = ? WHERE teacher_id = ?";

                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, salary_status.getSelectionModel().getSelectedItem());
                    prepare.setString(2, salary_teacherID.getText());

                    prepare.executeUpdate();

                    salaryDisplayData();

                    alert.successMessage("Paid successfully!");

                    salaryClear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void salaryClear() {
        salary_teacherID.clear();
        salary_name.clear();
        salary_salaryPerDay.clear();
        salary_status.getSelectionModel().clearSelection();
        salary_fromDate.setValue(null);
        salary_toDate.setValue(null);
        countDays = 0;
        totalSalary = 0;
    }

    public void salarySalaryStatusList() {

        List<String> listSS = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listSS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listSS);
        salary_status.setItems(listData);

    }

    public ObservableList<SalaryData> salarySPGetdata() {

        ObservableList<SalaryData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM salary";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            SalaryData sData;

            while (result.next()) {
//                SalaryData(Integer id, String teacherID, String name, Double salaryPerDay,
//            Integer totalDays, Double salaryPaid, Date datePaid)
                sData = new SalaryData(result.getInt("id"),
                        result.getString("teacher_id"),
                        result.getString("name"),
                        result.getDouble("salary_per_day"),
                        result.getInt("total_days"),
                        result.getDouble("salary_paid"),
                        result.getDate("date_paid"));

                listData.add(sData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<SalaryData> salarySPListData;

    public void salaryDisplaydata() {
        salarySPListData = salarySPGetdata();

        salary_SP_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        salary_SP_col_salaryPaid.setCellValueFactory(new PropertyValueFactory<>("salaryPaid"));
        salary_SP_col_datePaid.setCellValueFactory(new PropertyValueFactory<>("datePaid"));

        salary_SP_tableView.setItems(salarySPListData);
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {

            dashboard_form.setVisible(true);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            dashboardDisplayTS();
            dashboardDisplayTT();
            dashboardDisplaySRT();
            dashboardDisplayTI();
            dashboardDSChart();
            dashboardDTChart();
            dashboardDIChart();

        } else if (event.getSource() == addStudent_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(true);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            addStudentDisplayData();
        } else if (event.getSource() == addTeacher_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(true);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            addTeacherDisplayData();
            addTeacherGenderList();
            addTeacherSSList();
            addTeacherEYList();
            addTeacherDepartmentList();
            addTeacherStatusList();
            addTeacherDisplayTeacherID();
        } else if (event.getSource() == addCourse_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(true);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            addCourseDisplayData();
            addCourseStatus();
        } else if (event.getSource() == addSubject_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(true);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            addSubjectDisplayData();
            addSubjectCourseList();
            addSubjectStatusList();
        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(true);
            salary_form.setVisible(false);

            paymentDisplayData();
            paymentPaymentStatusList();
            paymentDisableFields();
        } else if (event.getSource() == salary_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(true);

            salaryDisplayData();
            salaryDisableFields();
            salarySalaryStatusList();
            salaryDisplaydata();
        }

    }

    public void displayGreet() {
        String username = ListData.admin_username;
        username = username.substring(0, 1).toUpperCase() + username.substring(1);

        greet_username.setText("Welcome, " + username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayGreet();

        dashboardDisplayTS();
        dashboardDisplayTT();
        dashboardDisplaySRT();
        dashboardDisplayTI();
        dashboardDSChart();
        dashboardDTChart();
        dashboardDIChart();
    }
}
