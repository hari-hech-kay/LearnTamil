package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        words.add(new Word("Mother", "Amma", R.drawable.family_mother, R.raw.mother));
        words.add(new Word("Father", "Appa", R.drawable.family_father, R.raw.father ));
        words.add(new Word("Grandmother", "Paati", R.drawable.family_grandmother, R.raw.grandma ));
        words.add(new Word("Grandfather", "Thaatha", R.drawable.family_grandfather, R.raw.grandpa ));
        words.add(new Word("Elder Brother", "Anna", R.drawable.family_older_brother, R.raw.elder_bro ));
        words.add(new Word("Elder Sister", "Akka", R.drawable.family_older_sister, R.raw.elder_sis ));
        words.add(new Word("Younger Brother", "Thambi", R.drawable.family_younger_brother, R.raw.younger_bro ));
        words.add(new Word("Younger Sister", "Thangai", R.drawable.family_younger_sister, R.raw.younger_sis ));
        words.add(new Word("Son", "Magan", R.drawable.family_son, R.raw.son ));
        words.add(new Word("Daughter", "Magal", R.drawable.family_daughter, R.raw.daughter ));

        WordAdapter familyAdapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(familyAdapter);

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
