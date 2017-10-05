import com_server.ComunicacionServidor;

import processing.core.PApplet;

public class Ejemplo extends PApplet {
	private String textoTurno;
	private ComunicacionServidor comS;
	

// TIEMPO 

	boolean empezarTiempo= false;
	boolean escogio = false;
	

	
	
	public void setup() {
		textoTurno = "";
		size(600, 600);
		comS = new ComunicacionServidor(5001);

		
		
	}

	public void draw() {
		
		
		comS.actualSecs = millis()/1000; 
		  comS.actualMins = millis() /1000 / 60;
		  comS.scrnSecs =  comS.actualSecs -  comS.restartSecs;
		 comS.scrnMins =  comS.actualMins -  comS.restartMins; 
		

		switch (comS.turno) {
		case 0:
			background(255, 0, 0);
			text("instrucciones1", (width / 2) - 60, 50);
comS.setTurnoActivo(true);
			
			break;

		case 1:
			background(0, 255, 0);
			text("instrucciones2", (width / 2) - 60, 50);
			comS.setTurnoActivo(true);
			break;

		case 2:
			background(0, 0, 255);
			text("instrucciones3", (width / 2) - 60, 50);
			comS.setTurnoActivo(true);

			break;

		case 3:

			// AQUI VA EL JUEGO Y COMIENZAN YA LAS RONDAS DE JUEGO O EL CICLO
		
			if (comS.getTurnoOtroJugador()==3 && comS.isEmpezartiempo()==true) {
				
			}else if (comS.getTurnoOtroJugador()==3) {
			comS.setTurnoActivo(true);
		}
			
			
			background(0, 255, 255);
			text("Juego", (width / 2) - 60, 50);

			// switch de ciclos los ciclos son: Seleccionar un Jugador,
			// Seleccionar una Intencion, Ver una intencion, Seleecionar una
			// Accion, Ver la cuerda moverse en el rango.
		
		
			 if ( comS.isEmpezartiempo()==true) { 
				 empezarTiempo=true; 
				  } 
			 
			if (mousePressed && comS.isTurnoActivo()==true) {
			comS.setTurnoActivo(false);
			}

			 
			 if (empezarTiempo==true) {
				 if (comS.scrnSecs>=10) {
					comS.cicloJuego++;
					comS.setTurnoActivo(true);
					 comS.restartSecs =  comS.actualSecs; //stores elapsed SECONDS
					 comS.scrnSecs =  comS.startSec; //restart screen timer  
					 comS.restartMins =  comS.actualMins; //stores elapsed MINUTES
					 comS.scrnMins =  comS.startMin; //restart screen timer
					
				} 
				
			}
			 
			 
				  if ( comS.actualSecs % 60 == 0) { //after 60 secs, restart second timer  
					  comS.restartSecs =  comS.actualSecs;   //placeholder for this second in time
					  comS.scrnSecs =  comS.startSec; //reset to zero
				    
				    }
		
				 
				  
			
			
				  
				 
			
			
			
			switch (comS.cicloJuego) {

			case 0:

				//text(comS.cicloJuego, 20, 30);
				background(255);
				fill(255,0,0);
				
				
				rect(150, 50, 70, 100);
				rect(250, 50, 70, 100);
				rect(350, 50, 70, 100);
				rect(50, 50, 70, 100);
				rect(450, 50, 70, 100);
				
				rect(150, 350, 70, 100);
				rect(250, 350, 70, 100);
				rect(350, 350, 70, 100);
				rect(50, 350, 70, 100);
				rect(450, 350, 70, 100);
				
				if (comS.isTurnoActivo()==false) {
					fill(0,0,0,95);
					rect(0,0, 800, 800);
					fill(255);
				text("ESPERANDO JUGADOR", width/2-150, height/2);
				}
				
				
				break;

			case 1:
				text(comS.cicloJuego, 20, 30);
				break;

			case 2:
				text(comS.cicloJuego, 20, 30);
				break;

			case 3:
				text(comS.cicloJuego, 20, 30);
				break;

			case 4:
				text(comS.cicloJuego, 20, 30);
				break;


				
				
			}
			
			fill(0);
			textSize(20);
			
			 if (empezarTiempo==true) {
				  text(nf( comS.scrnMins, 2) + " : " + nf( comS.scrnSecs, 2), 20, 20);
			}else {
				text(nf(00, 2) + " : " + nf(00, 2), 20, 20);
			}
			

			if (comS.cicloJuego>=5) {
				comS.cicloJuego=0;
			}
			
			
			// AQUIIII ACABA EL JUEGOOOO!!!

			break;

		case 4:
			background(255, 0, 255);
			text("Juego2", (width / 2) - 60, 50);

			break;
		}

		fill(0);
		textoTurno = "" + comS.getTurno();
		text(textoTurno, 200, 200);
		text("Servidor", 200, 150);

		if (comS.turno > 4) {
			comS.turno = 3;

		}

	}

	public void mousePressed() {
		if (comS.isTurnoActivo()) {
			comS.enviar("hola Icesi soy server");
		
		}
		
	}
	
	
}
