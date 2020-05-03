package model.main;

import model.users.User;
import model.users.UserManager;

import java.awt.*;
import javax.swing.*;

class BudgetFormGUI extends JFrame {

    private JTextField foodBudget;
    private JTextField income;
    private JTextField livingBudget;
    private JTextField loanBudget;
    private JTextField saving;
    private JTextField shoppingBudget;
    private User user;

    BudgetFormGUI() {
        initComponents();
    }

    private void initComponents() {

        JButton jButton1 = new JButton();
        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea jTextArea1 = new JTextArea();
        JLabel jLabel8 = new JLabel();
        JScrollPane jScrollPane2 = new JScrollPane();
        JTextArea jTextArea2 = new JTextArea();
        livingBudget = new JTextField();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel7 = new JLabel();
        income = new JTextField();
        loanBudget = new JTextField();
        foodBudget = new JTextField();
        shoppingBudget = new JTextField();
        JButton inputFields = new JButton();
        JLabel jLabel9 = new JLabel();
        JLabel jLabel10 = new JLabel();
        JLabel jLabel11 = new JLabel();
        JLabel jLabel12 = new JLabel();
        JLabel jLabel13 = new JLabel();
        JLabel jLabel14 = new JLabel();
        saving = new JTextField();

        jButton1.setText("PROCEED");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel8.setFont(new Font("Lucida Grande", Font.PLAIN, 8)); // NOI18N
        jLabel8.setAlignmentY(0.0F);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new Font("Lucida Grande", Font.PLAIN, 10)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Note: All entries should be in dollars (no cents). If entry box for income is red following button \npress to proceed, your sum of entries in fields 2-5 exceed your monthly income, please\nre-enter amounts to ensure your monthly income is equal to or greater than the sum of fields\n2-5");
        jTextArea2.setUI(null);
        jScrollPane2.setViewportView(jTextArea2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("WHERE'S MY MONEY\n");
        setBackground(new Color(204, 255, 204));

        jLabel1.setText("Monthly Income:                                   $");

        jLabel2.setFont(new Font("Lucida Grande", Font.PLAIN, 18)); // NOI18N
        jLabel2.setText("                        WHERE'S MY MONEY");
        jLabel2.setAlignmentX(0.5F);
        jLabel2.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 102), 10));

        jLabel3.setText("Set Living Expenses:                             $");
        jLabel3.setMaximumSize(new java.awt.Dimension(253, 16));
        jLabel3.setMinimumSize(new java.awt.Dimension(253, 16));

        jLabel5.setText("Loan Repayment Goal (if any):               $");
        jLabel5.setMaximumSize(new java.awt.Dimension(253, 16));
        jLabel5.setMinimumSize(new java.awt.Dimension(253, 16));

        jLabel6.setText("Food Expenses Goal:                            $");
        jLabel6.setMaximumSize(new java.awt.Dimension(253, 16));
        jLabel6.setMinimumSize(new java.awt.Dimension(253, 16));

        jLabel7.setText("Savings Goal:                                        $");
        jLabel7.setMaximumSize(new java.awt.Dimension(253, 16));
        jLabel7.setMinimumSize(new java.awt.Dimension(253, 16));


        inputFields.setText("PROCEED");
        inputFields.addActionListener(evt -> inputFieldsActionPerformed());

        jLabel9.setText("Note:     All entries should be in dollars (no cents)");

        jLabel10.setText(" If entry box for income is red following button  press to proceed,");

        jLabel11.setText("your sum of entries in fields 2-5 does not equal your monthly income, ");

        jLabel12.setText("please re-enter amounts to ensure your monthly income is equal to");

        jLabel13.setText("the sum of fields 2-5");

        jLabel14.setText("Shopping/Miscellaneous Expenses:        $");
        jLabel14.setMaximumSize(new Dimension(253, 16));
        jLabel14.setMinimumSize(new Dimension(253, 16));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(foodBudget, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                        .addComponent(loanBudget, GroupLayout.Alignment.LEADING)
                                        .addComponent(saving, GroupLayout.Alignment.LEADING)
                                        .addComponent(shoppingBudget, GroupLayout.Alignment.LEADING)
                                        .addComponent(livingBudget)
                                        .addComponent(income))
                                .addGap(73, 73, 73))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 58, Short.MAX_VALUE)
                                                .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(inputFields)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(income, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(livingBudget))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loanBudget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(foodBudget, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(jLabel4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(shoppingBudget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(2, 2, 2)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(saving, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(inputFields)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addGap(17, 17, 17))
        );

        pack();
    }

    private void inputFieldsActionPerformed() {
        try {
            int userIncome = Integer.parseInt(this.income.getText());
            int userSaving = Integer.parseInt(this.saving.getText());
            int userLivingBudget = Integer.parseInt(this.livingBudget.getText());
            int userLoansBudget = Integer.parseInt(this.loanBudget.getText());
            int userFoodBudget = Integer.parseInt(this.foodBudget.getText());
            int userShoppingBudget = Integer.parseInt(this.shoppingBudget.getText());
            if (userIncome == userLivingBudget + userLoansBudget + userFoodBudget + userShoppingBudget + userSaving) {
                user = new User(new UserManager());
                user.setIncome(userIncome);
                user.setSavings(userSaving);
                user.setShoppingBudget(userShoppingBudget);
                user.setFoodBudget(userFoodBudget);
                user.setLivingBudget(userLivingBudget);
                user.setLoansBudget(userLoansBudget);
                assert (user.checkOkBudget());
                java.awt.EventQueue.invokeLater(() -> new TransactionEntriesGUI(user).setVisible(true));
                this.setVisible(false);

            } else {
                this.income.setBackground(Color.RED);
            }
        } catch (Exception ignore) {
        }
    }
}

