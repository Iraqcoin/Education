/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.model;

import com.vng.zing.education.common.MysqlClient;
import com.vng.zing.education.dto.BaseDTO;
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
public class ParentCategoryModel extends BaseModel<ParentCategoryDTO> {

    private static final ParentCategoryModel model = new ParentCategoryModel();

    public static BaseModel getInstances() {
        if (model != null) {
            return model;
        }
        return new ParentCategoryModel();
    }

    @Override
    public List<BaseDTO> getListAll() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<BaseDTO> data = new ArrayList<>();
        String selectSQL = "SELECT * FROM parent_category";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int parent_cate_id = rs.getInt("sub");
                String desc = rs.getString("description");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                ParentCategoryDTO dto = new ParentCategoryDTO();
                dto.setId(id);
                dto.setName(name);
                dto.setSub(parent_cate_id);
                dto.setDescription(desc);
                dto.setCreateDate(createDate);
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
        String selectSQL = "SELECT * FROM parent_category LIMIT ? , ?";
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
                int parent_cate_id = rs.getInt("sub");
                String desc = rs.getString("description");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                ParentCategoryDTO dto = new ParentCategoryDTO();
                dto.setId(id);
                dto.setName(name);
                dto.setSub(parent_cate_id);
                dto.setDescription(desc);
                dto.setCreateDate(createDate);
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
        String selectSQL = "SELECT * FROM parent_category WHERE sub = ? LIMIT ? , ?";
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
                int parent_cate_id = rs.getInt("sub");
                String desc = rs.getString("description");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                ParentCategoryDTO dto = new ParentCategoryDTO();
                dto.setId(id);
                dto.setName(name);
                dto.setSub(parent_cate_id);
                dto.setDescription(desc);
                dto.setCreateDate(createDate);
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
    public ParentCategoryDTO getDataById(int id) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ParentCategoryDTO dto = null;
        String selectSQL = "SELECT * FROM parent_category WHERE id = ?";
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int parent_cate_id = rs.getInt("sub");
                String desc = rs.getString("description");
                Date date = rs.getDate("createDate");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatter.format(date);
                dto = new ParentCategoryDTO();
                dto.setId(id);
                dto.setName(name);
                dto.setSub(parent_cate_id);
                dto.setDescription(desc);
                dto.setCreateDate(createDate);
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
    public int insertData(ParentCategoryDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO parent_category (name,sub,description,createDate) values(?, ?,?,?)";
        int generatedkey = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getSub());
            preparedStatement.setString(3, dto.getDescription());
            Date myDate = new Date();// formatter.parse(dto.getCreateDate());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            preparedStatement.setDate(4, sqlDate);
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
    public int updateData(ParentCategoryDTO dto) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE  parent_category SET name=? ,  sub=? , description=? WHERE id = ?";
        int flag = -1;
        try {
            dbConnection = MysqlClient.getMysqlClient("main").getDbConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setInt(2, dto.getSub());
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
        String sql = "DELETE FROM  parent_category  WHERE id = ?";
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
        String selectSQL = "SELECT COUNT(*) FROM parent_category";
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
