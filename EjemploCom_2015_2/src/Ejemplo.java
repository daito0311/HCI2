import com_server.ComunicacionServidor;

import processing.core.PApplet;
import processing.core.PImage;

public class Ejemplo extends PApplet {
	private String textoTurno;
	private ComunicacionServidor comS;

	// TIEMPO

	private boolean empezartiempo;

	// IMAGENES
	private boolean card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
	private PImage inicio, fondo, instrucciones1, instrucciones2;
	private PImage tirar, ceder, siguiente, jugar;
	private PImage pj1, pj2, pj3, pj4, pj5, pj6, pj7, pj8, pj9, pj10;

	public void setup() {
		textoTurno = "";
		size(1200, 700);
		comS = new ComunicacionServidor(5001);

		// CARGAR IMAGENES

		inicio = loadImage("../imagenes/Inicio.png");
		fondo = loadImage("../imagenes/Escenario.png");
		instrucciones1 = loadImage("../imagenes/Instrucciones.png");
		instrucciones2 = loadImage("../imagenes/Premios.png");

		jugar = loadImage("../imagenes/Boton Jugar.png");
		siguiente = loadImage("../imagenes/Boton Siguiente.png");
		tirar = loadImage("../imagenes/Boton Ceder.png");
		ceder = loadImage("../imagenes/Boton Halar.png");

		pj1 = loadImage("../imagenes/1.png");
		pj2 = loadImage("../imagenes/2.png");
		pj3 = loadImage("../imagenes/3.png");
		pj4 = loadImage("../imagenes/4.png");
		pj5 = loadImage("../imagenes/5.png");
		pj6 = loadImage("../imagenes/6.png");
		pj7 = loadImage("../imagenes/7.png");
		pj8 = loadImage("../imagenes/8.png");
		pj9 = loadImage("../imagenes/9.png");
		pj10 = loadImage("../imagenes/10.png");

	}

