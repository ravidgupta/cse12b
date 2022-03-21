//-----------------------------------------------------------------------------
// Ravi Gupta
// radgupta
// CMPS12B/M
// 5/4/19
// DuplicateKeyException.java
// Creates an exception to be used in Dictionary
//-----------------------------------------------------------------------------

public class DuplicateKeyException extends RuntimeException{
   public DuplicateKeyException(String key){
         super(key);
   }
}
