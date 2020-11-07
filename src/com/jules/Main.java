package com.jules;

import com.jules.tree.Tree;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String __dirname = new File(".").getCanonicalPath();
        Tree t = new Tree(__dirname);
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                t.getWriteLoopStats();
            }
        });
    }

}