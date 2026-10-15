package com.springboot.helpdesk_backend.mapper;

import com.springboot.helpdesk_backend.dto.request.ExecutiveDto;
import com.springboot.helpdesk_backend.model.Executive;
import org.springframework.stereotype.Component;

@Component
public class ExecutiveMapper {
    public static Executive mapDtoToEntity(ExecutiveDto dto){
        Executive executive=new Executive();
        executive.setName(dto.name());
        executive.setEmail(dto.email());
        executive.setContact(dto.contact());
        executive.setJobTitle(dto.jobTitle());
        return executive;
    }

}
