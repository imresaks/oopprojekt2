package org.proj2.oopprojekt2;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OOP Projekt 2");

        GridPane root = new GridPane();


        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Väikeettevõtja tööriist");
        dialog.setHeaderText("Millega on sul täna abi vaja?\nVali h/r/m või exit");
        dialog.setContentText("h - hinnaelastsuse arvutamine\nr - palgakulu leidmine ühe töötaja kohta\n    (optimaalse tootmise juures)\nm - motivatsiooni probleemid");
        dialog.setResizable(true);
        dialog.showAndWait().ifPresent(this::otsustaTegevus); //klassi optional ja meetodit otsustaTegevus kasutades ootab, et kasutaja sisestaks midagi

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);

    }

    private void otsustaTegevus(String sisend) {
        switch (sisend) {
            case "h":
                hinnaMuutmine();
                break;
            case "r":
                ressursideOptimiseerimine();
                break;
            case "m":
                motivatsiooniprobleemid();
                break;
            case "exit":
                Platform.exit();
                break;
            default:
                viskaErroriTeavitus("Halb sisend", "Vali üks järgnevatest valikutest: h/r/m/exit");
                start(new Stage());
                break;
        }
    }

    private void motivatsiooniprobleemid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Motivatsiooniprobleemid");
        alert.setHeaderText(null);

        Random rnd = new Random();
        int msgIndeks = rnd.nextInt(100);
        alert.setContentText("Mõtteainet sulle:" + System.lineSeparator() + loeSõnumit("inspmsg.txt", msgIndeks));
        alert.setResizable(true);
        alert.setWidth(500);
        alert.setHeight(500);
        alert.showAndWait();
        //kutsume uuesti välja start meetodi
        start(new Stage());
    }

    private String loeSõnumit(String fileName, int msgIndeks) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String rida;
            int käesolevRida = 0;
            while ((rida = br.readLine()) != null) {
                if (käesolevRida == msgIndeks) {
                    return rida;
                }
                käesolevRida++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void ressursideOptimiseerimine() {
        Stage stage = new Stage();
        stage.setTitle("Ressurside optimiseerimine");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        stage.setWidth(550);
        stage.setHeight(320);

        Label label1 = new Label("Palju sul oli aasta tagasi töötajaid?");
        TextField textField1 = new TextField();

        Label label2 = new Label("Palju oli sul aasta tagasi kapitali (masinad, töövahendid jne)?");
        TextField textField2 = new TextField();

        Label label3 = new Label("Palju on sul praegu töötajaid?");
        TextField textField3 = new TextField();

        Label label4 = new Label("Palju on sul praegu kapitali (kogus ühikutes)?");
        TextField textField4 = new TextField();

        Label label5 = new Label("Palju muutus sinu tootmiskogus võrreldes eelmise aastaga \n(koguse muutus ühikutes)?");
        TextField textField5 = new TextField();

        Label label6 = new Label("Palju maksab praegu üks ühik kapitali?");
        TextField textField6 = new TextField();

        Button calculateButton = new Button("Arvuta");
        calculateButton.setOnAction(event -> {
            // Võtame sisendi tekstiväljadest
            int L1 = Integer.parseInt(textField1.getText());
            int K1 = Integer.parseInt(textField2.getText());
            int L2 = Integer.parseInt(textField3.getText());
            int K2 = Integer.parseInt(textField4.getText());
            int Deltaq = Integer.parseInt(textField5.getText());
            int r = Integer.parseInt(textField6.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tulemused");
            alert.setHeaderText(null);
            // Siin saame kasutada ValemidPalkKogukulu klassi arvutuste tegemiseks
            // Loome objekti ja anname sisendid konstruktorile
            ValemidPalkKogukulu ressurss = new ValemidPalkKogukulu(Deltaq, K1, K2, L1, L2, r);
            // Kuvame tulemused
            alert.setWidth(600);
            alert.setHeight(500);
            alert.setContentText("Palgakulu peaks olema " + Math.round(ressurss.palk() * 10000.0) / 10000.0 + " optimaalse tootmise juures.\nSinu ettevõtte kogukulu optimaalse tootmise juures peaks olema: \n" + Math.round(ressurss.kogukulu() * 100.0) / 100.0);
            alert.setResizable(true);
            alert.showAndWait();



            //sulgeme eelmise stseeni, et see ette ei jääks
            stage.close();
            //kutsume uuesti start meetodi välja
            start(new Stage());

        });
        root.addRow(0, label1, textField1);
        root.addRow(1, label2, textField2);
        root.addRow(2, label3, textField3);
        root.addRow(3, label4, textField4);
        root.addRow(4, label5, textField5);
        root.addRow(5, label6, textField6);
        root.add(calculateButton, 0, 6, 2, 1);

        Scene scene = new Scene(root, 550, 320);
        stage.setScene(scene);
        stage.show();
    }

    private void hinnaMuutmine() {
        Stage stage = new Stage();
        stage.setTitle("Hinna muutmine");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        stage.setWidth(550);
        stage.setHeight(250);

        Label label1 = new Label("Sisesta, mis oli su toote/teenuse hind enne hinna muutmist?");
        TextField textField1 = new TextField();

        Label label2 = new Label("Sisesta, mis oli su toote/teenuse hind peale hinna muutmist?");
        TextField textField2 = new TextField();

        Label label3 = new Label("Palju müüsid esialgse hinna juures (kogus ühikutes)?");
        TextField textField3 = new TextField();

        Label label4 = new Label("Palju müüsid muudetud hinna juures (kogus ühikutest)?");
        TextField textField4 = new TextField();
        Label label5 = new Label("Mis on su firma nimi (jäta tühjaks kui ei soovi andmeid salvestada)");
        TextField textField5 = new TextField();

        Button calculateButton = new Button("Arvuta");
        calculateButton.setOnAction(event -> {
            // Võtame sisendi tekstiväljadest
            double H1 = Double.parseDouble(textField1.getText());
            double H2 = Double.parseDouble(textField2.getText());
            double Q1 = Double.parseDouble(textField3.getText());
            double Q2 = Double.parseDouble(textField4.getText());
            String firmanimi = textField5.getText();

            // Siin saame kasutada ValemidElastsus klassi arvutuste tegemiseks
            // Loome objekti ja anname sisendid konstruktorile
            ValemidElastsus andmed = new ValemidElastsus(H1, H2, Q1, Q2);
            andmed.hinnaelastsus(H1,H2, Q1, Q2);
            if (!firmanimi.isEmpty()) {
                try {
                    failiTrükk(firmanimi, andmed, "firmaAndmed.txt");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            stage.setResizable(true);
            // Kuvame tulemused
            //sulgeme eelmise stseeni, et see ette ei jääks
            stage.close();
            //kutsume uuesti start meetodi välja
            start(new Stage());

        });

        root.addRow(0, label1, textField1);
        root.addRow(1, label2, textField2);
        root.addRow(2, label3, textField3);
        root.addRow(3, label4, textField4);
        root.addRow(4, label5, textField5);
        root.add(calculateButton, 0, 5, 2, 1);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }


    private void viskaErroriTeavitus(String pealkiri, String sisu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(pealkiri);
        alert.setHeaderText(null);
        alert.setContentText(sisu);
        alert.showAndWait(); //ootab et kasutaja vajutaks ok
    }
    public static void failiTrükk(String firmanimi, Object obj, String failinimi) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(failinimi, true), StandardCharsets.UTF_8))) {
            bw.write(firmanimi + ": " + obj.toString() + System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
