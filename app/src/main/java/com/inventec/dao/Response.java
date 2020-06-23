package com.inventec.dao;

import com.inventec.entity.pos_shopinfo;

import java.util.List;

/**
 * Created by 21737367 on 2018/10/22.
 */

public class Response {
    public String message;
    public String code;
    public List<pos_shopinfo> datas;
    public int size;

    public Response(String message, String code, List<pos_shopinfo> datas,int size) {
        this.message = message;
        this.code = code;
        this.datas = datas;
        this.size = size;
    }

    public Response(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
