package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pos.util.jersey.json.XJsonUtil;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<BarcodeEntity> barl= new ArrayList<BarcodeEntity>();
		
		try {
			new XJsonUtil().saveToFile("./test.json", barl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
