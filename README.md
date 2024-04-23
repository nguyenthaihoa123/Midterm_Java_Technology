# 52100413 - Nguyễn Thái Hòa

## Thông tin đề tài:

Đối với đề tài giữa kì, em chọn thiết kế một trang web bán áo quần đơn giản sử dụng Templete Engine là Thymeleaf và kết hợp cùng với Spring Boot để hoàn thành ứng dụng.

## Công nghệ sử dụng trong đề tài:

**MySQL**: MySQL là một hệ thống quản trị cơ sở dữ liệu quan hệ (RDBMS) mã nguồn mở, được phát triển bởi Oracle Corporation. Nó hoạt động theo mô hình client-server, với máy chủ lưu trữ dữ liệu và máy khách truy cập dữ liệu từ xa.
**Spring Boot**: Spring Boot là một framework mã nguồn mở giúp đơn giản hóa việc tạo các ứng dụng Spring. Nó tự động cấu hình nhiều khía cạnh của ứng dụng, giúp bạn tiết kiệm thời gian và tập trung vào việc viết mã kinh doanh.
**Thymeleaf**: Thymeleaf là một engine template mã nguồn mở được sử dụng để tạo giao diện người dùng web cho các ứng dụng Java. Nó cho phép bạn kết hợp HTML với các biểu thức Thymeleaf để tạo các trang web động một cách dễ dàng.
**Spring Data JPA** là một framework mã nguồn mở giúp đơn giản hóa việc truy cập dữ liệu trong các ứng dụng Java bằng cách sử dụng Java Persistence API (JPA). Nó cung cấp một lớp trừu tượng giúp bạn thao tác với các cơ sở dữ liệu quan hệ (RDBMS) một cách dễ dàng và hiệu quả.
**Sự kết hợp của Thymeleaf và Spring Boot**: Thymeleaf và Spring Boot là hai công cụ mạnh mẽ được sử dụng rộng rãi trong phát triển ứng dụng web Java. Khi kết hợp với nhau, chúng tạo ra một giải pháp toàn diện và hiệu quả để xây dựng các ứng dụng web hiện đại.

-   Lợi ích:
    -   Dễ sử dụng: Thymeleaf cung cấp cú pháp HTML quen thuộc với các thẻ và thuộc tính bổ sung để xử lý logic và truy cập dữ liệu. Spring Boot tự động cấu hình nhiều khía cạnh của ứng dụng, giúp bạn tiết kiệm thời gian và tập trung vào việc viết mã kinh doanh.
    -   Mạnh mẽ: Thymeleaf hỗ trợ nhiều tính năng nâng cao như biểu thức điều kiện, vòng lặp, và phân tích cú pháp. Spring Boot cung cấp nhiều chức năng tích hợp sẵn như Spring MVC, Spring Data, và Spring Security.

## Annotation trong spring boot và các annotation dùng trong đề tài:

Trong Spring Boot, Annotation đóng vai trò quan trọng trong việc định nghĩa và cấu hình các thành phần ứng dụng, giúp đơn giản hóa và tăng tốc độ phát triển.

-   Sau đây là một số Annotation quan trọng được sử dụng trong đề tài:
    **@RestController:**
    Chú thích Controller để xử lý các yêu cầu RESTful.
    Kết hợp cả @Controller và @ResponseBody.
    **@RequestMapping:**
    Annotation này được sử dụng để ánh xạ các request HTTP đến các method trong controller.
    **@Autowired:**
    Annotation này được sử dụng để tiêm các dependency vào các bean Spring.
    **@Service:**
    Annotation này được sử dụng để đánh dấu các class là các bean Spring cung cấp các chức năng nghiệp vụ.
    **@Repository:**
    Annotation này được sử dụng để đánh dấu các class là các bean Spring truy cập dữ liệu.
    **@Configuration:**
    Đánh dấu class là một class cấu hình Spring.
    **@Bean:**
    Annotation này được sử dụng để định nghĩa một bean Spring thủ công.
    **@PostMapping, @GetMapping, @PutMapping, @DeleteMapping:**
    Tương ứng với các phương thức HTTP POST, GET, PUT, DELETE.

