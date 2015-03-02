package com.zorz.mario.model;

import java.util.Comparator;

/**
 * Created by mariozorz on 3/2/15.
 */
public class ComparatorId implements Comparator<ProjectItem> {

    @Override
    public int compare(ProjectItem o1, ProjectItem o2){

        if (o1.id == o2.id)
            return 0;
        if (o1.id < o2.id)
            return -1;
        return 1;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof ComparatorId)
            return true;
        return false;
    }
}
