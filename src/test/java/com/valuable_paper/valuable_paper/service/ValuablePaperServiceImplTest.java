package com.valuable_paper.valuable_paper.service;

import com.valuable_paper.valuable_paper.entity.ValuablePaper;
import com.valuable_paper.valuable_paper.repository.ValuablePaperRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValuablePaperServiceImplTest {

    @Mock
    ValuablePaperRepo valuablePaperRepo;


    private ValuablePaperService service;


    public static final LocalDate STOCK_DATE = LocalDate.now();
    public static final String STOCKNAME = "Test name";
    public static final int STOCK_PRICE = 1000;



    @BeforeEach
    void beforeEach() {
        ValuablePaper paper = new ValuablePaper();

        paper.setDate(STOCK_DATE);
        paper.setName(STOCKNAME);
        paper.setPrice(STOCK_PRICE);


        when(valuablePaperRepo.findById(anyInt()))
                .thenReturn(Optional.of(paper)); //Прояснить правиильно или нет

        service = new ValuablePaperServiceImpl(valuablePaperRepo);
    }

    @Test
    void getPaper() {

        ValuablePaper paper = service.getPaper(1);

        assertEquals(STOCK_DATE, paper.getDate());
        assertEquals(STOCKNAME, paper.getName());
        assertEquals(STOCK_PRICE, paper.getPrice());

        verify(valuablePaperRepo, times(1)).findById(anyInt());
    }

}