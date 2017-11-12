package com.lingyu.steward.web.service.resource;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 静态资源服务
 *
 * @author allan
 * @date 26/10/2017
 */
public interface StaticResourceService {
    /**
     * 上传一个资源
     *
     * @param path 相对路径
     * @param data 数据流
     * @return 新资源的资源定位符
     * @throws IOException
     */
    Resource upload(String path, InputStream data) throws IOException;

    /**
     * 获取指定资源的资源定位符
     *
     * @param path 资源路径（相对）
     * @return 资源实体
     */
    Resource get(String path);

    /**
     * 删除资源
     *
     * @param path 资源路径（相对）
     * @throws IOException 删除时错误
     */
    void delete(String path) throws IOException;
}
