package com.mastercard.billingrequestreport.controller;

import com.mastercard.billingrequestreport.model.OfflineDetailsResponse;
import com.mastercard.billingrequestreport.model.OfflineRequestCreate;
import com.mastercard.billingrequestreport.model.OfflineRequestCreateResponse;
import com.mastercard.billingrequestreport.service.BillingReportRequestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping(path = "/billing/requests")
public class BillingReportController {

    @Autowired
    private BillingReportRequestService billingReportRequestService;

    @PostMapping
    @ApiOperation("Returns Request Id and message post submittting the request for offline processing.")
    public ResponseEntity<OfflineRequestCreateResponse> requestReport(@RequestBody @Valid OfflineRequestCreate offlineRequestCreate) {

        offlineRequestCreate.setUserId("SN20098787");//need to fetch it from SAML token
        offlineRequestCreate.setPath("http://www.google.com");//temp
        offlineRequestCreate.setStatus("INITIATED");//temp

        OfflineRequestCreate.inpReportType.valueOf(offlineRequestCreate.getReportType());
       return new ResponseEntity<OfflineRequestCreateResponse>(billingReportRequestService.sendBillingReportRequest(offlineRequestCreate), HttpStatus.CREATED);

       // return ResponseEntity.status(HttpStatus.CREATED).body(billingReportRequestService.sendBillingReportRequest(offlineRequestCreate));
    }

    @ApiOperation("Returns Offline request details for {request-id} .")
    @GetMapping("/{request-id}")
    public ResponseEntity<OfflineDetailsResponse> getReportRequestDetailsById(@PathVariable(value = "request-id") String requestId) throws SQLException {
        OfflineDetailsResponse offlineDetailsResponse = billingReportRequestService.getReportRequestDetailsById(requestId);
        return ResponseEntity.ok().body(offlineDetailsResponse);
    }

    @ApiOperation("Downloads offline details as per file generated for {request-id}.")
    @GetMapping("/billing/requests/{request-id}/download")
    public ResponseEntity<Object> downloadReportById(@PathVariable(value = "request-id") String requestId) throws SQLException {
        return ResponseEntity.ok().body(billingReportRequestService.downloadReportById(requestId));
    }



}
