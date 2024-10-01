package DrSport.service.impl;

import DrSport.config.VNPayConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class VNPayService {

    public String createPaymentUrl(double total, String orderInfo, String returnUrl, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // Prepare the request parameters for VNPay
        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", "2.0.0"); // Đổi về phiên bản hiện tại
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
        vnpParams.put("vnp_Amount", String.valueOf((long) (total * 100))); // Convert total to VND in smallest units
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", VNPayConfig.getRandomNumber(8)); // Unique transaction reference
        vnpParams.put("vnp_OrderInfo", orderInfo);
        vnpParams.put("vnp_OrderType", "billpayment"); // Define the type of payment
        vnpParams.put("vnp_Locale", "vn"); // Language preference
        vnpParams.put("vnp_ReturnUrl", returnUrl); // Return URL after the payment
        vnpParams.put("vnp_IpAddr", VNPayConfig.getIpAddress(request)); // Get user's IP address
        vnpParams.put("vnp_CreateDate", getCurrentTime()); // The date and time of request

        // Ensure required fields are included for hashing
        vnpParams.put("vnp_ExpireDate", getExpireTime()); // Optional: expiration date for transaction

        // Secure hash generation
        String secureHash = VNPayConfig.hashAllFields(vnpParams);
        vnpParams.put("vnp_SecureHash", secureHash);

        // Build the query string for VNPay
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                queryString.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }
        }

        // Remove the last "&"
        String query = queryString.substring(0, queryString.length() - 1);

        // Construct the full URL for the payment redirect
        return VNPayConfig.vnp_PayUrl + "?" + query;
    }

    private String getCurrentTime() {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date now = new java.util.Date();
        return formatter.format(now);
    }

    private String getExpireTime() {
        // Optional: Set expiration time for the transaction, e.g., 15 minutes from now
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MINUTE, 15); // Set expiration time
        return formatter.format(cal.getTime());
    }
}
