/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.android.tflitecamerademo;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;

/**
 * This classifier works with the Inception-v3 slim model.
 * It applies floating point inference rather than using a quantized model.
 */
public class ImageClassifierFloatBodypose extends ImageClassifier {

    /**
     * The inception net requires additional normalization of the used input.
     */
    //private static final int IMAGE_MEAN = 128;
    //private static final float IMAGE_STD = 128.0f;

    /**
     * An array to hold inference results, to be feed into Tensorflow Lite as outputs.
     * This isn't part of the super class, because we need a primitive array here.
     */
    //input shape float32[1,353,257,3]
    //outpu shape float32[1,23,17,17]
    private float[][][][] jointProbArray = null;

    /**
     * Initializes an {@code ImageClassifier}.
     *
     * @param activity
     */
    ImageClassifierFloatBodypose(Activity activity) throws IOException {
        super(activity);
        jointProbArray = new float[1][getHeatmapWidth()][getHeatmapHeight()][getNumJoint()];
    }
    @Override
    protected String getModelPath() {
        // you can download this file from
        // https://storage.googleapis.com/download.tensorflow.org/models/tflite/inception_v3_slim_2016_android_2017_11_10.zip
        //return "multi_person_mobilenet_v1_075_float.tflite";
        //return "model_cpm.tflite";
        return "model_h.tflite";
    }

  /*
  @Override
  protected String getLabelPath() {
    return "labels_imagenet_slim.txt";
  }
  */

    @Override
    protected int getImageSizeX() {
        return 192;
        //return 353;

    }


    @Override
    protected int getImageSizeY() {
        return 192;
        //return 257;
    }

    @Override
    protected int getNumBytesPerChannel() {
        // a 32bit float value requires 4 bytes
        return 4;
    }

    @Override
    protected void addPixelValue(int pixelValue) {
    /*
    imgData.putFloat((pixelValue & 0xFF) / 255.f);
    imgData.putFloat(((pixelValue >> 8) & 0xFF) / 255.f);
    imgData.putFloat(((pixelValue >> 16) & 0xFF) / 255.f);
    */
        imgData.putFloat(Float.valueOf(pixelValue & 0xFF));
        imgData.putFloat(Float.valueOf(pixelValue >> 8 & 0xFF));
        imgData.putFloat(Float.valueOf(pixelValue >> 16 & 0xFF));

    }

    @Override
    protected float getProbability(int index, int width, int height, int joint) {
        return jointProbArray[index][width][height][joint];
    }
    /*
  @Override
  protected void setProbability(int labelIndex, Number value) {
    labelProbArray[0][labelIndex] = value.floatValue();
  }
  */

    /*
    @Override
    protected float getNormalizedProbability(int labelIndex) {
      // TODO the following value isn't in [0,1] yet, but may be greater. Why?
      return getProbability(labelIndex);
    }
    */
    @Override
    protected void runInference() {
        tflite.run(imgData, jointProbArray);
    /*
    for (int i=0; i<96; i++){
      for (int j=0; j<96; j++) {
        for(int k=0; k<14; k++) {
          Log.d("yangace", "value[" + i + "][" + j + "][" + k + "] = " + jointProbArray[0][i][j][1]);
        }
      }
    }
    */

    }
}
