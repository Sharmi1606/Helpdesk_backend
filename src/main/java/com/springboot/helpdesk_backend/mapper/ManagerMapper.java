package com.springboot.helpdesk_backend.mapper;

import com.springboot.helpdesk_backend.dto.request.ManagerDto;
import com.springboot.helpdesk_backend.model.Manager;

public class ManagerMapper {
    public static Manager convertDtoToEntity(ManagerDto dto){
        Manager manager=new Manager();
        manager.setName(dto.name());
        return manager;
    }
}
