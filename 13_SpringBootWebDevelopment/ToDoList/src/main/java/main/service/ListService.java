package main.service;

import main.DealDto;
import main.model.Deal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListService {
    List<DealDto> findAll();
    ResponseEntity findById(int id);
    int addDeal(DealDto dealDto);
    ResponseEntity updateDeal(int id, DealDto newDealDto);
    ResponseEntity updateAll(DealDto newDealDto);
    void deleteDeal(int id);
    void clearList();
}
