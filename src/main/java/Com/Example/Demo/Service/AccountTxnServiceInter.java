package Com.Example.Demo.Service;

import java.util.List;

import Com.Example.Demo.Entity.AccountData;
import Com.Example.Demo.Entity.TransactionData;

public interface AccountTxnServiceInter {
	
	
	
	int createAccount(AccountData accountData);

	AccountData	 getAccountDetailsUsngAccId(int accID);
	
	AccountData getAccDataUserName(String accountHolderName);
	
	void DeleteAccountDataUsingId(int id);
	
	AccountData updateAccountDataDetail(AccountData accData);
	
	void   addTransactionData(TransactionData transactionData ,String accnum);
	
	
	void sendAttachment();
	
	List<AccountData> generateCSVFileData();
	
	
	
	
}
