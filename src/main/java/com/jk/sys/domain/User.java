package com.jk.sys.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//用户实体
public class User implements UserDetails {

    private static final long serialVersionUID = -1L;

    //用户数据id
    private Long id;
    //用户头像
    private String avatar;
    //用户账号
    private  String account;
    //用户密码
    private  String password;
    //MD5密码加密撒盐
    private  String salt;
    //用户名称
    private  String name;
    //用户生日
    private  String birthday;
    //用户性别(1:男 2：女)
    private  Integer sex;
    //用户邮箱
    private  String email;
    //用户手机
    private  String phone;
    //用户角色id
    private  Integer roleid;
    //用户部门id
    private  Integer deptid;
    //用户状态状态(1：启用  2：冻结  3：删除）
    private Integer status;
    //用户创建时间
    private String createtime;
    //用户表，保留字段
    private  Integer version;

    HashSet<SimpleGrantedAuthority> authorities;

    public User(Long id, String account,String name, String password, HashSet<SimpleGrantedAuthority> singleton) {
        this.id = id;
    	this.account = account;
        this.name = name;
        this.password = password;
        this.authorities = singleton;
    }

    public User(String username) {
        this.account = username;
    }

    public User() {

    }

    //Set Get方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    //toString方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roleid=" + roleid +
                ", deptid=" + deptid +
                ", status=" + status +
                ", createtime='" + createtime + '\'' +
                ", version=" + version +
                '}';
       }
}


