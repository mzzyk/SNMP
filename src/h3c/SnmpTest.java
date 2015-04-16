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

	/* ����һ��snmpȫ�ֶ��� */
	private Snmp snmp = null;
	/* Ҫ����snmp�����������ַ */
	private Address targetAddress = null;

	/**
	 * ��ʼ����������������
	 */
	public void initComm() {

		/* ��ʼ��Agent�˵�IP�Ͷ˿� (�˿ں�һ��������) */
		targetAddress = GenericAddress.parse("udp:127.0.0.1/161");

		try {
			// ���ô���ӳ��Ľӿ�ΪĬ�ϵ�UDPЭ��
			TransportMapping transport = new DefaultUdpTransportMapping();
			// ��ʼ��snmp
			snmp = new Snmp(transport);
			// ����snmp��Ϣ
			transport.listen();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * ���췢��Ŀ��
	 * 
	 * @return �����Ŀ��
	 */
	public CommunityTarget setPDU() {
		// ����Ŀ��
		CommunityTarget target = new CommunityTarget();
		// ������������
		target.setCommunity(new OctetString("public"));
		// ��������ķ��ʵ�ַ
		target.setAddress(targetAddress);
		// ����ͨ�Ų��ɹ������Դ���
		target.setRetries(2);
		// ���ó�ʱʱ��
		target.setTimeout(3000);
		// ����snmpЭ��汾
		target.setVersion(SnmpConstants.version1);

		return target;
	}

	/**
	 * ���췢������
	 * 
	 * @return �����PDU
	 */
	public PDU createPDU() {
		// ����һ���µ�PDU
		PDU pdu = new PDU();
		// ����MIB�ķ��ʷ�ʽ��get,getnext,getbulk,set etc.
		pdu.setType(PDU.GET);
		// ����PDU��OID��Ϣ
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0")));
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0")));
		// pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5")));
		// ����Ҳ��������������OID�ķ�ʽ
		// pdu.add(new VariableBinding(new OID(new int[]{1,3,6,1,2,1,1,5,0})));

		return pdu;
	}

	/*
	 * ͬ��ģʽ������Ϣ
	 */
	public void sendPDU() {

		CommunityTarget target = setPDU();
		PDU newPdu = createPDU();

		try {
			// ͬ��ģʽ������Ϣ
			ResponseEvent msg = snmp.send(newPdu, target);

			System.out.println("ͬ��������ʼ");
			// ������Ϣ
			readReponse(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * �����첽��Ϣ
	 */
	public void sendAsynPDU() throws IOException, InterruptedException {
		CommunityTarget target = setPDU();
		PDU pdu = createPDU();

		ResponseListener listener = new ResponseListener() {

			@Override
			public void onResponse(ResponseEvent msg) {
				System.out.println("�첽������ʼ��������");
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
		// һ��ҪҪ���̵߳ȴ������ӣ�����ȡ������Ϣ��
		Thread.sleep(6000);
	}

	/**
	 * ������Ϣ
	 * 
	 * @param msgΪ��Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public void readReponse(ResponseEvent msg) {

		PDU resPDU = msg.getResponse();
		// �����жϷ��ص���Ϣ�Ƿ�Ϊ�գ������Ϊ�գ�Ȼ���ٽ�����ȡ�������ݡ�
		if (msg != null && resPDU != null) {

			// ��ȡ����״̬
			int errorStatus = resPDU.getErrorStatus();
			// ��ȡ����ָ��
			int errorIndex = resPDU.getErrorIndex();
			// ��ȡ����״̬����
			String errorStatusText = resPDU.getErrorStatusText();
			
			//�жϻ�ȡ��PDU�Ƿ��д����û����ȡ��Ϣ������д����������Ϣ
			if (errorStatus == PDU.noError) {
				// ��ȡ���ؽ����������
				Vector<VariableBinding> res = msg.getResponse()
						.getVariableBindings();

				// �����������ݣ�������ʵ���Ϣ
				for (int i = 0; i < res.size(); i++) {
					VariableBinding rec = res.elementAt(i);
					System.out.println(rec.getOid() + ": " + rec.getVariable());
				}
			}else {
				//��ʾ����ʧ�ܣ����������������Ϣ
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
