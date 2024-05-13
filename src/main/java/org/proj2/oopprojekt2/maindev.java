///**
// * Objektorienteeritud programmeerimine. Rühmatöö nr 1.
// * Autorid: Imre Saks, Hannah Kaarma
// */
//package org.proj2.oopprojekt2;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Random;
//import java.util.Scanner;
//
//public class maindev {
//    public static void main(String[] args) {
//        //System.out.println(valemid.piirkuluKapital(100, 5, 8));
//        algus();
//    }
//
//    private static void algus() {
//        Scanner obj = new Scanner(System.in);
//        boolean jätka=true;
//        while(jätka) {
//            System.out.println("----------------------------------");
//            System.out.println("Millega on sul täna abi vaja?");
//            System.out.println("Hinna muutmise otsuste analüüsimine (h)");
//            System.out.println("Tööjõu palga ja kogu kulu leidmine optimaalse tootmise korral (r)");
//            System.out.println("Motivatsiooniprobleemid (m)");
//            System.out.println("Programmi lõpetamiseks sisesta 'exit'");
//            System.out.print("Kirjuta valik siia: ");
//            String sisend = obj.nextLine();
//            if ((sisend.equals("exit"))){
//                jätka=false;
//            }
//            else{
//                otsustaTegevus(sisend);}}
//    }
//
//    private static void otsustaTegevus(String sisend) {
//        switch (sisend) {
//            case "h":
//                hinnaMuutmine();
//                break;
//            case "r":
//                ressursideOptimiseerimine();
//                break;
//            case "m":
//                motivatsiooniprobleemid();
//                break;
//            default:
//                System.out.println("Halb sisend");
//                System.out.println("Vali üks järgnevatest valikutest: h/r/m");
//                algus();
//                break;
//        }
//    }
//
//    private static void motivatsiooniprobleemid() {
//        System.out.println("Tundub, et sul on probleeme motivatsiooniga.\nÄra muretse, meie programm aitab sind taas tööle saada!\n--------------------------------");
//        try {
//            Thread.sleep(2000); //sleep 2s et näeks naturaalsem välja
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Random rnd = new Random();
//        int msgIndeks = rnd.nextInt(100);
//        System.out.println("Mõtteainet sulle:");
//        System.out.println(loeSõnumit("inspmsg.txt", msgIndeks));
//    }
//
//    /**
//     *
//     * @param fileName failinimi
//     * @param msgIndeks mis realt lugeda (0 kuni lõpp-1)
//     * @return String motsõnumiga
//     * Kasutan BufferedReaderit, sest pikkade txt failide käsitlemisel on see mõistlikum kui lihtsalt Scanneri kasutamine
//     * Sõnumid genereeris AI
//     */
//    private static String loeSõnumit(String fileName, int msgIndeks) {
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
//            String rida;
//            int käesolevRida = 0;
//            while ((rida = br.readLine()) != null){
//                if (käesolevRida == msgIndeks){
//                    return rida;
//                }
//                käesolevRida++;
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    private static void ressursideOptimiseerimine() {
//        System.out.println("Kõigepealt vajame mõningaid andmeid, et teha vajalikud arvutused");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Palju sul oli aasta tagasi töötajaid");
//        int L1 = scanner.nextInt();
//        System.out.println("Palju oli sul aasta tagasi kapitali (masinad, töövahendid jne) (kogus ühikutes)?");
//        int K1 = scanner.nextInt();
//        System.out.println("Palju on sul praegu töötajaid?");
//        int L2 = scanner.nextInt();
//        System.out.println("Palju on sul praegu kapitali (kogus ühikutes)?");
//        int K2 = scanner.nextInt();
//        System.out.println("Palju muutus sinu tootmiskogus võrreldes eelmise aastaga (koguse muutus ühikutes)?");
//        int deltaQ = scanner.nextInt();
//        System.out.println("Palju maksab praegu üks ühik kapitali?");
//        int r = scanner.nextInt();
//        System.out.println();
//        ValemidPalkKogukulu ressurss = new ValemidPalkKogukulu(deltaQ, K1, K2, L1, L2, r);
//        System.out.println(ressurss);
//        System.out.println("Palgakulu peaks olema "+ressurss.palk()+" optimaalse tootmise juures");
//        System.out.println("Sinu ettevõtte kogukulu optimaalse tootmise juures peaks olema: "+ressurss.kogukulu());
//
//    }

//    private static void hinnaMuutmine() {
//        System.out.println("Kõigepealt vajame mõningaid andmeid, et teha vajalikud arvutused");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Sisesta, mis oli su toote/teenuse hind enne hinna muutmist?");
//        double H1 = scanner.nextDouble();
//        System.out.println("Sisesta, mis oli su toote/teenuse hind peale hinna muutmist?");
//        double H2 = scanner.nextDouble();
//        System.out.println("Palju müüsid esialgse hinna juures (kogus ühikutes)?");
//        double Q1 = scanner.nextDouble();
//        System.out.println("Palju müüsid muudetud hinna juures (kogus ühikutest)?");
//        double Q2 = scanner.nextDouble();
//
//        System.out.println();
//        ValemidElastsus andmed = new ValemidElastsus(H1, H2, Q1, Q2);
//        //System.out.println(andmed);
//        System.out.println("Tulemus andmete " + andmed.getH1() + ", " + andmed.getH2() + ", " + andmed.getQ1() + ", " + andmed.getQ2() + " korral:");
//        andmed.hinnaelastsus();
//        System.out.println("Sinu ettevõtte käibe muutus oli "+ andmed.käibeMuutus() +"%");
//
//    }
//}
