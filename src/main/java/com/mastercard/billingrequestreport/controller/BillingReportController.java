package com.mastercard.billingrequestreport.controller;

import com.mastercard.billingrequestreport.model.OfflineRequestDTO;
import com.mastercard.billingrequestreport.service.BillingReportRequestService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String requestReport(@RequestBody @Valid OfflineRequestDTO offlineRequestDTO) {


        offlineRequestDTO.setUserId("SN20098787");//need to fetch it from SAML token
        offlineRequestDTO.setPath("http://www.google.com");//temp
        offlineRequestDTO.setStatus("INITIATED");//temp


        return billingReportRequestService.sendBillingReportRequest(offlineRequestDTO);



     // return "Hello";
    }

   /* @GetMapping("/{RequestId}")
    public ResponseEntity<OfflineRequestDTO> getReportRequestDetailsById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        return ResponseEntity.ok().body(user);
    }*/

    @GetMapping("/{RequestId}")
    public ResponseEntity<OfflineRequestDTO> getReportRequestDetailsById(@PathVariable(value = "RequestId") String requestId) throws SQLException {
        OfflineRequestDTO offlineRequestDTO = billingReportRequestService.getReportRequestDetailsById(requestId);
               /* userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));*/
        return ResponseEntity.ok().body(offlineRequestDTO);
    }

}
