package com.kal.customer_service.service;

import com.kal.customer_service.dto.DtoCustomerFeign;
import com.kal.customer_service.entity.Customer;
import com.kal.customer_service.exception.ApiException;
import com.kal.customer_service.repository.RepoCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SvcCustomerImp implements SvcCustomer {

    private final RepoCustomer repoCustomer;

    @Override
    public void create(DtoCustomerFeign req) {
        Customer customer = Customer.builder()
                .id(req.customerId())
                .email(req.email())
                .name(req.name())
                .build();
        repoCustomer.save(customer);
    }

    @Override
    public Customer findById(Integer id) {
        return repoCustomer.findById(id)
                .orElseThrow(() -> new ApiException("Cliente no encontrado: " + id, HttpStatus.NOT_FOUND));
    }
}
