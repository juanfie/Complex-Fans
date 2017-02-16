/**
 * Clase para modelar un intervalo de ángulo, implementa los métodos y las funciones para
su manejo, hereda de la clase Interval.
 * @author Manuel Cipriano
 */

public class AngleInterval extends Interval{
    
	/**
	 * Constructor que inicializa el primer y el segundo extremo en 0.0, el primer lı́mite
en ‘(’ y el segundo lı́mite en ‘)’, es decir, un intervalo vacı́o. Utiliza el constructor
de la clase padre.
	 */
	public AngleInterval(){
		super();
	}

	/**
	 * Constructor para inicializar un intervalo de ángulo de acuerdo a los valores recibi-
dos.
	 * @param feIncluded el lı́mite para el primer extremo
	 * @param firstExtreme el valor del primer extremo
	 * @param secondExtreme el valor del segundo extremo
	 * @param seIncluded el lı́mite para el segundo extremo
	 */
	public AngleInterval(char feIncluded, double firstExtreme, double secondExtreme, char seIncluded){
		super(feIncluded, firstExtreme, secondExtreme, seIncluded);
	}

	/**
	 * Constructor para inicializar un intervalo de ángulo de acuerdo a los valores recibi-
dos, en otro orden.
	 * @param firstExtreme el valor del primer extremo
	 * @param secondExtreme el valor del segundo extremp
	 * @param feIncluded el limite para el primer extremo
	 * @param seIncluded el limite para el segundo extremo
	 */
	public AngleInterval(double firstExtreme, double secondExtreme, char feIncluded, char seIncluded){
		super(firstExtreme, secondExtreme, feIncluded, seIncluded);
	}

	/**
	 * Constructor para inicializar un intervalo de ángulo a partir de otro intervalo de
ángulo.
	 * @param ai el intervalo de ángulo a copiar
	*/
	public AngleInterval(AngleInterval ai){
		super(ai);
	}
    
	/**
	 * Constructor para inicializar un intervalo de ángulo a partir de un intervalo.
	 * @param in el intervalo a copiar
	 */
	public AngleInterval(Interval in){
		super(in);
	}

	/**
	 * Esta función normaliza los extremos del intervalo de ángulo.
	 */
	@Override
	public void normalize(){
		double fe = getFirstExtreme();
		double se = getSecondExtreme();	
		if(fe>360.0||fe<0.0){
			setFirstExtreme(modulo360(fe));
			fe = getFirstExtreme();
		}
		if(se>360.0||se<0.0){
			setSecondExtreme(modulo360(se));
			se = getSecondExtreme();
		} 
		if(fe==360.0&&se!=360.0){
			setFirstExtreme(0.0d);
		}
		if(se==0.0&&fe!=0.0){
			setSecondExtreme(360.0d);
		}
	}
    
	/**
	 * Función para sacar módulo base 360 al valor que recibe.
	 * @param opd el valor al cual se va a sacar módulo base 360
	 * @return el módulo base 360 del valor recibido
	 */
	public static double modulo360(double opd){
		double res;
		if (opd>360.0d){
			res = opd-360.0d;
			while (res>360.0d){
				res=res-360.0d;
			}
			return res;
		}
		else if (opd<-360.0d){
			res = opd+360.0d;
			while (res<-360.0d){
				res=res+360.0d;
			}
			return res+360.0d;
		}
		else if (opd<0.0d){
			return opd+360.0d;
		}
		else 
			return opd;
	}

	/**
	 * Función para sumar dos intervalos de ángulo.
	 * @param ai1 el primer operando de la suma
	 * @param ai2 el segundo operando de la suma
	 * @return el resultado de la suma
	 */
	public static AngleInterval addition(AngleInterval ai1, AngleInterval ai2){
		AngleInterval res = new AngleInterval(Interval.addition(ai1, ai2));
		res.normalize();
		return res;
	}

	/**
	 * Función para restar dos intervalos de ángulo.
	 * @param ai1 el primer operando de la resta
	 * @param ai2 el segundo operando de la resta
	 * @return el resultado de la resta
	 */
	public static AngleInterval subtraction(AngleInterval ai1, AngleInterval ai2){
		AngleInterval res = new AngleInterval(Interval.subtraction(ai1, ai2));
		res.normalize();
		return res;
	}
    
	/**
	 * Esta función suma 180 grados al intervalo de ángulo que recibe. Depende de la
función addition.
	 * @param ai el intervalo de ángulo a utilizar
	 * @return el resultado de sumar 180 grados al intervalo de ángulo
	 */
	public static AngleInterval add180toAI(AngleInterval ai){
		return addition(ai,new AngleInterval(180.0d,180.0d,ai.getFEincluded(),ai.getSEincluded()));
	}

