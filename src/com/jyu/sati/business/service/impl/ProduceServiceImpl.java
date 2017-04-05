package com.jyu.sati.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.ProduceDao;
import com.jyu.sati.business.dao.ProduceImageDao;
import com.jyu.sati.business.service.ProduceService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.common.util.UploadUtil;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.Produce;
import com.jyu.sati.entity.ProduceImage;
import com.jyu.sati.vo.ProduceInfoVo;
import com.jyu.sati.vo.ProducePageVo;

/**
 * 产品成果服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class ProduceServiceImpl extends BaseServiceImpl implements ProduceService {

	@Autowired
	private ProduceDao produceDao;
	@Autowired
	private ProduceImageDao produceImageDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	private AuditInfoDao auditDao;

	@Transactional
	@Override
	public ProduceInfoVo createNewProduce() throws BusinessException {
		String userId = getCurrentUserId();
		ProduceInfoVo produceInfo = new ProduceInfoVo();
		try {
			Produce produce = new Produce();
			ContactInfo contact = contactDao.getContactInfoByUserId(userId);// 获取用户的联系信息
			contact.setContactId(null);// 置id为空
			contactDao.insertSelective(contact);// 生成新的联系人信息

			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_NO_AUDIT);
			auditInfo.setAuditType(AuditInfo.AUDIT_TYPE_PRODUCE);
			auditDao.insert(auditInfo);

			produce.setPublisherId(userId);// 设置发布人
			produce.setProduceStatus(Produce.PRODUCE_STATUS_NORMAL);// 设置产品成果状态
			produce.setPageView(0);// 设置浏览次数
			produce.setAuditInfoId(auditInfo.getAuditInfoId());
			produce.setContactInfoId(contact.getContactId());
			produceDao.insertSelective(produce);// 插入数据库获取id

			produceInfo = bindAuditInfo(produceInfo, auditInfo);
			produceInfo = bindContactInfo(produceInfo, contact);
			produceInfo = bindProduce(produceInfo, produce);

		} catch (Exception e) {
			errorLog("创建产品成果失败！", e);
			throw new BusinessException(getMsg());
		}
		return produceInfo;
	}

	/**
	 * 绑定联系人
	 * 
	 * @param produceInfo
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	private ProduceInfoVo bindContactInfo(ProduceInfoVo produceInfo, ContactInfo contact) throws BusinessException {
		if (contact != null) {
			produceInfo.setContactInfoId(contact.getContactId());
			produceInfo.setContactAddress(contact.getContactAddress());
			produceInfo.setContactBusiness(contact.getContactBusiness());
			produceInfo.setContactName(contact.getContactName());
			produceInfo.setPostcode(contact.getPostcode());
			produceInfo.setEmail(contact.getEmail());
			produceInfo.setContactUrl(contact.getContactUrl());
			produceInfo.setFaxNumber(contact.getFaxNumber());
			produceInfo.setQqormsnNumer(contact.getQqormsnNumer());
			produceInfo.setPhoneNumber(contact.getPhoneNumber());
			produceInfo.setContactNumber(contact.getContactNumber());
		}
		return produceInfo;
	}

	/**
	 * 绑定审核信息
	 * 
	 * @param produceInfo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private ProduceInfoVo bindAuditInfo(ProduceInfoVo produceInfo, AuditInfo auditInfo) throws BusinessException {
		if (auditInfo != null) {
			produceInfo.setAuditInfoId(auditInfo.getAuditInfoId());
			produceInfo.setSubmitTime(auditInfo.getSubmitTime());
			produceInfo.setAuditTime(auditInfo.getAuditTime());
			produceInfo.setAuditStatus(auditInfo.getAuditStatus());
			produceInfo.setAuditResult(auditInfo.getAuditResult());
			produceInfo.setUserId(auditInfo.getUserId());
		}
		return produceInfo;
	}

	/**
	 * 绑定产品成果信息
	 * 
	 * @param produceInfo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private ProduceInfoVo bindProduce(ProduceInfoVo produceInfo, Produce produce) throws BusinessException {
		if (produce != null) {
			produceInfo.setProduceId(produce.getProduceId());
			produceInfo.setProduceStatus(produce.getProduceStatus());
			produceInfo.setHomePageImageId(produce.getHomePageImageId());
			produceInfo.setPageView(produce.getPageView());
			produceInfo.setProduceDesc(produce.getProduceDesc());
			produceInfo.setProduceKey(produce.getProduceKey());
			produceInfo.setProduceName(produce.getProduceName());
			produceInfo.setPublisherId(produce.getPublisherId());
			produceInfo.setProduceTypeId(produce.getProduceTypeId());
			produceInfo.setPublishTime(new Date());
			produceInfo.setImages(produce.getImages());
			produceInfo.setIsRecommend(produce.getIsRecommend());
		}
		return produceInfo;
	}

	@Transactional
	@Override
	public void submitProduce(ProduceInfoVo produceInfo) throws BusinessException {
		try {
			try {
				if (produceInfo.getHomePageImageId() != null) {
					ProduceImage image = new ProduceImage();
					image.setProduceImageId(produceInfo.getHomePageImageId());
					image.setProduceImageType(ProduceImage.IMAGE_TYPE_SHOW_IN_HOMEPAGE);
					if (produceImageDao.updateImageTypeByImageIdAndType(image) < 1) {
						errorLog("指定首页展示的图片失败！");
						throw new BusinessException(getMsg());
					}
				} else {
					errorLog("请指定在首页展示的图片");
					throw new BusinessException(getMsg());
				}
			} catch (Exception e) {
				errorLog("指定在首页展示的图片失败！请联系管理员！");
				throw new BusinessException(getMsg());
			}
			// 更新产品成果信息
			updateProduce(produceInfo);
			// 更新联系人信息
			updateContactInfo(produceInfo);
			try {
				auditDao.updateProduceAuditStatus(produceInfo.getProduceId(), AuditInfo.AUDIT_STATUS_WAITING, null);
			} catch (Exception e) {
				errorLog("更新产品审核状态失败！", e);
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("提交审核失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新联系人
	 * 
	 * @param produceInfo
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	private void updateContactInfo(ProduceInfoVo produceInfo) throws BusinessException {
		try {
			ContactInfo contact = new ContactInfo();
			contact.setContactId(produceInfo.getContactInfoId());
			contact.setContactAddress(produceInfo.getContactAddress());
			contact.setContactBusiness(produceInfo.getContactBusiness());
			contact.setContactName(produceInfo.getContactName());
			contact.setPostcode(produceInfo.getPostcode());
			contact.setEmail(produceInfo.getEmail());
			contact.setContactUrl(produceInfo.getContactUrl());
			contact.setFaxNumber(produceInfo.getFaxNumber());
			contact.setQqormsnNumer(produceInfo.getQqormsnNumer());
			contact.setPhoneNumber(produceInfo.getPhoneNumber());
			contact.setContactNumber(produceInfo.getContactNumber());
			contactDao.updateByPrimaryKey(contact);
		} catch (Exception e) {
			errorLog("更新联系人失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新产品成果信息
	 * 
	 * @param produceInfo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateProduce(ProduceInfoVo produceInfo) throws BusinessException {
		try {
			Produce produce = new Produce();
			produce.setProduceId(produceInfo.getProduceId());
			produce.setProduceStatus(produceInfo.getProduceStatus());
			produce.setHomePageImageId(produceInfo.getHomePageImageId());
			produce.setPageView(produceInfo.getPageView());
			produce.setProduceDesc(produceInfo.getProduceDesc());
			produce.setProduceKey(produceInfo.getProduceKey());
			produce.setProduceName(produceInfo.getProduceName());
			produce.setPublisherId(produceInfo.getPublisherId());
			produce.setProduceTypeId(produceInfo.getProduceTypeId());
			produce.setPublishTime(new Date());
			produce.setIsRecommend(produceInfo.getIsRecommend());
			produceDao.updateByPrimaryKeySelective(produce);
		} catch (Exception e) {
			errorLog("更新产品失败", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional(readOnly = true)
	@Override
	public ProduceInfoVo getProduceInfoById(Integer produceId) throws BusinessException {
		ProduceInfoVo produceInfo = new ProduceInfoVo();
		Produce produce = null;
		ContactInfo contact = null;
		AuditInfo auditInfo = null;
		try {
			produce = produceDao.selectByPrimaryKey(produceId);
			try {
				List<ProduceImage> images = produceImageDao.getProduceImagesByProduceId(produceId);
				// 获取在首页展示的图片id
				for (ProduceImage image : images) {
					if (image.getProduceImageType() == ProduceImage.IMAGE_TYPE_SHOW_IN_HOMEPAGE) {
						produce.setHomePageImageId(image.getProduceImageId());
						break;
					}
				}
				produce.setImages(images);
			} catch (Exception e) {
				errorLog("获取产品成果图片失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				contactDao.selectByPrimaryKey(produce.getContactInfoId());
			} catch (Exception e) {
				errorLog("获取产品联系人信息失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				auditInfo = auditDao.selectByPrimaryKey(produce.getAuditInfoId());
			} catch (Exception e) {
				errorLog("获取产品审核信息失败！", e);
				throw new BusinessException(getMsg());
			}

			produceInfo = bindProduce(produceInfo, produce);// 绑定产品成果信息
			produceInfo = bindAuditInfo(produceInfo, auditInfo);// 绑定审核信息
			produceInfo = bindContactInfo(produceInfo, contact);// 绑定联系信息

		} catch (Exception e) {
			errorLog("获取产品成果失败！", e);
			throw new BusinessException(getMsg());
		}
		return produceInfo;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProduceImage> getAllProduceImagesByProduceId(Integer produceId) throws BusinessException {
		List<ProduceImage> images = new ArrayList<>();
		try {
			images = produceImageDao.getProduceImagesByProduceId(produceId);
		} catch (Exception e) {
			errorLog("获取产品成果图片失败！", e);
			throw new BusinessException(getMsg());
		}
		return images;
	}

	@Transactional
	@Override
	public List<ProduceImage> getAllProduceImagesByImageType(Integer imageType) throws BusinessException {
		List<ProduceImage> images = new ArrayList<>();
		try {
			images = produceImageDao.getProduceImagesByImageType(imageType);
		} catch (Exception e) {
			errorLog("获取产品成果图片失败！", e);
			throw new BusinessException(getMsg());
		}
		return images;
	}

	@Transactional
	@Override
	public ProduceImage getProduceImagesByProduceIdAndImageType(Integer produceId, Integer imageType) {
		ProduceImage image = new ProduceImage();
		try {
			image = produceImageDao.getProduceImagesByProduceIdAndImageType(produceId, imageType);
		} catch (Exception e) {
			errorLog("获取产品成果图片失败！", e);
			throw new BusinessException(getMsg());
		}
		return image;
	}

	@Transactional
	@Override
	public void removeProduce(Integer produceId) throws BusinessException {
		if (produceId == null) {
			throw new BusinessException("产品成果id为空！");
		}
		try {
			String publisherId = produceDao.getPublisherIdByProduceId(produceId);
			if (StringUtil.isEmpty(publisherId)) {
				throw new BusinessException("产品成果信息异常！");
			}
			if (!publisherId.equals(getCurrentUserId())) {
				throw new BusinessException("不是当前产品成果的发布人！");
			}
			Produce produce = produceDao.selectByPrimaryKey(produceId);
			// 删除产品成果
			produceDao.deleteByPrimaryKey(produceId);
			List<ProduceImage> images = produceImageDao.getProduceImagesByProduceId(produceId);
			// 删除产品成果图片
			produceImageDao.deleteAllProduceImagesByProduceId(produceId);// 删除指定产品的所有图排尿
			for (ProduceImage image : images) {
				// 删除文件
				UploadUtil.deleteFile(image.getProduceImageUrl());
			}
			// 删除产品成果审核信息
			try {
				if (auditDao.deleteByPrimaryKey(produce.getAuditInfoId()) < 1) {
					throw new BusinessException("删除产品成果审核信息失败！");
				}
			} catch (Exception e) {
				errorLog("删除产品成果审核信息失败！", e);
				throw new BusinessException(getMsg());
			}
			// 删除产品成果审核信息
			try {
				if (contactDao.deleteByPrimaryKey(produce.getContactInfoId()) < 1) {
					throw new BusinessException("删除产品成果联系人信息失败！");
				}
			} catch (Exception e) {
				errorLog("删除产品成果联系人信息失败！", e);
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("删除产品成果失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void updateProduceStatus(Integer produceId, Integer produceStatus) throws BusinessException {
		try {
			if (produceId != null && produceStatus != null) {
				Produce produce = new Produce();
				produce.setProduceId(produceId);
				produce.setProduceStatus(produceStatus);
				if (produceDao.updateProduceStatus(produce) < 1) {
					throw new BusinessException("没有产品成果被更新！");
				}
			} else {
				errorLog("产品id或状态为空！");
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("更改产品成果状态失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	@Transactional
	@Override
	public void updateIsRecommenedStatus(Integer produceId) throws BusinessException {
		try {
			if (produceId != null) {
				if (produceDao.updateIsRecommendStatus(produceId) < 1) {
					throw new BusinessException("没有产品成果被更新！");
				}
			} else {
				errorLog("产品id为空！");
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("更改是否推荐状态失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public List<Produce> getProducePageByCondition(ProducePageVo condition) throws BusinessException {
		List<Produce> produces = null;
		try {
			int totalNo = produceDao.getTotalNoByCondition(condition);
			condition.setTotalNo(totalNo);
			produces = produceDao.getProducePageByCondition(condition);
			// 加载图片数据
			for (int i = 0; i < produces.size(); i++) {
				List<ProduceImage> images = produceImageDao.getProduceImagesByProduceId(produces.get(i).getProduceId());
				produces.get(i).setImages(images);
			}
		} catch (Exception e) {
			errorLog("获取产品成果信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return produces;
	}

	@Transactional
	@Override
	public ProduceInfoVo getAuditProduceById(Integer produceId) throws BusinessException {
		ProduceInfoVo produceInfo = new ProduceInfoVo();
		try {
			produceInfo = getProduceInfoById(produceId);
			try {
				AuditInfo auditInfo = auditDao.getAuditInfoByProduceId(produceId);
				auditInfo.setAuditorId(getCurrentUserId());
				auditInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_AUDITING);
				auditDao.updateByPrimaryKeySelective(auditInfo);
			} catch (Exception e) {
				errorLog("更新审核状态失败！");
				throw new BusinessException(getMsg());
			}

		} catch (Exception e) {
			throw e;
		}
		return produceInfo;
	}

	@Override
	public void saveProduce(ProduceInfoVo produceInfo) throws BusinessException {
		try {
			try {
				if (produceInfo.getHomePageImageId() != null) {
					ProduceImage image = new ProduceImage();
					image.setProduceImageId(produceInfo.getHomePageImageId());
					image.setProduceImageType(ProduceImage.IMAGE_TYPE_SHOW_IN_HOMEPAGE);
					produceImageDao.updateImageTypeByImageIdAndType(image);// 清除之前的
				}
			} catch (Exception e) {
				errorLog("指定在首页展示的图片失败！请联系管理员！");
				throw new BusinessException(getMsg());
			}
			// 更新产品成果信息
			updateProduce(produceInfo);
			// 更新联系人信息
			updateContactInfo(produceInfo);
		} catch (Exception e) {
			errorLog("保存失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Override
	public Integer getProduceCountByAuditStatusAndPublisher(Integer auditStatus, String publisherId)
			throws BusinessException {
		Integer count = 0;
		try {
			count = produceDao.getProduceCountByAuditStatusAndPublisher(auditStatus, publisherId);
		} catch (Exception e) {
			errorLog("获取数据失败！");
			throw new BusinessException(getMsg());
		}
		return count;
	}

	@Override
	public Integer getProdcueCountByCondition(ProducePageVo condition) throws BusinessException {
		Integer count = 0;
		try {
			count = produceDao.getTotalNoByCondition(condition);
		} catch (Exception e) {
			errorLog("获取产品数目失败！");
			throw new BusinessException(getMsg());
		}
		return count;
	}

}
