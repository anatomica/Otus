package homework;

import java.util.ArrayDeque;

public class CustomerReverseOrder {

    private final ArrayDeque<Customer> customers;

    public CustomerReverseOrder() {
        customers = new ArrayDeque<>();
    }

    public void add(Customer customer) {
        customers.push(customer);
    }

    public Customer take() {
        return customers.pop();
    }
}
