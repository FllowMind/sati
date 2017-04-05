package com.jyu.sati.entity;

/**
 * 上传文件实体类
 * 
 * @author 淋雨又调皮
 *
 */
public class Document {

	public final static int DUCUMENT_TYPE_ID_CARD_IMAGE = 1;// 身份证图片类型文件
	public final static int DUCUMENT_TYPE_PERSON_IMAGE = 2;// 个人照片类型文件
	public final static int DUCUMENT_TYPE_ENCLOSURE = 3;// 附件文件类型
	public final static int DUCUMENT_TYPE_UNIT_CODE_IMAGE = 4;// 单位机构代码图片或统一社会信用类型文件
	public final static int DUCUMENT_TYPE_HOME_PAGE_IMAGE = 5;// 主页宣传图片类型

	private Integer documentId;// 文件iD
	private String documentUrl;// 文件存路径
	private String documentDesc;// 文件描述
	private Integer documentType;// 文件类型

	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public String getDocumentDesc() {
		return documentDesc;
	}

	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}

	public Integer getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}

	@Override
	public String toString() {
		return "Document [documentId=" + documentId + ", documentUrl=" + documentUrl + ", documentDesc=" + documentDesc
				+ ", documentType=" + documentType + "]";
	}

}