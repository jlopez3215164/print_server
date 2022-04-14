package application;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;

public interface Bematech extends Library	{
	
	public Bematech instance = (Bematech) Native.loadLibrary("BemaFI32.dll", Bematech.class);
	
	// Funciones de Inicialización

	public int Bematech_FI_CambiaSimboloMoneda(String SimboloMoneda);
	public int Bematech_FI_ProgramaAlicuota(String Aliquota, int ICMS_ISS);
	public int Bematech_FI_ProgramaHorarioVerano();
	public int Bematech_FI_CrearDepartamento(int Indice, String Departamento);
	public int Bematech_FI_CrearTotalizadorSinIcms(int Indice, String Totalizador);
	public int Bematech_FI_ProgramaRedondeo();
	public int Bematech_FI_ProgramaTruncamiento();
	public int Bematech_FI_LineasEntreCupones(int Linhas);
	public int Bematech_FI_EspacioEntreLineas(int Dots);
	public int Bematech_FI_FuerzaImpactoAgujas(int FuerzaImpacto);
	public int Bematech_FI_ActivaDesactivaVentaUnaLineaMFD(int iTipo);
	public int Bematech_FI_ProgramaFormaPagoMFD(String cFormaPago, String cTef);
	public int Bematech_FI_CrearInformeGerencialMFD(String cIndice, String cDescripcion);

	// Funciones del Cupon Fiscal

	public int Bematech_FI_AbreComprobanteDeVenta(String RIF, String Nombre);
	public int Bematech_FI_AbreComprobanteDeVentaEx(String RIF, String Nombre, String Direccion);
	public int Bematech_FI_DevolucionArticulo(String cCodigo, String cDescripcion, String cAlicuota, String cTipoCantidad, String cCantidad, int iCasasDecimales, String cValorUnit, String cTipoDescuento, String cValorDesc);
	public int Bematech_FI_AbreNotaDeCredito(String cNombre, String cNumeroSerie, String cRIF, String cDia, String cMes, String cAno, String cHora, String cMinuto, String cSecundo, String cCOO);
	public int Bematech_FI_AbreCupon(String CGC_CPF);
	public int Bematech_FI_AbreCuponMFD(String RIF, String Nombre, String Direccion);
	public int Bematech_FI_VendeArticulo(String Codigo, String Descripcion, String Alicuota, String TipoCantidad, String Cantidad, int CasasDecimales, String ValorUnitario, String TipoDescuento, String Descuento);	
	public int Bematech_FI_VendeArticuloDepartamento(String Codigo, String Descripcion, String Alicuota, String ValorUnitario, String Cantidad, String Incremento, String Descuento, String IndiceDepartamento, String UnidadMedida);
	public int Bematech_FI_AnulaArticuloAnterior();
	public int Bematech_FI_AnulaArticuloGenerico(String NumeroItem);
	public int Bematech_FI_AnulaCupon();
	public int Bematech_FI_AnulaCuponMFD(String RIF, String Nombre, String Direccion);
	public int Bematech_FI_CierraCuponReducido(String FormaPago, String Mensaje);
	public int Bematech_FI_CierraCupon(String FormaPago, String IncrementoDescuento, String TipoIncrementoDescuento, String ValorIncrementoDescuento, String ValorPago, String Mensaje);
	public int Bematech_FI_ResetaImpresora();
	public int Bematech_FI_IniciaCierreCupon(String IncrementoDescuento, String TipoincrementoDescuento, String ValorIncrementoDescuento);
	public int Bematech_FI_EfectuaFormaPago(String FormaPago, String ValorFormaPago);
	public int Bematech_FI_EfectuaFormaPagoDescripcionForma(String FormaPago, String ValorFormaPago, String DescripcionFormaPago);
	public int Bematech_FI_RectificaFormasPago(String FormaOrigen, String FormaDestino, String Valor);
	public int Bematech_FI_UsaUnidadMedida(String UnidadMedida);
	public int Bematech_FI_ExtenderDescripcionArticulo(String Descripcion);
	public int Bematech_FI_FinalizarCierreCupon(String cMensage);
	public int Bematech_FI_FinalizaCierreCuponCodigoBarrasMFD(String cMensage, String cTipoCodigo, String cCodigo, int iAlura, int iLargura, int iPosicao, int iFonte, int iMargem, int iErros, int iColunas);

