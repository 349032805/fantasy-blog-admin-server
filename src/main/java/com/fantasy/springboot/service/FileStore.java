package com.fantasy.springboot.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStore {

	private static String PICTURE = "picture";

	private static String DOCUMENT = "document";

	private static String ZIP = "zip";

	private static String VIDEO = "video";

	private File fileStoreDir;
	private File pictureDir;
	private File documentDir;
	private File zipDir;
	private File videoDir;
	private File otherDir;

	public FileStore() {
		System.out.println(
				"---------------------------------------------初始化创建文件夹-----------------------------------------------");
		String filePath = "./files";
		fileStoreDir = new File(filePath);

		if (!fileStoreDir.exists()) {
			fileStoreDir.mkdirs();
		}

		pictureDir = new File(fileStoreDir, PICTURE);
		if (!pictureDir.exists()) {
			pictureDir.mkdir();
		}

		documentDir = new File(fileStoreDir, DOCUMENT);
		if (!documentDir.exists()) {
			documentDir.mkdir();
		}

		zipDir = new File(fileStoreDir, ZIP);
		if (!zipDir.exists()) {
			zipDir.mkdir();
		}

		videoDir = new File(fileStoreDir, VIDEO);
		if (!videoDir.exists()) {
			videoDir.mkdir();
		}

		otherDir = new File(fileStoreDir, "other");
		if (!otherDir.exists()) {
			otherDir.mkdir();
		}
	}

	protected static List<String> pictureSuffixes = Arrays.asList(new String[] { "png", "jpg", "jpeg" });

	protected static List<String> documentSuffixes = Arrays.asList(new String[] { "xls", "xlsx", "docx", "pdf" });

	protected static List<String> zipSuffixes = Arrays.asList(new String[] { "zip", "rar" });

	protected static List<String> videoSuffixes = Arrays
			.asList(new String[] { "mp3", "flv", "mp4", "ogg", "avi", "wmv" });

	// 上传文件,返回处理后的文件名,路径和文件类型
	public Map uploadBy(MultipartFile file) throws IOException, UnsupportedContentType {
		String suffix = getFileTypeByFileOriginName(file);
		if (suffix == null) {
			throw new UnsupportedContentType("不存在文件后缀名!");
		}

		String fileName = Hex.encodeHexString(DigestUtils.md5(file.getInputStream())) + "." + suffix;

		Map<String, String> map = new HashMap<>();
		map.put("fileName", fileName);

		String fileType = getFileType(suffix);
		map.put("fileType", fileType);

		String filePath = "";
		File target = null;

		if (fileType.equals(PICTURE)) {
			filePath = "/files/picture/" + fileName;
			target = new File(pictureDir, fileName);

		} else if (fileType.equals(DOCUMENT)) {
			filePath = "/files/document/" + fileName;
			target = new File(documentDir, fileName);

		} else if (fileType.equals(ZIP)) {
			filePath = "/files/zip/" + fileName;
			target = new File(zipDir, fileName);

		} else if (fileType.equals(VIDEO)) {
			filePath = "/files/video/" + fileName;
			target = new File(videoDir, fileName);
		} else if (fileType.equals("unknow")) {
			filePath = "/files/other/" + fileName;
			target = new File(otherDir, fileName);
		}

		map.put("filePath", filePath);

		if (!target.exists()) {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(target));
		} else {
			map = null;
		}
		return map;
	}

	// 根据源文件名称后缀获取文件类型,不严谨
	// 如果想要更严谨,使用MimeType
	public String getFileTypeByFileOriginName(MultipartFile file) {
		return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
				file.getOriginalFilename().length());
	}

	public String getFileType(String suffix) {
		for (String s : pictureSuffixes) {
			if (suffix.equalsIgnoreCase(s)) {
				return PICTURE;
			}
		}

		for (String s : documentSuffixes) {
			if (suffix.equalsIgnoreCase(s)) {
				return DOCUMENT;
			}
		}

		for (String s : zipSuffixes) {
			if (suffix.equalsIgnoreCase(s)) {
				return ZIP;
			}
		}

		for (String s : videoSuffixes) {
			if (suffix.equalsIgnoreCase(s)) {
				return VIDEO;
			}
		}

		return "unknow";
	}

	public static class UnsupportedContentType extends Exception {
		public UnsupportedContentType(String message) {
			super(message);
		}
	}

	public File getFileByFilePath(String filePath) throws Exception {
		if (filePath != null && filePath.startsWith("/files/")) {
			return new File(fileStoreDir, filePath.substring("/files/".length()));
		}
		throw new Exception("file path is not right" + filePath);
	}

	// 根据文件路径删除文件
	public void deleteFileByPath(String filePath) throws Exception {
		File file = getFileByFilePath(filePath);
		if (file.isFile()) {
			file.delete();
		}
	}

	// public String getSuffixByContentType(String contentType) {
	// try {
	// MimeType type =
	// MimeTypes.getDefaultMimeTypes().getRegisteredMimeType(contentType);
	// if (type != null)
	// return type.getExtension();
	// } catch (MimeTypeException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

}
