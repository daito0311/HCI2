import processing.core.PApplet;
import com_client.ComunicacionCliente;

public class EjemploC extends PApplet {
	private String textoTurno;
	private ComunicacionCliente comC;

	public void setup() {
		textoTurno = "";
		size(400, 400);
		comC = new ComunicacionCliente(5001);
	}

	public void draw() {
		switch (comC.turno) {
		case 0:
			background(255,0,0);
			text("instrucciones1",(width/2)-60, 50);
			
			break;

		case 1:
			background(  0,255,0);
			text("instrucciones2",(width/2)-60, 50);
			break;
		
		
	case 2:
		background(  0,0,255);
		text("instrucciones3",(width/2)-60, 50);
		
		break;
		
	case 3:
		background(  0,255,255);
		text("Juego",(width/2)-60, 50);
		
		
		break;
		
	case 4:
		background(  255,0,255);
		text("Juego2",(width/2)-60, 50);
		
		break;
	}
		fill(0);
		textoTurno = "" + comC.getTurno();
		text(textoTurno, 200, 200);
		text("Cliente",200,150);
	}

	public void mousePressed() {
		if (comC.isTurnoActivo()) {
			
			comC.enviar("hola icesi soy cliente");
			
			
			
		}
	}
}
