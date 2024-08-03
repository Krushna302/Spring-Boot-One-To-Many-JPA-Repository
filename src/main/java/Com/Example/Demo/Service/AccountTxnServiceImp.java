package Com.Example.Demo.Service;

import java.io.File;
import java.lang.module.FindException;
import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import Com.Example.Demo.Entity.AccountData;
import Com.Example.Demo.Entity.TransactionData;
import Com.Example.Demo.Exception.ExceptionResponse;
import Com.Example.Demo.Repository.AccountTxnRepositroy;




@Service
public class AccountTxnServiceImp implements AccountTxnServiceInter{
	
	
	
	
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	private AccountTxnRepositroy accountTxnRepositroy;
	
	/*
	
	public static int number=0000001;
	public static String generateAccountNumber() {
		

		
        String prefix="SBI";
        
        number++;
        
       String formattedNumber = String.format("%07d", number++);
       
       String accountNumber = prefix + formattedNumber;
        
        return accountNumber ;

		*/
	
	


		/*
		
		

		Random random = new Random();
			
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
        	sb.append(random.nextInt(7));
        }
        String pr=sb.toString();

        String prefix = "SBI";
        
        
        
        String accountNumber =prefix + pr;
        
        
         return accountNumber;
		 
	}

	
		   String accountNumber = generateAccountNumber();
	        
	        // Set accountNumber
	        accountData.setAccountNumber(accountNumber);
	*/
	
	
	

	
	
	
	
	@Override
	public int createAccount(AccountData accountData) {
		
		
		    long count =accountTxnRepositroy.count();
		

		    
		    System.out.println("DB count"+count);
		    if(count == 0) {
	
		    	count +=1; 	
		    	
		    	
		    }else if(count > 0){                 // Data Base Count
		    	
		    	count++;
		    	
		    	
		    	
		    }
		    
		    accountData.setAccountNumber("SBIN"+count);
		    
		    
		
									//currently date and time display
			Date date = new Date(System.currentTimeMillis()); 
			
			//SimpleDateFormat is class create date and time format
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy HH:MM:SS");
			
			//format method display String Data
			String openingDate = dateFormat.format(date);
			
			//format String data set openingDate
			accountData.setAccountOpeningDate(openingDate);
			//active data
			accountData.setStatus(true);
			
			
	
			//  accountTxnRepository  save Data accountData return accountdata
		AccountData accountData1 =	accountTxnRepositroy.save(accountData);
			
	   
		
		if(accountData1 !=null) {
		
			//SimpleMailmessage is a class
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo("pranaydalvi122@gmail.com","uiuxvivekk@gmail.com");
		
		message.setSubject("Thanks For Creating Account. ");
		
		message.setText(" Hi Team ,Thank You For Getting Our Service Your Account Successfully Created.");
		
		javaMailSender.send(message);
		
		
		
		}
		
		// return getId Data based store than return ID 1 and get details in controller
		return accountData1.getId();
	}
	
	

	@Override
	public AccountData getAccountDetailsUsngAccId(int accID) {
		
		
		// add on accNumber always same name
		String accnumber ="SBIN"+accID;
		
			
		
				
	// return accountData															// add accnumber 
	AccountData accountData = accountTxnRepositroy.findByAccountNumber(accnumber)	;	
		

	
	
		return accountData;
	}


	@Override
	public AccountData getAccDataUserName(String accountHolderName) {
		
		
		AccountData accountdata=null;
		
		try {
		System.out.println("Fetching AccountData Details :-"+accountHolderName);
		
	 accountdata =	accountTxnRepositroy.findByAccountHolderName(accountHolderName);
	
	 
	   System.out.println( accountdata.getAccountHolderName());
	    
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
			
			throw new ExceptionResponse("In AccountData Invalid Account Holder Name");
		}

	
	
		return accountdata;
	}



	
	@Override
	public void DeleteAccountDataUsingId(int id) {

		
		System.out.println("Delete AccountData Using ID :-"+id);
		
		accountTxnRepositroy.deleteById(id);
		
		
		
	}



