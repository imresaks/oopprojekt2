package org.proj2.oopprojekt2;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ValemidElastsus {
    private double H1;
    private double H2;
    private double Q1;
    private double Q2;

    public ValemidElastsus(double h1, double h2, double q1, double q2) {
        H1 = h1;
        H2 = h2;
        Q1 = q1;
        Q2 = q2;
    }

    public double getH1() {
        return H1;
    }

    public void setH1(double h1) {
        H1 = h1;
    }

    public double getH2() {
        return H2;
    }

    public void setH2(double h2) {
        H2 = h2;
    }

    public double getQ1() {
        return Q1;
    }

    public void setQ1(double q1) {
        Q1 = q1;
    }

    public double getQ2() {
        return Q2;
    }

    public void setQ2(double q2) {
        Q2 = q2;
    }

    public void hinnaelastsus(double H1, double H2, double Q1, double Q2) {
        double elastsus1 = ((Q2 - Q1) / Q1) / ((H2 - H1) / H1);
        double elastsus = (double) Math.round(elastsus1 * 10000) / 10000;
        if (elastsus < 0) elastsus = elastsus * -1;

        String message;
        if (H2 > H1) {
            if (elastsus == 1) {
                message = "Sinu toote/teenuse hinnaelastsus on " + elastsus + ", ehk on ühikelastne.\nSee tähendab, et kui tõstsid hinda, siis kogus muutub täpselt samas proportsioonis, aga vastassuunas, sama kehtib hinda alandades\nSoovitame hinnakujunduse pidevat jälgimist ja kohandamist vastavalt turuolukorrale!";
            } else if (elastsus < 1) {
                message = "Sinu toote/teenuse hinnaelastsus on " + elastsus + ", ehk on mitte elastne.\nSee tähendab, et sinu toode/teenus pole nii tundlik hinnamuutusele\nSoovitame sul jätkata oma praguse hinnatõusu strateegiaga, aga mõistlikuse piires!\nVõiks ka kaaluda kvaliteedi või lisaväärtuse suurendamist veelgi, et õigustada kõrgemat hinda ja suurendada kasumit";
            } else {
                message = "Sinu toote/teenuse hinnaelastsus on " + elastsus + ", ehk on elastne.\nSee tähendab, et sinu toode/teenus on üsna tundlik hinnamuutusele\nSoovitame sul hinda pigem mitte tõsta, sest nii väheneb su müügikogus\nVõiks ka kaaluda oma toote/teenuse eristamist teistest konkurentidest, et tarbija kohe hinna väiksemagi tõusu juures teise odavama tootja juurde üle ei läheks";
            }
        } else {
            if (elastsus == 1) {
                message = "Sinu toote/teenuse hinnaelastsus on " + elastsus + ", ehk on ühikelastne.\nSee tähendab, et kui muutsid hinda odavamaks, siis kogus muutub täpselt samas proportsioonis, aga vastassuunas, sama kehtib hinda tõstes\nSoovitame hinnakujunduse pidevat jälgimist ja kohandamist vastavalt turuolukorrale!";
            } else if (elastsus < 1) {
                message = "Sinu toote/teenuse hinnaelastsus on " + elastsus + ", ehk on mitte elastne.\nSee tähendab, et sinu toode/teenus pole nii tundlik hinnamuutusele\nSoovitame sul hinda pigem tõsta, aga mõistlikuse piires!\nVõiks ka kaaluda kvaliteedi või lisaväärtuse suurendamist, et õigustada kõrgemat hinda ja suurendada kasumit";
            } else {
                message = "Sinu toote/teenuse hinnaelastsus on " + elastsus + ", ehk on elastne.\nSee tähendab, et sinu toode/teenus on üsna tundlik hinnamuutusele\nSoovitame sul jätkata praguse strateegiaga, et pigem hinda alanadada, sest nii suureneb su müügikogus\nVõiks ka kaaluda oma toote/teenuse eristamist teistest konkurentidest, et ei peaks kõige odavamat toodet turul hoidma. See võib küll koguseid suurendada, aga brändi kuvand jääb pigem odav ja mittekvaliteetne";
            }
        }
        message += System.lineSeparator() + "Sinu ettevõtte käibe muutus oli " + käibeMuutus() + "%";
        // Kuva sõnum
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setResizable(true);
        alert.setContentText(message);
        alert.setWidth(900);
        alert.setHeight(800);
        alert.showAndWait();

    }
    public double käibeMuutus(){
        return (double) Math.round((Q2*H2*100)/(Q1*H1)*100)/100;
    }

    @Override
    public String toString() {
        return "Esialgne hind = " + H1 +
                ", hind pärast muutmist = " + H2 +
                ", esialgne kogus = " + Q1 +
                ", kogus pärast hinna muutmist = " + Q2 +
                ", käibemuutus = " + käibeMuutus() + "%.";
    }
}
