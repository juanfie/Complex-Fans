/**
 * Esta clase se ha implementado para modelar un intervalo, implementa atributos, métodos
y funciones para su manejo. Cabe mencionar que esta clase también sirve para modelar un
intervalo de magnitud; se deja la obligación al usuario de ingresar intervalos de magnitud con
valores positivos, no se promete al usuario que los resultados sean válidos si ingresa valores
negativos
 * @author Manuel Cipriano
 */

public class Interval {
	private double firstExtreme;   		// el primer extremo del intervalo
	private double secondExtreme;		// el segundo extremo del intervalo
	private char feIncluded;			// el lı́mite para el primer extremo del intervalo
	private char seIncluded;			// el lı́mite para el segundo extremo del intervalo
    
	/**
	 * Constructor que inicializa un intervalo de magnitud vacı́o, es decir, (0, 0).
	 */
	public Interval(){
		firstExtreme = 0.0d;
		secondExtreme = 0.0d;
		feIncluded = '(';
		seIncluded = ')';
	}

	/**
	 * Constructor que inicializa un intervalo de magnitud con los valores recibidos para
	sus atributos.
	 * @param fei el lı́mite del primer extremo
	 * @param fe el valor del primer extremo
	 * @param se el valor del segundo extremo
	 * @param sei el lı́mite del segundo extremo
	 */
	public Interval(char fei, double fe, double se, char sei){
		firstExtreme = fe;
		secondExtreme = se;
		feIncluded = fei;
		seIncluded = sei;
	}
		 
	/**
	 * Constructor que inicializa un intervalo de magnitud con los valores recibidos para
	sus atributos, en otro orden.
	 * @param fe el valor del primer extremo
	 * @param se el valor del segundo extremo
	 * @param fei el lı́mite del primer extremo
	 * @param sei el lı́mite del segundo extremo
	 */
	public Interval(double fe, double se, char fei, char sei){
		firstExtreme = fe;
		secondExtreme = se;
		feIncluded = fei;
		seIncluded = sei;
	}
    
	/**
	 * Constructor para inicializar un intervalo de magnitud a partir de otro intervalo de
magnitud. 
	 * @param in el intervalo de magnitud a ser copiado
	 */
	public Interval(Interval in){
		firstExtreme = in.getFirstExtreme();
		secondExtreme = in.getSecondExtreme();
		feIncluded = in.getFEincluded();
		seIncluded = in.getSEincluded();
	}

	/**
	 * Función para asignar el valor del primer extremo.
	 * @param fe el nuevo valor del primer extremo
	 */
	public void setFirstExtreme(double fe){
		firstExtreme = fe;
	}
    
	/**
	 * Función para asignar el valor del segundo extremo.
	 * @param se el nuevo valor del segundo extremo
	 */
	public void setSecondExtreme(double se){
		secondExtreme = se;
	}
    
	/**
	 * Función para asignar el lı́mite del primer extremo.
	 * @param fei el nuevo lı́mite del primer extremo.
	 */
	public void setFEincluded(char fei){
		if(fei=='(' || fei=='[' ){
			feIncluded = fei;
		}
	}

	/**
	 * Función para asignar el lı́mite del segundo extremo.
	 * @param sei el nuevo lı́mite del segundo extremo.
	 */
	public void setSEincluded(char sei){
		if(sei==')' || sei==']'){
			seIncluded = sei;
		}
	}

	/**
	 * Función para obtener el valor del primer extremo.
	 * @return el valor del primer extremo
	 */
	public double getFirstExtreme(){
		return firstExtreme;
	}

	/**
	 * Función para obtener el valor del segundo extremo.
	 * @return el valor del segundo extremo
	 */
	public double getSecondExtreme(){
		return secondExtreme;
	}

	/**
	 * Función para obtener el lı́mite del primer extremo, es decir, regresa ‘(’ o ‘[’.
	 * @return el lı́mite del primer extremo
	 */
	public char getFEincluded(){
		return feIncluded;
	}

	/**
	 * Función para obtener el lı́mite del segundo extremo, es decir, regresa ‘)’ o ‘]’.
	 * @return el limite del segundo extremo
	 */
	public char getSEincluded(){
		return seIncluded;
	}

