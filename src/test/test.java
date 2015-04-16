package test;

import java.io.IOException;
import java.io.OutputStream;

import org.snmp4j.asn1.BERInputStream;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

public class test {

	public static void setOID() {
		OID oid1 = new OID("1.2.3.4.5");
		VariableBinding vb = new VariableBinding(new OID("0.1.3.2"));
		System.out.println(vb.getOid());
		vb.setOid(oid1);
		//设置给定OID的值，这里的Variable用OctetString 对象来表示
		vb.setVariable(new OctetString("hello world"));
		System.out.println(vb.getVariable().toString());
		System.out.println(vb);
		System.out.println(vb.getOid());
		vb.setVariable(new OctetString("1.2.5.6.0"));
		System.out.println(vb.getVariable().toString());
		System.out.println(vb);
		
	}

	public static void main(String args[]) {
		setOID();
	}
}
