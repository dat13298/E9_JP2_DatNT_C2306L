package Service;

import Connectivity.MySQLConnection;
import Entity.Customer;
import Entity.Order;
import Generic.ShopRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDAO implements ShopRepository<Order> {
    private Connection conn;
    private final Customer customer;
    private final Order order;

    public OrderDAO(Connection conn, Customer customer, Order order){
        this.conn = conn;
        this.customer = customer;
        this.order = order;
    }

    @Override
    public Order add(Order order) {
        PreparedStatement pstmt = null;
        String query = "INSERT INTO `Order`(id, cus_id, datetime) VALUES (?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, order.getId());
            pstmt.setInt(2, order.getCustomer().getId());
            pstmt.setTimestamp(3, Timestamp.valueOf(order.getDateTime()));
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return order;
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    public Order findByOrderId(String id) {
        PreparedStatement pstmt = null;
        String query = "SELECT o.id, o.cus_id, o.datetime, c.name " +
                "FROM `Order` o " +
                "INNER JOIN Customer c ON o.cus_id = c.id " +
                "WHERE o.id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                customer.setId(rs.getInt("o.cus_id"));
                customer.setName(rs.getString("c.name"));
                order.setCustomer(customer);
                order.setId(rs.getString("o.id"));
                Timestamp datetime = rs.getTimestamp("o.datetime");
                LocalDateTime localDateTime = datetime.toLocalDateTime();
                order.setDateTime(localDateTime);
                return order;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
