package com.cesar.web.framework.response;

import java.util.Arrays;
import java.util.List;

public class Response<T> {
    public static final String SUCCESS_CODE = "2000";
    public static final String FAILURE_CODE = "4000";
    private String code;
    private String message;
    private T data;
    private List<Object> placeholder;

    public Response() {
    }

    public Response(Response.Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
        this.placeholder = builder.placeholder;
    }

    public static <T> Response<T> succeed(T data) {
        Response<T> response = new Response();
        response.setCode("2000");
        response.setData(data);
        return response;
    }

    public static <T> Response<T> succeed(T data, String message) {
        Response<T> response = new Response();
        response.setCode("2000");
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static Response<String> fail(String message) {
        Response response = new Response();
        response.setCode("4000");
        response.setMessage(message);
        return response;
    }

    public static Response<String> fail(String code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static Response<String> fail(String code, String message, String data) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(List<Object> placeholder) {
        this.placeholder = placeholder;
    }

    /*public static Response fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = new ParameterizedType() {
            public Type getRawType() {
                return Response.class;
            }

            public Type[] getActualTypeArguments() {
                return new Type[]{clazz};
            }

            public Type getOwnerType() {
                return null;
            }
        };
        return (Response) gson.fromJson(json, objectType);
    }*/

    public static class Builder<T> {
        private String code;
        private String message;
        private T data;
        private List<Object> placeholder;

        public Builder() {
        }

        public Response.Builder code(String code) {
            this.code = code;
            return this;
        }

        public Response.Builder message(String message) {
            this.message = message;
            return this;
        }

        public Response.Builder data(T data) {
            this.data = data;
            return this;
        }

        public Response.Builder placeholder(Object... placeholder) {
            this.placeholder = Arrays.asList(placeholder);
            return this;
        }

        public Response buildSuccess() {
            this.code = "2000";
            return new Response(this);
        }

        /*public Response buildFail() {
            if (StringUtils.isEmpty(this.code)) {
                this.code = "4000";
            }

            return new Response(this);
        }*/
    }
}
