package com.valuable_paper.valuable_paper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuable_paper.valuable_paper.entity.ValuablePaper;
import com.valuable_paper.valuable_paper.service.ValuablePaperService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RController.class)
class RControllerTest {


    @Autowired
    MockMvc mockMvc;


    @MockBean
    ValuablePaperService service;



//    private final LocalDate date = LocalDate.now();

    ValuablePaper stock = new ValuablePaper("name", 4200);



    @BeforeEach
    void beforeEach() {
        when(service.getPaper(anyInt())).thenReturn(stock);
    }


    @Test
    public void testPaper() throws Exception {
        var result = mockMvc.perform(get(String.format("/papers/%s", stock.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        verify(service, times(1)).getPaper(anyInt());
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(result, mapper.writeValueAsString(stock));
    }

}