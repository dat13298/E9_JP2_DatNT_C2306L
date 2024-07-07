import Connectivity.MySQLConnection;
import Entity.*;
import Global.RegularExpression;
import Service.*;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        boolean flag = false;
        String orderId, productId;
        int quantity, customerId;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Customer customer = new Customer();
        Order order = new Order();
        Product product = new Product();
        OrderDetail orderDetail = new OrderDetail();

        CustomerDAO customerDAO = new CustomerDAO(MySQLConnection.getConnection(), customer);
        OrderDAO orderDAO = new OrderDAO(MySQLConnection.getConnection(), customer, order);
        ProductDAO productDAO = new ProductDAO(MySQLConnection.getConnection(), product);
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(MySQLConnection.getConnection());

        InsertOrderThread insertOrderThread = new InsertOrderThread(orderDetail, order, orderDAO, orderDetailDAO, productDAO);
        AddOrderToOrderDetailThread addOrderToOrderDetailThread = new AddOrderToOrderDetailThread(orderDetail, order, orderDAO, orderDetailDAO, productDAO);
        UpdateQuantityThread updateQuantityThread = new UpdateQuantityThread(orderDetail, order, orderDAO, orderDetailDAO, productDAO);
        UpdateStatusOrderThread updateStatusOrderThread = new UpdateStatusOrderThread(orderDetail, order, orderDAO, orderDetailDAO, productDAO);

        do {
            try {
                /*INSERT ORDER*/
                System.out.println("---Insert new order");
                System.out.print("Input order id: ");
                orderId = br.readLine();
                if(!RegularExpression.isOderId(orderId)) throw new Exception("Invalid order id");
                if(orderDAO.findByOrderId(orderId) != null) throw new Exception("Order already exists");
                System.out.print("Input customer id: ");
                customerId = Integer.parseInt(br.readLine());
                if (customerDAO.findById(customerId) == null) throw new Exception("Customer does not exist");
                order.setId(orderId);
                order.setCustomer(customer);
                order.setDateTime(LocalDateTime.now());

                /*INSERT ORDER DETAIL*/
                System.out.print("Input product id: ");
                productId = br.readLine();
                if(!RegularExpression.isProductId(productId)) throw new Exception("Invalid product id");
                if(productDAO.getProductById(productId) == null) throw new Exception("Can't find product");

                /*INPUT QUANTITY*/
                System.out.print("Input quantity: ");
                quantity = Integer.parseInt(br.readLine());
                if(quantity < 0) throw new Exception("Invalid quantity");

                /*SET PROPERTIES ORDER DETAIL*/
                orderDetail.setId(1);
                orderDetail.setOrder(order);
                orderDetail.setProduct(product);
                orderDetail.setQuantity(quantity);
                orderDetail.seteStatus(EStatus.PENDING);

                /*RUN THREAD*/
                Thread thread1 = new Thread(insertOrderThread);// insert Order and orderDetail
                Thread thread2 = new Thread(addOrderToOrderDetailThread);//make orderDetail references order
                Thread thread3 = new Thread(updateQuantityThread);//update quantity product
                Thread thread4 = new Thread(updateStatusOrderThread);//update status
                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
                try {
                    thread1.join();
                    thread2.join();
                    thread3.join();
                    thread4.join();
                }catch (IOError|InterruptedException e){
                    System.out.println(e.getMessage());
                }

                /*MESSAGE IF NOT ENOUGH QUANTITY*/
                if(orderDetail.geteStatus() == EStatus.CANCEL) throw new Exception("Product out of stock");

                flag = true;
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!flag);
    }
}