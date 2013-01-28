package org.p632.getFB;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Dictionary;

import javax.swing.JOptionPane;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.cishell.framework.CIShellContext;
import org.cishell.framework.algorithm.Algorithm;
import org.cishell.framework.algorithm.AlgorithmExecutionException;
import org.cishell.framework.data.Data;
import org.osgi.service.log.LogService;

public class GetFaceBookData implements Algorithm {
	private Data[] data;
	private Dictionary parameters;
	private CIShellContext ciShellContext;
	private LogService logger;

	public GetFaceBookData(Data[] data, Dictionary parameters,
			CIShellContext ciShellContext) {
		this.data = data;
		this.parameters = parameters;
		this.ciShellContext = ciShellContext;
		this.logger = (LogService) ciShellContext.getService(LogService.class
				.getName());
	}
	
	public Data[] execute() {
		this.logger.log(LogService.LOG_INFO, "Opening Facebook login page");
		// logger.log(LogService.LOG_WARNING, "Warning msg");
		// logger.log(LogService.LOG_ERROR, "Error msg");
		// logger.log(LogService.LOG_DEBUG, "Debug msg");
		
		try {
			URI url = new URI("https://www.facebook.com/dialog/oauth?client_id=283202715139589&redirect_uri=https://morning-fjord-1741.herokuapp.com/token.php&scope=manage_friendlists&response_type=token");		
			Desktop.getDesktop().browse(url);			
		} catch (URISyntaxException e1) {
			logger.log(LogService.LOG_INFO, e1.getMessage());
		} catch (IOException e1) {
			logger.log(LogService.LOG_INFO, e1.getMessage());
		}
		
		String input =  JOptionPane.showInputDialog("Enter Access Token:");
		
		this.logger.log(LogService.LOG_INFO, "Access Token: "+ input);
		String data = "access_token="+input;
		httpRequest(data);
		return null;
	}

	public String httpRequest(String data){
		String responseBody = "";
		int returnCode;
		String url = "https://graph.facebook.com/me?"+data;

//		HttpClient client = new HttpClient();
//		GetMethod method = new GetMethod(url);
//		try {						
//			returnCode = client.executeMethod(method);
//			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
//				logger.log(LogService.LOG_ERROR,
//						"The Post method is not implemented by this URI");
//			} else {
//				logger.log(LogService.LOG_DEBUG, "Posted");
//			}
//			responseBody = method.getResponseBodyAsString();
//			logger.log(LogService.LOG_INFO, "response:" + responseBody);
//			method.releaseConnection();
//		} catch (HttpException e1) {
//			this.logger.log(LogService.LOG_INFO, e1.getMessage());
//		} catch (IOException e1) {
//			this.logger.log(LogService.LOG_INFO, e1.getMessage()); 
//		} finally {
//			method.releaseConnection();
//		}
		return responseBody;
	}

}