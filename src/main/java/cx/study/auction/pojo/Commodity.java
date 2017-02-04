package cx.study.auction.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Commodity {
    private Integer id;

    private String commodityName;

    private Integer typeId;

    private String description;

    private Integer customerId;

    private BigDecimal appraisedPrice;

    private BigDecimal reservePrice;

    private BigDecimal startingPrice;

    private BigDecimal bidIncrements;

    private BigDecimal hammerPrice;

    private BigDecimal biddingDeposit;

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

    public BigDecimal getAppraisedPrice() {
        return appraisedPrice;
    }

    public void setAppraisedPrice(BigDecimal appraisedPrice) {
        this.appraisedPrice = appraisedPrice;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getBidIncrements() {
        return bidIncrements;
    }

    public void setBidIncrements(BigDecimal bidIncrements) {
        this.bidIncrements = bidIncrements;
    }

    public BigDecimal getHammerPrice() {
        return hammerPrice;
    }

    public void setHammerPrice(BigDecimal hammerPrice) {
        this.hammerPrice = hammerPrice;
    }

    public BigDecimal getBiddingDeposi() {
        return biddingDeposit;
    }

    public void setBiddingDeposit(BigDecimal biddingDeposit) {
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