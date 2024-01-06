# Springboot Microservices-based E-commerce Application

This is a Java 17, Spring Boot, and Spring Cloud-based microservices E-commerce application. The services are built with Maven and utilize a PostgreSQL database.

## Technologies
- Java 17
- Spring Boot
- Spring Cloud
- Maven
- PostgreSQL

## Microservices Architecture:

Breakdown of services and their corresponding ports:

- api-gateway - Port:8080
- discovery-service - Port:8761
- config-service - Port:8081
- customer-service - Port:8181
- auth-service - Port:8182
- cart-service - Port:8183
- catalog-service - Port:8184
- image-service - Port:8185
- order-service - Port:8186

## Service Configuration

Each service in the application has a dedicated configuration file that can be found under the 'config-service' resource folder. These configuration files are integral to the functioning of their respective services.

### Instructions

1. Navigate to the 'config service' resource folder in the project directory.

2. Open the configuration file of the service you wish to edit.

3. Inside the configuration file, you will find various properties that define the behavior of the service.

4. Modify the values of these properties as per your requirements.

Please be mindful when modifying the configuration files. Incorrect configurations may lead to unexpected behaviors or even cause the service to fail. Always ensure to backup the original configuration before making any changes.

## Database:

Assuming a PostgreSQL database is running locally on port 55000, the following schemas should already be created:

- ecommerce_customer
- ecommerce_cart
- ecommerce_catalog
- ecommerce_order

Please change the username and password of the db properties under the respective service at /ecommerce-services/config-service/src/main/resources/config.

## Concurrency Control:

The product management service implements concurrent access control via optimistic Locking to ensure multiple users can safely read and update product information without conflicts.

## Clustering and High Availability:

The database stores user information, product data, and order history. Entities and relations are present in each microservice.

## APIs and Communication:

RESTful APIs are developed for each microservice, allowing them to communicate with each other. Endpoints for all services are implemented and communicate via the REST protocol.

## Authentication and Authorization:

User authentication and authorization are implemented using Spring Security with JWT for accessing protected endpoints.

## Running Locally:

Each microservice is a self-executing Spring Boot jar.

To create a jar for each service, navigate to the service directory and run the following commands:

```bash
cd service_directory
mvn clean install
```

To run a service, use the following command:

```bash
java -jar <jar_path>/<service_name_jar>.jar
```

Start the services in the following sequence:

