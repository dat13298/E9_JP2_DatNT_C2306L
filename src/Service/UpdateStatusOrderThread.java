package Service;

import Entity.EStatus;
import Entity.Order;
import Entity.OrderDetail;

public class UpdateStatusOrderThread extends ShopManagement implements Runnable {
    public UpdateStatusOrderThread(OrderDetail orderDetail, Order order, OrderDAO orderDAO, OrderDetailDAO orderDetailDAO, ProductDAO productDAO) {
        super(orderDetail, order, orderDAO, orderDetailDAO, productDAO);
    }

    public EStatus updateStatus() {
        if(super.getOrderDetail().getQuantity() <= super.getOrderDetail().getProduct().getQuantity()) {
            super.getOrderDetail().seteStatus(EStatus.COMPLETE);
        } else super.getOrderDetail().seteStatus(EStatus.CANCEL);
        super.getOrderDetailDAO().update(super.getOrderDetail());
        return super.getOrderDetail().geteStatus();
    }

    @Override
    public void run() {
        updateStatus();
    }
}
