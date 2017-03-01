package rocks.athrow.sample_horizontal_filters.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rocks.athrow.sample_horizontal_filters.R;
import rocks.athrow.sample_horizontal_filters.Utilities;
import rocks.athrow.sample_horizontal_filters.adapter.SubTypesAdapter;
import rocks.athrow.sample_horizontal_filters.data.Item;
import rocks.athrow.sample_horizontal_filters.data.RealmQueries;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();
        int countItems = realm.where(Item.class).findAll().size();
        if ( countItems == 0 ){
            loadItems();
        }
    }

    private void updateUi() {
        setupSubTypesList();
    }

    private void loadItems() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                Utilities.loadItemsFromJSON(getApplicationContext());
                handler.post(new Runnable() {
                    public void run() {
                        updateUi();
                    }
                });
            }
        };
        new Thread(runnable).start();
    }



    private void setupSubTypesList() {
        String[] subTypes = RealmQueries.loadSubTypes(getApplicationContext());
        if ( subTypes == null ){
            return;
        }
        SubTypesAdapter adapter = new SubTypesAdapter(subTypes);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sub_type_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
