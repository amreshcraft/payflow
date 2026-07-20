package com.amreshmaurya.payflow.controller.merchant;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.service.merchant.MerchantService;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    MerchantService merchantService;

    MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<MerchantResponse> createMerchant(@RequestBody CreateMerchantRequest createMerchantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.merchantService.createMerchant(createMerchantRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantResponse> getMerchant(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                merchantService.getMerchantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchantResponse> putMerchant(
            @PathVariable UUID id,
            @RequestBody CreateMerchantRequest createMerchantRequest) {
        return ResponseEntity.ok(merchantService.updateMerchant(id, createMerchantRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(
            @PathVariable UUID id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();

    }
}