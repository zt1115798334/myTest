package com.zt.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_userbase", schema = "jdjr", catalog = "")
public class Userbase {
    private long id;
    private String userAccount;
    private String cName;
    private String phone;
    private String cPassword;
    private String salt;
    private String businessType;
    private Timestamp createdDate;
    private String createdUser;
    private Timestamp modifiedDate;
    private String modifiedUser;
    private String keywordWarningState;
    private String thresholdWarningState;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_account")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "c_name")
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "c_password")
    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_user")
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Basic
    @Column(name = "modified_date")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Basic
    @Column(name = "modified_user")
    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @Basic
    @Column(name = "keyword_warning_state")
    public String getKeywordWarningState() {
        return keywordWarningState;
    }

    public void setKeywordWarningState(String keywordWarningState) {
        this.keywordWarningState = keywordWarningState;
    }

    @Basic
    @Column(name = "threshold_warning_state")
    public String getThresholdWarningState() {
        return thresholdWarningState;
    }

    public void setThresholdWarningState(String thresholdWarningState) {
        this.thresholdWarningState = thresholdWarningState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userbase userbase = (Userbase) o;

        if (id != userbase.id) return false;
        if (userAccount != null ? !userAccount.equals(userbase.userAccount) : userbase.userAccount != null)
            return false;
        if (cName != null ? !cName.equals(userbase.cName) : userbase.cName != null) return false;
        if (phone != null ? !phone.equals(userbase.phone) : userbase.phone != null) return false;
        if (cPassword != null ? !cPassword.equals(userbase.cPassword) : userbase.cPassword != null) return false;
        if (salt != null ? !salt.equals(userbase.salt) : userbase.salt != null) return false;
        if (businessType != null ? !businessType.equals(userbase.businessType) : userbase.businessType != null)
            return false;
        if (createdDate != null ? !createdDate.equals(userbase.createdDate) : userbase.createdDate != null)
            return false;
        if (createdUser != null ? !createdUser.equals(userbase.createdUser) : userbase.createdUser != null)
            return false;
        if (modifiedDate != null ? !modifiedDate.equals(userbase.modifiedDate) : userbase.modifiedDate != null)
            return false;
        if (modifiedUser != null ? !modifiedUser.equals(userbase.modifiedUser) : userbase.modifiedUser != null)
            return false;
        if (keywordWarningState != null ? !keywordWarningState.equals(userbase.keywordWarningState) : userbase.keywordWarningState != null)
            return false;
        if (thresholdWarningState != null ? !thresholdWarningState.equals(userbase.thresholdWarningState) : userbase.thresholdWarningState != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (cPassword != null ? cPassword.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifiedUser != null ? modifiedUser.hashCode() : 0);
        result = 31 * result + (keywordWarningState != null ? keywordWarningState.hashCode() : 0);
        result = 31 * result + (thresholdWarningState != null ? thresholdWarningState.hashCode() : 0);
        return result;
    }
}
