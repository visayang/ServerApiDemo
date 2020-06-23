/*
 * Copyright © 2016 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.inventec.handler;

import android.util.Log;

import com.google.gson.Gson;
import com.inventec.dao.Response;
import com.inventec.entity.pos_shopinfo;
import com.yanzhenjie.andserver.RequestHandler;

import com.yanzhenjie.andserver.RequestMethod;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.util.HttpRequestParser;

import org.apache.httpcore.HttpException;
import org.apache.httpcore.HttpRequest;
import org.apache.httpcore.HttpResponse;
import org.apache.httpcore.entity.StringEntity;
import org.apache.httpcore.protocol.HttpContext;
import org.litepal.LitePal;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * <p>Login Handler.</p>
 * Created by Yan Zhenjie on 2016/6/13.
 */
public class LoginHandler implements RequestHandler {

    @RequestMapping(method = {RequestMethod.POST})
    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        Map<String, String> params = HttpRequestParser.parseParams(request);
        String content = HttpRequestParser.getContent(request);

        Log.i("123", "====" + content);

        if (!params.containsKey("type")) {
            StringEntity stringEntity = new StringEntity("Please enter your account number and password.", "utf-8");

            response.setStatusCode(400);
            response.setEntity(stringEntity);
            return;
        }

        String userName = URLDecoder.decode(params.get("type"), "utf-8");

        if ("1".equals(userName) ) {
            List<pos_shopinfo> all = LitePal.where("id < ?","10" ).find(pos_shopinfo.class);

            if(all.size()>0){
                Response resinfo = new Response("Insert Success","1",all.subList(0,5),all.size());

                String s = new Gson().toJson(resinfo);

                StringEntity stringEntity = new StringEntity(s, "utf-8");

                response.setStatusCode(200);
                response.setEntity(stringEntity);
            }else {
                Response resinfo = new Response("Insert Failed","0");
                String s = new Gson().toJson(resinfo);

                StringEntity stringEntity = new StringEntity(s, "utf-8");

                response.setStatusCode(200);
                response.setEntity(stringEntity);
            }
        } else {
            Response resinfo = new Response("类型错误","0");
            String s = new Gson().toJson(resinfo);

            StringEntity stringEntity = new StringEntity(s, "utf-8");

            response.setStatusCode(400);
            response.setEntity(stringEntity);
        }
    }


}
