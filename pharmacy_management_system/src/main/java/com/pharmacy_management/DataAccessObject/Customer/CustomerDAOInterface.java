package com.pharmacy_management.DataAccessObject.Customer;
import java.util.List;

import com.pharmacy_management.Data.Customer;

public interface CustomerDAOInterface {
    void addCustomer(Customer customer);
    Customer getCustomerById(String customerId);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(String customerId);
}
