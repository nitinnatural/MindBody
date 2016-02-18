
package com.androidyug.mindbody.model.venuedetail;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Hours {

    private String status;
    private Boolean isOpen;
    private Boolean isLocalHoliday;
    private List<Timeframe> timeframes = new ArrayList<Timeframe>();

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The isOpen
     */
    public Boolean getIsOpen() {
        return isOpen;
    }

    /**
     * 
     * @param isOpen
     *     The isOpen
     */
    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 
     * @return
     *     The isLocalHoliday
     */
    public Boolean getIsLocalHoliday() {
        return isLocalHoliday;
    }

    /**
     * 
     * @param isLocalHoliday
     *     The isLocalHoliday
     */
    public void setIsLocalHoliday(Boolean isLocalHoliday) {
        this.isLocalHoliday = isLocalHoliday;
    }

    /**
     * 
     * @return
     *     The timeframes
     */
    public List<Timeframe> getTimeframes() {
        return timeframes;
    }

    /**
     * 
     * @param timeframes
     *     The timeframes
     */
    public void setTimeframes(List<Timeframe> timeframes) {
        this.timeframes = timeframes;
    }

}
