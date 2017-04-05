package com.first.domain;

public class MemberIntegralWithBLOBs extends MemberIntegral {
    private String mintRemark;

    private String mintRemainDetail;

    private String mintSingleMarketDetail;

    private String mintPerorderDetail;

    private String mintChildRetrunDetail;

    private String mintChildProfitDetail;

    private String mintSonDetail;

    public String getMintRemark() {
        return mintRemark;
    }

    public void setMintRemark(String mintRemark) {
        this.mintRemark = mintRemark == null ? null : mintRemark.trim();
    }

    public String getMintRemainDetail() {
        return mintRemainDetail;
    }

    public void setMintRemainDetail(String mintRemainDetail) {
        this.mintRemainDetail = mintRemainDetail == null ? null : mintRemainDetail.trim();
    }

    public String getMintSingleMarketDetail() {
        return mintSingleMarketDetail;
    }

    public void setMintSingleMarketDetail(String mintSingleMarketDetail) {
        this.mintSingleMarketDetail = mintSingleMarketDetail == null ? null : mintSingleMarketDetail.trim();
    }

    public String getMintPerorderDetail() {
        return mintPerorderDetail;
    }

    public void setMintPerorderDetail(String mintPerorderDetail) {
        this.mintPerorderDetail = mintPerorderDetail == null ? null : mintPerorderDetail.trim();
    }

    public String getMintChildRetrunDetail() {
        return mintChildRetrunDetail;
    }

    public void setMintChildRetrunDetail(String mintChildRetrunDetail) {
        this.mintChildRetrunDetail = mintChildRetrunDetail == null ? null : mintChildRetrunDetail.trim();
    }

    public String getMintChildProfitDetail() {
        return mintChildProfitDetail;
    }

    public void setMintChildProfitDetail(String mintChildProfitDetail) {
        this.mintChildProfitDetail = mintChildProfitDetail == null ? null : mintChildProfitDetail.trim();
    }

    public String getMintSonDetail() {
        return mintSonDetail;
    }

    public void setMintSonDetail(String mintSonDetail) {
        this.mintSonDetail = mintSonDetail == null ? null : mintSonDetail.trim();
    }
}