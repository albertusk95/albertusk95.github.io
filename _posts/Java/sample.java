class sample {
	
	public void func1() {
		int val1_int = 1;
		
		func2();	// line 7
	}
	
	public void func2() {
		String val2_str = "func2 string value";
	}
	
	public void func3(Object obj) {
		String val3_str = "func3 string value";
	}
	
	public static void main(String[] args) {
		int val0_int = 1;
		
		Object obj = new Object();	// line 21
		
		sample x = new sample();	// line 23
		x.func1();					// line 24
		x.func3(obj);				// line 25
	}
	
}