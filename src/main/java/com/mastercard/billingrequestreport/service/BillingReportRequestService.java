package com.mastercard.billingrequestreport.service;

import com.mastercard.billingrequestreport.model.OfflineRequestDTO;
import com.mastercard.billingrequestreport.repository.BillingReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BillingReportRequestService {

@Autowired
BillingReportRepository billingReportRepository;
    public String sendBillingReportRequest(OfflineRequestDTO offlineRequestDTO) {

        /*
         * Insert into offline request table
         *
         */



        int i = billingReportRepository.insert(offlineRequestDTO);

        if (i>0) {
            return "Successfully created the offline request";
        } else{
        return "We are unable to process your request ,please contact administrator";

    }

    }


    /*public void validateBillingReportRequest(BillingRequestReportModel billingRequestReportModel){



    }*/

public OfflineRequestDTO getReportRequestDetailsById (String requestId) throws SQLException {
   return  billingReportRepository.findByRequestId(requestId);
}
}
