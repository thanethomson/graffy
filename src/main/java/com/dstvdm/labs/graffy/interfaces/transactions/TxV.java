package com.dstvdm.labs.graffy.interfaces.transactions;

import com.dstvdm.labs.graffy.Transaction;

/**
 * A transactional wrapper that doesn't require a return value.
 */
public interface TxV {
  
  /**
   * Provides the executable code that should be wrapped in a transaction.
   * @param t The transaction object that will be passed in.
   * @throws Throwable
   */
  public void execute(Transaction t) throws Throwable;

}
