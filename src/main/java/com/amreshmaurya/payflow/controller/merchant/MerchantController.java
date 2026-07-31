package com.amreshmaurya.payflow.controller.merchant;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amreshmaurya.payflow.common.response.ApiResponse;
import com.amreshmaurya.payflow.dto.merchant.request.CreateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.request.UpdateMerchantRequest;
import com.amreshmaurya.payflow.dto.merchant.response.MerchantResponse;
import com.amreshmaurya.payflow.mapper.MerchantMapper;
import com.amreshmaurya.payflow.service.merchant.MerchantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;
    private final MerchantMapper merchantMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<MerchantResponse>> createMerchant(
            @Valid @RequestBody CreateMerchantRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant created successfully")
                        .data(merchantMapper.toResponse(merchantService.createMerchant(request)))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MerchantResponse>> getMerchantById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant retrieved successfully")
                        .data(merchantMapper.toResponse(merchantService.getMerchantById(id)))           
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping("/code/{merchantCode}")
    public ResponseEntity<ApiResponse<MerchantResponse>> getMerchantByMerchantCode(
            @PathVariable String merchantCode) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant retrieved successfully")
                        .data(merchantMapper.toResponse(merchantService.getMerchantByMerchantCode(merchantCode)))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping("/email")
    public ResponseEntity<ApiResponse<MerchantResponse>> getMerchantByEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant retrieved successfully")
                        .data(merchantMapper.toResponse(merchantService.getMerchantByEmail(email)))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MerchantResponse>> updateMerchant(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateMerchantRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant updated successfully")
                        .data(merchantMapper.toResponse(merchantService.updateMerchant(id, request)))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    /*
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

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<MerchantResponse>> activateMerchant(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant activated successfully")
                        .data(merchantService.activateMerchant(id))
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<MerchantResponse>> deactivateMerchant(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<MerchantResponse>builder()
                        .success(true)
                        .message("Merchant deactivated successfully")
                        .data(merchantService.deactivateMerchant(id))
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    */

}