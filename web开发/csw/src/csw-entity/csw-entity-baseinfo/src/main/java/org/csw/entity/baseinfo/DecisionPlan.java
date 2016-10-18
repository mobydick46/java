package org.csw.entity.baseinfo;
/*
 * 功能类型 决策计划类
 */

public class DecisionPlan {
	
	String id;
	
	/*决策计划项目*/
	String planName;
	
	/*功能类型 调度期时间ID*/
	String timeID;
	
	/*水库对象*/
	String reservoirObject;
	
	/*水库对象编码  从功能类型 计算对象中获取*/
	String objectCode;
	
	/*水库对象编号*/
	String objectNumber;
	
	/*水库列序号*/
	String number;
	
	/*功能类型的ID*/
	String functionId;

	@Override
	public String toString() {
		return "DecisionPlan [id=" + id + ", planName=" + planName + ", timeID=" + timeID + ", reservoirObject="
				+ reservoirObject + ", objectCode=" + objectCode + ", objectNumber=" + objectNumber + ", number="
				+ number + ", functionId=" + functionId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((objectCode == null) ? 0 : objectCode.hashCode());
		result = prime * result + ((objectNumber == null) ? 0 : objectNumber.hashCode());
		result = prime * result + ((planName == null) ? 0 : planName.hashCode());
		result = prime * result + ((reservoirObject == null) ? 0 : reservoirObject.hashCode());
		result = prime * result + ((timeID == null) ? 0 : timeID.hashCode());
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
		DecisionPlan other = (DecisionPlan) obj;
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
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (objectCode == null) {
			if (other.objectCode != null)
				return false;
		} else if (!objectCode.equals(other.objectCode))
			return false;
		if (objectNumber == null) {
			if (other.objectNumber != null)
				return false;
		} else if (!objectNumber.equals(other.objectNumber))
			return false;
		if (planName == null) {
			if (other.planName != null)
				return false;
		} else if (!planName.equals(other.planName))
			return false;
		if (reservoirObject == null) {
			if (other.reservoirObject != null)
				return false;
		} else if (!reservoirObject.equals(other.reservoirObject))
			return false;
		if (timeID == null) {
			if (other.timeID != null)
				return false;
		} else if (!timeID.equals(other.timeID))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getTimeID() {
		return timeID;
	}

	public void setTimeID(String timeID) {
		this.timeID = timeID;
	}

	public String getReservoirObject() {
		return reservoirObject;
	}

	public void setReservoirObject(String reservoirObject) {
		this.reservoirObject = reservoirObject;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectNumber() {
		return objectNumber;
	}

	public void setObjectNumber(String objectNumber) {
		this.objectNumber = objectNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
}
