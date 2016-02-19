
package com.androidyug.mindbody.model.venuelist;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class SuggestedBounds  implements Serializable {

    private Ne ne;
    private Sw sw;

    /**
     * 
     * @return
     *     The ne
     */
    public Ne getNe() {
        return ne;
    }

    /**
     * 
     * @param ne
     *     The ne
     */
    public void setNe(Ne ne) {
        this.ne = ne;
    }

    /**
     * 
     * @return
     *     The sw
     */
    public Sw getSw() {
        return sw;
    }

    /**
     * 
     * @param sw
     *     The sw
     */
    public void setSw(Sw sw) {
        this.sw = sw;
    }

}
