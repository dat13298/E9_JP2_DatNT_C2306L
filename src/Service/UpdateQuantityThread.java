package Service;

import Entity.Order;
import Entity.OrderDetail;

public class UpdateQuantityThread extends ShopManagement implements Runnable {
    public UpdateQuantityThread(OrderDetail orderDetail, Order order, OrderDAO orderDAO, OrderDetailDAO orderDetailDAO, ProductDAO productDAO) {
        super(orderDetail, order, orderDAO, orderDetailDAO, productDAO);
    }

    public int updateQuantity() {
        if(super.getOrderDetail().getQuantity() <= super.getOrderDetail().getProduct().getQuantity()) {
            super.getOrderDetail().getProduct().setQuantity(
                    super.getOrderDetail().getProduct().getQuantity() - super.getOrderDetail().getQuantity()
            );// update quantity product object
            super.getProductDAO().update(super.getOrderDetail().getProduct());//update quantity product database
        }
        return super.getOrderDetail().getProduct().getQuantity();
    }

    @Override
    public void run() {
        updateQuantity();
    }
}
