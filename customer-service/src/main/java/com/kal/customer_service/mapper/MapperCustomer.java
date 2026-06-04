package com.kal.customer_service.mapper;


import com.kal.customer_service.dto.DtoCustomerFeign;
import com.kal.customer_service.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperCustomer {

    DtoCustomerFeign customerToDtoCustomerFeign(Customer customer);
}
