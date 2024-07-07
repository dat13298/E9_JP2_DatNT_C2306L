package Service;

import Connectivity.MySQLConnection;
import Entity.OrderDetail;
import Generic.ShopRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAO implements ShopRepository<OrderDetail> {
    private Connection conn = MySQLConnection.getConnection();

    public OrderDetailDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        PreparedStatement pstmt = null;
        String query = "INSERT INTO OrderDetail(order_id, product_id, quantity, status) VALUES(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, orderDetail.getOrder().getId());
            pstmt.setString(2, orderDetail.getProduct().getId());
            pstmt.setInt(3, orderDetail.getQuantity());
            pstmt.setString(4, orderDetail.geteStatus().toString());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return orderDetail;
    }

    @Override
    public OrderDetail findById(int id) {
        return null;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        PreparedStatement pstmt = null;
        String query = "UPDATE OrderDetail SET status = ? WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, orderDetail.geteStatus().toString());
            pstmt.setInt(2, orderDetail.getId());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> findAll() {
        return List.of();
    }
}
