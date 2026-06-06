package com.kal.invoice_service.service;

import com.kal.invoice_service.dto.DtoInvoiceIn;
import com.kal.invoice_service.dto.DtoInvoiceOut;

public interface SvcInvoice {
    DtoInvoiceOut create(Integer customerId, DtoInvoiceIn in);
}
