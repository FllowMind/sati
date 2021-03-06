package com.jyu.sati.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author 韦金强
 * 
 */
public class FileUtil {

	// 验证字符串是否为正确路径名的正则表达式
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	// 通过 sPath.matches(matches) 方法的返回值判断是否正确
	// sPath 为路径字符串

	public static boolean DeleteFolder(String deletePath) {// 根据路径删除指定的目录或文件，无论存在与否
		boolean flag = false;
		File file = null;
		if (deletePath.matches(matches)) {
			file = new File(deletePath);
			if (!file.exists()) {// 判断目录或文件是否存在
				return flag; // 不存在返回 false
			} else {

				if (file.isFile()) {// 判断是否为文件
					return deleteFile(deletePath);// 为文件时调用删除文件方法
				} else {
					return deleteDirectory(deletePath);// 为目录时调用删除目录方法
				}
			}
		} else {
			System.out.println("要传入正确路径！");
			return false;
		}
	}

	public static boolean deleteFile(String filePath) {// 删除单个文件
		boolean flag = false;
		File file = null;
		flag = false;
		file = new File(filePath);
		if (file.isFile() && file.exists()) {// 路径为文件且不为空则进行删除
			file.delete();// 文件删除
			flag = true;
		}
		return flag;
	}

	public static boolean deleteDirectory(String dirPath) {// 删除目录（文件夹）以及目录下的文件
		boolean flag = false;
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!dirPath.endsWith(File.separator)) {
			dirPath = dirPath + File.separator;
		}
		File dirFile = new File(dirPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		File[] files = dirFile.listFiles();// 获得传入路径下的所有文件
		for (int i = 0; i < files.length; i++) {// 循环遍历删除文件夹下的所有文件(包括子目录)
			if (files[i].isFile()) {// 删除子文件
				flag = deleteFile(files[i].getAbsolutePath());
				System.out.println(files[i].getAbsolutePath() + " 删除成功");
				if (!flag)
					break;// 如果删除失败，则跳出
			} else {// 运用递归，删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;// 如果删除失败，则跳出
			}
		}
		if (!flag)
			return false;
		if (dirFile.delete()) {// 删除当前目录
			return true;
		} else {
			return false;
		}
	}

	// 创建单个文件
	public static boolean createFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {// 判断文件是否存在
			System.out.println("目标文件已存在" + filePath);
			return false;
		}
		if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
			System.out.println("目标文件不能为目录！");
			return false;
		}
		if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
			// 如果目标文件所在的文件夹不存在，则创建父文件夹
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
				System.out.println("创建目标文件所在的目录失败！");
				return false;
			}
		}
		try {
			if (file.createNewFile()) {// 创建目标文件
				System.out.println("创建文件成功:" + filePath);
				return true;
			} else {
				System.out.println("创建文件失败！");
				return false;
			}
		} catch (IOException e) {// 捕获异常
			e.printStackTrace();
			System.out.println("创建文件失败！" + e.getMessage());
			return false;
		}
	}

	// 创建目录
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("创建目录失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("创建目录成功！" + destDirName);
			return true;
		} else {
			System.out.println("创建目录失败！");
			return false;
		}
	}

	// 创建临时文件
	public static String createTempFile(String prefix, String suffix, String dirName) {
		File tempFile = null;
		if (dirName == null) {// 目录如果为空
			try {
				tempFile = File.createTempFile(prefix, suffix);// 在默认文件夹下创建临时文件
				return tempFile.getCanonicalPath();// 返回临时文件的路径
			} catch (IOException e) {// 捕获异常
				e.printStackTrace();
				System.out.println("创建临时文件失败：" + e.getMessage());
				return null;
			}
		} else {
			// 指定目录存在
			File dir = new File(dirName);// 创建目录
			if (!dir.exists()) {
				// 如果目录不存在则创建目录
				if (createDir(dirName)) {
					System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
					return null;
				}
			}
			try {
				tempFile = File.createTempFile(prefix, suffix, dir);// 在指定目录下创建临时文件
				return tempFile.getCanonicalPath();// 返回临时文件的路径
			} catch (IOException e) {// 捕获异常
				e.printStackTrace();
				System.out.println("创建临时文件失败!" + e.getMessage());
				return null;
			}
		}
	}

	// 保存文件
	public static void saveFile(String newsRootPath, String filename, InputStream fis) {
		try {
			File newsFileRoot = new File(newsRootPath);
			if (!newsFileRoot.exists()) {
				newsFileRoot.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(newsRootPath + filename);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) > 0) {
				fos.write(buf, 0, len);
			}
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	// 删除文件与目录
	public static boolean deleteFolder(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(filePath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(filePath);
			}
		}
	}



	/**
	 * 判断是否是图片类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isImageFileType(String fileName) {
		// 声明图片后缀名列表
		List<String> imageSuffix = Arrays.asList(".BMP", ".DIB", ".GIF", ".JFIF", ".JPE", ".JPEG", ".JPG", ".PNG",
				".TIF", ".TIFF", ".ICO", ".bmp", ".dib", ".gif", ".jfif", ".jpe", ".jpeg", ".jpg", ".png", ".tif",
				".tiff", ".ico");
		try {

			String suffix = fileName.substring(fileName.lastIndexOf("."));// 获取后缀
			if (StringUtil.isNotEmpty(suffix)) {
				if (imageSuffix.contains(suffix)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 判断是否是文本类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isTextFileType(String fileName) {
		// 声明图片后缀名列表
		List<String> imageSuffix = Arrays.asList(".txt", ".doc", ".ppt", ".docx", ".wps", ".TXT", ".DOC", ".PPT",
				".DOCX", ".WPS", ".WPS");
		try {

			String suffix = fileName.substring(fileName.lastIndexOf("."));// 获取后缀
			if (StringUtil.isNotEmpty(suffix)) {
				if (imageSuffix.contains(suffix)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 判断是否是视频类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isVideoFileType(String fileName) {
		// 声明视频后缀名列表
		List<String> imageSuffix = Arrays.asList(".rm", ".rmvb", ".wmv", ".avi", ".mp4", ".3gp", ".mkv", ".mpeg", ".RM",
				".RMVB", ".WMV", ".AVI", ".MP4", ".3GP", ".MKV", ".MPEG");

		try {
			String suffix = fileName.substring(fileName.lastIndexOf("."));// 获取后缀
			if (StringUtil.isNotEmpty(suffix)) {
				if (imageSuffix.contains(suffix)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 判断是否是word文档类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isWordFileType(String fileName) {
		// 声明后缀名列表
		List<String> imageSuffix = Arrays.asList(".doc", ".docx", ".DOC", ".DOCX");
		try {

			String suffix = fileName.substring(fileName.lastIndexOf("."));// 获取后缀
			if (StringUtil.isNotEmpty(suffix)) {
				if (imageSuffix.contains(suffix)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 判断是否不是word文档类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isNotWordFileType(String fileName) {
		return !isWordFileType(fileName);
	}

	/**
	 * 不是图片类型的文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isNotImageFileType(String fileName) {
		return !isImageFileType(fileName);
	}

	/**
	 * 不是视频类型的文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isNotVideoFileType(String fileName) {
		return !isVideoFileType(fileName);
	}

	/**
	 * 不是文本类型的文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isNotTextType(String fileName) {
		return !isTextFileType(fileName);
	}

}