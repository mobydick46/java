package org.csw.entity.baseinfo;
/*
 * 功能类型  调度期时间类
 */

public class ScheduleTime {
	
	String id;
	
	String timeNumber;
	
	String timeUnit;
	
	String defaultNumber;
	
	String isDefault;
	
	String functionId;

	@Override
	public String toString() {
		return "ScheduleTime [id=" + id + ", timeNumber=" + timeNumber + ", timeUnit=" + timeUnit + ", defaultNumber="
				+ defaultNumber + ", isDefault=" + isDefault + ", functionId=" + functionId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultNumber == null) ? 0 : defaultNumber.hashCode());
		result = prime * result + ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((timeNumber == null) ? 0 : timeNumber.hashCode());
		result = prime * result + ((timeUnit == null) ? 0 : timeUnit.hashCode());
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
		ScheduleTime other = (ScheduleTime) obj;
		if (defaultNumber == null) {
			if (other.defaultNumber != null)
				return false;
		} else if (!defaultNumber.equals(other.defaultNumber))
			return false;
		if (functionId == null) {
			if (other.functionId != null)
				return false;
		} else if (!functionId.equals(other.functionId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDefault == null) {
			if (other.isDefault != null)
				return false;
		} else if (!isDefault.equals(other.isDefault))
			return false;
		if (timeNumber == null) {
			if (other.timeNumber != null)
				return false;
		} else if (!timeNumber.equals(other.timeNumber))
			return false;
		if (timeUnit == null) {
			if (other.timeUnit != null)
				return false;
		} else if (!timeUnit.equals(other.timeUnit))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimeNumber() {
		return timeNumber;
	}

	public void setTimeNumber(String timeNumber) {
		this.timeNumber = timeNumber;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getDefaultNumber() {
		return defaultNumber;
	}

	public void setDefaultNumber(String defaultNumber) {
		this.defaultNumber = defaultNumber;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
}
