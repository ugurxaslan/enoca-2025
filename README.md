# 🛍️ Enoca Project 2025

Bu proje, müşteri (**Customer**), ürün (**Product**), sepet (**Cart**) ve sipariş (**Order**) yönetimini sağlayan bir **Spring Boot** uygulamasıdır.

Tüm tablolar, ortak alanları barındıran **BaseEntity** sınıfından miras alır. Projede müşteri, ürün, sepet ve sipariş arasında gerekli ilişkiler kurulmuş, stok ve fiyat yönetimi entegre edilmiştir. Müşterilerin sepet ve sipariş süreçleri gerçekçi e-ticaret senaryolarına uygun şekilde ele alınmıştır.

---

## 🚀 Çalıştırma

1. `src/main/resources/application.properties` dosyasını kendi yerel bilgisayarınıza göre güncelleyin.
2. Proje dizinine girin:
   ```bash
   cd enoca-project-2025
   ```
3. Gerekli servisleri ayağa kaldırmak için Docker Compose çalıştırın:
   ```bash
   docker compose up -d
   ```
4. Maven ile derleyin ve çalıştırın:
   ```bash
   ./mvn install
   ./mvn spring-boot:run
   ```
5. Swagger UI üzerinden API dokümantasyonuna erişin:  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🗂️ Proje Özellikleri

- **BaseEntity**: Tüm entity sınıflarına miras olan temel alanlar.
- **Customer**: Sisteme kayıtlı kullanıcı bilgilerini tutar. Bir müşterinin **1 sepeti** ve **birden fazla siparişi** olabilir.
- **Cart**: Müşterinin sepetini temsil eder. Sepet içeriği her işlemde (ekleme, çıkarma, miktar artırma/azaltma) stok kontrolüne göre güncellenir ve **total price** yeniden hesaplanır.
- **Order**: Sipariş bilgilerini saklar. Sipariş sırasında ürünlerin anlık fiyatı **OrderItem** tablosunda tutulur. Ürün fiyatı güncellense bile müşteri geçmiş siparişlerinde o anki fiyatı görebilir.
- **Product**: Ürün bilgilerini ve stok miktarını içerir. Stok bitmişse ürün sepete eklenemez ve sipariş verilemez.

- [ER Diagram Linki](https://miro.com/app/board/uXjVJTBMCUg=/?share_link_id=789464132536)

---

## 📌 API Endpointleri

### 👤 Customer Service

- **addCustomer**  
  `POST /api/customers/`  
  Yeni bir kullanıcıyı sisteme dahil eder.

---

### 📦 Product Service

- **createProduct**  
  `POST /api/products`  
  Yeni bir ürün ekler.

- **getProduct**  
  `GET /api/products/{productId}`  
  Verilen `productId` ile ürünü getirir.

- **updateProduct**  
  `PUT /api/products/{productId}`  
  Ürün bilgilerini günceller.

- **deleteProduct**  
  `DELETE /api/products/{productId}`  
  Ürünü siler. Eğer ürün bir **cart item** veya **order item** ile ilişkiliyse, zorunlu ilişkiden dolayı silinemez.

---

### 🛒 Cart Service

- **getCart**  
  `GET /api/carts/{customerId}`  
  Belirtilen müşteri ID'sine ait sepeti getirir.

- **updateCart**  
  `PUT /api/carts/{customerId}`  
  Sepeti günceller. Stok miktarları kontrol edilir, fazla ürünler sepetten düşülür ve **total price** yeniden hesaplanır.

- **addProductToCart**  
  `PUT /api/carts/{customerId}/addProductToCart/{productId}`  
  Stokta varsa ürünü sepete ekler veya sepette varsa miktarını 1 artırır.

- **removeProductFromCart**  
  `PUT /api/carts/{customerId}/removeProductFromCart/{productId}`  
  Sepetten ürünü kaldırır. Eğer birden fazla varsa miktarı 1 azaltır, tek ise tamamen siler.

- **emptyCart**  
  `PUT /api/carts/{customerId}/emptyCart`  
  Sepeti tamamen boşaltır.

---

### 📦📦 Order Service

- **placeOrder**  
  `POST /api/orders/{customerId}`  
  Müşterinin sepetindeki ürünleri stok kontrolünden sonra siparişe dönüştürür.

  - Sipariş anındaki ürün fiyatı `order item` üzerinde saklanır.
  - Siparişe özel bir **orderCode** üretilir.

- **getOrderForCode**  
  `GET /api/orders/{orderCode}`  
  Verilen kod ile sipariş bilgilerini getirir (ürünler ve alış fiyatı dahil).

- **getAllOrdersForCustomer**  
  `GET /api/orders/{customerId}`  
  Belirtilen müşterinin tüm siparişlerini listeler.

---

## 🛠️ Kullanılan Teknolojiler

- **Java 17**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **Spring Validation**
- **Lombok**
- **Maven**
- **RESTful API**
- **Docker**
- **MySQL**

---
