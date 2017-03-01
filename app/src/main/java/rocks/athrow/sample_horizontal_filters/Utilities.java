package rocks.athrow.sample_horizontal_filters;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by joselopez1 on 3/1/2017.
 */

public class LoadItemsFromJSON {
    public static void loadItemsFromJSON(Context context) {;
        Resources res = context.getResources();
        StringBuilder builder = new StringBuilder();
        InputStream in = res.openRawResource(R.raw.locations);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String rawJson = builder.toString();
        try {
            JSONArray list = new JSONArray(rawJson);
            int size = list.length();
            if (size == 0) {
                return;
            }
            final String ID = "id";
            final String TYPE = "type";
            final String SUB_TYPE = "subType";
            final String ITEM = "item";
            RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
            Realm.setDefaultConfiguration(realmConfig);
            Realm realm = Realm.getDefaultInstance();
            for (int i = 0; i < size; i++) {
                JSONObject item = list.getJSONObject(i);
                String id = item.getString(ID);
                String type = item.getString(TYPE);
                String subType = item.getString(SUB_TYPE);
                String name = item.getString(ITEM);
                Item realmItem = new Item();
                realm.beginTransaction();
                realmItem.setId(id);
                realmItem.setType(type);
                realmItem.setSubType(subType);
                realmItem.setItem(name);
                realm.copyToRealmOrUpdate(realmItem);
                realm.commitTransaction();
            }
            realm.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
