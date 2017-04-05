package com.jyu.sati.entity;

public class ContactInfo {
	
    private Integer contactId;//联系人ID
    private String contactName;//联系人名字
    private String contactBusiness;//联系人职务
    private String contactNumber;//联系电话
    private String contactAddress;//联系地址
    private String postcode;//邮编
    private String email;//联系人邮箱
    private String contactUrl;//网址
    private String faxNumber;//传真
    private String qqormsnNumer;//联系人的QQ或MSN号码
    private String phoneNumber;//手机号码

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactBusiness() {
        return contactBusiness;
    }

    public void setContactBusiness(String contactBusiness) {
        this.contactBusiness = contactBusiness;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getQqormsnNumer() {
        return qqormsnNumer;
    }

    public void setQqormsnNumer(String qqormsnNumer) {
        this.qqormsnNumer = qqormsnNumer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	@Override
	public String toString() {
		return "ContactInfo [contactId=" + contactId + ", contactName=" + contactName + ", contactBusiness="
				+ contactBusiness + ", contactNumber=" + contactNumber + ", contactAddress=" + contactAddress
				+ ", postcode=" + postcode + ", email=" + email + ", contactUrl=" + contactUrl + ", faxNumber="
				+ faxNumber + ", qqormsnNumer=" + qqormsnNumer + ", phoneNumber=" + phoneNumber + "]";
	}
    
    
}