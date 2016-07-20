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
public class SubCategoryDTO extends BaseDTO{
    private int parent_cate_id;
    private String link_icon;

    public SubCategoryDTO(int parent_cate_id, String link_icon, int id, String name, String description) {
        super(id, name, description);
        this.parent_cate_id = parent_cate_id;
        this.link_icon = link_icon;
    }

    public int getParent_cate_id() {
        return parent_cate_id;
    }

    public void setParent_cate_id(int parent_cate_id) {
        this.parent_cate_id = parent_cate_id;
    }

    public String getLink_icon() {
        return link_icon;
    }

    public void setLink_icon(String link_icon) {
        this.link_icon = link_icon;
    }
    
}
