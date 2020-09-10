package com.nutn.utm.model.dto.mqtt;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "channel",
        "sf",
        "time",
        "gwip",
        "gwid",
        "repeater",
        "systype",
        "rssi",
        "snr",
        "snr_max",
        "snr_min",
        "macAddr",
        "data",
        "frameCnt",
        "fport"
})
public class LoRaGatewayMessage {

    @JsonProperty("channel")
    private Integer channel;
    @JsonProperty("sf")
    private Integer sf;
    @JsonProperty("time")
    private String time;
    @JsonProperty("gwip")
    private String gwip;
    @JsonProperty("gwid")
    private String gwid;
    @JsonProperty("repeater")
    private String repeater;
    @JsonProperty("systype")
    private Integer systype;
    @JsonProperty("rssi")
    private Double rssi;
    @JsonProperty("snr")
    private Double snr;
    @JsonProperty("snr_max")
    private Double snrMax;
    @JsonProperty("snr_min")
    private Double snrMin;
    @JsonProperty("macAddr")
    private String macAddr;
    @JsonProperty("data")
    private String data;
    @JsonProperty("frameCnt")
    private Integer frameCnt;
    @JsonProperty("fport")
    private Integer fport;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("channel")
    public Integer getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @JsonProperty("sf")
    public Integer getSf() {
        return sf;
    }

    @JsonProperty("sf")
    public void setSf(Integer sf) {
        this.sf = sf;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("gwip")
    public String getGwip() {
        return gwip;
    }

    @JsonProperty("gwip")
    public void setGwip(String gwip) {
        this.gwip = gwip;
    }

    @JsonProperty("gwid")
    public String getGwid() {
        return gwid;
    }

    @JsonProperty("gwid")
    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    @JsonProperty("repeater")
    public String getRepeater() {
        return repeater;
    }

    @JsonProperty("repeater")
    public void setRepeater(String repeater) {
        this.repeater = repeater;
    }

    @JsonProperty("systype")
    public Integer getSystype() {
        return systype;
    }

    @JsonProperty("systype")
    public void setSystype(Integer systype) {
        this.systype = systype;
    }

    @JsonProperty("rssi")
    public Double getRssi() {
        return rssi;
    }

    @JsonProperty("rssi")
    public void setRssi(Double rssi) {
        this.rssi = rssi;
    }

    @JsonProperty("snr")
    public Double getSnr() {
        return snr;
    }

    @JsonProperty("snr")
    public void setSnr(Double snr) {
        this.snr = snr;
    }

    @JsonProperty("snr_max")
    public Double getSnrMax() {
        return snrMax;
    }

    @JsonProperty("snr_max")
    public void setSnrMax(Double snrMax) {
        this.snrMax = snrMax;
    }

    @JsonProperty("snr_min")
    public Double getSnrMin() {
        return snrMin;
    }

    @JsonProperty("snr_min")
    public void setSnrMin(Double snrMin) {
        this.snrMin = snrMin;
    }

    @JsonProperty("macAddr")
    public String getMacAddr() {
        return macAddr;
    }

    @JsonProperty("macAddr")
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("frameCnt")
    public Integer getFrameCnt() {
        return frameCnt;
    }

    @JsonProperty("frameCnt")
    public void setFrameCnt(Integer frameCnt) {
        this.frameCnt = frameCnt;
    }

    @JsonProperty("fport")
    public Integer getFport() {
        return fport;
    }

    @JsonProperty("fport")
    public void setFport(Integer fport) {
        this.fport = fport;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}