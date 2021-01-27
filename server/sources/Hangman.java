package server;

import java.io.File;
import java.util.*;
import java.io.IOException;

public class Hangman{
	Array board = new Array(7, 7);

	public String word = "";
	public StringBuilder guess = new StringBuilder("______");
	public int misses;

	public Hangman(){
		String [][]b = new String[7][7];
        	for (int i=0; i<7; i++)
        	{
                	for (int j=0; j<7; j++)
                        	b[i][j]=" ";
        	}
        	b[1][4]="|";
        	for (int j=0; j<=4; j++)
                	b[0][j]="-";
        	for (int i=1; i<=6; i++)
                	b[i][0]="|";

		board.Initialize(b);

		try {
  			randomString();
		}
		catch(IOException e) {
 			e.printStackTrace();
		}


	}

	public void randomString() throws IOException{
		String rand_word = "";
		// generate random word.
		Random rand = new Random();
		File file = new File("./resources/data.txt");
        	Scanner sc  = new Scanner(file);
		while(sc.hasNextLine()){
			String data = sc.nextLine();
			String[] arr = data.split(",");
			int rand_num = rand.nextInt(arr.length);
			rand_word = arr[rand_num];
		}
		this.word = rand_word;
	}



	public String PrintBoard()
	{
		return board.Print();
	}

	public void Guess(char guessLetter){
        	boolean correct=false;
        	for (int i=0; i<word.length(); i++)
        	{
                	if (guessLetter==word.charAt(i))
                	{
                        	guess.setCharAt(i, guessLetter);
                        	correct=true;
                	}
        	}
        	if (correct==false)
                	misses++;
	}

	public String Redraw()
	{
        	switch (misses)
        	{
        	case 1:
                	board.Update(2, 3, "(");
                	board.Update(2, 5, ")");
                	break;
        	case 2:
                	board.Update(3, 4, "|");
                	board.Update(4, 4, "|");
                	break;
        	case 3:
                	board.Update(3, 3, "/");
                	break;
		case 4:
                	board.Update(3, 5, "\\");
			break;
		case 5:
			board.Update(5, 3, "|");
			board.Update(6, 2, "-");
                	break;
		case 6:
			board.Update(5, 6, "|");
			board.Update(6, 6, "-");
			break;
		default:
                	break;
        	}
        	return PrintBoard();
	}

	public void Print(StringBuilder guess)
	{
        	System.out.println(guess);
	}

	public boolean GameOver()
	{
        	if (misses==10)
        	{
                	System.out.println("You lose!");
                	return true;
        	}
        	else if (guess.indexOf("_")<0) //no  more '_'s in the user-visible word
        	{
                	System.out.println("You win!");
                	return true;
        	}
	return false;
	}

	public String Play(String word){
                char letter;
		letter=word.charAt(0);
                Guess(letter);
                return Redraw();
	}

}
