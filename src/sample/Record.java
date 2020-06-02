package sample;


public class Record {

    String countriesAndTerritories;
    String dateRep;
    int cases;
    int New_Case;
    int deaths;
    int New_Deaths;
    double Mortality;
    double Attack_Rate;
    String geoId;
    int Pop_Data2018;
    String contient;


    public Record(String countriesAndTerritories, int cases, int deaths, String geoId, int Pop_Data2018, String contient,String dateRep,int new_Case,int new_Deaths) {
        this.countriesAndTerritories = countriesAndTerritories;
        this.cases = cases;
        this.deaths = deaths;
        this.geoId = geoId;
        this.Pop_Data2018 = Pop_Data2018;
        this.contient = contient;
        this.dateRep=dateRep;
        this.New_Case=new_Case;
        this.New_Deaths=new_Deaths;


    }

    public String getCountriesAndTerritories() {
        return countriesAndTerritories;
    }

    public void setCountriesAndTerritories(String countriesAndTerritories) {
        this.countriesAndTerritories = countriesAndTerritories;
    }

    public String getDate() {
        return dateRep;
    }

    public void setDate(String dateRep) {
        this.dateRep = dateRep;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }


    public int getPop_Data2018() {
        return Pop_Data2018;
    }

    public void setPop_Data2018(int Pop_Data2018) {
        this.Pop_Data2018 = Pop_Data2018;
    }


    public double getMortality() {
        return Mortality;
    }

    public void setMortality(double mortality) {
        Mortality = mortality;
    }

    public double getAttack_Rate() {
        return Attack_Rate;
    }

    public void setAttack_Rate(double attack_Rate) {
        Attack_Rate = attack_Rate;
    }

    public int getNew_Case() {
        return New_Case;
    }

    public void setNew_Case(int new_Case) {
        New_Case = new_Case;
    }

    public int getNew_Deaths() {
        return New_Deaths;
    }

    public void setNew_Deaths(int new_Deaths) {
        New_Deaths = new_Deaths;
    }

    public String getContient() {
        return contient;
    }

    public void setContient(String contient) {
        this.contient = contient;
    }

    public void HesapVakOl(int vaka, int olum) {
        this.cases += vaka;
        this.deaths += olum;


    }


}
