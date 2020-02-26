package com.softsquared.template.src.main.models;

import java.util.Date;

public class SignUpInfo {
    String nation;
    String name;
    Date residentNo;
    int gender;
    String phoneAgency;
    String phoneNo;
    String id;
    String pw;
    String inviteCode;
    String cardNo;
    Date cardDate;
    String ToSAggrementOne;
    String ToSAggrementTwo;
    String ToSAggrementThree;
    String ToSAggrementFour;
    String licenseType;
    String licenseRegion;
    String licenseNo;
    Date licenseExpiryDate;
    Date licenseDate;
    String licenseAggrement;

    public SignUpInfo(String nation, String name, Date residentNo, int gender,
                          String phoneAgency, String phoneNo, String id, String pw,
                          String inviteCode, String cardNo, Date cardDate, String ToSAggrementOne,
                          String ToSAggrementTwo, String ToSAggrementThree, String ToSAggrementFour,
                          String licenseType, String licenseRegion, String licenseNo, Date licenseExpiryDate,
                          Date licenseDate, String licenseAggrement){

        this.nation = nation;
        this.name = name;
        this.residentNo = residentNo;
        this.gender = gender;
        this.phoneAgency = phoneAgency;
        this.phoneNo = phoneNo;
        this.id = id;
        this.pw = pw;
        this.inviteCode = inviteCode;
        this.cardNo = cardNo;
        this.cardDate = cardDate;
        this.ToSAggrementOne = ToSAggrementOne;
        this.ToSAggrementTwo = ToSAggrementTwo;
        this.ToSAggrementThree = ToSAggrementThree;
        this.ToSAggrementFour = ToSAggrementFour;
        this.licenseType = licenseType;
        this.licenseRegion = licenseRegion;
        this.licenseNo = licenseNo;
        this.licenseExpiryDate = licenseExpiryDate;
        this.licenseDate = licenseDate;
        this.licenseAggrement = licenseAggrement;
    }
}
