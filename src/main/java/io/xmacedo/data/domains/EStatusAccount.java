package io.xmacedo.data.domains;

public enum EStatusAccount {

  DESATIVADA (0, true),
  IDLE (1, true),
  INVALID_STATUS (-1, false);

  //--------------------------------
  private int value;
  private boolean consistentState;


  EStatusAccount(int value, boolean consistentState) {
    this.value = value;
    this.consistentState = consistentState;
  }

  public int getValue() {
    return value;
  }
  
  public boolean isConsistentState() {
    return consistentState;
  }
  
  public static EStatusAccount fromValue(int value) {
    
    for (EStatusAccount statusConta : EStatusAccount.values()) {
      if (statusConta.getValue() == value) {
        return statusConta;
      }
    }
    
    return EStatusAccount.INVALID_STATUS;
  }
  
  
}
