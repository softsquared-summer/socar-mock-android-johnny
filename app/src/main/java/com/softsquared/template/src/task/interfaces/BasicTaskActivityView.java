package com.softsquared.template.src.task.interfaces;

        import com.softsquared.template.src.task.models.SocarzoneInfo;

        import java.util.ArrayList;

public interface BasicTaskActivityView {
    void validateSuccess(ArrayList<SocarzoneInfo> socarzoneInfo);
    void validateFailure(String fail);
}
