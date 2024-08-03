package Com.Example.Demo.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import Com.Example.Demo.Entity.AccountData;
import Com.Example.Demo.Entity.TransactionData;
import Com.Example.Demo.Service.AccountTxnServiceInter;

@RestController
public class AccountTxnController {
	
	
	
	
	
	@Autowired
	private AccountTxnServiceInter accountTxnServiceInter ;
	
	
	
	@PostMapping(value = "/createAccount")
	public ResponseEntity<String> createAccount(@RequestBody AccountData accountData){
		
		System.out.println("Check Create Account :- "+accountData);
		
	int i = accountTxnServiceInter.createAccount(accountData);
		
	if(i > 0) {
		
		return new ResponseEntity<String>("Account Created Successfully..!! ", HttpStatus.CREATED);
	}
	else {
		
		return new ResponseEntity<String>("Account Not Create..!!", HttpStatus.NOT_FOUND);
		
	}
	
		
	}
		
	
	@GetMapping(value="/getAccountDetail/{accID}")
	public ResponseEntity<AccountData> getAccountDetailUsingAccId(@PathVariable int accID){
		
		System.out.println("Check Account Data :-"+accID);
		
	AccountData  accountData =	accountTxnServiceInter.getAccountDetailsUsngAccId(accID);
		
	return new ResponseEntity<AccountData>(accountData, HttpStatus.OK);
		
	}
	
	
	
	@GetMapping(value="/getAccDataUserName/{accountHolderName}")
	public ResponseEntity<AccountData> getAccDataUserName(@PathVariable String accountHolderName){
		
		System.out.println("Get Account Data Using Name :-"+accountHolderName);
		
	AccountData accountdata =	accountTxnServiceInter.getAccDataUserName(accountHolderName);
		
		
		return new ResponseEntity<AccountData>(accountdata, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/deleteAccData/{id}")
	public ResponseEntity<String> DeleteAccountDataUsingId(@PathVariable int id){
		
		System.out.println("Check AccountData Using Id :-"+id);
		
			accountTxnServiceInter.DeleteAccountDataUsingId(id);
	
	  
		return new ResponseEntity<String>(" Delete AccountData Successfully Using :-"+id, HttpStatus.OK);
		
		
		
	}
	
	@PutMapping(value="/updateAccountData")
	public ResponseEntity<String> updateAccountDataDetail(@RequestBody AccountData accData){
		
		System.out.println("Check And Update AccountData :-"+accData);
		
		
	AccountData accountData =	accountTxnServiceInter.updateAccountDataDetail(accData);
		
		if(accountData != accData) {
		
			return new ResponseEntity<String>(" Update AccountData Successfully", HttpStatus.OK);
			
		}
		else {
			
			return new ResponseEntity<String>("AccountData Not Update Successfully", HttpStatus.NOT_FOUND);
			
			
		}

	}
	
	
	
	
	@PostMapping(value="/txtData/{accnum}")
	public ResponseEntity<String> addTransactionData(@RequestBody TransactionData transactionData ,@PathVariable String accnum){
		
		
		System.out.println("Check account number and transactionData :-"+transactionData+" "+accnum);
		
		accountTxnServiceInter.addTransactionData(transactionData, accnum);
		
		return new ResponseEntity<String>("Opreation Done.", HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "/sendMailAttachment")
	public ResponseEntity<String> sendAttachment(){
		
		accountTxnServiceInter.sendAttachment();
		
		return new ResponseEntity<String>("Mail Send Done", HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value="/generateCSV")
	public void generateCSVFileData(HttpServletResponse response) throws IOException {
		
		
		List<AccountData> list = accountTxnServiceInter.generateCSVFileData();
		
		
		Date date = new Date(System.currentTimeMillis());
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-mm-yy HH:mm:ss");
		
		String timeDate = dateformat.format(date);
		
		
		
		
		
		response.setContentType("text/cvs");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=accountData_" +timeDate+ ".csv";
		
		
		response.setHeader(headerKey, headerValue);//response set headerKey And headerValue
		
		ICsvBeanWriter csvwrite = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
		
		
		String[] csvName= {"ID","ACCOUNTNUMBER","ACCOUNTTYPE","IFSCCODE","ACCOUNTHOLDERNAME","BANKNRANCH","TOTALBALANCE"};
		
		String[] csvMapping= {"id","accountNumber","accountType","ifscCode","accountHolderName","bankbranch","totalBalance"};
		
		csvwrite.writeHeader(csvName);
		
		
		
		for(AccountData accountData:list) {
			
			csvwrite.write(accountData, csvMapping);
		
		}
		
		csvwrite.close();
		
		
	}
	
	
	
	
	
	
	
	

}
