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
	private PImage rope, principal, inicial2;

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

		principal = loadImage("../imagenes/inicial.png");
		inicial2 = loadImage("../imagenes/inicial2.png");
		rope = loadImage("../imagenes/Lazo.png");

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

			text("Juego", (width / 2) - 60, 50);

			// switch de ciclos los ciclos son: Seleccionar un Jugador,
			// Seleccionar una Intencion, Ver una intencion, Seleecionar una
			// Accion, Ver la cuerda moverse en el rango.

			if (comS.gotime == true) {
				empezartiempo = true;
			}

			if (comS.cicloJuego == 0) {

				if (empezartiempo == true) {

					if (comS.scrnSecs >= 10) {
						comS.escogio = 0;
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

			}

			if (comS.cicloJuego == 1) {
				if (comS.scrnSecs >= 10) {
					comS.escogio = 0;
					comS.cicloJuego++;
					comS.setTurnoActivo(true);
					comS.restartSecs = comS.actualSecs; // stores elapsed
														// SECONDS
					comS.scrnSecs = comS.startSec; // restart screen timer
					comS.restartMins = comS.actualMins; // stores elapsed
														// MINUTES
					comS.scrnMins = comS.startMin; // restart screen timer

				}
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

				if (card5 == false) {
					if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 50 && mouseY <= 300) {
						fill(255, 165, 0);
						rect(930 + 7, 48, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Gum", 943 + 60, 78);
						image(pj7, 975, 98, 133 + 5, 156 + 5);
						text("Force = 2", 939 + 27 + 20, 287);

					} else {
						fill(255, 165, 0);
						rect(930 + 10, 50, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Gum", 945 + 60, 80);
						image(pj7, 978, 100, 133, 156);
						text("Force = 2", 939 + 30 + 20, 285);
					}
				} else {
					fill(255, 165, 0);
					rect(930 + 10, 50, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Gum", 945 + 60, 80);
					image(pj7, 978, 100, 133, 156);
					text("Force = 2", 939 + 30 + 20, 285);
					fill(0, 0, 0, 80);
					rect(930 + 10, 50, 200, 250, 15);
				}

				// LINEA INFERIOR DE CARTAS

				// CARTA 6
				if (card6 == false) {
					if (mouseX >= 60 && mouseX <= 260 && mouseY >= 350 && mouseY <= 600) {
						fill(255, 165, 0);
						rect(59 - 2, 348, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Blue", 115 + 8, 348 + 30);
						image(pj2, 93, 348 + 44 + 3, 141 + 3, 166);
						text("Force = 4", 83 + 5 + 13, 348 + 240);

					} else {
						fill(255, 165, 0);
						rect(50 + 10, 350, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Blue", 115 + 10, 348 + 30);
						image(pj2, 93, 348 + 50, 136, 161);
						text("Force = 4", 83 + 13 + 10, 348 + 236);

					}
				} else {
					fill(255, 165, 0);
					rect(50 + 10, 350, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Blue", 115 + 10, 348 + 30);
					image(pj2, 93, 348 + 50, 136, 161);
					text("Force = 4", 83 + 13 + 10, 348 + 236);
					fill(0, 0, 0, 80);
					rect(50 + 10, 350, 200, 250, 15);
				}

				// CARTA 7
				if (card7 == false) {
					if (mouseX >= 280 && mouseX <= 480 && mouseY >= 350 && mouseY <= 600) {
						fill(255, 165, 0);
						rect(279 - 2, 348, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Black", 348 - 10, 348 + 30);
						image(pj3, 323, 348 + 47, 131 + 5, 156 + 5);
						text("Force = 9", 308 + 5 + 13, 348 + 240);
					} else {
						fill(255, 165, 0);
						rect(270 + 10, 350, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Black", 348 - 10, 348 + 30);
						image(pj3, 323, 348 + 47, 131, 156);
						text("Force = 9", 308 + 5 + 13, 346 + 240);
					}
				} else {
					fill(255, 165, 0);
					rect(270 + 10, 350, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Black", 348 - 10, 348 + 30);
					image(pj3, 323, 348 + 47, 131, 156);
					text("Force = 9", 308 + 5 + 13, 346 + 240);
					fill(0, 0, 0, 80);
					rect(270 + 10, 350, 200, 250, 15);

				}

				// CARTA 8
				if (card8 == false) {
					if (mouseX >= 500 && mouseX <= 700 && mouseY >= 350 && mouseY <= 600) {
						fill(255, 165, 0);
						rect(499 - 2, 348, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Monster", 543, 348 + 30);
						image(pj4, 528, 333 + 47, 137 + 5, 189 + 5);
						text("Force = 12", 529 + 15, 348 + 240);

					} else {
						fill(255, 165, 0);
						rect(490 + 10, 350, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Monster", 543, 348 + 30);
						image(pj4, 528, 333 + 47, 137, 189);
						text("Force = 12", 529 + 15, 346 + 240);

					}
				} else {
					fill(255, 165, 0);
					rect(490 + 10, 350, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Monster", 543, 348 + 30);
					image(pj4, 528, 333 + 47, 137, 189);
					text("Force = 12", 529 + 15, 346 + 240);
					fill(0, 0, 0, 80);
					rect(490 + 10, 350, 200, 250, 15);
				}

				// CARTA 9

				if (card9 == false) {
					if (mouseX >= 720 && mouseX <= 920 && mouseY >= 350 && mouseY <= 600) {
						fill(255, 165, 0);
						rect(719 - 2, 348, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ Flame", 743 + 30, 348 + 30);
						image(pj5, 763, 333 + 47, 143 - 20, 214 - 20);
						text("Force = 11", 714 + 30 + 15, 346 + 245);
					} else {
						fill(255, 165, 0);
						rect(710 + 10, 350, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ Flame", 743 + 30, 348 + 30);
						image(pj5, 763, 333 + 47, 138 - 25, 209 - 25);
						text("Force = 11", 714 + 30 + 15, 346 + 240);
					}
				} else {
					fill(255, 165, 0);
					rect(710 + 10, 350, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ Flame", 743 + 30, 348 + 30);
					image(pj5, 763, 333 + 47, 138 - 25, 209 - 25);
					text("Force = 11", 714 + 30 + 15, 346 + 240);
					fill(0, 0, 0, 80);
					rect(710 + 10, 350, 200, 250, 15);

				}

				// CARTA 10
				if (card10 == false) {

					if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 350 && mouseY <= 600) {
						fill(255, 165, 0);
						rect(939 - 2, 348, 205, 255, 15);
						fill(0);
						textSize(24);
						text("PJ White", 943 + 57, 348 + 30);
						image(pj8, 975, 348 + 47, 133 + 5, 156 + 5);
						text("Force = 6", 939 + 27 + 20, 346 + 240);
					} else {
						fill(255, 165, 0);
						rect(930 + 10, 350, 200, 250, 15);
						fill(0);
						textSize(22);
						text("PJ White", 943 + 57, 348 + 30);
						image(pj8, 975, 348 + 47, 133, 156);
						text("Force = 6", 939 + 27 + 20, 346 + 240);
					}
				} else {
					fill(255, 165, 0);
					rect(930 + 10, 350, 200, 250, 15);
					fill(0);
					textSize(22);
					text("PJ White", 943 + 57, 348 + 30);
					image(pj8, 975, 348 + 47, 133, 156);
					text("Force = 6", 939 + 27 + 20, 346 + 240);
					fill(0, 0, 0, 80);
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

				image(fondo, 0, 0);
				textSize(40);
				fill(0);
				text("Comparte con tu contrincante que acción quieres realizar", 50, 100);

				if (mouseX >= width / 2 - 300 && mouseX <= width / 2 - 300 + 203 && mouseY >= height / 2
						&& mouseY <= height / 2 + 102) {
					image(ceder, width / 2 - 300 - 2, height / 2, 208, 107 - 2);
				} else {
					image(ceder, width / 2 - 300, height / 2, 203, 102);
				}

				if (mouseX >= width / 2 + 100 && mouseX <= width / 2 + 100 + 208 && mouseY >= height / 2
						&& mouseY <= height / 2 + 102) {
					image(tirar, width / 2 + 100 - 2, height / 2 - 2, 208, 107 - 2);
				} else {
					image(tirar, width / 2 + 100, height / 2, 203, 102);
				}

				image(principal, width / 2 - 110, height / 2 + 70);
				image(rope, 0, height / 2 + 250);

				if (comS.isTurnoActivo() == false) {

					fill(0, 0, 0, 95);
					rect(0, 0, 1200, 800);
					fill(255);
					textSize(50);
					text("ESPERANDO JUGADOR", width / 2 - 260, height / 2 - 9);
				}

				break;

			case 2:

				// MOSTRAR INTENCION DE JUEGO
				
				image(fondo, 0, 0);
				textSize(40);
				fill(0);
				text("Intencion por parte de los jugadores", 250, 100);
				text("Tú", 140, height / 2);
				image(principal, 50, height / 2 + 70);
				image(rope, 0, height / 2 + 250);
				

				
				break;

			case 3:
				text(comS.cicloJuego, 20, 30);
				break;

			case 4:
				text(comS.cicloJuego, 20, 30);
				break;

			}

			fill(0);
			textSize(30);

			if (empezartiempo == true) {
				text(nf(comS.scrnMins, 2) + " : " + nf(comS.scrnSecs, 2), 25, 30);
			} else {
				text(nf(00, 2) + " : " + nf(00, 2), 25, 30);
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
		// if (comC.isTurnoActivo()) {

		// }
	}

	@Override
	public void mouseClicked() {

		comS.enviar("hola Icesi soy server");

		if (mouseX >= 550 && mouseX <= 550 + 204 && mouseY >= 450 && mouseY <= 450 + 88 && comS.turno == 0) {
			comS.turno++;
		}

		if (mouseX >= 900 && mouseX <= 900 + 204 && mouseY >= 550 && mouseY <= 550 + 88 && comS.turno > 0
				&& comS.turno < 3) {
			comS.turno++;

		}
		// carta 1
		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {

			if (card1 == false) {
				if (mouseX >= 60 && mouseX <= 260 && mouseY >= 50 && mouseY <= 300) {
					comS.fuerza += 8;
					comS.setTurnoActivo(false);
					card1 = true;

				}
			}
		}

		// carta 2
		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card2 == false) {
				if (mouseX >= 280 && mouseX <= 480 && mouseY >= 50 && mouseY <= 300) {

					if (empezartiempo == true) {
						comS.fuerza += 15;
						comS.setTurnoActivo(false);
						card2 = true;

					}
				}
			}
		}
		// carta 3

		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card3 == false) {
				if (mouseX >= 500 && mouseX <= 700 && mouseY >= 50 && mouseY <= 300) {
					if (comS.isTurnoActivo() == true) {
						if (empezartiempo == true) {
							comS.fuerza += 4;
							comS.setTurnoActivo(false);
							card3 = true;
						}
					}
				}
			}
		}

		// carta 4
		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card4 == false) {
				if (mouseX >= 720 && mouseX <= 920 && mouseY >= 50 && mouseY <= 300) {
					if (comS.isTurnoActivo() == true) {
						if (empezartiempo == true) {
							comS.fuerza += 10;
							comS.setTurnoActivo(false);
							card4 = true;
						}
					}
				}
			}
		}

		// carta 5
		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card5 == false) {

				if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 50 && mouseY <= 300) {
					if (comS.isTurnoActivo() == true) {
						if (empezartiempo == true) {
							comS.fuerza += 2;
							comS.setTurnoActivo(false);
							card5 = true;
						}
					}
				}
			}
		}

		// carta 6

		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card6 == false) {

				if (mouseX >= 60 && mouseX <= 260 && mouseY >= 350 && mouseY <= 600) {
					if (comS.isTurnoActivo() == true) {
						if (empezartiempo == true) {
							comS.fuerza += 4;
							comS.setTurnoActivo(false);
							card6 = true;
						}
					}
				}
			}
		}

		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card7 == false) {

				if (mouseX >= 280 && mouseX <= 480 && mouseY >= 350 && mouseY <= 600) {
					if (comS.isTurnoActivo() == true) {
						if (empezartiempo == true) {
							comS.fuerza += 9;
							comS.setTurnoActivo(false);
							card7 = true;
						}
					}
				}
			}
		}
		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card8 == false) {
				if (mouseX >= 500 && mouseX <= 700 && mouseY >= 350 && mouseY <= 600) {
					if (comS.isTurnoActivo() == true) {
						if (empezartiempo == true) {
							comS.fuerza += 12;
							comS.setTurnoActivo(false);
							card8 = true;
						}
					}
				}
			}
		}

		// CARTA 9
		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card9 == false) {
				if (comS.isTurnoActivo() == true) {
					if (mouseX >= 720 && mouseX <= 920 && mouseY >= 350 && mouseY <= 600) {
						if (empezartiempo == true) {
							comS.fuerza += 11;
							comS.setTurnoActivo(false);
							card9 = true;
						}
					}
				}
			}
		}

		// CARTA 10
		if (comS.cicloJuego == 0 && comS.isTurnoActivo() == true && empezartiempo == true) {
			if (card10 == false) {
				if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 350 && mouseY <= 600) {
					if (comS.isTurnoActivo() == true) {
						if (empezartiempo == true) {
							comS.fuerza += 6;
							comS.setTurnoActivo(false);
							card10 = true;
						}
					}
				}
			}
		}

		/// CUANDO SE HACE CLICK SOBRE TIRAR O CEDER PARA MOSTRAR LA INTENCION
		/// AL JUGADOR CONTRARIO

		if (comS.cicloJuego == 1 && comS.isTurnoActivo() == true) {
			if (mouseX >= width / 2 - 300 && mouseX <= width / 2 - 300 + 203 && mouseY >= height / 2
					&& mouseY <= height / 2 + 102) {

				// TIRAR
				comS.intencion = 1;
				comS.setTurnoActivo(false);

			} else if (mouseX >= width / 2 + 100 && mouseX <= width / 2 + 100 + 208 && mouseY >= height / 2
					&& mouseY <= height / 2 + 102) {

				// CEDER
				comS.intencion = 2;
				comS.setTurnoActivo(false);

			}
		}
	}
}
