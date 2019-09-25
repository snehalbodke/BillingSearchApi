package com.mastercard.billingrequestreport.service;

import com.mastercard.billingrequestreport.model.OfflineDetailsResponse;
import com.mastercard.billingrequestreport.model.OfflineRequestCreate;
import com.mastercard.billingrequestreport.model.OfflineRequestCreateResponse;
import com.mastercard.billingrequestreport.repository.BillingReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BillingReportRequestService {

@Autowired
BillingReportRepository billingReportRepository;


    public OfflineRequestCreateResponse sendBillingReportRequest(OfflineRequestCreate offlineRequestCreate) {
        int i = billingReportRepository.insert(offlineRequestCreate);

String message = null;
        if (i>0) {
            message= "Successfully created the offline request";
        } else{
            message= "We are unable to process your request ,please contact administrator";
    }
    return new OfflineRequestCreateResponse(message,"1234");
    }

public OfflineDetailsResponse getReportRequestDetailsById (String requestId) throws SQLException {
   return  billingReportRepository.findByRequestId(requestId);
}

//To download Files from S3
    public Object downloadReportById (String requestId) throws SQLException {
        return  new Object();
    }
}
