package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.DocumentDao;
import com.jyu.sati.business.dao.PersonDao;
import com.jyu.sati.business.dao.PersonInfoDao;
import com.jyu.sati.business.dao.ProduceImageDao;
import com.jyu.sati.business.dao.TechEnclosureDao;
import com.jyu.sati.business.dao.TechSupplyInfoDao;
import com.jyu.sati.business.dao.UnitBaseInfoDao;
import com.jyu.sati.business.dao.UserDao;
import com.jyu.sati.business.service.UploadService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.common.util.UploadUtil;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.Document;
import com.jyu.sati.entity.ProduceImage;
import com.jyu.sati.entity.TechEnclosure;
import com.jyu.sati.entity.User;
import com.jyu.sati.vo.UploadFileVo;

import javafx.scene.image.Image;

/**
 * 上传服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class UploadServiceImpl extends BaseServiceImpl implements UploadService {

	@Autowired
	private PersonInfoDao personInfoDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UnitBaseInfoDao ubiDao;
	@Autowired
	private ProduceImageDao produceImageDao;
	@Autowired
	private AuditInfoDao auditDao;
	@Autowired
	private TechEnclosureDao techEnclosureDao;
	@Autowired
	private TechSupplyInfoDao techSupplyDao;

	@Transactional
	@Override
	public void uploadPersonImage(MultipartFile file, String userId, int uploadType) throws BusinessException {
		String imageUrl = "";
		try {
			// 保存图片
			Document image = null;
			boolean isImageExist = true;
			// 从数据中获取现有的数据
			if (uploadType == 1) {
				image = documentDao.getPersonImageByUserId(userId);
			} else if (uploadType == 2) {
				image = documentDao.getIdCardImageByUserId(userId);
			}
			// 数据库不存在数据，创建新的
			if (image == null) {
				image = new Document();
				isImageExist = false;
			} else {
				if (StringUtil.isNotEmpty(image.getDocumentUrl())) {
					// 文件已存在，删除现有文件
					UploadUtil.deleteFile(image.getDocumentUrl());
				}
			}
			// 写入图片文件
			if (uploadType == 1) {
				imageUrl = UploadUtil.saveFiles(file, Document.DUCUMENT_TYPE_PERSON_IMAGE, userId);
				image.setDocumentDesc("【" + userId + "】的个人照片");
				image.setDocumentType(Document.DUCUMENT_TYPE_PERSON_IMAGE);
			} else if (uploadType == 2) {
				imageUrl = UploadUtil.saveFiles(file, Document.DUCUMENT_TYPE_ID_CARD_IMAGE, userId);
				image.setDocumentDesc("【" + userId + "】的身份证照片");
				image.setDocumentType(Document.DUCUMENT_TYPE_ID_CARD_IMAGE);
			}
			// 保存文件url到数据库
			image.setDocumentUrl(imageUrl);

			int result = -1;
			if (isImageExist) {
				result = documentDao.updateByPrimaryKeySelective(image);
			} else {
				result = documentDao.insert(image);
			}

			if (result > 0) {
				int result2 = -1;
				Integer imageId = image.getDocumentId();
				if (uploadType == 1) {
					result2 = personInfoDao.bindImage(userId, null, imageId);// 绑定IDCardS图片
				} else if (uploadType == 2) {
					result2 = personInfoDao.bindImage(userId, imageId, null);// 绑定个人照片图片
				}
				// 判断是否绑定成功
				if (result2 < 1) {
					// UploadUtil.deleteFile(imageUrl);// 删除上传的文件
					errorLog("保存数据到数据库失败1！", image.toString());
					throw new BusinessException(getMsg());
				}
				// 更新用户审信息为等待审核状态
				auditDao.updateUserInfoAuditStatus(getCurrentUserId(), AuditInfo.AUDIT_STATUS_WAITING, "");
			} else {
				// UploadUtil.deleteFile(imageUrl);// 删除上传的文件
				errorLog("保存数据到数据库失败2！", image.toString());
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			UploadUtil.deleteFile(imageUrl);// 删除上传的文件
			errorLog("保存数据到数据库失败3！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void uploadEnclosure(MultipartFile file, String userId) throws BusinessException {

		Document enclosure = null;
		boolean isEnclosureExsit = true;
		String savaUrl = "";
		try {
			// 写入文件并获取路径
			savaUrl = UploadUtil.saveFiles(file, Document.DUCUMENT_TYPE_ENCLOSURE, userId);
			enclosure = documentDao.getEnclosureByUserId(userId);
			if (enclosure == null) {
				isEnclosureExsit = false;
				enclosure = new Document();
				enclosure.setDocumentDesc("【" + userId + "】的附件");
				enclosure.setDocumentType(Document.DUCUMENT_TYPE_ENCLOSURE);
			} else {
				// 删除旧文件
				if (StringUtil.isNotEmpty(enclosure.getDocumentUrl())) {
					UploadUtil.deleteFile(enclosure.getDocumentUrl());
				}
			}
			enclosure.setDocumentUrl(savaUrl);
			int result = -1;
			if (isEnclosureExsit) {
				result = documentDao.updateByPrimaryKeySelective(enclosure);
			} else {
				result = documentDao.insertSelective(enclosure);
			}
			if (result < 1) {
				errorLog("插入附件数据失败！", enclosure.toString());
				throw new BusinessException(getMsg());
			}
			// 绑定附件信息
			int result2 = personDao.bindEnclosureForPerson(userId, enclosure.getDocumentId());
			if (result2 < 1) {
				errorLog("绑定附件数据失败！", enclosure.toString());
				throw new BusinessException(getMsg());
			}
			// 更新用户审信息为未审核状态
			auditDao.updateUserInfoAuditStatus(getCurrentUserId(), AuditInfo.AUDIT_STATUS_NO_AUDIT, "");
		} catch (Exception e) {
			UploadUtil.deleteFile(savaUrl);// 删除上传的文件
			errorLog("上传附件失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void uploadUnitCodeImage(MultipartFile file, String userId) throws BusinessException {

		String savaUrl = "";
		try {
			// 根据用户账号获取用户类型
			int userType = userDao.getUserTypeByUserId(userId);
			// 判断用户类型是否是单位类型
			if (userType == User.USER_TYPE_AGENCY || userType == User.USER_TYPE_COLLEGE
					|| userType == User.USER_TYPE_SCIENTIFIC || userType == User.USER_TYPE_COMPANY) {

				// 写入文件并获取文件保存路径
				savaUrl = UploadUtil.saveFiles(file, Document.DUCUMENT_TYPE_UNIT_CODE_IMAGE, userId);

				int result = -1;
				// 绑定文件信息
				switch (userType) {
				case User.USER_TYPE_AGENCY:
					try {
						result = ubiDao.updateAgencyUnitCodeImage(userId, savaUrl);
					} catch (Exception e) {
						errorLog("上传中介机构单位机构代码图片失败！", e);
						throw new BusinessException(getMsg());
					}
					break;
				case User.USER_TYPE_COMPANY:
					// 企业用户类型
					try {
						result = ubiDao.updateCompanyUnitCodeImage(userId, savaUrl);
					} catch (Exception e) {
						errorLog("上传企业用户单位机构代码图片失败！", e);
						throw new BusinessException(getMsg());
					}
					break;
				case User.USER_TYPE_SCIENTIFIC:
					// 科研单位类型
					try {
						result = ubiDao.updateScientifyUnitCodeImage(userId, savaUrl);
					} catch (Exception e) {
						errorLog("上传科研单位用户单位机构代码图片失败！", e);
						throw new BusinessException(getMsg());
					}
					break;
				case User.USER_TYPE_COLLEGE:
					// 高校用户类型
					try {
						result = ubiDao.updateCollegeUnitCodeImage(userId, savaUrl);
					} catch (Exception e) {
						errorLog("上传科研单位用户单位机构代码图片失败！", e);
						throw new BusinessException(getMsg());
					}
					break;
				default:
					errorLog("当前用户不能上传该类型数据！");
					throw new BusinessException(getMsg());
				}
				if (result < 1) {
					throw new BusinessException("更新数据失败！");
				}
			} else {
				throw new BusinessException("该用户类型不能进行该操作！");
			}

			// 更新用户审信息为未审核状态
			auditDao.updateUserInfoAuditStatus(getCurrentUserId(), AuditInfo.AUDIT_STATUS_WAITING, "");
		} catch (Exception e) {
			UploadUtil.deleteFile(savaUrl);// 删除上传失败的文件
			errorLog("上传单位组织机构代码图片失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void uploadHomePageImage(MultipartFile file) throws BusinessException {
		Document homeImage = null;
		String savaUrl = "";
		try {
			// 写入文件并获取文件保存路径
			savaUrl = UploadUtil.saveFiles(file, Document.DUCUMENT_TYPE_HOME_PAGE_IMAGE, null);

			homeImage = new Document();
			homeImage.setDocumentDesc("主页宣传图片");
			homeImage.setDocumentUrl(savaUrl);
			homeImage.setDocumentType(Document.DUCUMENT_TYPE_HOME_PAGE_IMAGE);

			int result = -1;
			result = documentDao.insertSelective(homeImage);
			if (result < 1) {
				errorLog("插入单位组织机构代码图片数据失败！", homeImage.toString());
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			UploadUtil.deleteFile(savaUrl);// 删除上传失败的文件
			errorLog("上传主页宣传图片失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void uploadProduceImage(UploadFileVo fileVo) throws BusinessException {
		String imageUrl = null;
		try {
			try {
				imageUrl = UploadUtil.saveFiles(fileVo.getFile(), ProduceImage.PRODUCE_IMAGE, getCurrentUserId());
			} catch (Exception e) {
				errorLog("写入文件失败！", e);
				throw new BusinessException(getMsg());
			}

			ProduceImage image = new ProduceImage();
			image.setProduceId(fileVo.getBindId());
			image.setProduceImageUrl(imageUrl);
			image.setProduceImageDesc(fileVo.getDesc());
			image.setProduceImageType(ProduceImage.IMAGE_TYPE_GENERAL);
			produceImageDao.insert(image);
		} catch (Exception e) {
			UploadUtil.deleteFile(imageUrl);// 删除上传失败的文件
			errorLog("上传产品成果图片失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void uploadTechSupplyImageEnclosure(UploadFileVo fileVo) throws BusinessException {
		String imageUrl = null;
		try {
			try {
				imageUrl = UploadUtil.saveFiles(fileVo.getFile(), TechEnclosure.ENCLOSURE_TYPE_IMAGE,
						getCurrentUserId());
			} catch (Exception e) {
				errorLog("写入文件失败！", e);
				throw new BusinessException(getMsg());
			}
			Integer tsiId = fileVo.getBindId();
			if (tsiId == null) {
				errorLog("技术供给信息id为空！");
				throw new BusinessException(getMsg());
			}

			TechEnclosure image = techEnclosureDao.getImageEnclosureByTsiId(fileVo.getBindId());
			// 判断数据库是否存在图片附加信息
			if (image == null) {
				image = new TechEnclosure();
				image.setEnclosureDesc(fileVo.getDesc());
				image.setEnclosureUrl(imageUrl);
				image.setEnclosureType(TechEnclosure.ENCLOSURE_TYPE_IMAGE);
				try {
					techEnclosureDao.insert(image);// 插入数据获取id
				} catch (Exception e) {
					errorLog("插入图片附件信息到数据库失败！", e);
					throw new BusinessException(getMsg());
				}

				try {
					// 绑定技术供给信息
					if (techSupplyDao.updateSupplyImageEnclosure(tsiId, image.getEnclosureId()) < 1) {
						throw new BusinessException("更新数据失败！");
					}
				} catch (Exception e) {
					errorLog("绑定图片附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
			} else {
				// 已存在图片附件信息
				String oldUrl = image.getEnclosureUrl();
				image.setEnclosureUrl(imageUrl);
				image.setEnclosureDesc(fileVo.getDesc());
				try {
					techEnclosureDao.updateByPrimaryKeySelective(image);
				} catch (Exception e) {
					errorLog("更新图片附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
				// 上传成功，删除就旧文件
				UploadUtil.deleteFile(oldUrl);
			}

		} catch (Exception e) {
			UploadUtil.deleteFile(imageUrl);// 删除上传失败的文件
			errorLog("上传技术供给图片附件失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void uploadTechSupplyTextEnclosure(UploadFileVo fileVo) throws BusinessException {
		String textUrl = null;
		try {
			try {
				textUrl = UploadUtil.saveFiles(fileVo.getFile(), TechEnclosure.ENCLOSURE_TYPE_TEXT, getCurrentUserId());
			} catch (Exception e) {
				errorLog("写入文件失败！", e);
				throw new BusinessException(getMsg());
			}
			Integer tsiId = fileVo.getBindId();
			if (tsiId == null) {
				errorLog("技术供给信息id为空！");
				throw new BusinessException(getMsg());
			}

			TechEnclosure text = techEnclosureDao.getTextEnclosureByTsiId(fileVo.getBindId());
			// 判断数据库是否存在文本附加信息
			if (text == null) {
				text = new TechEnclosure();
				text.setEnclosureDesc(fileVo.getDesc());
				text.setEnclosureUrl(textUrl);
				text.setEnclosureType(TechEnclosure.ENCLOSURE_TYPE_TEXT);
				try {
					techEnclosureDao.insert(text);// 插入数据获取id
				} catch (Exception e) {
					errorLog("插入文本附件信息到数据库失败！", e);
					throw new BusinessException(getMsg());
				}

				try {
					// 绑定技术供给信息
					techSupplyDao.updateSupplyTextEnclosure(tsiId, text.getEnclosureId());
				} catch (Exception e) {
					errorLog("绑定文本附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
			} else {
				// 已存在文本附件信息
				String oldUrl = text.getEnclosureUrl();
				text.setEnclosureUrl(textUrl);
				text.setEnclosureDesc(fileVo.getDesc());
				try {
					techEnclosureDao.updateByPrimaryKeySelective(text);
				} catch (Exception e) {
					errorLog("更新文本附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
				// 上传成功，删除就旧文件
				UploadUtil.deleteFile(oldUrl);
			}

		} catch (Exception e) {
			UploadUtil.deleteFile(textUrl);// 删除上传失败的文件
			errorLog("上传技术供给文本附件失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	@Transactional
	@Override
	public void uploadTechSupplyVideoEnclosure(UploadFileVo fileVo) throws BusinessException {
		String videoUrl = null;
		try {
			try {
				videoUrl = UploadUtil.saveFiles(fileVo.getFile(), TechEnclosure.ENCLOSURE_TYPE_VIDEO,
						getCurrentUserId());
			} catch (Exception e) {
				errorLog("写入文件失败！", e);
				throw new BusinessException(getMsg());
			}
			Integer tsiId = fileVo.getBindId();
			if (tsiId == null) {
				errorLog("技术供给信息id为空！");
				throw new BusinessException(getMsg());
			}

			TechEnclosure video = techEnclosureDao.getVideoEnclosureByTsiId(fileVo.getBindId());
			// 判断数据库是否存在视频附加信息
			if (video == null) {
				video = new TechEnclosure();
				video.setEnclosureDesc(fileVo.getDesc());
				video.setEnclosureUrl(videoUrl);
				video.setEnclosureType(TechEnclosure.ENCLOSURE_TYPE_VIDEO);
				try {
					techEnclosureDao.insert(video);// 插入数据获取id
				} catch (Exception e) {
					errorLog("插入视频附件信息到数据库失败！", e);
					throw new BusinessException(getMsg());
				}

				try {
					// 绑定技术供给信息
					techSupplyDao.updateSupplyVideoEnclosure(tsiId, video.getEnclosureId());
				} catch (Exception e) {
					errorLog("绑定视频附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
			} else {
				// 已存在视频附件信息
				String oldUrl = video.getEnclosureUrl();
				video.setEnclosureUrl(videoUrl);
				video.setEnclosureDesc(fileVo.getDesc());
				try {
					techEnclosureDao.updateByPrimaryKeySelective(video);
				} catch (Exception e) {
					errorLog("更新是视频附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
				// 上传成功，删除就旧文件
				UploadUtil.deleteFile(oldUrl);
			}

		} catch (Exception e) {
			UploadUtil.deleteFile(videoUrl);// 删除上传失败的文件
			errorLog("上传技术供给视频附件失败！", e);
			throw new BusinessException(getMsg());
		}

	}

}