	public void draw() {
		
	

		comS.actualSecs = millis() / 1000;
		comS.actualMins = millis() / 1000 / 60;
		comS.scrnSecs = comS.actualSecs - comS.restartSecs;
		comS.scrnMins = comS.actualMins - comS.restartMins;

	
		
		
		switch (comS.turno) {
		case 0:
			background(255);
			image(inicio, 0, 0);
			comS.setTurnoActivo(true);
			if (mouseX >= 550 && mouseX <= 550 + 204 && mouseY >= 450 && mouseY <= 450 + 88) {
				image(jugar, 548, 448, 209, 93);
			} else {
				image(jugar, 550, 450, 204, 88);
			}

			break;

		case 1:
			background(255);
			image(instrucciones1, 0, 0);
			comS.setTurnoActivo(true);
			if (mouseX >= 900 && mouseX <= 900 + 204 && mouseY >= 550 && mouseY <= 550 + 88) {
				image(jugar, 898, 548, 209, 93);
			} else {
				image(jugar, 900, 550, 204, 88);
			}

			break;

		case 2:
			background(255);
			image(instrucciones2, 0, 0);
			comS.setTurnoActivo(true);
			if (mouseX >= 900 && mouseX <= 900 + 204 && mouseY >= 550 && mouseY <= 550 + 88) {
				image(jugar, 898, 548, 209, 93);
			} else {
				image(jugar, 900, 550, 204, 88);
			}

			break;

		case 3:

			
			// INICIAR EL JUEGO 
			
				
			
			// AQUI VA EL JUEGO Y COMIENZAN YA LAS RONDAS DE JUEGO O EL CICLO

			if (comS.getTurnoOtroJugador() ==3  && comS.isEmpezartiempo()==true) {
				empezartiempo=true;
			}
			
			background(255);

			text("Juego", (width / 2) - 60, 50);

			// switch de ciclos los ciclos son: Seleccionar un Jugador,
			// Seleccionar una Intencion, Ver una intencion, Seleecionar una
			// Accion, Ver la cuerda moverse en el rango.

			

			// DESACTIVAR EL TURNO UNA VEZ SELECCIONA UNA CARTA

			// if (mousePressed && comS.isTurnoActivo() == true && empezarTiempo
			// == true) {

			// }

			// --------------------------------------------------------------

			if (empezartiempo==true) {
				if (comS.scrnSecs >= 10) {
					comS.cicloJuego++;
					comS.setTurnoActivo(true);
					comS.restartSecs = comS.actualSecs; // stores elapsed
														// SECONDS
					comS.scrnSecs = comS.startSec; // restart screen timer
					comS.restartMins = comS.actualMins; // stores elapsed
														// MINUTES
					comS.scrnMins = comS.startMin; // restart screen timer

				}

			} else {
				comS.setTurnoActivo(false);
			}

			if (comS.actualSecs % 60 == 0) { // after 60 secs, restart second
												// timer
				comS.restartSecs = comS.actualSecs; // placeholder for this
													// second in time
				comS.scrnSecs = comS.startSec; // reset to zero

			}

			switch (comS.cicloJuego) {

			case 0:

				// text(comS.cicloJuego, 20, 30);
				image(fondo, 0, 0);

				// AQUI VA EL DISEÑO DE LA CARTAS Y LAS ZONAS SENSIBLES CUANDO
				// EL MOUSE ESTE ENCIMA DE LA CARTA

				// LINEA SUPERIOR DE CARTAS

				// CARTA 1

				if (card1 == false) {

					if (mouseX >= 60 && mouseX <= 260 && mouseY >= 50 && mouseY <= 300) {
						fill(255, 165, 0);
						rect(59 - 2, 48, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Tongue", 108, 80);
						image(pj1, 93, 100, 136, 161);
						text("Force = 8", 83 + 13, 288);

					} else {
						fill(255, 165, 0);
						rect(50 + 10, 50, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Tongue", 110, 80);
						image(pj1, 95, 100, 131, 156);
						text("Force = 8", 83 + 15, 285);
					}

				} else {
					fill(255, 165, 0);
					rect(50 + 10, 50, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Tongue", 110, 80);
					image(pj1, 95, 100, 131, 156);
					text("Force = 8", 83 + 15, 285);
					fill(0, 0, 0, 80);
					rect(50 + 10, 50, 200, 250, 15);
				}

				// CARTA 2

				if (card2 == false) {

					if (mouseX >= 280 && mouseX <= 480 && mouseY >= 50 && mouseY <= 300) {
						fill(255, 165, 0);
						rect(270 + 7, 48, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Bat", 348, 80);
						image(pj9, 328, 98, 133 - 15, 201 - 35);
						text("Force = 15", 308 + 13, 288);

					} else {
						fill(255, 165, 0);
						rect(270 + 10, 50, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Bat", 350, 80);
						image(pj9, 330, 100, 133 - 20, 201 - 40);
						text("Force = 15", 310 + 15, 285);

					}

				} else {
					fill(255, 165, 0);
					rect(270 + 10, 50, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Bat", 350, 80);
					image(pj9, 330, 100, 133 - 20, 201 - 40);
					text("Force = 15", 310 + 15, 285);
					fill(0, 0, 0, 80);
					rect(270 + 10, 50, 200, 250, 15);
				}

				// CARTA 3

				if (card3 == false) {

					if (mouseX >= 500 && mouseX <= 700 && mouseY >= 50 && mouseY <= 300) {
						fill(255, 165, 0);
						rect(490 + 7, 48, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Cyclope", 543, 78);
						image(pj6, 548, 98, 103, 160);
						text("Force = 4", 529 + 15, 287);
					} else {
						fill(255, 165, 0);
						rect(490 + 10, 50, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Cyclope", 545, 80);
						image(pj6, 550, 100, 98, 155);
						text("Force = 4", 531 + 15, 285);

					}
				} else {
					fill(255, 165, 0);
					rect(490 + 10, 50, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Cyclope", 545, 80);
					image(pj6, 550, 100, 98, 155);
					text("Force = 4", 531 + 15, 285);
					fill(0, 0, 0, 80);
					rect(490 + 10, 50, 200, 250, 15);

				}

				// CARTA 4

				if (card4 == false) {

					if (mouseX >= 720 && mouseX <= 920 && mouseY >= 50 && mouseY <= 300) {
						fill(255, 165, 0);
						rect(710 + 7, 48, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Bot", 743 + 40, 78);
						image(pj10, 753, 98, 138, 160);
						text("Force = 10", 714 + 30 + 15, 287);

					} else {
						fill(255, 165, 0);
						rect(710 + 10, 50, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Bot", 745 + 40, 80);
						image(pj10, 753, 100, 133, 155);
						text("Force = 10", 716 + 30 + 15, 285);
					}
				} else {

					fill(255, 165, 0);
					rect(710 + 10, 50, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Bot", 745 + 40, 80);
					image(pj10, 753, 100, 133, 155);
					text("Force = 10", 716 + 30 + 15, 285);
					fill(0, 0, 0, 80);
					rect(710 + 10, 50, 200, 250, 15);
				}

				// CARTA 5
				if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 50 && mouseY <= 300) {
					fill(255, 165, 0);
					rect(930 + 7, 48, 205, 255, 15);

				} else {
					fill(255, 165, 0);
					rect(930 + 10, 50, 200, 250, 15);
				}

				// LINEA INFERIOR DE CARTAS

				// CARTA 6

				if (mouseX >= 60 && mouseX <= 260 && mouseY >= 350 && mouseY <= 600) {
					rect(59 - 2, 348, 205, 255, 15);
				} else {
					rect(50 + 10, 350, 200, 250, 15);

				}

				// CARTA 7
				if (mouseX >= 280 && mouseX <= 480 && mouseY >= 350 && mouseY <= 600) {
					rect(279 - 2, 348, 205, 255, 15);
				} else {
					rect(270 + 10, 350, 200, 250, 15);
				}

				// CARTA 8

				if (mouseX >= 500 && mouseX <= 700 && mouseY >= 350 && mouseY <= 600) {

					rect(499 - 2, 348, 205, 255, 15);
				} else {
					rect(490 + 10, 350, 200, 250, 15);

				}

				// CARTA 9
				if (mouseX >= 720 && mouseX <= 920 && mouseY >= 350 && mouseY <= 600) {
					rect(719 - 2, 348, 205, 255, 15);
				} else {
					rect(710 + 10, 350, 200, 250, 15);
				}

				// CARTA 10
				if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 350 && mouseY <= 600) {
					rect(939 - 2, 348, 205, 255, 15);
				} else {
					rect(930 + 10, 350, 200, 250, 15);
				}

				if (comS.isTurnoActivo() == false) {
					fill(0, 0, 0, 95);
					rect(0, 0, 1200, 800);
					fill(255);
					textSize(50);
					text("ESPERANDO JUGADOR", width / 2 - 260, height / 2 - 9);
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

			if (empezartiempo == true) {
				text(nf(comS.scrnMins, 2) + " : " + nf(comS.scrnSecs, 2), 20, 20);
			} else {
				text(nf(00, 2) + " : " + nf(00, 2), 20, 20);
			}

			if (comS.cicloJuego >= 5) {
				comS.cicloJuego = 0;
			}

			// AQUIIII ACABA EL JUEGOOOO!!!

			break;

		case 4:
			background(255, 0, 255);
			text("Juego2", (width / 2) - 60, 50);

			break;
		}

		// fill(0);
		// textoTurno = "" + comS.getTurno();
		// text(textoTurno, 200, 200);
		// text("Servidor", 200, 150);

		if (comS.turno > 4) {
			comS.turno = 3;

		}

	}

	public void mousePressed() {
		if (comS.isTurnoActivo()) {
			comS.enviar("hola Icesi soy server");

		}

	}

	@Override
	public void mouseClicked() {

		if (mouseX >= 550 && mouseX <= 550 + 204 && mouseY >= 450 && mouseY <= 450 + 88 && comS.turno == 0) {
			comS.turno++;
		}

		if (mouseX >= 900 && mouseX <= 900 + 204 && mouseY >= 550 && mouseY <= 550 + 88 && comS.turno > 0
				&& comS.turno < 3) {
			comS.turno++;
			
		}
		
		
		

		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			// carta 1
			if (card1 == false) {
				if (mouseX >= 60 && mouseX <= 260 && mouseY >= 50 && mouseY <= 300) {
					comS.fuerza += 8;
					comS.setTurnoActivo(false);
					card1 = true;

				}
			}
		}

		// carta 2
		if (card2 == false) {
			if (mouseX >= 280 && mouseX <= 480 && mouseY >= 50 && mouseY <= 300) {

				if (empezartiempo == true) {
					comS.fuerza += 15;
					comS.setTurnoActivo(false);
					card2 = true;
				}
			}
		}
		// carta 3
		if (card3 == false) {
			if (mouseX >= 500 && mouseX <= 700 && mouseY >= 50 && mouseY <= 300) {

				if (empezartiempo == true) {
					comS.fuerza += 4;
					comS.setTurnoActivo(false);
					card3 = true;
				}

			}
		}

		// carta 4

		if (card4 == false) {
			if (mouseX >= 720 && mouseX <= 920 && mouseY >= 50 && mouseY <= 300) {

				if (empezartiempo == true) {
					comS.fuerza += 10;
					comS.setTurnoActivo(false);
					card4 = true;
				}
			}
		}
	}
}
