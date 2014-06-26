package thomascorfield.fr.mydeezer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class MusicActivity extends Activity {

    private TextView musicSongTitle;
    private TextView musicArtistTextView;
    private TextView musicAlbumTextView;

    private RadioButton musicFavYesRadioBtn;
    private RadioButton musicFavNoRadioBtn;

    private Button musicListenBtn;
    private Button musicGoToBtn;

    private Music music;

    public static final String MUSIC_SELECTED = "musicactivity.musicselected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        this.musicSongTitle = (TextView) findViewById(R.id.musicSongTitle);
        this.musicArtistTextView = (TextView) findViewById(R.id.musicArtistTextView);
        this.musicAlbumTextView = (TextView) findViewById(R.id.musicAlbumTextView);

        this.musicFavYesRadioBtn = (RadioButton) findViewById(R.id.musicFavYesRadioBtn);
        this.musicFavNoRadioBtn = (RadioButton) findViewById(R.id.musicFavNoRadioBtn);

        this.musicListenBtn = (Button) findViewById(R.id.musicListenBtn);
        this.musicGoToBtn = (Button) findViewById(R.id.musicGoToBtn);

        //this.music = Music.getDefaultMusic();

        try {

            Intent envelop = getIntent();
            this.music = (Music) envelop.getSerializableExtra(MUSIC_SELECTED);

        } catch (NullPointerException e) {

            this.music = Music.getDefaultMusic();
        }

        this.musicSongTitle.setText(this.music.getTitle());

        this.musicArtistTextView.setText(this.music.getArtist());
        this.musicAlbumTextView.setText( this.music.getAlbum());

        this.musicFavYesRadioBtn.setChecked(this.music.isFavorite());
        this.musicFavNoRadioBtn.setChecked(!this.music.isFavorite());

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
    }

    private void play () {


    }

    private void launchWebBrowser () {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.music.getLink()));
        startActivity(intent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("onStop", "Stopping");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", "Pausing");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "Resuming");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("onRestart", "Restarting");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart", "Starting");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
