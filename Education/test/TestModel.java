
import com.vng.zing.education.dto.BaseDTO;
import com.vng.zing.education.dto.SubCategoryDTO;
import com.vng.zing.education.model.SubCategoryModel;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class TestModel {
    public static void main(String[] args) {
       // SubCategoryDTO dto = new SubCategoryDTO(1, "https://www.codeschool.com/assets/paths/badge-html-css-min-a6acee226fc4253bc3d56bf8873eca2731005113a7291a094c656845d6c65c30.svg", 1, "CSS", "NCNCN");
       SubCategoryDTO data = (SubCategoryDTO)SubCategoryModel.getInstances().getDataById(1);
        System.out.println(data.getLink_icon());
      
      //  System.out.println(id);
    }
}
