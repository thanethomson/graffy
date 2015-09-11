package com.dstvdm.labs.graffy.interfaces.transactions;

import com.dstvdm.labs.graffy.Transaction;

/**
 * Allows one to wrap one's code in a transaction.
 * @param <R> The return type of your transaction return value.
 */
public interface Tx<R> {
  
  /**
   * Provides the executable code that should be wrapped in a transaction.
   * @param t The transaction object that will be passed in.
   * @return
   */
  public R execute(Transaction t) throws Throwable;

}
