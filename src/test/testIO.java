package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ZYK java I/O 字节的读取和写入（此种方法可以读取任何形式的文件）， 字符的读取和写入。
 *         凡是以InputStream或OutputStream结尾的类型均为字节流, 凡是以Reader或Writer结尾的均为字符流。
 *         带buffer的为缓冲处理流方式
 */
public class testIO {

	public static void main(String args[]) {

		byteIO();

		// charIO();
		// buffIO();
	}

	/**
	 * 以字节流的方式读取和写入数据 俩种读取方式 。 FileInputStream可以使用read()方法一次读入一个字节，并以int类型返回，
	 * 或者是使用read()方法时读入至一个byte 数组，byte数组的元素有多少个，就读
	 * 入多少个字节。在将整个文件读取完成或写入完毕的过程中，这么一个byte数组通常 被当作缓冲区，因为这么一个
	 * byte数组通常扮演承接数据的中间角色。
	 */
	public static void byteIO() {
		try {
			// 使用字节流处理数据
			FileInputStream input = new FileInputStream("a.txt");
			FileOutputStream output = new FileOutputStream("c.txt");

			// 读取较大的文件时，采用字节数组的方式进行读取与写入
			// byte[] b = new byte[1024];
			// int t;
			// //input.read(b);
			// while ((t=input.read(b)) != -1){
			// output.write(b);
			// }

			// 适合于读取小文件
			int temp = input.read();
			while (temp != -1) {
				output.write(temp);
				temp = input.read();
			}

			input.close();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 以字符的方式进行读取. FileReader可以使用read()方法一次读入一个字节，并以int类型返回，
	 * 或者是使用read()方法时读入至一个char 数组，char数组的元素有多少个，就读
	 * 入多少个字节。在将整个文件读取完成或写入完毕的过程中，这么一个char数组通常 被当作缓冲区，因为这么一个
	 * char数组通常扮演承接数据的中间角色。 亲测得结论：如果将源文件换成图片，那么采用此种字符流的方式将会发生错误，而采用字节流则正常运行。
	 */
	public static void charIO() {
		try {
			FileReader fr = new FileReader("a.txt");
			FileWriter fw = new FileWriter("b.txt");

			// 也可以在此使用buff缓冲流来操作字符，来提高访问速度
			// BufferedReader br =new BufferedReader(fr);
			// BufferedWriter bw =new BufferedWriter(fw);

			// //适合于小文件，一个一个字符读
			// int temp = fr.read();
			// while (temp != -1){
			// fw.write(temp);
			// temp = fr.read();
			// }

			// 适合于大文件，一串字符一串字符地读
			char[] tem = new char[1024];
			int t = 0;
			while ((t = fr.read(tem)) != -1) {
				fw.write(tem);
			}

			fr.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * buff 处理流是对一个已存在的流的连接和封装,通过封装的流的功能调用实现增强的数据读/写功能,处理流并不直接连接到数据源.
	 * 缓冲区的作用的主要目的是：避免每次和硬盘打交道，提高数据访问的效率。
	 */
	public static void buffIO() {

		try {
			// 通过buff流，提高数据访问的效率
			FileInputStream fi = new FileInputStream("jdk-7u45-windows-x64.exe");
			BufferedInputStream bi = new BufferedInputStream(fi);

			FileOutputStream fo = new FileOutputStream("d.exe");
			BufferedOutputStream bo = new BufferedOutputStream(fo);

			byte[] readBuff = new byte[1];
			int temp = 0;
			while ((temp = bi.read(readBuff)) != -1) {
				bo.write(readBuff);
			}

			// 不能fi 和 fo close 掉，那样的话无法实现读取和写入。
			// 最好FileInputStream和FileOutputStream采用内嵌的方式,那样就不要关心close了.具体形式如下
			// BufferedInputStream bi = new BufferedInputStream(new
			// FileInputStream("a.txt"));
			// BufferedOutputStream bo = new BufferedOutputStream(new
			// FileOutputStream("b.txt"));

			// fi.close();
			// fo.close();

			bi.close();
			bo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
