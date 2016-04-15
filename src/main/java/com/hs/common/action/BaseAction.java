package com.hs.common.action;

import com.hs.common.model.BaseData;
import com.hs.common.service.IBaseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by work_tl on 2016/4/5.
 */
public class BaseAction extends ActionSupport implements Preparable, ServletRequestAware, ServletResponseAware, ServletContextAware,SessionAware{

    private BaseData baseData;
    protected ServletContext servletContext;
    protected HttpServletRequest httpServletRequest;
    protected HttpServletResponse httpServletResponse;
    protected Map<String, Object> session;

    public void flushOutput(){
        if(baseData!=null && baseData.getOutput()!=null){
            try {
                httpServletResponse.setCharacterEncoding("utf-8");
                String retS = "";
                if(JSONArray.class.isAssignableFrom(baseData.getOutput().getClass())){
                    retS = JSONArray.fromObject(baseData.getOutput()).toString();
                }
                if(JSONObject.class.isAssignableFrom(baseData.getOutput().getClass())){
                    retS = JSONObject.fromObject(baseData.getOutput()).toString();
                }
                httpServletResponse.getWriter().write(retS);
                httpServletResponse.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepare() throws Exception {
        System.out.print("BaseAction prepare");
    }

    public void setServletContext(ServletContext servletContext) {
         this.servletContext = servletContext;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public BaseData getBaseData() {
        if(this.baseData==null){
            this.baseData = new BaseData();
        }
        if(this.baseData.getInput()==null){
            baseData.setInput(transToMAP(httpServletRequest.getParameterMap()));
        }
        return baseData;
    }

    public void setBaseData(BaseData baseData) {
        this.baseData = baseData;
    }

    /** request参数转换成普通map */
    private Map transToMAP(Map parameterMap){
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = parameterMap.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return  returnMap;
    }

    /** 页面数据分页查询 */
    public void queryDataForJSONArrayPage(IBaseService baseService, String sqlName, BaseData data){
        data.addInput("sqlType", "count");
        JSONArray countJs = baseService.queryDataForJSONArray(sqlName, data);
        long total = Long.valueOf(String.valueOf(countJs.getJSONObject(0).get("count")));
        data.addInput("sqlType", "record");
        JSONArray recordJs = baseService.queryDataForJSONArray(sqlName, data);
        JSONObject output = new JSONObject();
        output.put("total",total);
        output.put("rows",recordJs);
        data.setOutput(output);
    }
}
