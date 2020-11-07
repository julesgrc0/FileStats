package com.jules.stats;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class FileStats {

    private final File fs;

    public FileStats(File fs){
        this.fs=fs;
    }

    public FileStatsInfo getStats() {
        FileStatsInfo fsi = new FileStatsInfo();
        fsi.FILE_NAME = fs.getName();
        fsi.FILE_LAST_EDIT = fs.lastModified();
        try{
            BasicFileAttributes attr = Files.readAttributes(Paths.get(fs.getPath()),BasicFileAttributes.class);
            fsi.FILE_CREATION =attr.creationTime();
            fsi.FILE_SIZE = Math.max(attr.size(), fs.length());
            fsi.FILE_LAST_ACCESS = attr.lastAccessTime();
        }catch (IOException e){
            fsi.FILE_CREATION=null;
            fsi.FILE_SIZE= fs.length();
            fsi.FILE_LAST_ACCESS = null;
        }

        fsi.FILE_PARENT_DIR = fs.getParentFile().toPath();
        fsi.FILE_PARENT_NAME = fs.getParentFile().getName();
        fsi.FILE_PATH = fs.toPath();
        fsi.FILE_HIDDEN = fs.isHidden();

        if(fs.canRead()){
            try{
                Scanner reader = new Scanner(fs);
                int count = 0;
                while (reader.hasNextLine()) {
                    reader.nextLine();
                    count++;
                }
                fsi.FILE_LINES = count;
            }catch (IOException ignored){}
        }
        return fsi;
    }

}
