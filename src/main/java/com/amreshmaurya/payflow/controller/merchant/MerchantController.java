package com.amreshmaurya.payflow.controller.merchant;


import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.service.merchant.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    MerchantService merchantService;

    @Autowired
    MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<MerchantResponse> createMerchant(@RequestBody CreateMerchantRequest createMerchantRequest) {
        return   ResponseEntity.status(HttpStatus.CREATED).body(this.merchantService.createMerchant(createMerchantRequest));
    }



    @GetMapping("/{merchantId}")
    public ResponseEntity<MerchantResponse> getMerchant(
            @PathVariable UUID merchantId) {

        return ResponseEntity.ok(
                merchantService.getMerchantById(merchantId)
        );
    }



}
