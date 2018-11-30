package com.xmbl.ops.sessionConfig;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名:  MySessionContext 
 * @创建时间:  2018年9月6日 下午5:23:27
 * @修改时间:  2018年9月6日 下午5:23:27
 * @类说明:
 */
public class MySessionContext {
    
    private static HashMap<String,Object> sessionIdMap = new HashMap<String,Object>();
    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionIdMap.put(session.getId(), session);
        }
    }

    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionIdMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (sessionIdMap.containsKey(session_id)){
            return (HttpSession) sessionIdMap.get(session_id);
        }else{
            return null;
        }
    }
}
