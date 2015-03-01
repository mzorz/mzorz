package com.zorz.mario.model.favorites;

import android.content.Context;

import com.google.gson.Gson;
import com.utils.Utils;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.model.ProjectItem;
import com.zorz.mario.model.ProjectsResponse;

import java.util.ArrayList;

/**
 * Created by mariozorz on 3/1/15.
 */
public class FavoriteHandler {

    public static ProjectsResponse getFavorites(Context ctx){
        ProjectsResponse favs = new ProjectsResponse();

        String profJson = Utils.getSharedPreferences(ctx).getString(ConstantsMzorz.MZORZ_FAVORITE_LIST,"");
        Gson gson = new Gson();
        favs = gson.fromJson(profJson, ProjectsResponse.class);

        return favs;
    }

    private static void saveFavorites(Context ctx, ProjectsResponse updatedProjectList){
        Gson gson = new Gson();
        String json = gson.toJson(updatedProjectList);
        Utils.getPreferencesEditor(ctx).putString(ConstantsMzorz.MZORZ_FAVORITE_LIST,json).commit();
    }

    public static ProjectsResponse addFavorite(Context ctx, ProjectItem newitem){
        ProjectsResponse favs = getFavorites(ctx);
        if (favs == null)
            favs = new ProjectsResponse();
        favs.projects.add(newitem);
        saveFavorites(ctx, favs);
        return favs;
    }

    public static ProjectsResponse removeFavorite(Context ctx, ProjectItem itemToRemove){
        ProjectsResponse favs = getFavorites(ctx);
        if (favs != null){
            for (int i=0; i < favs.projects.size(); i++){
                if (favs.projects.get(i).id == itemToRemove.id){
                    favs.projects.remove(i);
                    break;
                }
            }
            saveFavorites(ctx, favs);
        }
        return favs;
    }

    public static void updateServerListWithLocalFavlistInfo(Context ctx, ProjectsResponse serverList){

        if (serverList != null && serverList.projects != null){
            ProjectsResponse localFavs = getFavorites(ctx);
            //run through server response and mark each favorite if present in our local.
            // Not optimal, but I know I'm dealing with just a bunch a projects only ;)
            if (localFavs != null && localFavs.projects != null) {
                for (int i=0; i < serverList.projects.size(); i++){
                    for (int j=0; j < localFavs.projects.size(); j++){
                        if (serverList.projects.get(i).id == localFavs.projects.get(j).id){
                            serverList.projects.get(i).favorite = true;
                            break;
                        }
                    }
                }
            }
        }
    }

}
