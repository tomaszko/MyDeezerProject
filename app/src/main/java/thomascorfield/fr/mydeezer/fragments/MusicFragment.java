package thomascorfield.fr.mydeezer.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.IOException;
import java.net.URI;

import thomascorfield.fr.mydeezer.MainActivity;
import thomascorfield.fr.mydeezer.model.DatabaseManager;
import thomascorfield.fr.mydeezer.model.Music;
import thomascorfield.fr.mydeezer.R;


public class MusicFragment extends Fragment {

    private TextView musicSongTitle;
    private TextView musicArtistTextView;
    private TextView musicAlbumTextView;

    private RadioButton musicFavYesRadioBtn;
    private RadioButton musicFavNoRadioBtn;

    private Button musicListenBtn;
    private Button musicGoToBtn;

    private Music music;

    private DatabaseManager dbmgr;

    private MediaPlayer player = new MediaPlayer();

    public static final String MUSIC_SELECTED = "musicactivity.musicselected";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_music, null);

        this.dbmgr = ((MainActivity) getActivity()).getDatabaseManager();

        this.musicSongTitle = (TextView) v.findViewById(R.id.musicSongTitle);
        this.musicArtistTextView = (TextView) v.findViewById(R.id.musicArtistTextView);
        this.musicAlbumTextView = (TextView) v.findViewById(R.id.musicAlbumTextView);

        this.musicFavYesRadioBtn = (RadioButton) v.findViewById(R.id.musicFavYesRadioBtn);
        this.musicFavNoRadioBtn = (RadioButton) v.findViewById(R.id.musicFavNoRadioBtn);

        this.musicListenBtn = (Button) v.findViewById(R.id.musicListenBtn);
        this.musicGoToBtn = (Button) v.findViewById(R.id.musicGoToBtn);

        if (this.music == null) {

            this.music = Music.getDefaultMusic();

        }

        refresh();

        this.musicListenBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                play();
            }
        });

        this.musicGoToBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                launchWebBrowser();
            }
        });

        this.musicFavNoRadioBtn.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View view) {
                dbmgr.remove(music);
            }
        });

        this.musicFavYesRadioBtn.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View view) {
                dbmgr.add(music);
            }
        });

        return v;
    }

    private void play () {

        try {
            player.reset();
            player.setDataSource(getActivity(), Uri.parse(this.music.getSampleURL()));
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void launchWebBrowser () {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.music.getLink()));
        startActivity(intent);

    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void refresh() {

        if (this.musicSongTitle != null) {

            this.musicSongTitle.setText(this.music.getTitle());
            this.musicArtistTextView.setText(this.music.getArtist());
            this.musicAlbumTextView.setText(this.music.getAlbum());
            this.musicFavYesRadioBtn.setChecked(this.music.isFavorite());
            this.musicFavNoRadioBtn.setChecked(!this.music.isFavorite());
        }
    }
}
