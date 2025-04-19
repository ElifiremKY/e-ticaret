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

                Product product = new Product.ProductBuilder()
                        .setId(id)
                        .setName(name)
                        .setDescription(desc)
                        .setPrice(price)
                        .setStock(stock)
                        .build();

                new ProductDetailDialog(product);

            } else
            {
                JOptionPane.showMessageDialog(this, "Lütfen bir ürün seçin.");
            }
        });
    }

    // Yeni ürün ekleme
    public void showAddProductDialog()
    {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField sellerIdField = new JTextField(); // Sadece örnek için

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Ürün ID:"));
        panel.add(idField);
        panel.add(new JLabel("Ad:"));
        panel.add(nameField);
        panel.add(new JLabel("Açıklama:"));
        panel.add(descField);
        panel.add(new JLabel("Fiyat:"));
        panel.add(priceField);
        panel.add(new JLabel("Stok:"));
        panel.add(stockField);
        panel.add(new JLabel("Satıcı ID:"));
        panel.add(sellerIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Yeni Ürün Ekle",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION)
        {
            try
            {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String desc = descField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                // sellerId alınabilir ama kullanılmıyor

                Product product = new Product.ProductBuilder()
                        .setId(id)
                        .setName(name)
                        .setDescription(desc)
                        .setPrice(price)
                        .setStock(stock)
                        .build();

                DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) getComponent(0)).getViewport().getView()).getModel();
                model.addRow(new Object[]{product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock()});
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bilgiler girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Ürün silme
    public void removeSelectedProduct()
    {
        JTable table = (JTable) ((JScrollPane) getComponent(0)).getViewport().getView();
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1)
        {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);
        } else
        {
            JOptionPane.showMessageDialog(this, "Lütfen silmek için bir ürün seçin.");
        }
    }

}
