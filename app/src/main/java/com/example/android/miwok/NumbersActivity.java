package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer myMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener= new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ){
                myMediaPlayer.pause();
                myMediaPlayer.seekTo(0);
            }
            else if  (i == AudioManager.AUDIOFOCUS_GAIN){
                myMediaPlayer.start();
            }
            else if (i == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }

        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("One", "Ondru", R.drawable.number_one, R.raw.one));
        words.add(new Word("Two", "Irandu", R.drawable.number_two, R.raw.two));
        words.add(new Word("Three", "Moondru", R.drawable.number_three, R.raw.three));
        words.add(new Word("Four", "Naangu", R.drawable.number_four, R.raw.four));
        words.add(new Word("Five", "Aindhu", R.drawable.number_five, R.raw.five));
        words.add(new Word("Six", "Aaru", R.drawable.number_six, R.raw.six));
        words.add(new Word("Seven", "Yeazhu", R.drawable.number_seven, R.raw.seven));
        words.add(new Word("Eight", "Yettu", R.drawable.number_eight, R.raw.eight));
        words.add(new Word("Nine", "Onbadhu", R.drawable.number_nine, R.raw.nine));
        words.add(new Word("Ten", "Pathu", R.drawable.number_ten, R.raw.ten));

        WordAdapter numbersAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(numbersAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word= words.get(i);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), word.getAudioResourceId());
                    myMediaPlayer.start();
                    myMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {

        if (myMediaPlayer != null) {
            myMediaPlayer.release();
            myMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
