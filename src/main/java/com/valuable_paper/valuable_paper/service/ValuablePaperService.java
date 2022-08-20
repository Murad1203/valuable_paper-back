package com.valuable_paper.valuable_paper.service;

import com.valuable_paper.valuable_paper.entity.ValuablePaper;
import org.springframework.stereotype.Service;

import java.util.List;


public abstract class ValuablePaperService {

    public abstract List<ValuablePaper> allValuablePaper();
    public abstract void saveValuablePaper(ValuablePaper valuablePaper);
    public abstract ValuablePaper getPaper(int id);
    public abstract String getrRes(); // ПРИМЕР, УДАЛИ ПОТОМ
}
