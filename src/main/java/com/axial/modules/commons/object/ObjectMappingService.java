package com.axial.modules.commons.object;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ObjectMappingService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    private void initOps() {


    }


}
