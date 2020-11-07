package com.jules.stats;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FolderStats {

    private final File dir;

    public FolderStats(File folder){
        if(folder.isFile()){
            this.dir = folder.getParentFile();
        }else{
            this.dir = folder;
        }
    }

    public FolderStatsInfo getStats(){
        FolderStatsInfo fsi = new FolderStatsInfo();
        fsi.FOLDER_NAME=this.dir.getName();
        fsi.FOLDER_PATH=this.dir.toPath();
        fsi.FOLDER_PARENT_DIR=this.dir.getParentFile().toPath();
        fsi.FOLDER_PARENT_NAME=this.dir.getParentFile().getName();
        fsi.FOLDER_HIDDEN=this.dir.isHidden();
        fsi.FOLDER_SIZE= this.getFolderSize(this.dir);

        try{
            BasicFileAttributes attr = Files.readAttributes(Paths.get(this.dir.getPath()),BasicFileAttributes.class);
            fsi.FOLDER_CREATION=attr.creationTime();
            fsi.FOLDER_LAST_ACCESS=attr.lastModifiedTime();
            fsi.FOLDER_SIZE= Math.max(attr.size(), fsi.FOLDER_SIZE);
        }catch (IOException ignored) { }
        return fsi;
    }

    private long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                length += file.length();
            } else {
                length += getFolderSize(file);
            }
        }
        return length;
    }
}
