package com.kal.invoice_service.mapper;

import com.kal.invoice_service.dto.DtoInvoiceItemOut;
import com.kal.invoice_service.dto.DtoInvoiceOut;
import com.kal.invoice_service.entity.Invoice;
import com.kal.invoice_service.entity.InvoiceItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InvoiceItem.class})
public interface MapperInvoice {

    DtoInvoiceOut invoiceToDtoInvoiceOut(Invoice invoice);

    DtoInvoiceItemOut invoiceItemToDtoInvoiceItemOut(InvoiceItem invoiceItem);

    List<DtoInvoiceOut> invoicesToDtoInvoicesOut(List<Invoice> invoices);

    List<DtoInvoiceItemOut> invoiceItemsToDtoInvoiceItemsOut(List<InvoiceItem> invoiceItems);


}
