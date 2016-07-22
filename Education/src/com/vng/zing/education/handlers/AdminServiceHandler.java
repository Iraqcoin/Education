/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.vng.zing.common.HReqParam;
import com.vng.zing.education.dto.BaseDTO;
import com.vng.zing.education.dto.CategoryDTO;
import com.vng.zing.education.dto.CourseDTO;
import com.vng.zing.education.dto.CourseDetailsDTO;
import com.vng.zing.education.dto.ParentCategoryDTO;
import com.vng.zing.education.dto.SubCategoryDTO;
import com.vng.zing.education.model.BaseModel;
import com.vng.zing.education.model.CategoryModel;
import com.vng.zing.education.model.CourseDetailsModel;
import com.vng.zing.education.model.CourseModel;
import com.vng.zing.education.model.ParentCategoryModel;
import com.vng.zing.education.model.SubCategoryModel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class AdminServiceHandler extends BaseHandler {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = HReqParam.getString(req, "action", "");
        
        switch (action) {
            case "get-parent-category":
                getDataPaging(req, resp, ParentCategoryModel.getInstances());
                break;
            case "get-sub-category":
                getDataPaging(req, resp, SubCategoryModel.getInstances());
                break;
            case "get-category":
                getDataPaging(req, resp, CategoryModel.getInstances());
                break;
            case "get-course":
                getDataPaging(req, resp, CourseModel.getInstances());
                break;
            case "get-course-detail":
                getDataPaging(req, resp, CourseDetailsModel.getInstances());
                break;

            case "get-all-parent-category":
                List<ParentCategoryDTO> data = ParentCategoryModel.getInstances().getListAll();
                JsonObject res = parseObjectToJson(data);
                printJSON(res, resp);
                break;
            case "get-all-sub-category":
                List<SubCategoryDTO> sub = SubCategoryModel.getInstances().getListAll();
                JsonObject subres = parseObjectToJson(sub);
                printJSON(subres, resp);
                break;
            case "get-all-category":
                List<CategoryDTO> cate = CategoryModel.getInstances().getListAll();
                JsonObject cateres = parseObjectToJson(cate);
                printJSON(cateres, resp);
                break;
            case "get-all-course":
                List<CourseDTO> course = CourseModel.getInstances().getListAll();
                JsonObject courseres = parseObjectToJson(course);
                printJSON(courseres, resp);
                break;
            case "get-all-course-detail":
                List<CourseDetailsDTO> courseDT = CourseDetailsModel.getInstances().getListAll();
                JsonObject courseDTRes = parseObjectToJson(courseDT);
                printJSON(courseDTRes, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = HReqParam.getString(req, "action", "");
        String data = HReqParam.getString(req, "data", "");
       
        Gson gson = new Gson();
        switch (action) {
            case "post-parent-category":
                ParentCategoryDTO dto = gson.fromJson(data, ParentCategoryDTO.class);
                postData(req, resp, ParentCategoryModel.getInstances(), dto);
                break;
            case "post-sub-category":
                SubCategoryDTO Subdto = gson.fromJson(data, SubCategoryDTO.class);
                postData(req, resp, SubCategoryModel.getInstances(), Subdto);
                break;
            case "post-category":
                CategoryDTO Catedto = gson.fromJson(data, CategoryDTO.class);
                postData(req, resp, CategoryModel.getInstances(), Catedto);
                break;
            case "post-course":
                CourseDTO Coursedto = gson.fromJson(data, CourseDTO.class);
                postData(req, resp, CourseModel.getInstances(), Coursedto);
                break;
            case "post-course-detail":
                CourseDetailsDTO CoursedDT = gson.fromJson(data, CourseDetailsDTO.class);
                postData(req, resp, CourseDetailsModel.getInstances(), CoursedDT);
                break;

            case "update-parent-category":
                ParentCategoryDTO dtoU = gson.fromJson(data, ParentCategoryDTO.class);
                updateData(req, resp, ParentCategoryModel.getInstances(), dtoU);
                break;
            case "update-sub-category":
                SubCategoryDTO SubdUp = gson.fromJson(data, SubCategoryDTO.class);
                updateData(req, resp, SubCategoryModel.getInstances(), SubdUp);
                break;
            case "update-category":
                CategoryDTO CatedUp = gson.fromJson(data, CategoryDTO.class);
                updateData(req, resp, CategoryModel.getInstances(), CatedUp);
                break;
            case "update-course":
                CourseDTO CoursedUp = gson.fromJson(data, CourseDTO.class);
                updateData(req, resp, CourseModel.getInstances(), CoursedUp);
                break;
            case "update-course-detail":
                CourseDetailsDTO CoursedDTUp = gson.fromJson(data, CourseDetailsDTO.class);
                updateData(req, resp, CourseDetailsModel.getInstances(), CoursedDTUp);
                break;

            case "delete-parent-category":
                deleteData(req, resp, ParentCategoryModel.getInstances());
                break;
            case "delete-sub-category":
                deleteData(req, resp, SubCategoryModel.getInstances());
                break;
            case "delete-category":
                deleteData(req, resp, CategoryModel.getInstances());
                break;
            case "delete-course":
                deleteData(req, resp, CourseModel.getInstances());
                break;
            case "delete-course-detail":
                deleteData(req, resp, CourseDetailsModel.getInstances());
                break;
        }
    }

    private void getDataPaging(HttpServletRequest req, HttpServletResponse res, BaseModel model) {
        int page = HReqParam.getInt(req, "page", 1);
        int maxRow = HReqParam.getInt(req, "maxRow", 10);
        int offset = (page - 1) * maxRow;
        int parentID = HReqParam.getInt(req, "parentID", -1);
        int admin = HReqParam.getInt(req, "admin",0);
        if (parentID != -1) {
            List<? extends BaseDTO> data = model.getScileDataFromParentID(parentID, offset, maxRow);
            JsonObject ress = parseObjectToJson(data);
            printJSON(ress, res);
            return;
        }
        List<? extends BaseDTO> data = model.getScileData(offset, maxRow);
        JsonObject ress = null;
        if(admin == 0)
           ress = parseObjectToJson(data);
        else
            ress = parseObjectToJson(data, model.getTotal());
        printJSON(ress, res);
    }

    private void postData(HttpServletRequest req, HttpServletResponse res, BaseModel model, BaseDTO dto) {
        int id = model.insertData(dto);
        printJSON(parseErrorToJson(id), res);
    }

    private void updateData(HttpServletRequest req, HttpServletResponse res, BaseModel model, BaseDTO dto) {
        int id = model.updateData(dto);
        printJSON(parseErrorToJson(id), res);
    }

    private void deleteData(HttpServletRequest req, HttpServletResponse res, BaseModel model) {
        int id = HReqParam.getInt(req, "id", 0);
        if (id <= 0) {
            printJSON(parseErrorToJson(-1), res);
            return;
        }
        int ids = model.deleteData(id);
        printJSON(parseErrorToJson(ids), res);
    }
    
    

    private JsonObject parseObjectToJson(List<? extends BaseDTO> dto) {
        JsonObject results = new JsonObject();
        if (dto == null) {
            results.addProperty("error", -1);
        }
        Gson gson = new Gson();
        JsonElement data = gson.toJsonTree(dto, new TypeToken<List<?>>() {
        }.getType());
        results.addProperty("error", 0);
        results.add("data", data.getAsJsonArray());
        return results;
    }
    private JsonObject parseObjectToJson(List<? extends BaseDTO> dto,  int total) {
        JsonObject results = new JsonObject();
        if (dto == null) {
            results.addProperty("error", -1);
        }
        Gson gson = new Gson();
        JsonElement data = gson.toJsonTree(dto, new TypeToken<List<?>>() {
        }.getType());
        results.addProperty("error", 0);
        results.addProperty("total", total);
        results.add("data", data.getAsJsonArray());
        return results;
    }

    private JsonObject parseErrorToJson(int id) {
        JsonObject results = new JsonObject();
        if (id < 0) {
            results.addProperty("error", -1);
        } else {
            results.addProperty("error", 0);
        }
        return results;
    }
}
