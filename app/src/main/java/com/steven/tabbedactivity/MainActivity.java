package com.steven.tabbedactivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.steven.tabbedactivity.ui.main.SectionsPagerAdapter;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
import android.app.ActivityOptions;
import android.transition.Slide;
public class MainActivity extends AppCompatActivity {
    public MediaPlayer mediaPlayer;
    public TabLayout tabs;
    public int tabNumber;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabNumber = tabs.getSelectedTabPosition() + 1;
                switch(tabNumber)
                {
                    case 1:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.main_theme);
                        break;
                    case 2:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.shulk_sound);
                        break;
                    case 3:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.reyn_sound);
                        break;
                    case 4:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.dunban_sound);
                        break;
                    case 5:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.sharla_sound);
                        break;
                    case 6:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.fiora_sound);
                        break;
                    case 7:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.melia_sound);
                        break;
                    case 8:
                        mediaPlayer = MediaPlayer.create(MainActivity.this,
                                R.raw.riki_sound);
                        break;
                    default:
                        System.out.println(R.string.err_msg_sound);
                }

                mediaPlayer.start();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        Toast.makeText(MainActivity.this, item.getTitle() + " Menu is Selected",
                Toast.LENGTH_SHORT).show();
        switch(id) {
            case R.id.menu_survey:
                intent = new Intent(MainActivity.this, SurveyActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_webpage:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.nintendo.com/games/detail/xenoblade-chronicles-definitive-edition-switch/"));
                                startActivity(browserIntent);
                return true;

            case R.id.menu_video:
                intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void setupWindowAnimations(){
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }
}