	/**
	 * Función para normalizar un intervalo de magnitud, es decir, que el primer extremo
sea menor o igual que el segundo extremo.
	 */
	public void normalize(){
		if (firstExtreme>secondExtreme){
			double auxExtreme = firstExtreme;
			char auxLimit = feIncluded;
			firstExtreme = secondExtreme;
			secondExtreme = auxExtreme;
			feIncluded = seIncluded==']'?'[':'(';
			seIncluded = auxLimit=='['?']':')';
		}
	}

	/**
	 * Función para multiplicar un intervalo por una constante.
	 * @param k el factor constante
	 */
	public void byConstant(double k){
		if (k<0){
			double aux = firstExtreme;
			firstExtreme = k*secondExtreme;
			secondExtreme = k*aux;
		}else{
			firstExtreme = k*firstExtreme;
			secondExtreme = k*secondExtreme;
		}
	}

	/**
	 * Función para verificar si un intervalo esta vacı́o.
	 * @return si el intervalo es vacı́o
	 */
	public boolean isEmpty(){
		return firstExtreme==secondExtreme && ((feIncluded=='(' && seIncluded==')') || (feIncluded=='[' && seIncluded==')') || (feIncluded=='(' && seIncluded==']'));
	}
    
	/**
	 * Función para calcular la unión de dos intervalos. Esta función primero verifica si
	existe intersección entre los dos intervalos que recibe, si lo hay realiza la operación
	de la unión, si no lo hay regresa un intervalo vacı́o.
	 * @param in1 el primer intervalo para la unión
	 * @param in2 el segundo intervalo para la unión
	 * @return el resultado de la unión de intervalos
	 */
	public static Interval union(Interval in1, Interval in2){
		if (Interval.intersection(in1, in2).isEmpty())
			return new Interval();
		double a = in1.getFirstExtreme();
		double b = in1.getSecondExtreme();
		double c = in2.getFirstExtreme();
		double d = in2.getSecondExtreme();
		double fe, se;
		char ai = in1.getFEincluded();
		char bi = in1.getSEincluded();
		char ci = in2.getFEincluded();
		char di = in2.getSEincluded();
		char fei, sei;
		if (a<c){
			fe = a;
			fei = ai;
		}
		else if (a>c){
			fe = c;
			fei = ci;
		}
		else{
			fe = a;
			fei = ai==ci?ai:'(';
		}
		if (d<b){
			se = b;
			sei = bi;
		}
		else if (d>b){
			se = d;
			sei = di;
		}
		else{
			se = b;
			sei = bi==di?bi:')';
		}
		return new Interval(fe,se,fei,sei);
	}
    
	/**
	 * Función para calcular la intersección de dos intervalos.
	 * @param in1 el primer intervalo para la intersección
	 * @param in2 el segundo intervalo para la intersección
	 * @return el resultado de la intersección
	 */
	public static Interval intersection(Interval in1, Interval in2){
		double a = in1.getFirstExtreme();
		double b = in1.getSecondExtreme();
		double c = in2.getFirstExtreme();
		double d = in2.getSecondExtreme();
		char p1 = in1.getFEincluded();
		char p2 = in1.getSEincluded();
		char p3 = in2.getFEincluded();
		char p4 = in2.getSEincluded();
	
		if (a==c && b==d){
			if (a==b){
				if (p1=='[' && p3=='[' && p2==']' && p4==']'){
					return new Interval(in1);
				}else{
					return new Interval(0,0,'(',')');
				}
			}else{
				return new Interval(a,b,p1==p3?p1:'(',p2==p4?p2:')');
			}
		}else if (a<c && b<=c){
			if (b==c && p2==']' && p3=='['){
				return new Interval(b,b,p3,p2);
			}else{
				return new Interval(0,0,'(',')');
			}
		}else if (a<=c && b<d){
			if (a==c){
				return new Interval(a,b,p1==p3?p1:'(',p2);
			}else{
				return new Interval(c,b,p3,p2);
			}
		}else if(a<c && b==d){
			return new Interval(c,b,p3,p2==p4?p2:')');
		}else if(a<=c && b>d){
			if (a==c){
				return new Interval(a,d,p1==p3?p1:'(',p4);
			}else{
				return new Interval(in2);
			}
		}else if (a>c && b<=d){
			if (b==d){
				return new Interval(a,b,p1,p2==p4?p2:')');
			}else{
				return new Interval(in1);
			}
		}else if (a<=d && b>d){
			if (a==d){
				if (p1=='[' && p4==']'){
					return new Interval(a,a,p1,p4);
				}else{
					return new Interval(0,0,'(',')');
				}
			}else{
				return new Interval(a,d,p1,p4);
			}
		}else{
			return new Interval(0,0,'(',')');
		}
	}
    
