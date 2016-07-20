
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class Import {

    public static void main(String[] args) {
        Gson gson = new Gson();
        String data = "{\n" +
"  \"data\": [\n" +
"    {\n" +
"      \"id\": \"1\",\n" +
"      \"name\": \"Grass World\",\n" +
"      \"level\": 1,\n" +
"      \"description\": \"Create and log common objects\",\n" +
"      \"link_icon\": \"https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/247/level-1-on-try-objective-c-11755b8fe723f3186699116d4997d36c.png\",\n" +
"      \"num_video\": 1,\n" +
"      \"num_challenges\": 14,\n" +
"      \"free\": 0,\n" +
"      \"course_id\": 2\n" +
"    },\n" +
"    {\n" +
"      \"id\": \"2\",\n" +
"      \"name\": \"Ice World\",\n" +
"      \"level\": 2,\n" +
"      \"description\": \"Sending messages and getting results\",\n" +
"      \"link_icon\": \"https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/248/level-2-on-try-objective-c-eb7fc97ab60c09066184efcc9b59064a.png\",\n" +
"      \"num_video\": 1,\n" +
"      \"num_challenges\": 14,\n" +
"      \"free\": 0,\n" +
"      \"course_id\": 2\n" +
"    },\n" +
"    {\n" +
"      \"id\": \"3\",\n" +
"      \"name\": \"Sand World\",\n" +
"      \"level\": 3,\n" +
"      \"description\": \"Control the flow\",\n" +
"      \"link_icon\": \"https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/249/level-3-on-try-objective-c-eb6eb18f34044444af8be55253a496ed.png\",\n" +
"      \"num_video\": 1,\n" +
"      \"num_challenges\": 14,\n" +
"      \"free\": 0,\n" +
"      \"course_id\": 2\n" +
"    },\n" +
"    {\n" +
"      \"id\": \"4\",\n" +
"      \"name\": \"Lava World\",\n" +
"      \"level\": 4,\n" +
"      \"description\": \"Create your own classes\",\n" +
"      \"link_icon\": \"https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/250/level-4-on-try-objective-c-4f070ee5a338c4fad949e42ea0d21b7b.png\",\n" +
"      \"num_video\": 1,\n" +
"      \"num_challenges\": 5,\n" +
"      \"free\": 0,\n" +
"      \"course_id\": 2\n" +
"    },\n" +
"    {\n" +
"      \"id\": \"5\",\n" +
"      \"name\": \"Space World\",\n" +
"      \"level\": 5,\n" +
"      \"description\": \"Learning from mistakes\",\n" +
"      \"link_icon\": \"https://d1ffx7ull4987f.cloudfront.net/images/achievements/large_badge/251/level-5-on-try-objective-c-f86ad56ea2b33997a08e7782f9d12ffb.png\",\n" +
"      \"num_video\": 1,\n" +
"      \"num_challenges\": 6,\n" +
"      \"free\": 1,\n" +
"      \"course_id\": 2\n" +
"    }\n" +
"  ]\n" +
"}";
//       JsonElement element = gson.fromJson (data, JsonElement.class);
//        JsonObject jsonObj = element.getAsJsonObject();
//        JsonArray arr = jsonObj.getAsJsonArray("data").getAsJsonArray();
//        for (JsonElement jsonElement : arr) {
//            System.out.println(jsonElement);
//             SubCategoryDTO dto = gson.fromJson(jsonElement, SubCategoryDTO.class);
//            postData(null, null, SubCategoryModel.getInstances(), dto);
//        }
        
//        JsonElement element = gson.fromJson (data, JsonElement.class);
//        JsonObject jsonObj = element.getAsJsonObject();
//        JsonArray arr = jsonObj.getAsJsonArray("data").getAsJsonArray();
//        for (JsonElement jsonElement : arr) {
//            System.out.println(jsonElement);
//             CategoryDTO dto = gson.fromJson(jsonElement, CategoryDTO.class);
//            postData(null, null, CategoryModel.getInstances(), dto);
//        }
//  JsonElement element = gson.fromJson (data, JsonElement.class);
//        JsonObject jsonObj = element.getAsJsonObject();
//        JsonArray arr = jsonObj.getAsJsonArray("data").getAsJsonArray();
//        for (JsonElement jsonElement : arr) {
//            System.out.println(jsonElement);
//             CourseDTO dto = gson.fromJson(jsonElement, CourseDTO.class);
//            postData(null, null, CourseModel.getInstances(), dto);
//        }
//        
         JsonElement element = gson.fromJson (data, JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();
        JsonArray arr = jsonObj.getAsJsonArray("data").getAsJsonArray();
        for (JsonElement jsonElement : arr) {
            System.out.println(jsonElement);
             CourseDetailsDTO dto = gson.fromJson(jsonElement, CourseDetailsDTO.class);
            postData(null, null, CourseDetailsModel.getInstances(), dto);
        }
       
    }

    private static void postData(HttpServletRequest req, HttpServletResponse res, BaseModel model, BaseDTO dto) {
        int id = model.insertData(dto);
        System.out.println("Import.postData() : " + id);
    }
}
