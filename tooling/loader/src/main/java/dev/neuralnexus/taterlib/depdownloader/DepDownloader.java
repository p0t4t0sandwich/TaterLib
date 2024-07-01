/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.depdownloader;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.logger.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class DepDownloader {
    private final String[] repos;
    final Map<String, String> deps;

    public DepDownloader(String[] repos, Map<String, String> deps) {
        this.repos = repos;
        this.deps = deps;
    }

    public void downloadAll() {
        Logger logger = Loader.instance().platformData().logger("taterlib-depdownloader");
        Path path =
                TaterAPIProvider.api()
                        .get()
                        .platformData()
                        .configFolder()
                        .resolve(LoaderImpl.PROJECT_ID);
        for (String dependency : deps.keySet()) {
            try {
                Path jarPath = getJarPath(path, dependency);
                if (Files.exists(jarPath)) {
                    if (checkMd5(jarPath, deps.get(dependency))) {
                        continue;
                    }
                    Files.delete(jarPath);
                    logger.warn("MD5 mismatch for " + dependency + ", re-downloading");
                }
                for (String repo : repos) {
                    if (!repo.endsWith("/")) {
                        repo += "/";
                    }
                    URL url = new URL(repo + getMavenCoords(dependency));
                    try {
                        downloadDep(url, jarPath);
                        if (checkMd5(jarPath, deps.get(dependency))) {
                            break;
                        }
                        Files.delete(jarPath);
                        logger.warn("MD5 mismatch for " + dependency + ", download aborted");
                    } catch (IOException e) {
                        Files.deleteIfExists(jarPath);
                        logger.error("Failed to download " + dependency + " from " + repo);
                    }
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public Path getJarPath(Path path, String dependency) {
        String[] parts = dependency.split(":");
        String jarName = String.format("%s-%s.jar", parts[1], parts[2]);
        return path.resolve(jarName);
    }

    /**
     * Get the maven coordinates for a dependency
     *
     * @param dependency The dependency
     * @return The maven coordinates
     */
    public String getMavenCoords(String dependency) {
        String[] parts = dependency.split(":");
        return String.format(
                "%s/%s/%s/%s-%s.jar",
                parts[0].replace(".", "/"), parts[1], parts[2], parts[1], parts[2]);
    }

    /**
     * Downloads a file from a URL and stores it in the specified location
     *
     * @param url location to read
     * @param path location to write
     * @throws IOException if an I/O error occurs
     */
    public void downloadDep(URL url, Path path) throws IOException {
        URLConnection uc = url.openConnection();
        int len = uc.getContentLength();
        try (InputStream is = new BufferedInputStream(uc.getInputStream())) {
            byte[] data = new byte[len];
            int offset = 0;
            while (offset < len) {
                int read = is.read(data, offset, data.length - offset);
                if (read < 0) {
                    break;
                }
                offset += read;
            }
            if (offset < len) {
                throw new IOException(String.format("Read %d bytes; expected %d", offset, len));
            }
            try (OutputStream os =
                    new BufferedOutputStream(Files.newOutputStream(path.toFile().toPath()))) {
                os.write(data);
            } catch (IOException e) {
                Files.deleteIfExists(path);
                throw e;
            }
        }
    }

    public boolean checkMd5(Path path, String md5) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream is = new BufferedInputStream(Files.newInputStream(path.toFile().toPath()));
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
        return sb.toString().equals(md5);
    }
}