	// Funciones de los Informes Fiscales

	public int Bematech_FI_LecturaX();
	public int Bematech_FI_LecturaXSerial();
	public int Bematech_FI_InformeTransaccionesMFD(String Tipo, String FechaCOOInicial, String FechaCOOFinal, String Optiones);
	public int Bematech_FI_ReduccionZ(String Fecha, String Hora);
	public int Bematech_FI_InformeGerencial(String Texto);
	public int Bematech_FI_InformeGerencialTEF(String Texto);
	public int Bematech_FI_CierraInformeGerencial();
	public int Bematech_FI_LecturaMemoriaFiscalFecha(String FechaInicial, String FechaFinal);
	public int Bematech_FI_LecturaMemoriaFiscalReduccion(String ReduccionInicial, String ReduccionFinal);
	public int Bematech_FI_LecturaMemoriaFiscalSerialFecha(String FechaInicial, String FechaFinal);
	public int Bematech_FI_LecturaMemoriaFiscalSerialReduccion(String ReduccionInicial, String ReduccionFinal);

	// Funciones de las Operaciones No Fiscales

	public int Bematech_FI_RecebimentoNoFiscal(String IndiceTotalizador, String Valor, String FormaPago);
	public int Bematech_FI_AbreComprobanteNoFiscalVinculado(String FormaPago, String Valor, String NumeroCupon);
	public int Bematech_FI_ImprimeComprobanteNoFiscalVinculado(String Texto);
	public int Bematech_FI_UsaComprobanteNoFiscalVinculadoTEF(String Texto);
	public int Bematech_FI_CierraComprobanteNoFiscalVinculado();
	public int Bematech_FI_SegundaViaNoFiscalVinculadoMFD();
	public int Bematech_FI_Sangria(String Valor);
	public int Bematech_FI_Provision(String Valor, String FormaPago);
	public int Bematech_FI_AbreRecibimientoNoFiscalMFD(String cRIF, String cNombre, String cDireccion);
	public int Bematech_FI_EfectuaRecibimientoNoFiscalMFD(String cIndice, String cValor);
	public int Bematech_FI_SubTotalizaRecibimientoMFD();
	public int Bematech_FI_TotalizaRecibimientoMFD();
	public int Bematech_FI_IniciaCierreRecibimientoNoFiscalMFD(String cIncrementoDescuento, String cTipoIncrementoDescuento, String cValorIncremento, String cValorDescuento);
	public int Bematech_FI_CierraRecibimientoNoFiscalMFD(String cMensaje);
	public int Bematech_FI_AbreComprobanteNoFiscalVinculadoMFD(String cFormaPago, String cValor, String cNumeroCupon, String cRIF, String cNombre, String cDireccion);
	public int Bematech_FI_AnulaRecibimientoNoFiscalMFD(String cRIF, String Nombre, String cDireccion);
	public int Bematech_FI_AnulaArticuloNoFiscalMFD(String cIndice);
	public int Bematech_FI_RecibimientoNoFiscal(String cIndiceTotalizador, String cValorRecebimiento, String cFormaPago);
	public int Bematech_FI_ReimpresionNoFiscalVinculadoMFD();

	// Funciones de Informaciones de la Impresora

