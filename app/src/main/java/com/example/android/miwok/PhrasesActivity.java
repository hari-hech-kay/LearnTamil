package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        words.add(new Word("Where are you going?", "Ni yengu selgiraai?", R.raw.where_r_u_going ));
        words.add(new Word("What is your name?", "Un peyar enna?", R.raw.what_is_ur_name ));
        words.add(new Word("My name is...", "Yen peyar...", R.raw.en_peyar ));
        words.add(new Word("How are you feeling?", "Ni yevvaru unargiraai?", R.raw.how_do_u_feel ));
        words.add(new Word("I'm feeling good", "Naan nandraaga/sandhosamaaga unargiren", R.raw.i_m_feeling_good));
        words.add(new Word("Are you coming?", "Ni varugiraaya?", R.raw.are_u_coming ));
        words.add(new Word("Yes! I'm coming", "Aam! Naan varuven/varugiren", R.raw.yes_im_coming ));
        words.add(new Word("I'm coming", "Naan varuven/varugiren", R.raw.i_m_coming ));
        words.add(new Word("Let's go", "Naam selvom", R.raw.let_s_go ));
        words.add(new Word("Come here!", "Ingey va!", R.raw.come_here ));

        WordAdapter familyAdapter = new WordAdapter(this, words, R.color.category_phrases);
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
