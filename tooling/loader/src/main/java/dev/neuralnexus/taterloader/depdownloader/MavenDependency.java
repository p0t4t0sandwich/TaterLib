/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.depdownloader;

import org.jetbrains.annotations.ApiStatus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApiStatus.Experimental
public class MavenDependency {
    private final String groupId;
    private final String artifactId;
    private final String version;
    private final String md5;
    private final Path filePath;

    public MavenDependency(String mavenCoords, String md5, Path basePath) {
        String[] parts = mavenCoords.split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid maven coordinates: " + mavenCoords);
        }
        this.groupId = parts[0];
        this.artifactId = parts[1];
        this.version = parts[2];

        this.md5 = md5;
        this.filePath = basePath.resolve(this.getFileUrl());
    }

    /**
     * Get the file path
     *
     * @return The file path
     */
    public Path filePath() {
        return this.filePath;
    }

    /**
     * Get the maven coordinates for a dependency
     *
     * @return The maven coordinates
     */
    public String getMavenUrl() {
        return String.format(
                "%s/%s/%s/%s-%s.jar",
                groupId.replace(".", "/"), artifactId, version, artifactId, version);
    }

    /**
     * Convert a Maven url to a file path
     *
     * @return The file path
     */
    public String getFileUrl() {
        return getMavenUrl().replace("/", File.separator);
    }

    /**
     * Check the MD5 of a file
     *
     * @return If the MD5 matches
     * @throws NoSuchAlgorithmException If the MD5 algorithm is not found
     * @throws IOException If an IO error occurs
     */
    public boolean checkMd5() throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream is = new BufferedInputStream(Files.newInputStream(this.filePath));
        byte[] dataBytes = new byte[1024];
        int nread;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        byte[] mdbytes = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }
        is.close();
        return sb.toString().equals(this.md5);
    }

    public boolean checkExistingMd5() throws NoSuchAlgorithmException, IOException {
        if (Files.exists(this.filePath)) {
            if (this.checkMd5()) {
                return true;
            }
            Files.delete(this.filePath);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", groupId, artifactId, version);
    }
}
