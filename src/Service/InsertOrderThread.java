package Service;

import Entity.EStatus;
import Entity.OrderDetail;

public class InsertOrderThread extends ShopManagement implements Runnable {

    public InsertOrderThread(OrderDetail orderDetail, OrderDetailDAO orderDetailDAO, ProductDAO productDAO) {
        super(orderDetail, orderDetailDAO, productDAO);
    }

    public OrderDetail insertOrderDetail() {
        /*ENOUGH QUANTITY*/
        if(super.getOrderDetail().getQuantity() <= super.getOrderDetail().getProduct().getQuantity()) {
            super.getOrderDetail().seteStatus(EStatus.COMPLETE);
            super.getOrderDetail().getProduct().setQuantity(
                    super.getOrderDetail().getProduct().getQuantity() - super.getOrderDetail().getQuantity()
            );
            super.getOrderDetailDAO().add(super.getOrderDetail());//insert orderDetail to Database
            super.getProductDAO().update(super.getOrderDetail().getProduct());//update quantity product in Database
        }
        /*NOT ENOUGH QUANTITY*/
        if(super.getOrderDetail().getQuantity() > super.getOrderDetail().getProduct().getQuantity()) {
            super.getOrderDetail().seteStatus(EStatus.CANCEL);
            super.getOrderDetailDAO().add(super.getOrderDetail());//insert orderDetail to Database
        }
        return super.getOrderDetail();
    }

    @Override
    public void run() {
        insertOrderDetail();
    }
}
