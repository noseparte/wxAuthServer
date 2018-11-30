package com.xmbl.ops.sessionConfig;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名:  MySessionListener 
 * @创建时间:  2018年9月6日 下午5:22:54
 * @修改时间:  2018年9月6日 下午5:22:54
 * @类说明:
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        MySessionContext.addSession(httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        MySessionContext.delSession(session);
    }
}