package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer myMediaPlayer;
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

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Red", "Sivappu", R.drawable.color_red, R.raw.red));
        words.add(new Word("Green", "Pachai", R.drawable.color_green, R.raw.green));
        words.add(new Word("Yellow", "Manjal", R.drawable.color_mustard_yellow, R.raw.yellow));
        words.add(new Word("Grey", "Saambal niram", R.drawable.color_gray, R.raw.grey));
        words.add(new Word("Black", "Karuppu", R.drawable.color_black, R.raw.black));
        words.add(new Word("White", "Vellai", R.drawable.color_white, R.raw.white));
        words.add(new Word("Brown", "Pazhuppu niram", R.drawable.color_brown, R.raw.brown));
        words.add(new Word("Dusty yellow", "Pazhuppu manjal", R.drawable.color_dusty_yellow, R.raw.dusty_yellow));

        WordAdapter colorsAdapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(colorsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word= words.get(i);
                releaseMediaPlayer();
                myMediaPlayer= MediaPlayer.create(getApplicationContext(), word.getAudioResourceId() );
                myMediaPlayer.start();
                myMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (myMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            myMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            myMediaPlayer = null;
        }
    }
}
