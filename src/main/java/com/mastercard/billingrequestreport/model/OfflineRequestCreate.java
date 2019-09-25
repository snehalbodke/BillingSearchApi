package com.mastercard.billingrequestreport.model;

import javax.validation.constraints.NotNull;

public class OfflineRequestCreate {

    @NotNull
    private String feederType;
    public enum inpReportType  {Summary,Detail};
    private String reportType;
    @NotNull
    private String invoiceDate;
    private String userId;
    private String createdIimestamp;
    private String lastUpdatedTimestamp;
    private String status;
    private String path;
    private String isDeleted;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getFeederType() {
        return feederType;
    }

    public void setFeederType(String feederType) {
        this.feederType = feederType;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedIimestamp() {
        return createdIimestamp;
    }

    public void setCreatedIimestamp(String createdIimestamp) {
        this.createdIimestamp = createdIimestamp;
    }

    public String getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}
