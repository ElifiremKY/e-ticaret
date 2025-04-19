package Proje;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductTablePanel extends JPanel
{
    private JTable table;
    private DefaultTableModel model;
    private ProductDAO productDAO = new ProductDAO();

    public ProductTablePanel(String role)
    {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Ad", "Açıklama", "Fiyat", "Stok"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton detayBtn = new JButton("Detayları Göster");
        add(detayBtn, BorderLayout.SOUTH);

        detayBtn.addActionListener(e -> showSelectedProductDetails());

        loadProductsFromDatabase();
    }

    private void loadProductsFromDatabase()
    {
        model.setRowCount(0); // Tabloyu temizle

        List<Product> products = productDAO.getAllProducts();
        for (Product product : products)
        {
            model.addRow(new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock()
            });
        }
    }

    private void showSelectedProductDetails()
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1)
        {
            Product product = new Product.ProductBuilder()
                    .setId((int) model.getValueAt(selectedRow, 0))
                    .setName((String) model.getValueAt(selectedRow, 1))
                    .setDescription((String) model.getValueAt(selectedRow, 2))
                    .setPrice((double) model.getValueAt(selectedRow, 3))
                    .setStock((int) model.getValueAt(selectedRow, 4))
                    .build();

            new ProductDetailDialog(product);
        } else
        {
            JOptionPane.showMessageDialog(this, "Lütfen bir ürün seçin.");
        }
    }

    public void showAddProductDialog()
    {
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Ad:"));
        panel.add(nameField);
        panel.add(new JLabel("Açıklama:"));
        panel.add(descField);
        panel.add(new JLabel("Fiyat:"));
        panel.add(priceField);
        panel.add(new JLabel("Stok:"));
        panel.add(stockField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Yeni Ürün Ekle",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION)
        {
            try
            {
                String name = nameField.getText();
                String desc = descField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());

                Product product = new Product.ProductBuilder()
                        .setName(name)
                        .setDescription(desc)
                        .setPrice(price)
                        .setStock(stock)
                        .build();

                productDAO.addProduct(product);
                loadProductsFromDatabase();

            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bilgiler girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void removeSelectedProduct()
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1)
        {
            int confirm = JOptionPane.showConfirmDialog(this, "Bu ürünü silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION)
            {
                int id = (int) model.getValueAt(selectedRow, 0);
                productDAO.deleteProduct(id);
                loadProductsFromDatabase();
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "Lütfen silmek için bir ürün seçin.");
        }
    }
}
