/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg02_multiplicacion;

import Funciones.IOBinFile;
import Funciones.MyListArgs;
import Funciones.MySintaxis;
import pkg00001_threads_lib.SimpleThreads;

/**
 *
 * @author miguel
 */
public class Multiplicacion {
    String   TMP;
    String   JAR;
    String   OUT;
    int      op2;
    int      mul;
    int      op1;
    int      sum;
    
    
    
    Multiplicacion(String[]args){
        String        ConfigFile = "";
        
        MyListArgs    Param         ;
        
        Param      = new MyListArgs(args)                  ;
        ConfigFile = Param.ValueArgsAsString("-CONFIG", "");
        
        if (!ConfigFile.equals("")) 
        {
            Param.AddArgsFromFile(ConfigFile);
           }//fin if
        
        String Sintaxis   = "-OP1:int -OP2:int -OUT:str -TMP_FILE:str -JAR_SUMA:str";
        MySintaxis Review = new MySintaxis(Sintaxis, Param);
        //PARAMETROS FORZOSOS   
        this.TMP  = Param.ValueArgsAsString ("-TMP_FILE", "");
        this.JAR  = Param.ValueArgsAsString ("-JAR_SUMA", "");
        this.OUT  = Param.ValueArgsAsString (     "-OUT", "");
        this.op1  = Param.ValueArgsAsInteger(     "-OP1",  0);     
        this.op2  = Param.ValueArgsAsInteger(     "-OP2",  0);
        
        this.sum  = this.op2;
    }
    
    public void multiplica()
    {
        String config;
        
        for (int i = 0; i < op2-2; i++) {
            config = "-OP1 "+op1+" -OP2 "+sum+" -OUT "+TMP;
            System.out.println("java -jar "+JAR+" "+config);
            SimpleThreads.executeUNIXScript("java -jar "+JAR+" "+config);    
            sum = (int)IOBinFile.ReadBinFloatFileIEEE754(TMP)[0];                      
        }
        System.out.println(this.op1+"*"+this.op2+" = "+sum);
    }
    
    public void saveMultiplicacion(){
        IOBinFile.WriteBinFloatFileIEEE754(OUT, new float[]{mul}); 
    }
}
