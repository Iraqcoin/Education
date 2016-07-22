/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.common.MysqlClient;
import com.vng.zing.education.dto.BaseDTO;
import com.vng.zing.education.dto.CourseDTO;
import com.vng.zing.education.dto.ParentCategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class CourseModel extends BaseModel<CourseDTO> {

    private static final CourseModel model = new CourseModel();

    public static BaseModel getInstances() {
        if (model != null) {
            return model;
        }
        return new CourseModel();
    }

    @Override
    public List<BaseDTO> getListAll() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<BaseDTO> data = new ArrayList<>();
        String selectSQL = "SELECT * FROM course";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int cate_id = rs.getInt("cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                String link_image_bgr = rs.getString("link_image_bgr");
                String tag_id = rs.getString("tag_id");
                String related_course_id = rs.getString("related_course_id");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                CourseDTO dto = new CourseDTO(link_icon, link_image_bgr, tag_id, related_course_id, cate_id, createDate, id, name, description);
                data.add(dto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return data;
    }

    @Override
    public List<BaseDTO> getScileData(int from, int count) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<BaseDTO> data = new ArrayList<>();
        String selectSQL = "SELECT * FROM course LIMIT ? , ?";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, count);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int cate_id = rs.getInt("cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                String link_image_bgr = rs.getString("link_image_bgr");
                String tag_id = rs.getString("tag_id");
                String related_course_id = rs.getString("related_course_id");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                CourseDTO dto = new CourseDTO(link_icon, link_image_bgr, tag_id, related_course_id, cate_id, createDate, id, name, description);
                data.add(dto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return data;
    }

    @Override
    public List<BaseDTO> getScileDataFromParentID(int pid, int from, int count) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<BaseDTO> data = new ArrayList<>();
        String selectSQL = "SELECT * FROM course WHERE cate_id = ? LIMIT ? , ?";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, pid);
            preparedStatement.setInt(2, from);
            preparedStatement.setInt(3, count);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int cate_id = rs.getInt("cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                String link_image_bgr = rs.getString("link_image_bgr");
                String tag_id = rs.getString("tag_id");
                String related_course_id = rs.getString("related_course_id");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                CourseDTO dto = new CourseDTO(link_icon, link_image_bgr, tag_id, related_course_id, cate_id, createDate, id, name, description);
                data.add(dto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return data;
    }

    @Override
    public CourseDTO getDataById(int id) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        CourseDTO dto = null;
        String selectSQL = "SELECT * FROM course WHERE id = ?";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int cate_id = rs.getInt("cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                String link_image_bgr = rs.getString("link_image_bgr");
                String tag_id = rs.getString("tag_id");
                String related_course_id = rs.getString("related_course_id");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                dto = new CourseDTO(link_icon, link_image_bgr, tag_id, related_course_id, cate_id, createDate, id, name, description);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return dto;
    }

    @Override
    public int insertData(CourseDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO course (name,cate_id,description,link_icon,link_image_bgr,tag_id,related_course_id,createDate) values(?, ?,?,?,?,?,?,?)";
        int generatedkey = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getCate_id());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setString(4, dto.getLink_icon());
            preparedStatement.setString(5, dto.getLink_image_bgr());
            preparedStatement.setString(6, dto.getTag_id());
            preparedStatement.setString(7, dto.getRelated_course_id());
            //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = new Date();// formatter.parse(dto.getCreateDate());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            preparedStatement.setDate(8, sqlDate);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedkey = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return generatedkey;
    }

    @Override
    public int updateData(CourseDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE  course SET name=? , cate_id=? , description=? , link_icon=?,link_image_bgr=?,tag_id=?,related_course_id=?,createDate=? WHERE id = ?";
        int flag = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getCate_id());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setString(4, dto.getLink_icon());
            preparedStatement.setString(5, dto.getLink_image_bgr());
            preparedStatement.setString(6, dto.getTag_id());
            preparedStatement.setString(7, dto.getRelated_course_id());
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = formatter.parse(dto.getCreateDate());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            preparedStatement.setDate(8, sqlDate);
            preparedStatement.setInt(9, dto.getId());
            flag = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return flag;
    }

    @Override
    public int deleteData(int dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM  course  WHERE id = ?";
        int flag = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, dto);
            flag = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return flag;
    }

    @Override
    public int getTotal() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ParentCategoryDTO dto = null;
        String selectSQL = "SELECT COUNT(*) FROM course";
        int total = 0;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
               total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (dbConnection != null) {
                MysqlClient.getMysqlClient("main").releaseDbConnection(dbConnection);
            }
        }
        return total;
    }

}
