package model.main;

import model.exceptions.*;
import model.expenses.*;
import model.users.User;


import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class TransactionEntriesGUI extends javax.swing.JFrame {

    private JTextField tranAmountEntry;
    private JTextField tranDateEntry;
    private JTextField tranDescripEntry;
    private JComboBox<String> tranTypeBox;
    private JTable transactionTable;

    private User user;

    TransactionEntriesGUI(User user) {
        initComponents();
        this.user = user;
    }

    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        JList<String> jList1 = new JList<>();
        JScrollPane jScrollPane4 = new JScrollPane();
        JTable jTable1 = new JTable();
        JScrollPane jScrollPane3 = new JScrollPane();
        JTable transacTable = new JTable();
        JLabel jLabel2 = new JLabel();
        tranTypeBox = new JComboBox<>();
        JLabel transactionType = new JLabel();
        JLabel transactionDate = new JLabel();
        tranDateEntry = new JTextField();
        JLabel transactionAmount = new JLabel();
        tranAmountEntry = new JTextField();
        JButton addTransactionButton = new JButton();
        JLabel transactionsDescription = new JLabel();
        tranDescripEntry = new JTextField();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JScrollPane jScrollPane5 = new JScrollPane();
        transactionTable = new JTable();
        JButton removeLastRowButton = new JButton();
        JButton jButton1 = new JButton();
        JButton displayChart = new JButton();

        jList1.setModel(new AbstractListModel<String>() {
            String[] strings = {"Food", "Shopping", "Living", "Loans"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane4.setViewportView(jTable1);

        transacTable.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Date", "Type", "Description", "Amount"
                }
        ));
        transacTable.setGridColor(new Color(102, 102, 102));
        jScrollPane3.setViewportView(transacTable);
        if (transacTable.getColumnModel().getColumnCount() > 0) {
            transacTable.getColumnModel().getColumn(0).setPreferredWidth(35);
            transacTable.getColumnModel().getColumn(3).setPreferredWidth(35);
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", Font.PLAIN, 18)); // NOI18N
        jLabel2.setText("                                  WHERE'S MY MONEY");
        jLabel2.setAlignmentX(0.5F);
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102), 10));
        jLabel2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        tranTypeBox.setModel(new DefaultComboBoxModel<>(new String[]{"Shopping", "Food", "Living", "Loans"}));

        transactionType.setText("Type of Transaction:");

        transactionDate.setText("Date:");

        tranDateEntry.setText("YYYY-MM-DD");

        transactionAmount.setText("Amount:");

        addTransactionButton.setText("Add Transaction");
        addTransactionButton.addActionListener(evt -> addTransactionButtonActionPerformed());

        transactionsDescription.setText("Short Description:");

        jLabel1.setText("jLabel1");

        jLabel3.setText("jLabel3");

        transactionTable.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "DATE", "TYPE", "DESCRIPTION", "AMOUNT"
                }
        ));
        transactionTable.setGridColor(new Color(0, 0, 0));
        jScrollPane5.setViewportView(transactionTable);

        removeLastRowButton.setText("Remove Last Row");
        removeLastRowButton.addActionListener(evt -> removeLastRowButtonActionPerformed());

        jButton1.setText("Request Improved Budget");
        jButton1.addActionListener(evt -> jButton1ActionPerformed());

        displayChart.setText("Display Expense Chart");
        displayChart.addActionListener(evt -> displayChartActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(15, 15, 15)
                                                                .addComponent(transactionsDescription)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tranDescripEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(170, 170, 170)
                                                                .addComponent(addTransactionButton))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(transactionType)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tranTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(39, 39, 39)
                                                                .addComponent(transactionDate)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tranDateEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(transactionAmount)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tranAmountEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane5)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(removeLastRowButton)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(displayChart, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(transactionType)
                                        .addComponent(tranTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(transactionDate)
                                        .addComponent(tranDateEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(transactionAmount)
                                        .addComponent(tranAmountEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(transactionsDescription)
                                        .addComponent(tranDescripEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addTransactionButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(displayChart)
                                        .addComponent(removeLastRowButton))
                                .addGap(15, 15, 15))
        );

        pack();
    }

    private void addTransactionButtonActionPerformed() {
        DefaultTableModel model = (DefaultTableModel) this.transactionTable.getModel();
        String dateStr = this.tranDateEntry.getText();
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
            cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(5, 7)) - 1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(8, 10)));
            Date date = cal.getTime();
            String description = this.tranDescripEntry.getText();
            int amount = Integer.parseInt(this.tranAmountEntry.getText());

            String type = Objects.requireNonNull(this.tranTypeBox.getSelectedItem()).toString();
            Expense exp;
            switch (type) {
                case "Shopping":
                    exp = new Shopping(description, amount, date);
                    break;
                case "Food":
                    exp = new Food(description, amount, date);
                    break;
                case "Living":
                    exp = new Living(description, amount, date);
                    break;
                default:
                    exp = new Loans(description, amount, date);
                    break;
            }
            try {
                user.makeTransaction(exp);
            } catch (ExceedingBudgetException ex) {
                JOptionPane.showMessageDialog(this, "Note: You have exceeded your limit for " + type + " transactions");
            } catch (BudgetLimitReachedException ex) {
                JOptionPane.showMessageDialog(this, "Note: You have reached your limit for " + type + " transactions");
            }
            model.addRow(new Object[]{dateStr, type, description, "$ " + amount});

        } catch (Exception ignored) {
        }
    }

    private void removeLastRowButtonActionPerformed() {
        DefaultTableModel model = (DefaultTableModel) this.transactionTable.getModel();
        try {
            int selectedRow = this.transactionTable.getRowCount();
            String s = (String) this.transactionTable.getValueAt(selectedRow - 1, 1);
            model.removeRow(selectedRow - 1);
            user.deleteLatestTransaction(s);
        } catch (Exception ignored) {
        }
    }

    private void jButton1ActionPerformed() {
        int[] newBudget = user.recommendedNewLimits();
        JOptionPane.showMessageDialog(this,
                "Based on your expenses, we recommend the following budget:\n"
                        + "Living  =   $ " + newBudget[2] + "\n"
                        + "Loans  =   $ " + newBudget[3] + "\n"
                        + "Food  =   $ " + newBudget[1] + "\n"
                        + "Shopping/Miscellaneous  =   $ " + newBudget[0] + "\n"
                        + "Saving  =   $ " + user.getSaving() + "\n");
    }

    private void displayChartActionPerformed() {
        new PieChartGUI(user).setVisible(true);

    }


}

