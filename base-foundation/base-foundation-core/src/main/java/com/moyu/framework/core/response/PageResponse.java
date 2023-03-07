package com.moyu.framework.core.response;

import com.moyu.framework.core.page.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> implements Response<Page<T>> {

    private Integer code;
    private String msg;
    private Page<T> data;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Page<T> getData() {
        return data;
    }

    public void setData(Page<T> data) {
        this.data = data;
    }

}
