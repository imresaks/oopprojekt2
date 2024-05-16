package org.proj2.oopprojekt2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OOP Projekt 2");

        VBox root = new VBox();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Väike ettevõtja tööriist");
        dialog.setHeaderText("Millega on sul täna abi vaja?\nVali h/r/m või exit");
        dialog.setContentText("h - hinnaelastsuse arvutamine\nr - palgakulu leidmine ühe töötaja kohta\nm - motivatsiooni probleemid");

        dialog.showAndWait().ifPresent(this::otsustaTegevus); //klassi optional ja meetodit otsustaTegevus kasutades ootab, et kasutaja sisestaks midagi

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        //primaryStage.show();
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
                showErrorAlert("Halb sisend", "Vali üks järgnevatest valikutest: h/r/m/exit");
                break;
        }
    }

    private void motivatsiooniprobleemid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(true);
        alert.setWidth(500);
        alert.setHeight(500);
        alert.setTitle("Motivatsiooniprobleemid");
        alert.setHeaderText(null);
        alert.setContentText("Tundub, et sul on probleeme motivatsiooniga.\nÄra muretse, meie programm aitab sind taas tööle saada!");

        try {
            Thread.sleep(2000); //sleep 2s et näeks naturaalsem välja
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Random rnd = new Random();
        int msgIndeks = rnd.nextInt(100);
        alert.setContentText("Mõtteainet sulle:\n" + loeSõnumit("inspmsg.txt", msgIndeks));
        alert.showAndWait();
    }

    private String loeSõnumit(String fileName, int msgIndeks) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            String rida;
            int käesolevRida = 0;
            while ((rida = br.readLine()) != null) {
                if (käesolevRida == msgIndeks) {
                    return rida;
                }
                käesolevRida++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void ressursideOptimiseerimine() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setTitle("Ressurside optimiseerimine");

        VBox root = new VBox();
        root.setSpacing(7);

        Label label1 = new Label("Palju sul oli aasta tagasi töötajaid?");
        TextField textField1 = new TextField();

        Label label2 = new Label("Palju oli sul aasta tagasi kapitali (masinad, töövahendid jne)?");
        TextField textField2 = new TextField();

        Label label3 = new Label("Palju on sul praegu töötajaid?");
        TextField textField3 = new TextField();

        Label label4 = new Label("Palju on sul praegu kapitali (kogus ühikutes)?");
        TextField textField4 = new TextField();

        Label label5 = new Label("Palju muutus sinu tootmiskogus võrreldes eelmise aastaga (koguse muutus ühikutes)?");
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


            // Siin saame kasutada ValemidPalkKogukulu klassi arvutuste tegemiseks
            // Loome objekti ja anname sisendid konstruktorile
            ValemidPalkKogukulu ressurss = new ValemidPalkKogukulu(Deltaq, K1, K2, L1, L2, r); // Määrame 0-deltaQ ja 0-r, kuna neid sisendeid me ei küsi
            // Kuvame tulemused
            showErrorAlert("Tulemus", "Palgakulu peaks olema " + ressurss.palk() + " optimaalse tootmise juures.\nSinu ettevõtte kogukulu optimaalse tootmise juures peaks olema: " + ressurss.kogukulu());
        });

        root.getChildren().addAll(label1, textField1, label2, textField2, label3, textField3, label4, textField4, label5, textField5, label6, textField6,calculateButton);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();

    }

    private void hinnaMuutmine() {

            Stage stage = new Stage();
            stage.setTitle("Hinna muutmine");

            VBox root = new VBox();
            root.setSpacing(7);

            Label label1 = new Label("Sisesta, mis oli su toote/teenuse hind enne hinna muutmist?");
            TextField textField1 = new TextField();

            Label label2 = new Label("Sisesta, mis oli su toote/teenuse hind peale hinna muutmist?");
            TextField textField2 = new TextField();

            Label label3 = new Label("Palju müüsid esialgse hinna juures (kogus ühikutes)?");
            TextField textField3 = new TextField();

            Label label4 = new Label("Palju müüsid muudetud hinna juures (kogus ühikutest)?");
            TextField textField4 = new TextField();

            Button calculateButton = new Button("Arvuta");
            calculateButton.setOnAction(event -> {
                // Võtame sisendi tekstiväljadest
                double H1 = Double.parseDouble(textField1.getText());
                double H2 = Double.parseDouble(textField2.getText());
                double Q1 = Double.parseDouble(textField3.getText());
                double Q2 = Double.parseDouble(textField4.getText());

                // Siin saame kasutada ValemidElastsus klassi arvutuste tegemiseks
                // Loome objekti ja anname sisendid konstruktorile
                ValemidElastsus andmed = new ValemidElastsus(H1, H2, Q1, Q2);
                andmed.hinnaelastsus(H1,H2, Q1, Q2);
                // Kuvame tulemused
                //showErrorAlert("Tulemus", "Sinu ettevõtte käibe muutus oli " + andmed.käibeMuutus() + "%");
            });

            root.getChildren().addAll(label1, textField1, label2, textField2, label3, textField3, label4, textField4, calculateButton);

            Scene scene = new Scene(root, 400, 300);
            stage.setScene(scene);
            stage.show();
        }


    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
