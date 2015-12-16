package com.itechart.app.controller.helpers;

import com.itechart.app.dao.LocationDAO;
import com.itechart.app.entity.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Maxim on 12/16/2015.
 */
public class LocationHelper {

    final Logger LOGGER = LoggerFactory.getLogger(LocationHelper.class);

    public static final LocationHelper INSTANCE = new LocationHelper();

    private LocationHelper(){}

    public void updateLocation(Location location){
        LOGGER.debug("updating location");
        Location previousLocation = LocationDAO.INSTANCE.findEntityById(location.getId());
        if(previousLocation != null)LocationDAO.INSTANCE.update(location);
        else LocationDAO.INSTANCE.create(location);
    }

}
