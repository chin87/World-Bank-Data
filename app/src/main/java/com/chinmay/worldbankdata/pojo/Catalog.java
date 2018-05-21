package com.chinmay.worldbankdata.pojo;

/**
 * Created by chinmaydeshpande on 30/10/17.
 */

public class Catalog {
		private String total;

		private String per_page;

		private String page;

		private String pages;

		private Datacatalog[] datacatalog;

		public String getTotal ()
		{
			return total;
		}

		public void setTotal (String total)
		{
			this.total = total;
		}

		public String getPer_page ()
		{
			return per_page;
		}

		public void setPer_page (String per_page)
		{
			this.per_page = per_page;
		}

		public String getPage ()
		{
			return page;
		}

		public void setPage (String page)
		{
			this.page = page;
		}

		public String getPages ()
		{
			return pages;
		}

		public void setPages (String pages)
		{
			this.pages = pages;
		}

		public Datacatalog[] getDatacatalog ()
		{
			return datacatalog;
		}

		public void setDatacatalog (Datacatalog[] datacatalog)
		{
			this.datacatalog = datacatalog;
		}

		@Override
		public String toString()
		{
			return "ClassPojo [total = "+total+", per_page = "+per_page+", page = "+page+", pages = "+pages+", datacatalog = "+datacatalog+"]";
		}
	}
