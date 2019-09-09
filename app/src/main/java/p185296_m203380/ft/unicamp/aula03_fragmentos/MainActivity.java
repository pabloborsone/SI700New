package p185296_m203380.ft.unicamp.aula03_fragmentos;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    public static final String AUTHORS_KEY = "authors";
    public static final String MAIL_KEY = "mail";
    public static final String STUDENTS_KEY = "students";

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

    @SuppressWarnings("StatementWithEmptyBody")
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

        } else if (id == R.id.nav_game_one) {

        } else if (id == R.id.nav_game_two) {

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

}
