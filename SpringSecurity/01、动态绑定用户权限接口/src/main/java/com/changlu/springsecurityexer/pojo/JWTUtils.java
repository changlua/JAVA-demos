package com.changlu.springsecurityexer.pojo;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;

import java.util.Base64;

/**
 * @ClassName JWTUtils
 * @Author ChangLu
 * @Date 2021/9/19 10:55
 * @Description TODO
 */
public class JWTUtils {

    private static String secret = "jidjiof";

    public static void main(String[] args) {
        JSONObject header = new JSONObject();
        header.put("alg", "HS256");
        header.put("typ", "jwt");
        JSONObject playLoad = new JSONObject();
        playLoad.put("id", "123456");
        //base64
        String base64Header = Base64.getEncoder().encodeToString(header.toJSONString().getBytes());
        String base64PlayLoad = Base64.getEncoder().encodeToString(playLoad.toJSONString().getBytes());

        //第三部分
        String signature = MD5.create().digestHex(base64PlayLoad + secret);

        String token = base64Header + "." + base64PlayLoad + "." + signature;
        System.out.println(token);

    }
}