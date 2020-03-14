package com.kaixin8848.home.web.base.controller;

import com.kaixin8848.home.utility.StringUtils;
import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultGenerator;
import com.kaixin8848.home.utility.upload.FileUtil;
import com.kaixin8848.home.web.base.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
/**
 * BaseController
 * @author zhoulvjie
 * @date 2018/10/15
 */
@RestController
@RequestMapping("/common")
@Api(description = "公共通用操作接口")
public class CommonController {

    @Autowired
    private UploadFileService uploadFileService;

    @Value("${web.upload-path}")
    private String path;


    /**
     * 文件上传oss fileUpload
     *
     * @param file
     * @return
     */
    @ApiOperation(value="上传图片", notes="上传图片")
    @ApiResponses({
            @ApiResponse(code = 400,message = "上传失败")
    })
    @PostMapping("fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            String fileExtension = getExtensionName(file.getOriginalFilename());//获取文件扩展名
            String fileName = StringUtils.replaceAll(UUID.randomUUID().toString(),"-","")+ "." + fileExtension;
            try {
                FileUtil.uploadFile(file.getBytes(), path+"system/", fileName);
                return ResultGenerator.genSuccessResult("system/"+fileName);
            } catch (Exception e) {
                return ResultGenerator.genFailResult("上传失败:" + e.getMessage());
            }
        }
        return ResultGenerator.genFailResult("上传失败");
    }


    /**
     * 文件上传oss fileUpload
     *
     * @param file
     * @return
     */
    @ApiOperation(value="上传图片", notes="上传图片")
    @ApiResponses({
            @ApiResponse(code = 400,message = "上传失败")
    })
    @PostMapping("fileUpload_V2")
    public Result fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("shortPath")String shortPath,HttpServletRequest request) {
        String id =UUID.randomUUID().toString();
        if (!file.isEmpty()) {
            String fileExtension = getExtensionName(file.getOriginalFilename());//获取文件扩展名
            String fileName = StringUtils.replaceAll(id,"-","")+ "." + fileExtension;
            try {
                FileUtil.uploadFile(file.getBytes(), path+shortPath+"/", fileName);
                return ResultGenerator.genSuccessResult(shortPath+"/"+fileName);
            } catch (Exception e) {
                return ResultGenerator.genFailResult("上传失败:" + e.getMessage());
            }
        }
        return ResultGenerator.genFailResult("上传失败");
    }



    /**
     * Java文件操作 获取文件扩展名
     *
     *  Created on: 2011-8-2
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
    //endregion

}
