package runningset;

import android.view.Display;

import com.trainer.courserunner.Application.enumtype.ModeType;

import java.io.Serializable;

public class RunningSetting implements Serializable {
    Integer drawableId;
    ModeType modeType;
    public RunningSetting(ModeType modeType){
        this.modeType=modeType;
    }


    public ModeType getModeType() {
        return modeType;
    }

    public Integer getDrawableId() {
        return drawableId;
    }
    public void setDrawableId(Integer drawableId) {
        this.drawableId = drawableId;
    }
}
