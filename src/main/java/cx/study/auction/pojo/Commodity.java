package cx.study.auction.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Commodity {
    private Integer id;

    private String commodityName;

    private Integer typeId;

    private String description;

    private Integer customerId;

    private double appraisedPrice;

    private double reservePrice;

    private double startingPrice;

    private double bidIncrements;

    private double hammerPrice;

    private double biddingDeposit;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private Date registrationTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public double getAppraisedPrice() {
        return appraisedPrice;
    }

    public void setAppraisedPrice(double appraisedPrice) {
        this.appraisedPrice = appraisedPrice;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getBidIncrements() {
        return bidIncrements;
    }

    public void setBidIncrements(double bidIncrements) {
        this.bidIncrements = bidIncrements;
    }

    public double getHammerPrice() {
        return hammerPrice;
    }

    public void setHammerPrice(double hammerPrice) {
        this.hammerPrice = hammerPrice;
    }

    public double getBiddingDeposit() {
        return biddingDeposit;
    }

    public void setBiddingDeposit(double biddingDeposit) {
        this.biddingDeposit = biddingDeposit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}