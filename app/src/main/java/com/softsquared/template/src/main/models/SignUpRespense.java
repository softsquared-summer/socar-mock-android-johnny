package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SignUpRespense {
    @SerializedName("isSuccess") public boolean isSuccess;
    @SerializedName("code") public int code;
    @SerializedName("message") public String message;
    @SerializedName("nation") public String nation;
    @SerializedName("name") public String name;
    @SerializedName("residentNo") public Date residentNo;
    @SerializedName("gender") public int gender;
    @SerializedName("phoneAgency") public String phoneAgency;
    @SerializedName("phoneNo") public String phoneNo;
    @SerializedName("id") public String id;
    @SerializedName("pw") public String pw;
    @SerializedName("inviteCode") public String inviteCode;
    @SerializedName("cardNo") public String cardNo;
    @SerializedName("cardDate") public Date cardDate;
    @SerializedName("ToSAggrementOne") public String ToSAggrementOne;
    @SerializedName("ToSAggrementTwo") public String ToSAggrementTwo;
    @SerializedName("ToSAggrementThree") public String ToSAggrementThree;
    @SerializedName("ToSAggrementFour") public String ToSAggrementFour;
    @SerializedName("licenseType") public String licenseType;
    @SerializedName("licenseRegion") public String licenseRegion;
    @SerializedName("licenseNo") public String licenseNo;
    @SerializedName("licenseExpiryDate") public Date licenseExpiryDate;
    @SerializedName("licenseDate") public Date licenseDate;
    @SerializedName("licenseAggrement") public String licenseAggrement;

    public boolean getIsSuccess(){return isSuccess;}

    public int getCode(){return code;}

    public String getMessage(){return message;}

    public SignUpRespense(SignUpInfo signUpInfo){
        this.nation = signUpInfo.nation;
        this.name = signUpInfo.name;
        this.residentNo = signUpInfo.residentNo;
        this.gender = signUpInfo.gender;
        this.phoneAgency = signUpInfo.phoneAgency;
        this.phoneNo = signUpInfo.phoneNo;
        this.id = signUpInfo.id;
        this.pw = signUpInfo.pw;
        this.inviteCode = signUpInfo.inviteCode;
        this.cardNo = signUpInfo.cardNo;
        this.cardDate = signUpInfo.cardDate;
        this.ToSAggrementOne = signUpInfo.ToSAggrementOne;
        this.ToSAggrementTwo = signUpInfo.ToSAggrementTwo;
        this.ToSAggrementThree = signUpInfo.ToSAggrementThree;
        this.ToSAggrementFour = signUpInfo.ToSAggrementFour;
        this.licenseType = signUpInfo.licenseType;
        this.licenseRegion = signUpInfo.licenseRegion;
        this.licenseNo = signUpInfo.licenseNo;
        this.licenseExpiryDate = signUpInfo.licenseExpiryDate;
        this.licenseDate = signUpInfo.licenseDate;
        this.licenseAggrement = signUpInfo.licenseAggrement;
    }

}
