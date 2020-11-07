package com.jules.creator;

import java.beans.Statement;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class XmlCreator {

    private final File dir;

    public XmlCreator(File file){
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
                    createPath="tree.xml";
                    break;
                case FILE_STAST:
                    createPath="file_stats.xml";
                    break;
                case FOLDER_STATS:
                    createPath="folder_stats.xml";
                    break;
            }
            try{
                if(!(new File(p.resolve(createPath).toString()).exists())){
                    new File(p.resolve(createPath).toString()).createNewFile();
                }
            }catch (IOException ignored){}
        }
    }

    public void init(){
        List<TypeFile> typefile = new ArrayList<>(EnumSet.allOf(TypeFile.class));
        for(TypeFile op : typefile){
            this.create(op);
        }
    }

    public void create(TypeFile type) {
        Path p = this.dir.toPath();
        String Folder = "./FileStats "+getDate()+"/xml";
        if(new File(p.resolve(Folder).toString()).exists()){
            this.filecreate(new File(p.resolve(Folder).toString()),type);
        }else{
            if(new File(p.resolve(Folder).toString()).mkdir()){
                this.filecreate(new File(p.resolve(Folder).toString()),type);
            }
        }
    }


    public void writelines(TypeFile type,List<String[]> lines){
        Path p = this.dir.toPath();
        String Folder = "./FileStats "+getDate()+"/xml";
        if(!new File(p.resolve(Folder).toString()).exists()){
            this.create(type);
        }
        String createPath="";
        switch (type){
            case FILE_STAST:
                createPath="file_stats.xml";
                break;
            case FOLDER_STATS:
                createPath="folder_stats.xml";
                break;
        }
        File file = new File(p.resolve(Folder).resolve(createPath).toString());

        try {
            FileOutputStream os = new FileOutputStream(file.getPath(),true);
            XMLEncoder encoder = new XMLEncoder(os);
            for (String[] el : lines) {
                for(String line : el){
                    if(line != null) {
                        encoder.writeObject(line);
                    }else{
                        encoder.writeObject("");
                    }
                }
            }

            encoder.close();
        }catch (FileNotFoundException ignored){
        }

    }


}
