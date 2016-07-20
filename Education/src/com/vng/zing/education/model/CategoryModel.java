/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.common.MysqlClient;
import com.vng.zing.education.dto.BaseDTO;
import com.vng.zing.education.dto.CategoryDTO;
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
public class CategoryModel extends BaseModel<CategoryDTO> {

    private static final CategoryModel model = new CategoryModel();

    public static BaseModel getInstances() {
        if (model != null) {
            return model;
        }
        return new CategoryModel();
    }

    @Override
    public List<BaseDTO> getListAll() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<BaseDTO> data = new ArrayList<>();
        String selectSQL = "SELECT * FROM category";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sub_cate_id = rs.getInt("sub_cate_id");
                String description = rs.getString("description");
                CategoryDTO dto = new CategoryDTO();
                dto.setName(name);
                dto.setId(id);
                dto.setSub_cate_id(sub_cate_id);
                dto.setDescription(description);
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
        String selectSQL = "SELECT * FROM category LIMIT ? , ?";
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
                int sub_cate_id = rs.getInt("sub_cate_id");
                String description = rs.getString("description");
                CategoryDTO dto = new CategoryDTO();
                dto.setName(name);
                dto.setId(id);
                dto.setSub_cate_id(sub_cate_id);
                dto.setDescription(description);
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
        String selectSQL = "SELECT * FROM category WHERE parent_cate_id = ? LIMIT ? , ?";
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
                int sub_cate_id = rs.getInt("sub_cate_id");
                String description = rs.getString("description");
                CategoryDTO dto = new CategoryDTO();
                dto.setName(name);
                dto.setId(id);
                dto.setSub_cate_id(sub_cate_id);
                dto.setDescription(description);
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
    public CategoryDTO getDataById(int id) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        CategoryDTO dto = null;
        String selectSQL = "SELECT * FROM category WHERE id = ?";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int sub_cate_id = rs.getInt("sub_cate_id");
                String description = rs.getString("description");
                dto = new CategoryDTO();
                dto.setName(name);
                dto.setId(id);
                dto.setSub_cate_id(sub_cate_id);
                dto.setDescription(description);
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
    public int insertData(CategoryDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO category (name, sub_cate_id , description) values(?, ?,?)";
        int generatedkey = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getSub_cate_id());
            preparedStatement.setString(3, dto.getDescription());
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
    public int updateData(CategoryDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE  category SET name=? , sub_cate_id=? , description=?  WHERE id = ?";
        int flag = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getSub_cate_id());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.setInt(4, dto.getId());
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
        String sql = "DELETE FROM  category  WHERE id = ?";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
