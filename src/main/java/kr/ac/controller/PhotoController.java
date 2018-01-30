package kr.ac.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.controller.board.dto.JsonResult;
import kr.ac.controller.board.dto.NurseVO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

@Controller
public class PhotoController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired private kr.ac.controller.board.dao.UserDAO UserDaoImpl=null;	
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void uploadImageCtlr(ModelMap model,
                                  HttpServletRequest request,
                                  @RequestParam(value = "photo") MultipartFile file){
    	System.out.println("upload");
        String rootPath = "C:\\Users\\gunyoungkim";
        System.out.println(rootPath);
        File dir = new File(rootPath + File.separator + "img");
        System.out.println(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
        System.out.println(serverFile);
        String latestUploadPhoto = file.getOriginalFilename();
        System.out.println(latestUploadPhoto);
        //write uploaded image to disk
        try {
            try (InputStream is = file.getInputStream();
                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }

        //send photo name to jsp
        model.addAttribute("photo", latestUploadPhoto);
    }
   }
