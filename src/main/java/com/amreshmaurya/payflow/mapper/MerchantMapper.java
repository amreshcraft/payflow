package com.amreshmaurya.payflow.mapper;


import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.entity.merchant.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MerchantMapper {
    // Merchant toEntity(UpdateMerchantRequest updateMerchantRequest);
    Merchant toEntity(CreateMerchantRequest merchantRequest);
    MerchantResponse toResponse(Merchant merchant);
    void updateMerchantFromRequest(CreateMerchantRequest createMerchantRequest, @MappingTarget Merchant merchant);

}
