package com.lingyu.steward.web.service.resource.impl;

import com.lingyu.steward.web.service.resource.base.VFSFileSystemResource;
import com.lingyu.steward.web.service.resource.vfs.VFSHelper;
import com.lingyu.steward.web.service.resource.StaticResourceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 基于VFS的静态资源处理
 * 生产环境用该服务
 *
 * @author allan
 * @date 12/11/2017
 */
@Service("resourcService")
@Profile("container")
public class VFSResourceServiceImpl implements StaticResourceService {
    private static final Log log = LogFactory.getLog(VFSResourceServiceImpl.class);

    private URI uriPrefix;
    private URI fileHome;

    @Autowired
    private VFSHelper vfsHelper;

    @Autowired
    public VFSResourceServiceImpl(Environment environment) throws URISyntaxException {
        String uriPrefix = environment.getProperty("resourceUri", "");
        if (StringUtils.isEmpty(uriPrefix)) {
            throw new IllegalStateException("未设置resourceUri");
        }
        String fileHome = environment.getProperty("resourceHome", "");
        if (StringUtils.isEmpty(fileHome)) {
            throw new IllegalStateException("未设置resourceHome");
        }
        this.uriPrefix = new URI(uriPrefix);
        this.fileHome = new URI(fileHome);
    }

    @Override
    public Resource upload(String path, InputStream data) throws IOException {
        StringBuilder filePath = new StringBuilder(fileHome.toString());
        if (!filePath.toString().endsWith("/") && !path.startsWith("/")) {
            filePath.append("/");
        }
        filePath.append(path);

        vfsHelper.handle(filePath.toString(), file -> {
            if (file.exists()) {
                throw new IllegalStateException("" + file.toString() + " already existing");
            }
            OutputStream out = file.getContent().getOutputStream();

            try {
                StreamUtils.copy(data, out);
            } catch (IOException e) {
                throw new FileSystemException(e);
            } finally {
                try {
                    data.close();
                    out.close();
                } catch (IOException e) {
                    log.info("Exception on close stream." + e);
                }
            }
            return null;
        });

        return get(path);
    }

    @Override
    public Resource get(String path) {
        StringBuilder stringBuilder = new StringBuilder(uriPrefix.toString());
        if (!stringBuilder.toString().endsWith("/") && !path.startsWith("/")) {
            stringBuilder.append("/");
        }
        stringBuilder.append(path);


        StringBuilder fileBuilder = new StringBuilder(fileHome.toString());
        if (!fileBuilder.toString().endsWith("/") && !path.startsWith("/")) {
            fileBuilder.append("/");
        }
        fileBuilder.append(path);

        try {
            return new VFSFileSystemResource(vfsHelper, fileBuilder.toString(), new URI(stringBuilder.toString()));
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public void delete(String path) throws IOException {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        StringBuilder file = new StringBuilder(fileHome.toString());
        if (!file.toString().endsWith("/") && !path.startsWith("/")) {
            file.append("/");
        }
        file.append(path);

        vfsHelper.handle(file.toString(), FileObject::delete);
    }
}
