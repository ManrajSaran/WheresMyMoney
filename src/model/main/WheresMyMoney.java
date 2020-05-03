package model.main;

import javax.swing.*;

import static java.util.logging.Level.SEVERE;

public class WheresMyMoney {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(BudgetFormGUI.class.getName()).log(SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new BudgetFormGUI().setVisible(true));
    }
}
