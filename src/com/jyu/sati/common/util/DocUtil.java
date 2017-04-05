package com.jyu.sati.common.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import com.jyu.sati.common.exception.BusinessException;

/**
 * word文档工具
 * 
 * @author 淋雨又调皮
 *
 */
public class DocUtil {

	/**
	 * word 文档转html
	 * 
	 * @param input
	 *            work输入流
	 * @param filePath
	 *            word文档保存的路径
	 * @return
	 * @throws Exception
	 */
	public static String word2html(String filePath) throws Exception {

		try {
			if (filePath.endsWith(".docx") || filePath.endsWith(".DOCX")) {
				return Word2007ToHtml(filePath);
			} else if (filePath.endsWith(".doc") || filePath.endsWith(".DOC")) {
				return Word2003ToHtml(filePath);
			} else {
				throw new BusinessException("无法解析的文档类型！");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 2007版本word转换成html 格式字符串
	 * 
	 * @param filePath
	 *            需要转化的word文档绝对路径
	 * @return
	 * @throws IOException
	 */
	public static String Word2007ToHtml(String filepath) throws IOException {
		final String imagepath = filepath.substring(0, filepath.lastIndexOf("/")) + "/images";
		if (!filepath.endsWith(".docx") && !filepath.endsWith(".DOCX")) {
			System.out.println("不是2007版本 + 的word文档！");
		}
		String content = null;// 转化后的结果
		try {

			File f = new File(filepath);
			if (!f.exists()) {
				System.out.println("Sorry File does not Exists!");
			} else {
				if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

					// 1) 加载word文档生成 XWPFDocument对象
					InputStream in = new FileInputStream(f);
					XWPFDocument document = new XWPFDocument(in);

					// 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
					File imageFolderFile = new File(imagepath);
					XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
					options.setExtractor(new FileImageExtractor(imageFolderFile));
					options.setIgnoreStylesIfUnused(false);
					options.setFragment(true);

					// // 3) 将 XWPFDocument转换成XHTML
					// OutputStream out = new FileOutputStream(new File(filepath
					// +
					// htmlName));
					// XHTMLConverter.getInstance().convert(document, out,
					// options);

					// 也可以使用字符数组流获取解析的内容
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					XHTMLConverter.getInstance().convert(document, baos, options);
					content = baos.toString();
					String saveUrl = UploadUtil.getRootPath();
					saveUrl = saveUrl.replaceAll("\\\\", "/");
					content = content.replaceAll("\\\\", "/");
					content = content.replaceAll(saveUrl, UploadUtil.getServerUrl());// 替换word里面的图片路径为服务器地址
					baos.close();
				} else {
					System.out.println("Enter only MS Office 2007+ files");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return content;
	}

	/**
	 * /** 2003版本word转换成html 格式字符串
	 * 
	 * @param filePath
	 *            需要转化的word文档绝对路径
	 * @return
	 * @throws IOException
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	@Test
	public static String Word2003ToHtml(String filePath)
			throws IOException, TransformerException, ParserConfigurationException {
		final String imagepath = filePath.substring(0, filePath.lastIndexOf("/")) + "/images";
		if (!filePath.endsWith(".doc") && !filePath.endsWith(".DOC")) {
			System.out.println("不是2003版本 + 的word文档！");
		}
		String content = null;// 转化后的内容
		try {
			InputStream input = new FileInputStream(new File(filePath));
			HWPFDocument wordDocument = new HWPFDocument(input);
			WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
			// 设置图片存放的位置
			wordToHtmlConverter.setPicturesManager(new PicturesManager() {
				public String savePicture(byte[] content, PictureType pictureType, String suggestedName,
						float widthInches, float heightInches) {
					File imgPath = new File(imagepath);
					if (!imgPath.exists()) {// 图片目录不存在则创建
						imgPath.mkdirs();
					}
					File file = new File(imagepath + suggestedName);
					try {
						OutputStream os = new FileOutputStream(file);
						os.write(content);
						os.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return imagepath + suggestedName;
				}
			});

			// 解析word文档
			wordToHtmlConverter.processDocument(wordDocument);
			org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();

			// 转化成文件
			// File htmlFile = new File(filepath + htmlName);
			// OutputStream outStream = new FileOutputStream(htmlFile);

			// 也可以使用字符数组流获取解析的内容
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStream outStream = new BufferedOutputStream(baos);

			DOMSource domSource = new DOMSource(htmlDocument);
			StreamResult streamResult = new StreamResult(outStream);

			TransformerFactory factory = TransformerFactory.newInstance();
			javax.xml.transform.Transformer serializer = factory.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");

			serializer.transform(domSource, streamResult);

			// 使用字符数组流获取解析的内容
			content = baos.toString();
			String saveUrl = UploadUtil.getRootPath();
			saveUrl = saveUrl.replaceAll("\\\\", "/");
			content = content.replaceAll("\\\\", "/");
			content = content.replaceAll(saveUrl, UploadUtil.getServerUrl());// 替换word里面的图片路径为服务器地址
			baos.close();
			// 关闭流
			outStream.close();
		} catch (Exception e) {
			throw e;
		}
		return content;
	}

}
