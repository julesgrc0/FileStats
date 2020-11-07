package com.jules.tree;

import com.jules.creator.FileCreator;
import com.jules.creator.TypeFile;
import com.jules.stats.FileStats;
import com.jules.stats.FileStatsInfo;
import com.jules.stats.FolderStats;
import com.jules.stats.FolderStatsInfo;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;

public class Loop {

    public Loop(@NotNull File fs,SavedStats dt){
        for(File element : fs.listFiles()){
            dt.PATHS.add(element.getPath());
            if(element.isFile()){
                dt.FILES_COUNT++;
                FileStats stats = new FileStats(element);
                FileStatsInfo info =stats.getStats();
                try{
                    FileCreator fc= new FileCreator(new File(new File(".").getCanonicalPath()));
                    String[] fsArr = {
                            "{",
                            "   Name: "+info.FILE_NAME,
                            "   Size: "+ ((info.FILE_SIZE != 0) ? ((double)(info.FILE_SIZE/ 1024))+" ko" : "<unknow>" ),
                            "   Path: "+info.FILE_PATH,
                            "   Number lines: "+((info.FILE_LINES != 0) ? info.FILE_LINES : "<unknow>" ),
                            "   is Hidden: "+info.FILE_HIDDEN,
                            "   Created at: "+info.FILE_CREATION,
                            "   Last edit at: "+info.FILE_LAST_EDIT,
                            "   Last access at: "+(info.FILE_LAST_ACCESS != null ? info.FILE_LAST_ACCESS : "<unknow>"),
                            "   Parent Name: "+info.FILE_PARENT_NAME,
                            "   Parent Path: "+info.FILE_PARENT_DIR,
                            "}"
                    };
                    dt.MAX_FILE_SIZE = Math.max(dt.MAX_FILE_SIZE,info.FILE_SIZE);
                    dt.MIN_FILE_SIZE = Math.min(dt.MIN_FILE_SIZE,info.FILE_SIZE);
                    dt.MAX_FILE_LINES = Math.max(dt.MAX_FILE_LINES,info.FILE_LINES);
                    dt.MIN_FILE_LINES = Math.min(dt.MIN_FILE_LINES,info.FILE_LINES);
                    fc.writelines(TypeFile.FILE_STAST,fsArr);
                    fsArr[0] = null;
                    fsArr[fsArr.length-1]=null;
                    dt.fileSI.add(fsArr);
                }catch (IOException ignored){}
            }else{
                dt.FOLDER_COUNT++;

                FolderStats stats = new FolderStats(element);
                FolderStatsInfo info = stats.getStats();
                try{
                    FileCreator fc= new FileCreator(new File(new File(".").getCanonicalPath()));
                    String[] fsArr = {
                            "{",
                            "   Name: "+info.FOLDER_NAME,
                            "   Size: "+ ((info.FOLDER_SIZE != 0) ? ((double)(info.FOLDER_SIZE/ 1024))+" ko" : "<unknow>" ),
                            "   Path: "+info.FOLDER_PATH,
                            "   is Hidden: "+info.FOLDER_HIDDEN,
                            "   Created at: "+info.FOLDER_CREATION,
                            "   Last access at: "+(info.FOLDER_LAST_ACCESS != null ? info.FOLDER_LAST_ACCESS : "<unknow>"),
                            "   Parent Name: "+info.FOLDER_PARENT_NAME,
                            "   Parent Path: "+info.FOLDER_PARENT_DIR,
                            "}"
                    };
                    dt.MAX_FOLDER_SIZE = Math.max(dt.MAX_FOLDER_SIZE,info.FOLDER_SIZE);
                    dt.MIN_FOLDER_SIZE = Math.min(dt.MIN_FOLDER_SIZE,info.FOLDER_SIZE);
                    fc.writelines(TypeFile.FOLDER_STATS,fsArr);
                    fsArr[0] = null;
                    fsArr[fsArr.length-1]=null;
                    dt.folderSI.add(fsArr);
                }catch (IOException ignored){}
                new Loop(element,dt);
            }
        }
    }

}
