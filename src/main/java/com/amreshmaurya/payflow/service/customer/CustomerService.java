package com.amreshmaurya.payflow.service.customer;



import java.util.UUID;
import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.request.UpdateCustomerRequest;
import com.amreshmaurya.payflow.entity.customer.Customer;

public interface CustomerService {

    Customer createCustomer(CreateCustomerRequest request);

    Customer updateCustomer(UUID customerId, UpdateCustomerRequest request);

    Customer getCustomerById(UUID customerId);

    Customer getCustomerByEmail(String email);

    Customer getCustomerByPhone(String phone);

    void activateCustomer(UUID customerId);

    void deactivateCustomer(UUID customerId);

    void deleteCustomer(UUID customerId);

}