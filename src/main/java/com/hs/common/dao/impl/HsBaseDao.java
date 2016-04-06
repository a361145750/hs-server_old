package com.hs.common.dao.impl;

import com.hs.common.dao.IHsBaseDao;
import com.hs.common.model.BaseData;
import com.hs.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by work_tl on 2016/4/5.
 */
@Repository
public class HsBaseDao extends SqlSessionDaoSupport implements IHsBaseDao {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public JSONObject queryDataForJSONObject(String sqlname, BaseData data){
        JSONObject retObject = JSONObject.fromObject(tranResultMap(this.getSqlSession().selectOne(sqlname, data.getInput())));
        data.setOutput(retObject);
        return retObject;
    }

    public JSONArray queryDataForJSONArray(String sqlname, BaseData data){
        JSONArray retArray = JSONArray.fromObject(tranResultListMap(this.getSqlSession().selectList(sqlname, data.getInput())));
        data.setOutput(retArray);
        return retArray;
    }

    public int insertData(String sqlname, BaseData data){
        return this.getSqlSession().insert(sqlname,data.getInput());
    }

    public int updateData(String sqlname, BaseData data){
        return this.getSqlSession().update(sqlname,data.getInput());
    }

    public int deleteData(String sqlname, BaseData data){
        return this.getSqlSession().delete(sqlname,data.getInput());
    }

    /** 处理数据库传回带下划线的值 */
    public static Map tranResultMap(Object parameterMap){
        // 返回值Map
        Map returnMap = new HashMap();
        if(!Map.class.isAssignableFrom(parameterMap.getClass())){
            return returnMap;
        }
        Iterator entries = ((Map)parameterMap).entrySet().iterator();
        Map.Entry entry;
        String name = "";
        Object value = null;
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else{
                name = StringUtil.underlineToCamel(name);
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return  returnMap;
    }

    /** 处理数据库传回带下划线的值 */
    public static List<Map> tranResultListMap(Object parameterMap){
        // 返回值Map
        List<Map> returnMap = new LinkedList<Map>();
        if(!List.class.isAssignableFrom(parameterMap.getClass())){
            return returnMap;
        }
        Iterator entries = ((List<Map>)parameterMap).iterator();
        Map entry;
        while (entries.hasNext()) {
            returnMap.add(tranResultMap((Map) entries.next()));
        }
        return  returnMap;
    }
}
