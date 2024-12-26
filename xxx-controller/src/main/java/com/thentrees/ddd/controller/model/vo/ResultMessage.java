package com.thentrees.ddd.controller.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResultMessage
 * @Description VO tương tác với frontend và backend
 * VO => value object
 */

@Data
public class ResultMessage<T> implements Serializable {

    // thong bao trang thai
    private boolean success;

    /**
     * Thông báo
     */
    private String message;

    /**
     * Mã code trả về
     */
    private Integer code;

    /**
     * Thời gian lúc trả về
     */
    private long timeStamp = System.currentTimeMillis();

    /**
     * Đối tượng trả về
     */
    private T result;
}
