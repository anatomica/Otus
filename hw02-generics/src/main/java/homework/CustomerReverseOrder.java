package homework;

import java.util.LinkedList;

public class CustomerReverseOrder {

    private final LinkedList<Customer> customers;

    public CustomerReverseOrder() {
        customers = new LinkedList<>();
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        Customer customer = customers.getLast();
        customers.remove(customer);
        return customer;
    }
}
