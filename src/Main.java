package CovidDatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {

    protected static Stage primaryStage;

    protected static Patient[] patients;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("screen.fxml"));
        primaryStage.setTitle("Covid-19 Database");
        primaryStage.setScene(new Scene(root, 475, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws ParseException {
        patients = new Patient[20];

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        patients[0] = new Patient("Flora", 6, 'A', date.parse("1-4-2020"));
        patients[1] = new Patient("Denys", 24, 'B', date.parse("1-4-2020"));
        patients[2] = new Patient("Jim", 42, 'C', date.parse("18-5-2020"));
        patients[3] = new Patient("Hazel", 87, 'D', date.parse("23-6-2020"));
        patients[4] = new Patient("Caery", 72, 'A', date.parse("1-6-2020"));
        patients[5] = new Patient("David", 7, 'B', date.parse("14-6-2020"));
        patients[6] = new Patient("Kevim", 37, 'D', date.parse("5-6-2020"));
        patients[7] = new Patient("Tom", 67, 'D', date.parse("20-6-2020"));
        patients[8] = new Patient("Bob", 74, 'A', date.parse("4-7-2020"));
        patients[9] = new Patient("Rachel", 48, 'C', date.parse("24-7-2020"));
        patients[10] = new Patient("Thomas", 21, 'C', date.parse("11-6-2020"));
        patients[11] = new Patient("Mary", 17, 'D', date.parse("21-6-2020"));
        patients[12] = new Patient("Smith", 89, 'A', date.parse("7-8-2020"));
        patients[13] = new Patient("Pearson", 47, 'B', date.parse("4-6-2020"));
        patients[14] = new Patient("Anderson", 62, 'B', date.parse("27-7-2020"));
        patients[15] = new Patient("Johnson", 10, 'D', date.parse("1-8-2020"));
        patients[16] = new Patient("Robertz", 50, 'A', date.parse("9-8-2020"));
        patients[17] = new Patient("Julie", 86, 'B', date.parse("2-5-2020"));
        patients[18] = new Patient("Edith", 42, 'D', date.parse("7-6-2020"));
        patients[19] = new Patient("John", 95, 'D', date.parse("1-6-2020"));

        launch(args);
    }

    static Patient[] filterByTower(Patient[] patients, char c) {
        ArrayList<Patient> res = new ArrayList<>();
        for (Patient patient : patients) {
            if(patient.getTower() == c)
                res.add(patient);
        }
        return res.toArray(Patient[]::new);
    }

    static Patient[] patientsBeforeDate(Patient[] patients, Date date) {
        ArrayList<Patient> res = new ArrayList<>();
        for(Patient patient : patients) {
            if(patient.getReportingDate().compareTo(date) < 0)
                res.add(patient);
        }
        return res.toArray(Patient[]::new);
    }
}
