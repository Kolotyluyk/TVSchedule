package com.sk.tvschedule.event;

import com.squareup.otto.Bus;

/**
 * Created by Сергій on 03.03.2017.
 */

public class BusStationChangeCursor {
    private static Bus bus=new Bus();


    public static Bus getBus() {
        return bus;
    }
}
