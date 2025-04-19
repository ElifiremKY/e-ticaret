package Proje;

import javax.swing.*;
import java.awt.*;

public class SellerPanel extends JFrame
{
    public SellerPanel()
    {
        setTitle("Satıcı Paneli");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new ProductTablePanel("seller"), BorderLayout.CENTER);

        JButton exitButton = new JButton("Çıkış Yap");
        exitButton.addActionListener(e ->
        {
            dispose();
            new LoginFrame();
        });
        mainPanel.add(exitButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
