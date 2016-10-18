package org.csw.entity.baseinfo;
/*
 * 功能类型  预报方案类
 */

public class PredictionScheme {
	
	String id;
	
	/*预报方案编码*/
	String predictionCode;
	
	/*预报时段类型的number*/
	String typeNumber;
	
	/*预报时段类型的单位*/
	String typeUnit;
	
	/*是否为默认项*/
	String isDefault;
	
	/*功能类型 - 计算对象ID*/
	String funCalculationId;

	@Override
	public String toString() {
		return "PredictionScheme [id=" + id + ", predictionCode=" + predictionCode + ", typeNumber=" + typeNumber
				+ ", typeUnit=" + typeUnit + ", isDefault=" + isDefault + ", funCalculationId=" + funCalculationId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funCalculationId == null) ? 0 : funCalculationId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((predictionCode == null) ? 0 : predictionCode.hashCode());
		result = prime * result + ((typeNumber == null) ? 0 : typeNumber.hashCode());
		result = prime * result + ((typeUnit == null) ? 0 : typeUnit.hashCode());
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
		PredictionScheme other = (PredictionScheme) obj;
		if (funCalculationId == null) {
			if (other.funCalculationId != null)
				return false;
		} else if (!funCalculationId.equals(other.funCalculationId))
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
		if (predictionCode == null) {
			if (other.predictionCode != null)
				return false;
		} else if (!predictionCode.equals(other.predictionCode))
			return false;
		if (typeNumber == null) {
			if (other.typeNumber != null)
				return false;
		} else if (!typeNumber.equals(other.typeNumber))
			return false;
		if (typeUnit == null) {
			if (other.typeUnit != null)
				return false;
		} else if (!typeUnit.equals(other.typeUnit))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPredictionCode() {
		return predictionCode;
	}

	public void setPredictionCode(String predictionCode) {
		this.predictionCode = predictionCode;
	}

	public String getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}

	public String getTypeUnit() {
		return typeUnit;
	}

	public void setTypeUnit(String typeUnit) {
		this.typeUnit = typeUnit;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getFunCalculationId() {
		return funCalculationId;
	}

	public void setFunCalculationId(String funCalculationId) {
		this.funCalculationId = funCalculationId;
	}
}
