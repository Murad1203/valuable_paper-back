package com.valuable_paper.valuable_paper.service;

import com.valuable_paper.valuable_paper.entity.ValuablePaper;
import com.valuable_paper.valuable_paper.repository.ValuablePaperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ValuablePaperServiceImpl extends ValuablePaperService {


    private final ValuablePaperRepo valuablePaperRepo;

    @Autowired
    public ValuablePaperServiceImpl(ValuablePaperRepo valuablePaperRepo) {
        this.valuablePaperRepo = valuablePaperRepo;
    }


    @Override
    @Transactional
    public List<ValuablePaper> allValuablePaper() {
        return valuablePaperRepo.findAll();
    }

    @Override
    public void saveValuablePaper(ValuablePaper valuablePaper) {

        valuablePaper.setDate(LocalDate.now());
        valuablePaperRepo.save(valuablePaper);
    }

    @Override
    public ValuablePaper getPaper(int id) {
        ValuablePaper paper = null;
        Optional<ValuablePaper> optional = valuablePaperRepo.findById(id);
        if(optional.isPresent()) {
            paper = optional.get();
        }
        return paper;
    }

    @Override
    public String getrRes() {
        return "res";
    }



}
