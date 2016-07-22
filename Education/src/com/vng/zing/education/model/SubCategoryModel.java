/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.common.MysqlClient;
import com.vng.zing.education.dto.BaseDTO;
import com.vng.zing.education.dto.ParentCategoryDTO;
import com.vng.zing.education.dto.SubCategoryDTO;
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
public class SubCategoryModel extends BaseModel<SubCategoryDTO> {

    private static final SubCategoryModel model = new SubCategoryModel();

    public static BaseModel getInstances() {
        if (model != null) {
            return model;
        }
        return new SubCategoryModel();
    }

    @Override
    public List<BaseDTO> getListAll() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<BaseDTO> data = new ArrayList<>();
        String selectSQL = "SELECT * FROM sub_category";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int parent_cate_id = rs.getInt("parent_cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                BaseDTO dto = new SubCategoryDTO(parent_cate_id, link_icon, id, name, description);
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
        String selectSQL = "SELECT * FROM sub_category LIMIT ? , ?";
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
                int parent_cate_id = rs.getInt("parent_cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                BaseDTO dto = new SubCategoryDTO(parent_cate_id, link_icon, id, name, description);
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
        String selectSQL = "SELECT * FROM sub_category WHERE parent_cate_id = ? LIMIT ? , ?";
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
                int parent_cate_id = rs.getInt("parent_cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                BaseDTO dto = new SubCategoryDTO(parent_cate_id, link_icon, id, name, description);
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
    public SubCategoryDTO getDataById(int id) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        SubCategoryDTO dto = null;
        String selectSQL = "SELECT * FROM sub_category WHERE id = ?";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int parent_cate_id = rs.getInt("parent_cate_id");
                String description = rs.getString("description");
                String link_icon = rs.getString("link_icon");
                dto = new SubCategoryDTO(parent_cate_id, link_icon, id, name, description);
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
    public int insertData(SubCategoryDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO sub_category (name, parent_cate_id,description,link_icon) values(?, ?,?,?)";
        int generatedkey = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getParent_cate_id());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setString(4, dto.getLink_icon());
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
    public int updateData(SubCategoryDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE  sub_category SET name=? , parent_cate_id=? , description=? , link_icon=? WHERE id = ?";
        int flag = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getParent_cate_id());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setString(4, dto.getLink_icon());
            preparedStatement.setInt(5, dto.getId());
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
        String sql = "DELETE FROM  sub_category  WHERE id = ?";
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
        String selectSQL = "SELECT COUNT(*) FROM sub_category";
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
