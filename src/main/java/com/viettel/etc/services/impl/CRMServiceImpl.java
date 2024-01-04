package com.viettel.etc.services.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viettel.etc.dto.ContractDetailDTO;
import com.viettel.etc.dto.ResContractDetailDTO;
import com.viettel.etc.services.CRMService;
import com.viettel.etc.utils.FnCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class CRMServiceImpl implements CRMService {

    @Value("${ws.crm.contract.details}")
    String wsContractDetails;

    @Override
    public ContractDetailDTO getContractDetails(Authentication authentication) {
        String response = FnCommon.doGetRequest(wsContractDetails, null, FnCommon.getStringToken(authentication));
        Gson gson = new Gson();
        Type type = new TypeToken<ResContractDetailDTO>() {
        }.getType();
        if (response != null) {
            ResContractDetailDTO res = gson.fromJson(response, type);
            if (res.getData() != null) {
                return res.getData();
            }
        }
        return null;
    }
}