- api-gateway - http://localhost:8080
 ```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "Demo Application Microservices APIs ",
    "description": "Documentation for all the Microservices in Demo Application",
    "contact": {
      "name": "Demo Application Development Team",
      "email": "demo_support@imaginarycompany.com"
    },
    "version": "v1.0.0"
  },
  "servers": [
    {
      "url": "http://192.168.0.100:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {},
  "components": {}
}
```
- discovery-service - http://localhost:8761![discovery-service.png](images%2Fdiscovery-service.png)
- config-service - http://localhost:8081
```json
{
  "name": "v3",
  "profiles": [
    "api-docs"
  ],
  "label": null,
  "version": null,
  "state": null,
  "propertySources": []
}
```
- customer-service - http://localhost:8181
```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "Customer API",
    "description": "API for managing customers",
    "version": "1.0.0"
  },
  "servers": [
    {
      "url": "http://customer-service:8181"
    }
  ],
  "paths": {
    "/api/v1/customers/shipping-address/{id}": {
      "get": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Get a shipping address by ID",
        "description": "Get a shipping address by ID",
        "operationId": "getShippingAddressById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Shipping address retrieved successfully",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          },
          "404": {
            "description": "Shipping address not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Update a shipping address by ID",
        "description": "Update a shipping address by ID",
        "operationId": "updateShippingAddress",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ShippingAddressRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Shipping address updated successfully",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid request body",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          },
          "404": {
            "description": "Shipping address not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Delete a shipping address by ID",
        "description": "Delete a shipping address by ID",
        "operationId": "deleteShippingAddress",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Shipping address deleted successfully"
          },
          "404": {
            "description": "Shipping address not found"
          },
          "500": {
            "description": "Internal server error"
          }
        }
      }
    },
    "/api/v1/wishlist": {
      "post": {
        "tags": [
          "Wishlist Controller"
        ],
        "summary": "Create a new item for wishlist",
        "description": "Create a new item for wishlist",
        "operationId": "create",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/WishlistRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Item created",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/WishlistResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid customer data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/WishlistResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/WishlistResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/customers": {
      "get": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Get a paginated list of customers",
        "description": "Returns a paginated list of all customers",
        "operationId": "getAll",
        "parameters": [
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved list of customers",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageCustomerResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageCustomerResponse"
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Create a new customer",
        "description": "Create a new customer",
        "operationId": "registration",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/CustomerRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Customer created",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid customer data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "409": {
            "description": "Customer already exists",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/customers/{customerId}/upload": {
      "post": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Upload a profile picture",
        "description": "Upload a profile picture",
        "operationId": "uploadImage",
        "parameters": [
          {
            "name": "customerId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "required": [
                  "image"
                ],
                "type": "object",
                "properties": {
                  "image": {
                    "type": "string",
                    "format": "binary"
                  }
                }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "Picture uploaded",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/customers/shipping-address": {
      "post": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Create a new shipping address",
        "description": "Create a new shipping address",
        "operationId": "createShippingAddress",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ShippingAddressRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Shipping address created successfully",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid request body",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ShippingAddressResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/wishlist/customer/{customerId}": {
      "get": {
        "tags": [
          "Wishlist Controller"
        ],
        "summary": "Get a customer wishlist item",
        "description": "Returns a wishlist items by customer id",
        "operationId": "getAllByCustomerId",
        "parameters": [
          {
            "name": "customerId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Retrieved wishlist item",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageWishlistResponse"
                }
              }
            }
          },
          "404": {
            "description": "Customer not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageWishlistResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageWishlistResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/customers/{id}/shipping-address/": {
      "get": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Get all shipping addresses by customer ID",
        "description": "Get all shipping addresses by customer ID",
        "operationId": "getAllShippingAddressesByCustomerId",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Shipping addresses retrieved successfully",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/ShippingAddressResponse"
                  }
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/ShippingAddressResponse"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/customers/{customerId}": {
      "get": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Get a customer by ID",
        "description": "Returns a single customer identified by its ID",
        "operationId": "getById",
        "parameters": [
          {
            "name": "customerId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Customer found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "404": {
            "description": "Customer not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Delete a customer by id",
        "description": "Delete a customer by its ID",
        "operationId": "deleteById_1",
        "parameters": [
          {
            "name": "customerId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Customer deleted"
          },
          "404": {
            "description": "Customer not found"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    },
    "/api/v1/customers/username/{username}": {
      "get": {
        "tags": [
          "Customer Controller"
        ],
        "summary": "Get a customer by username",
        "description": "Returns a single customer identified by its username",
        "operationId": "getByUsername",
        "parameters": [
          {
            "name": "username",
            "in": "path",
            "required": true,
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Customer found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "404": {
            "description": "Customer not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CustomerResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/wishlist/{wishlistId}": {
      "delete": {
        "tags": [
          "Wishlist Controller"
        ],
        "summary": "Delete a wishlist item by id",
        "description": "Delete a wishlist item by its ID",
        "operationId": "deleteById",
        "parameters": [
          {
            "name": "wishlistId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Wishlist item deleted"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "ShippingAddressRequest": {
        "type": "object",
        "properties": {
          "firstName": {
            "type": "string"
          },
          "lastName": {
            "type": "string"
          },
          "email": {
            "type": "string"
          },
          "phone": {
            "type": "string"
          },
          "streetAddress": {
            "type": "string"
          },
          "country": {
            "type": "string"
          },
          "city": {
            "type": "string"
          },
          "zipCode": {
            "type": "string"
          },
          "customerId": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "ShippingAddressResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "firstName": {
            "type": "string"
          },
          "lastName": {
            "type": "string"
          },
          "email": {
            "type": "string"
          },
          "phone": {
            "type": "string"
          },
          "streetAddress": {
            "type": "string"
          },
          "country": {
            "type": "string"
          },
          "city": {
            "type": "string"
          },
          "zipCode": {
            "type": "string"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "WishlistRequest": {
        "type": "object",
        "properties": {
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "productId": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "WishlistResponse": {
        "type": "object"
      },
      "CustomerRequest": {
        "type": "object",
        "properties": {
          "username": {
            "type": "string"
          },
          "password": {
            "type": "string"
          },
          "role": {
            "type": "string",
            "enum": [
              "USER",
              "ADMIN"
            ]
          },
          "firstName": {
            "maxLength": 20,
            "minLength": 2,
            "type": "string"
          },
          "lastName": {
            "maxLength": 20,
            "minLength": 2,
            "type": "string"
          }
        }
      },
      "CustomerResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "username": {
            "type": "string"
          },
          "password": {
            "type": "string"
          },
          "role": {
            "type": "string",
            "enum": [
              "USER",
              "ADMIN"
            ]
          },
          "firstName": {
            "type": "string"
          },
          "lastName": {
            "type": "string"
          },
          "imageUrl": {
            "type": "string"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "Pageable": {
        "type": "object",
        "properties": {
          "page": {
            "minimum": 0,
            "type": "integer",
            "format": "int32"
          },
          "size": {
            "minimum": 1,
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "type": "array",
            "items": {
              "type": "string"
            }
          }
        }
      },
      "PageWishlistResponse": {
        "type": "object",
        "properties": {
          "totalPages": {
            "type": "integer",
            "format": "int32"
          },
          "totalElements": {
            "type": "integer",
            "format": "int64"
          },
          "first": {
            "type": "boolean"
          },
          "last": {
            "type": "boolean"
          },
          "size": {
            "type": "integer",
            "format": "int32"
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/WishlistResponse"
            }
          },
          "number": {
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "pageable": {
            "$ref": "#/components/schemas/PageableObject"
          },
          "numberOfElements": {
            "type": "integer",
            "format": "int32"
          },
          "empty": {
            "type": "boolean"
          }
        }
      },
      "PageableObject": {
        "type": "object",
        "properties": {
          "offset": {
            "type": "integer",
            "format": "int64"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "paged": {
            "type": "boolean"
          },
          "unpaged": {
            "type": "boolean"
          },
          "pageNumber": {
            "type": "integer",
            "format": "int32"
          },
          "pageSize": {
            "type": "integer",
            "format": "int32"
          }
        }
      },
      "SortObject": {
        "type": "object",
        "properties": {
          "empty": {
            "type": "boolean"
          },
          "unsorted": {
            "type": "boolean"
          },
          "sorted": {
            "type": "boolean"
          }
        }
      },
      "PageCustomerResponse": {
        "type": "object",
        "properties": {
          "totalPages": {
            "type": "integer",
            "format": "int32"
          },
          "totalElements": {
            "type": "integer",
            "format": "int64"
          },
          "first": {
            "type": "boolean"
          },
          "last": {
            "type": "boolean"
          },
          "size": {
            "type": "integer",
            "format": "int32"
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/CustomerResponse"
            }
          },
          "number": {
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "pageable": {
            "$ref": "#/components/schemas/PageableObject"
          },
          "numberOfElements": {
            "type": "integer",
            "format": "int32"
          },
          "empty": {
            "type": "boolean"
          }
        }
      }
    }
  }
}
```
- auth-service - http://localhost:8182
```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "Auth API",
    "description": "API for authentication",
    "version": "1.0.0"
  },
  "servers": [
    {
      "url": "http://localhost:8182"
    }
  ],
  "paths": {
    "/api/v1/auth": {
      "post": {
        "tags": [
          "Authentication Controller"
        ],
        "summary": "Authenticate user",
        "operationId": "authenticate",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AuthenticationRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Order created",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AuthenticationResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid input data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AuthenticationResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AuthenticationResponse"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "AuthenticationRequest": {
        "type": "object",
        "properties": {
          "username": {
            "type": "string"
          },
          "password": {
            "type": "string"
          }
        }
      },
      "AuthenticationResponse": {
        "type": "object",
        "properties": {
          "jwtToken": {
            "type": "string"
          }
        }
      }
    }
  }
}
```
- cart-service - http://localhost:8183
```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "Cart API",
    "description": "API for managing cart",
    "version": "1.0.0"
  },
  "servers": [
    {
      "url": "http://localhost:8183"
    }
  ],
  "paths": {
    "/api/v1/carts/{cartId}/complete": {
      "put": {
        "tags": [
          "Cart Controller"
        ],
        "summary": "Create order",
        "description": "Clear customer cart and create order",
        "operationId": "completeCart",
        "parameters": [
          {
            "name": "cartId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully created order",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "404": {
            "description": "Cart not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/carts": {
      "post": {
        "tags": [
          "Cart Controller"
        ],
        "summary": "Create a new cart",
        "description": "Creates a new cart",
        "operationId": "createCart",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/CartRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Successfully created cart",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/carts/{cartId}/items": {
      "post": {
        "tags": [
          "Cart Controller"
        ],
        "summary": "Create a new cart item",
        "description": "Returns a single cart identified by customer id",
        "operationId": "createCartItem",
        "parameters": [
          {
            "name": "cartId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/CartItemRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Successfully created cart item",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/carts/{cartId}": {
      "get": {
        "tags": [
          "Cart Controller"
        ],
        "summary": "Get a cart by ID",
        "description": "Returns a single cart identified by its ID",
        "operationId": "getCartById",
        "parameters": [
          {
            "name": "cartId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved cart",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "404": {
            "description": "cart not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/carts/customer/{customerId}": {
      "get": {
        "tags": [
          "Cart Controller"
        ],
        "summary": "Get a cart by customer id",
        "description": "Get a cart by customer id",
        "operationId": "getCartByCustomerId",
        "parameters": [
          {
            "name": "customerId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved cart",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "404": {
            "description": "Customer not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/carts/{cartId}/items/{itemId}": {
      "delete": {
        "tags": [
          "Cart Controller"
        ],
        "summary": "Delete cart item",
        "description": "Delete cart item",
        "operationId": "deleteCartItem",
        "parameters": [
          {
            "name": "cartId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "itemId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully deleted cart item",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CartResponse"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "CartItemResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "productId": {
            "type": "integer",
            "format": "int64"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          },
          "price": {
            "type": "number"
          },
          "cart": {
            "$ref": "#/components/schemas/CartResponse"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "CartResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "cartItems": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/CartItemResponse"
            }
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "CartRequest": {
        "required": [
          "customerId"
        ],
        "type": "object",
        "properties": {
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "name": {
            "type": "string"
          }
        }
      },
      "CartItemRequest": {
        "required": [
          "price",
          "productId",
          "quantity"
        ],
        "type": "object",
        "properties": {
          "productId": {
            "type": "integer",
            "format": "int64"
          },
          "quantity": {
            "maximum": 10000000,
            "minimum": 0,
            "type": "integer",
            "format": "int32"
          },
          "price": {
            "maximum": 99999999.99,
            "exclusiveMaximum": false,
            "minimum": 0,
            "exclusiveMinimum": false,
            "type": "number"
          }
        }
      }
    }
  }
}
```
- catalog-service - http://localhost:8184
```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "Catalog API",
    "description": "API for managing products & categories",
    "version": "1.0.0"
  },
  "servers": [
    {
      "url": "http://localhost:8184"
    }
  ],
  "paths": {
    "/api/v1/catalog/reviews/{id}": {
      "get": {
        "tags": [
          "Review Controller"
        ],
        "summary": "Get a review by ID",
        "description": "Returns a single review identified by its ID",
        "operationId": "getById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved the review",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          },
          "404": {
            "description": "Review not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "Review Controller"
        ],
        "summary": "Update Review",
        "description": "Update a review by its ID",
        "operationId": "updateReview",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ReviewRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Review updated",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid request",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          },
          "404": {
            "description": "Review not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "Review Controller"
        ],
        "summary": "Delete a review by id",
        "description": "Delete a review by its ID",
        "operationId": "deleteReview",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Review deleted"
          },
          "404": {
            "description": "Review not found"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    },
    "/api/v1/catalog/products/{productId}": {
      "get": {
        "tags": [
          "Product Controller"
        ],
        "summary": "Get a product by ID",
        "description": "Returns a single product identified by its ID",
        "operationId": "getProductById",
        "parameters": [
          {
            "name": "productId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved product",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          },
          "404": {
            "description": "Product not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "Product Controller"
        ],
        "summary": "Update product",
        "description": "Update a product by its ID",
        "operationId": "updateProduct",
        "parameters": [
          {
            "name": "productId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "multipart/form-data": {
              "schema": {
                "$ref": "#/components/schemas/ProductRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Product updated",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          },
          "404": {
            "description": "Category not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "Product Controller"
        ],
        "summary": "Delete a product by id",
        "description": "Delete a product by its ID",
        "operationId": "deleteProduct",
        "parameters": [
          {
            "name": "productId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Product deleted"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    },
    "/api/v1/catalog/products/{productId}/orders/count/{increase}": {
      "put": {
        "tags": [
          "Product Controller"
        ],
        "summary": "Update orders count in product",
        "description": "Update orders count in product by product ID",
        "operationId": "updateOrderCount",
        "parameters": [
          {
            "name": "productId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "increase",
            "in": "path",
            "required": true,
            "schema": {
              "type": "boolean"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully updated orders count"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    },
    "/api/v1/catalog/categories/{id}": {
      "get": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Get a category by ID",
        "description": "Returns a single category identified by its ID",
        "operationId": "getById_1",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved the category",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "404": {
            "description": "Category not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Update Category",
        "description": "Update a category by its ID",
        "operationId": "updateCategory",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/CategoryRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Category updated",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid request",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "404": {
            "description": "Category not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Delete a category by id",
        "description": "Delete a category by its ID",
        "operationId": "deleteCategory",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Category deleted"
          },
          "404": {
            "description": "Category not found"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    },
    "/api/v1/catalog/reviews": {
      "post": {
        "tags": [
          "Review Controller"
        ],
        "summary": "Create a new review",
        "description": "Create a new review",
        "operationId": "create",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ReviewRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Review created",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid input data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ReviewResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/products": {
      "get": {
        "tags": [
          "Product Controller"
        ],
        "summary": "Get a paginated list of products",
        "description": "Returns a paginated list of all products",
        "operationId": "getProducts",
        "parameters": [
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved list of products",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageProductResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageProductResponse"
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "Product Controller"
        ],
        "summary": "Create a new product",
        "description": "Creates a new product",
        "operationId": "createProduct",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ProductRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Successfully created product",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/products/{productId}/upload": {
      "post": {
        "tags": [
          "Product Controller"
        ],
        "summary": "Upload new images for a product",
        "description": "Upload new images for a product",
        "operationId": "uploadImage",
        "parameters": [
          {
            "name": "images",
            "in": "query",
            "required": true,
            "schema": {
              "type": "array",
              "items": {
                "type": "string",
                "format": "binary"
              }
            }
          },
          {
            "name": "productId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully uploaded images",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ProductResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/categories": {
      "get": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Get All Categories",
        "description": "Retrieve all categories",
        "operationId": "getCategories",
        "responses": {
          "200": {
            "description": "Successfully retrieved list of categories",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/CategoryResponse"
                  }
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/CategoryResponse"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Create a new category",
        "description": "Create a new category",
        "operationId": "createCategory",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/CategoryRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Category created",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid input data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/categories/{id}/banner": {
      "post": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Upload a banners to category",
        "description": "Upload a banners to category by its ID",
        "operationId": "uploadBanners",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "Banners",
            "in": "query",
            "required": true,
            "schema": {
              "type": "array",
              "items": {
                "type": "string",
                "format": "binary"
              }
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Banner uploaded",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "404": {
            "description": "Category not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CategoryResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/reviews/product/{id}": {
      "get": {
        "tags": [
          "Review Controller"
        ],
        "summary": "Get Reviews by Product ID",
        "description": "Retrieve all reviews belonging to a product",
        "operationId": "getByProductId",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved the reviews",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageReviewResponse"
                }
              }
            }
          },
          "404": {
            "description": "Review not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageReviewResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageReviewResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/reviews/customer/{id}": {
      "get": {
        "tags": [
          "Review Controller"
        ],
        "summary": "Get Reviews by Customer ID",
        "description": "Retrieve all reviews belonging to a customer",
        "operationId": "getByCustomerId",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved the reviews",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageReviewResponse"
                }
              }
            }
          },
          "404": {
            "description": "Review not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageReviewResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageReviewResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/categories/{id}/products": {
      "get": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Get Products by Category ID",
        "description": "Retrieve all products belonging to a category",
        "operationId": "getProductsByCategoryId",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved the products",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageProductResponse"
                }
              }
            }
          },
          "404": {
            "description": "Category not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageProductResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error occurred",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageProductResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/catalog/categories/banner/{id}": {
      "delete": {
        "tags": [
          "Category Controller"
        ],
        "summary": "Delete a banner by id",
        "description": "Delete a banner by its ID",
        "operationId": "deleteBanner",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Banner deleted"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "ReviewRequest": {
        "required": [
          "customerId",
          "productId",
          "rating"
        ],
        "type": "object",
        "properties": {
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "text": {
            "maxLength": 150,
            "minLength": 10,
            "type": "string"
          },
          "productId": {
            "type": "integer",
            "format": "int64"
          },
          "rating": {
            "maximum": 5,
            "minimum": 1,
            "type": "integer",
            "format": "int32"
          }
        }
      },
      "ReviewResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "text": {
            "type": "string"
          },
          "rating": {
            "type": "integer",
            "format": "int32"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "ProductRequest": {
        "required": [
          "categoryId",
          "description",
          "price",
          "productName",
          "quantity"
        ],
        "type": "object",
        "properties": {
          "productName": {
            "maxLength": 40,
            "minLength": 3,
            "type": "string"
          },
          "description": {
            "maxLength": 500,
            "minLength": 20,
            "type": "string"
          },
          "price": {
            "maximum": 99999999.99,
            "exclusiveMaximum": false,
            "minimum": 0,
            "exclusiveMinimum": false,
            "type": "number"
          },
          "quantity": {
            "maximum": 10000000,
            "minimum": 0,
            "type": "integer",
            "format": "int32"
          },
          "categoryId": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "BannerResponse": {
        "type": "object",
        "properties": {
          "src": {
            "type": "string"
          }
        }
      },
      "CategoryResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "name": {
            "type": "string"
          },
          "level": {
            "type": "integer",
            "format": "int32"
          },
          "childCategories": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/CategoryResponse"
            }
          },
          "parentCategory": {
            "$ref": "#/components/schemas/CategoryResponse"
          },
          "banners": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/BannerResponse"
            }
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "ProductImageResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "src": {
            "type": "string"
          }
        }
      },
      "ProductResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "productName": {
            "type": "string"
          },
          "description": {
            "type": "string"
          },
          "price": {
            "type": "number"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          },
          "category": {
            "$ref": "#/components/schemas/CategoryResponse"
          },
          "images": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/ProductImageResponse"
            }
          },
          "averageStar": {
            "type": "number",
            "format": "float"
          },
          "ordersCount": {
            "type": "integer",
            "format": "int32"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "CategoryRequest": {
        "required": [
          "name"
        ],
        "type": "object",
        "properties": {
          "name": {
            "maxLength": 25,
            "minLength": 3,
            "type": "string"
          },
          "parentCategoryId": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "Pageable": {
        "type": "object",
        "properties": {
          "page": {
            "minimum": 0,
            "type": "integer",
            "format": "int32"
          },
          "size": {
            "minimum": 1,
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "type": "array",
            "items": {
              "type": "string"
            }
          }
        }
      },
      "PageReviewResponse": {
        "type": "object",
        "properties": {
          "totalPages": {
            "type": "integer",
            "format": "int32"
          },
          "totalElements": {
            "type": "integer",
            "format": "int64"
          },
          "first": {
            "type": "boolean"
          },
          "last": {
            "type": "boolean"
          },
          "size": {
            "type": "integer",
            "format": "int32"
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/ReviewResponse"
            }
          },
          "number": {
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "pageable": {
            "$ref": "#/components/schemas/PageableObject"
          },
          "numberOfElements": {
            "type": "integer",
            "format": "int32"
          },
          "empty": {
            "type": "boolean"
          }
        }
      },
      "PageableObject": {
        "type": "object",
        "properties": {
          "offset": {
            "type": "integer",
            "format": "int64"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "pageNumber": {
            "type": "integer",
            "format": "int32"
          },
          "pageSize": {
            "type": "integer",
            "format": "int32"
          },
          "paged": {
            "type": "boolean"
          },
          "unpaged": {
            "type": "boolean"
          }
        }
      },
      "SortObject": {
        "type": "object",
        "properties": {
          "empty": {
            "type": "boolean"
          },
          "unsorted": {
            "type": "boolean"
          },
          "sorted": {
            "type": "boolean"
          }
        }
      },
      "PageProductResponse": {
        "type": "object",
        "properties": {
          "totalPages": {
            "type": "integer",
            "format": "int32"
          },
          "totalElements": {
            "type": "integer",
            "format": "int64"
          },
          "first": {
            "type": "boolean"
          },
          "last": {
            "type": "boolean"
          },
          "size": {
            "type": "integer",
            "format": "int32"
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/ProductResponse"
            }
          },
          "number": {
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "pageable": {
            "$ref": "#/components/schemas/PageableObject"
          },
          "numberOfElements": {
            "type": "integer",
            "format": "int32"
          },
          "empty": {
            "type": "boolean"
          }
        }
      }
    }
  }
}
```
- image-service - http://localhost:8185
```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "Image API",
    "description": "API for uploading images",
    "version": "1.0.0"
  },
  "servers": [
    {
      "url": "http://localhost:8185"
    }
  ],
  "paths": {
    "/api/v1/images/upload": {
      "post": {
        "tags": [
          "Image Controller"
        ],
        "summary": "Upload image",
        "operationId": "uploadImage",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "required": [
                  "file"
                ],
                "type": "object",
                "properties": {
                  "file": {
                    "type": "string",
                    "format": "binary"
                  }
                }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "Image uploaded successfully",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          },
          "400": {
            "description": "Invalid file format",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {}
}
```
- order-service - http://localhost:8186
```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "Order API",
    "description": "API for managing orders",
    "version": "1.0.0"
  },
  "servers": [
    {
      "url": "http://localhost:8186"
    }
  ],
  "paths": {
    "/api/v1/orders/{id}": {
      "get": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Get an order by ID",
        "description": "Returns a single order identified by its ID",
        "operationId": "getOrderById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successfully retrieved order",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          },
          "404": {
            "description": "Order not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Update order by ID",
        "description": "Update a category by its ID",
        "operationId": "updateOrder",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/OrderRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Order updated",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid input data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          },
          "404": {
            "description": "Order not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Delete an order by id",
        "description": "Delete an order by its ID",
        "operationId": "deleteOrder",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Order deleted"
          },
          "404": {
            "description": "Order not found"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    },
    "/api/v1/orders": {
      "get": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Get a paginated list of order",
        "description": "Returns a paginated list of all orders",
        "operationId": "getAll",
        "parameters": [
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "All orders retrieved successfully",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderResponse"
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Create order",
        "description": "Create a new order",
        "operationId": "create",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/OrderRequest"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "Order created",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          },
          "400": {
            "description": "Invalid input data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/OrderResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/orders/{id}/items": {
      "get": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Get Order items by Order ID",
        "description": "Retrieve all order items belonging to a order",
        "operationId": "getOrderItemsByOrder",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Order items retrieved successfully",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderItemResponse"
                }
              }
            }
          },
          "404": {
            "description": "Order not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderItemResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderItemResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/orders/customer/{id}": {
      "get": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Get Orders by Customer ID",
        "description": "Retrieve all orders belonging to a customer",
        "operationId": "getOrdersByUser",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Orders retrieved successfully",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderResponse"
                }
              }
            }
          },
          "404": {
            "description": "User not found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderResponse"
                }
              }
            }
          },
          "500": {
            "description": "Internal server error",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageOrderResponse"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/orders/items/{id}": {
      "delete": {
        "tags": [
          "Order Controller"
        ],
        "summary": "Delete an order item by id",
        "description": "Delete an order item by its ID",
        "operationId": "deleteOrderItem",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "204": {
            "description": "Order item deleted"
          },
          "404": {
            "description": "Order item not found"
          },
          "500": {
            "description": "Internal server error occurred"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "CartItemResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "productId": {
            "type": "integer",
            "format": "int64"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          },
          "price": {
            "type": "number"
          },
          "cart": {
            "$ref": "#/components/schemas/CartResponse"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "CartResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "OrderRequest": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "cartItems": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/CartItemResponse"
            }
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          },
          "orderDescription": {
            "maxLength": 25,
            "minLength": 3,
            "type": "string"
          },
          "orderStatus": {
            "type": "string",
            "enum": [
              "PENDING",
              "PROCESSING",
              "SHIPPED",
              "DELIVERED",
              "CANCELLED",
              "REFUNDED",
              "COMPLETED"
            ]
          }
        }
      },
      "OrderItemResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          },
          "productId": {
            "type": "integer",
            "format": "int64"
          },
          "order": {
            "$ref": "#/components/schemas/OrderResponse"
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "OrderResponse": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "customerId": {
            "type": "integer",
            "format": "int64"
          },
          "orderDescription": {
            "type": "string"
          },
          "orderFee": {
            "type": "number"
          },
          "orderStatus": {
            "type": "string",
            "enum": [
              "PENDING",
              "PROCESSING",
              "SHIPPED",
              "DELIVERED",
              "CANCELLED",
              "REFUNDED",
              "COMPLETED"
            ]
          },
          "orderItems": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/OrderItemResponse"
            }
          },
          "createdAt": {
            "type": "string",
            "format": "date-time"
          },
          "updatedAt": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "Pageable": {
        "type": "object",
        "properties": {
          "page": {
            "minimum": 0,
            "type": "integer",
            "format": "int32"
          },
          "size": {
            "minimum": 1,
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "type": "array",
            "items": {
              "type": "string"
            }
          }
        }
      },
      "PageOrderResponse": {
        "type": "object",
        "properties": {
          "totalPages": {
            "type": "integer",
            "format": "int32"
          },
          "totalElements": {
            "type": "integer",
            "format": "int64"
          },
          "first": {
            "type": "boolean"
          },
          "last": {
            "type": "boolean"
          },
          "size": {
            "type": "integer",
            "format": "int32"
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/OrderResponse"
            }
          },
          "number": {
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "numberOfElements": {
            "type": "integer",
            "format": "int32"
          },
          "pageable": {
            "$ref": "#/components/schemas/PageableObject"
          },
          "empty": {
            "type": "boolean"
          }
        }
      },
      "PageableObject": {
        "type": "object",
        "properties": {
          "offset": {
            "type": "integer",
            "format": "int64"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "pageNumber": {
            "type": "integer",
            "format": "int32"
          },
          "pageSize": {
            "type": "integer",
            "format": "int32"
          },
          "paged": {
            "type": "boolean"
          },
          "unpaged": {
            "type": "boolean"
          }
        }
      },
      "SortObject": {
        "type": "object",
        "properties": {
          "empty": {
            "type": "boolean"
          },
          "unsorted": {
            "type": "boolean"
          },
          "sorted": {
            "type": "boolean"
          }
        }
      },
      "PageOrderItemResponse": {
        "type": "object",
        "properties": {
          "totalPages": {
            "type": "integer",
            "format": "int32"
          },
          "totalElements": {
            "type": "integer",
            "format": "int64"
          },
          "first": {
            "type": "boolean"
          },
          "last": {
            "type": "boolean"
          },
          "size": {
            "type": "integer",
            "format": "int32"
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/OrderItemResponse"
            }
          },
          "number": {
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "$ref": "#/components/schemas/SortObject"
          },
          "numberOfElements": {
            "type": "integer",
            "format": "int32"
          },
          "pageable": {
            "$ref": "#/components/schemas/PageableObject"
          },
          "empty": {
            "type": "boolean"
          }
        }
      }
    }
  }
}
```

API documentation for each service can be found at 
```http://localhost:<service_port>/v3/api-docs``` and its Swagger UI at ```http://localhost:<service_port>/swagger-ui/index.html```