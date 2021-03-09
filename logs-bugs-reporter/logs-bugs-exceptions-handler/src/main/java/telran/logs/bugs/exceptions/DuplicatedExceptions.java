package telran.logs.bugs.exceptions;

@SuppressWarnings("serial")
public class DuplicatedExceptions extends RuntimeException {
public DuplicatedExceptions(String message) {
  super(message);
}
}
