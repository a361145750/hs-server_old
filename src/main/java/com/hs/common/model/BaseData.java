package com.hs.common.model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by work_tl on 2016/4/5.
 */
public class BaseData {

    private Map input;
    private Object output;

    public Map getInput() {
        return input;
    }

    public String getInputString(String key){
        if(input!=null && input.containsKey(key)){
            return (String)input.get(key);
        }
        return "";
    }

    public void setInput(Map input) {
        this.input = input;
    }

    public void addInput(String key, Object value) {
        if (input == null) {
            input = new HashMap();
        }
        input.put(key,value);
    }

    public void addInput(Map map) {
        if (map != null) {
            if (input == null) {
                input = new HashMap();
            }
            input.putAll(map);
        }
    }

    public Object getOutput() {
        return output;
    }

    public JSONObject getOutputForJSONObject() {
        return output==null?null:(JSONObject.class.isAssignableFrom(output.getClass())?(JSONObject)output:null);
    }

    public JSONArray getOutputForJSONArray() {
        return output==null?null:(JSONArray.class.isAssignableFrom(output.getClass())?(JSONArray)output:null);
    }

    public void setOutput(Object output) {
        this.output = output;
    }

}
