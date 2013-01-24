package com.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.dao.GcibDAO;
import com.form.Gcib;
/**
 * Action class to search records and show file.
 * @author Victor(DIAO,PIGANG)
 */
public class SearchAction extends  DispatchAction{
	/**
	 * Search method.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
        JSONObject result = new JSONObject();
        response.setContentType("text/json");
        String idPara = request.getParameter("idPara");	//APP search condition.
        String namePara = request.getParameter("namePara");	  //APP search condition.
        String sortname = request.getParameter("sortname");  //flexigrid's attribute.
        String sortorder = request.getParameter("sortorder");	//flexigrid's attribute.
        /*
         String query = request.getParameter("query");   //flexigrid's query attribute.
         String qtype = request.getParameter("qtype");   //flexigrid's query attribute.
         */       
        try {
        	request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            Map<String,Object> paraMap = new HashMap<String,Object>();
            paraMap.put("idPara", idPara);
            paraMap.put("namePara", namePara);
            paraMap.put("sortname", sortname);
            paraMap.put("sortorder", sortorder);
		    GcibDAO dao = new GcibDAO();
            int page = Integer.valueOf(StringUtils.defaultIfEmpty(request.getParameter("page"), "1"));
            int rp = Integer.valueOf(StringUtils.defaultIfEmpty(request.getParameter("rp"), "10"));
            int start = (page - 1) * rp;
            int end = start + rp;
	        int total = dao.getTotal(); 
		    List<?> demoList = new ArrayList<Gcib>();
		    demoList = dao.getGcibList(paraMap,start, end);
            result.put("rows", demoList);
            result.put("total", total);
            result.put("page", page);
            result.put("rp", rp);
        }catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	//IMPORTANT: this statement is very important for flexigrid. if without this, there will show nothing in the grid.
            writer.println(result.toString());	
            writer.flush();
            writer.close();
        }
	}

	/**
	 * Show file method. For now.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward showPDFandIMG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		OutputStream out = new BufferedOutputStream(response.getOutputStream());
		GcibDAO dao = new GcibDAO();
		String fileName = "FileName";
		String fileType = "";
		Blob blob = dao.getDBBlobFile(new Integer(request.getParameter("fileID")));
		fileType = dao.getFileType(new Integer(request.getParameter("fileID")));
		fileName = fileName + fileType;
		try {
			//stop IE cookie
			/*response.setHeader("pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);*/
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content_Length", new Long(blob.length()).toString());
			if (fileType.contains("pdf")) {
				response.setContentType("application/pdf;charset=UTF-8");
			}
			if (fileType.contains("jpg")||fileType.contains("jpeg")) {
				response.setContentType("image/*;charset=UTF-8");
			}
			//IMPORTANT: different browser different ways to deal file download and preview. 
			String agent = request.getHeader("USER-AGENT");
			if(agent.indexOf("MSIE")==-1){
				String enableName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
				//IMPORTANT: attachment mean download the file；inline mean open file in browser.
				response.setHeader("Content-Disposition","inline; filename=" + enableName );
			}else{
				response.setHeader("Content-Disposition","inline; filename=" + java.net.URLEncoder.encode(fileName,"UTF-8") );
			}
			BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
			byte[] data = new byte[1024];
			long k = 0;
			while (k<blob.length()) {
				int j = in.read(data, 0, 1024);
				k+=j;
				out.write(data, 0, j);
			}
			out.flush();
			in.close();
			out.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
