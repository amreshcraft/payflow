# **Payflow - Payment Gateway Backend** ⭐⭐⭐⭐⭐

## Core Modules

- [] User Authentication (JWT)
- [x] Merchant Registration
- [ ] Customer API
- [ ] Create Payment Order
- [ ] Payment Link
- [ ] Payment Processing
- [ ] Payment Status
- [ ] Refund API
- [ ] Transaction History
- [ ] Webhook Callback

## Advanced Features

- [ ] Idempotency Key (Prevent Duplicate Payments)
- [ ] Optimistic Locking
- [ ] Pessimistic Locking
- [ ] @Transactional
- [ ] Redis Caching
- [ ] Rate Limiting
- [ ] Audit Logging
- [ ] Async Email Notifications
- [ ] Kafka/RabbitMQ (Optional)
- [ ] Docker
- [ ] AWS Deployment
- [ ] Unit Testing
- [ ] Integration Testing
- [ ] API Documentation (Swagger)

## Database Tables

- [ ] users
- [ ] merchants
- [ ] customers
- [ ] payment_orders
- [ ] payments
- [ ] refunds
- [ ] transactions
- [ ] webhooks
- [ ] audit_logs



# 📂 Folder Structure

```text
controller
├── admin
│   └── AdminController.java
├── apikey
│   └── ApiKeyController.java
├── auditlog
│   └── AuditLogController.java
├── auth
│   └── AuthController.java
├── customer
│   └── CustomerController.java
├── dashboard
│   └── DashboardController.java
├── health
│   └── HealthController.java
├── merchant
│   └── MerchantController.java
├── notification
│   └── NotificationController.java
├── payment
│   └── PaymentController.java
├── paymentlink
│   └── PaymentLinkController.java
├── paymentorder
│   └── PaymentOrderController.java
├── refund
│   └── RefundController.java
├── report
│   └── ReportController.java
├── settlement
│   └── SettlementController.java
├── transaction
│   └── TransactionController.java
└── webhook
    └── WebhookController.java
```

---

# 🎮 Controllers

| Controller | Description |
|------------|-------------|
| HealthController | Health Check APIs |
| AuthController | Authentication & JWT |
| MerchantController | Merchant Management |
| ApiKeyController | Merchant API Keys |
| CustomerController | Customer Management |
| PaymentOrderController | Payment Order Management |
| PaymentController | Payment Processing |
| PaymentLinkController | Payment Link Management |
| RefundController | Refund Processing |
| TransactionController | Transaction History |
| WebhookController | Webhook Events |
| SettlementController | Merchant Settlements |
| DashboardController | Dashboard Statistics |
| ReportController | Reports & Export |
| NotificationController | Notifications |
| AuditLogController | Audit Logs |
| AdminController | Admin Operations |

---




# 🚀 REST APIs

## ❤️ Health

| Method | Endpoint |
|---------|----------|
| GET | `/api/v1/health` |

---

## 🔐 Authentication

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/auth/register` |
| POST | `/api/v1/auth/login` |
| POST | `/api/v1/auth/refresh-token` |
| POST | `/api/v1/auth/logout` |
| POST | `/api/v1/auth/forgot-password` |
| POST | `/api/v1/auth/reset-password` |
| POST | `/api/v1/auth/change-password` |

---

## 🏪 Merchant

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/merchants` |
| GET | `/api/v1/merchants` |
| GET | `/api/v1/merchants/{merchantId}` |
| PUT | `/api/v1/merchants/{merchantId}` |
| PATCH | `/api/v1/merchants/{merchantId}/activate` |
| PATCH | `/api/v1/merchants/{merchantId}/deactivate` |
| DELETE | `/api/v1/merchants/{merchantId}` |

---

## 🔑 API Keys

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/merchants/{merchantId}/api-keys` |
| GET | `/api/v1/merchants/{merchantId}/api-keys` |
| PATCH | `/api/v1/merchants/{merchantId}/api-keys/{keyId}/rotate` |
| DELETE | `/api/v1/merchants/{merchantId}/api-keys/{keyId}` |

---

## 👤 Customers

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/customers` |
| GET | `/api/v1/customers` |
| GET | `/api/v1/customers/{customerId}` |
| PUT | `/api/v1/customers/{customerId}` |
| DELETE | `/api/v1/customers/{customerId}` |

