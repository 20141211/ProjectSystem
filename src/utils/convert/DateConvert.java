package utils.convert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConvert extends StrutsTypeConverter{

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println(1+"-------------------------------------------------");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String strdate=   values[0];
       Date d=null;
		try {
			d = sdf.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return d;
	}

	@Override
	public String convertToString(Map context, Object o) {
		// TODO Auto-generated method stub
		System.out.println(2+"-------------------------------------------------");
		Date date=(Date)context;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		
		return sdf.format(date);
	}

}
