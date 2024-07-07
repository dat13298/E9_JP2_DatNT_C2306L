package Service;

import Entity.OrderDetail;

public class ShopManagement {
    private final OrderDetail orderDetail;
    private final OrderDetailDAO orderDetailDAO;
    private final ProductDAO productDAO;

    public ShopManagement(OrderDetail orderDetail, OrderDetailDAO orderDetailDAO, ProductDAO productDAO) {
        this.orderDetail = orderDetail;
        this.orderDetailDAO = orderDetailDAO;
        this.productDAO = productDAO;
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
