package com.mapping.wService;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.mapping.entities.Enterprise;
import com.mapping.entities.User;
import com.mapping.services.EnterpriseEjb;
import com.mapping.services.UserEjb;

@Stateless
@WebService(name="WebServic", targetNamespace = "http://localhost:8080/Mapping-ejb/WebServic" )
public class WebServic {
	
	@EJB
	EnterpriseEjb enterpriseAction;
	
	
	
	@WebMethod(action= "http://localhost:8080/Mapping-ejb/WebServic/hola")
	@WebResult(name="parametro",partName="parametro")
	public String hola(){
		return "Diego";
	}
	
	@WebMethod
	public List<Enterprise> lista(){
		List<Enterprise> listaE = new ArrayList<Enterprise>();
		try {
			listaE = enterpriseAction.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaE;
	}
	
	
//	@WebMethod
//	public List<String> prueba1(){
//		List<String> nombres = new ArrayList<String>();
//		nombres.add("Diego");
//		nombres.add("Juan");
////		List<User> usrList = new ArrayList<User>();
////		try {
////			usrList = userAction.findAll();
////			for(User aux: usrList){
////				nombres.add(aux.getUsrName());
////			}
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return nombres;
//	}
//	
//	@WebMethod
//	public void adios(@WebParam(name="metodo2")String param){
//		System.out.println(param);
//	}
//	
//	@WebMethod
//	public String prueba(@WebParam(name="metodo3")String param){
//		System.out.println(param);
//		return param;
//	}
	

}
