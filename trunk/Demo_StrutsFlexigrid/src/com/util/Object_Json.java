package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 使用Json-lib进行对象与Json之间的相互转换。<br/>
 * &nbsp&nbsp数组与集合的操作主要由JSONArray类完成。<br/>
 * &nbsp&nbspMap与Object之前的操作主要由JSONObject类完成。
 *	<p>Json-lib requires (at least) the following dependencies in your classpath:<br/>
    Json-lib jar<br/>
    jakarta commons-lang 2.5<br/>
    jakarta commons-beanutils 1.8.0<br/>
    jakarta commons-collections 3.2.1<br/>
    jakarta commons-logging 1.1.1<br/>
    ezmorph 1.0.6</p>
 * @author victor
 */
public class Object_Json {
	/**
	 * 将数组转换成json
	 * 
	 * @param objectArrays
	 * @return
	 */
	public String arrays2Json(Object[] objectArrays) {
		String json = "";
		JSONArray jsonArray = JSONArray.fromObject(objectArrays);
		json = jsonArray.toString();
		return json;
	}

	/**
	 * 将json转换成数组
	 * 
	 * @param json
	 * @return
	 */
	public Object[] json2Arrays(String json) {
		Object[] object = null;
		JSONArray jsonArray = JSONArray.fromObject( json );  
		object = jsonArray.toArray();
		return object;
	}	
	
	/**
	 * 将集合转换成json
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String list2Json(List list) {
		String json = "";
		JSONArray jsonArray = JSONArray.fromObject(list);
		json = jsonArray.toString();
		return json;
	}
	
	public static JSONArray list2Json2(List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}	
	
	/**
	 * 将json转换成集合
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List json2List(String json) {
		List list = new ArrayList();
		JSONArray jsonArray = JSONArray.fromObject( json );  
		Iterator it = jsonArray.iterator();
		while(it.hasNext()){
			list.add(it.next());
		}
		return list;
	}	
	
	/**
	 * 将Map转换成json
	 * 
	 * @param map
	 * @return
	 */
	public String map2Json(Map map){
		String json = "";
		JSONObject jsonObject = JSONObject.fromObject(map);  
		json = jsonObject.toString();
		return json;
	}
	
	/**
	 * 将json转换成Map
	 * 
	 * @param json
	 * @return
	 */
	public Map json2Map(String json){
		Map map = new HashMap();
		JSONObject jsonObject = JSONObject.fromObject( json );  
        for(Object k : jsonObject.keySet()){
            Object v = jsonObject.get(k); 
            map.put(k.toString(), v);
        }
		return map;
	}	
	
	/**
	 * 将JavaBean转换成json
	 * 
	 * @param map
	 * @return
	 */
	public String javaBean2Json(Object javaBean){
		String json = "";
		JSONObject jsonObject = JSONObject.fromObject(javaBean);  
		json = jsonObject.toString();
		return json;
	}
	
	/**
	 * 将json转换成JavaBean
	 * 
	 * @param json
	 * @return
	 */
	public Object json2JavaBean(String json, Object obj){
		Object object = new Object();
		JSONObject jsonObject = JSONObject.fromObject( json );  
		object = JSONObject.toBean(jsonObject, obj.getClass() );  		
		return object;
	}
	
	/**
	 * 本类的测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		Object_Json transform = new Object_Json();
		//测试json2JavaBean
		/*String json2JavaBean = "{'folder_name':'username','folder_type':0,'id':1,'parent_id':0,'username':'test'}";
		Folder folder= (Folder) transform.json2JavaBean(json2JavaBean, new Folder());
		System.out.println(folder.toString());*/
		
		//测试javaBean2Json
		/*Folder folder = new Folder(1, "Jack", 0, "收件箱", 1);
		String str_javaBean2Json = transform.javaBean2Json(folder);
		System.out.println(str_javaBean2Json);*/
		
		//)测试json2Map
		/*String json2map = "{'map2':{'folder_name':'username2','folder_type':2,'id':2,'parent_id':2,'username':'test'},'map1':{'folder_name':'username','folder_type':0,'id':1,'parent_id':0,'username':'test'}}";
		Map mapjson2Map =  transform.json2Map(json2map);
		System.out.println(mapjson2Map.get("map2"));*/
		
		//测试map2Json
		/*String json = "{'folder_name':'username','folder_type':0,'id':1,'parent_id':0,'username':'test'}";
		Folder folder1= (Folder) transform.json2JavaBean(json, new Folder());
		Map map = new HashMap();
		map.put("map1", folder1);
		map.put("map2", folder1);
		transform.map2Json(map);*/

		//测试list2Json
		/*Gcib bean1 = new Gcib();
		bean1.setId(1);
		bean1.setName("name");
		bean1.setUrl("123");
		Gcib bean2 = new Gcib();
		bean2.setId(1);
		bean2.setName("name1");
		bean2.setUrl("456");
		ArrayList array = new ArrayList();
		array.add(bean1);
		array.add(bean2);
		String str = transform.list2Json(array);
		System.out.println( str ); */
		
		
		//测试json2List
		/*String str_json2List = "["
				+ "{'folder_name':'收件箱','folder_type':0,'id':1,'parent_id':0,'username':'test'},"
				+ "{'folder_name':'垃圾箱','folder_type':0,'id':2,'parent_id':0,'username':'test'},"
				+ "{'folder_name':'草稿箱','folder_type':0,'id':3,'parent_id':0,'username':'test'},"
				+ "{'folder_name':'发件箱','folder_type':0,'id':4,'parent_id':0,'username':'test'},"
				+ "{'folder_name':'文件夹','folder_type':0,'id':5,'parent_id':0,'username':'test'},"
				+ "{'folder_name':'文件夹','folder_type':0,'id':5,'parent_id':5,'username':'test'}"
				+ "]";
		List list = transform.json2List(str_json2List);
		System.out.println( list );*/
		
		//测试array2Json
		/*String[] arrays_array2Json = new String[]{"aa","bb","cc","dd"};
		String str_array2Json = transform.arrays2Json(arrays_array2Json);
		System.out.println( str_array2Json );*/
		
		//测试json2Arrays
		/*String str_json2Arrays = "['aa','bb','cc','dd']";
		Object[] str = transform.json2Arrays(str_json2Arrays);
		System.out.println( Arrays.toString(str));*/
	}
}