	public int Bematech_FI_VerificaFormasPagoMFD(PointerByReference FormasPago);
	public int Bematech_FI_NumeroSerie(PointerByReference NumeroSerie);
	public int Bematech_FI_SubTotal(PointerByReference SubTotal);
	public int Bematech_FI_NumeroCupon(PointerByReference NumeroCupon);
	public int Bematech_FI_ContadorComprobantesCreditoMFD(PointerByReference Contador);
	public int Bematech_FI_VersionFirmware(PointerByReference VersionFirmware);
	public int Bematech_FI_MarcaModeloTipoImpresoraMFD(PointerByReference Marca, PointerByReference Modelo, PointerByReference Tipo);
	public int Bematech_FI_VersionFirmwareMFD(PointerByReference VersionFirmware);
	public int Bematech_FI_VersionFirmwareGT(PointerByReference VersionFirmwareGT);
	public int Bematech_FI_CGC_IE(PointerByReference CGC, PointerByReference IE);
	public int Bematech_FI_GranTotal(PointerByReference GranTotal);
	public int Bematech_FI_Cancelamientos(PointerByReference ValorCancelamientos);
	public int Bematech_FI_Descuentos(PointerByReference ValorDescuentos);
	public int Bematech_FI_NumeroOperacionesNoFiscales(PointerByReference NumeroOperaciones);
	public int Bematech_FI_NumeroCuponesAnulados(PointerByReference NumeroCancelamientos);
	public int Bematech_FI_NumeroIntervenciones(PointerByReference NumeroIntervenciones);
	public int Bematech_FI_NumeroReducoes(PointerByReference NumeroReducoes);
	public int Bematech_FI_NumeroSustituicionesPropietario(PointerByReference NumeroSustituiciones);
	public int Bematech_FI_UltimoArticuloVendido(PointerByReference NumeroArticulo);
	public int Bematech_FI_ClichePropietario(PointerByReference Cliche);
	public int Bematech_FI_NumeroCaja(PointerByReference NumeroCaja);
	public int Bematech_FI_NumeroTienda(PointerByReference NumeroTienda);
	public int Bematech_FI_SimboloMoneda(PointerByReference SimboloMoneda);
	public int Bematech_FI_MinutosPrendida(PointerByReference Minutos);
	public int Bematech_FI_MinutosImprimiendo(PointerByReference Minutos);
	public int Bematech_FI_VerificaModoOperacion(PointerByReference Modo);
	public int Bematech_FI_VerificaEpromConectada(PointerByReference Flag);
	public int Bematech_FI_FlagsFiscales(PointerByReference Flag);
	public int Bematech_FI_ValorPagoUltimoCupon(PointerByReference ValorCupon);
	public int Bematech_FI_FechaHoraImpresora(PointerByReference Fecha, PointerByReference Hora);
	public int Bematech_FI_ContadoresTotalizadoresNoFiscales(PointerByReference Contadores);
	public int Bematech_FI_VerificaTotalizadoresNoFiscales(PointerByReference Totalizadores);
	public int Bematech_FI_FechaHoraReduccion(PointerByReference Fecha, PointerByReference  Hora);
	public int Bematech_FI_FechaMovimiento(PointerByReference Data);
	public int Bematech_FI_VerificaTruncamiento(PointerByReference Flag);
	public int Bematech_FI_Agregado(PointerByReference ValorIncrementos);
	public int Bematech_FI_ContadorBilletePasaje(PointerByReference ContadorPasaje);
	public int Bematech_FI_VerificaAlicuotasIss(PointerByReference Flag);
	public int Bematech_FI_VerificaFormasPago(PointerByReference Formas);
	public int Bematech_FI_VerificaRecebimientoNoFiscal(PointerByReference Recebimentos);
	public int Bematech_FI_VerificaDepartamentos(PointerByReference Departamentos);
	public int Bematech_FI_VerificaTipoImpresora(PointerByReference TipoImpresora);
	public int Bematech_FI_VerificaTotalizadoresParciales(PointerByReference Totalizadores);
	public int Bematech_FI_RetornoAlicuotas(PointerByReference Alicuotas);
	public int Bematech_FI_VerificaEstadoImpresora(PointerByReference ACK, PointerByReference ST1, PointerByReference ST2);
	public int Bematech_FI_DatosUltimaReduccion(PointerByReference DadosReduccion);
	public int Bematech_FI_MonitoramentoPapel(PointerByReference Lineas);
	public int Bematech_FI_VerificaIndiceAlicuotasIss(PointerByReference Flag);
	public int Bematech_FI_ValorFormaPago(PointerByReference FormaPago, PointerByReference Valor);
	public int Bematech_FI_ValorTotalizadorNoFiscal(PointerByReference Totalizador, PointerByReference Valor);
	public int Bematech_FI_ContadorCuponFiscalMFD(PointerByReference cContador);
	public int Bematech_FI_DatosUltimaReduccionMFD(PointerByReference cDadosReduccion);
	public int Bematech_FI_FechaHoraUltimoDocumentoMFD(PointerByReference cFechaHora);
	public int Bematech_FI_FechaMovimientoUltimaReduccionMFD(PointerByReference cFecha);
	public int Bematech_FI_GranTotalUltimaReduccionMFD(PointerByReference cGranTotal);
	public int Bematech_FI_NumeroComprobanteFiscal(PointerByReference cNumero);
	public int Bematech_FI_NumeroCuponsCancelados(PointerByReference cNumero);
	public int Bematech_FI_NumeroReducciones(PointerByReference cNumero);
	public int Bematech_FI_NumSerieMemoriaMFD(PointerByReference cNumero);
	public int Bematech_FI_NumeroSerieMFD(PointerByReference cNumero);
	public int Bematech_FI_PorcentualLibreMFD(PointerByReference cPorcentualLibre);
	public int Bematech_FI_VentaBruta(PointerByReference cVentaBruta);
	public int Bematech_FI_VerificaRecibimientoNoFiscalMFD(PointerByReference cRecebimientos);
	public int Bematech_FI_VerificaTotalizadoresNoFiscalesMFD(PointerByReference cTotalizadores);
	public int Bematech_FI_VerificaTotalizadoresParcialesMFD(PointerByReference cTotalizadores);
	public int Bematech_FI_ReducionesRestantesMFD(PointerByReference cReducciones);

