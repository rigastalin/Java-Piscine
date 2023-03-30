package ex04;

import java.util.UUID;
public interface TransactionsList {
    void addTrans(Transaction transaction);
    void deleteTrans(UUID id);
    Transaction[] toArray();

}
