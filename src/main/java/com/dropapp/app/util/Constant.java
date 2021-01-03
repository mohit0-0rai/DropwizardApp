package com.dropapp.app.util;

public interface Constant {
    Integer SUCCESS_CODE = 200;
    Integer SERVER_ERROR_CODE = 500;
    Integer FORBIDDEN_ACCESS_CODE = 403;
    Integer UNAUTHORIZED_CODE = 401;
    Integer FAILURE_CODE = 400;
    String APP_SECRET_KEY = "ifVSetMV3LpjwHOocTIGLmQGNm2UWbgY";
    String SERVER_ERROR_MESSAGE = "Server error, please try after some time.";
    Long JWT_EXPIRATION_OFFSET = 1000L * 60 * 60 * 6;
}
