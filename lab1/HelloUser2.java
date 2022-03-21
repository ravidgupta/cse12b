// Ravi Gupta
// radgupta
// CMPS12B/M
// 4/10/19
// HelloUser2.java
// Prints Hello to the user and wishes the best to the user
class HelloUser2{
	public static void main( String[] args ) {
		String userName = System.getProperty("user.name");
		System.out.println( "Hello " + userName);
		System.out.println( "Hope your day goes wonderfully!");
	}
}		
