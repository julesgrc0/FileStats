package com.jules.tree;

import com.jules.stats.FolderStatsInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SavedStats {

    public int FILES_COUNT=0;
    public int FOLDER_COUNT=0;
    public int TOTAL_COUNT=0;

    public long MAX_FOLDER_SIZE=0;
    public long MIN_FOLDER_SIZE=0;

    public long MAX_FILE_LINES=0;
    public long MIN_FILE_LINES=0;
    public long MAX_FILE_SIZE=0;
    public long MIN_FILE_SIZE=0;

    public List<String[]> folderSI = new ArrayList<>();
    public List<String[]> fileSI = new ArrayList<>();

    public List<String> PATHS = new ArrayList<>();
    public Date Start;
    public Date End;
    public long Duration=0;
}
