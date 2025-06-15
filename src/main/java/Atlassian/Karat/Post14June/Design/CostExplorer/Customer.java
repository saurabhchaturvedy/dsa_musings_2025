package Post14June.Design.CostExplorer;

public class Customer {


    String id;
    String productName;
    Subscription subscription;

    public Customer(String id, String productName, Subscription subscription) {
        this.id = id;
        this.productName = productName;
        this.subscription = subscription;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
