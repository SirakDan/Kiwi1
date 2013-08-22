package business;
import controller.Controller;
import deliver.Deliver;
/*
 * This class parses the given input and output an operation type-based syntax
 * form.
 */

/**
 *
 * @author Kiwi
 */
public class Operaciones {

    public static void orderFormat(String orders) {
    	String phrase = "%^X_ORDEN_SINTAX.\"";

        String[] splitOrders = orders.split(" ");   
        for (int i = 0; i < splitOrders.length - 1; i++) {
            phrase += splitOrders[i] + ", ";
        }
        phrase += splitOrders[splitOrders.length- 1];
        
        phrase += "\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }

    public static void commandFormatExe(String opciones) {
    	// TODO: En la interfaz hay que poner un ejemplo de esto, para recordar las posibles operaciones.
    	String phrase = "%^C_EJECUTA_COMANDO.\"" + opciones + "\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }

    public static void commandFormatSintax(String sintaxis) {
        // TODO: En la interfaz hay que poner un ejemplo de esto, para recordar las posibles operaciones.
    	String phrase = "%^C_SINTAXIS_COMANDO.\"" + sintaxis + "\"";
    	Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }

    public static void failureManagerFormat(String variables) {
        // Ej: variables = S:NUM_EQU|X:REMOTA|Campo5|E:SOC
        String phrase = "%^CAMPOS_GF.\"";
        
        String[] splitvars = variables.split(" ");   
        for (int i = 0; i < splitvars.length - 1; i++) {
            phrase += splitvars[i] + ",";
        }
        phrase += splitvars[splitvars.length- 1];
        
        phrase += "\"";
    	Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }
    
    public static void concatOperationSintax(String variables) {
        // Ej: Variables0 = Input1 = [X:|S:|C:|E:][N:|H:]NAME_op1
        // Ej: Variables1 = Input2 = [X:|S:|C:|E:][N:|H:]NAME_op2
        // Ej: Variables2 = Output = [X:|S:|C:|E:][N:|H:]NAME_res
        
        String phrase = "%^X_OPERACION_SINTAX.\"";
        
        String[] splitvars = variables.split(" "); 
        
        phrase += splitvars[0] + " + " + splitvars[1] + 
                " = " + splitvars[2];
        
        phrase += "\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }
    
    public static void compAsigOperationSintax(String Fields) {
        //Field0 = VariableComp1 = [X:|S:|C:|E:][N:|H:]NAME_op1
        //Field1 = operation = ‘==’, ‘!=’, ‘>=’, ‘<=’, ‘>’ , ‘<’ , ‘$’ y ‘!!’
        //Field2 = VariableComp2 = [X:|S:|C:|E:][N:|H:]NAME_op2
        //Field3 = VariableHit = [X:|S:|C:|E:][N:|H:]NAME_Hit
        //Field4 = VariableMiss = [X:|S:|C:|E:][N:|H:]NAME_Miss
        //Field5 = VariableAsig =[X:|S:|C:|E:][N:|H:]NAME_Asig
        
        String phrase = "%^X_OPERACION_SINTAX.\"";
        
        String[] splitfields = Fields.split(" "); 
        
        phrase += splitfields[0] + " " + splitfields[1] + " " + splitfields[2];        
        for (int i = 0; i < 2; i++) {
            phrase += " # " + splitfields[3+i] + " = " + splitfields[5];
        }
        
        phrase += "\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }
    
    public static void basicProcessingFormat(String Fields) {
        //  %^_TRATAR_.” | %^I_TRATAR_INSERT.”
        //Field(s%3=0) = VariableComp1 = [X:|S:|C:|E:][N:|H:]NAME_op1
        //Field(s%3=1) = operation = ',' ',,'
        //Field(s%3=2) = VariableComp2 = [X:|S:|C:|E:][N:|H:]NAME_op2
        
        String phrase = "%^_TRATAR_.\"";
        
        String[] splitfields = Fields.split(" "); 
        
        for (int i = 0; i < splitfields.length - 3; i += 3) {
            phrase += splitfields[i] + " " + splitfields[i+1] + " " 
                    + splitfields[i+2] + " # ";
        }
        phrase += splitfields[splitfields.length - 3] + " " 
                + splitfields[splitfields.length - 2] + " " 
                + splitfields[splitfields.length - 1];
        
        phrase += "\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }
    
    public static void newProcessingFormat(String Fields) {
        //Field(s%4=0) = VariableComp1 = [X:|S:|C:|E:][N:|H:]NAME_op1
        //Field(s%4=1) = operation = '==' '!=' '<' '>' '>=' '<='
        //Field(s%4=2) = VariableComp2 = [X:|S:|C:|E:][N:|H:]NAME_op2
        //Field(s%4=3) = VariableAsig = [A|B|C|...]
        //Field(s-1) = VariableForm = Ej: !(A|B)^C
        
        String phrase = "%^_TRATAR_.\"";
        
        String[] splitfields = Fields.split(" "); 

        for (int i = 0; i < splitfields.length - 5; i += 4) {
            phrase += splitfields[i] + " " + splitfields[i+1] + " " 
                    + splitfields[i+2] + " = " + splitfields[i+3] + " ; ";
        }
        phrase += splitfields[splitfields.length - 5] + " " 
                + splitfields[splitfields.length - 4] + " " 
                + splitfields[splitfields.length - 3] + " = " 
                + splitfields[splitfields.length - 2] + " # " 
                + splitfields[splitfields.length - 1];
        
        phrase += "\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }
    
    //before 2 ArrayList (ArrayList ReplaceVars, ArrayList NewVars)
    public static void associationFormat(String vars) {
        // ReplaceVars = variables que se van a sustituir
        // NewVars = Variables nuevas que cogen el contenido de las ReplaceVars
        
        String phrase = "%^X_ASOCIAR_SINTAX.\"";
        
        String[] parts = vars.split(" ; "); 
        String[] ReplaceVars = parts[0].split(" ");
        String[] NewVars = parts[1].split(" "); 
        
        for(int i = 0; i < ReplaceVars.length - 1; i++) {
            phrase += ReplaceVars[i] + ", ";
        }
        phrase += ReplaceVars[ReplaceVars.length - 1] + " # ";

        for(int i = 0; i < NewVars.length - 1; i++) {
            phrase += NewVars[i] + ", ";
        }
        phrase += NewVars[NewVars.length - 1];

        phrase += "\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }
    
    public static void specialEventFormat(String template) {
        String phrase = "_SPECIAL_.\"" + template +"??\"";
        Deliver.deliver(Deliver.SYNTAX_AREA, phrase);
    }
}
