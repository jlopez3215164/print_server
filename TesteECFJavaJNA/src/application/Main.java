package application;

import java.util.Scanner;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.PointerByReference;

public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO 
		
		Bematech bemafi = Bematech.instance;
		
		int iRetorno;
		int iOpcion;
		String cStatusImpresora = "";
		PointerByReference ACK = new PointerByReference();
		PointerByReference ST1 = new PointerByReference();
		PointerByReference ST2 = new PointerByReference();
		PointerByReference ST3 = new PointerByReference();
		
		Scanner menu = new Scanner (System.in);
		
		iRetorno = bemafi.Bematech_FI_HabilitaInhabilitaRetornoExtendidoMFD("1");
		
		do {
		
			System.out.println("1  - Lectura X");
			System.out.println("2  - Lectura X Serial");
			System.out.println("3  - Abre Factura de Venta");
			System.out.println("4  - Vente Artículo");
			System.out.println("5  - Inicia Cierre Factura de Venta");
			System.out.println("6  - Efectua Forma de Pago");
			System.out.println("7  - Termina Cierre Factura de Venta");
			System.out.println("8  - Anula Factura de Venta");
			System.out.println("9  - Retorno del número de cupón");
			System.out.println("10 - Contador de Nota de Credito");
			System.out.println("11 - Contador de Factura");
			System.out.println("12 - Numero Serial de la impresora");
			System.out.println("0  - SALIR");
			System.out.println("Eleja una opcion:");			
			
			iOpcion = menu.nextInt();
			
			switch (iOpcion) {
				case 1:	// Lectura X
					iRetorno = bemafi.Bematech_FI_LecturaX();
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;

				case 2:	// Lectura X Serial
					iRetorno = bemafi.Bematech_FI_LecturaXSerial();
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;
					
				case 3:	// Abre Factura de Venta
					iRetorno = bemafi.Bematech_FI_AbreComprobanteDeVentaEx("123456789", "Pablo", "Calle sin fin, 123");
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;

				case 4:	// Venta de Artículo
					iRetorno = bemafi.Bematech_FI_VendeArticulo("123", "Pluma", "II", "I", "10", 2, "0,25", "%", "0000");
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;
					
				case 5:	// Inicia Cierre Factura de Venta
					iRetorno = bemafi.Bematech_FI_IniciaCierreCupon("A", "%", "0000");
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;

				case 6:	// Efectua Forma de Pago
					iRetorno = bemafi.Bematech_FI_EfectuaFormaPago("Efectivo", "10,00");
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;

				case 7:	// Finaliza Cierre Factura de Venta
					iRetorno = bemafi.Bematech_FI_FinalizarCierreCupon("Gracias, vuelve siempre!");
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;
					
				case 8:	// Anula Factura de Venta
					iRetorno = bemafi.Bematech_FI_AnulaCupon();
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);	
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;
					
				case 9: // Retorno del Número del Cupon
					PointerByReference numeroCupon = new PointerByReference();
					iRetorno = bemafi.Bematech_FI_NumeroCupon(numeroCupon);
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);
						System.out.println("El número del cupon es: " + numeroCupon.getPointer().getString(0));
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					
					break;

				case 10:	// Contador de Nota de Crédito
					PointerByReference Contador = new PointerByReference();
					iRetorno = bemafi.Bematech_FI_ContadorComprobantesCreditoMFD(Contador);
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);
						System.out.println("El contador de nota de credito es: " + Contador.getPointer().getString(0));
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;
					
				case 11:	// Contador de Factura
					PointerByReference Contador_Factura = new PointerByReference();
					iRetorno = bemafi.Bematech_FI_ContadorCuponFiscalMFD(Contador_Factura);
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);
						System.out.println("El contador de facturas es: " + Contador_Factura.getPointer().getString(0));
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;

				case 12:	// Contador de Factura
					PointerByReference NumeroSerie = new PointerByReference();
					iRetorno = bemafi.Bematech_FI_NumeroSerieMFD(NumeroSerie);
					if (iRetorno == 1) {
						iRetorno = bemafi.Bematech_FI_RetornoImpresoraMFD(ACK, ST1, ST2, ST3);
						cStatusImpresora = "ACK: " + ACK.getPointer().getInt(0) + "\nST1: " + ST1.getPointer().getInt(0) + "\nST2: "
								+ ST2.getPointer().getInt(0) + "\nST3: " + ST3.getPointer().getInt(0);
						System.out.println(cStatusImpresora);
						System.out.println("El número serial es: " + NumeroSerie.getPointer().getString(0).substring(0, 20));
					} else {
						System.out.println("Ocurrió un error en la ejecución!");
					}
					break;
					
				default:
					System.out.print("\nOpción incorrecta!");
					break;
					
				case 0:
					System.out.print("\nHasta luego!");
					menu.close();		
			}
		} while (menu.nextInt() != 0);
	}
}