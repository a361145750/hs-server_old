package com.hs.system.model;

import java.util.Date;

/**
 * Created by work_tl on 2016/4/4.
 */
public class User {

    private long user_id;
    private String login_id;
    private String user_name;
    private String pass_word;
    private String creat_by;
    private Date creat_date;
    private String update_by;
    private Date update_date;
    private long role;
    private long user_right;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getCreat_by() {
        return creat_by;
    }

    public void setCreat_by(String creat_by) {
        this.creat_by = creat_by;
    }

    public Date getCreat_date() {
        return creat_date;
    }

    public void setCreat_date(Date creat_date) {
        this.creat_date = creat_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public long getUser_right() {
        return user_right;
    }

    public void setUser_right(long user_right) {
        this.user_right = user_right;
    }
}
