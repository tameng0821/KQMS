package com.yyj.entity;

public class AttendanceType {
	private int typeId;
	private String typeName;
	private String typeCategory;
	public AttendanceType() {
		super();
	}
	public AttendanceType(int typeId, String typeName, String typeCategory) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeCategory = typeCategory;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCategory() {
		return typeCategory;
	}
	public void setTypeCategory(String typeCategory) {
		this.typeCategory = typeCategory;
	}
	@Override
	public String toString() {
		return "AttendanceType [typeId=" + typeId + ", typeName=" + typeName + ", typeCategory=" + typeCategory + "]";
	}
	
}
