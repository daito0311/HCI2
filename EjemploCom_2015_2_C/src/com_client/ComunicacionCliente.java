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

public class ComunicacionCliente extends Thread {

	/*
	 * En data archivos con opciones de puestas, el servidor escoje con cual
	 * trabajar y le indica al cliente que archivo cargar.
	 */
	private int puerto;
	ServerSocket ss;
	private Socket servidor;
	public boolean empezar = false;	
	public int turno;
	private int turnoOtroJugador;
	private boolean turnoActivo;

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
			entradaBytes = servidor.getInputStream();
			entradaDatos = new DataInputStream(entradaBytes);
			String mensaje = entradaDatos.readUTF();
			System.out.println("aviso recibido:  " + mensaje);

			// si llega turno revisa si mi turno es igual o menor al turno del
			// otro jugador, si es asi turnoActivo=true;
			if (mensaje.contains("turno")) {
				String[] partes = mensaje.split("/");

				turnoOtroJugador = Integer.parseInt(partes[1]);
				System.out.println("miTurno_cliente: "+turno+" "+"turnoOtro_cliente: "+turnoOtroJugador);
				//if (turno <= turnoOtroJugador) {
					turnoActivo = true;
				//} else {
				//	turnoActivo = false;
				//}
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
			// Tras enviar se aumenta el turno en 1 y se envia;
			turno++;
			
			if (turno==3) {
				empezar =true;
				
			}
			
			//turnoActivo=false;
			salidaDatos.writeUTF("turno/" + turno);
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

	public boolean isEmpezar() {
		return empezar;
	}

	public void setEmpezar(boolean empezar) {
		this.empezar = empezar;
	}
	
	
}

