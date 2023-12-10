
package javafxapplication2;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


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
        String inputPX = JOptionPane.showInputDialog(null, "Masukkan pendapatan per kelas matematika :");
        int PX = Integer.parseInt(inputPX); // Pendapatan matematika
        String inputPY = JOptionPane.showInputDialog(null, "Masukkan pendapatan per kelas bahasa :");
        int PY = Integer.parseInt(inputPY); // Pendapatan bahasa
        System.out.println("Z = " + PX + "X + " + PY + "Y");

        // Langkah 3: Perumusan fungsi kendala
        
//      input kendala non negatif
        // Menggunakan dialog input untuk meminta nilai XOpt
        String inputXOpt = JOptionPane.showInputDialog(null, "Masukkan Kendala Non Negatif (X) : ");
        int XOpt = Integer.parseInt(inputXOpt);

        // Menggunakan dialog input untuk meminta nilai YOpt
        String inputYOpt = JOptionPane.showInputDialog(null, "Masukkan Kendala Non Negatif (Y) : ");
        int YOpt = Integer.parseInt(inputYOpt);
        
        // Menggunakan dialog input untuk meminta nilai MaxR
        String inputMaxR = JOptionPane.showInputDialog(null, "Masukkan nilai kebutuhan maksimum ruang kelas:");
        int MaxR = Integer.parseInt(inputMaxR);  // Input kendala 1 (kebutuhan maksimum ruang kelas)

        // Menggunakan dialog input untuk meminta nilai MaxP
        String inputMaxP = JOptionPane.showInputDialog(null, "Masukkan nilai kebutuhan maksimum pengajar");
        int MaxP = Integer.parseInt(inputMaxP); // Input kendala 2 (kebutuhan maksimum pengajar)
        
        
        for (int XVal = 0; XVal <= MaxR; XVal++) {
            for (int YVal = 0; YVal <= MaxR; YVal++) {
                if (XVal + YVal <= MaxP) {
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
        System.out.println("Kendala 2 -> X + Y ≤ " + MaxP);

        // Langkah 4: Membuat grafik
        String inputRuangKelas = JOptionPane.showInputDialog(null, "Masukkan jumlah ruang kelas (R) : ");
        int R = Integer.parseInt(inputRuangKelas); // Jumlah ruang kelas
        String inputPengajar = JOptionPane.showInputDialog(null, "Masukkan jumlah pengajar (P) : ");
        int P = Integer.parseInt(inputPengajar); // Jumlah pengajar

        // Menentukan titik optimal
        int x = R;
        int y = P;
        if (x >= 0 && y >= 0) {
            System.out.println("Kedua variabel memenuhi kondisi x ≥ 0 dan y ≥ 0.");
        } else {
            System.out.println("Salah satu atau kedua variabel tidak memenuhi kondisi.");
        }

        x = R;
        y = R;
        if (x <= MaxR)
            x = MaxR;
        if (y <= MaxR)
            y = MaxR;
        System.out.println("Titik koordinat kedua: (" + x + "," + y + ")");

        x = 0;
        y = 0;
        if (x == 0)
            y = MaxP;
        else if (y == 0)
            x = MaxP;
        System.out.println("Titik koordinat ketiga: (" + x + "," + y + ")");

        // Menentukan laba/pendapatan maksimum dan solusi optimal
        int ZValue = 0;
        while ((x + y <= R) && (x + y <= P)) {
            ZValue = PX * x + PY * y;
            if (ZValue > ZMax) {
                ZMax = ZValue;
                XOpt = x;
                YOpt = y;
            }

            if (PX > PY)
                x = x + 1;
            else
                y = y + 1;
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
        line2.getData().add(new XYChart.Data(MaxP,0));
        line2.getData().add(new XYChart.Data(0,MaxP));
        
        
       
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
