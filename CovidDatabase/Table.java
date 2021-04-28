package CovidDatabase;

public class Table {
    private final String name;
    private String age;
    private String tower;
    private String status;
    private String reportingDate;
    private String recoveryDate;

    public Table(String name, String age, String tower, String status, String reportingDate, String recoveryDate) {
        this.name = name;
        this.age = age;
        this.tower = tower;
        this.status = status;
        this.reportingDate = reportingDate;
        this.recoveryDate = recoveryDate;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(String reportingDate) {
        this.reportingDate = reportingDate;
    }

    public String getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(String recoveryDate) {
        this.recoveryDate = recoveryDate;
    }
}
