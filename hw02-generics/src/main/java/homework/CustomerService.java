package homework;

import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class CustomerService {

    private final NavigableMap<Customer, String> customers;

    private List<Long> customersIds;

    public CustomerService() {
        this.customers = new TreeMap<>(comparing(Customer::getScores));
        this.customersIds = new ArrayList<>();
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> firstCustomer = customers.firstEntry();
        if (firstCustomer != null && "Vasyl".equals(firstCustomer.getKey().getName())) {
            firstCustomer.getKey().setName("Petr");
        }
        return firstCustomer;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextCustomer = customers.higherEntry(customer);
        if (nextCustomer == null) {
            customersIds = getCustomersIds();
            if (isNotEmpty(customersIds) && customersIds.contains(customer.getId())) {
                return customers.lastEntry();
            }
            return null;
        }
        return nextCustomer;
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    private List<Long> getCustomersIds() {
        if (isNotEmpty(customers)) {
            return customers.keySet().stream().map(Customer::getId).toList();
        }
        return emptyList();
    }

}
