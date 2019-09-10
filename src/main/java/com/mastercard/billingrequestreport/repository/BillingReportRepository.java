package com.mastercard.billingrequestreport.repository;

import com.mastercard.billingrequestreport.model.BillingRequestReportModel;
import com.mastercard.billingrequestreport.model.OfflineRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class BillingReportRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insert(OfflineRequestDTO offlineRequestDTO) {
        String dateStr = getDate();
        return jdbcTemplate.update("insert into OFFLINE_REQUESTS (USER_ID, CREATED_TIMESTAMP, LAST_UPDATED_TIMESTAMP,STATUS,PATH,IS_DELETED,FEEDER_TYPE,SEARCH_CRITERIA,REPORT_TYPE) " + "values(?,  ?, ?,?,  ?, ?,?,?,?)",
                new Object[]{
                        //offlineRequestDTO.getUserId(), offlineRequestDTO.getCreatedIimestamp(), offlineRequestDTO.getLastUpdatedTimestamp(), offlineRequestDTO.getStatus(), offlineRequestDTO.getPath(), offlineRequestDTO.getIsDeleted(),offlineRequestDTO.getFeederType(),offlineRequestDTO.getSearchCriteria(),offlineRequestDTO.getReportType()

                        offlineRequestDTO.getUserId(), dateStr, dateStr, offlineRequestDTO.getStatus(), offlineRequestDTO.getPath(), offlineRequestDTO.getIsDeleted(), offlineRequestDTO.getFeederType(), offlineRequestDTO.getSearchCriteria(), offlineRequestDTO.getReportType()
                });
    }

    public OfflineRequestDTO findByRequestId(String id) {

        String sql = "SELECT * FROM OFFLINE_REQUESTS WHERE REQUEST_ID = ?";

        return (OfflineRequestDTO) jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper(OfflineRequestDTO.class));

    }


    public String getDate() {

        LocalDateTime current = LocalDateTime.now();
        System.out.println("current date and time : " +
                current);
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formatedDateTime = current.format(format);
        System.out.println("formatedDateTime :" + formatedDateTime);

        return formatedDateTime;

    }


}
