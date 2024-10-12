package stor.ensa.ma.stor;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import stor.ensa.ma.stor.adapter.StarAdapter;
import stor.ensa.ma.stor.service.ShowService;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp4.R;

import java.util.ArrayList;
import java.util.List;

import stor.ensa.ma.stor.beans.Show;

public class ListActivity extends AppCompatActivity {
    private List<Show> shows;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;

    ShowService service = ShowService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shows = new ArrayList<>();

        service = ShowService.getInstance();
        init();

        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init(){
        service.create(new Show("You", "https://tse3.mm.bing.net/th?id=OIP.XJF3pLSHes0gfLE_BOUJvAHaEH&pid=Api&P=0&h=220", 4));
        service.create(new Show("the vampire diaries", "https://naeye.net/wp-content/uploads/2021/04/vampire-diaries-image.jpg", 4));
        service.create(new Show("Riverdale", "https://tse1.mm.bing.net/th?id=OIP.Jv_2oYkuEl9mjkg4Iwm6CgHaLH&pid=Api&P=0&h=220", 5));
        service.create(new Show("Dark", "https://tse4.mm.bing.net/th?id=OIP.ZAzL3Og6vDmQG5vpc6mkrAHaEK&pid=Api&P=0&h=220", 4.5f));
        service.create(new Show("Gossip girl", "https://tse2.mm.bing.net/th?id=OIP.t3lyLEaMolcoEY_lBtaqrQHaDt&pid=Api&P=0&h=220", 5));
        service.create(new Show("the maze runner", "https://tse2.mm.bing.net/th?id=OIP.yXaPv5DzYpUm_yw-qdd2LAHaLH&pid=Api&P=0&h=220", 4));
        service.create(new Show("the dynasty", "https://tse3.mm.bing.net/th?id=OIP.fE0S9HA5-F2xzkhLlf4JAwHaDt&pid=Api&P=0&h=220", 4));
        service.create(new Show("interstellar", "https://1.bp.blogspot.com/-l7aTAUwMI58/X6O7G6yslfI/AAAAAAAAAHM/BRXfZCuEU6caMvMhFZznB9VhFOwLQKGUQCLcBGAsYHQ/s1697/111c5c9ad99661af2d80e38948cf29d8.jpg", 4));
        service.create(new Show("outer banks", "https://tse2.mm.bing.net/th?id=OIF.In7%2fLfYcCV28EiYy0JZDhA&pid=Api&P=0&h=220", 4));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}
