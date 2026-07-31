package com.amreshmaurya.payflow.service.merchant;



import java.util.UUID;
import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.request.UpdateMerchantRequest;
import com.amreshmaurya.payflow.entity.merchant.Merchant;

public interface MerchantService {

    Merchant createMerchant(CreateMerchantRequest request);

    Merchant updateMerchant(UUID merchantId, UpdateMerchantRequest request);

    Merchant getMerchantById(UUID merchantId);

    Merchant getMerchantByMerchantCode(String merchantCode);

    Merchant getMerchantByEmail(String email);

    void deleteMerchant(UUID merchantId);

    void activateMerchant(UUID merchantId);

    void deactivateMerchant(UUID merchantId);

}