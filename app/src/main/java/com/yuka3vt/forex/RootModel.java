package com.yuka3vt.forex;

import com.google.gson.annotations.SerializedName;

import kotlin.jvm.internal.SerializedIr;

public class RootModel {
    @SerializedName("rates")
    private RatesModel ratesModel;
    public RootModel(){}

    public RatesModel getRatesModel(){return ratesModel;}

    public void setRatesModel(RatesModel ratesModel){this.ratesModel = ratesModel;}
}
