package Service;

import Entity.Order;
import Entity.OrderDetail;

public class ShopManagement {
    private final OrderDetail orderDetail;
    private final Order order;
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;
    private final ProductDAO productDAO;

    public ShopManagement(OrderDetail orderDetail, Order order, OrderDAO orderDAO, OrderDetailDAO orderDetailDAO, ProductDAO productDAO) {
        this.orderDetail = orderDetail;
        this.order = order;
        this.orderDAO = orderDAO;
        this.orderDetailDAO = orderDetailDAO;
        this.productDAO = productDAO;
    }

    public Order getOrder() {
        return order;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public OrderDetailDAO getOrderDetailDAO() {
        return orderDetailDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }
}