---

## 📦 Payment Orders

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/payment-orders` |
| GET | `/api/v1/payment-orders` |
| GET | `/api/v1/payment-orders/{orderId}` |
| PATCH | `/api/v1/payment-orders/{orderId}/cancel` |

---

## 💳 Payments

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/payments` |
| GET | `/api/v1/payments` |
| GET | `/api/v1/payments/{paymentId}` |
| POST | `/api/v1/payments/{paymentId}/capture` |
| POST | `/api/v1/payments/{paymentId}/cancel` |
| POST | `/api/v1/payments/{paymentId}/retry` |

---

## 🔗 Payment Links

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/payment-links` |
| GET | `/api/v1/payment-links` |
| GET | `/api/v1/payment-links/{linkId}` |
| PATCH | `/api/v1/payment-links/{linkId}/enable` |
| PATCH | `/api/v1/payment-links/{linkId}/disable` |
| DELETE | `/api/v1/payment-links/{linkId}` |

---

## 💰 Refunds

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/refunds` |
| GET | `/api/v1/refunds` |
| GET | `/api/v1/refunds/{refundId}` |
| POST | `/api/v1/refunds/{refundId}/retry` |

---

## 📜 Transactions

| Method | Endpoint |
|---------|----------|
| GET | `/api/v1/transactions` |
| GET | `/api/v1/transactions/{transactionId}` |
| GET | `/api/v1/transactions/payment/{paymentId}` |
| GET | `/api/v1/transactions/customer/{customerId}` |

---

## 🔔 Webhooks

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/webhooks/payment` |
| POST | `/api/v1/webhooks/refund` |
| POST | `/api/v1/webhooks/settlement` |
| GET | `/api/v1/webhooks/events` |
| GET | `/api/v1/webhooks/events/{eventId}` |

---

## 🏦 Settlements

| Method | Endpoint |
|---------|----------|
| GET | `/api/v1/settlements` |
| GET | `/api/v1/settlements/{settlementId}` |
| GET | `/api/v1/settlements/merchant/{merchantId}` |

---

## 📊 Dashboard

| Method | Endpoint |
|---------|----------|
| GET | `/api/v1/dashboard/summary` |
| GET | `/api/v1/dashboard/revenue` |
| GET | `/api/v1/dashboard/payments` |
| GET | `/api/v1/dashboard/refunds` |

---

## 📈 Reports

| Method | Endpoint |
|---------|----------|
| GET | `/api/v1/reports/payments` |
| GET | `/api/v1/reports/refunds` |
| GET | `/api/v1/reports/transactions` |
| GET | `/api/v1/reports/settlements` |

---

## 🔔 Notifications

| Method | Endpoint |
|---------|----------|
| POST | `/api/v1/notifications/test` |
| GET | `/api/v1/notifications` |

---

## 📋 Audit Logs

| Method | Endpoint |
|---------|----------|
| GET | `/api/v1/audit-logs` |
| GET | `/api/v1/audit-logs/{auditId}` |

---

## 👑 Admin

| Method | Endpoint |
|---------|----------|
| GET | `/api/v1/admin/merchants` |
| GET | `/api/v1/admin/payments` |
| GET | `/api/v1/admin/customers` |
| GET | `/api/v1/admin/refunds` |
| PATCH | `/api/v1/admin/merchants/{merchantId}/suspend` |
| PATCH | `/api/v1/admin/merchants/{merchantId}/unsuspend` |

---

# 📌 API Summary

| Module | APIs |
|---------|-----:|
| Health | 1 |
| Authentication | 7 |
| Merchant | 7 |
| API Keys | 4 |
| Customer | 5 |
| Payment Order | 4 |
| Payment | 6 |
| Payment Link | 6 |
| Refund | 4 |
| Transaction | 4 |
| Webhook | 5 |
| Settlement | 3 |
| Dashboard | 4 |
| Report | 4 |
| Notification | 2 |
| Audit Log | 2 |
| Admin | 6 |
