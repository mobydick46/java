package org.csw.entity.baseinfo;
/*
 * 功能类型 计算对象类
 */

public class FunctionCalculation {
	
	String id;
	
	/*上一节中的计算对象编码*/
	String objectCode;
	
	/*上一节中的计算对象名称*/
	String objectName;
	
	/*上一节中的计算对象父对象编码*/
	String parentCode;
	
	/*上一节中的计算对象父对象名称*/
	String parentName;
	
	/*是否计算*/
	String isCalculation;
	
	/*上一节中的计算对象类型*/
	String nodeType;
	
	/*功能类型的ID*/
	String functionId;

	@Override
	public String toString() {
		return "FunctionCalculation [id=" + id + ", objectCode=" + objectCode + ", objectName=" + objectName
				+ ", parentCode=" + parentCode + ", isCalculation=" + isCalculation + ", nodeType=" + nodeType
				+ ", functionId=" + functionId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isCalculation == null) ? 0 : isCalculation.hashCode());
		result = prime * result + ((nodeType == null) ? 0 : nodeType.hashCode());
		result = prime * result + ((objectCode == null) ? 0 : objectCode.hashCode());
		result = prime * result + ((objectName == null) ? 0 : objectName.hashCode());
		result = prime * result + ((parentCode == null) ? 0 : parentCode.hashCode());
		result = prime * result + ((parentName == null) ? 0 : parentName.hashCode());
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
		FunctionCalculation other = (FunctionCalculation) obj;
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
		if (isCalculation == null) {
			if (other.isCalculation != null)
				return false;
		} else if (!isCalculation.equals(other.isCalculation))
			return false;
		if (nodeType == null) {
			if (other.nodeType != null)
				return false;
		} else if (!nodeType.equals(other.nodeType))
			return false;
		if (objectCode == null) {
			if (other.objectCode != null)
				return false;
		} else if (!objectCode.equals(other.objectCode))
			return false;
		if (objectName == null) {
			if (other.objectName != null)
				return false;
		} else if (!objectName.equals(other.objectName))
			return false;
		if (parentCode == null) {
			if (other.parentCode != null)
				return false;
		} else if (!parentCode.equals(other.parentCode))
			return false;
		if (parentName == null) {
			if (other.parentName != null)
				return false;
		} else if (!parentName.equals(other.parentName))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getIsCalculation() {
		return isCalculation;
	}

	public void setIsCalculation(String isCalculation) {
		this.isCalculation = isCalculation;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
}
