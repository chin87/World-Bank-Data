package com.chinmay.worldbankdata.POJO;

/**
 * Created by chinmaydeshpande on 30/10/17.
 */

public class Datacatalog {
	private String id;

	private Metatype[] metatype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Metatype[] getMetatype() {
		return metatype;
	}

	public void setMetatype(Metatype[] metatype) {
		this.metatype = metatype;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", metatype = " + metatype + "]";
	}

	public String getName() {
		return getValue("name");
	}

	public String getDescription() {
		return getValue("description");
	}

	public String getType() {
		return getValue("type");
	}

	public String getDate(){
		return getValue("lastrevisiondate");
	}

	public String getCite(){
		return getValue("cite");
	}

	public String getUrl(){
		return getValue("url");
	}

	public String getValue(String key){
		for (Metatype metatypes: metatype) {
			if( metatypes.getId().equals(key)){
				return metatypes.getValue();
			}
		}
		return "Not available";
	}
}

