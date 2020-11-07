package com.jules.creator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FileCreator {

    private final File dir;

    public FileCreator(File file){
        if(file.isFile()){
            this.dir = file.getParentFile();
        }else{
            this.dir = file;
        }
    }

    private String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void filecreate(File dirpath,TypeFile type){
        if(!dirpath.isFile()){

            Path p = dirpath.toPath();
            String createPath="";
            switch (type){
                case FILE_Tree:
                    createPath="tree.txt";
                    break;
                case FILE_STAST:
                    createPath="file_stats.txt";
                    break;
                case FOLDER_STATS:
                    createPath="folder_stats.txt";
                    break;
            }
            try{
                if(!(new File(p.resolve(createPath).toString()).exists())){
                     new File(p.resolve(createPath).toString()).createNewFile();
                }
            }catch (IOException ignored){}
        }
    }

    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public void init(){
        Path p = this.dir.toPath();
        String Folder = "./FileStats "+getDate();
        deleteDirectory(new File(String.valueOf(p.resolve(Folder))));
        List<TypeFile> typefile = new ArrayList<>(EnumSet.allOf(TypeFile.class));
        for(TypeFile op : typefile){
            this.create(op);
        }
    }

    public void create(TypeFile type) {
        Path p = this.dir.toPath();
        String Folder = "./FileStats "+getDate();
        if(new File(p.resolve(Folder).toString()).exists()){
            this.filecreate(new File(p.resolve(Folder).toString()),type);
        }else{
                if(new File(p.resolve(Folder).toString()).mkdir()){
                    this.filecreate(new File(p.resolve(Folder).toString()),type);
                }
        }
    }

    public void write(TypeFile type,String content){
        Path p = this.dir.toPath();
        String Folder = "./FileStats "+getDate();
        if(!new File(p.resolve(Folder).toString()).exists()){
            this.create(type);
        }
        String createPath="";
        switch (type){
            case FILE_Tree:
                createPath="tree.txt";
                break;
            case FILE_STAST:
                createPath="file_stats.txt";
                break;
            case FOLDER_STATS:
                createPath="folder_stats.txt";
                break;
        }
        File file = new File(p.resolve(Folder).resolve(createPath).toString());
        try{
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(content);
            br.close();
            fr.close();
        }catch (IOException ignored){}
    }

    public void writelines(TypeFile type,String[] lines){
        Path p = this.dir.toPath();
        String Folder = "./FileStats "+getDate();
        if(!new File(p.resolve(Folder).toString()).exists()){
            this.create(type);
        }
        String createPath="";
        switch (type){
            case FILE_Tree:
                createPath="tree.txt";
                break;
            case FILE_STAST:
                createPath="file_stats.txt";
                break;
            case FOLDER_STATS:
                createPath="folder_stats.txt";
                break;
        }
        File file = new File(p.resolve(Folder).resolve(createPath).toString());
        try{
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            for(String line : lines){
                br.newLine();
                br.write(line);
            }
            br.close();
            fr.close();
        }catch (IOException ignored){}
    }

}
