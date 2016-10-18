package org.csw.entity.baseinfo;
/*
 * 模型方法类
 */

public class ModelMethod {
	
	String id;
	
	String code;
	
	String name;
	
	String description;
	
	String type;
	
	String combCalculation;
	
	String sourceClass;
	@Override
	public String toString() {
		return "ModelMethod [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description
				+ ", type=" + type + ", combCalculation=" + combCalculation + ", sourceClass=" + sourceClass + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((combCalculation == null) ? 0 : combCalculation.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sourceClass == null) ? 0 : sourceClass.hashCode());
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
		ModelMethod other = (ModelMethod) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (combCalculation == null) {
			if (other.combCalculation != null)
				return false;
		} else if (!combCalculation.equals(other.combCalculation))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sourceClass == null) {
			if (other.sourceClass != null)
				return false;
		} else if (!sourceClass.equals(other.sourceClass))
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCombCalculation() {
		return combCalculation;
	}

	public void setCombCalculation(String combCalculation) {
		this.combCalculation = combCalculation;
	}

	public String getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(String sourceClass) {
		this.sourceClass = sourceClass;
	}
}
