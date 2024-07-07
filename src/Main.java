import Connectivity.MySQLConnection;
import Entity.*;
import Global.RegularExpression;
import Service.*;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        boolean flag = false;
        String orderId, productId;
        int quantity;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Customer customer = new Customer();
        Order order = new Order();
        Product product = new Product();
        OrderDetail orderDetail = new OrderDetail();

        CustomerDAO customerDAO = new CustomerDAO(MySQLConnection.getConnection(), customer);
        OrderDAO orderDAO = new OrderDAO(MySQLConnection.getConnection(), customer, order);
        ProductDAO productDAO = new ProductDAO(MySQLConnection.getConnection(), product);
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(MySQLConnection.getConnection());

        InsertOrderThread insertOrderThread = new InsertOrderThread(orderDetail, orderDetailDAO, productDAO);


        do {
            try {
                System.out.println("---Insert new order detail");

                /*INPUT ORDER ID*/
                System.out.print("Input order id: ");
                orderId = br.readLine();
                if(!RegularExpression.isOderId(orderId)) throw new Exception("Invalid order id");
                if(orderDAO.findByOrderId(orderId) == null) throw new Exception("Can't find order");

                /*INPUT PRODUCT ID*/
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
                Thread thread = new Thread(insertOrderThread);
                thread.start();
                try {
                    thread.join();
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