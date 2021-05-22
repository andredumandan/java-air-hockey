import java.io.*;
import java.net.*;

public class GameServer {
    
    private ServerSocket ss;
    private Socket p1Socket;
    private Socket p2Socket;
    private int numPlayers;
    private int maxPlayers;
    private ReadFromClient p1ReadRunnable;
    private ReadFromClient p2ReadRunnable;
    private WriteToClient p1WriteRunnable;
    private WriteToClient p2WriteRunnable;
    private double p1X, p1Y, p2X, p2Y;

    public GameServer(){
        System.out.println("======SERVER======");
        numPlayers = 0;
        maxPlayers = 2;
        p1X = 169;
        p1Y = 179;
        p2X = 589;
        p2Y = 179;
        try {
            ss = new ServerSocket(50301);
        } catch(IOException ex){
            System.out.println("IOException occured at constructor");
        }
    }

    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");

            while(numPlayers<maxPlayers){
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if (numPlayers == 1){
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                }
                else{
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                }
            }

            System.out.println("No longer accepting connections.");
        } catch(IOException ex){
            System.out.println("IOException occured at acceptConnections");
        }
    }

    private class ReadFromClient implements Runnable {

        private int playerID;
        private DataInputStream dataIn;

        public ReadFromClient(int playerID, DataInputStream dataIn) {
            this.playerID = playerID;
            this.dataIn = dataIn;
            System.out.println("RFC " + playerID + "Runnable created");
        }

        @Override
        public void run() {
            try{
                while(true){
                    if(playerID == 1){
                        p1X = dataIn.readDouble();
                        p1Y = dataIn.readDouble();
                    }
                    else{
                        p2X = dataIn.readDouble();
                        p2Y = dataIn.readDouble();
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from RFC run()");
            }
            
        }
    }

    private class WriteToClient implements Runnable {

        private int playerID;
        private DataOutputStream dataOut;

        public WriteToClient(int playerID, DataOutputStream dataOut) {
            this.playerID = playerID;
            this.dataOut = dataOut;
            System.out.println("WTC " + playerID + "Runnable created");
        }

        @Override
        public void run() {
            try{
                while(true){
                    if(playerID == 1){
                        dataOut.writeDouble(p2X);
                        dataOut.writeDouble(p2Y);
                        dataOut.flush();
                    }
                    else{
                        dataOut.writeDouble(p1X);
                        dataOut.writeDouble(p1Y);
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(25);
                    } catch(InterruptedException ex){
                        System.out.println("InterruptedException from WTC run()");
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from RFC run()");
            }
            
        }

        public void sendStartMsg(){
            try{
                dataOut.writeUTF("Both players have connected. Starting the game...");
            }
            catch(IOException ex){
                System.out.println("IOException from sendStartMsg()");
            }
        }
    }

    public static void main(String[] args){
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }

}