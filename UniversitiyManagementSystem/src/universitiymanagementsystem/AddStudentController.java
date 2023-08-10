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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author WINDOWS 10
 */
public class AddStudentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField student_number;

    @FXML
    private TextField student_name;

    @FXML
    private DatePicker student_birthDate;

    @FXML
    private ComboBox<String> student_year;

    @FXML
    private ComboBox<String> student_course;

    @FXML
    private ComboBox<String> student_section;

    @FXML
    private TextField student_pay;

    @FXML
    private ComboBox<String> student_payment;

    @FXML
    private ImageView student_imageView;

    @FXML
    private Button student_importBtn;

    @FXML
    private Button student_addBtn;

    @FXML
    private Button student_updateBtn;

    @FXML
    private ComboBox<String> student_status;

    @FXML
    private Label student_price;

    @FXML
    private ComboBox<String> student_semester;

    @FXML
    private ComboBox<String> student_gender;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    public void addBtn() {

        if (student_number.getText().isEmpty()
                || student_name.getText().isEmpty()
                || student_year.getSelectionModel().getSelectedItem().isEmpty()
                || student_course.getSelectionModel().getSelectedItem().isEmpty()
                || student_section.getSelectionModel().getSelectedItem().isEmpty()
                || student_pay.getText().isEmpty()
                || student_payment.getSelectionModel().getSelectedItem().isEmpty()
                || student_status.getSelectionModel().getSelectedItem().isEmpty()
                || ListData.path == null || "".equals(ListData.path)
                || student_birthDate.getValue() == null
                || student_semester.getSelectionModel().getSelectedItem().isEmpty()
                || student_gender.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields.");
        } else {
            connect = Database.connectDB();

            String checkStudentNum = "SELECT * FROM student WHERE student_id = '"
                    + student_number.getText() + "'";

            try {
                prepare = connect.prepareStatement(checkStudentNum);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage("Student Number: " + student_number.getText() + " is already taken");
                } else {
                    String insertData = "INSERT INTO student "
                            + "(student_id, full_name, gender, birth_date, year, age, course, section, semester, payment"
                            + ", status_payment, image, date_insert, status) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, student_number.getText());
                    prepare.setString(2, student_name.getText());
                    prepare.setString(3, student_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, String.valueOf(student_birthDate.getValue()));
                    prepare.setString(5, student_year.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(getAge));
                    prepare.setString(7, student_course.getSelectionModel().getSelectedItem());
                    prepare.setString(8, student_section.getSelectionModel().getSelectedItem());
                    prepare.setString(9, student_semester.getSelectionModel().getSelectedItem());
                    prepare.setString(10, String.valueOf(price));
                    prepare.setString(11, student_payment.getSelectionModel().getSelectedItem());

                    String path = ListData.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(12, path);

//                    C:\Users\WINDOWS 10\Documents\NetBeansProjects\UniversitiyManagementSystem\src\Student_Directory
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(13, String.valueOf(sqlDate));
                    prepare.setString(14, student_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    Path transfer = Paths.get(path);
                    Path copy = Paths.get("C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\UniversitiyManagementSystem\\src\\Student_Directory\\"
                            + student_number.getText() + ".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                    alert.successMessage("Added Successfully!");

                    clearFields();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void updateBtn() {

        if (student_number.getText().isEmpty()
                || student_name.getText().isEmpty()
                || student_year.getSelectionModel().getSelectedItem().isEmpty()
                || student_course.getSelectionModel().getSelectedItem().isEmpty()
                || student_section.getSelectionModel().getSelectedItem().isEmpty()
                || student_pay.getText().isEmpty()
                || student_payment.getSelectionModel().getSelectedItem().isEmpty()
                || student_status.getSelectionModel().getSelectedItem().isEmpty()
                || ListData.path == null || "".equals(ListData.path)
                || student_birthDate.getValue() == null
                || student_semester.getSelectionModel().getSelectedItem().isEmpty()
                || student_gender.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields.");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String path = ListData.path;
            path = path.replace("\\", "\\\\");
            priceList();
            if (alert.confirmMessage("Are you sure you want to Update Student ID: "
                    + student_number.getText())) {
                String updateData = "UPDATE student SET "
                        + "full_name = '"
                        + student_name.getText() + "', birth_date = '"
                        + student_birthDate.getValue() + "', age = '"
                        + getAge + "', year = '"
                        + student_year.getSelectionModel().getSelectedItem() + "', gender = '"
                        + student_gender.getSelectionModel().getSelectedItem() + "', course = '"
                        + student_course.getSelectionModel().getSelectedItem() + "', semester = '"
                        + student_semester.getSelectionModel().getSelectedItem() + "', section = '"
                        + student_section.getSelectionModel().getSelectedItem() + "', payment = '"
                        + price + "', status_payment = '"
                        + student_payment.getSelectionModel().getSelectedItem() + "', image = '"
                        + path + "', date_update = '"
                        + String.valueOf(sqlDate) + "', status = '"
                        + student_status.getSelectionModel().getSelectedItem()
                        + "' WHERE student_id = '" + student_number.getText() + "'";

                try {

                    Path transfer = Paths.get(path);
                    Path copy = Paths.get("C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\UniversitiyManagementSystem\\src\\Student_Directory\\"
                            + student_number.getText() + ".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                alert.errorMessage("Cancelled");
            }

        }

    }

    public void clearFields() {
        displayStudentNumber();

        student_name.clear();
        student_birthDate.setValue(null);
        student_year.getSelectionModel().clearSelection();
        student_course.getSelectionModel().clearSelection();
        student_gender.getSelectionModel().clearSelection();
        student_section.getSelectionModel().clearSelection();
        student_semester.getSelectionModel().clearSelection();
        student_payment.getSelectionModel().clearSelection();
        student_status.getSelectionModel().clearSelection();
        student_pay.clear();

        ListData.path = "";

        student_imageView.setImage(null);

    }

    private int getAge = 0;

    public void countAge() {
        if (student_birthDate.getValue() != null) {
            LocalDate birthDate = student_birthDate.getValue();
            int age = Period.between(birthDate, LocalDate.now()).getYears();

            getAge = age;

            System.out.println(getAge);
        }
    }

    public void importBtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.jpg", "*.jpeg", "*.png"));

        File file = open.showOpenDialog(student_importBtn.getScene().getWindow());

        if (file != null) {
            ListData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 100, 113, false, true);
            student_imageView.setImage(image);
        }
    }

    public void displayStudentNumber() {
        FXMLDocumentController control = new FXMLDocumentController();

        int getnumber = control.studentIDGenerator();

        Date date = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy");

        String getyear = format.format(date) + "0000";

        int getStudentNum = Integer.parseInt(getyear) + getnumber;

        student_number.setText(String.valueOf(getStudentNum));

    }

    public void yearList() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.year) {
            listY.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listY);
        student_year.setItems(listData);
    }

    public void courseList() {
//        List<String> listC = new ArrayList<>();
//
//        for (String data : ListData.course) {
//            listC.add(data);
//        }
//
//        ObservableList listData = FXCollections.observableArrayList(listC);
//        student_course.setItems(listData);

        String sql = "SELECT * FROM course WHERE date_delete IS NULL";

        connect = Database.connectDB();

        try {
            ObservableList listData = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("course"));
            }
            student_course.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        priceList();

    }
    private double price = 0;

    public void priceList() {
//        if (student_course.getSelectionModel().getSelectedItem() != null) {
//            if (student_course.getSelectionModel().getSelectedItem().equals("BSCS")) {
//                price = 100;
//            } else if (student_course.getSelectionModel().getSelectedItem().equals("BSIT")) {
//                price = 105;
//            } else {
//                price = 0;
//            }
//            student_pay.setText("$" + String.valueOf(price));
//        }

        String selectData = "SELECT * FROM course WHERE course = '"
                + student_course.getSelectionModel().getSelectedItem() + "' AND date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                price = result.getDouble("price");
                System.out.println(price);
                student_pay.setText("$" + String.valueOf(price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFields() {
        try {
            if (ListData.temp_studentNumber != null) {
                String sql = "SELECT * FROM student WHERE student_id = '"
                        + ListData.temp_studentNumber + "'";
                connect = Database.connectDB();

                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                if (result.next()) {

                    if (result.getString("full_name") != null) {
                        student_number.setText(ListData.temp_studentNumber);
                        student_name.setText(result.getString("full_name"));
                        student_birthDate.setValue(LocalDate.parse(result.getString("birth_date")));
                        student_year.getSelectionModel().select(result.getString("year"));
                        student_course.getSelectionModel().select(result.getString("course"));
                        student_section.getSelectionModel().select(result.getString("section"));
                        student_gender.getSelectionModel().select(result.getString("gender"));
                        student_semester.getSelectionModel().select(result.getString("semester"));
                        student_payment.getSelectionModel().select(result.getString("status_payment"));
                        student_status.getSelectionModel().select(result.getString("status"));
                        student_pay.setText(result.getString("payment"));

                        ListData.path = result.getString("image");

                        image = new Image("File:" + ListData.path, 100, 113, false, true);
                        student_imageView.setImage(image);

                    } else {
                        student_number.setText(ListData.temp_studentNumber);
                        student_status.getSelectionModel().select(result.getString("status"));
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sectionList() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.section) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        student_section.setItems(listData);
    }

    public void statusPaymentList() {
        List<String> listSP = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listSP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listSP);
        student_payment.setItems(listData);
    }

    public void statusList() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        student_status.setItems(listData);
    }

    public void semesterList() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.semester) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        student_semester.setItems(listData);
    }

    public void genderList() {
        List<String> listG = new ArrayList<>();

        for (String data : ListData.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        student_gender.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yearList();
        courseList();
        sectionList();
        statusPaymentList();
        statusList();
        semesterList();
        genderList();

        displayStudentNumber();

        setFields();
    }

}
