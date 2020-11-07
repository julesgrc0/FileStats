package com.jules.stats;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class FileStatsInfo {
    public String FILE_NAME="";
    public long FILE_SIZE=0;
    public Path FILE_PATH=null;
    public long FILE_LAST_EDIT=0;
    public FileTime FILE_CREATION=null;
    public FileTime FILE_LAST_ACCESS=null;
    public String FILE_PARENT_NAME="";
    public Path FILE_PARENT_DIR=null;
    public int FILE_LINES=0;
    public boolean FILE_HIDDEN=false;
}
