package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public Hangman game;


    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run(){
        String received;
	game = new Hangman();

	String state = game.PrintBoard();
	String guess = game.guess.toString();

        while (true)
        {
            try {
		dos.writeUTF("The word is " + game.guess.toString() + "\n" + "Your current state is \n" + state + "\n" + "Enter your guess: ");


                received = dis.readUTF();

		// EXIT CALL
                if(received.equals("Exit")){
                    System.out.println("Closing connection in port "+ this.s.getPort());
                    this.s.close();
                    break;
                }

		// call game.play
		if(game.misses < 10){
			dos.writeUTF("you entered: "+ received);
			state = game.Play(received);
		}

		if(game.misses == 10){
			dos.writeUTF("GAME OVER!!" + "\nThe word is " + game.word);
			break;
		}

		if(game.guess.indexOf("_")<0){
			dos.writeUTF("Hurray, you won!!");
		        break;
		}


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
