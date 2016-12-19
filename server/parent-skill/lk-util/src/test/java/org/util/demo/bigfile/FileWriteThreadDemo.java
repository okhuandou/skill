package org.util.demo.bigfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ClassName: FileWriteThreadDemo <br/>
 * Function: 多线程下载demo <br/>
 * Reason: <br/>
 * date: 2016年11月17日 下午2:20:39 <br/>
 *
 * @author lk
 * @version
 * @since JDK 1.7
 */
public class FileWriteThreadDemo {

	public static void main(String[] args) throws Exception {
		// 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
		RandomAccessFile raf = new RandomAccessFile("D://abc.txt", "rw");
		raf.setLength(1024 * 1024); // 预分配 1M 的文件空间

		// 所要写入的文件内容
		String s1 = "第一个字符串";
		String s2 = "第二个字符串";
		String s3 = "第三个字符串";
		String s4 = "第四个字符串";
		String s5 = "第五个字符串";
		String name = Thread.currentThread().getName();
		System.out.println(name + " ---  " + s1.getBytes().length);
		// new FileWriteThreadWithLock(18 * 8, s5.getBytes()).start(); //
		// 从文件的5120字节之后开始写入数据
		// 利用多线程同时写入一个文件
		// new FileWriteThread(0, s1.getBytes()).start(); // 从文件的1024字节之后开始写入数据
		// new FileWriteThread(18 * 1, s1.getBytes()).start(); //
		// 从文件的1024字节之后开始写入数据
		// new FileWriteThreadWithLock(18 * 6, s5.getBytes()).start(); //
		// 从文件的5120字节之后开始写入数据
		// new FileWriteThread(18 * 2, s2.getBytes()).start(); //
		// 从文件的2048字节之后开始写入数据
		// new FileWriteThreadWithLock(18 * 7, s5.getBytes()).start(); //
		// 从文件的5120字节之后开始写入数据
		// new FileWriteThread(18 * 3, s3.getBytes()).start(); //
		// 从文件的3072字节之后开始写入数据
		// new FileWriteThread(18 * 4, s4.getBytes()).start(); //
		// 从文件的4096字节之后开始写入数据
		// new FileWriteThread(18 * 5, s5.getBytes()).start(); //
		// 从文件的5120字节之后开始写入数据
		new FileInputStream1().start();
		new FileInputStream1().start();
		new FileInputStream1().start();
		raf.close();
	}


	// 利用线程在文件的指定位置写入指定数据
	static class FileWriteThread extends Thread {
		private int skip;
		private byte[] content;

		public FileWriteThread(int skip, byte[] content) {
			this.skip = skip;
			this.content = content;
		}

		@Override
		public void run() {
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile("D://abc.txt", "rw");
				raf.seek(skip);
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + "  ----写++ ");
					raf.write(content);
				}
				// Thread.sleep(1000);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					raf.close();
					System.out.println(Thread.currentThread().getName() + "  ---释放-++ ");
				} catch (Exception e) {
				}
			}
		}
	}

	// 利用线程在文件的指定位置写入指定数据
	static class FileWriteThreadWithLock extends Thread {
		private int skip;
		private byte[] content;

		public FileWriteThreadWithLock(int skip, byte[] content) {
			this.skip = skip;
			this.content = content;
		}

		@Override
		public void run() {
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile("D://abc.txt", "rws");
				raf.seek(skip);
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + "  ++++写++++ ");
					raf.write(content);
				}
				Thread.sleep(10000);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println(Thread.currentThread().getName() + "  +++释放+++ ");
					raf.close();
				} catch (Exception e) {
				}
			}
		}
	}

	static class FileInputStream1 extends Thread {

		@Override
		public void run() {
			FileInputStream raf = null;
			try {
				raf = new FileInputStream("D://abc.txt");
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + " 00000写0000 " + i + raf.read());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println(Thread.currentThread().getName() + "  +++释放+++ ");
					raf.close();
				} catch (Exception e) {
				}
			}
		}
	}

}
