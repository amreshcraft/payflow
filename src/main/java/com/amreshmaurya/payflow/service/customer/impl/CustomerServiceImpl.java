package com.amreshmaurya.payflow.service.customer.impl;




import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.request.UpdateCustomerRequest;
import com.amreshmaurya.payflow.entity.customer.Customer;
import com.amreshmaurya.payflow.entity.user.User;
import com.amreshmaurya.payflow.enums.UserRole;
import com.amreshmaurya.payflow.exception.ResourceNotFoundException;
import com.amreshmaurya.payflow.repository.CustomerRepository;
import com.amreshmaurya.payflow.service.customer.CustomerService;
import com.amreshmaurya.payflow.service.user.UserService;
import com.amreshmaurya.payflow.util.HashedPasswordManager;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final HashedPasswordManager hashedPasswordManager;

    @Override
    public Customer createCustomer(CreateCustomerRequest request) {

        User user = userService.createUser(
                request.getEmail(),
                hashedPasswordManager.hashPassword(request.getPassword()),
                UserRole.CUSTOMER
        );

        Customer customer = Customer.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .user(user)
                .active(true)
                .build();

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(UUID customerId,
                                   UpdateCustomerRequest request) {

        Customer customer = getCustomerById(customerId);

        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
    
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(UUID customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        return customerRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));
    }




    @Override
    public Customer getCustomerByPhone(String phone) {

        return customerRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new ResourceNotFoundException(  "Customer not found"));
    }



    @Override
    public void activateCustomer(UUID customerId) {

        Customer customer = getCustomerById(customerId);

        customer.setActive(true);

        customerRepository.save(customer);
    }

    @Override
    public void deactivateCustomer(UUID customerId) {

        Customer customer = getCustomerById(customerId);

        customer.setActive(false);

        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {

        Customer customer = getCustomerById(customerId);

        customerRepository.delete(customer);
    }

}
