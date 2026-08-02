package com.amreshmaurya.payflow.service.merchant.impl;


import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.request.UpdateMerchantRequest;
import com.amreshmaurya.payflow.entity.merchant.Merchant;
import com.amreshmaurya.payflow.entity.user.User;
import com.amreshmaurya.payflow.enums.UserRole;
import com.amreshmaurya.payflow.exception.ResourceNotFoundException;
import com.amreshmaurya.payflow.mapper.MerchantMapper;
import com.amreshmaurya.payflow.repository.MerchantRepository;
import com.amreshmaurya.payflow.service.merchant.MerchantService;
import com.amreshmaurya.payflow.service.user.UserService;
import com.amreshmaurya.payflow.util.HashedPasswordManager;


@Service
@RequiredArgsConstructor
@Transactional
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final UserService userService;
    private final MerchantMapper merchantMapper;
    private final HashedPasswordManager hashedPasswordManager;

    @Override
    public Merchant createMerchant(CreateMerchantRequest request) {

        User user = userService.createUser(
                request.getEmail(),
                hashedPasswordManager.hashPassword(request.getPassword()),
                UserRole.MERCHANT
        );

        Merchant merchant = Merchant.builder()
                .businessName(request.getBusinessName())
                .merchantCode(request.getMerchantCode())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .user(user)
                .active(true)
                .build();

        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchant(UUID merchantId,
                                   UpdateMerchantRequest request) {

        Merchant merchant = getMerchantById(merchantId);

        merchant.setBusinessName(request.getBusinessName());
        merchant.setPhone(request.getPhone());
        merchant.setWebsite(request.getWebsite());

        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant getMerchantById(UUID merchantId) {

        return merchantRepository.findById(merchantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Merchant not found"));
    }

    @Override
    public Merchant getMerchantByMerchantCode(String merchantCode) {

        return merchantRepository.findByMerchantCode(merchantCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Merchant not found"));
    }

    @Override
    public Merchant getMerchantByEmail(String email) {

        return   merchantRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Merchant not found"));
    }

    @Override
    public void deleteMerchant(UUID merchantId) {

        Merchant merchant = getMerchantById(merchantId);

        merchantRepository.delete(merchant);
    }

    @Override
    public void activateMerchant(UUID merchantId) {

        Merchant merchant = getMerchantById(merchantId);

        merchant.setActive(true);

        merchantRepository.save(merchant);
    }

    @Override
    public void deactivateMerchant(UUID merchantId) {

        Merchant merchant = getMerchantById(merchantId);

        merchant.setActive(false);

        merchantRepository.save(merchant);
    }

}
