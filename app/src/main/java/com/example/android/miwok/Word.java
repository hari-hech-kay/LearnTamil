package com.example.android.miwok;


import android.media.Image;

public class Word {

    private String defaultTranslation;
    private String tamilTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTrans, String tamilTrans, int imgResId, int audioResId){
        defaultTranslation= defaultTrans;
        tamilTranslation= tamilTrans;
        imageResourceId= imgResId;
        audioResourceId= audioResId;
    }

    public Word(String defaultTrans, String tamilTrans, int audioResId ){
        defaultTranslation= defaultTrans;
        tamilTranslation= tamilTrans;
        audioResourceId= audioResId;
    }

    public String getDefaultTranslation(){
        return defaultTranslation;
    }
    public String getTamilTranslation(){
        return tamilTranslation;
    }
    public int getImageResourceId() { return imageResourceId; }
    public boolean hasImage(){ return imageResourceId != NO_IMAGE_PROVIDED;}
    public  int getAudioResourceId(){return audioResourceId;}

}

