package sample;

public class kıtalar {

    int Asya;
    int Avrupa;
    int Amerika;
    int Afrika;
    int Avusturalya;
    String continentExp;
    int cases;
    String countriesandTeritories;
    String dateRep;
    int Deaths;

    public String getdateRep() {
        return dateRep;
    }

    public void setdateRep(String dateRep) {
        this.dateRep = dateRep;
    }


    public String countriesandTeritories() {
        return countriesandTeritories;
    }

    public void countriesandTeritories(String countriesandTeritories) {
        this.countriesandTeritories = countriesandTeritories;
    }

    public String getContient() {
        return continentExp;
    }

    public void setContient(String continentExp) {
        this.continentExp = continentExp;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getAsya() {
        return Asya;
    }

    public void setAsya(int asya) {
        Asya = asya;
    }

    public int getAvrupa() {
        return Avrupa;
    }

    public void setAvrupa(int avrupa) {
        Avrupa = avrupa;
    }

    public int getAmerika() {
        return Amerika;
    }

    public void setAmerika(int amerika) {
        Amerika = amerika;
    }

    public int getAfrika() {
        return Afrika;
    }

    public void setAfrika(int afrika) {
        Afrika = afrika;
    }

    public int getAvusturalya() {
        return Avusturalya;
    }

    public void setAvusturalya(int avusturalya) {
        Avusturalya = avusturalya;
    }

    public int getDeaths() {
        return Deaths;
    }

    public void setDeaths(int deaths) {
        this.Deaths = deaths;
    }

    public kıtalar(String continentExp, int cases, String countriesandTeritories, String dateRep, int deaths) {
        this.continentExp = continentExp;
        this.cases = cases;
        this.countriesandTeritories = countriesandTeritories;
        this.dateRep=dateRep;
        this.Deaths=deaths;
    }


    public void HesapCase(int vaka) {
        this.cases += vaka;

    }

    public void vakaEkle(int vaka,int olum ,String date){

    }
}
