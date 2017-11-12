package com.lingyu.steward.web.service.resource.controller;

import com.alibaba.fastjson.JSONObject;
import com.lingyu.steward.common.utils.StringUtilsExt;
import com.lingyu.steward.web.service.base.ApiResult;
import com.lingyu.steward.web.service.base.ResultCodeEnum;
import com.lingyu.steward.web.service.resource.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Created by allan on 12/11/2017.
 */
@Controller
@RequestMapping(value = "/resource", method = RequestMethod.POST)
public class ResourceController {
    @Autowired
    private StaticResourceService resourceService;

    @RequestMapping("/img")
    @ResponseBody
    public ApiResult upload(@RequestParam(value = "img", required = false) MultipartFile file) throws IOException, URISyntaxException {
        Date now = new Date();
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            throw new FileNotFoundException("未上传任何图片");
        }
        // TODO: 27/10/2017 图片属性校验
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String path = "steward/" + StringUtilsExt.dateFormat(now, "yyyyMMdd") + "/"
                + StringUtilsExt.dateFormat(now, "yyyyMMddHHmmSS") + "." + prefix;

        Resource resource = resourceService.upload(path, file.getInputStream());

        JSONObject responseData = new JSONObject();
        responseData.put("fileUrl", resource.getURI());
        responseData.put("filePath", path);

        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, responseData);
    }
}
