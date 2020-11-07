package com.jules.stats;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class FolderStatsInfo {
    public String FOLDER_NAME="";
    public long FOLDER_SIZE=0;
    public Path FOLDER_PATH=null;
    public FileTime FOLDER_CREATION=null;
    public FileTime FOLDER_LAST_ACCESS=null;
    public String FOLDER_PARENT_NAME="";
    public Path FOLDER_PARENT_DIR=null;
    public boolean FOLDER_HIDDEN=false;
}
