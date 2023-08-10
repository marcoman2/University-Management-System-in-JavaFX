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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS 10
 */
public class StudentMainFormController implements Initializable {
    
    @FXML
    private Label student_id;
    
    @FXML
    private Button studentInformation_btn;
    
    @FXML
    private Button logout_btn;
    
    @FXML
    private TableView<DataStudentHandle> table_view;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_teacherID;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_name;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_gender;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_YE;
    
    @FXML
    private Circle circle_image;
    
    @FXML
    private Label teacher_id;
    
    @FXML
    private Label teacher_name;
    
    @FXML
    private Label teacher_gender;
    
    @FXML
    private Label teacher_date;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    AlertMessage alert = new AlertMessage();
    
    public ObservableList<DataStudentHandle> teacherSetData() {
        
        ObservableList<DataStudentHandle> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM teacher_student WHERE stud_studentID = '"
                + student_id.getText() + "' AND date_delete IS NULL";
        
        connect = Database.connectDB();
        
        try {
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            DataStudentHandle dsh;
            
            while (result.next()) {
//                DataStudentHandle(String teacherID, String studentID
//            , String name, String gender, Date dateInsert)

                dsh = new DataStudentHandle(result.getString("teacher_id"),
                        result.getString("stud_studentID"),
                        result.getString("stud_name"),
                        result.getString("stud_gender"),
                        result.getDate("date_insert"));
                listData.add(dsh);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private ObservableList<DataStudentHandle> teacherListData;
    
    public void teacherDisplayData() {
        teacherListData = teacherSetData();
        
        studentInfo_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        studentInfo_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentInfo_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        studentInfo_col_YE.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        
        table_view.setItems(teacherListData);
    }
    
    private Image image;
    
    public void teacherSelectData() {
        DataStudentHandle dsh = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if ((num - 1) < -1) {
            return;
        }
        
        String sql = "SELECT * FROM teacher WHERE teacher_id = '"
                + dsh.getTeacherID() + "'";
        
        connect = Database.connectDB();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                
                String path = "File:" + result.getString("image");
                
                image = new Image(path, 164, 73, false, true);
                circle_image.setFill(new ImagePattern(image));
                
                teacher_id.setText(result.getString("teacher_id"));
                teacher_name.setText(result.getString("full_name"));
                teacher_gender.setText(result.getString("gender"));
                teacher_date.setText(result.getString("date_insert"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void studentIDDisplay() {
        
        String sql = "SELECT * FROM users WHERE username = '"
                + ListData.student_username + "'";
        
        connect = Database.connectDB();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                student_id.setText(result.getString("student_id"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void logoutBtn() {
        
        try {
            if (alert.confirmMessage("Are you sure you want to logout?")) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
                
                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        teacherDisplayData();
        studentIDDisplay();
        
    }
    
}
