package Service;

import Connectivity.MySQLConnection;
import Entity.Customer;
import Generic.ShopRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO implements ShopRepository<Customer> {
    private Connection conn;
    private Customer customer;

    public CustomerDAO(Connection conn, Customer customer) {
        this.conn = conn;
        this.customer = customer;
    }


    @Override
    public Customer add(Customer customer) {
        return null;
    }

    @Override
    public Customer findById(int id) {
        PreparedStatement pstmt = null;
        String sql = "SELECT c.id, c.name FROM customer c WHERE c.id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                customer.setId(id);
                customer.setName(rs.getString("name"));
                return customer;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }
}
