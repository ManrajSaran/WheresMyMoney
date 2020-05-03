package model.main;

import java.awt.BorderLayout;

import model.users.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;

class PieChartGUI extends JFrame {

    private JPanel pieChart;
    private User user;

    PieChartGUI(User user) {
        initComponents();
        this.setLocationRelativeTo(null);
        pieChart.setLayout(new java.awt.BorderLayout());
        this.user = user;
        makePieChart();

    }

    private void initComponents() {
        pieChart = new JPanel();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pieChart.setBorder(BorderFactory.createEtchedBorder());
        GroupLayout pnChartLayout = new GroupLayout(pieChart);
        pieChart.setLayout(pnChartLayout);
        pnChartLayout.setHorizontalGroup(
                pnChartLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        pnChartLayout.setVerticalGroup(
                pnChartLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 333, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(pieChart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 226, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pieChart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                .addContainerGap())
        );

        pack();
    }

    private void makePieChart() {
        DefaultPieDataset expensesData = new DefaultPieDataset();
        expensesData.setValue("Living - $ " + this.user.livingSpending, new Double(this.user.livingSpending));
        expensesData.setValue("Loans - $ " + this.user.loansSpending, new Double(this.user.loansSpending));
        expensesData.setValue("Food - $ " + this.user.foodSpending, new Double(this.user.foodSpending));
        expensesData.setValue("Shopping - $ " + this.user.shoppingSpending, new Double(this.user.shoppingSpending));
        expensesData.setValue("Saving - $ " + this.user.getSaving(), new Double(this.user.getSaving()));
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Your Expenses",
                expensesData,
                true,
                true,
                false);
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setForegroundAlpha(0.60f);
        plot.setInteriorGap(0.02);
        ChartPanel chartPanel = new ChartPanel(chart);
        pieChart.removeAll();
        pieChart.add(chartPanel, BorderLayout.CENTER);
        pieChart.validate();
    }
}
