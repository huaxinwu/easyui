/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.pojo;

import java.io.Serializable;

/**
 * 用户信息实体类
 * @author wxh
 * @version $Id: User.java, v 0.1 2017年11月8日 上午11:16:19 wxh Exp $
 */
public class User implements Serializable {

    /** */
    private static final long serialVersionUID = -4915491347902360020L;

    private Long              uId;
    private String            userName;
    private String            password;
    private String            userSex;
    private Long              userAge;
    private String            education;
    private String            address;

    public User() {

    }

    /**
     * @param uId
     * @param userName
     * @param password
     * @param userSex
     * @param userAge
     * @param education
     * @param address
     */
    public User(Long uId, String userName, String password, String userSex, Long userAge,
                String education, String address) {
        this.uId = uId;
        this.userName = userName;
        this.password = password;
        this.userSex = userSex;
        this.userAge = userAge;
        this.education = education;
        this.address = address;
    }

    /**
     * Getter method for property <tt>uId</tt>.
     * 
     * @return property value of uId
     */
    public Long getuId() {
        return uId;
    }

    /**
     * Setter method for property <tt>uId</tt>.
     * 
     * @param uId value to be assigned to property uId
     */
    public void setuId(Long uId) {
        this.uId = uId;
    }

    /**
     * Getter method for property <tt>userName</tt>.
     * 
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * 
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>userSex</tt>.
     * 
     * @return property value of userSex
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * Setter method for property <tt>userSex</tt>.
     * 
     * @param userSex value to be assigned to property userSex
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * Getter method for property <tt>userAge</tt>.
     * 
     * @return property value of userAge
     */
    public Long getUserAge() {
        return userAge;
    }

    /**
     * Setter method for property <tt>userAge</tt>.
     * 
     * @param userAge value to be assigned to property userAge
     */
    public void setUserAge(Long userAge) {
        this.userAge = userAge;
    }

    /**
     * Getter method for property <tt>education</tt>.
     * 
     * @return property value of education
     */
    public String getEducation() {
        return education;
    }

    /**
     * Setter method for property <tt>education</tt>.
     * 
     * @param education value to be assigned to property education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * Getter method for property <tt>address</tt>.
     * 
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     * 
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** 
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((education == null) ? 0 : education.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((uId == null) ? 0 : uId.hashCode());
        result = prime * result + ((userAge == null) ? 0 : userAge.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userSex == null) ? 0 : userSex.hashCode());
        return result;
    }

    /** 
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (education == null) {
            if (other.education != null)
                return false;
        } else if (!education.equals(other.education))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (uId == null) {
            if (other.uId != null)
                return false;
        } else if (!uId.equals(other.uId))
            return false;
        if (userAge == null) {
            if (other.userAge != null)
                return false;
        } else if (!userAge.equals(other.userAge))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userSex == null) {
            if (other.userSex != null)
                return false;
        } else if (!userSex.equals(other.userSex))
            return false;
        return true;
    }

    /** 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [uId=" + uId + ", userName=" + userName + ", password=" + password
               + ", userSex=" + userSex + ", userAge=" + userAge + ", education=" + education
               + ", address=" + address + "]";
    }

}
