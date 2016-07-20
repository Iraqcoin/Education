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
public class CourseDTO extends BaseDTO{
    private String link_icon;
    private String link_image_bgr;
    private String tag_id;
    private String related_course_id;
    private int cate_id;
    private String createDate;

    public CourseDTO() {
    }

    public CourseDTO(String link_icon, String link_image_bgr, String tag_id, String related_course_id, int cate_id, String createDate, int id, String name, String description) {
        super(id, name, description);
        this.link_icon = link_icon;
        this.link_image_bgr = link_image_bgr;
        this.tag_id = tag_id;
        this.related_course_id = related_course_id;
        this.cate_id = cate_id;
        this.createDate = createDate;
    }

    public String getLink_icon() {
        return link_icon;
    }

    public void setLink_icon(String link_icon) {
        this.link_icon = link_icon;
    }

    public String getLink_image_bgr() {
        return link_image_bgr;
    }

    public void setLink_image_bgr(String link_image_bgr) {
        this.link_image_bgr = link_image_bgr;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getRelated_course_id() {
        return related_course_id;
    }

    public void setRelated_course_id(String related_course_id) {
        this.related_course_id = related_course_id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
}
