package ec.com.redepronik.negosys.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public enum Tarifa {
	IV001("0", "0%", new BigDecimal("0.00"), Impuesto.IV), IV002("2", "12%",
			new BigDecimal("12.00"), Impuesto.IV), IV003("6",
			"NO OBJETO DE IMPUESTO", new BigDecimal("0.00"), Impuesto.IV), IV004(
			"7", "EXCENTO DE IVA", new BigDecimal("0.00"), Impuesto.IV), IC001(
			"3023",
			"PRODUCTOS DEL TABACO Y SUCEDÁNEOS DEL TABACO (ABARCAN LOS PRODUCTOS PREPARADOS TOTALMENTE O EN PARTE UTILIZANDO COMO MATERIA PRIMA HOJAS DE TABACO Y DESTINADOS A SER FUMADOS, CHUPADOS, INHALADOS, MASCADOS O UTILIZADOS COMO RAPÉ).",
			new BigDecimal("150.00"), Impuesto.IC), IC002("3051",
			"BEBIDAS GASEOSAS", new BigDecimal("10.00"), Impuesto.IC), IC003(
			"3610", "PERFUMES Y AGUAS DE TOCADOR", new BigDecimal("20.00"),
			Impuesto.IC), IC004("3620", "VIDEOJUEGOS", new BigDecimal("35.00"),
			Impuesto.IC), IC005(
			"3630",
			"ARMAS DE FUEGO, ARMAS DEPORTIVAS Y MUNICIONES EXCEPTO AQUELLAS ADQUIRIDAS POR LA FUERZA PÚBLICA",
			new BigDecimal("300.00"), Impuesto.IC), IC006(
			"3640",
			"FOCOS INCANDESCENTES EXCEPTO AQUELLOS UTILIZADOS COMO INSUMOS AUTOMOTRICES",
			new BigDecimal("100.00"), Impuesto.IC), IC007(
			"3073",
			"VEHÍCULOS MOTORIZADOS CUYO PRECIO DE VENTA AL PÚBLICO SEA DE HASTA USD 20.000",
			new BigDecimal("5.00"), Impuesto.IC), IC008(
			"3072",
			"CAMIONETAS, FURGONETAS, CAMIONES, Y VEHÍCULOS DE RESCATE CUYO PRECIO DE VENTA AL PÚBLICO SEA DE HASTA USD 30.000",
			new BigDecimal("5.00"), Impuesto.IC), IC009(
			"3074",
			"VEHÍCULOS MOTORIZADOS, EXCEPTO CAMIONETAS, FURGONETAS, CAMIONES Y VEHÍCULOS DE RESCATE, CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 20.000 Y DE HASTA USD 30.000",
			new BigDecimal("10.00"), Impuesto.IC), IC010(
			"3075",
			"VEHÍCULOS MOTORIZADOS, CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 30.000 Y DE HASTA USD 40.000",
			new BigDecimal("15.00"), Impuesto.IC), IC011(
			"3077",
			"VEHÍCULOS MOTORIZADOS, CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 40.000 Y DE HASTA USD 50.000",
			new BigDecimal("20.00"), Impuesto.IC), IC012(
			"3078",
			"VEHÍCULOS MOTORIZADOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 50.000 Y DE HASTA USD 60.000",
			new BigDecimal("25.00"), Impuesto.IC), IC013(
			"3079",
			"VEHÍCULOS MOTORIZADOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 60.000 Y DE HASTA USD 70.000",
			new BigDecimal("30.00"), Impuesto.IC), IC014(
			"3080",
			"VEHÍCULOS MOTORIZADOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 70.000",
			new BigDecimal("35.00"), Impuesto.IC), IC015(
			"3171",
			"VEHÍCULOS HÍBRIDOS O ELÉCTRICOS CUYO PRECIO DE VENTA AL PÚBLICO SEA DE HASTA USD 35.000",
			new BigDecimal("0.00"), Impuesto.IC), IC016(
			"3172",
			"VEHÍCULOS HÍBRIDOS O ELÉCTRICOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 35.000 Y DE HASTA USD 40.000",
			new BigDecimal("0.00"), Impuesto.IC), IC017(
			"3173",
			"VEHÍCULOS HÍBRIDOS O ELÉCTRICOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 40.000 Y DE HASTA USD 50.000",
			new BigDecimal("0.00"), Impuesto.IC), IC018(
			"3174",
			"VEHÍCULOS HÍBRIDOS O ELÉCTRICOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 50.000 Y DE HASTA USD 60.000",
			new BigDecimal("0.00"), Impuesto.IC), IC019(
			"3175",
			"VEHÍCULOS HÍBRIDOS O ELÉCTRICOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 60.000 Y DE HASTA USD 70.000",
			new BigDecimal("0.00"), Impuesto.IC), IC020(
			"3176",
			"VEHÍCULOS HÍBRIDOS O ELÉCTRICOS CUYO PRECIO DE VENTA AL PÚBLICO SEA SUPERIOR A USD 70.000",
			new BigDecimal("0.00"), Impuesto.IC), IC021(
			"3081",
			"3. AVIONES, AVIONETAS Y HELICÓPTEROS EXCEPTO AQUELLAS DESTINADAS AL TRANSPORTE COMERCIAL DE PASAJEROS, CARGA Y SERVICIOS; MOTOS ACUÁTICAS, TRICARES, CUADRONES, YATES Y BARCOS DE RECREO:",
			new BigDecimal("15.00"), Impuesto.IC), IC022("3092",
			"SERVICIOS DE TELEVISIÓN PAGADA", new BigDecimal("15.00"),
			Impuesto.IC), IC023(
			"3650",
			"SERVICIOS DE CASINOS, SALAS DE JUEGO (BINGO - MECÁNICOS) Y OTROS JUEGOS DE AZAR",
			new BigDecimal("35.00"), Impuesto.IC), IC024("3011",
			"CIGARRILLOS RUBIOS", new BigDecimal("150.00"), Impuesto.IC), IC025(
			"3660",
			"LAS CUOTAS, MEMBRESÍAS, AFILIACIONES, ACCIONES Y SIMILARES QUE COBREN A SUS MIEMBROS Y USUARIOS LOS CLUBES SOCIALES, PARA PRESTAR SUS SERVICIOS, CUYO MONTO EN SU CONJUNTO SUPERE LOS US $ 1.500 ANUALES",
			new BigDecimal("35.00"), Impuesto.IC), IC026("3021",
			"CIGARRILLOS NEGROS", new BigDecimal("150.00"), Impuesto.IC), IC027(
			"3031", "BEBIDAS ALCOHÓLICAS, DISTINTAS A LA CERVEZA",
			new BigDecimal("40.00"), Impuesto.IC), IC028("3041", "CERVEZA",
			new BigDecimal("30.00"), Impuesto.IC), IR001("5001",
			"BOTELLAS PLÁSTICAS NO RETORNABLES", new BigDecimal("0.02"),
			Impuesto.IR);

	private final String idSri;
	private final String nombre;
	private final BigDecimal porcentaje;
	private final Impuesto impuesto;

	private Tarifa(String idSri, String nombre, BigDecimal porcentaje,
			Impuesto impuesto) {
		this.idSri = idSri;
		this.nombre = nombre;
		this.porcentaje = porcentaje;
		this.impuesto = impuesto;
	}

	public static Tarifa[] obtener(Impuesto impuesto) {
		List<Tarifa> list = new ArrayList<Tarifa>();
		for (Tarifa t : Tarifa.values())
			if (t.impuesto == impuesto)
				list.add(t);

		return list.toArray(new Tarifa[list.size()]);
	}

	public String getIdSri() {
		return idSri;
	}

	public Impuesto getImpuesto() {
		return impuesto;
	}

	public String getNombre() {
		return nombre;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}
}