
URI : http://{BaseURI}/api/orders
configured to localhost:8080
Sample Input:
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

sample Response:
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
