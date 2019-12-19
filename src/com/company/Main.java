package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class Main {

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "ВМиС",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint  (0, Color.green);
        renderer.setSeriesStroke  (0, new BasicStroke(1.5f));
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        return chart;

    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    private static XYDataset createDataset() {
        int[] koef = {6, 0, -4, -3, 5, 6, -6, -13, 7, 44, 64, 44, 7, -13, -6, 6, 5, -3, -4, 0, 6};
        XYSeries s1 = new XYSeries ("Значение F(X)");
        int kol = 0;
        for (double m = 2; m <= 22; m += 0.01) {
            double x = 2 / m, y = 0;
            double Re = 0, Im = 0;
            double A=0;
            for (int i=0; i<21; i++) {
                if (koef[i] == 0) continue;
                Re += Math.cos(2 * Math.PI * i / m) * koef[i];
                Im += Math.sin(2 * Math.PI * i / m) * koef[i];
        }
            A = Math.sqrt(Math.pow(Re, 2) + Math.pow(Im, 2));
            y = 20 * Math.log10(A);            s1.add(x, y);
            System.out.println("x " + x + " y  " + y +" A " + A);
        }
        System.out.print("number nodes "+kol);
        XYDataset dataset = new XYSeriesCollection(s1);
        return dataset;
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JPanel jp = createDemoPanel();
        jp.setBounds(100, 100, 600, 300);
        window.add(jp);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.BLACK);
        window.setBounds(0, 0, 700, 400);
        window.setVisible(true);
    }
}
