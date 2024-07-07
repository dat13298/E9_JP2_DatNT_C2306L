package Service;

import Entity.Order;
import Entity.OrderDetail;

public class AddOrderToOrderDetailThread extends ShopManagement implements Runnable{

    public AddOrderToOrderDetailThread(OrderDetail orderDetail, Order order, OrderDAO orderDAO, OrderDetailDAO orderDetailDAO, ProductDAO productDAO) {
        super(orderDetail, order, orderDAO, orderDetailDAO, productDAO);
    }

    public Order addOrderToOrderDetail() {
        super.getOrderDetail().setOrder(super.getOrder());
        return super.getOrder();
    }

    @Override
    public void run() {
        addOrderToOrderDetail();
    }
}
