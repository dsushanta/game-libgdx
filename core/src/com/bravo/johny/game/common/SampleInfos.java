package com.bravo.johny.game.common;

import com.bravo.johny.game.ApplicationListenerSampler;
import com.bravo.johny.game.GdxGeneratedSample;
import com.bravo.johny.game.GdxModuleInfoSample;
import com.bravo.johny.game.GdxReflectionSample;
import com.bravo.johny.game.InputListeningSample;
import com.bravo.johny.game.InputPollingSample;
import com.bravo.johny.game.OrthographicCameraSample;
import com.bravo.johny.game.ShapeRendererSample;
import com.bravo.johny.game.SpriteBatchSample;
import com.bravo.johny.game.ViewPortSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    public static List<SampleInfo> ALL = Arrays.asList(
            ApplicationListenerSampler.SAMPLE_INFO,
            GdxGeneratedSample.SAMPLE_INFO,
            GdxModuleInfoSample.SAMPLE_INFO,
            GdxReflectionSample.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO,
            ViewPortSample.SAMPLE_INFO,
            SpriteBatchSample.SAMPLE_INFO,
            ShapeRendererSample.SAMPLE_INFO
    );

    public static List<String> getSampleNames() {
        List<String> ret = new ArrayList<String>();
        for(SampleInfo info : ALL) {
            ret.add(info.getName());
        }
        Collections.sort(ret);
        return ret;
    }

    public static SampleInfo find(String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name argument is required !!");
        SampleInfo ret = null;
        for(SampleInfo info : ALL) {
            if(info.getName().equalsIgnoreCase(name)) {
                ret = info;
                break;
            }
        }
        if(ret == null)
            throw new IllegalArgumentException("No sample info found with the name provided !!");
        return ret;
    }

    private SampleInfos() {

    }


}
