package com.bossfight.rentalservices.startup;

import android.app.Application;

import com.bossfight.rentalservices.cache.ImagePipelineConfigFactory;
import com.facebook.drawee.backends.pipeline.Fresco;

public class FrescoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this, ImagePipelineConfigFactory.getImagePipelineConfig(this));
    }

}
