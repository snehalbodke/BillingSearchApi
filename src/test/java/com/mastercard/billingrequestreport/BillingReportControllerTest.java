package com.mastercard.billingrequestreport;


import com.mastercard.billingrequestreport.model.OfflineRequestDTO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BillingRequestReportApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BillingReportControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyBillingReportPostTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/billing/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                                "\t\"invoiceDate\":\"27-08-2019\",\n" +
                                "\t\"feederType\":\"AUTH\",\n" +
                                "\t\"reportType\":\"Summary\",\n" +
                                "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].feederType").exists())
                .andExpect(jsonPath("$.[0].reportType").exists())
                .andExpect(jsonPath("$.[0].requestId").exists())
                .andExpect(jsonPath("$.[0].userId").exists())
                .andExpect(jsonPath("$.[0].createdIimestamp").exists())
                .andExpect(jsonPath("$.[0].lastUpdatedTimestamp").exists())
                .andExpect(jsonPath("$.[0].status").exists())
                .andExpect(jsonPath("$.[0].path").exists())
                .andExpect(jsonPath("$.[0].isDeleted").exists())
                .andDo(print());
    }

    @Test
    public void GetBillingReportRequestDetails_When_ValidRequest() throws Exception {
        /* setup mock */

        mockMvc.perform(MockMvcRequestBuilders.get("/billing/requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].feederType").exists())
                .andExpect(jsonPath("$.[0].reportType").exists())
                .andExpect(jsonPath("$.[0].requestId").exists())
                .andExpect(jsonPath("$.[0].userId").exists())
                .andExpect(jsonPath("$.[0].createdIimestamp").exists())
                .andExpect(jsonPath("$.[0].lastUpdatedTimestamp").exists())
                .andExpect(jsonPath("$.[0].status").exists())
                .andExpect(jsonPath("$.[0].path").exists())
                .andExpect(jsonPath("$.[0].isDeleted").exists())
                .andDo(print());
               /*mockMvc.perform(get("/billing/requests/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.accountId").value(12345))
                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
                .andExpect(jsonPath("$.balance").value(5000.0));*/
    }


/*
    @LocalServerPort
    int randomServerPort;

    @InjectMocks
    BillingReportRequestService billingReportRequestService;

    @Test
    public void testGetEmployeeListSuccess() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:"+randomServerPort+"/billing/requests/1";
        URI uri = new URI(baseUrl);

        ResponseEntity<OfflineRequestDTO> result = restTemplate.getForEntity(uri, OfflineRequestDTO.class);

        When(billingReportRequestService.getReportRequestDetailsById)thenRe

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        //Assert.assertEquals(true, result.getBody().contains("offlineRequestDTO"));
    }*/
}
