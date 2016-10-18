package org.csw.entity.baseinfo;
/*
 * 功能类型  模型方法类
 */

public class FunctionModel {
	
	String id;
	
	/*模型方法编码*/
	String modelCode;
	
	/*模型方法名称*/
	String modelName;
	
	/*描述*/
	String description;
	
	/*类型*/
	String modelType;
	
	/*是否应用*/
	String isApply;
	
	/*本节中功能类型 - 计算方法ID*/
	String funCalculationId;
	
	/*功能类型ID*/
	String functionId;
	
	/*联算  单库类别*/
	String type;

	@Override
	public String toString() {
		return "FunctionModel [id=" + id + ", modelCode=" + modelCode + ", modelName=" + modelName + ", description="
				+ description + ", modelType=" + modelType + ", isApply=" + isApply + ", funCalculationId="
				+ funCalculationId + ", functionId=" + functionId + ", type=" + type
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((funCalculationId == null) ? 0 : funCalculationId.hashCode());
		result = prime * result + ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isApply == null) ? 0 : isApply.hashCode());
		result = prime * result + ((modelCode == null) ? 0 : modelCode.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((modelType == null) ? 0 : modelType.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		FunctionModel other = (FunctionModel) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (funCalculationId == null) {
			if (other.funCalculationId != null)
				return false;
		} else if (!funCalculationId.equals(other.funCalculationId))
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
		if (isApply == null) {
			if (other.isApply != null)
				return false;
		} else if (!isApply.equals(other.isApply))
			return false;
		if (modelCode == null) {
			if (other.modelCode != null)
				return false;
		} else if (!modelCode.equals(other.modelCode))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (modelType == null) {
			if (other.modelType != null)
				return false;
		} else if (!modelType.equals(other.modelType))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getIsApply() {
		return isApply;
	}

	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}

	public String getFunCalculationId() {
		return funCalculationId;
	}

	public void setFunCalculationId(String funCalculationId) {
		this.funCalculationId = funCalculationId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
