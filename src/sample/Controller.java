package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button btnKıtaOlumId;

    @FXML
    Button btnKıtaId;

    @FXML
    Button btn;

    @FXML
    TextField textfield;
    @FXML
    TableView<Record> tableView;

    @FXML
    TableColumn<Record, Integer> countriesAndTerritories;

    @FXML
    TableColumn<Record, Integer> cases;


    @FXML
    TableColumn<Record, Integer> New_Case;

    @FXML
    TableColumn<Record, Integer> Total_Deaths;

    @FXML
    TableColumn<Record, Integer> New_Deaths;


    @FXML
    TableColumn<Record, Integer> Pop_Data2018;


    @FXML
    TableColumn<Record, Integer> Mortality;

    @FXML
    TableColumn<Record, Integer> Attack_Rate;

    @FXML
    CategoryAxis cizgiX= new CategoryAxis();

    @FXML
    NumberAxis cizgiY= new NumberAxis();
    @FXML
    Button temizleBtn;

    @FXML
    LineChart<String, Number> lineChart = new LineChart<>(cizgiX,cizgiY);
    @FXML
    ChoiceBox<String> choiceCountry= new ChoiceBox<>();
    @FXML
    Button lineCases;
    @FXML
    Button listeBtn;

    @FXML
    CategoryAxis x = new CategoryAxis();

    @FXML
    NumberAxis y = new NumberAxis();

    @FXML
    StackedBarChart<String, Number> barChart;
    @FXML
    XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
    @FXML
    XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
    @FXML
    XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
    @FXML
    XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
    @FXML
    XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>();


    ObservableList<Record> data = FXCollections.observableArrayList();
    ObservableList<kıtalar> kıtalars = FXCollections.observableArrayList();


    public void initColumn() {
        countriesAndTerritories.setCellValueFactory(new PropertyValueFactory<>("countriesAndTerritories"));
        cases.setCellValueFactory(new PropertyValueFactory<>("cases"));
        New_Case.setCellValueFactory(new PropertyValueFactory<>("New_Case"));
        Total_Deaths.setCellValueFactory(new PropertyValueFactory<>("deaths"));
        New_Deaths.setCellValueFactory(new PropertyValueFactory<>("New_Deaths"));
        Mortality.setCellValueFactory(new PropertyValueFactory<>("Mortality"));
        Attack_Rate.setCellValueFactory(new PropertyValueFactory<>("Attack_Rate"));
        Pop_Data2018.setCellValueFactory(new PropertyValueFactory<>("Pop_Data2018"));

        tableView.setItems(data);

    }


    public void click() {
        try {
            String trmr = textfield.getText();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;

            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(trmr);
            NodeList countryList = doc.getElementsByTagName("record");
            initColumn();

            for (int i = 1; i < countryList.getLength(); i++) {
                Node p = countryList.item(i);


                Element country = (Element) p;


                String countriesAndTerritories = country.getElementsByTagName("countriesAndTerritories").item(0).getTextContent();
                String dateRep = country.getElementsByTagName("dateRep").item(0).getTextContent();
                int newCases = Integer.parseInt(country.getElementsByTagName("cases").item(0).getTextContent());
                int newDeaths = Integer.parseInt(country.getElementsByTagName("deaths").item(0).getTextContent());
                int cases = Integer.parseInt(country.getElementsByTagName("cases").item(0).getTextContent());
                int deaths = Integer.parseInt(country.getElementsByTagName("deaths").item(0).getTextContent());
                String geoId = country.getElementsByTagName("geoId").item(0).getTextContent();
                String Pop_Data2018 = country.getElementsByTagName("popData2018").item(0).getTextContent();
                String continentExp = country.getElementsByTagName("continentExp").item(0).getTextContent();


                int pop = 0;
                if (!Pop_Data2018.isEmpty()) {
                    pop = Integer.parseInt(Pop_Data2018);
                }


                if (!control(countriesAndTerritories)) {
                    data.add(new Record(countriesAndTerritories, cases, deaths, geoId, pop, continentExp, dateRep, newCases, newDeaths));

                    //System.out.println(newCases);
                    //System.out.println(newDeaths);
                }
                data.get(index(countriesAndTerritories)).HesapVakOl(cases, deaths);


            }


            for (int i = 0; i < data.size(); i++) {

                choiceCountry.getItems().add(data.get(i).countriesAndTerritories);


                if (data.get(i).cases != 0) {

                    double adeath = (data.get(i).deaths);
                    double bcases = (data.get(i).cases);
                    double mortality = adeath / bcases;
                    data.get(i).Mortality = mortality;


                }
                double bcases = (data.get(i).cases);
                int dPop = (data.get(i).Pop_Data2018);
                double attackRate = bcases / dPop;
                data.get(i).Attack_Rate = attackRate;


            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Veri alanı boş bırakılamaz");
        }

    }


    public void btnKıtavaka() {
        try {
            String trmr = textfield.getText();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;

            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(trmr);
            NodeList countryList = doc.getElementsByTagName("record");


            for (int i = 1; i < countryList.getLength(); i++) {
                Node p = countryList.item(i);


                Element country = (Element) p;

                int cases = Integer.parseInt(country.getElementsByTagName("cases").item(0).getTextContent());
                String contient = country.getElementsByTagName("continentExp").item(0).getTextContent();
                String countriesAndTerritories = country.getElementsByTagName("countriesAndTerritories").item(0).getTextContent();
                String dateRep = country.getElementsByTagName("dateRep").item(0).getTextContent();
                int deaths = Integer.parseInt(country.getElementsByTagName("deaths").item(0).getTextContent());

                kıtalars.add(new kıtalar(contient, cases, countriesAndTerritories, dateRep, deaths));


            }
            for (int j = 0; j < 20; j++) {
                x.getCategories().add(j, kıtalars.get(j).dateRep);
            }

           /*
            int AsyaToplam = 0;
            int AmericaToplam=0;
            int AvrupaToplam=0;
            int AfricaToplam=0;
            int AvusturalyaToplam=0;
            */
            tarihKontrolVaka();



                /*
                if (kıtalars.get(i).continentExp.contains("Asia")) {
                   AsyaToplam = AsyaToplam + kıtalars.get(i).cases;
                } else if (kıtalars.get(i).continentExp.contains("America")) {
                    AmericaToplam = AmericaToplam+kıtalars.get(i).cases;
                } else if (kıtalars.get(i).continentExp.contains("Europe")) {
                    AvrupaToplam = AvrupaToplam+kıtalars.get(i).cases;
                } else if (kıtalars.get(i).continentExp.contains("Oceania")) {
                    AvusturalyaToplam = AvusturalyaToplam+ kıtalars.get(i).cases;
                } else if (kıtalars.get(i).continentExp.contains("Africa")) {
                    AfricaToplam = AfricaToplam+ kıtalars.get(i).cases;
                }*/

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Veri alanı boş bırakılamaz");
        }


    }

    public boolean control(String countries) {
        boolean var = false;
        for (int i = 0; i < data.size(); i++) {
            if (countries.equals(data.get(i).countriesAndTerritories)) {
                return true;
            }
        }
        return var;
    }

    public boolean controlKıtalar(String countries) {
        boolean var = false;
        for (int i = 0; i < kıtalars.size(); i++) {
            if (countries.equals(kıtalars.get(i).countriesandTeritories)) {
                return true;
            }
        }
        return var;
    }


    public int index(String countries) {
        int idex = -1;
        for (int i = 0; i < data.size(); i++) {
            if (countries.equals(data.get(i).countriesAndTerritories))
                return i;
        }
        return idex;
    }

    public int indexKıtalar(String countries) {
        int idex = -1;
        for (int i = 0; i < kıtalars.size(); i++) {
            if (countries.equals(kıtalars.get(i).continentExp))
                return i;
        }
        return idex;
    }

    ArrayList<String> tarihler = new ArrayList<>();

    int[] dizi = new int[5];

    String[] diziKıtalar = {"Europe", "Asia", "Africa", "America", "Oceaine"};


//İse yaramadı
    public int indexKıtalarDeneme(String countries) {
        int idex = -1;
        int idexdeneme2 = 0;
        int idexdenem3 = 1;
        int idexdeneme4 ;

      //İse yaramadı silinecek
        return 1;
    }

    public void btnKıtaOlum() {
        try {
            String trmr = textfield.getText();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;

            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(trmr);
            NodeList countryList = doc.getElementsByTagName("record");


            for (int i = 1; i < countryList.getLength(); i++) {
                Node p = countryList.item(i);


                Element country = (Element) p;

                int cases = Integer.parseInt(country.getElementsByTagName("cases").item(0).getTextContent());
                String contient = country.getElementsByTagName("continentExp").item(0).getTextContent();
                String countriesAndTerritories = country.getElementsByTagName("countriesAndTerritories").item(0).getTextContent();
                String dateRep = country.getElementsByTagName("dateRep").item(0).getTextContent();
                int deaths = Integer.parseInt(country.getElementsByTagName("deaths").item(0).getTextContent());

                kıtalars.add(new kıtalar(contient, cases, countriesAndTerritories, dateRep, deaths));


            }
            tarihKonrtolOlum();


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Veri alanı boş bırakılamaz");
        }


    }

    int sayac = 0;

    public void tarihKontrolVaka() {

        for (int i = 0; i < kıtalars.size(); i++) {
            for (int j = 1; j < kıtalars.size(); j++) {


                if (kıtalars.get(i).dateRep.equals(kıtalars.get(j).dateRep) && i < j) {
                    if (kıtalars.get(i).continentExp.contains("Europe") && kıtalars.get(j).continentExp.contains("Europe")) {
                        if (kıtalars.get(i).Avrupa == 0) {
                            kıtalars.get(i).Avrupa = kıtalars.get(i).cases + kıtalars.get(j).cases;

                        } else {
                            kıtalars.get(i).Avrupa += kıtalars.get(j).cases;


                        }
                    } else if (kıtalars.get(i).continentExp.contains("Asia") && kıtalars.get(j).continentExp.contains("Asia")) {
                        if (kıtalars.get(i).Asya == 0) {
                            kıtalars.get(i).Asya = kıtalars.get(i).cases + kıtalars.get(j).cases;
                        } else {
                            kıtalars.get(i).Asya += kıtalars.get(j).cases;

                        }

                    } else if (kıtalars.get(i).continentExp.contains("America") && kıtalars.get(j).continentExp.contains("America")) {
                        if (kıtalars.get(i).Amerika == 0) {
                            kıtalars.get(i).Amerika = kıtalars.get(i).cases + kıtalars.get(j).cases;
                        } else {
                            kıtalars.get(i).Amerika += kıtalars.get(j).cases;


                        }

                    } else if (kıtalars.get(i).continentExp.contains("Africa") && kıtalars.get(j).continentExp.contains("Africa")) {
                        if (kıtalars.get(i).Afrika == 0) {
                            kıtalars.get(i).Afrika = kıtalars.get(i).cases + kıtalars.get(j).cases;
                        } else {
                            kıtalars.get(i).Afrika += kıtalars.get(j).cases;


                        }

                    } else if (kıtalars.get(i).continentExp.contains("Oceania") && kıtalars.get(j).continentExp.contains("Oceania")) {
                        if (kıtalars.get(i).Avusturalya == 0) {
                            kıtalars.get(i).Avusturalya = kıtalars.get(i).cases + kıtalars.get(j).cases;
                        } else {
                            kıtalars.get(i).Avusturalya += kıtalars.get(j).cases;

                        }

                    }

                }
            }

            // if ((sayacAfrika>0)&&(sayacAmerika>0)&&(sayacAsya>0)&&(sayacAvrupa>0)&&(sayacAvusturalya>0))
            //    break;
        }
        int sayacavrupa = 0;
        int sayacasya = 0;
        int sayacafrika = 0;
        int sayacamerika = 0;
        int sayacavusturallya = 0;
        series1.setName("Europe");
        series2.setName("Asia");
        series3.setName("Africa");
        series4.setName("America");
        series5.setName("Oceania");
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();


        for (int i = 0; i < kıtalars.size(); i++) {


          /*   if (kıtalars.get(i).Avrupa != 0 && sayac < 20) {
                System.out.println(kıtalars.get(i).dateRep);
                System.out.println(kıtalars.get(i).Avrupa);
                sayac++;
            }*/


            if (kıtalars.get(i).Avrupa != 0 && sayacavrupa <= 20) {

                series1.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Avrupa));
                sayacavrupa++;
            }
            if ((kıtalars.get(i).Asya != 0) && (sayacasya <= 20)) {

                series2.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Asya));
                sayacasya++;
            }
            if ((kıtalars.get(i).Afrika != 0) && (sayacafrika <= 20)) {

                series3.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Afrika));
                sayacafrika++;
            }
            if ((kıtalars.get(i).Amerika != 0) && (sayacamerika <= 20)) {

                series4.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Amerika));
                sayacamerika++;
            }
            if ((kıtalars.get(i).Avusturalya != 0) && (sayacavusturallya <= 20)) {

                series5.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Avusturalya));
                sayacavusturallya++;
            }

        }


        barChart.getData().addAll(series1, series2, series3, series4, series5);


        ObservableList<String> tarihler = FXCollections.observableArrayList();

        for (int i = 20; i >= 0; i--) {
            tarihler.add(kıtalars.get(i).dateRep);
        }
        x.setCategories(tarihler);

    }
    /*
    public void tarihKonrtolOlum() {


        for (int i = 0; i < kıtalars.size(); i++) {
            for (int j = 1; j < kıtalars.size(); j++) {


                if (kıtalars.get(i).dateRep.equals(kıtalars.get(j).dateRep) && i < j) {
                    if (kıtalars.get(i).continentExp.contains("Europe") && kıtalars.get(j).continentExp.contains("Europe")) {
                        if (kıtalars.get(i).Avrupa == 0) {
                            kıtalars.get(i).Avrupa = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;

                        } else {
                            kıtalars.get(i).Avrupa += kıtalars.get(j).Deaths;


                        }
                    } else if (kıtalars.get(i).continentExp.contains("Asia") && kıtalars.get(j).continentExp.contains("Asia")) {
                        if (kıtalars.get(i).Asya == 0) {
                            kıtalars.get(i).Asya = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Asya += kıtalars.get(j).Deaths;

                        }

                    } else if (kıtalars.get(i).continentExp.contains("America") && kıtalars.get(j).continentExp.contains("America")) {
                        if (kıtalars.get(i).Amerika == 0) {
                            kıtalars.get(i).Amerika = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Amerika += kıtalars.get(j).Deaths;


                        }

                    } else if (kıtalars.get(i).continentExp.contains("Africa") && kıtalars.get(j).continentExp.contains("Africa")) {
                        if (kıtalars.get(i).Afrika == 0) {
                            kıtalars.get(i).Afrika = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Afrika += kıtalars.get(j).Deaths;


                        }

                    } else if (kıtalars.get(i).continentExp.contains("Oceania") && kıtalars.get(j).continentExp.contains("Oceania")) {
                        if (kıtalars.get(i).Avusturalya == 0) {
                            kıtalars.get(i).Avusturalya = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Avusturalya += kıtalars.get(j).Deaths;

                        }

                    }

                }
            }

        }
        int sayacavrupa = 0;
        int sayacasya = 0;
        int sayacafrika = 0;
        int sayacamerika = 0;
        int sayacavusturallya = 0;
        series1.setName("Europe");
        series2.setName("Asia");
        series3.setName("Africa");
        series4.setName("America");
        series5.setName("Oceania");
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();


        for (int i = 0; i < kıtalars.size(); i++) {

             if (kıtalars.get(i).Avrupa != 0 && sayac < 20) {
                System.out.println(kıtalars.get(i).dateRep);
                System.out.println(kıtalars.get(i).Avrupa);
                sayac++;
            }


            if (kıtalars.get(i).Avrupa != 0 && sayacavrupa <= 20) {

                series1.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Avrupa));
                sayacavrupa++;
            }
            if ((kıtalars.get(i).Asya != 0) && (sayacasya <= 20)) {

                series2.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Asya));
                sayacasya++;
            }
            if ((kıtalars.get(i).Afrika != 0) && (sayacafrika <= 20)) {

                series3.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Afrika));
                sayacafrika++;
            }
            if ((kıtalars.get(i).Amerika != 0) && (sayacamerika <= 20)) {

                series4.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Amerika));
                sayacamerika++;
            }
            if ((kıtalars.get(i).Avusturalya != 0) && (sayacavusturallya <= 20)) {

                series5.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Avusturalya));
                sayacavusturallya++;
            }

        }


        barChart.getData().addAll(series1, series2, series3, series4, series5);


        ObservableList<String> tarihler = FXCollections.observableArrayList();
     stackbar ın "x" axisindeki verileri listeye atmak ve onları güncel tarihler yapmak
        for (int i = 20; i >= 0; i--) {
            tarihler.add(kıtalars.get(i).dateRep);
        }
        x.setCategories(tarihler);


    }*/

    public void tarihKonrtolOlum() {


        for (int i = 0; i < kıtalars.size(); i++) {
            for (int j = 1; j < kıtalars.size(); j++) {


                if (kıtalars.get(i).dateRep.equals(kıtalars.get(j).dateRep) && i < j) {
                    if (kıtalars.get(i).continentExp.contains("Europe") && kıtalars.get(j).continentExp.contains("Europe")) {
                        if (kıtalars.get(i).Avrupa == 0) {
                            kıtalars.get(i).Avrupa = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;

                        } else {
                            kıtalars.get(i).Avrupa += kıtalars.get(j).Deaths;


                        }
                    } else if (kıtalars.get(i).continentExp.contains("Asia") && kıtalars.get(j).continentExp.contains("Asia")) {
                        if (kıtalars.get(i).Asya == 0) {
                            kıtalars.get(i).Asya = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Asya += kıtalars.get(j).Deaths;

                        }

                    } else if (kıtalars.get(i).continentExp.contains("America") && kıtalars.get(j).continentExp.contains("America")) {
                        if (kıtalars.get(i).Amerika == 0) {
                            kıtalars.get(i).Amerika = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Amerika += kıtalars.get(j).Deaths;


                        }

                    } else if (kıtalars.get(i).continentExp.contains("Africa") && kıtalars.get(j).continentExp.contains("Africa")) {
                        if (kıtalars.get(i).Afrika == 0) {
                            kıtalars.get(i).Afrika = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Afrika += kıtalars.get(j).Deaths;


                        }

                    } else if (kıtalars.get(i).continentExp.contains("Oceania") && kıtalars.get(j).continentExp.contains("Oceania")) {
                        if (kıtalars.get(i).Avusturalya == 0) {
                            kıtalars.get(i).Avusturalya = kıtalars.get(i).Deaths + kıtalars.get(j).Deaths;
                        } else {
                            kıtalars.get(i).Avusturalya += kıtalars.get(j).Deaths;

                        }

                    }

                }
            }

        }
        int sayacavrupa = 0;
        int sayacasya = 0;
        int sayacafrika = 0;
        int sayacamerika = 0;
        int sayacavusturallya = 0;
        series1.setName("Europe");
        series2.setName("Asia");
        series3.setName("Africa");
        series4.setName("America");
        series5.setName("Oceania");
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();


        for (int i = 0; i < kıtalars.size(); i++) {

          /*   if (kıtalars.get(i).Avrupa != 0 && sayac < 20) {
                System.out.println(kıtalars.get(i).dateRep);
                System.out.println(kıtalars.get(i).Avrupa);
                sayac++;
            }*/


            if (kıtalars.get(i).Avrupa != 0 && sayacavrupa <= 20) {

                series1.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Avrupa));
                sayacavrupa++;
            }
            if ((kıtalars.get(i).Asya != 0) && (sayacasya <= 20)) {

                series2.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Asya));
                sayacasya++;
            }
            if ((kıtalars.get(i).Afrika != 0) && (sayacafrika <= 20)) {

                series3.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Afrika));
                sayacafrika++;
            }
            if ((kıtalars.get(i).Amerika != 0) && (sayacamerika <= 20)) {

                series4.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Amerika));
                sayacamerika++;
            }
            if ((kıtalars.get(i).Avusturalya != 0) && (sayacavusturallya <= 20)) {

                series5.getData().add(new XYChart.Data<String, Number>(kıtalars.get(i).dateRep, kıtalars.get(i).Avusturalya));
                sayacavusturallya++;
            }

        }

