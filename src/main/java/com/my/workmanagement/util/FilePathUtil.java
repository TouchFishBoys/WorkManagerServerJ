package com.my.workmanagement.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FilePathUtil {
    public static class FilePathBuilder {
        private final Queue<String> pathQueue;

        private FilePathBuilder() {
            this.pathQueue = new LinkedList<>();
        }

        public static FilePathBuilder builder() {
            return new FilePathBuilder();
        }

        public FilePathBuilder root() {
            this.pathQueue.clear();
            return this;
        }

        public FilePathBuilder enter(String directory) {
            if (directory.startsWith("/")) {
                directory = directory.substring(directory.indexOf("/"));
            }
            this.pathQueue.offer(directory);
            return this;
        }

        public String build() {
            StringBuilder path = new StringBuilder();
            while (!pathQueue.isEmpty()) {
                path.append("/").append(pathQueue.poll());
            }
            return path.toString();
        }
    }
}
