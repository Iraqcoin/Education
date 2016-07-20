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
public class CourseDetailsDTO extends BaseDTO{
    private int level;
    private String link_icon;
    private int num_video;
    private int num_challenges;
    private int free;
    private int course_id;

    public CourseDetailsDTO(int level, String link_icon, int num_video, int num_challenges, int free, int course_id, int id, String name, String description) {
        super(id, name, description);
        this.level = level;
        this.link_icon = link_icon;
        this.num_video = num_video;
        this.num_challenges = num_challenges;
        this.free = free;
        this.course_id = course_id;
    }

    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLink_icon() {
        return link_icon;
    }

    public void setLink_icon(String link_icon) {
        this.link_icon = link_icon;
    }

    public int getNum_video() {
        return num_video;
    }

    public void setNum_video(int num_video) {
        this.num_video = num_video;
    }

    public int getNum_challenges() {
        return num_challenges;
    }

    public void setNum_challenges(int num_challenges) {
        this.num_challenges = num_challenges;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
    
}
