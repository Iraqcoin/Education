/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.dto.BaseDTO;
import java.util.List;

/**
 *
 * @author root
 */
public abstract class BaseModel<T> {
    public abstract  List<BaseDTO> getListAll();
    public abstract List<BaseDTO> getScileData(int from , int count);
    public abstract List<BaseDTO> getScileDataFromParentID(int id ,int from , int count);
    public abstract T getDataById(int id);
    public abstract int getTotal();
    public abstract int insertData(T dto);
    public abstract int updateData(T dto);
    public abstract int deleteData(int dto);
}