	/**
	 * Esta función verifica si un intervalo de ángulo abarca los 4 cuadrantes, es decir, es
igual a [0, 360].
	 * @return si el intervalo de ángulo abarca los 360 grados
	 */
	public boolean VerifyCase0to360(){
		return getFirstExtreme()==0.0&&getSecondExtreme()==360.0&&getFEincluded()=='['&&getSEincluded()==']';
	}
    
	/**
	 * Función para verificar si un arreglo de intervalos de ángulo en conjunto abarcan
los 360 grados.
	 * @param lais el arreglo de intervalos de ángulo
	 * @return si el arreglo de intervalos, en conjunto abarcan los 360 grados
	 */
	public static boolean verifyCase0to360ofList(AngleInterval []lais){
		AngleInterval []ais = lais;
		int c=0;
		for (AngleInterval ai : ais) {
			if (ai.getFirstExtreme() == 0.0 && ai.getSecondExtreme() == 90.0 && ai.getFEincluded() == '[' && ai.getSEincluded() == ')') {
				c++;
				break;
			}
		}
		for (AngleInterval ai : ais) {
			if (ai.getFirstExtreme() == 90.0 && ai.getSecondExtreme() == 180.0 && ai.getFEincluded() == '[' && ai.getSEincluded() == ')') {
				c++;
				break;
			}
		}
		for (AngleInterval ai : ais) {
			if (ai.getFirstExtreme() == 180.0 && ai.getSecondExtreme() == 270.0 && ai.getFEincluded() == '[' && ai.getSEincluded() == ')') {
				c++;
				break;
			}
		}
		for (AngleInterval ai : ais) {
			if (ai.getFirstExtreme() == 270.0 && ai.getSecondExtreme() == 360.0 && ai.getFEincluded() == '[' && ai.getSEincluded() == ']') {
				c++;
				break;
			}
		}
		return c==4;
	}
    
	/**
	 * Función para unir un arreglo de intervalos de ángulo.
	 * @param res el arreglo de intervalos de ángulo a unir
	 * @return el resultado de la unión
	 */
	public static AngleInterval unionAIs(AngleInterval []res){
		if (res.length==1){
			return res[0];
		}
		if(verifyCase0to360ofList(res)){
	    	return new AngleInterval(0, 360, '[', ']');
		}
		AngleInterval tres = res[0];
		AngleInterval []rest = new AngleInterval[res.length-1];
		System.arraycopy(res, 1, rest, 0, rest.length);
		AngleInterval ai1, ai2;
		while(true){
			for(int i=0; i<rest.length; i++){
				ai1 = tres;
				ai2 = rest[i];
				if ((ai1.getSecondExtreme()==ai2.getFirstExtreme() && ai1.getSEincluded()==')' && ai2.getFEincluded()=='[') || (ai1.getSecondExtreme()==360 && ai2.getFirstExtreme()==0 && ai1.getSEincluded()==']' && ai2.getFEincluded()=='[')){
					ai1.setSecondExtreme(ai2.getSecondExtreme());
					ai1.setSEincluded(ai2.getSEincluded());
					tres = ai1;
					AngleInterval []auxrest = new AngleInterval[rest.length-1];
					System.arraycopy(rest, 0, auxrest, 0, i);
					System.arraycopy(rest, i+1, auxrest, i, rest.length-i-1);
					rest = auxrest;
					break;
				}
			}
			for(int j=0; j<rest.length; j++){
				ai1 = tres;
				ai2 = rest[j];
				if ((ai1.getFirstExtreme()==ai2.getSecondExtreme() && ai2.getSEincluded()==')' && ai1.getFEincluded()=='[') || (ai1.getFirstExtreme()==0 && ai2.getSecondExtreme()==360 && ai2.getSEincluded()==']' && ai1.getFEincluded()=='[')){
					ai1.setFirstExtreme(ai2.getFirstExtreme());
					ai1.setFEincluded(ai2.getFEincluded());
					tres = ai1;
					AngleInterval []auxrest = new AngleInterval[rest.length-1];
					System.arraycopy(rest, 0, auxrest, 0, j);
					System.arraycopy(rest, j+1, auxrest, j, rest.length-j-1);
					rest = auxrest;
					break;
				}
			}
			if (rest.length>0){
				AngleInterval aux = tres;
				tres = rest[0];
				AngleInterval []auxrest = new AngleInterval[rest.length-1];
				System.arraycopy(rest, 1, auxrest, 0, auxrest.length);
				System.arraycopy(auxrest, 0, rest, 0, auxrest.length);
				rest[rest.length-1] = aux;
			}
			else{
				break;
			}
		}
		return tres;
	}
}
