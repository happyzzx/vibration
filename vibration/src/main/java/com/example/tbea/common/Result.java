package com.example.tbea.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;



    //泛型方法 instance用于创建一个 Result 对象,可以适应不同类型的 Result 对象的创建需求。
    public static <T> Result<T> instance(Integer code, String message, T data) {
        //泛型方法，返回一个 Result<T> 对象
        //首先创建了一个 Result<T> 类的实例对象 result
        //将参数分别设置到 result 对象的相应属性中
        //最后，将创建好的 result 对象返回
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
