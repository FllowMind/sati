package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyu.sati.business.dao.DocumentDao;
import com.jyu.sati.business.service.DocumentService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Document;

@Service
public class DocumentServiceImpl extends BaseServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao docuDao;

	@Override
	public int delete(Integer documentId) throws BusinessException {

		int result = -1;
		try {
			result = docuDao.deleteByPrimaryKey(documentId);
		} catch (Exception e) {
			errorLog("删除文件数据失败", documentId.toString(), e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Override
	public int insert(Document record) throws BusinessException {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			result = docuDao.insert(record);
		} catch (Exception e) {
			errorLog("插入数据失败!", record.toString(), e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Override
	public int insertSelective(Document record) throws BusinessException {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			result = docuDao.insertSelective(record);
		} catch (Exception e) {
			errorLog("插入数据失败!", record.toString(), e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Override
	public Document select(Integer documentId) throws BusinessException {
		Document document = null;
		try {
			document = docuDao.selectByPrimaryKey(documentId);
		} catch (Exception e) {
			errorLog("获取文件数据失败!", +documentId + "", e);
			throw new BusinessException(getMsg());
		}
		return document;
	}

	@Override
	public int updateSelective(Document record) throws BusinessException {
		int result = -1;
		try {
			result = docuDao.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			errorLog("更新文件数据失败！", record.toString(), e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Override
	public int update(Document record) throws BusinessException {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			result = docuDao.updateByPrimaryKey(record);
		} catch (Exception e) {
			errorLog("更新文件数据失败！", record.toString(), e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Override
	public Document getIdCardImageByUserId(String userId) throws BusinessException {
		Document idCardImage = null;
		try {
			idCardImage = docuDao.getIdCardImageByUserId(userId);
		} catch (Exception e) {
			errorLog("获取身份证照片数据失败！", userId, e);
			throw new BusinessException(getMsg());
		}
		return idCardImage;
	}

	@Override
	public Document getPersonImageByUserId(String userId) throws BusinessException {
		Document personImage = null;
		try {
			personImage = docuDao.getPersonImageByUserId(userId);
		} catch (Exception e) {
			errorLog("获取个人照片数据失败！", userId, e);
			throw new BusinessException(getMsg());
		}
		return personImage;
	}

	@Override
	public Document getEnclosureByUserId(String userId) throws BusinessException {
		// TODO Auto-generated method stub
		Document enclosure = null;
		try {
			enclosure = docuDao.getEnclosureByUserId(userId);
		} catch (Exception e) {
			errorLog("获取附件数据失败！", userId, e);
			throw new BusinessException(getMsg());
		}
		return enclosure;
	}

}
