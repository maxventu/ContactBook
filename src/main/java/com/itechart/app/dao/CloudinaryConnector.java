package com.itechart.app.dao;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * Created by Maxim on 12/12/2015.
 */
public class CloudinaryConnector {
    private static Cloudinary CLOUDINARY;
    private CloudinaryConnector()
    {
        CLOUDINARY=new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "goodcloud",
                "api_key", "836668373272998",
                "api_secret", "HJ2Q7oe53Ru7muxKcpVj4ZdqVPQ"));
    }

    public static Cloudinary getCloudinary(){return CLOUDINARY;}
}