//
        barChart.getData().addAll(series1, series2, series3, series4, series5);


        ObservableList<String> tarihler = FXCollections.observableArrayList();
//stackbar ın "x" axisindeki verileri listeye atmak ve onları güncel tarihler yapmak
        for (int i = 20; i >= 0; i--) {
            tarihler.add(kıtalars.get(i).dateRep);
        }
        x.setCategories(tarihler);


    }
    //oldu sanırım
    public void initColumnDeneme() {
        countriesAndTerritories.setCellValueFactory(new PropertyValueFactory<>("countriesAndTerritories"));
        cases.setCellValueFactory(new PropertyValueFactory<>("cases"));
        New_Case.setCellValueFactory(new PropertyValueFactory<>("New_Case"));
        Total_Deaths.setCellValueFactory(new PropertyValueFactory<>("deaths"));
        New_Deaths.setCellValueFactory(new PropertyValueFactory<>("New_Deaths"));
        Mortality.setCellValueFactory(new PropertyValueFactory<>("Mortality"));
        Attack_Rate.setCellValueFactory(new PropertyValueFactory<>("Attack_Rate"));
        Pop_Data2018.setCellValueFactory(new PropertyValueFactory<>("Pop_Data2018"));

    }
//stackbar ı temizlemye yarayan click
    public void temizleBtn() {
        barChart.getData().clear();
    }
    //Denemeler Düzenlenecek
    public boolean controlKıtalarDeneme(String geoId) {
        boolean var = true;
        for (int i = 0; i < data.size(); i++) {
            if (geoId.equals(data.get(i).geoId)) {
                return false;
            }
        }
        return var;
    }
//gelen xml verisindeki ülkeleri listeye(choicebox) çeviren click
    public void listeClick() {


    }
    ObservableList<String> tarihDizi= FXCollections.observableArrayList();
    public void lineData(){
        String Secilen=choiceCountry.getSelectionModel().getSelectedItem();







        for (int i = 20; i > 0; i--) {
            tarihDizi.add(kıtalars.get(i).dateRep);
        }
        cizgiX.setCategories(tarihDizi);


    }
//Düzeltilecek daha çok şey var
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
