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
public class DetailDTO extends BaseDTO{
    private int type;
    private String link = "";
    private String content = "";
    private int course_detail_id;

    public DetailDTO() {
    }

    public DetailDTO(int type, String link, String content, int course_detail_id, int id, String name, String description) {
        super(id, name, description);
        this.type = type;
        this.link = link;
        this.content = content;
        this.course_detail_id = course_detail_id;
    }

    public int getCourse_detail_id() {
        return course_detail_id;
    }

    public void setCourse_detail_id(int course_detail_id) {
        this.course_detail_id = course_detail_id;
    }
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
