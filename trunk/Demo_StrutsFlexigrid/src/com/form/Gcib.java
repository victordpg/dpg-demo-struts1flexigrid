package com.form;
/**
 * GCIB class mapping the table GCIB in mysql.
 * add a url property for deal with the record.
 * @author Victor(DIAO,PIGANG)
 */
public class Gcib {
		private int id;
		private String name;
		private String filetype;
		private String url;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getFiletype() {
			return filetype;
		}
		public void setFiletype(String filetype) {
			this.filetype = filetype;
		}
}
