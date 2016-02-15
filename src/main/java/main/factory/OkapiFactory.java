package main.factory;

import java.lang.reflect.Method;

import org.javokapi;


public class OkapiFactory {

	javokapi okapiInterface = null;

	public javokapi getInstance () {

		if (okapiInterface == null ) 
			okapiInterface = new javokapi();

			return okapiInterface;
	}

}
