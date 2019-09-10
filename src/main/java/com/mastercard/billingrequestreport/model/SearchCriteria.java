package com.mastercard.billingrequestreport.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

public class SearchCriteria {

    @NotNull
    private String invoiceDate;

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "invoiceDate='" + invoiceDate + '\'' +
                '}';
    }
}
