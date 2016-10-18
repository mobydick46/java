package org.csw.entity.baseinfo;

public class TB_HYDRONODE_B {
    private String ndcd;

    private String ndnm;

    private Integer ndtp;

    private String rvcd;

    private Double rmcha;

    private Double lgtd;

    private Double lttd;

    private String dwndcd;

    private Double symlev;

    public String getNdcd() {
        return ndcd;
    }

    public void setNdcd(String ndcd) {
        this.ndcd = ndcd == null ? null : ndcd.trim();
    }

    public String getNdnm() {
        return ndnm;
    }

    public void setNdnm(String ndnm) {
        this.ndnm = ndnm == null ? null : ndnm.trim();
    }

    public Integer getNdtp() {
        return ndtp;
    }

    public void setNdtp(Integer ndtp) {
        this.ndtp = ndtp;
    }

    public String getRvcd() {
        return rvcd;
    }

    public void setRvcd(String rvcd) {
        this.rvcd = rvcd == null ? null : rvcd.trim();
    }

    public Double getRmcha() {
        return rmcha;
    }

    public void setRmcha(Double rmcha) {
        this.rmcha = rmcha;
    }

    public Double getLgtd() {
        return lgtd;
    }

    public void setLgtd(Double lgtd) {
        this.lgtd = lgtd;
    }

    public Double getLttd() {
        return lttd;
    }

    public void setLttd(Double lttd) {
        this.lttd = lttd;
    }

    public String getDwndcd() {
        return dwndcd;
    }

    public void setDwndcd(String dwndcd) {
        this.dwndcd = dwndcd == null ? null : dwndcd.trim();
    }

    public Double getSymlev() {
        return symlev;
    }

    public void setSymlev(Double symlev) {
        this.symlev = symlev;
    }
}