package Proje;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductTablePanel extends JPanel
{
    public ProductTablePanel(String role)
    {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Ad", "Açıklama", "Fiyat", "Stok"};
        Object[][] data = {
                {1, "Kulaklık", "Kablosuz kulaklık", 299.99, 5},
                {2, "Laptop", "16GB RAM, i7", 14999.90, 0},
                {3, "Mouse", "Oyuncu mouse", 399.50, 20}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton detayBtn = new JButton("Detayları Göster");
        add(detayBtn, BorderLayout.SOUTH);

        detayBtn.addActionListener(e ->
        {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1)
            {
                int id = (int) table.getValueAt(selectedRow, 0);
                String name = table.getValueAt(selectedRow, 1).toString();
                String desc = table.getValueAt(selectedRow, 2).toString();
                double price = (double) table.getValueAt(selectedRow, 3);
                int stock = (int) table.getValueAt(selectedRow, 4);

                new ProductDetailDialog(id, name, desc, price, stock);
            } else
            {
                JOptionPane.showMessageDialog(this, "Lütfen bir ürün seçin.");
            }
        });
    }
}
