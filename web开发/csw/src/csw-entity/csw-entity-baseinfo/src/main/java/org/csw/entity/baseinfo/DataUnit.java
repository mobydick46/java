package org.csw.entity.baseinfo;
/*
 * 工程项目 数据单位类
 */

public class DataUnit {
	
	String id;
	
	String datatype;
	
	String standardunit;
	
	String displayunit;
	
	String times;
	
	String controlformat;
	
	String figure;

	@Override
	public String toString() {
		return "DataUnit [id=" + id + ", datatype=" + datatype + ", standardunit=" + standardunit + ", displayunit="
				+ displayunit + ", times=" + times + ", controlformat=" + controlformat + ", figure=" + figure + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controlformat == null) ? 0 : controlformat.hashCode());
		result = prime * result + ((datatype == null) ? 0 : datatype.hashCode());
		result = prime * result + ((displayunit == null) ? 0 : displayunit.hashCode());
		result = prime * result + ((figure == null) ? 0 : figure.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((standardunit == null) ? 0 : standardunit.hashCode());
		result = prime * result + ((times == null) ? 0 : times.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataUnit other = (DataUnit) obj;
		if (controlformat == null) {
			if (other.controlformat != null)
				return false;
		} else if (!controlformat.equals(other.controlformat))
			return false;
		if (datatype == null) {
			if (other.datatype != null)
				return false;
		} else if (!datatype.equals(other.datatype))
			return false;
		if (displayunit == null) {
			if (other.displayunit != null)
				return false;
		} else if (!displayunit.equals(other.displayunit))
			return false;
		if (figure == null) {
			if (other.figure != null)
				return false;
		} else if (!figure.equals(other.figure))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (standardunit == null) {
			if (other.standardunit != null)
				return false;
		} else if (!standardunit.equals(other.standardunit))
			return false;
		if (times == null) {
			if (other.times != null)
				return false;
		} else if (!times.equals(other.times))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getStandardunit() {
		return standardunit;
	}

	public void setStandardunit(String standardunit) {
		this.standardunit = standardunit;
	}

	public String getDisplayunit() {
		return displayunit;
	}

	public void setDisplayunit(String displayunit) {
		this.displayunit = displayunit;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getControlformat() {
		return controlformat;
	}

	public void setControlformat(String controlformat) {
		this.controlformat = controlformat;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}
}
