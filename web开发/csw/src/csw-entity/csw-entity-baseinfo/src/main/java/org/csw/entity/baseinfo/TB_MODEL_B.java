package org.csw.entity.baseinfo;

public class TB_MODEL_B {
    private String modelcd;

    private String modelnm;

    private String modelinf;

    private Integer modeltp;
    
    @Override
	public String toString() {
		return "TB_MODEL_B [modelcd=" + modelcd + ", modelnm=" + modelnm + ", modelinf=" + modelinf + ", modeltp="
				+ modeltp + "]";
	}

	public String getModelcd() {
        return modelcd;
    }

    public void setModelcd(String modelcd) {
        this.modelcd = modelcd == null ? null : modelcd.trim();
    }

    public String getModelnm() {
        return modelnm;
    }

    public void setModelnm(String modelnm) {
        this.modelnm = modelnm == null ? null : modelnm.trim();
    }

    public String getModelinf() {
        return modelinf;
    }

    public void setModelinf(String modelinf) {
        this.modelinf = modelinf == null ? null : modelinf.trim();
    }

    public Integer getModeltp() {
        return modeltp;
    }

    public void setModeltp(Integer modeltp) {
        this.modeltp = modeltp;
    }
}