## Thiết kế cơ sở dữ liệu:

### ERD:

![hinh1](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/88d49657-e1bd-43ea-b06c-01308c78001c)


### ERD mức vật lý:

![hinh2](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/7ea46a58-8aca-4469-9e3d-d0f390cf5768)

### Giải thích về ERD:

-   Đầu tiên bắt đầu từ người dùng (User) sẽ có những thuộc tính thông tin cá nhân cơ bản như id, name, email, password, address. Trong đây User sẽ có mối quan hệ với bảng Role là n-n bởi vì 1 user có thể có nhiều role khác nhau.
-   Đối với entity giỏ hàng (Cart) sẽ có mối quan hệ 1-1 với User mỗi người dùng sẽ tương ứng với 1 giỏ hàng.
-   Tương tự entity giỏ hàng, entity Đơn hàng (order) có mối quan hệ n-1 với User là mỗi người dùng sẽ có 1 hoặc nhiều đơn hàng khác nhau.
-   2 entity Giỏ hàng và Đơn hàng sẽ có mối quan hệ 1-n với Sản phẩm (Product). Trong 2 entity này có thể chứa 1 hoặc nhiều thông tin sản phẩm trong giỏ hàng hoặc đơn hàng.
-   Trong sản phẩm (Product) sẽ bao gồm những thông tin cơ bản về sản phẩm như id, tên, giá, màu sắc và hình ảnh.
-   Nhãn hàng (Brand) có mối quan hệ 1-n với Sản phẩm (Product) là trong một nhãn hàng có thể bao gồm 1 sản phẩm hoặc nhiều sản phẩm

## Cấu trúc thư mục trong project:

### Cấu trúc tổng quát:

![hinh3](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/4a1aa9fb-150a-4db5-a0e9-e728875ff349)

Thư mục api: dùng để sử dụng các chức năng trong trang web
Thư mục config: dùng để cấu hình bảo mật cho trang web.
Thư mục controller: dùng để chứa điều hướng của trang web
Thư mục DTO: dùng để trả dữ liệu phù hợp theo trang web mà không cần giống hoàn toàn với entity.
Thư mục model: dùng để chứa các class ánh xạ xuống các entity trong cơ sở dữ liệu.
Thư mục repository: dùng để chứa các interface (Spring data JPA) tương tác trực tiếp với cơ sở dữ liệu.
Thư mục service: chứa các dịch vụ xử lý chức năng của hệ thống.
Thư mục static: dùng để chứa các file tĩnh như css, js,…
Thư mục template: dùng để chứa layout của trang web.
Thư mục test: dùng để chứa các class test các service trong hệ thống.

## Cấu hình để chạy project:

1. Tạo và đặt tên trong My SQL là midterm_hoa \
   ![hinh4](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/6be60883-acd0-4b77-b310-3b7db43bfe82)

2. Sau đó import data đã cung cấp để có dữ liệu.
3. Sau đó kiểm tra tài khoản trong trong database và đăng nhập để test.
   Tài khoản và mật khẩu cung cấp sẵn trong database là:
   Username: example3@gmail.com
   Password: 123456

## API trong ứng dụng:

![hinh5](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/8e935753-5b58-4949-aa9f-61f0883b0d9e)

Phương thức: Post\
Endpoint: 
```
http://localhost:8080/api/register
```
Đây là api đăng kí tài khoản

![hinh6](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/568c44f3-32e7-4b83-b242-e6cd5e5fdaf5)

Phương thức: Post\
Endpoint: 
```
http://localhost:8080/api/login
```
Đây là api đăng nhập

![hinh7](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/828f6f61-f6db-47a9-be94-7682c71863b3)

