package CovidDatabase;

public class TowerTable {
    private String tower;
    private String active;
    private String recovered;

    public TowerTable(String tower, String active, String recovered) {
        this.tower = tower;
        this.active = active;
        this.recovered = recovered;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
