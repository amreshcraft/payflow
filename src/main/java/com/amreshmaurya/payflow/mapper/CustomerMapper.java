package com.amreshmaurya.payflow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.amreshmaurya.payflow.dto.customer.request.CreateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.request.UpdateCustomerRequest;
import com.amreshmaurya.payflow.dto.customer.response.CustomerResponse;
import com.amreshmaurya.payflow.entity.customer.Customer;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    Customer toEntity(CreateCustomerRequest request);
    
    @Mapping(source = "user.email", target = "email")
    CustomerResponse toResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(UpdateCustomerRequest request, @MappingTarget Customer customer);
}
