package Entity;

import java.time.LocalDateTime;

public class Order {
    private String id;
    private Customer customer;
    private LocalDateTime dateTime;

    public Order(){;}
    public Order(String id, Customer customer, LocalDateTime dateTime) {
        this.id = id;
        this.customer = customer;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Oder{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", dateTime=" + dateTime +
                '}';
    }
}
