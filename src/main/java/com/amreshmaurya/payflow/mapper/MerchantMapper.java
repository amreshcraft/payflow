package com.amreshmaurya.payflow.mapper;

import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.entity.merchant.Merchant;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.amreshmaurya.payflow.dto.merchant.request.UpdateMerchantRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MerchantMapper {
    // Merchant toEntity(UpdateMerchantRequest updateMerchantRequest);
    public Merchant toEntity(CreateMerchantRequest merchantRequest);

    public MerchantResponse toResponse(Merchant merchant);

    public void updateMerchantFromRequest(UpdateMerchantRequest updateMerchantRequest,
            @MappingTarget Merchant merchant);

}
