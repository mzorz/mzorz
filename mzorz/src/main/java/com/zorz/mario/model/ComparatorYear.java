package com.zorz.mario.model;

import java.util.Comparator;

/**
 * Created by mariozorz on 3/2/15.
 */
public class ComparatorYear implements Comparator<ProjectItem> {

    @Override
    public int compare(ProjectItem o1, ProjectItem o2){

        if (o1 != null && o2 != null && o1.date != null && o2.date != null)
            return o2.date.compareTo(o1.date);
            //descending

        return -1;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof ComparatorYear)
            return true;
        return false;
    }
}
