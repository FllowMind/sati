package com.jyu.sati.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.InfoDao;
import com.jyu.sati.business.service.InfoService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.util.DocUtil;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.common.util.UploadUtil;
import com.jyu.sati.entity.Info;
import com.jyu.sati.vo.InfoPageVo;
import com.jyu.sati.vo.UploadFileVo;

/**
 * 政策法规和通知公告的服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class InfoServiceImpl extends BaseServiceImpl implements InfoService {

	@Autowired
	private InfoDao infoDao;

	@Transactional
	@Override
	public Info createNewInfo(Integer infoType) throws BusinessException {
		if (infoType == null) {
			errorLog("信息类型不能为空！");
			throw new BusinessException(getMsg());
		}
		if (infoType != Info.INFO_TYPE_NOTICE && infoType != Info.INFO_TYPE_POLICY) {
			errorLog("信息类型异常！");
			throw new BusinessException(getMsg());
		}
		Info info = new Info();
		try {
			info.setInfoType(infoType);
			info.setInfoStatus(Info.INFO_STATUS_UNPUBLISH);// 未发布状态
			infoDao.insertSelective(info);
		} catch (Exception e) {
			if (infoType == Info.INFO_TYPE_NOTICE) {
				errorLog("创建新的通知公告失败！", e);
			} else {
				errorLog("创建新的政策法规失败！", e);
			}
			throw new BusinessException(getMsg());
		}
		return info;
	}

	@Transactional
	@Override
	public List<Info> getInfoPageByCondition(InfoPageVo condition) throws BusinessException {
		if (condition == null) {
			errorLog("传入参数为空！");
			throw new BusinessException(getMsg());
		}
		List<Info> infos = null;
		try {
			condition.setTotalNo(infoDao.getTotalNoByCondition(condition));
			infos = infoDao.getInfoPageByCondition(condition);
		} catch (Exception e) {
			errorLog("获取数据失败！", e);
			throw new BusinessException(getMsg());
		}
		return infos;
	}

	@Transactional
	@Override
	public Info getInfoByInfoId(Integer infoId) throws BusinessException {
		if (infoId == null) {
			errorLog("传入参数为空！");
			throw new BusinessException(getMsg());
		}
		Info info = null;
		try {
			info = infoDao.selectByPrimaryKey(infoId);
		} catch (Exception e) {
			errorLog("获取数据失败！", e);
			throw new BusinessException(getMsg());
		}
		return info;
	}

	@Transactional
	@Override
	public void updateInfoStatusById(Integer infoId) throws BusinessException {
		if (infoId == null) {
			errorLog("传入参数为空！");
			throw new BusinessException(getMsg());
		}
		try {
			infoDao.updateInfoStatus(infoId);
		} catch (Exception e) {
			errorLog("更新状态失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	@Transactional
	@Override
	public void publishInfo(Info info) throws BusinessException {
		if (info == null) {
			errorLog("传入参数为空！");
			throw new BusinessException(getMsg());
		}
		try {
			info.setInfoStatus(Info.INFO_STATUS_PUBLISH);// 更改状态为发布状态
			info.setPublishTime(new Date());
			// 判断发布人是否为空
			if (StringUtil.isEmpty(info.getPublisherId())) {
				info.setPublisherId(getCurrentUserId());
			}
			if (infoDao.updateByPrimaryKeySelective(info) < 1) {
				throw new BusinessException("更新数据失败！");
			}
		} catch (Exception e) {
			errorLog("发布信息失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	@Transactional
	@Override
	public void saveInfo(Info info) throws BusinessException {
		if (info == null) {
			errorLog("传入参数为空！");
			throw new BusinessException(getMsg());
		}
		try {
			info.setInfoStatus(Info.INFO_STATUS_UNPUBLISH);// 更改状态为未发布状态
			if (infoDao.updateByPrimaryKeySelective(info) < 1) {
				throw new BusinessException("更新数据失败！");
			}
		} catch (Exception e) {
			errorLog("保存信息失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void uploadFile(UploadFileVo fileVo) throws BusinessException {
		if (fileVo.getBindId() == null && StringUtil.isEmpty(fileVo.getDesc())) {
			errorLog("传入参数为空！");
			throw new BusinessException(getMsg());
		}
		String saveUrl = null;
		String content = null;
		try {
			// 写入文件
			try {
				Integer infoType = null;
				Info info = null;
				try {
					info = infoDao.selectByPrimaryKey(fileVo.getBindId());
					if (info == null) {
						throw new BusinessException("获取信息为空！");
					}
					infoType = info.getInfoType();
				} catch (Exception e) {
					errorLog("获取信息失败！");
					throw new BusinessException(getMsg());
				}
				if (StringUtil.isNotEmpty(info.getFileUrl())) {
					try {
						String oldurl = info.getFileUrl();
						oldurl = oldurl.substring(0, oldurl.lastIndexOf("/"));
						// 删除旧文件
						UploadUtil.deleteDirectory(oldurl);

					} catch (Exception e) {
						errorLog("删除旧文件失败！");
						throw new BusinessException(getMsg());
					}
				}
				if (infoType == Info.INFO_TYPE_NOTICE) {
					saveUrl = UploadUtil.saveFiles(fileVo.getFile(), Info.UPLOAD_INFO_TYPE_NOTICE, getCurrentUserId());
				} else {
					saveUrl = UploadUtil.saveFiles(fileVo.getFile(), Info.UPLOAD_INFO_TYPE_POLICY, getCurrentUserId());
				}
			} catch (Exception e) {
				errorLog("写入文件失败！", e);
				throw new BusinessException(getMsg());
			}
			// 将文件内容转化成html格式保存到数据库
			try {
				String wordUrl = UploadUtil.getRootPath() + saveUrl;// 保存在本地的文件绝对路径
				content = DocUtil.word2html(wordUrl);
			} catch (Exception e) {
				errorLog("转化html失败！", e);
				throw new BusinessException(getMsg());
			}
			// 更新数据库
			try {
				Info info = new Info();
				info.setInfoId(fileVo.getBindId());
				info.setFileDesc(fileVo.getDesc());
				info.setFileUrl(saveUrl);
				info.setInfoContent(content);
				infoDao.updateByPrimaryKeySelective(info);
			} catch (Exception e) {
				errorLog("更新数据失败！", e);
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			UploadUtil.deleteFile(saveUrl.substring(0, saveUrl.lastIndexOf("/")));// 上传失败,删除整个文件
			errorLog("上传文件失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	@Override
	public void removeInfoById(Integer infoId) throws BusinessException {
		try {
			Info info = null;
			try {
				info = infoDao.selectByPrimaryKey(infoId);
			} catch (Exception e) {
				errorLog("获取信息失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				infoDao.deleteByPrimaryKey(infoId);
			} catch (Exception e) {
				errorLog("删除数据库数据失败！", e);
				throw new BusinessException(getMsg());
			}

			if (StringUtil.isNotEmpty(info.getFileUrl())) {
				try {
					String oldurl = info.getFileUrl();
					oldurl = oldurl.substring(0, oldurl.lastIndexOf("/"));
					// 删除旧文件
					UploadUtil.deleteDirectory(oldurl);
				} catch (Exception e) {
					errorLog("删除文件失败！", e);
					throw new BusinessException(getMsg());
				}
			}
		} catch (Exception e) {
			errorLog("删除信息失败！", e);
			throw new BusinessException(getMsg());
		}
	}

}
