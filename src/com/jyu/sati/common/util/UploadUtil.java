package com.jyu.sati.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Document;
import com.jyu.sati.entity.Info;
import com.jyu.sati.entity.ProduceImage;
import com.jyu.sati.entity.TechEnclosure;

/**
 * 文件上传工具类
 * 
 * @author 淋雨又调皮
 *
 */
public class UploadUtil {

	private static Properties config = PropsUtil.loadProps("uploadConfig.properties");// 加载配置文件

	/**
	 * 保存上传文件
	 * 
	 * @param newfile
	 *            上传的文件
	 * @param fileType
	 *            文件类型
	 * @param userId
	 *            用户账号（不是上传系统图片时，不能为空！）
	 * @return （文件保存成功的路径）
	 * @throws BusinessException
	 */
	public static String saveFiles(MultipartFile newfile, int fileType, String userId) throws BusinessException {
		// 获取路径
		String fileUrl = getSavePath(fileType);
		// 获取文件名后缀
		String fileName = newfile.getOriginalFilename();
		String suffix = "";
		if (StringUtil.isNotEmpty(fileName)) {
			suffix = fileName.substring(fileName.lastIndexOf("."));
			if (StringUtil.isEmpty(suffix)) {
				throw new BusinessException("无法识别的文件类型！");
			}
		}

		// 判断用户账号是否为空，如果为空且上传文件类型为主页宣传图片，则userId为home,否则为
		if (StringUtil.isEmpty(userId) && fileType == Document.DUCUMENT_TYPE_HOME_PAGE_IMAGE) {
			userId = "home";
		} else if (StringUtil.isEmpty(userId) && fileType != Document.DUCUMENT_TYPE_HOME_PAGE_IMAGE) {
			throw new BusinessException("保存文件失败！无法获取用户账号!");
		} else if (fileType == Info.UPLOAD_INFO_TYPE_NOTICE || fileType == Info.UPLOAD_INFO_TYPE_POLICY) {
			userId = "system";
		}

		String uuidName = UUIDUtil.getNormalUUID();

		/* 构建文件 **服务器文件存路径+对应文件类型文件存放路径+用户账号** 目录 */
		File fileDir = null;
		// 如果文件类型为word文档，则路径多一个文件夹，方便删除所有文件
		if (fileType == Info.UPLOAD_INFO_TYPE_NOTICE || fileType == Info.UPLOAD_INFO_TYPE_POLICY) {
			fileDir = new File(getRootPath() + fileUrl + userId + "/" + uuidName + "/");
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			// 文件保存的相对路径
			fileUrl += userId + "/" + uuidName + "/" + uuidName + suffix;// 加上UUID文件名和后缀
		} else {
			fileDir = new File(getRootPath() + fileUrl + userId + "/");
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			// 文件保存的相对路径
			fileUrl += userId + "/" + UUIDUtil.getNormalUUID() + suffix;// 加上UUID文件名和后缀
		}

		try {
			// 写入文件到硬盘
			newfile.transferTo(new File(getRootPath() + fileUrl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("写入文件失败！");
		}
		return fileUrl;
	}

	/**
	 * 删除制定文件
	 * 
	 * @param filePath
	 *            保存到数据库的url
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		return FileUtil.deleteFile(getRootPath() + filePath);
	}

	/**
	 * 删除整个文件夹
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean deleteDirectory(String filePath) {
		return FileUtil.deleteDirectory(getRootPath() + filePath);
	}

	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	public static String getRootPath() {
		return PropsUtil.getString(config, "rootPath");
	}

	/**
	 * 获取不同文件类型的保存路径
	 * 
	 * @param fileType
	 *            文件类型
	 * @return
	 */
	private static String getSavePath(int fileType) {
		String fileUrl = "";
		// 根据文件类型获取对应文件保存路径
		switch (fileType) {
		case Document.DUCUMENT_TYPE_ID_CARD_IMAGE:
			// 身份证图片
			fileUrl = getIdCardImagPath();
			break;
		case Document.DUCUMENT_TYPE_PERSON_IMAGE:
			// 个人图片
			fileUrl = getPersonImagesPath();
			break;
		case Document.DUCUMENT_TYPE_ENCLOSURE:
			// 附件
			fileUrl = getEnclosuresPath();
			break;
		case Document.DUCUMENT_TYPE_UNIT_CODE_IMAGE:
			// 机构代码图片
			fileUrl = getUnitCodeImagesPath();
			break;
		case Document.DUCUMENT_TYPE_HOME_PAGE_IMAGE:
			// 主页宣传图片
			fileUrl = getHomePageImagesPath();
			break;
		case ProduceImage.PRODUCE_IMAGE:
			// 产品成果
			fileUrl = getProduceImagesPath();
			break;
		case TechEnclosure.ENCLOSURE_TYPE_IMAGE:
			// 技术供给图片附件
			fileUrl = getTechSupplyImagesPath();
			break;
		case TechEnclosure.ENCLOSURE_TYPE_TEXT:
			// 技术供给文本附件
			fileUrl = getTechSupplyTextsPath();
			break;
		case TechEnclosure.ENCLOSURE_TYPE_VIDEO:
			// 技术供给视频附件
			fileUrl = getTechSupplyVideosPath();
			break;
		case Info.UPLOAD_INFO_TYPE_POLICY:
			// 政策法规类型
			fileUrl = getPolicyFilesPath();
			break;
		case Info.UPLOAD_INFO_TYPE_NOTICE:
			// 通知公告类型
			fileUrl = getNoticeFilesPath();
			break;
		default:
			throw new BusinessException("无法保存该类型文件");
		}
		return fileUrl;
	}

	/**
	 * 获取身份证照片保存路径
	 * 
	 * @return
	 */
	public static String getIdCardImagPath() {
		return PropsUtil.getString(config, "upload.idcardImagPath");
	}

	/**
	 * 获取 附件文件保存路径
	 * 
	 * @return
	 */
	public static String getEnclosuresPath() {
		return PropsUtil.getString(config, "upload.enclosuresPath");
	}

	/**
	 * 获取个人图片保存路径
	 * 
	 * @return
	 */
	public static String getPersonImagesPath() {
		return PropsUtil.getString(config, "upload.personImagesPath");
	}

	/**
	 * 获取组织机构代码图片或统一社会信用代码图片存放路径
	 * 
	 * @return
	 */
	public static String getUnitCodeImagesPath() {
		return PropsUtil.getString(config, "upload.unitCodeImagesPath");
	}

	/**
	 * 获取产品成果图片存放位置
	 * 
	 * @return
	 */
	public static String getProduceImagesPath() {
		return PropsUtil.getString(config, "upload.produceImagesPath");
	}

	/**
	 * 获取首页宣传图片存放路径
	 * 
	 * @return
	 */
	public static String getHomePageImagesPath() {
		return PropsUtil.getString(config, "upload.homePageImagePath");
	}

	/**
	 * 技术供给图片附件路径
	 * 
	 * @return
	 */
	public static String getTechSupplyImagesPath() {
		return PropsUtil.getString(config, "upload.techSupplyImagesPath");
	}

	/**
	 * 技术供给视频附件位置
	 *
	 * @return
	 */
	public static String getTechSupplyVideosPath() {
		return PropsUtil.getString(config, "upload.techSupplyVideosPath");
	}

	/**
	 * 技术供给文本附件路径
	 * 
	 * @return
	 */
	public static String getTechSupplyTextsPath() {
		return PropsUtil.getString(config, "upload.techSupplyTextsPath");
	}

	/**
	 * 通知公告位置位置
	 * 
	 * @return
	 */
	public static String getNoticeFilesPath() {
		return PropsUtil.getString(config, "upload.noticeFilesPath");
	}

	/**
	 * #政策法规位置位置
	 * 
	 * @return
	 */
	public static String getPolicyFilesPath() {
		return PropsUtil.getString(config, "upload.policyFilesPath");
	}

	/**
	 * 获取服务器访问地址
	 * 
	 * @return
	 */
	public static String getServerUrl() {
		return PropsUtil.getString(config, "serverUrl");
	}

}
