package Com.Example.Demo.Repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import Com.Example.Demo.Entity.AccountData;

@Repository
public interface AccountTxnRepositroy  extends JpaRepository<AccountData, Integer>{

	
	AccountData findByStatus(boolean status);

	AccountData findByAccountNumber(String accNumber);
	
	AccountData findByAccountHolderName(String accountHolderName);
	
	
	
	@Query(value ="select email from account_data ",nativeQuery = true)
	List<String> findEmail();
	
	

	
	
	
	
}
