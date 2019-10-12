package p185296_m203380.ft.unicamp.aula03_fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import alunos.AlunosFragment;
import p185296_m203380.ft.unicamp.aula03_fragmentos.database.DatabaseFragment;
import p185296_m203380.ft.unicamp.aula03_fragmentos.kotlin.EmptyActivity;
import puzzle.PuzzleFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentController {

    private FragmentManager fragmentManager;
    public static final String AUTHORS_KEY = "authors";
    public static final String MAIL_KEY = "mail";
    public static final String STUDENTS_KEY = "students";
    public static final String BIOGRAPHY_KEY = "biography";
    public static final String PUZZLE_KEY = "puzzle";
    public static final String GAME_KEY = "game";
    public static final String DATABASE_KEY = "database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (fragmentManager == null) {
            fragmentManager = this.getSupportFragmentManager();
        }
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_contact) {
            Fragment mailFragment = fragmentManager.findFragmentByTag(MainActivity.MAIL_KEY);
            if (mailFragment == null) {
                mailFragment = new MailFragment();
            }
            replaceFragment(mailFragment, MainActivity.MAIL_KEY);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (fragmentManager == null) {
            fragmentManager = this.getSupportFragmentManager();
        }

        if (id == R.id.nav_authors) {
            Fragment authorFragment = fragmentManager.findFragmentByTag(MainActivity.AUTHORS_KEY);
            if (authorFragment == null) {
                authorFragment = new AuthorsFragment();
            }
            replaceFragment(authorFragment, MainActivity.AUTHORS_KEY);
        } else if (id == R.id.nav_students) {
            Fragment studentFragment = fragmentManager.findFragmentByTag(MainActivity.STUDENTS_KEY);
            if (studentFragment == null) {
                studentFragment = new AlunosFragment();
            }
            replaceFragment(studentFragment, MainActivity.STUDENTS_KEY);
        } else if (id == R.id.nav_biography) {
            Fragment biographyFragment = fragmentManager.findFragmentByTag(MainActivity.BIOGRAPHY_KEY);
            if (biographyFragment == null) {
                biographyFragment = new BiographyFragment();
            }
            replaceFragment(biographyFragment, MainActivity.BIOGRAPHY_KEY);
        } else if (id == R.id.nav_game_one) {
            Fragment puzzleFragment = fragmentManager.findFragmentByTag(MainActivity.PUZZLE_KEY);
            if (puzzleFragment == null) {
                puzzleFragment = new PuzzleFragment();
            }
            replaceFragment(puzzleFragment, MainActivity.PUZZLE_KEY);
        } else if (id == R.id.nav_game_two) {
            Fragment nameFragment = fragmentManager.findFragmentByTag(MainActivity.GAME_KEY);
            if (nameFragment == null) {
                nameFragment = new NameFragment();
            }
            replaceFragment(nameFragment, MainActivity.GAME_KEY);
        } else if (id == R.id.nav_kotlin) {
            startActivity(new Intent(this, EmptyActivity.class));
        } else if (id == R.id.nav_database) {
            Fragment databaseFragment = fragmentManager.findFragmentByTag(MainActivity.DATABASE_KEY);
            if (databaseFragment == null) {
                databaseFragment = new DatabaseFragment();
            }
            replaceFragment(databaseFragment, MainActivity.DATABASE_KEY);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.authors_frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void hideMainActivityElements() {
        findViewById(R.id.welcome_text_view).setVisibility(View.GONE);
    }

    public void showMainActivityElements() {
        findViewById(R.id.welcome_text_view).setVisibility(View.VISIBLE);
    }

}
