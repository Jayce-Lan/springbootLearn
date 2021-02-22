package com.example.response;

public class CommonReturnType {
    //表明对应请求的返回处理结果，success或fail
    private String status;
    //若status=success，data返回前端需要的json数据；如果status=fail，则data内使用通用错误码格式
    private Object data;

    /**
     * 定义一个通用的创建方式
     *
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }

    /**
     *
     * @param result
     * @param status
     * @return
     */
    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);

        return type;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
