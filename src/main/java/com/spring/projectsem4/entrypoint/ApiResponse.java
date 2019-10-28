package com.spring.projectsem4.entrypoint;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiResponse implements Serializable {

    private HashMap<String, Object> response;

    private ApiResponse() {
        this.response = new HashMap<>();
    }

    private HashMap<String, Object> getResponse() {
        return response;
    }

    public void setResponse(HashMap<String, Object> response) {
        this.response = response;
    }

    private void addResponse(String key, Object value) {
        this.response.put(key, value);
    }

    public static class Error {

        private HashMap<String, String> errors;
        private int status;
        private String message;

        public Error() {
            this.errors = new HashMap<>();
            this.status = 0;
            this.message = "";
        }

        public Error setStatus(int status) {
            this.status = status;
            return this;
        }

        public Error setMessage(String message) {
            this.message = message;
            return this;
        }

        public Error addError(String key, String value) {
            this.errors.put(key, value);
            return this;
        }

        public Error addErrors(HashMap<String, String> errors) {
            this.errors.putAll(errors);
            return this;
        }

        public HashMap<String, Object> build() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.addResponse("status", this.status);
            apiResponse.addResponse("message", this.message);
            String errorKey = "error";
            if (this.errors.size() > 1) {
                errorKey = "errors";
            }
            apiResponse.addResponse(errorKey, this.errors);
            return apiResponse.getResponse();
        }
    }

    public static class SimpleError {

        private int code;
        private String message;

        public SimpleError() {
            this.code = 0;
            this.message = "";
        }

        public SimpleError setCode(int code) {
            this.code = code;
            return this;
        }

        public SimpleError setMessage(String message) {
            this.message = message;
            return this;
        }

        public HashMap<String, Object> build() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.addResponse("status", this.code);
            apiResponse.addResponse("message", this.message);
            return apiResponse.getResponse();
        }
    }

    public static class Success {
        @JsonProperty("status")
        private int status;

        @JsonProperty("message")
        private String message;

        @JsonProperty("entity")
        private List<Object> body;

        public Success() {
            this.status = 200;
            this.message = "Success";
            this.body = new ArrayList<>();
        }


        public Success setStatus(int status) {
            this.status = status;
            return this;
        }

        public Success setMessage(String message) {
            this.message = message;
            return this;
        }

        public Success addData(Object obj) {
            this.body.add(obj);
            return this;
        }

        public Success addDatas(List listObj) {
            this.body.addAll(listObj);
            return this;
        }

        public HashMap<String, Object> build() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.addResponse("status", this.status);
            apiResponse.addResponse("message", this.message);
            if (this.body.size() == 1) {
                apiResponse.addResponse("body", this.body.get(0));

            } else {
                apiResponse.addResponse("body", this.body);
            }
//            if (this.pagination != null) {
//                restResponse.addResponse("pagination", this.pagination);
//            }
            return apiResponse.getResponse();
        }

        public HashMap<String, Object> buildDatas() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.addResponse("status", this.status);
            apiResponse.addResponse("message", this.message);
            apiResponse.addResponse("body", this.body);
//            if (this.pagination != null) {
//                restResponse.addResponse("pagination", this.pagination);
//            }
            return apiResponse.getResponse();
        }
    }
}
