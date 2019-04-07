package com.androidlearning.bressondev.musicplayer;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class App_core extends Application {

    /**
     * Le champ static contenant le <i>Context</i> de l'application.
     * @see Context
     *
     * */
    private Context mContext;
    private ArrayList<HashMap<String, String>> songsList;

    /**
     * Paths towards the directories containing the songs, on the phone and a sd card
     */
    final String MEDIA_PATH_LOCAL = Environment.getExternalStorageDirectory().getAbsolutePath();
    final String MEDIA_PATH_SD_CARD = "/storage/sdcard1/";

    /**
     * L'accesseur du context de l'application
     *
     * @return Le context de l'application.
     *
     * */
    public Context getContext(){
        return this.mContext;
    }

    public static String id;

    public static void setId(String id){
        App_core.id=id;
    }

    public static String getId(){
        return App_core.id;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.songsList = this.findSongs(MEDIA_PATH_LOCAL);
        mContext = this;
    }

    private ArrayList<HashMap<String,String>> findSongs(String path) {
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();

        Log.i("App_core entering", "ok");
        try {
            File rootFolder = new File(path);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (findSongs(file.getAbsolutePath()) != null) {
                        fileList.addAll(findSongs(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3") || file.getName().endsWith(".MP3")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    Log.i("App_core song search", file.getName());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }
}
