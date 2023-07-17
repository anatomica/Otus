package homework;

import java.util.*;

import static java.util.Comparator.comparing;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class CustomerService {

    private final NavigableMap<Customer, String> customers;

    private final Set<Customer> backupCustomers;

    public CustomerService() {
        this.customers = new TreeMap<>(comparing(Customer::getScores));
        this.backupCustomers = new HashSet<>();
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> firstCustomer = customers.firstEntry();
        checkCustomerName(firstCustomer);
        return firstCustomer;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextCustomer = customers.higherEntry(customer);
        return checkCustomerId(customer, nextCustomer);
    }

    public void add(Customer customer, String data) {
        backupCustomers.add(new Customer(customer.getId(), customer.getName(), customer.getScores()));
        customers.put(customer, data);
    }

    private void checkCustomerName(Map.Entry<Customer, String> firstCustomer) {
        if (firstCustomer != null && isNotEmpty(backupCustomers)) {
            Customer backupCustomer = backupCustomers.stream()
                    .filter(c -> c.getId() == firstCustomer.getKey().getId())
                    .findFirst()
                    .orElse(null);
            if (backupCustomer != null && !firstCustomer.getKey().getName().equals(backupCustomer.getName())) {
                firstCustomer.getKey().setName(backupCustomer.getName());
            }
        }
    }

    private Map.Entry<Customer, String> checkCustomerId(Customer customer, Map.Entry<Customer, String> nextCustomer) {
        if (nextCustomer == null && isNotEmpty(backupCustomers)) {
            if (backupCustomers.stream().anyMatch(c -> c.getId() == customer.getId())) {
                return customers.lastEntry();
            }
            return null;
        }
        return nextCustomer;
    }

}
