package com.ttl.ito.common.propertiesmanager;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.ttl.ito.business.beans.AddOnComponent;
import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.ConsultantDetails;
import com.ttl.ito.business.beans.CustomerDetails;
import com.ttl.ito.business.beans.CustomerProfileForm;
import com.ttl.ito.business.beans.EndUserDetails;
import com.ttl.ito.business.beans.F2FForm;
import com.ttl.ito.business.beans.FirmDetails;
import com.ttl.ito.business.beans.QuestionsBean;
import com.ttl.ito.business.beans.QuestionsEntity;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.beans.SaveQuesDetails;
import com.ttl.ito.business.beans.ScopeOfSupply;
import com.ttl.ito.business.beans.SelectBox;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.common.Utility.UtilityMethods;

public class MysqlConTest {
	public static void main(String args[]) {

		PreparedStatement preparedStatement = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetAddOns = null;
		ResultSet rs = null;
		ResultSet resultSetMsg1 = null;
		ResultSet resultSetQuot = null;
		ResultSet resultSetMsg2 = null;
		ResultSet resultSetQuestions = null;
		ResultSet resultsetScope = null;
		ResultSet resultSetConsultantDetails = null;
		ResultSet resultSetEnduserDetails = null;
		int resultOutParameterInt1 = -1;
		String resultOutParameterString1 = null;
		Connection connection = null;
		
		ResultSet resultSetExhaustList = null;
		ResultSet resultSetVariantList = null;
		QuotationForm quotationForm = new QuotationForm();
		List<QuestionsEntity> questionsEntityList = new ArrayList<QuestionsEntity>();
		Map<Integer, QuestionsEntity> quesMap = new HashMap<Integer, QuestionsEntity>();
		
		List<QuestionsEntity> questionsList = new ArrayList<QuestionsEntity>();
		Map<Integer, QuestionsEntity> questions = new HashMap<Integer, QuestionsEntity>();
		List<QuestionsEntity> quesVal = new ArrayList<>();
		int r=0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			AdminForm adminForm = new AdminForm();
			System.out.println("DB Connection started");
			String userName = "sa";
			String password = "Prod@123";
			String url = "jdbc:sqlserver://TTLITOPRD\\TTLITOPROD:1433;databaseName=PROD";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("DB Connection started");
			//connection = jdbcTemplate.getDataSource().getConnection();	
			/*String oppSeqNo="TurbineCostEstimation";
			StringBuffer region=new StringBuffer();
			for(int i=0;i<oppSeqNo.length();i++){
				char s=oppSeqNo.charAt(i);
				if(Character.isUpperCase(oppSeqNo.charAt(i))){
					Character.toLowerCase(oppSeqNo.charAt(i));
					region.append(oppSeqNo.charAt(i));
					region.append("_");
				}else{
					region.append(oppSeqNo.charAt(i));
				}
			}
			String reg=region.toString();
			System.out.println(reg);*/
			
			/*String s1 = "TomMarvoloRiddle";
			String s2 = "IamLordVoldemort";
			s1 = s1.toLowerCase();
			s2 = s2.toLowerCase();
			System.out.println(s1 + "  " + s2);

			boolean anagram = false;
			List<Character> a1 = new ArrayList<>();
			List<Character> a2 = new ArrayList<>();
			a1 = prepArray(s1, a1);
			a2 = prepArray(s2, a2);
			a1.sort(null);
			a2.sort(null);

			for (int i = 0; i < a1.size(); i++) {

				System.out.println(a1);
				System.out.println(a2);
				if (a1.get(i) == a2.get(i)) {
					anagram = true;
				} else {
					anagram = false;

					break;

				}

			}*/
			//System.out.println(" anagram " + anagram);
			
		/*	quotationDao.fetchQuotCacheData(quotationForm);
			
			
			adminForm.setDropDownColumnvalues(quotationForm.getDropDownColumnvalues());
			
				boolean contains = false;
				
				for (TurbineDetails bean1 : quotationForm.getDropDownColumnvalues().getFrameWithPowerList().get("FRAMES_WITH_POWER")) {
					for (TurbineDetails bean2 : oldframeList) {
						if (bean1.getFramePowerId() == bean2.getFramePowerId()) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						newFramePowerList.add(bean1);
					} else {
						contains = false;
					}
				}
				adminForm.setNewFrameWithPowersList(newFramePowerList);*/
		
		/*String str="I am Kavya . I am working in TCS . I am working in TCS from 3 years";
	
		String[] splited = str.split("\\s");
		
		Integer count =1;
		Map<String,Integer> hmap = new HashMap<String,Integer>();
		for( int i=0;i<splited.length;i++){
			if(hmap.containsKey(splited[i])){
				count=hmap.get(splited[i]);
				hmap.put(splited[i], ++count);
			}else{
				hmap.put(splited[i], 1);
			}
		}*/
			
			
			//BigDecimal num=10000000;
			List<ReportBean> al= new ArrayList<>();
		
			ReportBean b1= new ReportBean();
			b1.setScopeCode("f2F");
			b1.setItemName("fhbdfnnfdnhd");
			b1.setCost("1000");
			al.add(b1);
			
			ReportBean b2= new ReportBean();
			b2.setScopeCode("f2F");
			b2.setItemName("ioejoiik,g");
			b2.setCost("2000");
			al.add(b2);
			
			ReportBean b3= new ReportBean();
			b3.setScopeCode("f2F");
			b3.setItemName("ghdandkmmcaaf");
			b3.setCost("3000");
			al.add(b3);
			
			
			ReportBean b4= new ReportBean();
			b4.setScopeCode("DBO");
			b4.setItemName("wdsaxzdwqd");
			b4.setCost("4000");
			al.add(b4);
			
			ReportBean b5= new ReportBean();
			b5.setScopeCode("DBO");
			b5.setItemName("iojh,lsas");
			b5.setCost("5000");
			al.add(b5);
			
			System.out.println(al);
			List<String> a2= new ArrayList<>();
			for(int i=0;i<al.size();i++){
			a2.add(al.get(i).getScopeCode());
			}
			System.out.println(a2);
			String frst=al.get(0).getScopeCode();
		for(int i=0;i<al.size();i++){
			System.out.println(a2.lastIndexOf(al.get(i).getScopeCode()));
			if(al.get(i).getScopeCode()==frst){
				System.out.println("111111111111111"+al.get(i).getScopeCode());
			}else{
				System.out.println("222222222222222"+al.get(i).getScopeCode());
				frst=al.get(i).getScopeCode();
			}
		}
			//System.out.println(formatNum.format(num));
		} catch (NullPointerException e) {
			System.out.println("NullPointerException111");
			e.printStackTrace();
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			System.out.println(r);
			//UtilityMethods.closeResource(connection, preparedStatement, rs);
			
		}

	}
	public static int prepArray(){
		try{
			System.out.println("DB Connection started");
			throw new NullPointerException();
			
			}catch(NullPointerException e){
			System.out.println("NullPointerException");
			return 6;
		}finally{
			System.out.println("Inside finally");
			return 7;
		}
	}
	
}
