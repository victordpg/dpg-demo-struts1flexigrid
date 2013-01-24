package com.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.form.Gcib;
import com.util.DBConnection;
/**
 * Manage the GCIB object.
 * @author Victor(DIAO,PIGANG)
 */
public class GcibDAO {
	/**
	 * Get the search result with pagination.
	 * @param paraMap
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Gcib> getGcibList(Map<String,Object> paraMap,int start, int end) {
		List<Gcib> folderList = new ArrayList<Gcib>();
		String sql = "select id,name,filetype from gcib where 1=1 ";
		//search conditions in where statement. 
		String idPara = (String)paraMap.get("idPara");
		String namePara = (String)paraMap.get("namePara");
		//flexigrid order.
		String sortname = (String)paraMap.get("sortname");
		String sortorder = (String)paraMap.get("sortorder");
		String orderSQL = "";	 //record order statement.
		if(!this.isEmpty(sortname)||!this.isEmpty(sortorder)){
			orderSQL= " order by " + sortname+" "+sortorder;
		}
		sql = sql+(!this.isEmpty(idPara)?" and id ="+idPara :"");
		sql = sql+(!this.isEmpty(namePara)?" and name ='"+namePara+"'":"");
		sql = sql + orderSQL;
		sql = sql + " limit "+start+","+end+"";
		Connection connect = DBConnection.getConnection();
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Gcib gcib = new Gcib();
				Integer idInt = rs.getInt("id");
				gcib.setId(idInt);
				gcib.setName(rs.getString("name"));
				gcib.setFiletype(rs.getString("filetype"));
				//String url = "<a href=\"/blank/SearchAction.do?method=showPDFandIMG&fileID="+idInt.toString()+"\" traget=\"_black\"\"><font color=\'blue\'>预览</font></a>";
				//如果使用window.showModalDialog方法弹出下载文件，在IE中不好用。所以使用window.open或者上面的方法。 
				String url = "<a href=\"javascript:onClick=GCIB.showObject(\'"+idInt.toString()+"\')\"><font color=\'blue\'>预览</font></a>";
				gcib.setUrl(url);
				folderList.add(gcib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return folderList;
	}
	
	/**
	 * Get the file in byte[].
	 * @param id
	 * @return
	 */
	public byte[] getDBFile(int id) {
		String sqlFind = "select file from gcib where id = "+id;
		byte[] pdf = null;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				pdf = rs.getBytes("file");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return pdf;
	}

	/**
	 * Get the file in Blob.
	 * @param id
	 * @return
	 */
	public Blob getDBBlobFile(int id) {
		String sqlFind = "select file from gcib where id = "+id;
		Blob pdf = null;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				pdf = rs.getBlob("file");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return pdf;
	}	
	
	/**
	 * Get the total of the records.
	 * @return
	 */
	public int getTotal() {
		int total  = 0;
		String sqlFind = "select count(*) from gcib ";
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * Get the filetype of the stream for download and preview the file. 
	 * @param id
	 * @return
	 */
	public String getFileType(Integer id) {
		String sqlFind = "select fileType from gcib where id = "+id;
		String result = null;
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFind);
			while(rs.next()){
				result = rs.getString("fileType");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException enull){
			enull.printStackTrace();
		}
		return result;
	}
	
	/**
	 * To check the string whether is empty.
	 * @param para
	 * @return
	 */
	private boolean isEmpty(String para){
		return null==para || para.length()==0;
	}	
}
