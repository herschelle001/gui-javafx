package CovidDatabase;

import java.util.Calendar;
import java.util.Date;

public class Patient {
    private final String name;
    private int age;
    private char tower;
    private Date reportingDate;
    private Date recoveryDate;

    public Patient(String name, int age, char tower, Date reportingDate) {
        this.name = name;
        this.age = age;
        this.tower = tower;
        this.reportingDate = reportingDate;

        Calendar c = Calendar.getInstance();
        c.setTime(reportingDate);
        c.add(Calendar.DATE, 22);
        recoveryDate = c.getTime();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getTower() {
        return tower;
    }

    public void setTower(char tower) {
        this.tower = tower;
    }

    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public Date getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }
}
