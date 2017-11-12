package com.lingyu.steward.web.service.resource.base;

import lombok.Getter;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * http协议的资源
 *
 * @author allan
 * @date 12/11/2017
 */
@Getter
public class HttpFileSystemResource extends AbstractResource implements WritableResource {
    /**
     * 文件
     */
    private final File file;
    /**
     * 绝对物理路径
     */
    private final String realPath;
    /**
     * 相对路径
     */
    private final String path;
    /**
     * 可访问的http协议地址
     */
    private final URI uriPrefix;

    public HttpFileSystemResource(File file, String path, URI uriPrefix) {
        Assert.notNull(file, "File must not be null");
        this.file = file;
        this.realPath = StringUtils.cleanPath(file.getPath());
        this.path = path;
        this.uriPrefix = uriPrefix;
    }

    public HttpFileSystemResource(String realPath, String path, URI uriPrefix) {
        Assert.notNull(realPath, "realPath must not be null");
        this.file = new File(realPath);
        this.realPath = StringUtils.cleanPath(realPath);
        this.path = path;
        this.uriPrefix = uriPrefix;
    }

    @Override
    public boolean exists() {
        return this.file.exists();
    }

    @Override
    public boolean isReadable() {
        return this.file.canRead() && !this.file.isDirectory();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public boolean isWritable() {
        return this.file.canWrite() && !this.file.isDirectory();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.file);
    }

    @Override
    public URL getURL() throws IOException {
        return this.file.toURI().toURL();
    }

    @Override
    public long contentLength() throws IOException {
        return this.file.length();
    }

    @Override
    public Resource createRelative(String relativePath) {
        String pathToUse = StringUtils.applyRelativePath(this.realPath, relativePath);
        return new FileSystemResource(pathToUse);
    }

    @Override
    public String getFilename() {
        return this.file.getName();
    }

    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof FileSystemResource && this.realPath.equals(((HttpFileSystemResource) obj).realPath);
    }

    @Override
    public int hashCode() {
        return this.realPath.hashCode();
    }

    @Override
    public URI getURI() throws IOException {
        StringBuilder stringBuilder = new StringBuilder(uriPrefix.toString());
        if (!stringBuilder.toString().endsWith("/") && !path.startsWith("/")) {
            stringBuilder.append("/");
        }
        stringBuilder.append(path);
        try {
            return new URI(stringBuilder.toString());
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }
}
