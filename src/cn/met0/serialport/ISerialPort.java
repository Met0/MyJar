package cn.met0.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

public abstract class ISerialPort implements SerialPortEventListener {

	protected InputStream is;
	protected OutputStream os;
	protected SerialPort serial;
	
	
	/**
	 * 初始化
	 * @param serial
	 * @throws IOException
	 */
	public ISerialPort(SerialPort serial) throws IOException{
		
		this.serial = serial;
		this.is = serial.getInputStream();
		this.os = serial.getOutputStream();
	}
	
	@Override
	public void serialEvent(SerialPortEvent arg0) {
	
	}
	
	
	public abstract void write(byte[] date) throws Exception;
	
	public abstract String read()  throws Exception;
	
	public abstract String close();
	
	

}
