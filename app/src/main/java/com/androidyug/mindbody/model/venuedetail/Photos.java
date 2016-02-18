
package com.androidyug.mindbody.model.venuedetail;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;


public class Photos {

    private Integer count;
    private List<Group_> groups = new ArrayList<Group_>();

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The groups
     */
    public List<Group_> getGroups() {
        return groups;
    }

    /**
     * 
     * @param groups
     *     The groups
     */
    public void setGroups(List<Group_> groups) {
        this.groups = groups;
    }

}
