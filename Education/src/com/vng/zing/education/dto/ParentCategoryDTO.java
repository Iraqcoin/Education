/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.dto;

/**
 *
 * @author root
 */
public class ParentCategoryDTO extends BaseDTO{
    private int sub;
    private String createDate;

    public ParentCategoryDTO() {
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ParentCategoryDTO(int sub) {
        this.sub = sub;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }
    
}
