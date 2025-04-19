package Proje;

import javax.swing.*;
import java.awt.*;

public class SellerPanel extends JFrame
{
    public SellerPanel()
    {
        setTitle("Satıcı Paneli");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        ProductTablePanel tablePanel = new ProductTablePanel("seller");
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Butonlar paneli
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Ürün Ekle");
        JButton removeButton = new JButton("Ürün Sil");
        JButton exitButton = new JButton("Çıkış Yap");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Buton işlevleri
        exitButton.addActionListener(e ->
        {
            dispose();
            new LoginFrame();
        });

        addButton.addActionListener(e ->
        {
            tablePanel.showAddProductDialog();
        });

        removeButton.addActionListener(e ->
        {
            tablePanel.removeSelectedProduct();
        });

        add(mainPanel);
        setVisible(true);
    }
}
