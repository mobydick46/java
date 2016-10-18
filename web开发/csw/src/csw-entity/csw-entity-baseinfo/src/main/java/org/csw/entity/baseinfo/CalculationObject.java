package org.csw.entity.baseinfo;
/*
 * 计算对象类
 */

public class CalculationObject {
	/*ID*/
	String id;
	/*计算对象编码*/
	String objectCode;
	/*计算对象名称*/
	String objectName;
	/*是否设置*/
	String isSet;
	/*计算对象类别*/
	String nodeType;
	/*计算对象父对象编码，当计算对象类型为机组闸门时才会起作用*/
	String parentCode;
	/*计算对象父对象名称，当计算对象类型为机组闸门时才会起作用*/
	String parentName;

	@Override
	public String toString() {
		return "CalculationObject [id=" + id + ", objectCode=" + objectCode + ", objectName=" + objectName + ", isSet="
				+ isSet + ", nodeType=" + nodeType + ", parentCode=" + parentCode + ", parentName=" + parentName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isSet == null) ? 0 : isSet.hashCode());
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
		CalculationObject other = (CalculationObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isSet == null) {
			if (other.isSet != null)
				return false;
		} else if (!isSet.equals(other.isSet))
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

	public String getIsSet() {
		return isSet;
	}

	public void setIsSet(String isSet) {
		this.isSet = isSet;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
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
}
