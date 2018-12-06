package com.youweather.sgg;

public class CheckInVerify {

	String campo,name,conds,loc,tempc,temp;
	
	public CheckInVerify() {
		
	}
	
	   /**Metodo che testa la correttezza (sia semantica che sintattica) 
	    * di tutti i campi inseriti
	    * @return
	    */
	public String CheckData() {
		String message = "*";
		name = CheckIn.getNome();
		loc  = CheckIn.getLocalita();
		conds= CheckIn.getCondizioni();
		temp = CheckIn.getTemperatura();
		
		
	    if(!CheckIsEmpty(name)) message += "Indica il tuo Nome \n";
		if(!CheckLenghtName(name)) message += "Nome troppo Corto o Lungo \n"; 
		if(!CheckName(name)) message += "Il nome puo' contenere solo numeri e lettere \n";
		if(!CheckIsEmpty(loc)) message += "Devi specificare la localita' \n";
	    if(!CheckIsEmpty(temp)){ 
	    	 message += "Devi specificare la temperatura \n";
	    }
	    else if(!CheckTemperature(temp)) message +="La temperatura deve essere compresa tra -459 e +100 \n";
		if(!CheckIsEmpty(conds)) message += "Inserisci una breve descrizione del tempo \n";
		if(!CheckLenghtConditions(conds)) message += "Hai inserito troppi caratteri o troppo pochi :P \n";
		
		return message;
	}

	//torna false se il campo in oggetto � vuoto
	public boolean CheckIsEmpty(String campo) {
		return !campo.isEmpty();
	}
	

	//Testa che il nome in oggetto sia almeno di 4 caratteri e massimo 32
	public boolean CheckLenghtName(String check) {
		
		if(check.length() < 4 || check.length() > 32) return false;
		return true;
	}

	//Testa che il campo condizioni sia almeno 8 caratteri e massimo 128
	public boolean CheckLenghtConditions(String check) {
		if(check.length() < 8 || check.length() > 128) return false;
		return true;
	}
	
	
    //Testa che il nome contenga solo lettere e numeri
	public boolean CheckName(String check) {
		if(!check.matches("^[a-zA-Z]+$")) return false;
		return true;
	}
	// Testa che la temperatura contenga solo numeri
	public boolean CheckTemperatureIsCorrect(String temperatura){
        if (!temperatura.matches("^[0-9]+$")) return false;	
		return true;
	}
	

	
	//Testa che la temperatura rientri nei limiti della scala celsius
	public boolean CheckTemperature(String temperatura) {
		int t = Integer.valueOf(temperatura);
		if(t < -459 || t > 100) return false;
		return true;
	}


}
