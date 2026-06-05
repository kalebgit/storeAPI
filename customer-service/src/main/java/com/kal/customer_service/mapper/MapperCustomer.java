package com.kal.customer_service.mapper;


import com.kal.customer_service.dto.DtoCustomerFeign;
import com.kal.customer_service.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperCustomer {

    @Mapping(source = "id", target = "customerId")
    DtoCustomerFeign customerToDtoCustomerFeign(Customer customer);
}
