# 📦 Order Management System

A containerized Spring Boot application for managing customer orders. This service provides a **RESTful API** to create and retrieve orders, backed by a persistent database and managed through Docker[cite: 1, 2].

---

## 🚀 Getting Started

### Prerequisites
*   **Docker** installed on your machine[cite: 1, 2].
*   **Docker Compose** (included with Docker Desktop)[cite: 1, 2].

### Installation & Setup
To build and start the service, run the following command in your project root:

```bash
docker compose up --build
```[cite: 1, 2]

The service will be available at: **`http://localhost:8080`**[cite: 1, 2]

---

## 🛠 API Documentation

### **Create Order**
*   **Endpoint:** `POST /api/orders`[cite: 1, 2]
*   **Content-Type:** `application/json`[cite: 2]

#### **Sample Request Body**
```json
{
  "customerId": "CUST-99284",
  "customerEmail": "catherine@example.com",
  "items": [
    {
      "productId": "PROD-002",
      "productName": "Wireless Mechanical Keyboard",
      "quantity": 1,
      "unitPrice": 129.99
    },
    {
      "productId": "PROD-043",
      "productName": "USB-C Braided Cable",
      "quantity": 2,
      "unitPrice": 15.50
    }
  ],
  "totalAmount": 160.99
}
```[cite: 1, 2]

#### **Sample Response**
```json
{
    "id": "9477fa0a-bf94-4522-97cb-573b496ab859",
    "customerId": "CUST-99284",
    "customerEmail": "catherine@example.com",
    "totalAmount": 145.49,
    "currency": "USD",
    "status": "PLACED",
    "placedAt": "2026-05-09T10:04:42.484502200Z",
    "updatedAt": null,
    "items": [
        {
            "id": null,
            "productId": "PROD-002",
            "productName": "Wireless Mechanical Keyboard",
            "quantity": 1,
            "unitPrice": 129.99
        },
        {
            "id": null,
            "productId": "PROD-043",
            "productName": "USB-C Braided Cable",
            "quantity": 2,
            "unitPrice": 15.50
        }
    ]
}
```[cite: 1, 2]

---

## 🏗 Technical Stack
*   **Framework:** Spring Boot[cite: 1, 2]
*   **Language:** Java[cite: 1, 2]
*   **Containerization:** Docker & Docker Compose[cite: 1, 2]
*   **Default Port:** 8080[cite: 2]
