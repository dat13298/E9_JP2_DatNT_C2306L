package Service;

import Entity.Product;
import Generic.ShopRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAO implements ShopRepository<Product> {
    private Connection conn;
    private final Product product;

    public ProductDAO(Connection conn, Product product) {
        this.conn = conn;
        this.product = product;
    }

    @Override
    public Product add(Product product) {
        return null;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public Product update(Product product) {
        PreparedStatement pstmt = null;
        String query = "UPDATE Product p SET p.quantity = ? WHERE p.id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, product.getQuantity());
            pstmt.setString(2, product.getId());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    public Product getProductById(String id) {
        PreparedStatement pstmt = null;
        String query = "SELECT p.id, p.name, p.quantity FROM Product p WHERE p.id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                return product;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
