package org.csw.entity.baseinfo;
/*
 * 模型方法-输入输出类
 */

public class InputOutput {
	
	String id;
	
	String projectName;
	
	String isSet;
	
	String description;
	
	String modelCode;
	
	String type;

	@Override
	public String toString() {
		return "InputOutput [id=" + id + ", projectName=" + projectName + ", isSet=" + isSet + ", description="
				+ description + ", modelCode=" + modelCode + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isSet == null) ? 0 : isSet.hashCode());
		result = prime * result + ((modelCode == null) ? 0 : modelCode.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
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
		InputOutput other = (InputOutput) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
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
		if (modelCode == null) {
			if (other.modelCode != null)
				return false;
		} else if (!modelCode.equals(other.modelCode))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIsSet() {
		return isSet;
	}

	public void setIsSet(String isSet) {
		this.isSet = isSet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
