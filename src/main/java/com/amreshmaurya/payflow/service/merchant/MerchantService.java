package com.amreshmaurya.payflow.service.merchant;

import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.entity.merchant.Merchant;
import com.amreshmaurya.payflow.exception.ResourceNotFoundException;
import com.amreshmaurya.payflow.mapper.MerchantMapper;
import com.amreshmaurya.payflow.repository.MerchantRepository;
import com.amreshmaurya.payflow.util.ApiKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.UUID;

import static com.amreshmaurya.payflow.util.ApiKeyGenerator.generateSecretKey;


@Service
public class MerchantService {

    MerchantRepository merchantRepository;
    MerchantMapper merchantMapper;
    PasswordEncoder passwordEncoder;


    @Autowired
    public MerchantService(MerchantRepository   merchantRepository, MerchantMapper merchantMapper,PasswordEncoder passwordEncoder) {
        this.merchantRepository = merchantRepository;
        this.merchantMapper = merchantMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public MerchantResponse createMerchant(CreateMerchantRequest createMerchantRequest) {
        System.out.println(createMerchantRequest);
        // hashed password
        createMerchantRequest.setPassword(passwordEncoder.encode(createMerchantRequest.getPassword()));



        String merchantId =  "MER" + UUID.randomUUID().toString();
        createMerchantRequest.setMerchantCode(merchantId);


        Merchant merchant = merchantMapper.toEntity(createMerchantRequest);

        merchant.setActive(true);
        merchant.setApiKey(ApiKeyGenerator.generateApiKey());
        merchant.setSecretKey(ApiKeyGenerator.generateSecretKey());
        Merchant res =  merchantRepository.save(merchant);
         return merchantMapper.toResponse(res);

    }


//    public MerchantResponse updateMerchant(UpdateMerchantRequest updateMerchantRequest) {}

    public MerchantResponse getMerchantById(UUID id) {
     Merchant res = merchantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Merchant not found"));
   return  merchantMapper.toResponse(res);
    }

}