Phương thức: Get\
Endpoint: 
```
http://localhost:8080/brands
```
Đây là lấy tất cả hãng trong database

![hinh8](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/e1064145-7ad3-4483-938f-eae9812c1189)

Phương thức: Post\
Endpoint: 
```
http://localhost:8080/brands
```
Đây là api thêm hãng vào database

![hinh9](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/f58cd8c4-50b1-49fa-8e79-19b815a83299)

Phương thức: Put\
Endpoint: 
```
http://localhost:8080/brands/{id}
```
Đây là api chỉnh sửa hãng với id của brand

![hinh10](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/c513c130-595e-4c85-9db7-4634e332df70)

Phương thức: Post\
Endpoint: 
```
http://localhost:8080/products
```
Đây là api thêm sản phẩm vào database

![hinh11](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/c913cc4b-7cf5-4bbe-920c-9c6bb341c703)

Phương thức: Get\
Endpoint: 
```
http://localhost:8080/products
```
Đây là api show tất cả sản phẩm trong database.

![hinh12](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/6eb13030-5f46-4420-a07f-789d74b86d80)

Phương thức: Put\
Endpoint: 
```
http://localhost:8080/products/{id}
```
Đây là api update thông tin sản phẩm bất kì với id của sản phẩm

![hinh13](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/9cc43281-3078-4d41-95af-cfd9d1e79f99)

Phương thức: Delete\
Endpoint: 
```
http://localhost:8080/products/{id}
```
Đây là api xóa một sản phẩm dựa trên id sản phẩm

![hinh14](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/c67dce18-f587-41dc-975d-4d4d223b07b5)

Phương thức: Post\
Endpoint: 
```
http://localhost:8080/api/cart/add-product/{product_id}/{user_id}
```
Đây là api thêm 1 sản phẩm vào cart của khách hàng với mặc định số lượng là 1

![hinh15](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/d24add9b-cd47-4144-ade3-d6927fdf2225)

Phương thức: Post\
Endpoint: 
```
http://localhost:8080/api/cart/change-quantity/{product_id}/{user_id}/{quantity}
```
Đây là api cập nhật số lượng của sản phẩm trong giỏ hàng của khách hàng

![hinh16](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/aa5eb311-5c28-4e81-9b7a-524bd211c24c)

Phương thức: Delete\
Endpoint: 
```
http://localhost:8080/api/cart/remove-product/{product_id}/{user_id}
```
Đây là api xóa sản phẩm trong giỏ hàng của khách hàng

## Giao diện sản phẩm:

![hinh17](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/0b518ba3-f240-44a4-98cf-fb9390754e35)

Giao diện đăng nhập

![hinh18](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/9be85e15-5ad8-4170-ab06-b70109af1f86)

Giao diện đăng kí

![hinh19](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/d6314936-561f-4553-bb57-e4ba168a34c9)

Giao diện trang chính dùng để show các sản phẩm

![hinh20](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/c2cc1afa-ab4b-4548-ba8b-155df309f04e)

Giao diện trang giỏ hàng

![hinh21](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/9f43b08e-1bd0-4a39-80db-2a3b3c55e764)

Giao diện trang thanh toán xuất hóa đơn

![hinh22](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/8ae81daf-36f2-46cc-9870-e3e5f85da257)

Giao diện khi thao tác lọc

![hinh23](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/8d65351f-95e2-4d48-a834-fe43517c872b)

Giao diện khi tìm kiếm 1 sản phẩm

## Giải thích Security

