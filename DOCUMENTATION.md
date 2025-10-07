1. InvalidatedToken

- InvalidatedToken is a blacklist of revoked/logged-out tokens.
- In JWT authentication, tokens are stateless - once issued, they remain valid until they expire. There's no built-in way to "cancel" a JWT token.
- The problem: When a user logs out, their token is still technically valid until it expires. If someone steals that token, they could still use it!
- The solution: Create a database table (InvalidatedToken) that stores IDs of tokens that should no longer be accepted, even if they haven't expired yet.

2. Refresh token

- Access Token (Short-lived)
- Duration: 10 seconds (VALID_DURATION = 10)
- Purpose: Used to access protected APIs
- Lifecycle: Very short for security
- Refresh Token Mechanism (Longer-lived)
- Duration: 15 seconds (REFRESHABLE_DURATION = 15)
- Purpose: Used to get a new access token without re-entering credentials

- Note: The durations in your config (10s and 15s) are unusually short - they're probably set for testing. In production, typical values are:
- Access token: 15-30 minutes
- Refresh token: 7-30 days
  -> FRONTENT: setTimeout(() => {
  refreshToken(token);
  }, (TOKEN_LIFETIME - REFRESH_BEFORE) \* 1000);

  -> refreshToken(currentToken) when call API from FE failed



3. Run command: ./mvnw spring-boot:run


-----
CORS

1. CORS là gì? 🌐
Để hiểu CORS, trước tiên cần hiểu về chính sách Same-Origin Policy (SOP):

Same-Origin Policy (SOP): Đây là một quy tắc bảo mật cơ bản của trình duyệt. Nó chặn các tập lệnh (script) đang chạy trên một trang web (ví dụ: https://nguon-a.com) không được phép gửi yêu cầu để đọc dữ liệu từ một nguồn gốc khác (ví dụ: https://nguon-b.com). Mục đích là để ngăn chặn các trang web độc hại đánh cắp thông tin nhạy cảm.
CORS được sinh ra để nới lỏng quy tắc SOP một cách có kiểm soát. Nó cho phép máy chủ định nghĩa rõ ràng những nguồn gốc bên ngoài nào được phép truy cập vào tài nguyên của mình.

2. Các Loại Yêu Cầu CORS
Có hai loại yêu cầu chính liên quan đến CORS:

a. Simple Requests (Yêu cầu Đơn giản)
Đây là các yêu cầu đáp ứng đủ ba điều kiện sau:

Phương thức là GET, HEAD, hoặc POST.

Chỉ sử dụng các tiêu đề (headers) an toàn và giới hạn (ví dụ: Accept, Accept-Language, Content-Language, Content-Type - nhưng Content-Type chỉ được là application/x-www-form-urlencoded, multipart/form-data, hoặc text/plain).

Không có luồng dữ liệu (stream) được sử dụng.

Với yêu cầu đơn giản, trình duyệt sẽ gửi yêu cầu HTTP trực tiếp và máy chủ phải trả về các tiêu đề CORS trong phản hồi (response) để trình duyệt kiểm tra.

b. Preflight Requests (Yêu cầu Tiền kiểm) ✈️
Đây là các yêu cầu phức tạp hơn (ví dụ: sử dụng phương thức PUT, DELETE, hoặc có tiêu đề tùy chỉnh như Authorization).

Với các yêu cầu này, trình duyệt sẽ thực hiện hai bước:

Gửi yêu cầu OPTIONS (Preflight Request): Trình duyệt gửi một yêu cầu HTTP với phương thức OPTIONS đến máy chủ trước khi gửi yêu cầu thực tế. Yêu cầu này hỏi máy chủ: "Tôi có thể gửi yêu cầu thực sự với phương thức/headers X, Y, Z từ nguồn gốc này không?".

Máy chủ phản hồi: Nếu máy chủ chấp nhận, nó sẽ gửi phản hồi với các tiêu đề CORS cho phép (như Access-Control-Allow-Origin, Access-Control-Allow-Methods).

Gửi yêu cầu thực sự: Chỉ khi nhận được phản hồi chấp thuận từ Preflight, trình duyệt mới gửi yêu cầu HTTP thực tế (ví dụ: POST hoặc PUT).



4. PostAuthorize
- Have to mark the Service as Transactional, to rollback the changes and not update DB if failed PostAuthorize
- Still have problem if have any operations outside transactions, they won't roll back like emailService.sendNotification("Updating user...")
-> Use @PreAuthorize Instead
-> check manually at the beginning (var authentication = SecurityContextHolder.getContext().getAuthentication();)