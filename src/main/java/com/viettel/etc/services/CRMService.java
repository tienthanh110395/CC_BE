package com.viettel.etc.services;

import com.viettel.etc.dto.ContractDetailDTO;
import org.springframework.security.core.Authentication;

public interface CRMService {
    ContractDetailDTO getContractDetails(Authentication authentication);
}