	/**
	 * Función para calcular la negación de un intervalo.
	 * @param in el intervalo a negar
	 * @return el resultado de la negación
	 */
	public static Interval negation(Interval in){
		return new Interval(-in.getSecondExtreme(),-in.getFirstExtreme(),in.getSEincluded()==']'?'[':'(',in.getFEincluded()=='['?']':')');
	}

	/**
	 * Función para sumar dos intervalos
	 * @param in1 el primer intervalo de la suma
	 * @param in2 el segundo intervalo de la suma
	 * @return el resultado de la suma
	 */
	public static Interval addition(Interval in1, Interval in2){
		return new Interval(in1.getFirstExtreme()+in2.getFirstExtreme(),in1.getSecondExtreme()+in2.getSecondExtreme(),in1.getFEincluded()==in2.getFEincluded()?in1.getFEincluded():'(',in1.getSEincluded()==in2.getSEincluded()?in1.getSEincluded():')');
	}

	/**
	 * Función para multiplicar dos intervalos
	 * @param in1 el primer intervalo del producto
	 * @param in2 el segundo intervalo del producto
	 * @return el resultado del producto
	 */
	public static Interval product(Interval in1, Interval in2){
		return new Interval(in1.getFirstExtreme()*in2.getFirstExtreme(),in1.getSecondExtreme()*in2.getSecondExtreme(),in1.getFEincluded()==in2.getFEincluded()?in1.getFEincluded():'(',in1.getSEincluded()==in2.getSEincluded()?in1.getSEincluded():')');
	}

	/**
	 * Función para restar dos intervalos
	 * @param in1 el primer intervalo para la resta
	 * @param in2 el segundo intervalo para la resta
	 * @return el resultado de la resta
	 */
	public static Interval subtraction(Interval in1, Interval in2){
		return new Interval(in1.getFirstExtreme()-in2.getSecondExtreme(),in1.getSecondExtreme()-in2.getFirstExtreme(),in1.getFEincluded()=='['&&in2.getSEincluded()==']'?in1.getFEincluded():'(',in1.getSEincluded()==']'&&in2.getFEincluded()=='['?in1.getSEincluded():')');
	}
    
	/**
	 * Función para dividir dos intervalos
	 * @param in1 el primer intervalo de la division
	 * @param in2 el segundo intervalo de la division
	 * @return el resultado de la division
	 */
	public static Interval division(Interval in1, Interval in2){
		if(in2.getSecondExtreme()==0.0||in2.getFirstExtreme()==0.0){
			System.out.println("División por cero!");
			System.exit(1);
			return new Interval();
		}else{
			return new Interval(in1.getFirstExtreme()/in2.getSecondExtreme(),in1.getSecondExtreme()/in2.getFirstExtreme(),in1.getFEincluded()=='['&&in2.getSEincluded()==']'?in1.getFEincluded():'(',in1.getSEincluded()==']'&&in2.getFEincluded()=='['?in1.getSEincluded():')');
		}
	}

	/**
	 * Función para imprimir la cadena de caracteres que representa un intervalo
	 */
	public void print(){
		System.out.println(toString());
	}

	/**
	 * Función que regresa la representación de un intervalo en cadena de caracteres.
	 * @return la cadena de caracteres que representa el intervalo
	 */
	@Override
	public String toString(){
		return ""+feIncluded+firstExtreme+","+secondExtreme+seIncluded;
	}  
}