Đoạn mã trên là một cấu hình Spring Security, được sử dụng để cấu hình bảo mật cho ứng dụng web Spring. Dưới đây là giải thích cho cấu hình này:
@EnableWebSecurity: Chỉ định rằng lớp này sẽ cấu hình bảo mật cho ứng dụng web Spring.
userDetailsService: Được sử dụng để cung cấp chi tiết người dùng cho Spring Security. Trong trường hợp này, UserDetailsService được autowired vào Spring Security để cung cấp thông tin người dùng.
passwordEncoder(): Tạo một bean PasswordEncoder để mã hóa và giải mã mật khẩu người dùng. Trong trường hợp này, sử dụng BCryptPasswordEncoder để mã hóa mật khẩu.
filterChain(): Phương thức này cấu hình các quy tắc bảo mật cho các request HTTP.

-   `.csrf(csrf -> csrf.disable())`: Tắt bảo vệ CSRF (Cross-Site Request Forgery) để cho phép các yêu cầu không cần cung cấp token CSRF. Điều này có thể hữu ích trong môi trường stateless như API hoặc khi không cần bảo vệ CSRF.
-   `.authorizeHttpRequests((authorize) -> {...})`: Cấu hình việc xác thực cho các request HTTP.
    -   `.requestMatchers("/checkout").authenticated()`: Yêu cầu xác thực cho các request đến "/checkout".
    -   `.anyRequest().permitAll()`: Cho phép tất cả các request khác không cần xác thực.
-   `.formLogin(...)`: Cấu hình form login.
    -   `.loginPage("/login")`: Chỉ định trang đăng nhập.
    -   `.loginProcessingUrl("/login")`: URL sẽ được sử dụng để xử lý quá trình đăng nhập.
    -   `.defaultSuccessUrl("/")`: Định tuyến sau khi đăng nhập thành công.
    -   `.permitAll()`: Cho phép tất cả các người dùng truy cập vào trang đăng nhập.
-   `.logout(...)`: Cấu hình logout.
    -   `.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))`: Xác định URL sẽ được sử dụng để thực hiện logout.
    -   `.permitAll()`: Cho phép tất cả các người dùng truy cập vào trang logout.

configureGlobal(AuthenticationManagerBuilder auth): Cấu hình AuthenticationManager để xác thực người dùng.

-   `.userDetailsService(userDetailsService)`: Cung cấp UserDetailsService để lấy thông tin người dùng.
-   `.passwordEncoder(passwordEncoder())`: Cung cấp PasswordEncoder để mã hóa và giải mã mật khẩu.

## Unit Test:
### Test service của Brand

![hinh24](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/4f9a2b69-4242-4823-b176-0fc06b5aec83)


| Stt | Test case             | Description                                       | Input            | Expect output                                | Result |
|-----|-----------------------|---------------------------------------------------|------------------|----------------------------------------------|--------|
| 1   | testUpdateBrand       | Cập nhật thông tin của nhãn hàng                 | Brand            | Thông tin được sửa đổi                      | Pass   |
| 2   | testFindByID_Exists  | Lấy ra một nhãn hàng bằng id nếu có             | void             | Lấy được thông tin của nhãn hàng            | Pass   |
| 3   | testAddBrand_Success | Thêm nhãn hàng nếu thành công                    | Brand            | Thêm nhãn hàng thành công                    | Pass   |
| 4   | testGetById_NotExists| Lấy 1 nhãn hàng nếu không tồn tại               | id               | Không thể lấy nhãn hàng vì không tồn tại     | Pass   |
| 5   | testGetAll            | Lấy tất cả nhãn hàng                             | void             | Lấy tất cả thông tin brand có trong dữ liệu | Pass   |
| 6   | testAddBrand_Failure  | Thêm nhãn hàng bị lỗi                            | Brand (error)    | Thêm dữ liệu brand thất bại vì lỗi dữ liệu | Pass   |

### Test service của giỏ hàng.
![hinh26](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/430abe52-70f2-47c4-a54f-d1b41e064de0)


