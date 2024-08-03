package Com.Example.Demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionData {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tid;

	
	
	private String txnId;

	private String txnDate;

	private double creditAmount;

	private double debitAmount;

	private String txnStatus;

	private String txnAccountTo;
	
	
	
	
	
	
	

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getTxnAccountTo() {
		return txnAccountTo;
	}

	public void setTxnAccountTo(String txnAccountTo) {
		this.txnAccountTo = txnAccountTo;
	}

	
	
	@Override
	public String toString() {
		return "TransactionData [tid=" + tid + ", txnId=" + txnId + ", txnDate=" + txnDate + ", creditAmount="
				+ creditAmount + ", debitAmount=" + debitAmount + ", txnStatus=" + txnStatus + ", txnAccountTo="
				+ txnAccountTo + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
