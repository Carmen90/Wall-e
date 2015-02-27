package tp.pr3.cityLoader.cityLoaderExceptions;

@SuppressWarnings("serial")
	public class WrongCityFormatException extends Exception {
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

