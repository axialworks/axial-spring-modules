package com.axial.modules.commons.core.request.model;

import com.axial.modules.commons.model.SuccessResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class GenericSuccessResponse extends SuccessResponse<Map<String, Object>> {

    public GenericSuccessResponse addHashItem(String hashKey, Object hashValue) {

        if (MapUtils.isEmpty(this.getResult())) {
            this.setResult(new HashMap<>());
        }

        this.getResult().put(hashKey, hashValue);
        return this;
    }

}
