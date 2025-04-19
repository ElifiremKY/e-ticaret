package Proje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO
{

    public List<Product> getAllProducts()
    {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {

            while (rs.next())
            {
                Product product = new Product.ProductBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setDescription(rs.getString("description"))
                        .setPrice(rs.getDouble("price"))
                        .setStock(rs.getInt("stock"))
                        .build();
                products.add(product);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return products;
    }

    public void addProduct(Product product)
    {
        String sql = "INSERT INTO products (name, description, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());

            pstmt.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id)
    {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
