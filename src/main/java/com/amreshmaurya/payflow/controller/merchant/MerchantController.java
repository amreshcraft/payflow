package com.amreshmaurya.payflow.controller.merchant;

import java.time.LocalDateTime;
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

import com.amreshmaurya.payflow.common.response.ApiResponse;
import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.request.UpdateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.service.merchant.MerchantService;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    MerchantService merchantService;

    MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MerchantResponse>> createMerchant(
            @RequestBody CreateMerchantRequest createMerchantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant created successfully")
                        .data(this.merchantService.createMerchant(createMerchantRequest))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MerchantResponse>> getMerchant(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant retrieved successfully")
                        .data(merchantService.getMerchantById(id))
                        .timestamp(LocalDateTime.now())
                        .build());
    }


   @GetMapping("merchantCode/{merchantCode}")
    public ResponseEntity<ApiResponse<MerchantResponse>> getMerchantByMerchantCode(
            @PathVariable String merchantCode) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant retrieved successfully")
                        .data(merchantService.getMerchantByMerchantCode(merchantCode))
                        .timestamp(LocalDateTime.now())
                        .build());
    }


   @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<MerchantResponse>> getMerchantByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant retrieved successfully")
                        .data(merchantService.getMerchantByEmail(email))
                        .timestamp(LocalDateTime.now())
                        .build());
    }



       @GetMapping("/phone/{phone}")
    public ResponseEntity<ApiResponse<MerchantResponse>> getMerchantByPhone(
            @PathVariable String phone ) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant retrieved successfully")
                        .data(merchantService.getMerchantByPhone(phone))
                        .timestamp(LocalDateTime.now())
                        .build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MerchantResponse>> putMerchant(
            @PathVariable UUID id,
            @RequestBody UpdateMerchantRequest updateMerchantRequest) {
        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant updated successfully")
                        .data(merchantService.updateMerchant(id, updateMerchantRequest))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMerchant(
            @PathVariable UUID id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Merchant deleted successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}