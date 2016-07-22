/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.common.MysqlClient;
import com.vng.zing.education.dto.BaseDTO;
import com.vng.zing.education.dto.CourseDetailsDTO;
import com.vng.zing.education.dto.ParentCategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class CourseDetailsModel extends BaseModel<CourseDetailsDTO> {

    private static final CourseDetailsModel model = new CourseDetailsModel();

    public static BaseModel getInstances() {
        if (model != null) {
            return model;
        }
        return new CourseDetailsModel();
    }

    @Override
    public List<BaseDTO> getListAll() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<BaseDTO> data = new ArrayList<>();
        String selectSQL = "SELECT * FROM course_details";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int level = rs.getInt("level");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                int num_video = rs.getInt("num_video");
                int num_challenges = rs.getInt("num_challenges");
                int free = rs.getInt("free");
                int course_id = rs.getInt("course_id");
                CourseDetailsDTO dto = new CourseDetailsDTO(level, link_icon, num_video, num_challenges, free, course_id, id, name, description);
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
        String selectSQL = "SELECT * FROM course_details LIMIT ? , ?";
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
                int level = rs.getInt("level");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                int num_video = rs.getInt("num_video");
                int num_challenges = rs.getInt("num_challenges");
                int free = rs.getInt("free");
                int course_id = rs.getInt("course_id");
                CourseDetailsDTO dto = new CourseDetailsDTO(level, link_icon, num_video, num_challenges, free, course_id, id, name, description);
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
        String selectSQL = "SELECT * FROM course_details WHERE course_id = ? LIMIT ? , ?";
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
                int level = rs.getInt("level");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                int num_video = rs.getInt("num_video");
                int num_challenges = rs.getInt("num_challenges");
                int free = rs.getInt("free");
                int course_id = rs.getInt("course_id");
                CourseDetailsDTO dto = new CourseDetailsDTO(level, link_icon, num_video, num_challenges, free, course_id, id, name, description);
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
    public CourseDetailsDTO getDataById(int id) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        CourseDetailsDTO dto = null;
        String selectSQL = "SELECT * FROM course_details WHERE id = ?";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int level = rs.getInt("level");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                int num_video = rs.getInt("num_video");
                int num_challenges = rs.getInt("num_challenges");
                int free = rs.getInt("free");
                int course_id = rs.getInt("course_id");
                dto = new CourseDetailsDTO(level, link_icon, num_video, num_challenges, free, course_id, id, name, description);
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
    public int insertData(CourseDetailsDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO course_details (name, course_id,description,link_icon,level,num_video,num_challenges,free) values(?, ?,?,?,?,?,?,?)";
        int generatedkey = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getCourse_id());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setString(4, dto.getLink_icon());
            preparedStatement.setInt(5, dto.getLevel());
            preparedStatement.setInt(6, dto.getNum_video());
            preparedStatement.setInt(7, dto.getNum_challenges());
            preparedStatement.setInt(8, dto.getFree());
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
    public int updateData(CourseDetailsDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE  course_details SET name=? , course_id=? , description=? , link_icon=? , level=?,num_video=?,num_challenges=?,free=? WHERE id = ?";
        int flag = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getCourse_id());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setString(4, dto.getLink_icon());
            preparedStatement.setInt(5, dto.getLevel());
            preparedStatement.setInt(6, dto.getNum_video());
            preparedStatement.setInt(7, dto.getNum_challenges());
            preparedStatement.setInt(8, dto.getFree());
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
        String sql = "DELETE FROM  course_details  WHERE id = ?";
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
        String selectSQL = "SELECT COUNT(*) FROM course_details";
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
