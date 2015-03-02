package com.zorz.mario.model.favorites;

import android.content.Context;

import com.google.gson.Gson;
import com.utils.Utils;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.model.ProjectItem;
import com.zorz.mario.model.ProjectsResponse;

import java.util.ArrayList;
import java.util.List;

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

    public static ProjectsResponse getFavorites(Context ctx, String type){
        ProjectsResponse favs = new ProjectsResponse();

        String profJson = Utils.getSharedPreferences(ctx).getString(ConstantsMzorz.MZORZ_FAVORITE_LIST,"");
        Gson gson = new Gson();
        favs = gson.fromJson(profJson, ProjectsResponse.class);

        //now filter
        favs.projects = filterOnlyThisProjectType(type, favs.projects);

        return favs;
    }

    private static List<ProjectItem> filterOnlyThisProjectType(String type, List<ProjectItem> origList){
        List<ProjectItem> projectsResult = new ArrayList<ProjectItem>();

        if (type != null && origList != null){
            for (int i = 0; i < origList.size(); i++){
                if (origList.get(i).type != null)
                    if (origList.get(i).type.compareToIgnoreCase(type) == 0){
                        projectsResult.add(origList.get(i));
                    }
            }
        }

        return projectsResult;

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
            //run through server response and mark each favorite if present in our local data.
            // Not optimal, but I know I'm dealing with just a bunch a projects only ;)
            if (localFavs != null && localFavs.projects != null) {
                for (int i=0; i < serverList.projects.size(); i++){
                    for (int j=0; j < localFavs.projects.size(); j++){
                        ProjectItem serverItem = serverList.projects.get(i);
                        if (serverItem.id == localFavs.projects.get(j).id){
                            serverItem.favorite = true;
                            break;
                        } else {
                            serverItem.favorite = false;
                        }
                    }
                }
            }
        }
    }

}
