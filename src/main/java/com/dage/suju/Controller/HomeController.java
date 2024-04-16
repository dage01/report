package com.dage.suju.Controller;
import com.dage.suju.DTO.*;
import com.dage.suju.Service.*;


import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;


import java.io.*;
import java.util.*;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    UserService Userservice;
    @GetMapping("/")
    public String home(){


        return "mod_list";
    }


    @RequestMapping("/{seq}")
    //@ResponseBody
    public String mod(@PathVariable String seq,Model model){




        ListDto info = Userservice.mod_list(seq);
        ListDto info_dt = Userservice.mod_list_dt(seq);
        List<ListDto> file_list = Userservice.file_list(seq);

        model.addAttribute("info",info);
        model.addAttribute("info_dt",info_dt);
        model.addAttribute("file", file_list);

        return "mod_list";

    }

    @RequestMapping(value = "/file_download")
    @ResponseBody
    public ResponseEntity<Resource> fileDownLoad(
            @Param("seq")String seq, HttpServletResponse response,
            HttpServletRequest request) throws IOException {

        ListDto filesInfo = Userservice.getFileInfo(seq);
        String str = filesInfo.getOrigin_nm();
        String str2 = filesInfo.getReal_file_name();

        if (str.indexOf(" ") != -1) {


            str2 = URLEncoder.encode(str2,"UTF-8");
            str2 = str2.replaceAll("[+]", "%20");
            UrlResource urlResource = new UrlResource(filesInfo.getPath() + filesInfo.getMiddle_path() + str2);

            String realFileName = filesInfo.getReal_file_name();


            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("Content-Disposition","attachment; filename="+ URLEncoder.encode(realFileName,"UTF-8"));
            headers.set("Access-Control-Expose-Headers", "X-Filename");
            headers.set("X-Filename", str2);
            return new ResponseEntity<Resource>(urlResource,headers,HttpStatus.OK);

        } else {

            UrlResource urlResource1 = new UrlResource(filesInfo.getPath() + '/' + filesInfo.getOrigin_nm());

            String realFileName = filesInfo.getReal_file_name();

            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("Content-Disposition","attachment; filename="+ URLEncoder.encode(realFileName,"UTF-8"));
            headers.set("Access-Control-Expose-Headers", "X-Filename");
            headers.set("X-Filename", realFileName);
            return new ResponseEntity<Resource>(urlResource1,headers,HttpStatus.OK);

        }


    }



}
