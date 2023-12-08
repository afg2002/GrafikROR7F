/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author afghanep
 */
public class GrafikJavaKelompok8R7F extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Langkah 1: Definisi variabel
        String X = "matematika";
        String Y = "bahasa";
       
        int Z = 0; // Pendapatan
        
        int ZMax = 0; // Pendapatan maksimal
        int NKel = 0; // Non-negatif kelas

        // Langkah 2: Perumusan fungsi tujuan
        int PX = 1000; // Pendapatan matematika
        int PY = 800; // Pendapatan bahasa
        System.out.println("Z = " + PX + "X + " + PY + "Y");

        // Langkah 3: Perumusan fungsi kendala
        
//      input kendala non negatif
        int XOpt = 0; // Jumlah optimal kelas matematika
        int YOpt = 0; // Jumlah optimal kelas bahasa
        int MaxR = 3; // Input kendala 1 (kebutuhan maksimum ruang kelas)
        int MaxT = 5; // Input kendala 2 (kebutuhan maksimum pengajar)

        for (int XVal = 0; XVal <= MaxR; XVal++) {
            for (int YVal = 0; YVal <= MaxR; YVal++) {
                if (XVal + YVal <= MaxT) {
                    Z = (XVal * PX) + (YVal * PY);
                    if (Z > ZMax) {
                        ZMax = Z;
                        XOpt = XVal;
                        YOpt = YVal;
                    }
                }
            }
        }
        System.out.println("Kendala non negatif -> X ≥ " + XOpt + ", Y ≥ " + YOpt);
        System.out.println("Kendala 1 -> X ≤ " + MaxR + ", Y ≤ " + MaxR);
        System.out.println("Kendala 2 -> X + Y ≤ " + MaxT);

        // Langkah 4: Membuat grafik
        int R = 3; // Jumlah ruang kelas
        int P = 5; // Jumlah pengajar

        // Menentukan titik optimal
        int x = 0;
        int y = 0;
        if (x >= 0 && y >= 0) {
            System.out.println("Kedua variabel memenuhi kondisi x ≥ 0 dan y ≥ 0.");
        } else {
            System.out.println("Salah satu atau kedua variabel tidak memenuhi kondisi.");
        }

        x = 3;
        y = 3;
        if (x <= MaxR)
            x = MaxR;
        if (y <= MaxR)
            y = MaxR;
        System.out.println("Titik koordinat kedua: (" + x + "," + y + ")");

        int XVal = 0;
        int YVal = 0;
        if (XVal == 0)
            YVal = MaxT;
        else if (YVal == 0)
            XVal = MaxT;
        System.out.println("Titik koordinat ketiga: (" + XVal + "," + YVal + ")");

        // Menentukan laba/pendapatan maksimum dan solusi optimal
        int ZValue = 0;
        while ((XVal + YVal <= R) && (XVal + YVal <= P)) {
            ZValue = PX * XVal + PY * YVal;
            if (ZValue > ZMax) {
                ZMax = ZValue;
                XOpt = XVal;
                YOpt = YVal;
            }

            if (PX > PY)
                XVal = XVal + 1;
            else
                YVal = YVal + 1;
        }

        System.out.println("Solusi optimal: " + XOpt + " kelas " + X + " dan " + YOpt + " kelas " + Y + ".");
        System.out.println("Pendapatan maksimum: $" + ZMax);
        // Langkah 4. Membuat Grafik
       
        //        menampilkan grafik dengan menetapkan batas minimum dan maksimum untuk sumbu x dan y dengan nilai ‘0’ 
        //        merupakan batas bawah/kiri, ‘6’ batas atas/kanan dan 1 yang merupakan jarak sumbu
        NumberAxis xAxis = new NumberAxis(0,6,1); 
        NumberAxis yAxis = new NumberAxis(0,6,1);
        
        LineChart<Number, Number> lineChart = new LineChart(xAxis, yAxis);
        
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data(XOpt, YOpt));
        
        XYChart.Series line1 = new XYChart.Series();
        line1.getData().add(new XYChart.Data(0,0)); 
        line1.getData().add(new XYChart.Data(MaxR,0));
        line1.getData().add(new XYChart.Data(0,MaxR));
        
        XYChart.Series line2 = new XYChart.Series();
        line2.getData().add(new XYChart.Data(MaxT,0));
        line2.getData().add(new XYChart.Data(0,MaxT));
        
        
       
        Scene scene = new Scene(lineChart,800,600);
        
        lineChart.getData().add(series);
        lineChart.getData().add(line1);
        lineChart.getData().add(line2);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
