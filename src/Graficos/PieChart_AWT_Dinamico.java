/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author daniel
 */
public class PieChart_AWT_Dinamico extends ApplicationFrame {

    private JFreeChart panePop;
    private XYSeriesCollection curvaGrafico;
    private XYPlot plot;
    private XYItemRenderer renderer = new StandardXYItemRenderer();

    private ValueAxis domainAxis = new NumberAxis("Gerações");
    private ValueAxis rangeAxis = new NumberAxis("Individuos");

    public PieChart_AWT_Dinamico(String title) {
        super(title);

        curvaGrafico = new XYSeriesCollection();
        plot = new XYPlot(curvaGrafico, domainAxis, rangeAxis, renderer);
        panePop = new JFreeChart("Evolução", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        setContentPane(new ChartPanel(panePop));

        new Thread() {
            public void run() {
                while (true) {
                    XYSeries series2 = new XYSeries("função");
                    curvaGrafico = null;
                    curvaGrafico = new XYSeriesCollection();
                    curvaGrafico.addSeries(series2);

                    double a = 0;
                    Random rnd = new Random();
                    for (int i = 0; i <= 100; i++) {
                        a = Math.sin(i) * rnd.nextInt();
//                        System.out.println(a);
                        series2.add(i, a);
                        plot.setDataset(curvaGrafico);
                        plot.setRenderer(renderer);
                    }
                    try {
                        sleep(1000);//Para por 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        PieChart_AWT_Dinamico demo = new PieChart_AWT_Dinamico("Mobile Sales");
        demo.setSize(380, 316);
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
