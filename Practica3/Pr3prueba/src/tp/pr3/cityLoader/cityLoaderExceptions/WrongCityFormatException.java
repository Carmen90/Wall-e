package tp.pr3.cityLoader.cityLoaderExceptions;

import java.io.IOException;

@SuppressWarnings("serial")
	public class WrongCityFormatException extends IOException {
		public WrongCityFormatException(){
			super();
		}
		
		public WrongCityFormatException(java.lang.String msg){
			super(msg);
		}
		
		public WrongCityFormatException(java.lang.String msg,
	            java.lang.Throwable arg){
			super (msg, arg);
		}
		
		public WrongCityFormatException(java.lang.Throwable arg){
			super(arg);
		}

	}