	@Override
	public AccountData updateAccountDataDetail(AccountData accData) {
		
		System.out.println("Update AccountData :-"+accData);
		
	AccountData accountData =	accountTxnRepositroy.save(accData);
		
		return accountData;
	}



	@Override
	public void addTransactionData(TransactionData transactionData, String accnum) {
		
		
		AccountData accountData =accountTxnRepositroy.findByAccountNumber(accnum);
		
		if(accountData!=null) {
			
		
			Random random = new Random();			// random class using create object 

			
			LongStream longstre =random.longs(1,0,9999999);			//longstream using method and random refer using longs method

			long l = longstre.findFirst().getAsLong();//
			
			String str =Long.toString(l);


			 //store data TxnId
			 transactionData.setTxnId(str);
		
		Date date = new Date(System.currentTimeMillis());		// Date class using create object and currentTimeMillis 

		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy HH:MM:SS");		//SimpleDateFormat is class using create object and create date and time

		
		String txtdate =dateFormat.format(date); 		//Date and time store format method

		
		transactionData.setTxnDate(txtdate);		//trasactionData using giving setTxnDate variable and store txtdate
		
		if(transactionData.getCreditAmount()>0) {
			double	balance ;
			
			balance=accountData.getTotalBalance()+transactionData.getCreditAmount();
			accountData.setTotalBalance(balance);
			transactionData.setTxnStatus("Success");
			
		}else if(transactionData.getDebitAmount()>0) { // Debit Transaction
			if(accountData.getTotalBalance()>=transactionData.getDebitAmount()) {
				
			if(addotheraccounttranscation(transactionData.getTxnAccountTo())) {
				
				double	balance ;
			balance =accountData.getTotalBalance()-transactionData.getDebitAmount();
			accountData.setTotalBalance(balance);
			transactionData.setTxnStatus("Success");
				
			}else {
				transactionData.setTxnStatus("Account Not Exists"); // account not exists 
				
			}
			
			}
			else {
				
				
				transactionData.setTxnStatus("Failed"); // balance not 
			}
			
		}
				
		accountData. getTransaDataList().add(transactionData); 		// accountData refer using getransaDataList and add method using store transcationData

		accountTxnRepositroy.save(accountData);		// accountTxnRepository using and save method using store accountData 

		
	}


	}

	private boolean addotheraccounttranscation(String AnotherAccounNumber ) {


	AccountData accountData = new AccountData();
	accountData.setAccountNumber("1234567");
	
	if(AnotherAccounNumber.equals(accountData.getAccountNumber())){
		
		return true;
		
	}
	
	return false;
		
	}



	@Override
	public void sendAttachment() {
		List<String>  emailList = accountTxnRepositroy.findEmail();
		
		System.out.println(emailList.toString());
		
		String[] strArr = new String[emailList.size()];
		
				
		for(int i=0;i<emailList.size();i++) {
			
			strArr[i]=emailList.get(i);
			
		}
				
		
		try {
		
		MimeMessage msg =javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(msg ,true);
			
		helper.setTo("pranaydalvi122@gmail.com"); // personal mail send using only mail
		
		helper.setText("Mail with Attachment.");
		
		helper.setSubject("Mail Sending");
		
		helper.addAttachment("Notification.pdf",new  File("C:\\Users\\HP\\OneDrive\\Pictures\\Window_Pictures\\w1.jpg"));
		
		javaMailSender.send(msg);
			
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		
	}



	@Override
	public List<AccountData> generateCSVFileData() {
		
		//findAll method return List<AccountData> and findAll method Sort by email and accountNumber using ascending order
		
		List<AccountData> list = accountTxnRepositroy.findAll(Sort.by("email").and(Sort.by("accountHolderName")).descending());
		
		System.out.println(list);
		
		return list;             //return list
		
	} 


	

	
	
	
	
	
	

}
