import processing.core.PApplet;
import processing.core.PImage;
import com_client.ComunicacionCliente;

public class EjemploC extends PApplet {
	private String textoTurno;
	private ComunicacionCliente comC;

	// TIEMPO

	boolean empezarTiempo = false;

	// IMAGENES
	private boolean card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
	private PImage inicio, fondo, instrucciones1, instrucciones2;
	private PImage tirar, ceder, siguiente, jugar;
	private PImage pj1, pj2, pj3, pj4, pj5, pj6, pj7, pj8, pj9, pj10;
	private PImage rope, principal, inicial2;
	private PImage nudo, ganar, perder;
	private int rounds;

	public void setup() {
		textoTurno = "";
		size(1200, 700);
		comC = new ComunicacionCliente(5001);

		// CARGAR IMAGENES

		inicio = loadImage("../imagenes/Inicio.png");
		fondo = loadImage("../imagenes/Escenario.png");
		instrucciones1 = loadImage("../imagenes/Instrucciones.png");
		instrucciones2 = loadImage("../imagenes/Premios.png");

		jugar = loadImage("../imagenes/Boton Jugar.png");
		siguiente = loadImage("../imagenes/Boton Siguiente.png");
		tirar = loadImage("../imagenes/Boton Ceder.png");
		ceder = loadImage("../imagenes/Boton Halar.png");

		nudo = loadImage("../imagenes/nudo.png");
		ganar = loadImage("../imagenes/Ganaste.png");
		perder = loadImage("../imagenes/Perdiste.png");

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

		comC.actualSecs = millis() / 1000;
		
		comC.scrnSecs = comC.actualSecs - comC.restartSecs;
		

		switch (comC.turno) {
		case 0:
			background(255);
			image(inicio, 0, 0);
			comC.setTurnoActivo(true);
			if (mouseX >= 550 && mouseX <= 550 + 204 && mouseY >= 450 && mouseY <= 450 + 88) {
				image(jugar, 548, 448, 209, 93);
			} else {
				image(jugar, 550, 450, 204, 88);
			}

			break;

		case 1:
			background(255);
			image(instrucciones1, 0, 0);
			comC.setTurnoActivo(true);
			if (mouseX >= 900 && mouseX <= 900 + 204 && mouseY >= 550 && mouseY <= 550 + 88) {
				image(jugar, 898, 548, 209, 93);
			} else {
				image(jugar, 900, 550, 204, 88);
			}

			break;

		case 2:
			background(255);
			image(instrucciones2, 0, 0);
			comC.setTurnoActivo(true);
			if (mouseX >= 900 && mouseX <= 900 + 204 && mouseY >= 550 && mouseY <= 550 + 88) {
				image(jugar, 898, 548, 209, 93);
			} else {
				image(jugar, 900, 550, 204, 88);
			}

			break;

		case 3:

			// AQUI VA EL JUEGO Y COMIENZAN YA LAS RONDAS DE JUEGO O EL CICLO

			background(255);

			text("Juego", (width / 2) - 60, 50);

			// switch de ciclos los ciclos son: Seleccionar un Jugador,
			// Seleccionar una Intencion, Ver una intencion, Seleecionar una
			// Accion, Ver la cuerda moverse en el rango.

			// DESACTIVAR EL TURNO UNA VEZ SELECCIONA UNA CARTA

			// if (mousePressed && comC.isTurnoActivo() == true && empezarTiempo
			// == true) {

			// }

			// --------------------------------------------------------------

			if (comC.gotime == true) {
				empezarTiempo = true;
			}

			if (comC.cicloJuego == 0) {

				comC.intencion = 0;
				comC.eleccion = 0;

				if (empezarTiempo == true) {
					if (comC.scrnSecs >= 10) {
						comC.cicloJuego++;
						comC.setTurnoActivo(true);
						comC.restartSecs = comC.actualSecs; // stores elapsed
															// SECONDS
						comC.scrnSecs = comC.startSec; // restart screen timer
						

					}

				} else {
					comC.setTurnoActivo(false);
				}
			}
			if (comC.cicloJuego == 1) {
				if (comC.scrnSecs >= 10) {
					comC.escogio = 0;
					comC.cicloJuego++;
					comC.enviar("TIMEITSOVER");
					comC.setTurnoActivo(true);
					comC.restartSecs = comC.actualSecs; // stores elapsed
														// SECONDS
					comC.scrnSecs = comC.startSec; // restart screen timer
				

				}
			}

			if (comC.cicloJuego == 2) {
				if (comC.scrnSecs >= 10) {
					comC.escogio = 0;
					comC.cicloJuego++;
					// comC.enviar("TIMEITSOVER");
					comC.setTurnoActivo(true);
					comC.restartSecs = comC.actualSecs; // stores elapsed
														// SECONDS
					comC.scrnSecs = comC.startSec; // restart screen timer
					
				}
			}

			if (comC.cicloJuego == 3) {
				if (comC.scrnSecs >= 10) {
					comC.escogio = 0;
					comC.cicloJuego++;

					// AMBOS TIRARON

					if (comC.eleccion == 2 && comC.eleccionOtroJugador == 2) {
						if (comC.fuerza > comC.fuerzaOtroJugador) {
							comC.posMono += 1;

						} else if (comC.fuerza < comC.fuerzaOtroJugador) {
							comC.posMono -= 1;

						}

					}
					// SERVIDOR TIRO CLIENTE CEDIO
					if (comC.eleccion == 2 && comC.eleccionOtroJugador == 1) {
						comC.posMono -= 1;

					}
					// SERVIDOR TIRO CLIENTE CEDIO o no tiro
					if (comC.eleccion == 2 && comC.eleccionOtroJugador == 0) {
						comC.posMono -= 1;

					}
					// SERVIDOR CEDIO CLIENTE TIRO
					if (comC.eleccion == 1 && comC.eleccionOtroJugador == 2) {
						comC.posMono += 1;

					}
					// AMBOS CEDIERON NO PASA NADA
					if (comC.eleccion == 1 && comC.eleccionOtroJugador == 1) {

					}

					comC.setTurnoActivo(true);
					comC.restartSecs = comC.actualSecs; // stores elapsed
					// SECONDS
					comC.scrnSecs = comC.startSec; // restart screen timer
					
				

				}
			}

			if (comC.cicloJuego == 4) {
				if (comC.scrnSecs >= 10) {

					rounds++;
					System.out.println("ROUNDS CLIENTE " + rounds);

					empezarTiempo = true;

					comC.escogio = 0;
					comC.cicloJuego++;
					comC.enviar("TIMEITSOVER");

					comC.restartSecs = comC.actualSecs; // stores elapsed
														// SECONDS
					comC.scrnSecs = comC.startSec; // restart screen timer
					
			

				}
			}

			if (comC.actualSecs % 60 == 0) { // after 60 secs, restart second
												// timer
				comC.restartSecs = comC.actualSecs; // placeholder for this
													// second in time
				
				comC.scrnSecs = comC.startSec; // reset to zero

			}

			switch (comC.cicloJuego) {

			case 0:

				// text(comC.cicloJuego, 20, 30);
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

				if (comC.isTurnoActivo() == false) {

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
					image(tirar, width / 2 - 300 - 2, height / 2, 208, 107 - 2);
				} else {
					image(tirar, width / 2 - 300, height / 2, 203, 102);
				}

				if (mouseX >= width / 2 + 100 && mouseX <= width / 2 + 100 + 208 && mouseY >= height / 2
						&& mouseY <= height / 2 + 102) {
					image(ceder, width / 2 + 100 - 2, height / 2 - 2, 208, 107 - 2);
				} else {
					image(ceder, width / 2 + 100, height / 2, 203, 102);
				}

				image(inicial2, width / 2 - 110, height / 2 + 70);
				image(rope, 0, height / 2 + 250);

				if (comC.isTurnoActivo() == false) {

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
				text("Tú", 930, height / 2);

				// TU INTENCION
				image(principal, 55, height / 2 + 70);
				if (comC.intencion == 1) {
					image(tirar, 30, height / 2, 208, 107);
				} else if (comC.intencion == 2) {
					image(ceder, 80, height / 2, 208, 107);
				}

				// INTENCION OTRO JUGADOR
				text("Contrincante", 110, height / 2);
				if (comC.intencionOtroJugador == 1) {

					image(tirar, 940, height / 2, 208, 107);

				} else if (comC.intencionOtroJugador == 2) {

					image(ceder, 900, height / 2, 208, 107);
				}

				image(inicial2, 870, height / 2 + 70);
				image(rope, 0, height / 2 + 250);

				break;

			case 3:

				comC.intencion = 0;
				// SELECIONAR SU ACCION SI DESEA TIRAR O CEDER
				image(fondo, 0, 0);
				textSize(40);
				fill(0);
				text("Comparte con tu contrincante que acción quieres realizar", 50, 100);

				if (mouseX >= width / 2 - 300 && mouseX <= width / 2 - 300 + 203 && mouseY >= height / 2
						&& mouseY <= height / 2 + 102) {
					image(tirar, width / 2 - 300 - 2, height / 2, 208, 107 - 2);
				} else {
					image(tirar, width / 2 - 300, height / 2, 203, 102);
				}

				if (mouseX >= width / 2 + 100 && mouseX <= width / 2 + 100 + 208 && mouseY >= height / 2
						&& mouseY <= height / 2 + 102) {
					image(ceder, width / 2 + 100 - 2, height / 2 - 2, 208, 107 - 2);
				} else {
					image(ceder, width / 2 + 100, height / 2, 203, 102);
				}

				image(inicial2, width / 2 - 110, height / 2 + 70);
				image(rope, 0, height / 2 + 250);

				if (comC.isTurnoActivo() == false) {

					fill(0, 0, 0, 95);
					rect(0, 0, 1200, 800);
					fill(255);
					textSize(50);
					text("ESPERANDO JUGADOR", width / 2 - 260, height / 2 - 9);
				}

				break;

			case 4:
				comC.intencion = 0;
				// MOSTRAR ACCIONES DE JUEGO

				image(fondo, 0, 0);
				textSize(40);
				fill(0);
				text("Aciones por parte de los jugadores", 250, 100);
				text("Tú", 930, height / 2);
				image(principal, 50, height / 2 + 70);
				image(inicial2, 870, height / 2 + 70);
				image(rope, 0, height / 2 + 250);

				strokeWeight(3);
				fill(103, 191, 0);
				rect(width / 2 - 50, height / 2 + 310, 100, 40);

				fill(251, 176, 59);
				rect(width / 2 - 50 - 100, height / 2 + 310, 100, 40);
				rect(width / 2 - 50 - 200, height / 2 + 310, 100, 40);
				rect(width / 2 - 50 + 100, height / 2 + 310, 100, 40);
				rect(width / 2 - 50 + 200, height / 2 + 310, 100, 40);
				/// Perdio
				fill(255, 0, 0);
				rect(width / 2 - 50 + 300, height / 2 + 310, 100, 40);
				rect(width / 2 - 50 - 300, height / 2 + 310, 100, 40);

				// MOÑO DEL JUEGO

				switch (comC.posMono) {
				case 0:
					image(nudo, width / 2 - 50 - 300, height / 2 + 250);

					comC.turno = 6;

					System.out.println("CLIENTE PERDIO");

					break;

				case 1:
					image(nudo, width / 2 - 50 - 200, height / 2 + 250);
					break;

				case 2:
					image(nudo, width / 2 - 50 - 100, height / 2 + 250);
					break;

				case 3:
					image(nudo, width / 2 - 50, height / 2 + 250);
					break;

				case 4:
					image(nudo, width / 2 - 50 + 100, height / 2 + 250);
					break;

				case 5:
					image(nudo, width / 2 - 50 + 200, height / 2 + 250);
					break;

				case 6:
					image(nudo, width / 2 - 50 + 300, height / 2 + 250);

					System.out.println("CLIENTE GANO");
					comC.turno = 7;

					break;

				}

				break;

			}

			fill(0);
			textSize(30);

			if (empezarTiempo == true) {
				text( nf(comC.scrnSecs, 2), 25, 30);
			} else {
				text(nf(00, 2), 25, 30);
			}

			if (comC.cicloJuego >= 5) {
				comC.cicloJuego = 0;
			}

			// AQUIIII ACABA EL JUEGOOOO!!!

			break;

		case 4:
			background(255, 0, 255);
			text("Juego2", (width / 2) - 60, 50);

			break;

		case 6:

			image(perder, 0, 0);

			break;

		case 7:

			image(ganar, 0, 0);
			break;

		}

		// fill(0);
		// textoTurno = "" + comC.getTurno();
		// text(textoTurno, 200, 200);
		// text("Servidor", 200, 150);

		if (comC.turno == 5) {
			comC.turno = 3;

		}

	}

	public void mousePressed() {
		// if (comC.isTurnoActivo()) {

		// }
	}

	@Override
	public void mouseClicked() {

		if (comC.cicloJuego != 4) {
			comC.enviar("hola Icesi soy server");
		}

		if (mouseX >= 550 && mouseX <= 550 + 204 && mouseY >= 450 && mouseY <= 450 + 88 && comC.turno == 0) {
			comC.turno++;
		}

		if (mouseX >= 900 && mouseX <= 900 + 204 && mouseY >= 550 && mouseY <= 550 + 88 && comC.turno > 0
				&& comC.turno < 3) {
			comC.turno++;
		}

		// carta 1
		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {

			if (card1 == false) {
				if (mouseX >= 60 && mouseX <= 260 && mouseY >= 50 && mouseY <= 300) {
					comC.fuerza += 8;
					comC.setTurnoActivo(false);
					card1 = true;

				}
			}
		}

		// carta 2
		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card2 == false) {
				if (mouseX >= 280 && mouseX <= 480 && mouseY >= 50 && mouseY <= 300) {

					if (empezarTiempo == true) {
						comC.fuerza += 15;
						comC.setTurnoActivo(false);
						card2 = true;

					}
				}
			}
		}
		// carta 3

		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card3 == false) {
				if (mouseX >= 500 && mouseX <= 700 && mouseY >= 50 && mouseY <= 300) {
					if (comC.isTurnoActivo() == true) {
						if (empezarTiempo == true) {
							comC.fuerza += 4;
							comC.setTurnoActivo(false);
							card3 = true;
						}
					}
				}
			}
		}

		// carta 4
		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card4 == false) {
				if (mouseX >= 720 && mouseX <= 920 && mouseY >= 50 && mouseY <= 300) {
					if (comC.isTurnoActivo() == true) {
						if (empezarTiempo == true) {
							comC.fuerza += 10;
							comC.setTurnoActivo(false);
							card4 = true;
						}
					}
				}
			}
		}

		// carta 5
		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card5 == false) {

				if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 50 && mouseY <= 300) {
					if (comC.isTurnoActivo() == true) {
						if (empezarTiempo == true) {
							comC.fuerza += 2;
							comC.setTurnoActivo(false);
							card5 = true;
						}
					}
				}
			}
		}

		// carta 6

		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card6 == false) {

				if (mouseX >= 60 && mouseX <= 260 && mouseY >= 350 && mouseY <= 600) {
					if (comC.isTurnoActivo() == true) {
						if (empezarTiempo == true) {
							comC.fuerza += 4;
							comC.setTurnoActivo(false);
							card6 = true;
						}
					}
				}
			}
		}

		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card7 == false) {

				if (mouseX >= 280 && mouseX <= 480 && mouseY >= 350 && mouseY <= 600) {
					if (comC.isTurnoActivo() == true) {
						if (empezarTiempo == true) {
							comC.fuerza += 9;
							comC.setTurnoActivo(false);
							card7 = true;
						}
					}
				}
			}
		}
		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card8 == false) {
				if (mouseX >= 500 && mouseX <= 700 && mouseY >= 350 && mouseY <= 600) {
					if (comC.isTurnoActivo() == true) {
						if (empezarTiempo == true) {
							comC.fuerza += 12;
							comC.setTurnoActivo(false);
							card8 = true;
						}
					}
				}
			}
		}

		// CARTA 9
		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card9 == false) {
				if (comC.isTurnoActivo() == true) {
					if (mouseX >= 720 && mouseX <= 920 && mouseY >= 350 && mouseY <= 600) {
						if (empezarTiempo == true) {
							comC.fuerza += 11;
							comC.setTurnoActivo(false);
							card9 = true;
						}
					}
				}
			}
		}

		// CARTA 10
		if (comC.cicloJuego == 0 && comC.isTurnoActivo() == true && empezarTiempo == true) {
			if (card10 == false) {
				if (mouseX >= 940 && mouseX <= 1140 && mouseY >= 350 && mouseY <= 600) {
					if (comC.isTurnoActivo() == true) {
						if (empezarTiempo == true) {
							comC.fuerza += 6;
							comC.setTurnoActivo(false);
							card10 = true;
						}
					}
				}
			}
		}

		/// CUANDO SE HACE CLICK SOBRE TIRAR O CEDER PARA MOSTRAR LA INTENCION
		/// AL JUGADOR CONTRARIO

		if (comC.cicloJuego == 1 && comC.isTurnoActivo() == true) {
			if (mouseX >= width / 2 - 300 && mouseX <= width / 2 - 300 + 203 && mouseY >= height / 2
					&& mouseY <= height / 2 + 102) {

				// TIRAR
				comC.intencion = 1;
				comC.setTurnoActivo(false);

			} else if (mouseX >= width / 2 + 100 && mouseX <= width / 2 + 100 + 208 && mouseY >= height / 2
					&& mouseY <= height / 2 + 102) {

				// CEDER
				comC.intencion = 2;
				comC.setTurnoActivo(false);

			}
		}

		/// CUANDO SE HACE CLICK SOBRE TIRAR O CEDER PARA ESCOGER

		if (comC.cicloJuego == 3 && comC.isTurnoActivo() == true) {
			if (mouseX >= width / 2 - 300 && mouseX <= width / 2 - 300 + 203 && mouseY >= height / 2
					&& mouseY <= height / 2 + 102) {

				// CEDER
				comC.eleccion = 1;
				comC.setTurnoActivo(false);

			} else if (mouseX >= width / 2 + 100 && mouseX <= width / 2 + 100 + 208 && mouseY >= height / 2
					&& mouseY <= height / 2 + 102) {
				// TIRAR

				comC.eleccion = 2;
				comC.setTurnoActivo(false);

			}
		}

	}
}
