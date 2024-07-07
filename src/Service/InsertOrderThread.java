package Service;

import Entity.EStatus;
import Entity.Order;
import Entity.OrderDetail;

public class InsertOrderThread extends ShopManagement implements Runnable {

    public InsertOrderThread(OrderDetail orderDetail, Order order, OrderDAO orderDAO, OrderDetailDAO orderDetailDAO, ProductDAO productDAO) {
        super(orderDetail, order, orderDAO, orderDetailDAO, productDAO);
    }

    public Order insertOrderDetail() {
        super.getOrderDAO().add(super.getOrder());
        /*ENOUGH QUANTITY*/
        if(super.getOrderDetail().getQuantity() <= super.getOrderDetail().getProduct().getQuantity()) {
            super.getOrderDetail().seteStatus(EStatus.COMPLETE);
            super.getOrderDetailDAO().add(super.getOrderDetail());//insert orderDetail to Database
        }
        /*NOT ENOUGH QUANTITY*/
        if(super.getOrderDetail().getQuantity() > super.getOrderDetail().getProduct().getQuantity()) {
            super.getOrderDetail().seteStatus(EStatus.CANCEL);
            super.getOrderDetailDAO().add(super.getOrderDetail());//insert orderDetail to Database
        }
        return super.getOrderDetail().getOrder();
    }

    @Override
    public void run() {
        insertOrderDetail();
    }
}
