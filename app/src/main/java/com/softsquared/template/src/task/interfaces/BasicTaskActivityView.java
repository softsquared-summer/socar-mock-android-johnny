package com.softsquared.template.src.task.interfaces;

import com.softsquared.template.src.task.models.SocarInfo;
import com.softsquared.template.src.task.models.SocarzoneInfo;

import java.util.ArrayList;

public interface BasicTaskActivityView {
    void validateSocarzoneSuccess(ArrayList<SocarzoneInfo> socarzoneInfo);
    void validateSocarzoneFailure(String fail);

    void validateSocarListSuccess(ArrayList<SocarInfo> socarInfo);
    void validateSocarListFailure(String fail);

}
