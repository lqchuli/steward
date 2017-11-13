package com.lingyu.steward.web.service.resource.impl;

import com.lingyu.steward.web.service.resource.base.HttpFileSystemResource;
import com.lingyu.steward.web.service.resource.StaticResourceService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 本地静态资源服务
 *
 * @author allan
 * @date 12/11/2017
 */
@Service("resourceService")
@Profile("!container")
public class LocalResourceServiceImpl implements StaticResourceService {
    private static final String RESOURCE_PATH = "/resource/upload/";
    private final URI uriPrefix;
    private final File fileHome;

    public LocalResourceServiceImpl(WebApplicationContext context) throws URISyntaxException {
        fileHome = new File(context.getServletContext().getRealPath(RESOURCE_PATH));
        fileHome.mkdir();
        this.uriPrefix = new URI("http://localhost:8080" + context.getServletContext().getContextPath() + RESOURCE_PATH);
    }

    @Override
    public Resource upload(String path, InputStream data) throws IOException {
        File file = new File(fileHome, path);
        file.getParentFile().mkdirs();
        if (file.exists()) {
            throw new IllegalStateException("" + file.toString() + " already existing");
        }
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            StreamUtils.copy(data, outputStream);
        }
        return new HttpFileSystemResource(file, path, uriPrefix);
    }

    @Override
    public Resource get(String path) {
        return new HttpFileSystemResource(new File(fileHome, path), path, uriPrefix);
    }

    @Override
    public void delete(String path) throws IOException {
        File file = new File(fileHome, path);
        if (file.exists()) {
            file.delete();
        }
    }
}
