import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

class henkan{

  public static void main(String args[]){
    String[] AA     = new String[256];
    String[] Master = new String[256];
    String[] Opr    = new String[256];
    int i;
    int j=0;
    int k=0;
    int l=0;
    int F=0;

    if(args[0].equals("infix") ){//1
		for(i = 1; i < args.length; i++){//2
        if(CheckNumber(args[i])){//mosi suuzi nara 3
          Master[j] = args[i];
          j = j + 1;
        }//3
		  else{//suuzi de nai nara 3
			 if(k == 0){//hitotume 4
				Opr[k] = args[i];
				k = k + 1;
			 }//4
			 else if(k != 0){//4
				if(PriorityCheck(Opr[k-1], args[i])){//yuusenndo ga takai nara 5
				  Opr[k] = args[i];
				  k = k + 1;
				}//5
				else{//hikui nara 5
				  for(l=k;l==0; l--){//6
					 Master[j] = Opr[l];
					 j = j + 1;
				  }//6
				  Opr[l] = args[i];
				  k = 1;
				}//5
			 }//4
		  }//3
		}//2
		k=k-1;
		for(; k>=0 ;k--){
		  Master[j] = Opr[k];
		  j = j + 1;
		}
	 }
    else if(args[0].equals("polish")){
      for(i = 1; i < args.length; i++){
        Master[j] = args[i];
        j = j + 1;
      }
    }
    else{
    System.out.printf("infix demo polish demo nai\n");
    }

    for(i=0; i < args.length-1; i++){
      if(CheckNumber(Master[i])){
        AA[i] = OpeNumber(Master[i]);
      }
      else{
        AA[i] = CheckOperator(Master[i]);
      }
    }


    F = i;

    FileCreate();

    try{
      File Wfile = new File("tests.txt");

      if(checkBeforeWritefile(Wfile)){
        FileWriter filewriter = new FileWriter(Wfile);
			BufferedWriter bw = new BufferedWriter(filewriter);

        for(i=0; AA[i]!=null; i++){
          bw.write(AA[i]+"\n");
        }

        bw.write("print, _\n");
        bw.write("halt, _\n");

        bw.close();
      }
      else{
        System.out.println("fairu ni kakikome masrenn");
      }
    }
    catch(IOException e){
      System.out.println(e);
    }

  }


//
  private static boolean checkBeforeReadfile(File file){
    if(file.exists()){
      if(file.isFile() && file.canRead()){
        return true;
      }
    }
    return false;
  }

    //suuzi no kakuninn
  public static boolean CheckNumber(String number){
    try{
      Integer.parseInt(number);
    }
    catch(NumberFormatException e){
      return false;
    }
    return true;
  }

//suuzi ni meirei wo tuika
  private static String OpeNumber(String ON){
    return ("ldi, " + ON);
  }

    //ennzannsi no kakuninn to meirei no tuika
  public static String CheckOperator(String opr){
    if(opr != null || opr.equals("+")){
      opr = "opr2, add";
      return opr;
    }
    else if(opr != null || opr.equals("-")){
      opr = "opr2, sub";
      return opr;
    }
    else if(opr != null || opr.equals("*")){
      opr = "opr2, mul";
      return opr;
    }
    else if(opr != null || opr.equals("/")){
      opr = "opr2, dvd";
      return opr;
    }
    else{
      return opr = "DAME!!!";
    }
  }

//fairu no sakusei
  public static void FileCreate(){
    File newfile = new File("tests.txt");
    try{
	   if(newfile.createNewFile()){
        System.out.println("ファイルの作成に失敗しました。");
      }else{
        System.out.println("ファイルの作成に成功しました。");
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }

  public static boolean PriorityCheck(String X, String Y){
    int a;
    int b;
    a = Priority(X);
    b = Priority(Y);
    if(a >= b){
      return true;
    }
    else{
    return false;
    }
  }

  public static int Priority(String Z){
    if(Z.equals("+")){
      return 0;
    }
    else if(Z.equals("-")){
      return 0;
    }
    else if(Z.equals("*")){
      return 1;
    }
    else if(Z.equals("/")){
      return 2;
    }
    return -1;
  }

  private static boolean checkBeforeWritefile(File Wfile){
    if(Wfile.exists()){
      if(Wfile.isFile() && Wfile.canWrite()){
        return true;
      }
    }
    return false;
  }

}