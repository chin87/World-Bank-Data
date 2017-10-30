package com.chinmay.worldbankdata.POJO;

/**
 * Created by chinmaydeshpande on 30/10/17.
 */

public class Datacatalog
{
	private String id;

	private Metatype[] metatype;

	public String getId ()
	{
		return id;
	}

	public void setId (String id)
	{
		this.id = id;
	}

	public Metatype[] getMetatype ()
	{
		return metatype;
	}

	public void setMetatype (Metatype[] metatype)
	{
		this.metatype = metatype;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [id = "+id+", metatype = "+metatype+"]";
	}
}
