package com.chinmay.worldbankdata.pojo;

/**
 * Created by chinmaydeshpande on 30/10/17.
 */

public class Metatype
{
	private String id;

	private String value;

	public String getId ()
	{
		return id;
	}

	public void setId (String id)
	{
		this.id = id;
	}

	public String getValue ()
	{
		return value;
	}

	public void setValue (String value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [id = "+id+", value = "+value+"]";
	}
}
