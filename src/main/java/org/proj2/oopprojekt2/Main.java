package org.proj2.oopprojekt2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OOP Projekt 2");

        VBox root = new VBox();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Tervitus");
        dialog.setHeaderText("Tere tulemast!");
        dialog.setContentText("Millega on sul täna abi vaja?\nVali h/r/m exit");

        dialog.showAndWait().ifPresent(this::otsustaTegevus);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
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
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
        // Implement ressursideOptimiseerimine() here
    }

    private void hinnaMuutmine() {
        // Implement hinnaMuutmine() here
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
