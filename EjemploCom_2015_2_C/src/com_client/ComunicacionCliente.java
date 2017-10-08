package com_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import processing.opengl.FrameBuffer;

public class ComunicacionCliente extends Thread {

	/*
	 * En data archivos con opciones de puestas, el servidor escoje con cual
	 * trabajar y le indica al cliente que archivo cargar.
	 */
	private int puerto;
	ServerSocket ss;
	private Socket servidor;
	public boolean gotime = false;	
	public int turno;
	public int fuerza;
	public int escogio;
	private int fuerzaOtroJugador;
	private int turnoOtroJugador;
	private int yaEscogioElOtroJugador;
	private boolean turnoActivo;
	private int scrnSecsOtroJugador;
	private boolean tiro, cedio;
	
	
	public int cicloJuego;
	
	public int actualSecs;
	public int actualMins;
	public int startSec = 0;
	public int startMin = 0;
	public int scrnSecs;
	public int scrnMins = 0;
	public int restartSecs = 0;
	public int restartMins = 0;

	public int intencion;
	public int eleccion;
	
	public int intencionOtroJugador;
	public int eleccionOtroJugador;
	
	public ComunicacionCliente(int i) {
		puerto = i;
	
		turnoActivo=true;
		try {
			servidor = new Socket(InetAddress.getByName("127.0.0.1"), puerto);
			System.out.println("exito!");
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

		start();
	}

	/*
	 * En data archivos con opciones de puestas, el servidor escoje con cual
	 * trabajar y le indica al cliente que archivo cargar.
	 */

	public void run() {
		
		while (true) {
			recibir();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void recibir() {
		InputStream entradaBytes;
		DataInputStream entradaDatos;
		try {
			
		servidor.getReceiveBufferSize();
			
			entradaBytes = servidor.getInputStream();
			entradaDatos = new DataInputStream(entradaBytes);
			String mensaje = entradaDatos.readUTF();
			System.out.println("aviso recibido:  " + mensaje);

			// si llega turno revisa si mi turno es igual o menor al turno del
			// otro jugador, si es asi turnoActivo=true;
			if (mensaje.contains("turno")) {
				String[] partes = mensaje.split("/");

				turnoOtroJugador = Integer.parseInt(partes[1]);
				System.out.println("miTurno_cliente: "+turno+" "+"turno_Server: "+turnoOtroJugador);

			 
				}
				
				
			if (mensaje.contains("eleccion")) {
				String[] partes = mensaje.split("/");

				eleccionOtroJugador = Integer.parseInt(partes[1]);
				System.out.println("Eleccion_Cliente: "+eleccion+" "+"Eleccion_Server: "+eleccionOtroJugador);

				}

			if (mensaje.contains("intencion")) {
				String[] partes = mensaje.split("/");

				intencionOtroJugador = Integer.parseInt(partes[1]);
				System.out.println("Intencion_Cliente: "+intencion+" "+"Intencion_Server: "+intencionOtroJugador);

				
				
				
				
				}
			
			
			
			if (mensaje.contains("fuerza")) {
				String[] partes = mensaje.split("/");

				fuerzaOtroJugador = Integer.parseInt(partes[1]);
				System.out.println("miFuerza_cliente: "+fuerza+" "+"Fuerza_Server: "+fuerzaOtroJugador);
				
			}
			
			if (mensaje.contains("escogio")) {
				String[] partes = mensaje.split("/");

				yaEscogioElOtroJugador = Integer.parseInt(partes[1]);

				System.out.println("Cliente_YaEscogio " + escogio);

				
				}
			
			if (mensaje.contains("Segundos")) {
				String[] partes = mensaje.split("/");

				scrnSecsOtroJugador = Integer.parseInt(partes[1]);

				

			}
			


		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * El protocolo de envio es el siguiente: puerta(entero). El protocolo de
	 * envio de confianza es: confianza/ValorHonestidad/ValorConfianza.
	 */
	public void enviar(String msj) {
		OutputStream salidaBytes;
		DataOutputStream salidaDatos;
		try {
			salidaBytes = servidor.getOutputStream();
			salidaDatos = new DataOutputStream(salidaBytes);
			salidaDatos.writeUTF(msj);
			System.out.println("mensaje enviado: " + msj);
			salidaDatos.flush();
			
			


			if (gotime==false) {
				
			
			if ((turnoOtroJugador==3 || turnoOtroJugador==2 ) && turno ==3) {
				turnoActivo=true;
				gotime=true;
			
				 restartSecs = actualSecs; //stores elapsed SECONDS
				    scrnSecs = startSec; //restart screen timer  
				    restartMins = actualMins; //stores elapsed MINUTES
				    scrnMins = startMin; //restart screen timer
				 //if mouse is pressed, restart timer	
			}
		
			}
			
			//turnoActivo=false;
			salidaDatos.writeUTF("turno/" + turno);
			salidaDatos.writeUTF("fuerza/" + fuerza);
			salidaDatos.writeUTF("escogio/" + escogio);
			salidaDatos.writeUTF("eleccion/" + eleccion);
			salidaDatos.writeUTF("intencion/" + intencion);
			salidaDatos.writeUTF("Segundos/" + scrnSecs);
			salidaDatos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getTurno() {
		return turno;
	}

	public boolean isTurnoActivo() {
		return turnoActivo;

	}

	

	public void setTurnoActivo(boolean turnoActivo) {
		this.turnoActivo = turnoActivo;
	}

	public int getTurnoOtroJugador() {
		return turnoOtroJugador;
	}

	public void setTurnoOtroJugador(int turnoOtroJugador) {
		this.turnoOtroJugador = turnoOtroJugador;
	}


	
	
	
	
}

