package Entity;

public class OrderDetail {
    private int id;
    private Order order;
    private Product product;
    private int quantity;
    private EStatus eStatus;

    public OrderDetail() {;}

    public OrderDetail(int id, Order order, Product product, int quantity, EStatus eStatus) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.eStatus = eStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public EStatus geteStatus() {
        return eStatus;
    }

    public void seteStatus(EStatus eStatus) {
        this.eStatus = eStatus;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", eStatus=" + eStatus +
                '}';
    }
}
