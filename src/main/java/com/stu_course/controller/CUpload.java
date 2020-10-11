package com.stu_course.controller;

//import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class CUpload {
    @RequestMapping("/show_upload")
    public String showUpload(Model model){
        String pathName =
         "C:/Users/红军/IdeaProjects/stu_course/src/main/resources/static/uploadfile";
        File file = new File(pathName);
        String[] fileList = file.list();
        model.addAttribute("file_list", fileList);
        return "upload_download";
    }
    @RequestMapping("/download")
    public void downLoad(HttpServletResponse response, HttpServletRequest request){
        String fileName = request.getParameter("fileName");
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        String pathName =
        "C:/Users/红军/IdeaProjects/stu_course/src/main/resources/static/uploadfile/"
                + fileName;
        System.out.println(pathName);
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(pathName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("export file finish");
    }
 //   @RequestMapping(value = "upload", method = RequestMethod.POST)
//    public @ResponseBody
////    String upload(MultipartFile file){
////        try {
////            String pathName ="C:/Users/红军/IdeaProjects/stu_course/src/main/resources/static/uploadfile/" + file.getOriginalFilename();
////            System.out.println(pathName);
////            FileUtils.writeByteArrayToFile(new File(pathName), file.getBytes());
////            return  "redirect:show_upload";
////        } catch (IOException e) {
////            e.printStackTrace();
////            return "error";
////        }
////    }
}
