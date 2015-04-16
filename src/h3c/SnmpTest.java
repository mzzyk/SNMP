package h3c;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpTest {

	/* 定义一个snmp全局对象 */
	private Snmp snmp = null;
	/* 要访问snmp服务的主机地址 */
	private Address targetAddress = null;

	/**
	 * 初始化访问主机的条件
	 */
	public void initComm() {

		/* 初始化Agent端的IP和端口 (端口号一定不能少) */
		targetAddress = GenericAddress.parse("udp:127.0.0.1/161");

		try {
			// 设置传输映射的接口为默认的UDP协议
			TransportMapping transport = new DefaultUdpTransportMapping();
			// 初始化snmp
			snmp = new Snmp(transport);
			// 监听snmp消息
			transport.listen();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 构造发送目标
	 * 
	 * @return 构造的目标
	 */
	public CommunityTarget setPDU() {
		// 设置目标
		CommunityTarget target = new CommunityTarget();
		// 设置团体名称
		target.setCommunity(new OctetString("public"));
		// 设置团体的访问地址
		target.setAddress(targetAddress);
		// 设置通信不成功的重试次数
		target.setRetries(2);
		// 设置超时时间
		target.setTimeout(3000);
		// 设置snmp协议版本
		target.setVersion(SnmpConstants.version1);

		return target;
	}

	/**
	 * 构造发生报文
	 * 
	 * @return 构造的PDU
	 */
	public PDU createPDU() {
		// 创建一个新的PDU
		PDU pdu = new PDU();
		// 设置MIB的访问方式，get,getnext,getbulk,set etc.
		pdu.setType(PDU.GET);
		// 设置PDU的OID信息
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0")));
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0")));
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5")));
		// 或者也可以用以下设置OID的方式
		// pdu.add(new VariableBinding(new OID(new int[]{1,3,6,1,2,1,1,5,0})));

		return pdu;
	}

	/*
	 * 同步模式发送消息
	 */
	public void sendPDU() {

		CommunityTarget target = setPDU();
		PDU newPdu = createPDU();

		try {
			// 同步模式发送消息
			ResponseEvent msg = snmp.send(newPdu, target);

			System.out.println("同步解析开始");
			// 解析消息
			readReponse(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 发送异步消息
	 */
	public void sendAsynPDU() throws IOException, InterruptedException {
		CommunityTarget target = setPDU();
		PDU pdu = createPDU();

		ResponseListener listener = new ResponseListener() {

			@Override
			public void onResponse(ResponseEvent msg) {
				System.out.println("异步解析开始。。。。");
				readReponse(msg);
			}
		};
		try {
			snmp.send(pdu, target, null, listener);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// main thread wait 6s for the completion of asynchronous request
		// 一定要要主线程等待几秒钟，否则取不到消息。
		Thread.sleep(6000);
	}

	/**
	 * 解析消息
	 * 
	 * @param msg为消息内容
	 */
	@SuppressWarnings("unchecked")
	public void readReponse(ResponseEvent msg) {

		PDU resPDU = msg.getResponse();
		// 首先判断返回的消息是否为空，如果不为空，然后再解析读取到的数据。
		if (msg != null && resPDU != null) {

			// 获取错误状态
			int errorStatus = resPDU.getErrorStatus();
			// 获取错误指针
			int errorIndex = resPDU.getErrorIndex();
			// 获取错误状态内容
			String errorStatusText = resPDU.getErrorStatusText();
			
			//判断获取的PDU是否有错，如果没错，提取信息；如果有错输出错误信息
			if (errorStatus == PDU.noError) {
				// 获取返回结果到容器中
				Vector<VariableBinding> res = msg.getResponse()
						.getVariableBindings();

				// 遍历容器内容，输出访问到消息
				for (int i = 0; i < res.size(); i++) {
					VariableBinding rec = res.elementAt(i);
					System.out.println(rec.getOid() + ": " + rec.getVariable());
				}
			}else {
				//提示请求失败，并输出错误的相关信息
				System.out.println("Error:Request Failed!");
				System.out.println("Error Status = "+ errorStatus);
				System.out.println("Error Index = "+ errorIndex);
				System.out.println("Error Status Text = "+ errorStatusText);
			}
		}
	}

	public static void main(String args[]) {
		SnmpTest test = new SnmpTest();
		test.initComm();
		test.sendPDU();

		try {
			test.sendAsynPDU();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