| Stt | Test case                              | Description                                                   | Input                            | Expect output                   | Result |
|-----|----------------------------------------|---------------------------------------------------------------|----------------------------------|---------------------------------|--------|
| 1   | testGetTotal                           | Lấy tổng tiền của giỏ hàng                                   | Id user                          | Tổng tiền                       | Pass   |
| 2   | testGetAllProduct                      | Lấy tất cả sản phẩm trong giỏ hàng                           | Id user                          | Tất cả sản phẩm trong giỏ      | Pass   |
| 3   | testFindCartItem ByUserId And ProductId_CartNotFound | Lấy toàn thông tin của sản phẩm trong giỏ hàng dựa trên id user và id sản phẩm trong trường hợp bị lỗi | Id user và id product | Không thể lấy data vì lỗi tham số | Pass   |
| 4   | testFindCartItem By UserIdAndProductId | Lấy toàn thông tin của sản phẩm trong giỏ hàng dựa trên id user và id sản phẩm | Id user và id product          | Lấy được dữ liệu                | Pass   |
| 5   | testGetQuantity                        | Lấy số lượng                                                   | Lấy số lượng sản phẩm có trong giỏ | Số lượng                        | Pass   |
| 6   | testAddProduct                         | Thêm sản phẩm vào giỏ hàng                                    | Id product, id user             | Thêm sản phẩm                   | Pass   |
| 7   | testMinusCartItem CartItemNotFound     | Giảm số lượng của sản phẩm có trong giỏ nếu không có sản phẩm đó trong giỏ | Id product, id user          | Không thể xóa vì không tồn tại | Pass   |
| 8   | testChangeProductQuantity              | Thay đổi số lượng của sản phẩm có trong giỏ                   | Id product, id user, quantity  | Thay đổi số lượng sẵn có       | Pass   |
| 9   | testMinusCartItem                      | Giảm số lượng của sản phẩm trong giỏ hàng                    | Id product, id user             | Giảm số lượng đi 1             | Pass   |


### Test service product
![hinh28](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/52c03215-8222-449d-bc37-149a23f88fce)

| Stt | Testcase                       | Description                             | Input        | Expect output                  | Result |
|-----|--------------------------------|-----------------------------------------|--------------|--------------------------------|--------|
| 1   | testFindProductById            | Lấy 1 sản phẩm với id                   | Id product   | Dữ liệu sản phẩm               | Pass   |
| 2   | testUpdateProduct              | Cập nhật thông tin sản phẩm             | Product      | Dữ liệu được thay đổi         | Pass   |
| 3   | testAddProduct                 | Thêm sản phẩm vào database              | Product      | 1 sản phẩm mới được thêm      | Pass   |
| 4   | testDeleteProduct              | Xóa một sản phẩm                        | Id product   | 1 sản phẩm chỉ định được xóa  | Pass   |
| 5   | testGetAllProducts             | Lấy tất cả thông tin của tất cả sản phẩm | void         | Danh sách thông tin            | Pass   |
| 6   | testSearchProduct_ByName       | Tìm kiếm thông tin 1 sản phẩm bằng Tên  | String name  | Thông tin của 1 sản phẩm theo tên | Pass   |

### Test service User
![hinh30](https://github.com/nguyenthaihoa123/Midterm_Java_Technology/assets/94378718/36c5e9a6-6c3f-4360-b43a-b0198849cc6f)

| Stt | Test case              | Description                              | Input        | Expect output        | Result |
|-----|------------------------|------------------------------------------|--------------|----------------------|--------|
| 1   | testGetUserByEmail     | Lấy thông tin người dùng với Email      | String email | Thông tin người dùng | Pass   |
| 2   | testSaveUser           | Lưu 1 người dùng vào hệ thống           | User         | New user             | Pass   |
| 3   | testGetUserByUserName  | Lấy thông tin của người dùng bằng tên   | String username | Thông tin người dùng | Pass   |
| 4   | testDeleteUserById     | Xóa 1 user khỏi hệ thống                | Id user      | Xóa người dùng       | Pass   |
| 5   | testGetUserById        | Lấy thông tin user bằng id              | Id user      | Thông tin người dùng | Pass   |

