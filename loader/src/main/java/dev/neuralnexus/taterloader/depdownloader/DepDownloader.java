/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.depdownloader;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterloader.Loader;
import dev.neuralnexus.taterloader.impl.LoaderImpl;

import org.jetbrains.annotations.ApiStatus;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@ApiStatus.Experimental
public class DepDownloader {
    private final String[] repos;
    final MavenDependency[] deps;

    public DepDownloader(String[] repos, Map<String, String> depInfo) {
        this.repos = repos;
        this.deps = new MavenDependency[depInfo.size()];
        int i = 0;
        Path basePath =
                TaterAPIProvider.api()
                        .get()
                        .platformData()
                        .modFolder()
                        .resolve(LoaderImpl.PROJECT_ID)
                        .resolve("libraries");
        for (Map.Entry<String, String> entry : depInfo.entrySet()) {
            deps[i] = new MavenDependency(entry.getKey(), entry.getValue(), basePath);
            i++;
        }
    }

    /** Downloads all dependencies */
    public void downloadAll() {
        Logger logger = Loader.instance().logger("taterlib-depdownloader");
        for (MavenDependency dep : deps) {
            try {
                if (Files.exists(dep.filePath())) {
                    if (dep.checkExistingMd5()) {
                        continue;
                    } else {
                        logger.warn("MD5 mismatch for " + dep + ", re-downloading");
                    }
                }
                for (String repo : repos) {
                    if (!repo.endsWith("/")) {
                        repo += "/";
                    }
                    URL url = new URL(repo + dep.getMavenUrl());
                    try {
                        downloadDep(url, dep.filePath());
                        if (dep.checkMd5()) {
                            logger.info("Downloaded " + dep + " from " + repo);
                            break;
                        }
                        Files.delete(dep.filePath());
                        logger.warn("MD5 mismatch for " + dep + ", download aborted");
                    } catch (IOException e) {
                        Files.deleteIfExists(dep.filePath());
                        logger.error("Failed to download " + dep + " from " + repo);
                    }
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Downloads a file from a URL and stores it in the specified location
     *
     * @param url location to read
     * @param path location to write
     * @throws IOException if an I/O error occurs
     */
    public void downloadDep(URL url, Path path) throws IOException {
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
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
}
