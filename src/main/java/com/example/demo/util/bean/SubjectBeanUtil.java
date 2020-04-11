package com.example.demo.util.bean;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectBeanUtil {

    @Autowired
    private ModelMapper modelMapper;

    public SubjectDTO entity2DTO(Subject subject){
        return modelMapper.map(subject,SubjectDTO.class);
    }
}
