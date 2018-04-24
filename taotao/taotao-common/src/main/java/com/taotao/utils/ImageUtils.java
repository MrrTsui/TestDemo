package com.taotao.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 图片处理类
 * <p>
 * Title: ImageUtils
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author wansiliang
 * @param <V>
 * 
 * @date 2018年4月6日
 */
public class ImageUtils implements Runnable {

	private InputStream imageFileInputStream;

	private String fileName;

	public ImageUtils() {
		super();
	}

	public ImageUtils(InputStream imageFileInputStream, String fileName) {
		super();
		this.imageFileInputStream = imageFileInputStream;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		System.out.println("上次图片多线程已经开启");
		try {
			// 拿到图片类
			// 拿到图片的inputStream
			// FileInputStream fileInputStream = new FileInputStream(imageFileInputStream);
			// 建立缓存器
			byte[] buf = new byte[1024 * 1024];
			// 记录每次读取的长度，以便输出到硬盘
			int len = 0;
			// 拿到包装字节流
			BufferedInputStream bufferedInputStream = new BufferedInputStream(imageFileInputStream);
			// 建立输出流
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(new File(
					"E:\\eclipse-workspace\\taotao\\taotao\\taotao-manager\\taotao-manager-web\\src\\main\\webapp\\WEB-INF\\image"),
					fileName)));
			// 读取并输出到服务器
			while ((len = bufferedInputStream.read(buf)) != -1) {
				bufferedOutputStream.write(buf, 0, len);
			}
			// 关闭流
			bufferedOutputStream.flush();
			bufferedInputStream.close();
			bufferedOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
