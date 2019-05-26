package chat.network;


import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;


    public class TCPConnection {

        private final Socket socket;
        private final Thread rxThread;
        private final TCPConnectionListener eventlistener;
        private final ObjectInputStream ois;
        private final ObjectOutputStream oos;

        public TCPConnection(TCPConnectionListener eventlistener, String ipAddr, int port, boolean identification) throws IOException {
            this(eventlistener,new Socket(ipAddr,port));
        }

        public TCPConnection(TCPConnectionListener eventlistener, Socket socket) throws IOException {
            this.eventlistener=eventlistener;
            this.socket=socket;
            ois= new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            rxThread= new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        eventlistener.onConnectionReady(TCPConnection.this);
                        while(!rxThread.isInterrupted()){
                            try {
                                eventlistener.onContainerTransfer(TCPConnection.this,(UserData)ois.readObject());
                            } catch (ClassNotFoundException e) {
                            }
                        }
                    }catch (IOException e){
                        eventlistener.onException(TCPConnection.this, e);
                    }finally {
                        eventlistener.onDisconnect(TCPConnection.this);
                    }
                }
            });
            rxThread.start();
        }

        public synchronized void Disconnect(){
            rxThread.interrupt();
            try {
                socket.close();
            } catch (IOException e) {
                eventlistener.onException(TCPConnection.this, e);
            }
        }
        public synchronized void ConteinerTransfer (UserData UD){
            try {
                oos.writeObject(UD);
            } catch (IOException e) {
                eventlistener.onException(TCPConnection.this, e);
            }
        }

        @Override
        public String toString() {
            return "TCPConnection: "+socket.getInetAddress()+": "+socket.getPort();
        }
    }


