package application;

public class Main {
	
	public static void main(String []args) {
		RegistrationForm register = new RegistrationForm();
		LoginForm login = new LoginForm();
		PrintAlbums printAlbums = new PrintAlbums();
		AddAlbum addAlbum = new AddAlbum();
		AddComment addComment = new AddComment();
		
		// nu exista legaturi intre formulare si nici user session
		// momentan interfata grafica nu este gata
		
		//register.launchRegister(args);
		
		//login.launchLogin(args);
		
		//print albums posted by a specific user (default anca)
		//printAlbums.launchPrintAlbums(args);
		
		//add albums (default artist anca)
		//addAlbum.launchAddAlbum(args);
		
		//add coment to a specific artist(default anca)
		//addComment.launchAddComment(args);
		
	}
}
