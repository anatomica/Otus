package homework;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.util.Comparator.comparing;

public class CustomerService {

    private final NavigableMap<Customer, String> customers;

    public CustomerService() {
        this.customers = new TreeMap<>(comparing(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> first = customers.firstEntry();
        return Map.entry(new Customer(first.getKey().getId(), first.getKey().getName(), first.getKey().getScores()), first.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> customerNext = customers.higherEntry(customer);
        Map.Entry<Customer, String> customerLast = customers.lastEntry();
        return customerNext == null && customer.getId() <= customerLast.getKey().getId() ? customerLast : customerNext;
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

}
