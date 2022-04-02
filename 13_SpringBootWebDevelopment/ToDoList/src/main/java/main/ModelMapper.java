package main;

import main.model.Deal;
import org.springframework.stereotype.Service;

@Service
public class ModelMapper {
    //из entity в dto
    public DealDto mapToDealDto(Deal entity){
        DealDto dto = new DealDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setDate(entity.getDate());
        dto.setReadiness(entity.isReadiness());
        return dto;
    }
    //из dto в entity
    public Deal mapToDealEntity(DealDto dto){
        Deal entity = new Deal();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setDate(dto.getDate());
        entity.setReadiness(dto.isReadiness());
        return entity;
    }
}
