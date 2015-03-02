package com.zorz.mario.model;

import java.util.Comparator;
import java.util.Objects;

/**
 * Created by mariozorz on 3/2/15.
 */
public class ComparatorDL implements Comparator<ProjectItem> {

    @Override
    public int compare(ProjectItem o1, ProjectItem o2){

        if (o1.dl_priority == o2.dl_priority)
            return 0;
        if (o1.dl_priority < o2.dl_priority)
            return -1;
//        if (o1.dl_priority > o2.dl_priority)
        return 1;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof ComparatorDL)
            return true;
        return false;
    }
}
