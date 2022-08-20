package com.valuable_paper.valuable_paper.controller;

import com.valuable_paper.valuable_paper.entity.ValuablePaper;
import com.valuable_paper.valuable_paper.service.ValuablePaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/papers")
public class RController {



    @Autowired
    private ValuablePaperService service;

    @GetMapping("/dscsd")
    public String getRes() {
        return service.getrRes();
    }

    @GetMapping
    public List<ValuablePaper> jsonPaper() {
        return service.allValuablePaper();
    }

    @GetMapping("/{id}")
    public ValuablePaper getPaperId(@PathVariable int id) {
        ValuablePaper paper = service.getPaper(id);
        return paper;
    }

    @PostMapping
    public ResponseEntity<ValuablePaper> savePaper(@RequestBody ValuablePaper paper) {


        URI uri  = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save").toUriString());
        service.saveValuablePaper(paper);
        return ResponseEntity.created(uri).body(paper);
    }


    @PostMapping("/update")
    public ValuablePaper updatePaper(@RequestBody ValuablePaper paper) {
        service.saveValuablePaper(paper);
        return paper;
    }


}

