package CovidDatabase;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class ScreenController {

    @FXML
    protected TextField day, month, year;

    @FXML
    protected CheckBox checkboxA, checkboxB, checkboxC, checkboxD;

    @FXML
    protected Button submitButton, resetButton;

    @FXML
    protected TableView<Table> table;

    @FXML
    protected TableView<TowerTable> towerTable;

    @FXML
    protected Label wrongDate;

    private Patient[] res;

    private int inputDay, inputMonth, inputYear;
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void initialize() {
        resetButton.setVisible(false);
        wrongDate.setVisible(false);
    }

    protected void function() throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        String[][] patientData = new String[20][6];
        int aActive=0, aRecovered=0, bActive=0, bRecovered=0, cActive=0, cRecovered=0, dActive=0, dRecovered=0;
        for (int i = 0; i < res.length; i++) {
            String status = "Active";
            if (dateFormat.parse(inputDay + "-" + inputMonth + "-" + inputYear).compareTo(res[i].getRecoveryDate()) >= 0) {
                status = "Recovered";
                if(res[i].getTower() == 'A') aRecovered++;
                if(res[i].getTower() == 'B') bRecovered++;
                if(res[i].getTower() == 'C') cRecovered++;
                if(res[i].getTower() == 'D') dRecovered++;
            }
            else {
                if(res[i].getTower() == 'A') aActive++;
                if(res[i].getTower() == 'B') bActive++;
                if(res[i].getTower() == 'C') cActive++;
                if(res[i].getTower() == 'D') dActive++;
            }
            patientData[i] = new String[]{res[i].getName(), String.valueOf(res[i].getAge()), String.valueOf(res[i].getTower()), status, date.format(res[i].getReportingDate()), date.format(res[i].getRecoveryDate())};
        }

        ObservableList<Table> pData = table.getItems();

        // Add data to table

        for (String[] p : patientData) {
            pData.add(new Table(p[0], p[1], p[2], p[3], p[4], p[5]));
        }

        String[][] towerData = new String[4][3];
        towerData[0] = new String[]{"A", (aActive == 0 && aRecovered == 0) ? "-" : String.valueOf(aActive), (aActive == 0 && aRecovered == 0) ? "-" : String.valueOf(aRecovered)};
        towerData[1] = new String[]{"B", (bActive == 0 && bRecovered == 0) ? "-" : String.valueOf(bActive), (bActive == 0 && bRecovered == 0) ? "-" : String.valueOf(bRecovered)};
        towerData[2] = new String[]{"C", (cActive == 0 && cRecovered == 0) ? "-" : String.valueOf(cActive), (cActive == 0 && cRecovered == 0) ? "-" : String.valueOf(cRecovered)};
        towerData[3] = new String[]{"D", (dActive == 0 && dRecovered == 0) ? "-" : String.valueOf(dActive), (dActive == 0 && dRecovered == 0) ? "-" : String.valueOf(dRecovered)};

        ObservableList<TowerTable> tData = towerTable.getItems();

        // Add data to towerTable

        for (String[] t : towerData) {
            tData.add(new TowerTable(t[0], t[1], t[2]));
        }

    }

    public void submit() {
        submitButton.setVisible(false);
        resetButton.setVisible(true);
        resetButton.setDisable(false);

        resetButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = Main.primaryStage;
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("screen.fxml"));
                } catch (IOException ignored) {
                }
                stage.setTitle("Covid-19 Database");
                assert root != null;
                stage.setScene(new Scene(root, 475, 600));
                stage.show();
            }
        });

        String dayString = day.getText().strip(),
                monthString = month.getText().strip(),
                yearString = year.getText().strip();

        if(!validateString(dayString, monthString, yearString)) {
            wrongDate.setText("Enter a valid date between 1-4-2020 to 31-8-2020 in dd-mm-yyyy");
            wrongDate.setVisible(true);
        }
        else {
            inputDay = Integer.parseInt(dayString);
            inputMonth = Integer.parseInt(monthString);
            inputYear = Integer.parseInt(yearString);

            if (!isValidDate(inputDay, inputMonth, inputYear)) {
                wrongDate.setText("Please enter a valid date between April-1 to August-31 in dd-mm-yyyy");
                wrongDate.setVisible(true);

            } else {
                boolean aCheckBox = checkboxA.isSelected(),
                        bCheckBox = checkboxB.isSelected(),
                        cCheckBox = checkboxC.isSelected(),
                        dCheckBox = checkboxD.isSelected();

                Patient[] patientsByDate = new Patient[0];
                try {
                    patientsByDate = Main.patientsBeforeDate(Main.patients, dateFormat.parse(inputDay+"-"+inputMonth+"-"+inputYear));
                } catch (ParseException parseException) {
                    System.out.println("Parse Exception");
                }
                ArrayList<Patient> patientsByTower = new ArrayList<>();

                if (aCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'A');
                    patientsByTower.addAll(Arrays.asList(p));
                }
                if (bCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'B');
                    patientsByTower.addAll(Arrays.asList(p));
                }
                if (cCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'C');
                    patientsByTower.addAll(Arrays.asList(p));
                }
                if (dCheckBox) {
                    Patient[] p = Main.filterByTower(patientsByDate, 'D');
                    patientsByTower.addAll(Arrays.asList(p));
                }

                res = patientsByTower.toArray(Patient[]::new);

                try {
                    function();
                } catch (ParseException parseException) {
                    System.out.println("Parse Exception");
                }
            }
        }
    }

    boolean validateString(String date, String month, String year) {
        if(date.length() != 2 || month.length() == 0 || month.length() > 2 || !year.equals("2020"))
            return false;
        for (int i = 0; i < date.length(); i++) {
            if(!Character.isDigit(date.charAt(i)))
                return false;
        }
        for(char c : month.toCharArray()) {
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    boolean isValidDate(int date, int month, int year) {
        if (date < 1 || month < 1 || month > 12 || year != 2020)
            return false;
        if (month == 4) { // April-30
            return date <= 30;
        } else if (month == 5) { // May-31
            return date <= 31;
        } else if (month == 6) { // June-30
            return date <= 30;
        } else if (month == 7) { // July-31
            return date <= 31;
        } else if (month == 8) { // August-31
            return date <= 31;
        }
        return false;
    }

}
