package fmi.informatics.interfaces;

import java.util.Calendar;

/**
 * 
 * @author Konstantin Rusev
 *
 */
public interface Active {
	
	public void getUpEarly(Calendar hour);
	
	public void run(int minutes);
	
	public void getShower();
}
