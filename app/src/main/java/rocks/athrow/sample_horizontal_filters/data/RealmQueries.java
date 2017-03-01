package rocks.athrow.sample_horizontal_filters.data;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by joselopez1 on 3/1/2017.
 */

public final class RealmQueries {

    public static String[] loadSubTypes(Context context){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Item> items = realm.where(Item.class).findAll().distinct("subType").sort("subType");
        String[] subTypes;
        int size = items.size();
        if ( size > 0 ){
            subTypes = new String[items.size()];
            for ( int i = 0 ; i < size ; i ++ ){
                subTypes[i] = items.get(i).getSubType();
            }
        }else{
            subTypes = null;
        }
        return subTypes;
    }
}
