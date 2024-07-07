package Global;

public class RegularExpression {
    public static boolean isProductId(String product_id){
        String pattern = "^(MS|NE|SE)[0-9]{6}$";
        return product_id.matches(pattern);
    }
    public static boolean isProductName(String product_name){
        String pattern = "^[a-z A-Z]+$";
        return product_name.matches(pattern);
    }
    public static boolean isNameCustomer(String customer_name){
        String pattern = "^[a-z A-Z]{3,50}$";
        return customer_name.matches(pattern);
    }
    public static boolean isOderId(String order_id){
        String pattern = "^(ORDPM)[0-9]{4}$";
        return order_id.matches(pattern);
    }
}
