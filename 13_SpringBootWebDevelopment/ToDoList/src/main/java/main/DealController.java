package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.service.ListService;

import java.util.List;

@RestController
public class DealController {

    @Autowired
    private ListService listService;

    @GetMapping("/deals/")
    public List<DealDto> list(){
        return listService.findAll();
    }

    @GetMapping("/deals/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return listService.findById(id);
    }

    @PostMapping("/deals/")
    public int add(DealDto dealDto){
        return listService.addDeal(dealDto);
    }


    @PutMapping("/deals/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody DealDto newDealDto) {
        return listService.updateDeal(id, newDealDto);
    }

    @PutMapping("/deals/")
    public ResponseEntity updateAll(@RequestBody DealDto newDealDto) {
        return listService.updateAll(newDealDto);
    }

    @DeleteMapping("/deals/")
    public void clear(){
        listService.clearList();
    }

    @DeleteMapping("/deals/{id}")
    public void delete(@PathVariable int id){
        listService.deleteDeal(id);
    }
}
