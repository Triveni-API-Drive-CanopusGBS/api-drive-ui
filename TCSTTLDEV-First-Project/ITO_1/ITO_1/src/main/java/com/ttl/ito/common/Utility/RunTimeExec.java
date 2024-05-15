package com.ttl.ito.common.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RunTimeExec {

	private static Logger logger = Logger.getLogger(RunTimeExec.class);

	/*
	 * public static void main(String[] args) { runCmd("WPQ1812009"); }
	 */

	public StreamWrapper getStreamWrapper(InputStream is, String type) {
		return new StreamWrapper(is, type);
	}

	public class StreamWrapper extends Thread {
		InputStream is = null;
		String type = null;
		String message = null;

		public String getMessage() {
			return message;
		}

		StreamWrapper(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}

		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				StringBuffer buffer = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					buffer.append(line);
				}
				message = buffer.toString();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public List runCmd(String numb){
		String methodName = "runCmd(numb)";

		logger.info(methodName + " : START");
		Runtime rt = Runtime.getRuntime();
		RunTimeExec rte = new RunTimeExec();
		StreamWrapper error, output;
		List<Object> newlist = new ArrayList<Object>();
		List<String> outputList = new ArrayList<String>();
		try {
			// String numb="0230000100";
			logger.info(numb);
			
			// Process proc = rt.exec("cmd /c d: && cd ghb && java -jar JAR_SFDC_ITO.jar \"" + numb + "\"");
			//Process proc = rt.exec("cmd /c cd D:/GHB && java -jar JAR_SFDC_ITO_REV2.jar \"" + numb + "\"");
			Process proc = rt.exec("cmd /c cd D:/GHB && java -jar JAR_SFDC_ITO_REV6.jar \"" + numb + "\"");
			// Process proc = rt.exec("cmd /c cd D:/GHB && java -jar
			// JAR_SFDC_ITO.jar SEA11712004 ");

			error = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			int exitVal = 0;

			error.start();
			output.start();
			error.join(0);
			output.join(0);
			exitVal = proc.waitFor();
			/*
			 * System.out.println(output.message.length()>0 &&
			 * output.message.contains(numb));
			 * System.out.println(error.message.length()>0);
			 */
			outputList.add(output.message);
			outputList.add(error.message);

			newlist = Arrays.asList(output.message.split("[|]"));
			logger.info("Output: " + output.message + "\nError: " + error.message);
			for (Object i : newlist) {
				logger.info(i);
			}

			logger.info(methodName + " : END");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return outputList;

	}

}
