package com_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ComunicacionServidor extends Thread {

	/*
	 * En data archivos con opciones de puestas, el servidor escoje con cual
	 * trabajar y le indica al cliente que archivo cargar.
	 */
	private int puerto;
	ServerSocket ss;
	private Socket cliente;
	public int turno;
	public int fuerza;
	private int turnoOtroJugador;
	private int fuerzaOtroJugador;
	private boolean turnoActivo;
	private boolean empezartiempo;
	
	
	public int actualSecs; 
	public int actualMins; 
	public int startSec = 0; 
	public int startMin = 0; 
	public int scrnSecs;
	public int scrnMins=0;
	public int restartSecs=0; 
	public int restartMins=0; 
	
	
	
	
public int cicloJuego;
	public ComunicacionServidor(int i) {
		puerto = i;
		turnoActivo=true;
		try {
			ss = new ServerSocket(i);
			cliente = ss.accept();
			System.out.println("Conectado exitosamente :)");
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

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
			entradaBytes = cliente.getInputStream();
			entradaDatos = new DataInputStream(entradaBytes);
			String mensaje = entradaDatos.readUTF();
			System.out.println("aviso recibido:  " + mensaje);

			// si llega turno revisa si mi turno es igual o menor al turno del
			// otro jugador, si es asi turnoActivo=true;
			
			
			if (mensaje.contains("turno")) {
				String[] partes = mensaje.split("/");

				turnoOtroJugador = Integer.parseInt(partes[1]);
				
				System.out.println("miTurno_server: "+turno+" "+"turnoOtro_server: "+turnoOtroJugador);
			
				
				
				 if (turnoOtroJugador==3) {
					 empezartiempo = true;
					turnoActivo=true;
					
					 restartSecs = actualSecs; 
					    scrnSecs = startSec; 
					    restartMins = actualMins; 
					    scrnMins = startMin;
					
				}	
					
				//} else {
					//turnoActivo = false;
				//}
			}
			
			
			if (mensaje.contains("fuerza")) {
				String[] partes = mensaje.split("/");

				fuerzaOtroJugador = Integer.parseInt(partes[1]);
				
				System.out.println("FUERZA_server: "+fuerza+" "+"FUERZA_cliente: "+fuerzaOtroJugador);
		
				
				
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
			salidaBytes = cliente.getOutputStream();
			salidaDatos = new DataOutputStream(salidaBytes);
			salidaDatos.writeUTF(msj);
			System.out.println("mensaje enviado: " + msj);
			salidaDatos.flush();
			// Tras enviar se aumenta el turno en 1 y se envia;
			
			if (turno < 3) {
				turno++;
				
			}

			
			
			
			
			//turnoActivo=false;
			salidaDatos.writeUTF("turno/"+ turno);
			salidaDatos.writeUTF("fuerza/"+ fuerza);
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

	public int getTurnoOtroJugador() {
		return turnoOtroJugador;
	}

	public void setTurnoOtroJugador(int turnoOtroJugador) {
		this.turnoOtroJugador = turnoOtroJugador;
	}

	public void setTurnoActivo(boolean turnoActivo) {
		this.turnoActivo = turnoActivo;
	}

	public boolean isEmpezartiempo() {
		return empezartiempo;
	}

	public void setEmpezartiempo(boolean empezartiempo) {
		this.empezartiempo = empezartiempo;
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	public int getActualSecs() {
		return actualSecs;
	}

	public void setActualSecs(int actualSecs) {
		this.actualSecs = actualSecs;
	}

	public int getActualMins() {
		return actualMins;
	}

	public void setActualMins(int actualMins) {
		this.actualMins = actualMins;
	}

	public int getStartSec() {
		return startSec;
	}

	public void setStartSec(int startSec) {
		this.startSec = startSec;
	}

	public int getStartMin() {
		return startMin;
	}

	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}

	public int getScrnSecs() {
		return scrnSecs;
	}

	public void setScrnSecs(int scrnSecs) {
		this.scrnSecs = scrnSecs;
	}

	public int getScrnMins() {
		return scrnMins;
	}

	public void setScrnMins(int scrnMins) {
		this.scrnMins = scrnMins;
	}

	public int getRestartSecs() {
		return restartSecs;
	}

	public void setRestartSecs(int restartSecs) {
		this.restartSecs = restartSecs;
	}

	public int getRestartMins() {
		return restartMins;
	}

	public void setRestartMins(int restartMins) {
		this.restartMins = restartMins;
	}
	
	
	
	
	
}