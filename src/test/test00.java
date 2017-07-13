package test;

import org.service.NVRService;
import org.service.imp.NVRServiceImp;

import speed.dao.SensorsDao;
import speed.dao.imp.SensorsDaoImp;

public class test00 {
	public static void main(String[] args) throws Exception {
//		NVRService nService = new NVRServiceImp();
//		nService.getNVRInfo();
		
		SensorsDao sDao = new SensorsDaoImp();
		System.out.println(sDao.getAllSensors(null, null));
	}
}
