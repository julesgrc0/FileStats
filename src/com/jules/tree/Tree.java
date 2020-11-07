package com.jules.tree;


import com.jules.creator.FileCreator;
import com.jules.creator.TypeFile;
import com.jules.creator.XmlCreator;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Tree {

    private SavedStats dt = new SavedStats();

    public Tree(String directory){
        File fs = new File(directory);
        File dir;
        if(fs.isFile()){
            dir =fs.getParentFile();
        }else{
            dir =fs;
        }
        try{
            XmlCreator xc= new XmlCreator(new File(new File(".").getCanonicalPath()));
            xc.init();
        }catch(IOException ignored){}
        try{
            FileCreator fc= new FileCreator(new File(new File(".").getCanonicalPath()));
            fc.init();
        }catch (IOException ignored){}
        dt.Start = new Date();
        new Loop(dir,dt);
    }

    public SavedStats getWriteLoopStats(){
        dt.End = new Date();
        dt.Duration = dt.End.getTime() - dt.Start.getTime();
        dt.TOTAL_COUNT = dt.FILES_COUNT+dt.FOLDER_COUNT;
        try{
            FileCreator fc= new FileCreator(new File(new File(".").getCanonicalPath()));
            XmlCreator xc= new XmlCreator(new File(new File(".").getCanonicalPath()));
            String[] Arr = {
                   "Start at: "+dt.Start,
                   "End at: "+dt.End,
                   "Duration: "+dt.Duration+" ms",
                    "Larger file: "+(dt.MAX_FILE_SIZE/1024)+" ko",
                    "Smallest file: "+(dt.MIN_FILE_SIZE /1024)+" ko",
                    "Larger folder: "+(dt.MAX_FOLDER_SIZE/1024)+" ko",
                    "Smallest folder: "+(dt.MIN_FOLDER_SIZE /1024)+" ko",
                    "File with the most lines: "+dt.MAX_FILE_LINES,
                    "File with fewer lines: "+dt.MIN_FILE_LINES,
                    "Folder count: "+dt.FOLDER_COUNT,
                    "Files count: "+dt.FILES_COUNT,
                    "Total count: "+dt.TOTAL_COUNT,
                    "",
                    ""
            };
            xc.writelines(TypeFile.FOLDER_STATS,dt.folderSI);
            xc.writelines(TypeFile.FILE_STAST,dt.fileSI);
            fc.writelines(TypeFile.FILE_Tree,Arr);
            for (String line : dt.PATHS){
                fc.write(TypeFile.FILE_Tree,line+"\n");
            }
        }catch (IOException e){}
        return dt;
    }

}
