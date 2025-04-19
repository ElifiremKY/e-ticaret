package Proje;

import javax.swing.*;
import java.awt.*;

public class ProductDetailDialog extends JDialog
{
    public ProductDetailDialog(int id, String name, String desc, double price, int stock)
    {
        setTitle("Ürün Detayı");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        add(new JLabel("ID: " + id));
        add(new JLabel("Ad: " + name));
        add(new JLabel("Açıklama: " + desc));
        add(new JLabel("Fiyat: " + price + "₺"));

        JLabel stockLabel = new JLabel("Stok: " + (stock > 0 ? "Var (" + stock + " adet)" : "Tükendi"));
        stockLabel.setForeground(stock > 0 ? Color.GREEN : Color.RED);
        add(stockLabel);

        setModal(true);
        setVisible(true);
    }
}