/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.dto.BaseDTO;
import com.vng.zing.education.dto.UserRegisterDTO;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author root
 */
public class CacheUserModel {
    public static  Map<String,UserRegisterDTO> USER_DATA =  new ConcurrentHashMap<String, UserRegisterDTO>();
    public static void init(){
       List<BaseDTO> data = UserRegisterModel.getInstances().getListAll();
        for (BaseDTO baseDTO : data) {
            UserRegisterDTO dto =  (UserRegisterDTO) baseDTO;
            USER_DATA.put(dto.getHashname(), dto);
        }
    }
    public static boolean removeCache(String hash){
        if(USER_DATA.containsKey(hash)){
            USER_DATA.remove(hash);
            return true;
        }
        return false;
    }
    public static UserRegisterDTO getCache(String hash){
       return USER_DATA.get(hash);
    }
    public static boolean updateCache(String hash, UserRegisterDTO dto){
        if(USER_DATA.containsKey(hash)){
            USER_DATA.remove(hash);
            USER_DATA.put(hash, dto);
        }else{
            USER_DATA.put(hash, dto);
        }
        return true;
    }
}
