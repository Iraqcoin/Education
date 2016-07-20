/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.handlers;


import com.vng.zing.common.HReqParam;
import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.List;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author baonh2
 */
public class FileUploadServlet extends BaseHandler {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
         int id = HReqParam.getInt(req, "id",0);
          if(id == 0)
              return;
         byte[] data = null;
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = upload.parseRequest(req);
                for (Object obj : items) {
                    FileItem fileItem = (FileItem) obj;
                    if ("file".equals(fileItem.getFieldName()) && fileItem.getSize() > 0) {
                        data = fileItem.get();
                    }
                }
            } catch (FileUploadException ex) {
               _logger.error(ex.getMessage() , ex);
            }
            
        }
      
    }

}
