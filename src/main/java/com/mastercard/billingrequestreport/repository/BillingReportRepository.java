package com.mastercard.billingrequestreport.repository;

import com.mastercard.billingrequestreport.model.OfflineDetailsResponse;
import com.mastercard.billingrequestreport.model.OfflineRequestCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class BillingReportRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insert(OfflineRequestCreate offlineRequestCreate) {
        String dateStr = getDate();
        return jdbcTemplate.update("insert into OFFLINE_REQUESTS (USER_ID, CREATED_TIMESTAMP, LAST_UPDATED_TIMESTAMP,STATUS,PATH,IS_DELETED,FEEDER_TYPE,SEARCH_CRITERIA,REPORT_TYPE) " + "values(?,  ?, ?,?,  ?, ?,?,?,?)",
                new Object[]{
                        //offlineRequestDTO.getUserId(), offlineRequestDTO.getCreatedIimestamp(), offlineRequestDTO.getLastUpdatedTimestamp(), offlineRequestDTO.getStatus(), offlineRequestDTO.getPath(), offlineRequestDTO.getIsDeleted(),offlineRequestDTO.getFeederType(),offlineRequestDTO.getSearchCriteria(),offlineRequestDTO.getReportType()

                        offlineRequestCreate.getUserId(), dateStr, dateStr, offlineRequestCreate.getStatus(), offlineRequestCreate.getPath(), offlineRequestCreate.getIsDeleted(), offlineRequestCreate.getFeederType(), offlineRequestCreate.getInvoiceDate(), offlineRequestCreate.getReportType()
                });
    }

    public OfflineDetailsResponse findByRequestId(String id) throws SQLException {
        String sql = "SELECT * FROM OFFLINE_REQUESTS WHERE REQUEST_ID = ?";

       /* try {
            String sql = "SELECT * FROM OFFLINE_REQUESTS WHERE REQUEST_ID = ?";
            OfflineRequestDetails offlineRequestDetails = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<OfflineRequestDetails>() {
                @Override
                public OfflineRequestDetails mapRow(ResultSet resultSet, int i) throws SQLException {
                    OfflineRequestDetails offlineRequestDetails = new OfflineRequestDetails();

                    return offlineRequestDetails;
                }
            });
            return offlineRequestDetails;
         //   return (OfflineRequestDetails) jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper(OfflineRequestDetails.class));
        }catch (EmptyResultDataAccessException e) {
            return null;
        }*/

       return (OfflineDetailsResponse) jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper(OfflineDetailsResponse.class));

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
