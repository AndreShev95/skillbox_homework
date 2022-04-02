package main.service;

import main.DealDto;
import main.ModelMapper;
import main.model.Deal;
import main.model.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("ListService")
@Transactional(propagation = Propagation.REQUIRED)
public class ListServiceImpl implements ListService{

    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DealDto> findAll() {
        Iterable<Deal> dealIterable = dealRepository.findAll();
        ArrayList<DealDto> dealsDto = new ArrayList<>();
        for(Deal deal : dealIterable){
            dealsDto.add(modelMapper.mapToDealDto(deal));
        }
        return dealsDto;
    }

    @Override
    public ResponseEntity findById(int id){
        DealDto dealDto = modelMapper.mapToDealDto(dealRepository.findById(id).orElse(null));
        if (dealDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
            return new ResponseEntity(dealDto, HttpStatus.OK);
    }

    @Override
    public int addDeal(DealDto dealDto) {
        Deal newDeal = dealRepository.save(modelMapper.mapToDealEntity(dealDto));
        return newDeal.getId();
    }

    @Override
    public ResponseEntity updateDeal(int id, DealDto newDealDto) {
        Optional<Deal> optionalDeal = dealRepository.findById(id);
        if (optionalDeal.isPresent() && newDealDto!=null){
            Deal deal = optionalDeal.get();
            deal.setDate(newDealDto.getDate());
            deal.setReadiness(newDealDto.isReadiness());
            deal.setText(newDealDto.getText());
            return new ResponseEntity(dealRepository.save(deal), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity updateAll(DealDto newDealDto) {
        ArrayList<Deal> deals = new ArrayList<>();
        dealRepository.findAll().forEach(deal -> {
            deal.setText(newDealDto.getText());
            deal.setDate(newDealDto.getDate());
            deal.setReadiness(newDealDto.isReadiness());
            deals.add(deal);
            dealRepository.save(deal);
        });
        return new ResponseEntity(deals, HttpStatus.OK);
    }

    @Override
    public void deleteDeal(int id) {
        dealRepository.deleteById(id);
    }

    @Override
    public void clearList() {
        dealRepository.deleteAll();
    }
}
