package com.lingyu.steward.web.service.resource.base;

import com.lingyu.steward.web.service.resource.function.FileObjectFunction;
import com.lingyu.steward.web.service.resource.vfs.VFSHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileObject;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * VFS资源定位符
 * Created by allan on 12/11/2017.
 */
public class VFSFileSystemResource extends AbstractResource implements Resource, WritableResource {
    private static final Log log = LogFactory.getLog(VFSFileSystemResource.class);

    /**
     * 资源地址
     */
    private final URI uri;
    /**
     * 文件据对路径
     */
    private final String fileName;
    private transient final VFSHelper helper;

    public VFSFileSystemResource(VFSHelper vfsHelper, String file, URI uri) {
        this.uri = uri;
        this.helper = vfsHelper;
        this.fileName = file;
    }

    public <T> T accessFileObject(FileObjectFunction<FileObject, T> function) throws IOException {
        return helper.handle(fileName, function);
    }

    @Override
    public boolean exists() {
        try {
            return accessFileObject(FileObject::exists);
        } catch (IOException e) {
            log.error("exists", e);
            return false;
        }
    }

    @Override
    public long contentLength() throws IOException {
        return accessFileObject(fileObject -> fileObject.getContent().getSize());
    }

    @Override
    public long lastModified() throws IOException {
        return accessFileObject(fileObject -> fileObject.getContent().getLastModifiedTime());
    }

    @Override
    public String getFilename() {
        return super.getFilename();
    }

    @Override
    public URI getURI() throws IOException {
        return uri;
    }

    @Override
    public URL getURL() throws IOException {
        return uri.toURL();
    }

    @Override
    public boolean isWritable() {
        try {
            return accessFileObject(FileObject::isWriteable);
        } catch (IOException e) {
            log.error("isWritable", e);
            return false;
        }
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new ByteArrayOutputStream() {
            @Override
            public void close() throws IOException {
                accessFileObject(fileObject -> {
                    OutputStream outputStream = fileObject.getContent().getOutputStream();
                    StreamUtils.copy(buf, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    return null;
                });
            }
        };
    }

    @Override
    public String getDescription() {
        return fileName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return accessFileObject(fileObject -> {
            InputStream inputStream = fileObject.getContent().getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            StreamUtils.copy(inputStream, byteArrayOutputStream);
            inputStream.close();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        });

    }
}
