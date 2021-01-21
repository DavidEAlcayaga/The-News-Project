
/*
 * Copyright 2020 David Canto-Alcayaga davidcanto01@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.dcanto.news.activities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import cl.ucn.disc.dsm.dcanto.news.R;
import cl.ucn.disc.dsm.dcanto.news.model.News;
import cl.ucn.disc.dsm.dcanto.news.adapters.NewsItem;
import cl.ucn.disc.dsm.dcanto.news.services.AppDatabase;
import cl.ucn.disc.dsm.dcanto.news.services.Contracts;
import cl.ucn.disc.dsm.dcanto.news.services.ContractsImplNewsApi;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ModelAdapter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Main Class.
 *
 * @author David Canto-Alcayaga.
 */

public class MainActivity extends AppCompatActivity {

  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

  /**
   * The List View
   */
  protected ListView listView;

  /**
   * OnCreate.
   *
   * @param savedInstanceState used to reload the app.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);

    log.debug("onCreate ..");
    setContentView(R.layout.activity_main);

    // The Toolbar
    this.setSupportActionBar(findViewById(R.id.am_t_toolbar));

    // The FastAdapter
    ModelAdapter<News, NewsItem> newsAdapter = new ModelAdapter<>(NewsItem::new);
    FastAdapter<NewsItem> fastAdapter = FastAdapter.with(newsAdapter);
    fastAdapter.withSelectable(false);

    // The Recycler view
    RecyclerView recyclerView = findViewById(R.id.am_rv_news);
    recyclerView.setAdapter(fastAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    // Local DB instance
    AppDatabase dataBase = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "localDb").build();

    // Checks conectivity
    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

    if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

      // Clears the Db
      AppDatabase.getInstance(getApplicationContext()).newsDao().nukeTable();
      // Get the news in the background thread
      AsyncTask.execute(() -> {

        // Using the contracts to get the news..
        Contracts contracts = new ContractsImplNewsApi("cb92acd6ea2d4b1e968da42e6b262c6a");

        // Get the news from NewsAPI (Internet!)
        List<News> listNews = contracts.retrieveNews(30);

        // Build the simple adapter to show the list of news (String!)
        ArrayAdapter<String> adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listNews
        );

        // Store data in local DB
        for (int i = 0; i < listNews.size()-1; i++) {

          if (listNews.get(i) != null) {

            dataBase.newsDao().insertNews(listNews.get(i));
          }
        }

        // Set the adapter!
        runOnUiThread(()->{

          newsAdapter.add(listNews);

        });

      });

      // Pull to refresh
      SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.am_swl_refresh);
      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

          // Clears the Db
          AppDatabase.getInstance(getApplicationContext()).newsDao().nukeTable();
          // Clear screen
          newsAdapter.clear();

          AsyncTask.execute(() -> {
            // Using the contracts to get the news..
            Contracts contracts = new ContractsImplNewsApi("cb92acd6ea2d4b1e968da42e6b262c6a");

            // Get the news from NewsAPI (Internet!)
            List<News> listNews = contracts.retrieveNews(30);



            // Replace the data
            for (int i = 0; i < listNews.size()-1; i++) {

              if (listNews.get(i) != null) {

                dataBase.newsDao().insertNews(listNews.get(i));
              }
            }

            // Update the listView
            runOnUiThread(()->{

              newsAdapter.add(listNews);
            });
          });

          fastAdapter.notifyAdapterDataSetChanged();
          swipeRefreshLayout.setRefreshing(false);
        }
      });

      Toast.makeText(getApplicationContext(), "CONECTADO", Toast.LENGTH_LONG).show();
    }else{

      // Get's the data from Db when the app is without internet
      Thread thread = new Thread(() -> newsAdapter.add(dataBase.newsDao().getAll()));
      thread.start();

      Toast.makeText(getApplicationContext(), "SIN CONEXION", Toast.LENGTH_LONG).show();

      // Pull to refresh
      SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.am_swl_refresh);
      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

          // Displays "Sin Conexion" message
          Toast.makeText(getApplicationContext(), "SIN CONEXION", Toast.LENGTH_LONG).show();

          swipeRefreshLayout.setRefreshing(false);
        }
      });
    }
  }

  /**
   * Change the label between night mode and day mode
   *
   * @param menu
   * @return true
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.main_menu, menu);
    // Change the label of the menu based on the state of the app
    int nightMode = AppCompatDelegate.getDefaultNightMode();

    if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){

      menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);

    } else{

      menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);

    }

    return true;
  }

  /**
   * Set the theme between night and day mode
   *
   * @param item
   * @return true
   */
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    // Check if correct item was clicked
    if(item.getItemId()==R.id.night_mode){

      // Get the night mode state of the app
      int nightMode = AppCompatDelegate.getDefaultNightMode();
      // Set the theme mode for the restarted activity

      if(nightMode==AppCompatDelegate.MODE_NIGHT_YES){

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

      } else {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

      }

      // Recreate the activity for the theme change to take effect.
      recreate();

    }

    return true;
  }

}