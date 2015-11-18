package cn.met0.serialport;

import java.io.IOException;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;


public class MySerialPort extends ISerialPort {


	
	
	

	


	public MySerialPort(SerialPort serial) throws IOException {
		super(serial); 
	}



	/**
	 * 写入数据
	 * @param date
	 * @throws IOException
	 */
	public void write(byte[] date) throws IOException{
		System.out.println("-------------------------------\nWRITE:\t" + new String(date));
		os.write(date);
		os.flush();
		os.close();
	}
	
	
	
	/**
	 * 读取读卡器数据
	 * @return 16进制字符串
	 * @throws IOException
	 */
	public String read() throws Exception{
		String response = null;
		int i = 0;
		Thread.sleep(300);
		while((i = is.available()) != 0){
			
			byte[] date = new byte[i];
			is.read(date);
			if(response != null){
				response += new String(date);
			}else{
				response = new String(date);
			}
			Thread.sleep(50);
		}
		
		System.out.println("READ:\t" + response);
		return response;
	}
	
	
	public static MySerialPort getSerialPort(String comm) throws NoSuchPortException, PortInUseException, IOException, UnsupportedCommOperationException{
		SerialPort sp = (SerialPort) CommPortIdentifier.getPortIdentifier(comm).open(comm, 1000);
	/*	sp.setSerialPortParams(115200,  
				SerialPort.DATABITS_8,  
				SerialPort.STOPBITS_1,  
				SerialPort.PARITY_NONE);   */
				
		return new MySerialPort(sp);
	}



	@Override
	public String close() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