	// Funciones de Autenticación y Gaveta de Dinero

	public int Bematech_FI_AccionaGaveta();
	public int Bematech_FI_VerificaEstadoGaveta(PointerByReference EstadoGaveta);

	// Otras Funciones

	public int Bematech_FI_AbrePuertaSerial();
	public int Bematech_FI_RetornoImpresora(PointerByReference ACK, PointerByReference ST1, PointerByReference ST2);
	public int Bematech_FI_HabilitaInhabilitaRetornoExtendidoMFD(String cTipo);
	public int Bematech_FI_RetornoImpresoraMFD(PointerByReference  ACK, PointerByReference ST1, PointerByReference ST2, PointerByReference ST3);
	public int Bematech_FI_VerificaEstadoImpresoraMFD(PointerByReference ACK, PointerByReference ST1, PointerByReference ST2, PointerByReference ST3);
	public int Bematech_FI_CierraPuertaSerial();
	public int Bematech_FI_AperturaDelDia(String ValorCompra, String FormaPago);
	public int Bematech_FI_CierreDelDia();
	public int Bematech_FI_ImprimeConfiguracionesImpresora();
	public int Bematech_FI_ImprimeDepartamentos();
	public int Bematech_FI_VerificaImpresoraPrendida();
	public int Bematech_FI_ImpresionCarne(String Titulo, String Parcelas, String Fechas, int Cantidad, String Texto, String Cliente, String RG_CPF, String Cupon, String Vias, int Firma);
	public int Bematech_FI_VersionDll(PointerByReference Version);
	public int Bematech_FI_LeerArchivoRetorno(PointerByReference Retorno);
	public int Bematech_FI_FormatoDatosMFD(String cArquivoOrigem, String cDestino, String cFormatoDados, String cTipoDownload, String cDadoInicial, String cDadoFinal, String cUsuario);
	public int Bematech_FI_ReloadINIFile();

	// Códigos de Barras

	public int Bematech_FI_ConfiguraCodigoBarrasMFD( int Altura, int Largura, int PosicaoCaracteres, int Fonte, int Margem);

	public int Bematech_FI_CodigoBarrasUPCAMFD(String Codigo);
	public int Bematech_FI_CodigoBarrasUPCEMFD(String Codigo);
	public int Bematech_FI_CodigoBarrasEAN13MFD(String Codigo);
	public int Bematech_FI_CodigoBarrasEAN8MFD(String Codigo);
	public int Bematech_FI_CodigoBarrasCODE39MFD(String Codigo);
	public int Bematech_FI_CodigoBarrasCODE93MFD(String Codigo);
	public int Bematech_FI_CodigoBarrasCODE128MFD(String Codigo);
	public int Bematech_FI_CodigoBarrasITFMFD(String Codigo);
	public int Bematech_FI_CodigoBarrasCODABARMFD(String Codigo);
	public int Bematech_FI_CodigoBarrasISBNMFD(String Codigo);
	public int Bematech_FI_CodigoBarrasMSIMFD(String Codigo);
	public int Bematech_FI_CodigoBarrasPLESSEYMFD(String Codigo);
	public int Bematech_FI_CodigoBarrasPDF417MFD(int NivelCorrecaoErros, int Altura, int Largura, int Colunas, int Codigo);	
}