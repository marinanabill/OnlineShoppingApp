package com.example.OnlineShoppingApp.payload;

public class ApiResponse {
    private String message;
    private boolean success;

    // Existing constructor
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // ✅ Added constructor to support calls like: new ApiResponse(true, "Message")
    public ApiResponse(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
