package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ZYK java I/O �ֽڵĶ�ȡ��д�루���ַ������Զ�ȡ�κ���ʽ���ļ����� �ַ��Ķ�ȡ��д�롣
 *         ������InputStream��OutputStream��β�����;�Ϊ�ֽ���, ������Reader��Writer��β�ľ�Ϊ�ַ�����
 *         ��buffer��Ϊ���崦������ʽ
 */
public class testIO {

	public static void main(String args[]) {

		byteIO();

		// charIO();
		// buffIO();
	}

	/**
	 * ���ֽ����ķ�ʽ��ȡ��д������ ���ֶ�ȡ��ʽ �� FileInputStream����ʹ��read()����һ�ζ���һ���ֽڣ�����int���ͷ��أ�
	 * ������ʹ��read()����ʱ������һ��byte ���飬byte�����Ԫ���ж��ٸ����Ͷ�
	 * ����ٸ��ֽڡ��ڽ������ļ���ȡ��ɻ�д����ϵĹ����У���ôһ��byte����ͨ�� ����������������Ϊ��ôһ��
	 * byte����ͨ�����ݳн����ݵ��м��ɫ��
	 */
	public static void byteIO() {
		try {
			// ʹ���ֽ�����������
			FileInputStream input = new FileInputStream("a.txt");
			FileOutputStream output = new FileOutputStream("c.txt");

			// ��ȡ�ϴ���ļ�ʱ�������ֽ�����ķ�ʽ���ж�ȡ��д��
			// byte[] b = new byte[1024];
			// int t;
			// //input.read(b);
			// while ((t=input.read(b)) != -1){
			// output.write(b);
			// }

			// �ʺ��ڶ�ȡС�ļ�
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
	 * ���ַ��ķ�ʽ���ж�ȡ. FileReader����ʹ��read()����һ�ζ���һ���ֽڣ�����int���ͷ��أ�
	 * ������ʹ��read()����ʱ������һ��char ���飬char�����Ԫ���ж��ٸ����Ͷ�
	 * ����ٸ��ֽڡ��ڽ������ļ���ȡ��ɻ�д����ϵĹ����У���ôһ��char����ͨ�� ����������������Ϊ��ôһ��
	 * char����ͨ�����ݳн����ݵ��м��ɫ�� �ײ�ý��ۣ������Դ�ļ�����ͼƬ����ô���ô����ַ����ķ�ʽ���ᷢ�����󣬶������ֽ������������С�
	 */
	public static void charIO() {
		try {
			FileReader fr = new FileReader("a.txt");
			FileWriter fw = new FileWriter("b.txt");

			// Ҳ�����ڴ�ʹ��buff�������������ַ�������߷����ٶ�
			// BufferedReader br =new BufferedReader(fr);
			// BufferedWriter bw =new BufferedWriter(fw);

			// //�ʺ���С�ļ���һ��һ���ַ���
			// int temp = fr.read();
			// while (temp != -1){
			// fw.write(temp);
			// temp = fr.read();
			// }

			// �ʺ��ڴ��ļ���һ���ַ�һ���ַ��ض�
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
	 * buff �������Ƕ�һ���Ѵ��ڵ��������Ӻͷ�װ,ͨ����װ�����Ĺ��ܵ���ʵ����ǿ�����ݶ�/д����,����������ֱ�����ӵ�����Դ.
	 * �����������õ���ҪĿ���ǣ�����ÿ�κ�Ӳ�̴򽻵���������ݷ��ʵ�Ч�ʡ�
	 */
	public static void buffIO() {

		try {
			// ͨ��buff����������ݷ��ʵ�Ч��
			FileInputStream fi = new FileInputStream("jdk-7u45-windows-x64.exe");
			BufferedInputStream bi = new BufferedInputStream(fi);

			FileOutputStream fo = new FileOutputStream("d.exe");
			BufferedOutputStream bo = new BufferedOutputStream(fo);

			byte[] readBuff = new byte[1];
			int temp = 0;
			while ((temp = bi.read(readBuff)) != -1) {
				bo.write(readBuff);
			}

			// ����fi �� fo close ���������Ļ��޷�ʵ�ֶ�ȡ��д�롣
			// ���FileInputStream��FileOutputStream������Ƕ�ķ�ʽ,�����Ͳ�Ҫ����close��.������ʽ����
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
